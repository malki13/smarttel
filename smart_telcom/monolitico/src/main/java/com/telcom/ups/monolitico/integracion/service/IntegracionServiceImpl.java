package com.telcom.ups.monolitico.integracion.service;

import com.telcom.ups.monolitico.credencialtipo.repository.CredencialTipoCrudRepository;
import com.telcom.ups.data.dto.*;
import com.telcom.ups.data.entities.*;
import com.telcom.ups.data.read.IntegracionRead;
import com.telcom.ups.monolitico.integracion.mapper.IntegracionMapper;
import com.telcom.ups.monolitico.integracion.repository.IntegracionCrudRepository;
import com.telcom.ups.monolitico.protocolotipo.repository.ProtocoloTipoCrudRepository;
import com.telcom.ups.monolitico.resquestresponse.repository.RequestResponseCrudRepository;
import com.telcom.ups.monolitico.topico.repository.TopicoCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import com.telcom.ups.monolitico.util.response.error.NotFoundExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class IntegracionServiceImpl implements IntegracionService {

    private static final Logger log = LoggerFactory.getLogger(IntegracionServiceImpl.class);

    @Autowired
    private IntegracionCrudRepository integracionCrudRepository;

    @Autowired
    private TopicoCrudRepository topicoCrudRepository;

    @Autowired
    private RequestResponseCrudRepository requestResponseCrudRepository;

    @Autowired
    private ProtocoloTipoCrudRepository protocoloTipoCrudRepository;

    @Autowired
    private CredencialTipoCrudRepository credencialTipoCrudRepository;

    @Autowired
    private IntegracionMapper integracionMapper;

    @Override
    public Page<IntegracionRead> findAll(Pageable pageable) {
        return integracionCrudRepository.findAll(pageable).map(integracion -> integracionMapper.toIntegracionRead(integracion));
    }

    @Override
    public Optional<IntegracionRead> getOne(Integer id) {
        Optional<Integracion> integracionDB = integracionCrudRepository.findById(id);
        if (integracionDB.isPresent()) {
            return integracionDB.map(integracion -> integracionMapper.toIntegracionRead(integracion));
        }
        throw new NotFoundExeption("Integracion con id " + id + " no encontrada");
    }

    @Override
    public Optional<IntegracionRead> finByNombre(String nombre) {
        Optional<Integracion> integracionDB = integracionCrudRepository.findByNombre(nombre);
        if (integracionDB.isPresent()) {
            return integracionDB.map(integracion -> integracionMapper.toIntegracionRead(integracion));
        }
        throw new NotFoundExeption("Integracion con id " + nombre + " no encontrada");
    }

    @Override
    public Optional<IntegracionRead> finByCodigo(String codigo) {
        Optional<Integracion> integracionDB = integracionCrudRepository.findByCodigo(codigo);
        if (integracionDB.isPresent()) {
            return integracionDB.map(integracion -> integracionMapper.toIntegracionRead(integracion));
        }
        throw new NotFoundExeption("Integracion con id " + codigo + " no encontrada");
    }

    @Override
    public boolean save(IntegracionDTO integracionDTO) {
        Optional<ProtocoloTipo> protocoloTipoDB = protocoloTipoCrudRepository.findById(integracionDTO.getProtocoloTipoId());
        if (protocoloTipoDB.isPresent()) {
            Optional<CredencialTipo> credencialTipoDB = credencialTipoCrudRepository.findById(integracionDTO.getCredencialTipoId());
            if (credencialTipoDB.isPresent()) {
                Integracion integracion = integracionMapper.toIntegracion(integracionDTO);
                Optional<Integracion> integracionDBNombre = integracionCrudRepository.findByNombre(integracionDTO.getNombre());
                if (!integracionDBNombre.isPresent()) {
                    if (!integracionDTO.getCodigo().isEmpty()) {
                        Optional<Integracion> integracionDbCodigo = integracionCrudRepository.findByCodigo(integracionDTO.getCodigo());
                        if (integracionDbCodigo.isPresent()) {
                            throw new BadRequestException("El codigo de integracion " + integracionDTO.getCodigo() + " ya esta registrado");
                        } else {
                            integracion.setCodigo(integracionDTO.getCodigo());
                        }
                    }
                    integracion.setNombre(integracion.getNombre().trim());
                    integracion.setProtocoloTipo(protocoloTipoDB.get());
                    integracion.setCredencialTipo(credencialTipoDB.get());
                    Integracion integracionSave = integracionCrudRepository.save(integracion);
                    if (integracionSave.getIden() > 0) {
                        saveTopicos(integracionDTO, integracionSave);
                        saveUrls(integracionDTO, integracionSave);
                    }
                    return true;
                }
                throw new BadRequestException("Integracion con nombre ''" + integracionDTO.getNombre() + "'' ya se encuentra registrado");
            }
            throw new NotFoundExeption("Tipo de credencial con id " + integracionDTO.getCredencialTipoId() + " no encontrada para relacionar con la integracion");
        }
        throw new NotFoundExeption("Tipo de protocolo con id " + integracionDTO.getProtocoloTipoId() + " no encontrado para relacionar con la integracion");
    }

    @Override
    public boolean delete(Integer id) {
        Optional<Integracion> integracionDB = integracionCrudRepository.findById(id);
        if (integracionDB.isPresent()) {
            integracionCrudRepository.deleteById(id);
            return true;
        }
        throw new NotFoundExeption("Integracion con id " + id + " no encontrada");
    }

    @Override
    @Transactional
    public boolean update(IntegracionDTO integracionDTO, Integer id) {
        Optional<Integracion> integracionDB = integracionCrudRepository.findById(id);
        if (integracionDB.isPresent()) {
            Integracion integracion = integracionDB.get();
            Optional<ProtocoloTipo> protocoloTipoDB = protocoloTipoCrudRepository.findById(integracionDTO.getProtocoloTipoId());
            if (protocoloTipoDB.isPresent()) {
                Optional<CredencialTipo> credencialTipoDB = credencialTipoCrudRepository.findById(integracionDTO.getCredencialTipoId());
                if (credencialTipoDB.isPresent()) {

                    if (!integracionDTO.getCodigo().isEmpty()) {
                        integracion.setCodigo(integracionDTO.getCodigo());
                    }

                    integracion.setNombre(integracionDTO.getNombre());
                    integracion.setActivo(integracionDTO.isActivo());
                    integracion.setServidor(integracionDTO.getServidor());
                    integracion.setPuerto(integracionDTO.getPuerto());
                    integracion.setTimeout(integracionDTO.getTimeout());
                    integracion.setDescripcion(integracionDTO.getDescripcion());
                    integracion.setUsername(integracionDTO.getUsername());
                    integracion.setPassword(integracionDTO.getPassword());
                    integracion.setBaseurl(integracionDTO.getBaseurl());
                    integracion.setAppServerUrl(integracionDTO.getAppServerUrl());
                    integracion.setAppServerToken(integracionDTO.getAppServerToken());
                    integracion.setQos(integracionDTO.getQos());
                    integracion.setProtocoloTipo(protocoloTipoDB.get());
                    integracion.setCredencialTipo(credencialTipoDB.get());
                    integracion.setUpdatedAt(LocalDateTime.now());

                    if (integracionDTO.getTopicos().size() > 0) {
                        topicoCrudRepository.deleteAllByIntegracionIden(integracion.getIden());
                        saveTopicos(integracionDTO, integracion);
                    }

                    if (integracionDTO.getUrls().size() > 0) {
                        requestResponseCrudRepository.deleteAllByIntegracionIden(integracion.getIden());
                        saveUrls(integracionDTO, integracion);
                    }

                    integracionCrudRepository.save(integracion);

                    return true;
                }
                throw new NotFoundExeption("Tipo de credencial con id " + integracionDTO.getCredencialTipoId() + " no encontrada para relacionar con la integracion");
            }
            throw new NotFoundExeption("Tipo de protocolo con id " + integracionDTO.getProtocoloTipoId() + " no encontrado para relacionar con la integracion");
        }
        throw new NotFoundExeption("Integracion con id " + id + " no encontrada para actualizar");
    }

    @Override
    public boolean addTopico(Integer idIntegracion, AddTopicoDTO addTopicoDTO) {
        Optional<Integracion> integracionDB = integracionCrudRepository.findById(idIntegracion);
        if (integracionDB.isPresent()) {
            Topico topico = new Topico();
            topico.setTopico(addTopicoDTO.getTopico());
            topico.setIntegracion(integracionDB.get());
            topicoCrudRepository.save(topico);
            return true;
        }
        throw new NotFoundExeption("Integracion con id " + idIntegracion + " no encontrada");
    }

    @Override
    public boolean removeTopico(Integer idTopico) {
        Optional<Topico> topicoDB = topicoCrudRepository.findById(idTopico);
        if (topicoDB.isPresent()) {
            topicoCrudRepository.deleteById(idTopico);
            return true;
        }
        throw new NotFoundExeption("Topico con id " + idTopico + " no encontrado");
    }

    @Override
    public boolean addUrl(Integer idIntegracion, AddUrlDTO addUrlDTO) {
        Optional<Integracion> integracionDB = integracionCrudRepository.findById(idIntegracion);
        if (integracionDB.isPresent()) {
            RequestResponse requestResponse = new RequestResponse();
            requestResponse.setUrl(addUrlDTO.getUrl());
            requestResponse.setIntegracion(integracionDB.get());
            requestResponseCrudRepository.save(requestResponse);
            return true;
        }
        throw new NotFoundExeption("Integracion con id " + idIntegracion + " no encontrada");
    }

    @Override
    public boolean removeUrl(Integer idUrl) {
        Optional<RequestResponse> requestResponseDB = requestResponseCrudRepository.findById(idUrl);
        if (requestResponseDB.isPresent()) {
            requestResponseCrudRepository.deleteById(idUrl);
            return true;
        }
        throw new NotFoundExeption("Url con id " + idUrl + " no encontrada");
    }

    private void saveTopicos(IntegracionDTO integracionDTO, Integracion integracionSave) {
        for (int i = 0; i < integracionDTO.getTopicos().size(); i++) {
            TopicoDTO topicoDTO = integracionDTO.getTopicos().get(i);
            Topico topico = new Topico();
            topico.setTopico(topicoDTO.getTopico());
            topico.setIntegracion(integracionSave);
            topicoCrudRepository.save(topico);
        }
    }

    private void saveUrls(IntegracionDTO integracionDTO, Integracion integracionSave) {
        for (int i = 0; i < integracionDTO.getUrls().size(); i++) {
            RequestResponseDTO requestResponseDTO = integracionDTO.getUrls().get(i);
            RequestResponse requestResponse = new RequestResponse();
            requestResponse.setUrl(requestResponseDTO.getUrl());
            requestResponse.setIntegracion(integracionSave);
            requestResponseCrudRepository.save(requestResponse);
        }
    }

}
