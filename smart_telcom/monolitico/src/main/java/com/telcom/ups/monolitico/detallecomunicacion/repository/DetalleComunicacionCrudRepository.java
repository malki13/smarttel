package com.telcom.ups.monolitico.detallecomunicacion.repository;

import com.telcom.ups.data.entities.DetalleComunicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleComunicacionCrudRepository extends JpaRepository<DetalleComunicacion, Integer> {
}
