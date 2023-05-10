package com.telcom.ups.monolitico.parametro.service;

import com.telcom.ups.data.dto.ParametroDTO;
import com.telcom.ups.data.read.ParametroRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ParametroService {

    Page<ParametroRead> findAll(Pageable pageable);

    Optional<ParametroRead> getOne(Integer id);

    ParametroRead save(Integer configuracionId, ParametroDTO parametroDTO);

    boolean delete(Integer id);

    ParametroRead update(Integer configuracionId, ParametroDTO parametroDTO, Integer id);

}
