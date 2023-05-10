package com.telcom.ups.monolitico.zona.repository;

import com.telcom.ups.data.entities.Zona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZonaCrudRepository extends PagingAndSortingRepository<Zona, Integer> {

    /**
     * Devuelve un Page de todas los Zonas de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param id
     * @param pageable
     * @return page de Zona
     */
    @Query(
            value = "SELECT * FROM tb_zonas tz WHERE tz.zon_emp_id = :id",
            countQuery = "SELECT count(*) FROM tb_zonas tz WHERE tz.zon_emp_id = :id",
            nativeQuery = true
    )
    Page<Zona> getAllByEmpresa(@Param("id") Integer id, Pageable pageable);

    /**
     * Devuelve un Optional de una Zona de la base de datos
     * Para la busqueda de información se usa JPA findByNombre
     *
     * @param nombre
     * @return optional de Zona
     */
    Optional<Zona> findByNombre(String nombre);
}
