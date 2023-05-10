package com.telcom.ups.monolitico.factura.repository;

import com.telcom.ups.data.entities.Factura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaCrudRepository extends JpaRepository<Factura, Integer> {

    /**
     * Devuelve un Page de todos las facturas de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idCliente
     * @param pageable
     * @return page de Factura
     */
    @Query(
            value = "SELECT * FROM tb_facturas fa WHERE fa.fac_cli_id = :idCliente",
            countQuery = "SELECT count(*) FROM tb_facturas fa WHERE fa.fac_cli_id = :idCliente",
            nativeQuery = true
    )
    Page<Factura> getAllByCliente(Integer idCliente, Pageable pageable);

    /**
     * Devuelve un Page de todos las facturas de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idEmpresa
     * @param pageable
     * @return Factura
     */
    @Query(
            value = "SELECT * FROM tb_facturas fa WHERE fa.fac_emp_id = :idEmpresa",
            countQuery = "SELECT count(*) FROM tb_facturas fa WHERE fa.fac_emp_id = :idEmpresa",
            nativeQuery = true
    )
    Page<Factura> getAllByEmpresa(Integer idEmpresa, Pageable pageable);

    /**
     * Devuelve un Optional de una factura de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param mes
     * @param anio
     * @param deveui
     * @return optionadl de Factura
     */
    @Query(
            //value = "select * from tb_facturas tf where extract(month from tf.fac_fech) = ?1 and extract(year from tf.fac_fech) = :anio and tf.fac_deveui = :deveui",
            value = "select * from tb_facturas tf where substring(tf.fac_fech\\:\\:text,6,2) = ?1 and substring(tf.fac_fech\\:\\:text,1,4) = ?2 and tf.fac_deveui = ?3",
            nativeQuery = true
            //value = "select f from Factura f where substring(f.fechaEmision,6,2) = :mes and substring(f.fechaEmision,1,4) = :anio and f.deveui = :deveui"
    )
    Optional<Factura> findByFechaAnio(String mes, String anio, String deveui);

    /**
     * Devuelve una lista de facturas de la base de datos
     *
     * @param idCliente
     * @param idEmpresa
     * @return lista de Facturas
     */
    List<Factura> findByClienteIdenAndEmpresaIden(Integer idCliente, Integer idEmpresa);

    /**
     * Devuelve un Page de todos las facturas de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idEmpresa
     * @param pageable
     * @return Factura
     */
    @Query(
            value = "select * from tb_facturas tf " +
                    "join tb_dispositivos td on td.dis_deveui = tf.fac_deveui \n" +
                    "join tb_applications ta on ta.app_iden = td.dis_app_id \n" +
                    "join tb_protocolo_tipos tpt on tpt.prt_iden = ta.app_pti_id  \n" +
                    "join tb_zonas tz on tz.zon_iden = td.dis_zon_id \n" +
                    "where tf.fac_emp_id = :idEmpresa \n" +
                    "and tpt.prt_iden = :idProtocolo \n" +
                    "and tz.zon_iden = :idZona ",
            countQuery = "select count(*) from tb_facturas tf " +
                    "join tb_dispositivos td on td.dis_deveui = tf.fac_deveui \n" +
                    "join tb_applications ta on ta.app_iden = td.dis_app_id \n" +
                    "join tb_protocolo_tipos tpt on tpt.prt_iden = ta.app_pti_id  \n" +
                    "join tb_zonas tz on tz.zon_iden = td.dis_zon_id \n" +
                    "where tf.fac_emp_id = :idEmpresa \n" +
                    "and tpt.prt_iden = :idProtocolo \n" +
                    "and tz.zon_iden = :idZona ",
            nativeQuery = true
    )
    Page<Factura> getAllByEmpProtZon(Integer idEmpresa, Integer idProtocolo, Integer idZona, Pageable pageable);

    /**
     * Devuelve un Page de todos las facturas de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idEmpresa
     * @param pageable
     * @return Factura
     */
    @Query(
            value = "select * from tb_facturas tf " +
                    "join tb_dispositivos td on td.dis_deveui = tf.fac_deveui \n" +
                    "join tb_applications ta on ta.app_iden = td.dis_app_id \n" +
                    "join tb_protocolo_tipos tpt on tpt.prt_iden = ta.app_pti_id \n" +
                    "join tb_clientes tc on tc.cli_iden = tf.fac_cli_id \n" +
                    "where tf.fac_emp_id = :idEmpresa \n" +
                    "and tpt.prt_iden = :idProtocolo \n" +
                    "and tc.cli_iden = :idCliente ",
            countQuery = "select count(*) from tb_facturas tf " +
                    "join tb_dispositivos td on td.dis_deveui = tf.fac_deveui \n" +
                    "join tb_applications ta on ta.app_iden = td.dis_app_id \n" +
                    "join tb_protocolo_tipos tpt on tpt.prt_iden = ta.app_pti_id \n" +
                    "join tb_clientes tc on tc.cli_iden = tf.fac_cli_id \n" +
                    "where tf.fac_emp_id = :idEmpresa \n" +
                    "and tpt.prt_iden = :idProtocolo \n" +
                    "and tc.cli_iden = :idCliente ",
            nativeQuery = true
    )
    Page<Factura> getAllByEmpProtCli(Integer idEmpresa, Integer idProtocolo, Integer idCliente, Pageable pageable);

}
