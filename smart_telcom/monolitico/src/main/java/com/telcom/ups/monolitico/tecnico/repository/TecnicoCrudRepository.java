package com.telcom.ups.monolitico.tecnico.repository;

import com.telcom.ups.data.entities.Tecnico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TecnicoCrudRepository extends JpaRepository<Tecnico, Integer> {

    /**
     * Devuelve un Page de todos los Tecnicos de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idEmpresa
     * @param pageable
     * @return page de Tecnicos
     */
    @Query(
            value = "SELECT * FROM tb_tecnicos te WHERE te.tec_emp_id = :idEmpresa",
            countQuery = "SELECT count(*) FROM tb_tecnicos te WHERE te.tec_emp_id = :idEmpresa",
            nativeQuery = true
    )
    Page<Tecnico> getAllByEmpresa(Integer idEmpresa, Pageable pageable);

    /**
     * Devuelve un Optional de un Tecnico de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idInterventor
     * @param idEmpresa
     * @return optional de Tecnico
     */
    @Query(
            value = "select * from tb_tecnicos tt where tt.tec_int_id = :idInterventor and tt.tec_emp_id = :idEmpresa",
            nativeQuery = true
    )
    Optional<Tecnico> findByInterventorEmpresa(int idInterventor, int idEmpresa);

    /**
     * Devuelve un Optional de Tecnico de la base de datos
     * @param idInterventor
     * @return optional de Tecnico
     */
    Optional<Tecnico> findByInterventorIden(Integer idInterventor);
}
