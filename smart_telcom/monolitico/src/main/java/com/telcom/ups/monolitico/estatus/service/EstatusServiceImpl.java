package com.telcom.ups.monolitico.estatus.service;

import com.telcom.ups.data.dto.EstatusDTO;
import com.telcom.ups.data.entities.Estatus;
import com.telcom.ups.data.read.EstatusRead;
import com.telcom.ups.monolitico.estatus.mapper.EstatusMapper;
import com.telcom.ups.monolitico.estatus.repository.EstatusCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EstatusServiceImpl implements EstatusService {

    @Autowired
    private EstatusCrudRepository estatusCrudRepository;

    @Autowired
    private EstatusMapper estatusMapper;


    /**
     * Devuelve un Page de EstatusReads que provee el repositorio
     * Para devolver un Page de EstatusReads se realiza el proceso de conversión de objetos con la interfaz EstatusMapper
     *
     * @param pageable
     * @return page de EstatusRead
     */
    @Override
    public Page<EstatusRead> findAll(Pageable pageable) {
        return estatusCrudRepository.findAll(pageable).map(estatus -> estatusMapper.toEstatusRead(estatus));
    }

    /**
     * Devuelve un Optional de EstatusRead que provee el repositorio
     * Para devolver un Optional de EstatusRead se realiza el proceso de conversión de objetos con la interfaz EstatusMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return optional de EstatusRead
     */
    @Override
    public Optional<EstatusRead> getOne(Integer id) {
        Optional<Estatus> estatusDB = estatusCrudRepository.findById(id);
        if (estatusDB.isPresent()) {
            return estatusDB.map(estatus -> estatusMapper.toEstatusRead(estatus));
        }
        throw new BadRequestException("Estatus con id " + id + " no encontrado");
    }

    /**
     * Devuelve un EstatusRead que provee el repositorio
     * Para devolver un EstatusRead se realiza el proceso de conversión de objetos con la interfaz EstatusMapper
     * Para guardar un estatus previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param estatusDTO
     * @return EstatusRead
     */
    @Override
    public EstatusRead save(EstatusDTO estatusDTO) {
        Optional<Estatus> estatusDBCodigo = estatusCrudRepository.findByCodigo(estatusDTO.getCodigo());
        if (!estatusDBCodigo.isPresent()) {
            Optional<Estatus> estatusDBNombre = estatusCrudRepository.findByNombre(estatusDTO.getNombre());
            if (!estatusDBNombre.isPresent()) {
                Estatus estatus = estatusMapper.toEstatus(estatusDTO);
                return estatusMapper.toEstatusRead(estatusCrudRepository.save(estatus));
            }
            throw new BadRequestException("Estatus con nombre " + estatusDTO.getNombre() + " ya esta registrado");
        }
        throw new BadRequestException("Estatus con codigo " + estatusDTO.getCodigo() + " ya esta registrado");
    }

    /**
     * Devuelve un boolean al borrar la informacion mediante el repositorio
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean delete(Integer id) {
        try {
            Optional<Estatus> estatusDB = estatusCrudRepository.findById(id);
            if (estatusDB.isPresent()) {
                estatusCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Estatus con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El estatus que intenta eliminar cuenta con objetos relacionados al mismo. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso el estatus no debe contar con objetos relacionados.");
        }
    }

    /**
     * Devuelve un EstatusRead que provee el repositorio
     * Para devolver un EstatusRead se realiza el proceso de conversión de objetos con la interfaz EstatusMapper
     * Para actualizar un estatus previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param estatusDTO
     * @param id
     * @return EstatusRead
     */
    @Override
    public EstatusRead update(EstatusDTO estatusDTO, Integer id) {
        Optional<Estatus> estatusDB = estatusCrudRepository.findById(id);
        if (estatusDB.isPresent()) {
            Estatus newEstatus = estatusMapper.toEstatus(estatusDTO);
            Estatus est = estatusDB.get();
            est.setCodigo(newEstatus.getCodigo());
            est.setNombre(newEstatus.getNombre());
            est.setUpdatedAt(LocalDateTime.now());
            return estatusMapper.toEstatusRead(estatusCrudRepository.save(est));
        }
        throw new BadRequestException("Estatus con id " + id + " no encontrado para actualizar");
    }
}
