package com.telcom.ups.monolitico.credencialtipo.repository;

import com.telcom.ups.data.entities.CredencialTipo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredencialTipoCrudRepository extends PagingAndSortingRepository<CredencialTipo, Integer> {

    Optional<CredencialTipo> findByNombre(String nombre);
}
