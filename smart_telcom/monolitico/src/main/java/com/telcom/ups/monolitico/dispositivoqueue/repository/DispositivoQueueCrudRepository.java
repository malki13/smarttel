package com.telcom.ups.monolitico.dispositivoqueue.repository;

import com.telcom.ups.data.entities.DispositivoQueue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DispositivoQueueCrudRepository extends JpaRepository<DispositivoQueue, Integer> {

    /**
     * Devuelve un Optional de un DispositivoQueue de la base de datos
     * Para la busqueda de información se usa JPA findByDeveui
     *
     * @param deveui
     * @return optional de DispositivoQueue
     */
    Optional<DispositivoQueue> findByDeveui(String deveui);

    /**
     * Elimina un DispositivoQueue de la base de datos
     * Para eliminar la información se usa JPA deleteByDeveui
     *
     * @param deveui
     */
    void deleteByDeveui(String deveui);

    /**
     * Devuelve un Page de todos los DispositivoQueues de la base de datos
     * Para la busqueda de información se usa un QueryNativo
     *
     * @param deveui
     * @param pageable
     * @return page de DispositivoQueue
     */
    @Query(
            value = "SELECT * FROM tb_dispositivos_queue tdq WHERE tdq.que_dis_deveui = :deveui",
            countQuery = "SELECT count(*) FROM tb_dispositivos_queue tdq WHERE tdq.que_dis_deveui = :deveui",
            nativeQuery = true
    )
    Page<DispositivoQueue> getAllByDeveui(String deveui, Pageable pageable);
}
