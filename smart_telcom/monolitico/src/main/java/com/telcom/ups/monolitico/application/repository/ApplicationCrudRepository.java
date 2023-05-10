package com.telcom.ups.monolitico.application.repository;

import com.telcom.ups.data.entities.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationCrudRepository extends JpaRepository<Application, Integer> {

    @Query(
            value = "SELECT * FROM tb_applications ap WHERE ap.app_emp_id = :idEmpresa",
            countQuery = "SELECT count(*) FROM tb_applications ap WHERE ga.app_emp_id = :idEmpresa",
            nativeQuery = true
    )
    Page<Application> getAllByEmpresa(Integer idEmpresa, Pageable pageable);

//    @Query(
//            value = "SELECT * FROM tb_applications ap WHERE ap.app_pes_id = :idPerfilServicio",
//            countQuery = "SELECT count(*) FROM tb_applications ap WHERE ap.app_pes_id = :idPerfilServicio",
//            nativeQuery = true
//    )
//    Page<Application> getAllByPerfilServicio(Integer idPerfilServicio, Pageable pageable);


    Optional<Application> findByCodigo(String codigo);

    Optional<Application> findByNombre(String nombre);

    /**
     * Devuelve una lista de aplicaciones de la base de datos
     *
     * @param idEmpresa
     * @param idProtocoloTipo
     * @return lista de Applications
     */
    List<Application> findByEmpresaIdenAndProtocoloTipoIden(Integer idEmpresa, Integer idProtocoloTipo);
}
