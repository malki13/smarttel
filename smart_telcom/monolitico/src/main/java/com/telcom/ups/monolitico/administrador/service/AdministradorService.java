package com.telcom.ups.monolitico.administrador.service;

import com.telcom.ups.data.info.AdministradorInfo;
import com.telcom.ups.data.read.AdministradorRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AdministradorService {

    Page<AdministradorInfo> findAll(Pageable pageable);

    Optional<AdministradorRead> getOne(Integer id);

    AdministradorInfo save(Integer estatusId, Integer interventorId, Integer empresaId);

    boolean delete(Integer id);

    AdministradorInfo update(Integer estatusId, Integer interventorId, Integer empresaId, Integer id);

    Page<AdministradorInfo> getAllByEmpresa(Integer idEmpresa, Pageable pageable);
}
