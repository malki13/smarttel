package com.telcom.ups.monolitico.estatus.repository;

import com.telcom.ups.data.entities.Estatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstatusCrudRepository extends PagingAndSortingRepository<Estatus, Integer> {

    /**
     * Devuelve un Optional de un Estatus de la base de datos
     * Para la busqueda de información se usa JPA findByCodigo
     *
     * @param codigo
     * @return optional de Estatus
     */
    Optional<Estatus> findByCodigo(String codigo);

    /**
     * Devuelve un Optional de un Estatus de la base de datos
     * Para la busqueda de información se usa JPA findByNombre
     *
     * @param nombre
     * @return optional de Estatus
     */
    Optional<Estatus> findByNombre(String nombre);
}
