package com.telcom.ups.monolitico.parametro.repository;

import com.telcom.ups.data.entities.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParametroCrudRepository extends JpaRepository<Parametro, Integer> {

    /**
     * Devuelve un Optional de un Parametro de la base de datos
     * Para la busqueda de información se usa JPA findByNombre
     *
     * @param nombre
     * @return optional de Parametro
     */
    Optional<Parametro> findByNombre(String nombre);
}
