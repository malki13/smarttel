package com.telcom.ups.monolitico.resquestresponse.repository;

import com.telcom.ups.data.entities.RequestResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestResponseCrudRepository extends JpaRepository<RequestResponse, Integer> {

    void deleteAllByIntegracionIden(Integer id);
}
