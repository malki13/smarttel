package com.telcom.ups.monolitico.configuracion.service;

import com.telcom.ups.data.dto.ConfiguracionDTO;
import com.telcom.ups.data.read.ConfiguracionRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ConfiguracionService {

    Page<ConfiguracionRead> findAll(Pageable pageable);

    Optional<ConfiguracionRead> getOne(Integer id);

    ConfiguracionRead save(Integer dispositivoTipoId, ConfiguracionDTO configuracionDTO);

    boolean delete(Integer id);

    ConfiguracionRead update(Integer dispositivoTipoId, ConfiguracionDTO configuracionDTO, Integer id);

    Page<ConfiguracionRead> getAllByDispositivoTipo(Integer idTipo, Pageable pageable);
}
