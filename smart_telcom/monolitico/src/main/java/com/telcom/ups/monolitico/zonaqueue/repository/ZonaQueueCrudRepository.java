package com.telcom.ups.monolitico.zonaqueue.repository;

import com.telcom.ups.data.entities.ZonaQueue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaQueueCrudRepository extends JpaRepository<ZonaQueue, Integer> {

    /**
     * Elimina un ZonaQueue de la base de datos
     * Para eliminar la información se usa JPA deleteAllByZonaIden
     *
     * @param zonaId
     */
    void deleteAllByZonaIden(Integer zonaId);

    /**
     * Devuelve un Page de todos los ZonaQueue de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idZona
     * @param pageable
     * @return page de ZonaQueue
     */
    @Query(
            value = "SELECT * FROM tb_zonas_queue tzq WHERE tzq.que_zon_id = :idZona",
            countQuery = "SELECT count(*) FROM tb_zonas_queue tzq WHERE tzq.que_zon_id = :idZona",
            nativeQuery = true
    )
    Page<ZonaQueue> getAllByZona(Integer idZona, Pageable pageable);
}
