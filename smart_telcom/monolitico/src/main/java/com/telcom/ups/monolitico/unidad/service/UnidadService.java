package com.telcom.ups.monolitico.unidad.service;

import com.telcom.ups.data.dto.UnidadDTO;
import com.telcom.ups.data.read.UnidadRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UnidadService {

    Page<UnidadRead> findAll(Pageable pageable);

    Optional<UnidadRead> getOne(Integer id);

    UnidadRead save(UnidadDTO unidadDTO);

    boolean delete(Integer id);

    UnidadRead update(UnidadDTO unidadDTO, Integer id);

}
