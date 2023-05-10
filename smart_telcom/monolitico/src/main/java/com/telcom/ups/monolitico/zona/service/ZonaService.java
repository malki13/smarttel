package com.telcom.ups.monolitico.zona.service;

import com.telcom.ups.data.dto.ZonaDTO;
import com.telcom.ups.data.info.ZonaInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ZonaService {

    Page<ZonaInfo> findAll(Pageable pageable);

    Optional<ZonaInfo> getOne(Integer id);

    ZonaInfo save(Integer empresaId, Integer adminId, Integer tecnicoId, ZonaDTO zonaDTO);

    boolean delete(Integer id);

    ZonaInfo update(Integer empresaId, Integer adminId, Integer tecnicoId, Integer id, ZonaDTO zonaDTO);

    Page<ZonaInfo> getAllByEmpresa(Integer id, Pageable pageable);
}
