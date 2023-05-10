package com.telcom.ups.monolitico.interventortipo.service;

import com.telcom.ups.data.dto.InterventorTipoDTO;
import com.telcom.ups.data.read.InterventorTipoRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface InterventorTipoService {

    Page<InterventorTipoRead> findAll(Pageable pageable);

    Optional<InterventorTipoRead> getOne(Integer id);

    InterventorTipoRead save(InterventorTipoDTO interventorTipoDTO);

    boolean delete(Integer id);

    InterventorTipoRead update(InterventorTipoDTO interventorTipoDTO, Integer id);
}
