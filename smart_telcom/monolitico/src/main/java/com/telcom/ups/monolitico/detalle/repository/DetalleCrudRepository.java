package com.telcom.ups.monolitico.detalle.repository;

import com.telcom.ups.data.entities.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCrudRepository extends JpaRepository<Detalle, Integer> {
}
