package com.telcom.ups.monolitico.credential.repository;

import com.telcom.ups.data.entities.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialCrudRepository extends JpaRepository<Credential, Integer> {

    /**
     * Devuelve un Optional de un credential de la base de datos
     * Para la busqueda de información se usa JPA findByCodigo
     *
     * @param codigo
     * @return optional de Credential
     */
    Optional<Credential> findByCodigo(String codigo);
}
