package com.telcom.ups.monolitico.dispositivotipo.repository;

import com.telcom.ups.data.entities.DispositivoTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DispositivoTipoCrudRepository extends JpaRepository<DispositivoTipo, Integer> {

    /**
     * Devuelve un Optional de un DispositivoTipo de la base de datos
     * Para la busqueda de información se usa JPA findByCodigo
     *
     * @param codigo
     * @return optional DispositivoTipo
     */
    Optional<DispositivoTipo> findByCodigo(String codigo);

    /**
     * Devuelve un Optional de un DispositivoTipo de la base de datos
     * Para la busqueda de información se usa JPA findByNombre
     *
     * @param nombre
     * @return optional DispositivoTipo
     */
    Optional<DispositivoTipo> findByNombre(String nombre);

}
