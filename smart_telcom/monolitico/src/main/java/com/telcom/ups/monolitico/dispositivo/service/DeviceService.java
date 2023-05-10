package com.telcom.ups.monolitico.dispositivo.service;

import com.telcom.ups.data.dto.DetalleComunicacionDTO;
import com.telcom.ups.data.dto.DispositivoDTO;
import com.telcom.ups.data.dto.ReporteComunicacionDTO;
import com.telcom.ups.data.info.DispositivoInfo;
import com.telcom.ups.data.info.DispositivoInfoOnly;
import com.telcom.ups.data.read.DispositivoRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DeviceService {

    Page<DispositivoInfoOnly> findAll(Pageable pageable);

    Optional<DispositivoInfo> getOne(Integer id);

    Optional<DispositivoInfo> getDeveui(String deveui);

    DispositivoRead save(DispositivoDTO dispositivoDTO);

    boolean delete(Integer id);

    boolean updatePropietario(Integer idCliente, Integer id);

    DispositivoRead update(DispositivoDTO dispositivoDTO, Integer id);

    boolean updateDetalleComunicacion(DetalleComunicacionDTO detalleComunicacionDTO, String deveui);

    boolean updateReporteComunicacion(ReporteComunicacionDTO reporteComunicacionDTO, String deveui);

    Page<DispositivoInfoOnly> getAllByApplication(Integer idApplication, Pageable pageable);

    Page<DispositivoInfoOnly> getAllByCliente(Integer idCliente, Pageable pageable);

    Page<DispositivoInfoOnly> getAllByZona(Integer idZona, Pageable pageable);

    Page<DispositivoInfoOnly> getAllByEmpresa(Integer idEmpresa, Pageable pageable);

    Page<DispositivoInfoOnly> getAllByTipoEmpZonProt(Integer idEmpresa, Integer idTipo, Integer idZona, Integer idProtocolo, Pageable pageable);

    Page<DispositivoInfoOnly> getAllByCienteProtocoloEmpresa(String codCliente, Integer idProtocolo, Integer idEmpresa, Pageable pageable);

}
