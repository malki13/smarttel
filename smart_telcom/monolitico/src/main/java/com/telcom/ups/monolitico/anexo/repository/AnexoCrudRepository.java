package com.telcom.ups.monolitico.anexo.repository;

import com.telcom.ups.data.entities.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnexoCrudRepository extends JpaRepository<Anexo, Integer> {
}
