package com.telcom.ups.monolitico.detallepago.repository;

import com.telcom.ups.data.entities.DetallePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePagoCrudRepository extends JpaRepository<DetallePago, Integer> {
}
