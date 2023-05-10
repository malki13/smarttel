package com.telcom.ups.monolitico.dispositivotipo.service;

import com.telcom.ups.data.dto.DispositivoTipoDTO;
import com.telcom.ups.data.info.DispositivoTipoInfo;
import com.telcom.ups.data.info.DispositivoTipoInfoOnly;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DispositivoTipoService {

    Page<DispositivoTipoInfoOnly> findAll(Pageable pageable);

    Optional<DispositivoTipoInfo> getOne(Integer id);

    DispositivoTipoDTO save(DispositivoTipoDTO dispositivoTipoDTO);

    boolean delete(Integer id);

    DispositivoTipoDTO update(DispositivoTipoDTO dispositivoTipoDTO, Integer id);

}
