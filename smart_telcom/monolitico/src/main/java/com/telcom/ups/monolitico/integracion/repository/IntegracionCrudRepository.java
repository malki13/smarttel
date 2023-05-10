package com.telcom.ups.monolitico.integracion.repository;

import com.telcom.ups.data.entities.Integracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntegracionCrudRepository extends JpaRepository<Integracion, Integer> {

    Optional<Integracion> findByNombre(String nombre);

    Optional<Integracion> findByCodigo(String codigo);
}
