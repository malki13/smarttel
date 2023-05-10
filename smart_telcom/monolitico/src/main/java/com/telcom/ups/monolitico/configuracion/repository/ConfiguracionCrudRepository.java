package com.telcom.ups.monolitico.configuracion.repository;

import com.telcom.ups.data.entities.Configuracion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfiguracionCrudRepository extends JpaRepository<Configuracion, Integer> {

    /**
     * Devuelve un Page de todas las Configuraciones de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idTipo
     * @param pageable
     * @return page de Configuracion
     */
    @Query(
            value = "SELECT * FROM tb_configuraciones co WHERE co.con_distip_id = :idTipo",
            countQuery = "SELECT count(*) FROM tb_configuraciones co WHERE co.con_distip_id = :idTipo",
            nativeQuery = true
    )
    Page<Configuracion> getAllByTipoDispositivo(Integer idTipo, Pageable pageable);

    /**
     * Devuelve un Optional de una Configuracion de la base de datos
     * Para la busqueda de información se usa JPA findByNombre
     *
     * @param nombre
     * @return optional de Configuracion
     */
    Optional<Configuracion> findByNombre(String nombre);
}
