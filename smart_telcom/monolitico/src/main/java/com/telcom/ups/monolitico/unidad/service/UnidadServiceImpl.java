package com.telcom.ups.monolitico.unidad.service;

import com.telcom.ups.data.dto.UnidadDTO;
import com.telcom.ups.data.entities.Unidad;
import com.telcom.ups.data.read.UnidadRead;
import com.telcom.ups.monolitico.unidad.mapper.UnidadMapper;
import com.telcom.ups.monolitico.unidad.repository.UnidadCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnidadServiceImpl implements UnidadService{

    @Autowired
    private UnidadCrudRepository unidadCrudRepository;

    @Autowired
    private UnidadMapper mapper;


    /**
     * Devuelve un Page de UnidadReads que provee el repositorio CRUD
     * Para devolver un Page de UnidadReads se realiza el proceso de conversión de objetos con la interfaz UnidadMapper
     *
     * @param pageable
     * @return page de UnidadRead
     */
    @Override
    public Page<UnidadRead> findAll(Pageable pageable) {
        return unidadCrudRepository.findAll(pageable).map(unidad -> mapper.toUnidadRead(unidad));
    }

    /**
     * Devuelve un Optional de UnidadRead que provee el repositorio CRUD
     * Para devolver un optional de UnidadRead se realiza el proceso de conversión de objetos con la interfaz UnidadMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return optional de UnidadRead
     */
    @Override
    public Optional<UnidadRead> getOne(Integer id) {
        Optional<Unidad> unidadDB = unidadCrudRepository.findById(id);
        if (unidadDB.isPresent()) {
            return unidadDB.map(unidad -> mapper.toUnidadRead(unidad));
        }
        throw new BadRequestException("Unidad con id " + id + " no encontrado");
    }

    /**
     * Devuelve un UnidadRead que provee el repositorio CRUD
     * Para devolver un optional de UnidadRead se realiza el proceso de conversión de objetos con la interfaz UnidadMapper
     * Para guardar una unidad previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param unidadDTO
     * @return UnidadRead
     */
    @Override
    public UnidadRead save(UnidadDTO unidadDTO) {
        Optional<Unidad> unidadDBNombre = unidadCrudRepository.findByNombre(unidadDTO.getNombre());
        if (!unidadDBNombre.isPresent()) {
            Unidad unidad = mapper.toUnidad(unidadDTO);
            return mapper.toUnidadRead(unidadCrudRepository.save(unidad));
        }
        throw new BadRequestException("Unidad con nombre " + unidadDTO.getNombre() + " ya registrada");
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
            Optional<Unidad> unidadDB = unidadCrudRepository.findById(id);
            if (unidadDB.isPresent()) {
                unidadCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Unidad con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("La unidad que intenta eliminar cuenta con objetos relacionados. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso la unidad no debe contar con objetos relacionados al mismo.");
        }
    }

    /**
     * Devuelve un UnidadRead que provee el repositorio CRUD
     * Para devolver un optional de UnidadRead se realiza el proceso de conversión de objetos con la interfaz UnidadMapper
     * Para actualizar una unidad previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param unidadDTO
     * @param id
     * @return UnidadRead
     */
    @Override
    public UnidadRead update(UnidadDTO unidadDTO, Integer id) {
        Optional<Unidad> unidadDB = unidadCrudRepository.findById(id);
        if (unidadDB.isPresent()) {
            Unidad newUnidad = mapper.toUnidad(unidadDTO);
            Unidad uni = unidadDB.get();
            uni.setNombre(newUnidad.getNombre());
            return mapper.toUnidadRead(unidadCrudRepository.save(uni));
        }
        throw new BadRequestException("Unidad con id " + id + " no encontrado para actualizar");
    }
}
