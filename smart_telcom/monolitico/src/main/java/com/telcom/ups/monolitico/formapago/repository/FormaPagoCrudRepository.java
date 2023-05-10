package com.telcom.ups.monolitico.formapago.repository;

import com.telcom.ups.data.entities.FormaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormaPagoCrudRepository extends JpaRepository<FormaPago, Integer> {

    /**
     * Devuelve un Optional de una FormaPago de la base de datos
     * Para la busqueda de información se usa JPA findByNombre
     *
     * @param nombre
     * @return optional de FormaPago
     */
    Optional<FormaPago> findByNombre(String nombre);
}
