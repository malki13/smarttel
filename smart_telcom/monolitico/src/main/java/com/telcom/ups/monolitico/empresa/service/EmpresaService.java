package com.telcom.ups.monolitico.empresa.service;


import com.telcom.ups.data.dto.EmpresaDTO;
import com.telcom.ups.data.read.EmpresaRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmpresaService {

    Page<EmpresaRead> findAll(Pageable pageable);

    Optional<EmpresaRead> getOne(Integer empresaId);

    EmpresaRead save(Integer interventorId, EmpresaDTO empresaDTO);

    boolean delete(Integer empresaId);

    EmpresaRead update(Integer interventorId, Integer empresaId, EmpresaDTO empresaDTO);
}
