package com.telcom.ups.monolitico.mediciontipo.repository;

import com.telcom.ups.data.entities.MedicionTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicionTipoCrudRepository extends JpaRepository<MedicionTipo, Integer> {

    Optional<MedicionTipo> findByNombre(String nombre);

    Optional<MedicionTipo> findByCodigo(String codigo);
}
