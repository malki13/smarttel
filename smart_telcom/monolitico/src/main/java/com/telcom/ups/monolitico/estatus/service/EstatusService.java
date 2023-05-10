package com.telcom.ups.monolitico.estatus.service;

import com.telcom.ups.data.dto.EstatusDTO;
import com.telcom.ups.data.read.EstatusRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EstatusService {

    Page<EstatusRead> findAll(Pageable pageable);

    Optional<EstatusRead> getOne(Integer id);

    EstatusRead save(EstatusDTO estatusDTO);

    boolean delete(Integer id);

    EstatusRead update(EstatusDTO estatusDTO, Integer id);
}
