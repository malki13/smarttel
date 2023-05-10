package com.telcom.ups.monolitico.parametro.service;

import com.telcom.ups.data.dto.ParametroDTO;
import com.telcom.ups.data.entities.Configuracion;
import com.telcom.ups.data.entities.Parametro;
import com.telcom.ups.data.read.ParametroRead;
import com.telcom.ups.monolitico.configuracion.repository.ConfiguracionCrudRepository;
import com.telcom.ups.monolitico.parametro.mapper.ParametroMapper;
import com.telcom.ups.monolitico.parametro.repository.ParametroCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParametroServiceImpl implements ParametroService{

    @Autowired
    private ParametroCrudRepository parametroCrudRepository;

    @Autowired
    private ConfiguracionCrudRepository configuracionCrudRepository;

    @Autowired
    private ParametroMapper mapper;

    /**
     * Devuelve un Page de ParametroReads que provee el repositorio CRUD
     * Para devolver un Page de ParametroReads se realiza el proceso de conversión de objetos con la interfaz ParametroMapper
     *
     * @param pageable
     * @return page de ParametroRead
     */
    @Override
    public Page<ParametroRead> findAll(Pageable pageable) {
        return parametroCrudRepository.findAll(pageable).map(parametro -> mapper.toParametroRead(parametro));
    }

    /**
     * Devuelve un Optional de ParametroRead que provee el repositorio CRUD
     * Para devolver un Optional de ParametroRead se realiza el proceso de conversión de objetos con la interfaz ParametroMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesta de la petición
     *
     * @param id
     * @return optional de ParametroRead
     */
    @Override
    public Optional<ParametroRead> getOne(Integer id) {
        Optional<Parametro> parametroDB = parametroCrudRepository.findById(id);
        if (parametroDB.isPresent()) {
            return parametroDB.map(parametro -> mapper.toParametroRead(parametro));
        }
        throw new BadRequestException("Parametro con id " + id + " no encontrado");
    }

    /**
     * Devuelve un ParametroRead que provee el repositorio CRUD
     * Para devolver un ParametroRead se realiza el proceso de conversión de objetos con la interfaz ParametroMapper
     * Para guardar el parametro se realizan las validaciones para el almacén de datos correctos respondiendo con una
     * excepcion de BadRequestException como respuesra de la petición
     *
     * @param configuracionId
     * @param parametroDTO
     * @return ParametroRead
     */
    @Override
    public ParametroRead save(Integer configuracionId, ParametroDTO parametroDTO) {
        Optional<Configuracion> configuracionDB = configuracionCrudRepository.findById(configuracionId);
        if (configuracionDB.isPresent()) {
            Optional<Parametro> parametroDB = parametroCrudRepository.findByNombre(parametroDTO.getNombre());
            if (!parametroDB.isPresent()) {
                Parametro parametro = mapper.toParametro(parametroDTO);
                parametro.setConfiguracion(configuracionDB.get());
                return mapper.toParametroRead(parametroCrudRepository.save(parametro));
            }
            throw new BadRequestException("Parametro con nombre " + parametroDTO.getNombre() + " ya esta registrado");
        }
        throw new BadRequestException("Configuracion con id " + configuracionId + " no encontrado para relacionar con parametro");
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
            Optional<Parametro> parametroDB = parametroCrudRepository.findById(id);
            if (parametroDB.isPresent()) {
                parametroCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Parametro con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El parametro que intenta eliminar cuenta con objetos relacionados. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso el parametro no debe contar con objetos relacionados al mismo.");
        }
    }

    /**
     * Devuelve un ParametroRead que provee el repositorio CRUD
     * Para devolver un ParametroRead se realiza el proceso de conversión de objetos con la interfaz ParametroMapper
     * Para actualizar el parametro se realizan las validaciones para el almacén de datos correctos respondiendo con una
     * excepcion de BadRequestException como respuesra de la petición
     *
     * @param configuracionId
     * @param parametroDTO
     * @param id
     * @return ParametroRead
     */
    @Override
    public ParametroRead update(Integer configuracionId, ParametroDTO parametroDTO, Integer id) {
        Optional<Parametro> parametroDB = parametroCrudRepository.findById(id);
        if (parametroDB.isPresent()) {
            Optional<Configuracion> configuracionDB = configuracionCrudRepository.findById(configuracionId);
            if (configuracionDB.isPresent()) {
                Parametro newParametro = mapper.toParametro(parametroDTO);
                Parametro par = parametroDB.get();
                par.setNombre(newParametro.getNombre());
                par.setDescripcion(newParametro.getDescripcion());
                par.setConfiguracion(configuracionDB.get());
                return mapper.toParametroRead(parametroCrudRepository.save(par));
            }
            throw new BadRequestException("Configuracion con id " + configuracionId + " no encontrado para relacionar con parametro");
        }
        throw new BadRequestException("Parametro con id " + id + " no encontrado para actualizar");
    }
}
