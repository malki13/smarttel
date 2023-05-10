package com.telcom.ups.monolitico.integracion.service;

import com.telcom.ups.data.dto.AddTopicoDTO;
import com.telcom.ups.data.dto.AddUrlDTO;
import com.telcom.ups.data.dto.IntegracionDTO;
import com.telcom.ups.data.read.IntegracionRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IntegracionService {

    Page<IntegracionRead> findAll(Pageable pageable);

    Optional<IntegracionRead> getOne(Integer id);

    Optional<IntegracionRead> finByNombre(String nombre);

    Optional<IntegracionRead> finByCodigo(String codigo);

    boolean save(IntegracionDTO integracionDTO);

    boolean delete(Integer id);

    boolean update(IntegracionDTO integracionDTO, Integer id);

    boolean addTopico(Integer idIntegracion, AddTopicoDTO addTopicoDTO);

    boolean removeTopico(Integer idTopico);

    boolean addUrl(Integer idIntegracion, AddUrlDTO addUrlDTO);

    boolean removeUrl(Integer idUrl);


}
