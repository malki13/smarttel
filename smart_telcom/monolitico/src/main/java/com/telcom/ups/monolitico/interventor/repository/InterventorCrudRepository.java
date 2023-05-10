package com.telcom.ups.monolitico.interventor.repository;

import com.telcom.ups.data.entities.Interventor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterventorCrudRepository extends JpaRepository<Interventor, Integer> {

    /**
     * Devuelve una lista de Interventores de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param codigo
     * @return lista de Interventores
     */
    @Query(nativeQuery = true, value = "SELECT * FROM tb_interventores ti WHERE ti.int_codi LIKE %:codigo% LIMIT 10")
    List<Interventor> searchByCodigo(@Param("codigo") String codigo);

    /**
     * Devuelve una lista de Interventores de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param apellido
     * @return lista de Interventores
     */
    @Query(nativeQuery = true, value = "SELECT * FROM tb_interventores ti WHERE ti.int_apel LIKE %:apellido% LIMIT 10")
    List<Interventor> searchByApellido(@Param("apellido") String apellido);

    /**
     * Devuelve un Optional de un Interventor de la base de datos
     * Para la busqueda de información se usa JPA findByCodigo
     *
     * @param codigo
     * @return optional de Interventor
     */
    Optional<Interventor> findByCodigo(String codigo);

}
