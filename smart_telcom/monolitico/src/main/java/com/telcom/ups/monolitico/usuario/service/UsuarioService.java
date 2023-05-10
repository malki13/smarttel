package com.telcom.ups.monolitico.usuario.service;

import com.telcom.ups.data.dto.UsuarioDTO;
import com.telcom.ups.data.info.UsuarioInfo;
import com.telcom.ups.data.read.UsuarioRead;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Page<UsuarioInfo> findAll(Pageable pageable);

    Optional<UsuarioRead> getOne(Integer id);

    Optional<UsuarioRead> getByNombre(String nombre);

    UsuarioInfo save(Integer estatusId, Integer interventorId, Integer empresaId, UsuarioDTO usuarioDTO);

    boolean delete(Integer id);

    UsuarioInfo update(Integer estatusId, Integer interventorId, Integer empresaId, Integer id, UsuarioDTO usuarioDTO);

    List<UsuarioRead> searchByNombre(String nombre);

    UsuarioInfo updatePassword(Integer id, String password);

    Page<UsuarioInfo> getAllByEmpresa(Integer id, Pageable pageable);
}
