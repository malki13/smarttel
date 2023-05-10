package com.telcom.ups.monolitico.dispositivo.repository;

import com.telcom.ups.data.entities.Dispositivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceCrudRepository extends JpaRepository<Dispositivo, Integer> {

    /**
     * Devuelve un Page de todos los Dispositivos de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idApplication
     * @param pageable
     * @return page de Dispositivo
     */
    @Query(
            value = "SELECT * FROM tb_dispositivos di WHERE di.dis_app_id = :idApplication",
            countQuery = "SELECT count(*) FROM tb_dispositivos di WHERE di.dis_app_id = :idApplication",
            nativeQuery = true
    )
    Page<Dispositivo> getAllByApplication(Integer idApplication, Pageable pageable);

    /**
     * Devuelve un Page de todos los Dispositivos de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idCliente
     * @param pageable
     * @return page de Dispositivo
     */
    @Query(
            value = "SELECT * FROM tb_dispositivos di WHERE di.dis_cli_id = :idCliente",
            countQuery = "SELECT count(*) FROM tb_dispositivos di WHERE di.dis_cli_id = :idCliente",
            nativeQuery = true
    )
    Page<Dispositivo> getAllByCliente(Integer idCliente, Pageable pageable);

    /**
     * Devuelve un Page de todos los Dispositivos de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idZona
     * @param pageable
     * @return page de Dispositivo
     */
    @Query(
            value = "SELECT * FROM tb_dispositivos di WHERE di.dis_zon_id = :idZona",
            countQuery = "SELECT count(*) FROM tb_dispositivos di WHERE di.dis_zon_id = :idZona",
            nativeQuery = true
    )
    Page<Dispositivo> getAllByZona(Integer idZona, Pageable pageable);

    /**
     * Devuelve un Page de todos los Dispositivos de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idEmpresa
     * @param pageable
     * @return page de Dispositivo
     */
    @Query(
            value = "SELECT * FROM tb_dispositivos di WHERE di.dis_emp_id = :idEmpresa",
            countQuery = "SELECT count(*) FROM tb_dispositivos di WHERE di.dis_emp_id = :idEmpresa",
            nativeQuery = true
    )
    Page<Dispositivo> getAllByEmpresa(Integer idEmpresa, Pageable pageable);

    /**
     * Devuelve un Optional de un Dispositivo de la base de datos
     * Para la busqueda de información se usa JPA findByDeveui
     *
     * @param deveui
     * @return optionsl de Dispositivo
     */
    Optional<Dispositivo> findByDeveui(String deveui);

    /**
     * Devuelve un Optional de un Dispositivo de la base de datos
     * Para la busqueda de información se usa JPA findByDeveuiEmpresa
     *
     * @param deveuiEmpresa
     * @return optionsl de Dispositivo
     */
    Optional<Dispositivo> findByDeveuiEmpresa(String deveuiEmpresa);

    /**
     * Devuelve una lista de Dispositivos de la base de datos
     * Para la busqueda de información se usa un QueryJPQL
     *
     * @param idZona
     * @return lista de Dispositivo
     */
    @Query(
            value = "select d from Dispositivo d where d.zona.iden = :idZona"
    )
    List<Dispositivo> getAllByZonaId(Integer idZona);

    /**
     * Devuelve una entero resultante del conteo de Dispositivos por appplication de la base de datos
     *
     * @param idApplication
     * @return Integer
     */
    Integer countByApplicationIden(Integer idApplication);

    /**
     * Devuelve un Page de todos los Dispositivos de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idTipo
     * @param idEmpresa
     * @param idZona
     * @param pageable
     * @return page de Dispositivo
     */
    @Query(
            value = "SELECT * FROM tb_dispositivos di " +
                    "left join tb_applications ta on ta.app_iden = di.dis_app_id \n" +
                    "left join tb_protocolo_tipos tpt on tpt.prt_iden = ta.app_pti_id \n" +
                    "WHERE di.dis_emp_id = :idEmpresa and di.dis_distip_iden = :idTipo and di.dis_zon_id = :idZona and tpt.prt_iden = :idProtocolo ",
            countQuery = "SELECT count(*) FROM tb_dispositivos di " +
                    "left join tb_applications ta on ta.app_iden = di.dis_app_id \n" +
                    "left join tb_protocolo_tipos tpt on tpt.prt_iden = ta.app_pti_id \n" +
                    "WHERE di.dis_emp_id = :idEmpresa and di.dis_distip_iden = :idTipo and di.dis_zon_id = :idZona and tpt.prt_iden = :idProtocolo",
            nativeQuery = true
    )
    Page<Dispositivo> getAllByTipoEmpZonProt(Integer idEmpresa, Integer idTipo, Integer idZona, Integer idProtocolo, Pageable pageable);

    /**
     * Devuelve un Page de todos los Dispositivos de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idCliente
     * @param idProtocolo
     * @param pageable
     * @return page de Dispositivo
     */
    @Query(
            value = "select * from tb_dispositivos td " +
                    "left join tb_applications ta on ta.app_iden = td.dis_app_id \n" +
                    "left join tb_protocolo_tipos tpt on tpt.prt_iden = ta.app_pti_id \n" +
                    "left join tb_clientes tc on tc.cli_iden = td.dis_cli_id \n" +
                    "left join tb_interventores ti on ti.int_iden = tc.cli_int_id \n" +
                    "where ti.int_codi = :codCliente \n" +
                    "and tpt.prt_iden = :idProtocolo \n" +
                    "and td.dis_emp_id = :idEmpresa ",
            countQuery = "select count(*) from tb_dispositivos td " +
                    "left join tb_applications ta on ta.app_iden = td.dis_app_id \n" +
                    "left join tb_protocolo_tipos tpt on tpt.prt_iden = ta.app_pti_id \n" +
                    "left join tb_clientes tc on tc.cli_iden = td.dis_cli_id \n" +
                    "left join tb_interventores ti on ti.int_iden = tc.cli_int_id \n" +
                    "where ti.int_codi = :codCliente \n" +
                    "and tpt.prt_iden = :idProtocolo \n" +
                    "and td.dis_emp_id = :idEmpresa ",
            nativeQuery = true
    )
    Page<Dispositivo> getAllByCienteProtocoloEmpresa(String codCliente, Integer idProtocolo, Integer idEmpresa, Pageable pageable);
}
