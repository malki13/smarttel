package com.telcom.ups.monolitico.configuracioncadena.service;

import com.telcom.ups.data.dto.ConfiguracionCadenaDTO;
import com.telcom.ups.data.info.ConfiguracionCadenaInfo;
import com.telcom.ups.data.read.ConfiguracionCadenaRead;
import org.apache.commons.codec.DecoderException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ConfiguracionCadenaService {

    Page<ConfiguracionCadenaInfo> findAll(Pageable pageable);

    Optional<ConfiguracionCadenaRead> getOne(Integer id);

    ConfiguracionCadenaRead save(Integer dispositivoTipoId, ConfiguracionCadenaDTO configuracionCadenaDTO) throws DecoderException;

    public ConfiguracionCadenaRead saveBase64OnlyFromHexa(Integer dispositivoTipoId, ConfiguracionCadenaDTO configuracionCadenaDTO) throws DecoderException;

    boolean delete(Integer id);

    ConfiguracionCadenaRead update(Integer dispositivoTipoId, ConfiguracionCadenaDTO configuracionCadenaDTO, Integer id) throws DecoderException;

    ConfiguracionCadenaRead updateManual(Integer dispositivoTipoId, ConfiguracionCadenaDTO configuracionCadenaDTO, Integer id) throws DecoderException;

    Page<ConfiguracionCadenaInfo> getAllByDispositivoTipo(Integer idTipo, Pageable pageable);
}
