package com.telcom.ups.monolitico.formapago.service;

import com.telcom.ups.data.dto.FormaPagoDTO;
import com.telcom.ups.data.read.FormaPagoRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FormaPagoService {

    Page<FormaPagoRead> findAll(Pageable pageable);

    Optional<FormaPagoRead> getOne(Integer id);

    FormaPagoRead save(FormaPagoDTO formaPagoDTO);

    boolean delete(Integer id);

    FormaPagoRead update(FormaPagoDTO formaPagoDTO, Integer id);

}
