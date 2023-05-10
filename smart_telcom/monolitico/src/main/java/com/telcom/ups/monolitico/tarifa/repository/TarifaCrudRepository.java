package com.telcom.ups.monolitico.tarifa.repository;

import com.telcom.ups.data.entities.Tarifa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarifaCrudRepository extends JpaRepository<Tarifa, Integer> {

    /**
     * Devuelve un Page de todas las Tarifa de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idEmpresa
     * @param pageable
     * @return page de Tarifas
     */
    @Query(
            value = "SELECT * FROM tb_tarifas ta WHERE ta.tar_emp_id = :idEmpresa",
            countQuery = "SELECT count(*) FROM tb_tarifas ta WHERE ta.tar_emp_id = :idEmpresa",
            nativeQuery = true
    )
    Page<Tarifa> getAllByEmpresa(Integer idEmpresa, Pageable pageable);

    /**
     * Devuelve un Optional de Tarifa de la base de datos
     * Para la busqueda de información se usa JPA findByNombreCategoria
     *
     * @param nombre
     * @return optional de Tarifa
     */
    Optional<Tarifa> findByNombreCategoria(String nombre);
}
