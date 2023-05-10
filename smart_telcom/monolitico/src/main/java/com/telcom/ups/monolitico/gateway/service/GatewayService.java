package com.telcom.ups.monolitico.gateway.service;

import com.telcom.ups.data.dto.GatewayDTO;
import com.telcom.ups.data.info.GatewayInfo;
import com.telcom.ups.data.read.GatewayRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GatewayService {

    Page<GatewayInfo> findAll(Pageable pageable);

    Optional<GatewayRead> getOne(Integer id);

    GatewayRead save(Integer empresaId, GatewayDTO gatewayDTO);

    boolean delete(Integer id);

    GatewayRead update(Integer empresaId, GatewayDTO gatewayDTO, Integer id);

    List<GatewayInfo> searchByNombre(String nombre);

    List<GatewayInfo> searchById(String id);

    Page<GatewayInfo> getAllByEmpresa(Integer idEmpresa, Pageable pageable);

//    Page<GatewayInfo> getAllByIntegracion(Integer idIntegracion, Pageable pageable);

}
