package com.telcom.ups.monolitico.administrador.repository;

import com.telcom.ups.data.entities.Administrador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorCrudRepository extends JpaRepository<Administrador, Integer> {

    /**
     * Devuelve un Page de todos los administradores de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idEmpresa
     * @param pageable
     * @return page de Administrador
     */
    @Query(
            value = "SELECT * FROM tb_administradores ad WHERE ad.adm_emp_id = :idEmpresa",
            countQuery = "SELECT count(*) FROM tb_administradores ad WHERE ad.adm_emp_id = :idEmpresa",
            nativeQuery = true
    )
    Page<Administrador> getAllByEmpresa(Integer idEmpresa, Pageable pageable);

    /**
     * Devuelve un Optional de un admisnitrador de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idInterventor
     * @param idEmpresa
     * @return optional de Administrador
     */
    @Query(
            value = "select * from tb_administradores ta where ta.adm_int_id = :idInterventor and ta.adm_emp_id = :idEmpresa",
            nativeQuery = true
    )
    Optional<Administrador> findByInterventorEmpresa(int idInterventor, int idEmpresa);

    /**
     * Devuelve un Optional de Administrador de la base de datos
     *
     * @param idInterventor
     * @return optional de Administrador
     */
    Optional<Administrador> findByInterventorIden(Integer idInterventor);

}
