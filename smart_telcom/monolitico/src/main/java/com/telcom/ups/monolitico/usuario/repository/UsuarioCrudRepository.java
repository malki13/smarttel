package com.telcom.ups.monolitico.usuario.repository;

import com.telcom.ups.data.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioCrudRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Devuelve un Optional de un Usuario de la base de datos
     * Para la busqueda de información se usa JPA findByNombre
     *
     * @param nombre
     * @returnm optional de Usuario
     */
    Optional<Usuario> findByNombre(String nombre);

    /**
     * Devuelve una lista de Usuarios de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param nombre
     * @return lista de Usuario
     */
    @Query(nativeQuery = true, value = "SELECT * FROM tb_usuarios tu WHERE tu.usu_nick LIKE %:nombre% LIMIT 10")
    List<Usuario> searchByNombre(@Param("nombre") String nombre);

    /**
     * Devuelve un Page de todos los Usuarios de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param id
     * @param pageable
     * @return page de Usuario
     */
    @Query(
            value = "SELECT * FROM tb_usuarios tu WHERE tu.usu_emp_id = :id",
            countQuery = "SELECT count(*) FROM tb_usuarios tu WHERE tu.usu_emp_id = :id",
            nativeQuery = true
    )
    Page<Usuario> getAllByEmpresa(@Param("id") Integer id, Pageable pageable);

    /**
     * Devuelve un Optional de un Usuario de la base de datos
     * NO USADO
     *
     * @param idInterventor
     * @param IdEmpresa
     * @return optional de Usuario
     */
    Optional<Usuario> findByInterventorIdenAndEmpresaIden(Integer idInterventor, Integer IdEmpresa);

    /**
     * Devuelve un Optional de un Usuario de la base de datos
     * NO USADO
     *
     * @param idInterventor
     * @return optional de Usuario
     */
    Optional<Usuario> findByInterventorIden(Integer idInterventor);

}
