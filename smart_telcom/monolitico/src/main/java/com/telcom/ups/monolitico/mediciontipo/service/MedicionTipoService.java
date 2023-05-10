package com.telcom.ups.monolitico.mediciontipo.service;

import com.telcom.ups.data.dto.MedicionTipoDTO;
import com.telcom.ups.data.read.MedicionTipoRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MedicionTipoService {

    Page<MedicionTipoRead> findAll(Pageable pageable);

    Optional<MedicionTipoRead> getOne(Integer id);

    MedicionTipoRead save(MedicionTipoDTO medicionTipoDTO);

    boolean delete(Integer id);

    MedicionTipoRead update(MedicionTipoDTO medicionTipoDTO, Integer id);
    Optional<MedicionTipoRead> findByCodigo(String codigo);
}
