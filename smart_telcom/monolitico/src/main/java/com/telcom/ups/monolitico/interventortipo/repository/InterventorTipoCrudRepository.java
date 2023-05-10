package com.telcom.ups.monolitico.interventortipo.repository;

import com.telcom.ups.data.entities.InterventorTipo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterventorTipoCrudRepository extends PagingAndSortingRepository<InterventorTipo, Integer> {

    /**
     * Devuelve un Optional de un InterventorTipo de la base de datos
     * Para la busqueda de información se usa JPA findByNombre
     *
     * @param nombre
     * @return optional de InterventorTipo
     */
    Optional<InterventorTipo> findByNombre(String nombre);
}
