package com.telcom.ups.monolitico.anexo.service;

import com.telcom.ups.data.dto.AnexoDTO;
import com.telcom.ups.data.read.AnexoRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AnexoService {

    Page<AnexoRead> findAll(Pageable pageable);

    Optional<AnexoRead> getOne(Integer id);

    AnexoRead save(AnexoDTO anexoDTO);

    void delete(Integer id);

    AnexoRead update(AnexoDTO anexoDTO, Integer id);

}
