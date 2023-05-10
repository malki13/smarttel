package com.telcom.ups.monolitico.factura.service;

import com.telcom.ups.data.dto.FacturaDTO;
import com.telcom.ups.data.info.FacturaInfo;
import com.telcom.ups.data.info.FacturaInfoOnly;
import com.telcom.ups.data.read.FacturaRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FacturaService {

    Page<FacturaInfoOnly> findAll(Pageable pageable);

    List<FacturaInfoOnly> getFacturasByClienteEmpresa(Integer idCliente, Integer idEmpresa);

    Optional<FacturaInfo> getOne(Integer id);

    boolean save(Integer empresaId, Integer clienteId, FacturaDTO facturaDTO);

    boolean delete(Integer id);

    FacturaRead update(FacturaDTO facturaDTO, Integer id);

    Page<FacturaInfoOnly> getAllByCliente(Integer idCliente, Pageable pageable);

    Page<FacturaInfoOnly> getAllByEmpresa(Integer idEmpresa, Pageable pageable);

    Page<FacturaInfoOnly> getAllByEmpProtZon(Integer idEmpresa, Integer idProtocolo, Integer idZona, Pageable pageable);

    Page<FacturaInfoOnly> getAllByEmpProtCli(Integer idEmpresa, Integer idProtocolo, Integer idCliente, Pageable pageable);

}
