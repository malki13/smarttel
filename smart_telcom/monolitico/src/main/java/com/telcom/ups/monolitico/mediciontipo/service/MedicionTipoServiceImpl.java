package com.telcom.ups.monolitico.mediciontipo.service;

import com.telcom.ups.data.dto.MedicionTipoDTO;
import com.telcom.ups.data.entities.MedicionTipo;
import com.telcom.ups.data.read.MedicionTipoRead;
import com.telcom.ups.monolitico.mediciontipo.mapper.MedicionTipoMapper;
import com.telcom.ups.monolitico.mediciontipo.repository.MedicionTipoCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import com.telcom.ups.monolitico.util.response.error.NotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MedicionTipoServiceImpl implements MedicionTipoService {

    @Autowired
    private MedicionTipoCrudRepository medicionTipoCrudRepository;

    @Autowired
    private MedicionTipoMapper medicionTipoMapper;

    @Override
    public Page<MedicionTipoRead> findAll(Pageable pageable) {
        return medicionTipoCrudRepository.findAll(pageable).map(medicionTipo -> medicionTipoMapper.toMedicionTipoRead(medicionTipo));
    }

    @Override
    public Optional<MedicionTipoRead> getOne(Integer id) {
        Optional<MedicionTipo> medicionTipoDB = medicionTipoCrudRepository.findById(id);
        if (!medicionTipoDB.isPresent())
            throw new NotFoundExeption("MedicionTipo con id " + id + " no encontrado");
        return medicionTipoDB.map(medicionTipo -> medicionTipoMapper.toMedicionTipoRead(medicionTipo));
    }

    @Override
    public MedicionTipoRead save(MedicionTipoDTO medicionTipoDTO) {
        Optional<MedicionTipo> medicionTipoDB = medicionTipoCrudRepository.findByCodigo(medicionTipoDTO.getCodigo());
        if (medicionTipoDB.isPresent()) {
            throw new BadRequestException("Medicion con codigo " + medicionTipoDTO.getCodigo() + " ya esta registrado");
        }
        Optional<MedicionTipo> medicionTipoNombre = medicionTipoCrudRepository.findByNombre(medicionTipoDTO.getNombre());
        if (medicionTipoNombre.isPresent())
            throw new BadRequestException("MedicionTipo con nombre " + medicionTipoDTO.getNombre() + " ya esta registrado");

        MedicionTipo medicionTipo = medicionTipoMapper.toMedicionTipo(medicionTipoDTO);
        return medicionTipoMapper.toMedicionTipoRead(medicionTipoCrudRepository.save(medicionTipo));
    }

    @Override
    public boolean delete(Integer id) {
        Optional<MedicionTipo> medicionTipoDB = medicionTipoCrudRepository.findById(id);
        if (!medicionTipoDB.isPresent())
            throw new NotFoundExeption("MedicionTipo con id " + id + " no encontrado para eliminar");
        medicionTipoCrudRepository.deleteById(id);
        return true;
    }

    @Override
    public MedicionTipoRead update(MedicionTipoDTO medicionTipoDTO, Integer id) {
        Optional<MedicionTipo> medicionTipoDB = medicionTipoCrudRepository.findById(id);
        if (!medicionTipoDB.isPresent())
            throw new NotFoundExeption("MedicionTipo con id " + id + " no encontrada para actualizar");
        MedicionTipo med = medicionTipoDB.get();
        MedicionTipo newMedicionTipo = medicionTipoMapper.toMedicionTipo(medicionTipoDTO);
        med.setCodigo(newMedicionTipo.getCodigo());
        med.setNombre(newMedicionTipo.getNombre());
        med.setDescripcion(newMedicionTipo.getDescripcion());
        med.setUpdatedAt(LocalDateTime.now());
        return medicionTipoMapper.toMedicionTipoRead(medicionTipoCrudRepository.save(med));
    }

    @Override
    public Optional<MedicionTipoRead> findByCodigo(String codigo) {
        Optional<MedicionTipo> medicionTipoDB = medicionTipoCrudRepository.findByCodigo(codigo);
        if (medicionTipoDB.isPresent()) {
            return medicionTipoDB.map(medicionTipo -> medicionTipoMapper.toMedicionTipoRead(medicionTipo));
        }
        throw new NotFoundExeption("Medicion con codigo " + codigo + " no encontrado");
    }
}
