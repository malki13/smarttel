package com.telcom.ups.monolitico.protocolotipo.service;

import com.telcom.ups.data.dto.ProtocoloTipoDTO;
import com.telcom.ups.data.read.ProtocoloTipoRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProtocoloTipoService {

    Page<ProtocoloTipoRead> findAll(Pageable pageable);

    Optional<ProtocoloTipoRead> getOne(Integer id);

    ProtocoloTipoRead save(ProtocoloTipoDTO protocoloTipoDTO);

    boolean delete(Integer id);

    ProtocoloTipoRead update(ProtocoloTipoDTO protocoloTipoDTO, Integer id);
}
