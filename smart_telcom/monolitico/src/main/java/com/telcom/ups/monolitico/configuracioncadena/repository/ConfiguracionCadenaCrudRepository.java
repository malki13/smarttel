package com.telcom.ups.monolitico.configuracioncadena.repository;

import com.telcom.ups.data.entities.ConfiguracionCadena;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfiguracionCadenaCrudRepository extends JpaRepository<ConfiguracionCadena, Integer> {

    /**
     * Devuelve un Page de todos las cadenas de configuracion de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idTipo
     * @param pageable
     * @return page ConfiguracionCadena
     */
    @Query(
            value = "SELECT * FROM tb_configuraciones_cadena coc WHERE coc.con_cad_distip_id = :idTipo",
            countQuery = "SELECT count(*) FROM tb_configuraciones_cadena coc WHERE coc.con_cad_distip_id = :idTipo",
            nativeQuery = true
    )
    Page<ConfiguracionCadena> getAllByTipoDispositivo(Integer idTipo, Pageable pageable);

    /**
     * Devuelve un Optional de una ConfiguracionCadena de la base de datos
     * Para la busqueda de información se usa JPA findByNombre
     *
     * @param nombre
     * @return optional de ConfiguracionCadena
     */
    Optional<ConfiguracionCadena> findByNombre(String nombre);
}
