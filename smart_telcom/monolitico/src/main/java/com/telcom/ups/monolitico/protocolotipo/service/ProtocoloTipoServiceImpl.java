package com.telcom.ups.monolitico.protocolotipo.service;

import com.telcom.ups.data.dto.ProtocoloTipoDTO;
import com.telcom.ups.data.entities.ProtocoloTipo;
import com.telcom.ups.data.read.ProtocoloTipoRead;
import com.telcom.ups.monolitico.protocolotipo.mapper.ProtocoloTipoMapper;
import com.telcom.ups.monolitico.protocolotipo.repository.ProtocoloTipoCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import com.telcom.ups.monolitico.util.response.error.NotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProtocoloTipoServiceImpl implements ProtocoloTipoService {

    @Autowired
    private ProtocoloTipoCrudRepository protocoloTipoCrudRepository;

    @Autowired
    private ProtocoloTipoMapper protocoloTipoMapper;

    @Override
    public Page<ProtocoloTipoRead> findAll(Pageable pageable) {
        return protocoloTipoCrudRepository.findAll(pageable).map(protocoloTipo -> protocoloTipoMapper.toProtocoloTipoRead(protocoloTipo));
    }

    @Override
    public Optional<ProtocoloTipoRead> getOne(Integer id) {
        Optional<ProtocoloTipo> protocoloTipoDB = protocoloTipoCrudRepository.findById(id);
        if (protocoloTipoDB.isPresent()) {
            return protocoloTipoDB.map(protocoloTipo -> protocoloTipoMapper.toProtocoloTipoRead(protocoloTipo));
        }
        throw new NotFoundExeption("Tipo con id " + id + " no encontrado");
    }

    @Override
    public ProtocoloTipoRead save(ProtocoloTipoDTO protocoloTipoDTO) {
        Optional<ProtocoloTipo> protocoloTipoDB = protocoloTipoCrudRepository.findByNombre(protocoloTipoDTO.getNombre());
        if (!protocoloTipoDB.isPresent()) {
            return protocoloTipoMapper.toProtocoloTipoRead(protocoloTipoCrudRepository.save(protocoloTipoMapper.toProtocoloTipo(protocoloTipoDTO)));
        }
        throw new BadRequestException("Tipo con nombre " + protocoloTipoDTO.getNombre() + " ya esta registrado");
    }

    @Override
    public boolean delete(Integer id) {
        Optional<ProtocoloTipo> protocoloTipoDB = protocoloTipoCrudRepository.findById(id);
        if (protocoloTipoDB.isPresent()) {
            protocoloTipoCrudRepository.deleteById(id);
            return true;
        }
        throw new NotFoundExeption("Tipo con id " + id + " no encontrado para eliminar");
    }

    @Override
    public ProtocoloTipoRead update(ProtocoloTipoDTO protocoloTipoDTO, Integer id) {
        Optional<ProtocoloTipo> protocoloTipoDB = protocoloTipoCrudRepository.findById(id);
        if (protocoloTipoDB.isPresent()) {
            ProtocoloTipo newProtocoloTipo = protocoloTipoMapper.toProtocoloTipo(protocoloTipoDTO);
            ProtocoloTipo protocoloTipo = protocoloTipoDB.get();
            protocoloTipo.setNombre(newProtocoloTipo.getNombre());
            protocoloTipo.setDescripcion(newProtocoloTipo.getDescripcion());
            protocoloTipo.setUpdatedAt(LocalDateTime.now());
            return protocoloTipoMapper.toProtocoloTipoRead(protocoloTipoCrudRepository.save(protocoloTipo));
        }
        throw new NotFoundExeption("Tipo con id " + id + " no encontrado para actualizar");
    }
}
