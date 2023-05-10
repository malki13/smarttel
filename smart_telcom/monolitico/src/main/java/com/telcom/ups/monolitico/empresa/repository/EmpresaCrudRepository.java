package com.telcom.ups.monolitico.empresa.repository;

import com.telcom.ups.data.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaCrudRepository extends JpaRepository<Empresa, Integer> {

    /**
     * Devuelve un Optional de una Empresa de la base de datos
     * Para la busqueda de información se usa JPA findByInterventorIden
     *
     * @param interventorId
     * @return optional de Empresa
     */
    Optional<Empresa> findByInterventorIden(Integer interventorId);

    /**
     * Devuelve una Empresa de la base de datos
     *
     * @param idEmpresa
     * @return Empresa
     */
    Empresa findByIden(Integer idEmpresa);
}
