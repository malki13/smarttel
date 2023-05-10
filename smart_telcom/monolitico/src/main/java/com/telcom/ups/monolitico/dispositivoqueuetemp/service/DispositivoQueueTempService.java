package com.telcom.ups.monolitico.dispositivoqueuetemp.service;

import com.telcom.ups.data.dto.ComunicacionDTO;
import com.telcom.ups.data.dto.DispositivoQueueTempDTO;
import com.telcom.ups.data.info.DispositivoQueueInfo;
import com.telcom.ups.data.info.DispositivoQueueTempInfo;
import com.telcom.ups.data.read.DispositivoQueueTempRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.Optional;

public interface DispositivoQueueTempService {

    Page<DispositivoQueueTempInfo> findAll(Pageable pageable);

    Page<DispositivoQueueInfo> findAllLogs(Pageable pageable);

    Page<DispositivoQueueTempInfo> getAllJob(Pageable pageable);

    Page<DispositivoQueueInfo> getAllByDeveui(String deveui, Pageable pageable);

    Optional<DispositivoQueueTempRead> getOne(Integer id);

    Optional<DispositivoQueueTempRead> findByDeveui(String deveui);

    ComunicacionDTO geEstado(String deveui);

    boolean save(DispositivoQueueTempDTO dispositivoQueueTempDTO, Integer usuarioId, String deveui) throws ParseException;

    boolean delete(String deveui);

    boolean deleteByDeveui(String deveui);

    DispositivoQueueTempRead update(DispositivoQueueTempDTO dispositivoQueueTempDTO, Integer id);

    boolean backEncolarMedidor(DispositivoQueueTempDTO dispositivoQueueTempDTO, Integer usuarioId, String deveui);
}
