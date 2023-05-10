package com.telcom.ups.monolitico.cliente.service;

import com.telcom.ups.data.info.ClienteInfo;
import com.telcom.ups.data.info.ClienteInfoOnly;
import com.telcom.ups.data.read.ClienteRead;
import com.telcom.ups.data.read.EmpresaRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Page<ClienteInfoOnly> findAll(Pageable pageable);

    Optional<ClienteInfo> getOne(Integer id);

    Optional<ClienteInfoOnly> getOneByEmpresaCodigo(String codigo, Integer idEmpresa);

    List<EmpresaRead> getEmpresasByCodigo(String codigo);

    ClienteRead save(Integer interventorId, Integer estatusId, Integer empresaId);

    boolean delete(Integer id);

    ClienteInfo update(Integer interventorId, Integer estatusId, Integer empresaId, Integer id);

    Page<ClienteInfoOnly> getAllByEmpresa(Integer idEmpresa, Pageable pageable);
}
