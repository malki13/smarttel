package com.telcom.ups.monolitico.rol.repository;

import com.telcom.ups.data.entities.Rol;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolCrudRepository extends PagingAndSortingRepository<Rol, Integer> {

    /**
     * Devuelve un Optional de un Rol de la base de datos
     * Para la busqueda de información se usa JPA findByNombre
     *
     * @param nombre
     * @return optional de Rol
     */
    Optional<Rol> findByNombre(String nombre);

}
