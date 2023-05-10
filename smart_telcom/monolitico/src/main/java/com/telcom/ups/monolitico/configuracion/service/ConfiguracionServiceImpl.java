package com.telcom.ups.monolitico.configuracion.service;

import com.telcom.ups.data.dto.ConfiguracionDTO;
import com.telcom.ups.data.entities.Configuracion;
import com.telcom.ups.data.entities.DispositivoTipo;
import com.telcom.ups.data.read.ConfiguracionRead;
import com.telcom.ups.monolitico.configuracion.mapper.ConfiguracionMapper;
import com.telcom.ups.monolitico.configuracion.repository.ConfiguracionCrudRepository;
import com.telcom.ups.monolitico.dispositivotipo.repository.DispositivoTipoCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfiguracionServiceImpl implements ConfiguracionService{

    @Autowired
    private ConfiguracionCrudRepository configuracionCrudRepository;

    @Autowired
    private DispositivoTipoCrudRepository dispositivoTipoCrudRepository;

    @Autowired
    private ConfiguracionMapper mapper;

    /**
     * Devuelve un Page de ConfiguracionReads que provee el repositorio CRUD
     * Para devolver un Page de ConfiguracionReads se realiza el proceso de conversión de objetos con la interfaz ConfiguracionMapper
     *
     * @param pageable
     * @return page de ConfiguracionReads
     */
    @Override
    public Page<ConfiguracionRead> findAll(Pageable pageable) {
        return configuracionCrudRepository.findAll(pageable).map(configuracion -> mapper.toConfiguracionRead(configuracion));
    }

    /**
     * Devuelve un Optional de ConfiguracionRead que provee el repositorio CRUD
     * Para devolver una ConfiguracionRead se realiza el proceso de conversión de objetos con la interfaz ConfiguracionMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return optional de ConfiguracionRead
     */
    @Override
    public Optional<ConfiguracionRead> getOne(Integer id) {
        Optional<Configuracion> configuracionDB = configuracionCrudRepository.findById(id);
        if (configuracionDB.isPresent()) {
            return configuracionCrudRepository.findById(id).map(configuracion -> mapper.toConfiguracionRead(configuracion));
        }
        throw new BadRequestException("Configuracion con id " + id + " no encontrada");
    }

    /**
     * Devuelve una ConfiguracionRead que provee el repositorio CRUD
     * Para devolver una ConfiguracionRead se realiza el proceso de conversión de objetos con la interfaz ConfiguracionMapper
     * Para guardar una configuracion previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param dispositivoTipoId
     * @param configuracionDTO
     * @return
     */
    @Override
    public ConfiguracionRead save(Integer dispositivoTipoId, ConfiguracionDTO configuracionDTO) {
        Optional<DispositivoTipo> dispositivoTipoDB = dispositivoTipoCrudRepository.findById(dispositivoTipoId);
        if (dispositivoTipoDB.isPresent()) {
            Optional<Configuracion> configuracionDBNombre = configuracionCrudRepository.findByNombre(configuracionDTO.getNombre());
            if (!configuracionDBNombre.isPresent()) {
                Configuracion configuracion = mapper.toConfiguracion(configuracionDTO);
                configuracion.setDispositivoTipo(dispositivoTipoDB.get());
                return mapper.toConfiguracionRead(configuracionCrudRepository.save(configuracion));
            }
            throw new BadRequestException("Configuracion con nombre " + configuracionDTO.getNombre() + " ya esta registrado");
        }
        throw new BadRequestException("DispositivoTipo con id " + dispositivoTipoId + " no encontrado para relacionar con la configuracion");
    }

    /**
     * Devuelve un boolean al borrar la informacion mediante el repositorio CRUD
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean delete(Integer id) {
        try {
            Optional<Configuracion> configuracionDB = configuracionCrudRepository.findById(id);
            if (configuracionDB.isPresent()) {
                configuracionCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Configuracion con id " + id + " no encontrada para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("La configuración que intenta eliminar cuenta con parámetros o variantes, previo a esto, para que " +
                    "el proceso de eliminación sea exitoso la configuración no debe tener parámetros y variantes generadas con esta configuración.");
        }
    }

    /**
     * Devuelve un ConfiguracionRead que provee el repositorio CRUD
     * Para devolver una ConfiguracionRead se realiza el proceso de conversión de objetos con la interfaz ConfiguracionMapper
     * Para actualizar una configuracion previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param dispositivoTipoId
     * @param configuracionDTO
     * @param id
     * @return ConfiguracionRead
     */
    @Override
    public ConfiguracionRead update(Integer dispositivoTipoId, ConfiguracionDTO configuracionDTO, Integer id) {
        Optional<Configuracion> configuracionDB = configuracionCrudRepository.findById(id);
        if (configuracionDB.isPresent()) {
            Optional<DispositivoTipo> dispositivoTipoDB = dispositivoTipoCrudRepository.findById(dispositivoTipoId);
            if (dispositivoTipoDB.isPresent()) {
                Configuracion newConfiguracion = mapper.toConfiguracion(configuracionDTO);
                Configuracion conf = configuracionDB.get();
                conf.setNombre(newConfiguracion.getNombre());
                conf.setComando(newConfiguracion.getComando());
                conf.setDescripcion(newConfiguracion.getDescripcion());
                conf.setDispositivoTipo(dispositivoTipoDB.get());
                return mapper.toConfiguracionRead(configuracionCrudRepository.save(conf));
            }
            throw new BadRequestException("DispositivoTipo con id " + dispositivoTipoId + " no encontrado para relacionar con la configuracion");
        }
        throw new BadRequestException("Configuracion con id " + id + " no encontrada para actualizar");
    }

    /**
     * Devuelve un Page de ConfiguracionReads que provee el repositorio CRUD
     * Para devolver un Page de ConfiguracionReads se realiza el proceso de conversión de objetos con la interfaz ConfiguracionMapper
     *
     * @param idTipo
     * @param pageable
     * @return page de ConfiguracionRead
     */
    @Override
    public Page<ConfiguracionRead> getAllByDispositivoTipo(Integer idTipo, Pageable pageable) {
        return configuracionCrudRepository.getAllByTipoDispositivo(idTipo, pageable).map(configuracion -> mapper.toConfiguracionRead(configuracion));
    }
}
