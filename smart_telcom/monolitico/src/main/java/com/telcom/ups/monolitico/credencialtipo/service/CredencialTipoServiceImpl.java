package com.telcom.ups.monolitico.credencialtipo.service;

import com.telcom.ups.monolitico.credencialtipo.mapper.CredencialTipoMapper;
import com.telcom.ups.monolitico.credencialtipo.repository.CredencialTipoCrudRepository;
import com.telcom.ups.data.dto.CredencialTipoDTO;
import com.telcom.ups.data.entities.CredencialTipo;
import com.telcom.ups.data.read.CredencialTipoRead;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import com.telcom.ups.monolitico.util.response.error.NotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CredencialTipoServiceImpl implements CredencialTipoService {

    @Autowired
    private CredencialTipoCrudRepository credencialTipoCrudRepository;

    @Autowired
    private CredencialTipoMapper credencialTipoMapper;

    @Override
    public Page<CredencialTipoRead> findAll(Pageable pageable) {
        return credencialTipoCrudRepository.findAll(pageable).map(credencialTipo -> credencialTipoMapper.toCredencialTipoRead(credencialTipo));
    }

    @Override
    public Optional<CredencialTipoRead> getOne(Integer id) {
        Optional<CredencialTipo> credencialTipoDB = credencialTipoCrudRepository.findById(id);
        if (credencialTipoDB.isPresent()) {
            return credencialTipoDB.map(credencialTipo -> credencialTipoMapper.toCredencialTipoRead(credencialTipo));
        }
        throw new NotFoundExeption("Tipo con id " + id + " no encontrado");
    }

    @Override
    public CredencialTipoRead save(CredencialTipoDTO credencialTipoDTO) {
        Optional<CredencialTipo> credencialTipoDB = credencialTipoCrudRepository.findByNombre(credencialTipoDTO.getNombre());
        if (!credencialTipoDB.isPresent()) {
            return credencialTipoMapper.toCredencialTipoRead(credencialTipoCrudRepository.save(credencialTipoMapper.toCredencialTipo(credencialTipoDTO)));
        }
        throw new BadRequestException("Tipo con nombre " + credencialTipoDTO.getNombre() + " ya esta registrado");
    }

    @Override
    public boolean delete(Integer id) {
        Optional<CredencialTipo> credencialTipoDB = credencialTipoCrudRepository.findById(id);
        if (credencialTipoDB.isPresent()) {
            credencialTipoCrudRepository.deleteById(id);
            return true;
        }
        throw new NotFoundExeption("Tipo con id " + id + " no encontrado para eliminar");
    }

    @Override
    public CredencialTipoRead update(CredencialTipoDTO credencialTipoDTO, Integer id) {
        Optional<CredencialTipo> credencialTipoDB = credencialTipoCrudRepository.findById(id);
        if (credencialTipoDB.isPresent()) {
            CredencialTipo newCredencialTipo = credencialTipoMapper.toCredencialTipo(credencialTipoDTO);
            CredencialTipo credencialTipo = credencialTipoDB.get();
            credencialTipo.setNombre(newCredencialTipo.getNombre());
            credencialTipo.setDescripcion(newCredencialTipo.getDescripcion());
            credencialTipo.setUpdatedAt(LocalDateTime.now());
            return credencialTipoMapper.toCredencialTipoRead(credencialTipoCrudRepository.save(credencialTipo));
        }
        throw new NotFoundExeption("Tipo con id " + id + " no encontrado para actualizar");
    }
}
