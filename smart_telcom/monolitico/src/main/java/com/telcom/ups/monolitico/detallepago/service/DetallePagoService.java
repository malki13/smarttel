package com.telcom.ups.monolitico.detallepago.service;

import com.telcom.ups.data.dto.DetallePagoDTO;
import com.telcom.ups.data.read.DetallePagoRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DetallePagoService {

    Page<DetallePagoRead> findAll(Pageable pageable);

    Optional<DetallePagoRead> getOne(Integer id);

    DetallePagoRead save(DetallePagoDTO detallePagoDTO);

    void delete(Integer id);

    DetallePagoRead update(DetallePagoDTO detallePagoDTO, Integer id);

}
