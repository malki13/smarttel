package com.telcom.ups.monolitico.gateway.repository;

import com.telcom.ups.data.entities.Gateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GatewayCrudRepository extends JpaRepository<Gateway, Integer> {

    /**
     * Devuelve una lista de Gateways de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param nombre
     * @return lista de Gateways
     */
    @Query(nativeQuery = true, value = "SELECT * FROM public.tb_gateways tg WHERE tg.gat_nomb LIKE %:nombre% LIMIT 10")
    List<Gateway> searchByNombre(@Param("nombre") String nombre);

    /**
     * Devuelve una lista de Gateways de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param id
     * @return lista de Gateways
     */
    @Query(nativeQuery = true, value = "SELECT * FROM public.tb_gateways tg WHERE tg.gat_id LIKE %:id% LIMIT 10")
    List<Gateway> searchById(@Param("id") String id);

    /**
     * Devuelve un Page de todos los Gateways de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idEmpresa
     * @param pageable
     * @return page de Gateways
     */
    @Query(
            value = "SELECT * FROM tb_gateways ga WHERE ga.gat_emp_id = :idEmpresa",
            countQuery = "SELECT count(*) FROM tb_gateways ga WHERE ga.gat_emp_id = :idEmpresa",
            nativeQuery = true
    )
    Page<Gateway> getAllByEmpresa(Integer idEmpresa, Pageable pageable);

//    /**
//     * Devuelve un Page de todos los Gateways de la base de datos
//     * Para la busqueda de información se usa un QueryNativo
//     *
//     * @param idIntegracion
//     * @param pageable
//     * @return page de Gateways
//     */
//    @Query(
//            value = "SELECT * FROM tb_gateways ga WHERE ga.gat_itg_id = :idIntegracion",
//            countQuery = "SELECT count(*) FROM tb_gateways ga WHERE ga.gat_itg_id = :idIntegracion",
//            nativeQuery = true
//    )
//    Page<Gateway> getAllByIntegracion(Integer idIntegracion, Pageable pageable);

    /**
     * Devuelve un Optional de un Gateway de la base de datos
     * Para la busqueda de información se usa JPA findByIdGateway
     *
     * @param id
     * @return optional de Gateway
     */
    Optional<Gateway> findByIdGateway(String id);

    /**
     * Devuelve una entero resultante del conteo de Gateways de la base de datos
     *
     * @param idEmpresa
     * @return Integer
     */
    Integer countByEmpresaIden(Integer idEmpresa);
}
