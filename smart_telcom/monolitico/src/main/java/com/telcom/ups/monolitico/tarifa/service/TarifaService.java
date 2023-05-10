package com.telcom.ups.monolitico.tarifa.service;

import com.telcom.ups.data.dto.TarifaDTO;
import com.telcom.ups.data.info.TarifaInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TarifaService {

    Page<TarifaInfo> findAll(Pageable pageable);

    Optional<TarifaInfo> getOne(Integer id);

    TarifaInfo save(Integer empresaId, TarifaDTO tarifaDTO);

    boolean delete(Integer id);

    TarifaInfo update(Integer empresaId, TarifaDTO tarifaDTO, Integer id);

    Page<TarifaInfo> getAllByEmpresa(Integer idEmpresa, Pageable pageable);

}
