package com.telcom.ups.monolitico.zonaqueutemp.repository;

import com.telcom.ups.data.entities.ZonaQueueTemp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZonaQueueTempCrudRepository extends JpaRepository<ZonaQueueTemp, Integer> {

    /**
     * Devuelve un Optional de un ZonaQueueTemp de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param deveui
     * @param idZona
     * @return optional de ZonaQueueTemp
     */
    @Query(
            value = "select * from tb_zonas_queue_temp tzq where tzq.quet_zon_deveui = :deveui and tzq.quet_zon_id = :idZona",
            nativeQuery = true
    )
    Optional<ZonaQueueTemp> findByDeveuiZona(String deveui, int idZona);

    /**
     * Devuelve una lista de ZonaQueueTemps de la base de datos
     * Para la busqueda de información se usa JPA findAllByZonaId
     *
     * @param idZona
     * @return lista de ZonaQueueTemp
     */
    List<ZonaQueueTemp> findAllByZonaId(int idZona);

    /**
     * Elimina un ZonaQueueTemp de la base de datos
     * Para eliminar la información se usa JPA deleteByDeveui
     *
     * @param deveui
     */
    void deleteByDeveui(String deveui);

    /**
     * Elimina un ZonaQueueTemp de la base de datos
     * Para eliminar la información se usa JPA deleteAllByZonaId
     *
     * @param zonaId
     */
    void deleteAllByZonaId(Integer zonaId);

    /**
     * Devuelve un Optional de un ZonaQueueTemp de la base de datos
     * Para la busqueda de información se usa JPA findByDeveui
     *
     * @param deveui
     * @return optional de ZonaQueueTemp
     */
    Optional<ZonaQueueTemp> findByDeveui(String deveui);

    /**
     * Devuelve un Page de todas los ZonaQueueTemps de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param idZona
     * @param pageable
     * @return page de ZonaQueueTemp
     */
    @Query(
            value = "SELECT * FROM tb_zonas_queue_temp tzqt WHERE tzqt.quet_zon_id = :idZona",
            countQuery = "SELECT count(*) FROM tb_zonas_queue_temp tzqt WHERE tzqt.quet_zon_id = :idZona",
            nativeQuery = true
    )
    Page<ZonaQueueTemp> getAllByZona(Integer idZona, Pageable pageable);
}
