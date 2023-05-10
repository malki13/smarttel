package com.telcom.ups.monolitico.topico.repository;

import com.telcom.ups.data.entities.Topico;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoCrudRepository extends PagingAndSortingRepository<Topico, Integer> {

    void deleteAllByIntegracionIden(Integer id);
}
