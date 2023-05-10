package com.telcom.ups.monolitico.application.service;

import com.telcom.ups.data.dto.ApplicationDTO;
import com.telcom.ups.data.info.ApplicationInfo;
import com.telcom.ups.data.read.ApplicationRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {

    Page<ApplicationInfo> findAll(Pageable pageable);

    Optional<ApplicationRead> getOne(Integer id);

    ApplicationRead save(ApplicationDTO applicationDTO);

    boolean delete(Integer id);

    ApplicationRead update(ApplicationDTO applicationDTO, Integer id);

//    Page<ApplicationInfo> getAllByPerfilServicio(Integer idPerfilServicio, Pageable pageable);

    Page<ApplicationInfo> getAllByEmpresa(Integer idEmpresa, Pageable pageable);

    List<ApplicationInfo> getApplicationsByEmpresaProtocolo(Integer idEmpresa, Integer idProtocoloTipo);
}
