package com.telcom.ups.monolitico.variante.repository;

import com.telcom.ups.data.entities.Variante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VarianteCrudRepository extends JpaRepository<Variante, Integer> {

    /**
     * Devuelve un Optional de una Variante de la base de datos
     * Para la busqueda de información se usa JPA findByNombre
     *
     * @param nombre
     * @return optional de Variante
     */
    Optional<Variante> findByNombre(String nombre);
}
