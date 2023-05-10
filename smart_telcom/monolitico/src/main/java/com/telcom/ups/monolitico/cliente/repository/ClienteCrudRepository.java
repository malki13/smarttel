package com.telcom.ups.monolitico.cliente.repository;

import com.telcom.ups.data.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteCrudRepository extends JpaRepository<Cliente, Integer> {

    /**
     * Devuelve un Page de todos los clientes de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idEmpresa
     * @param pageable
     * @return page de Cliente
     */
    @Query(
            value = "SELECT * FROM tb_clientes cl WHERE cl.cli_emp_id = :idEmpresa",
            countQuery = "SELECT count(*) FROM tb_clientes cl WHERE cl.cli_emp_id = :idEmpresa",
            nativeQuery = true
    )
    Page<Cliente> getAllByEmpresa(Integer idEmpresa, Pageable pageable);

    /**
     * Devuelve un Optional de un Cliente de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idInterventor
     * @param idEmpresa
     * @return optional de Cliente
     */
    @Query(
            value = "select * from tb_clientes tc where tc.cli_int_id = :idInterventor and tc.cli_emp_id = :idEmpresa",
            nativeQuery = true
    )
    Optional<Cliente> findByInterventorEmpresa(int idInterventor, int idEmpresa);

    /**
     * Devuelve un Optional de un Cliente de la base de datos
     * Para la busqueda de información se usa JPA findByInterventorCodigo
     *
     * @param codigo
     * @return optional de Cliente
     */
    Optional<Cliente> findByInterventorCodigoAndEmpresaIden(String codigo, Integer idEmpresa);

    /**
     * Devuelve un lista de Clientes de la base de datos
     *
     * @param codigo
     * @return lista de Clientes
     */
    List<Cliente> findByInterventorCodigo(String codigo);
}
