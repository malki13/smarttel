package com.telcom.ups.monolitico.variante.service;

import com.telcom.ups.data.dto.VarianteDTO;
import com.telcom.ups.data.read.VarianteRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VarianteService {

    Page<VarianteRead> findAll(Pageable pageable);

    Optional<VarianteRead> getOne(Integer id);

    VarianteRead save(Integer parametroId, VarianteDTO varianteDTO);

    boolean delete(Integer id);

    VarianteRead update(Integer parametroId, VarianteDTO varianteDTO, Integer id);

}
