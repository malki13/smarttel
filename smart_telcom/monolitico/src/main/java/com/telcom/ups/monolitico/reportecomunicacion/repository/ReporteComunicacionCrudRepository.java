package com.telcom.ups.monolitico.reportecomunicacion.repository;

import com.telcom.ups.data.entities.ReporteComunicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteComunicacionCrudRepository extends JpaRepository<ReporteComunicacion, Integer> {
}
