package com.telcom.ups.monolitico.detalle.service;

import com.telcom.ups.data.dto.DetalleDTO;
import com.telcom.ups.data.read.DetalleRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DetalleService {

    Page<DetalleRead> findAll(Pageable pageable);

    Optional<DetalleRead> getOne(Integer id);

    DetalleRead save(DetalleDTO detalleDTO);

    void delete(Integer id);

    DetalleRead update(DetalleDTO detalleDTO, Integer id);

}
