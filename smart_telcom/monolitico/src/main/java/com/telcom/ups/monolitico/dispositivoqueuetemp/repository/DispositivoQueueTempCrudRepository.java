package com.telcom.ups.monolitico.dispositivoqueuetemp.repository;

import com.telcom.ups.data.entities.DispositivoQueueTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DispositivoQueueTempCrudRepository extends JpaRepository<DispositivoQueueTemp, Integer> {

    /**
     * Devuelve un Optional de un DispositivoQueueTemp de la base de datos
     * Para la busqueda de información se usa JPA findByDeveui
     *
     * @param deveui
     * @return optional de DispositivoQueueTemp
     */
    Optional<DispositivoQueueTemp> findByDeveui(String deveui);

    /**
     * Elimina un DispositivoQueueTemp de la base de datos
     * Para eliminar la información se usa JPA deleteByDeveui
     *
     * @param deveui
     */
    void deleteByDeveui(String deveui);


}
