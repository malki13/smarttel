package com.telcom.ups.monolitico.zonaqueutemp.service;

import com.telcom.ups.data.dto.ComunicacionDTO;
import com.telcom.ups.data.dto.ZonaQueueTempDTO;
import com.telcom.ups.data.dto.ZonaQueueTempOneDTO;
import com.telcom.ups.data.info.ZonaQueueTempInfo;
import com.telcom.ups.data.read.ZonaQueueRead;
import com.telcom.ups.data.read.ZonaQueueReadTemp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.Optional;

public interface ZonaQueueTempService {

    Page<ZonaQueueReadTemp> findAll(Pageable pageable);

    Page<ZonaQueueRead> findAllLogs(Pageable pageable);

    Page<ZonaQueueTempInfo> getAllJob(Pageable pageable);

    Page<ZonaQueueRead> findAllLogsByZona(Integer idZona, Pageable pageable);

    Page<ZonaQueueTempInfo> findAllColaByZona(Integer idZona, Pageable pageable);

    Optional<ZonaQueueReadTemp> getOne(Integer id);

    ZonaQueueReadTemp findByZonaId(Integer id);

    ComunicacionDTO geEstado(Integer id);

    boolean save(ZonaQueueTempDTO zonaQueueTempDTO, Integer usuarioId, Integer zonaId) throws ParseException;

    boolean backEncolarMedidor(ZonaQueueTempOneDTO zonaQueueTempOneDTO, Integer usuarioId, Integer zonaId);

    boolean delete(Integer zonaId);

    boolean deleteByDeveui(String deveui);

    ZonaQueueReadTemp update(ZonaQueueTempDTO zonaQueueTempDTO, Integer id);
}
