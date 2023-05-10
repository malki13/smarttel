package com.telcom.ups.monolitico.tecnico.service;

import com.telcom.ups.data.info.TecnicoInfo;
import com.telcom.ups.data.read.TecnicoRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TecnicoService {

    Page<TecnicoInfo> findAll(Pageable pageable);

    Optional<TecnicoRead> getOne(Integer id);

    TecnicoInfo save(Integer estatusId, Integer interventorId, Integer empresaId);

    boolean delete(Integer id);

    TecnicoInfo update(Integer estatusId, Integer interventorId, Integer empresaId, Integer id);

    Page<TecnicoInfo> getAllByEmpresa(Integer idEmpresa, Pageable pageable);
}
