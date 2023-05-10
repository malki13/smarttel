package com.telcom.ups.monolitico.rol.service;

import com.telcom.ups.data.dto.RolDTO;
import com.telcom.ups.data.entities.Estatus;
import com.telcom.ups.data.entities.Rol;
import com.telcom.ups.data.read.RolRead;
import com.telcom.ups.monolitico.estatus.repository.EstatusCrudRepository;
import com.telcom.ups.monolitico.rol.mapper.RolMapper;
import com.telcom.ups.monolitico.rol.repository.RolCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolCrudRepository rolCrudRepository;

    @Autowired
    private EstatusCrudRepository estatusCrudRepository;

    @Autowired
    private RolMapper rolMapper;


    /**
     * Devuelve un Page de RolReads que provee el repositorio
     * Para devolver un Page de RolReads se realiza el proceso de conversión de objetos con la interfaz RolMapper
     *
     * @param pageable
     * @return page de RolRead
     */
    @Override
    public Page<RolRead> findAll(Pageable pageable) {
        return rolCrudRepository.findAll(pageable).map(rol -> rolMapper.toRolRead(rol));
    }

    /**
     * Devuelve un Optional de RolRead que provee el repositorio
     * Para devolver un Optional de RolRead se realiza el proceso de conversión de objetos con la interfaz RolMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return optional de RolRead
     */
    @Override
    public Optional<RolRead> getOne(Integer id) {
        Optional<Rol> rolDB = rolCrudRepository.findById(id);
        if (rolDB.isPresent()) {
            return rolDB.map(rol -> rolMapper.toRolRead(rol));
        }
        throw new BadRequestException("Rol con id " + id + " no encontrado");
    }

    /**
     * Devuelve un RolRead que provee el repositorio
     * Para devolver un RolRead se realiza el proceso de conversión de objetos con la interfaz RolMapper
     * Para guardar un rol previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param estatusId
     * @param rolDTO
     * @return RolRead
     */
    @Override
    public RolRead save(Integer estatusId, RolDTO rolDTO) {
        Optional<Estatus> estatusDB = estatusCrudRepository.findById(estatusId);
        if (estatusDB.isPresent()) {
            Optional<Rol> rolDB = rolCrudRepository.findByNombre(rolDTO.getNombre());
            if (!rolDB.isPresent()) {
                Rol rol = rolMapper.toRol(rolDTO);
                rol.setEstatus(estatusDB.get());
                return rolMapper.toRolRead(rolCrudRepository.save(rol));
            }
            throw new BadRequestException("Rol con nombre  " + rolDTO.getNombre() + " ya esta registrado");
        }
        throw new BadRequestException("Estatus con id " + estatusId + " no encontrado para relacionar a el rol");
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
            Optional<Rol> rolDB = rolCrudRepository.findById(id);
            if (rolDB.isPresent()) {
                rolCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Rol con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El rol que intenta eliminar cuenta con objetos relacionados. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso el rol no debe contar con objetos relacionados al mismo.");
        }
    }

    /**
     * Devuelve un RolRead que provee el repositorio
     * Para devolver un RolRead se realiza el proceso de conversión de objetos con la interfaz RolMapper
     * Para actualizar un rol previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param estatusId
     * @param id
     * @param rolDTO
     * @return RolRead
     */
    @Override
    public RolRead update(Integer estatusId, Integer id, RolDTO rolDTO) {
        Optional<Rol> rolDB = rolCrudRepository.findById(id);
        if (rolDB.isPresent()) {
            Optional<Estatus> estatusDB = estatusCrudRepository.findById(estatusId);
            if (estatusDB.isPresent()) {
                Rol rol = rolDB.get();
                Rol newRol = rolMapper.toRol(rolDTO);
                rol.setNombre(newRol.getNombre());
                rol.setDescripcion(newRol.getDescripcion());
                rol.setEstatus(estatusDB.get());
                rol.setUpdatedAt(LocalDateTime.now());
                return rolMapper.toRolRead(rolCrudRepository.save(rol));
            }
            throw new BadRequestException("Estatus con id " + estatusId + " no encontrado para actualizar el rol");
        }
        throw new BadRequestException("Rol con id " + id + " no encontrado para actualizar");
    }
}
