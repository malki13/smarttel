package com.telcom.ups.monolitico.unidad.repository;

import com.telcom.ups.data.entities.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnidadCrudRepository extends JpaRepository<Unidad, Integer> {

    /**
     * Devuelve un Optional de un Unidad de la base de datos
     * Para la busqueda de información se usa JPA findByNombre
     *
     * @param nombre
     * @return optional de Unidad
     */
    Optional<Unidad> findByNombre(String nombre);
}
