package com.telcom.ups.monolitico.credencialtipo.service;

import com.telcom.ups.data.dto.CredencialTipoDTO;
import com.telcom.ups.data.read.CredencialTipoRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CredencialTipoService {

    Page<CredencialTipoRead> findAll(Pageable pageable);

    Optional<CredencialTipoRead> getOne(Integer id);

    CredencialTipoRead save(CredencialTipoDTO credencialTipoDTO);

    boolean delete(Integer id);

    CredencialTipoRead update(CredencialTipoDTO credencialTipoDTO, Integer id);
}
