package com.telcom.ups.monolitico.usuario.service;

import com.telcom.ups.data.dto.UsuarioDTO;
import com.telcom.ups.data.entities.*;
import com.telcom.ups.data.info.UsuarioInfo;
import com.telcom.ups.data.read.UsuarioRead;
import com.telcom.ups.monolitico.empresa.repository.EmpresaCrudRepository;
import com.telcom.ups.monolitico.estatus.repository.EstatusCrudRepository;
import com.telcom.ups.monolitico.interventor.repository.InterventorCrudRepository;
import com.telcom.ups.monolitico.rol.mapper.RolMapper;
import com.telcom.ups.monolitico.rol.repository.RolCrudRepository;
import com.telcom.ups.monolitico.usuario.mapper.UsuarioMapper;
import com.telcom.ups.monolitico.usuario.repository.UsuarioCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private EmpresaCrudRepository empresaCrudRepository;

    @Autowired
    private InterventorCrudRepository interventorCrudRepository;

    @Autowired
    private EstatusCrudRepository estatusCrudRepository;

    @Autowired
    private RolCrudRepository rolCrudRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private RolMapper rolMapper;


    /**
     * Devuelve un Page de UsuarioInfos que provee el repositorio
     * Para devolver un Page de UsuarioInfos se realiza el proceso de conversión de objetos con la interfaz UsuarioMapper
     *
     * @param pageable
     * @return page de UsuarioInfo
     */
    @Override
    public Page<UsuarioInfo> findAll(Pageable pageable) {
        return usuarioCrudRepository.findAll(pageable).map(usuario -> usuarioMapper.toUsuarioInfo(usuario));
    }

    /**
     * Devuelve un Optional de UsuarioRead que provee el repositorio
     * Para devolver un optional de UsuarioRead se realiza el proceso de conversión de objetos con la interfaz UsuarioMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesta de la petición
     *
     * @param id
     * @return optional de UsuarioRead
     */
    @Override
    public Optional<UsuarioRead> getOne(Integer id) {
        Optional<Usuario> usuarioDB = usuarioCrudRepository.findById(id);
        if (usuarioDB.isPresent()) {
            return usuarioDB.map(usuario -> usuarioMapper.toUsuarioRead(usuario));
        }
        throw new BadRequestException("Usuario con id " + id + " no encontrado");
    }

    /**
     * Devuelve un Optional de UsuarioRead que provee el repositorio
     * Para devolver un optional de UsuarioRead se realiza el proceso de conversión de objetos con la interfaz UsuarioMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesta de la petición
     *
     * @param nombre
     * @return optional de UsuarioRead
     */
    @Override
    public Optional<UsuarioRead> getByNombre(String nombre) {
        Optional<Usuario> usuarioDB = usuarioCrudRepository.findByNombre(nombre);
        if (usuarioDB.isPresent()) {
            return usuarioDB.map(usuario -> usuarioMapper.toUsuarioRead(usuario));
        }
        throw new BadRequestException("Usuario con nombre " + nombre + " no encontrado");
    }

    /**
     * Devuelve un UsuarioInfo que provee el repositorio
     * Para devolver un UsuarioInfo se realiza el proceso de conversión de objetos con la interfaz UsuarioMapper
     * Para guardar un usuario previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesta de la petición
     *
     * @param estatusId
     * @param interventorId
     * @param empresaId
     * @param usuarioDTO
     * @return UsuarioInfo
     */
    @Override
    public UsuarioInfo save(Integer estatusId, Integer interventorId, Integer empresaId, UsuarioDTO usuarioDTO) {
        Optional<Estatus> estatusDB = estatusCrudRepository.findById(estatusId);
        if (estatusDB.isPresent()) {
            Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
            if (interventorDB.isPresent()) {
                Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
                if (empresaDB.isPresent()) {
                    Optional<Usuario> usuarioDB = usuarioCrudRepository.findByNombre(usuarioDTO.getNombre());
                    if (!usuarioDB.isPresent()) {
                        Usuario usuario = usuarioMapper.toUsuario(usuarioDTO);
                        usuario.setEstatus(estatusDB.get());
                        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
                        usuario.setInterventor(interventorDB.get());
                        usuario.setEmpresa(empresaDB.get());
                        return usuarioMapper.toUsuarioInfo(usuarioCrudRepository.save(usuario));
                    }
                    throw new BadRequestException("Usuario con nombre " + usuarioDTO.getNombre() + " ya esta registrado");
                }
                throw new BadRequestException("Empresa con id " + empresaId + " no encontrada para relacionar al usuario");
            }
            throw new BadRequestException("Interventor con id " + interventorId + " no encontrado para relacionar al usuario");
        }
        throw new BadRequestException("Estatus con id " + estatusId + " no encontrado para relacionar al usuario");
    }

    /**
     * Devuelve un boolean al borrar la informacion mediante el repositorio
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesta de la petición
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean delete(Integer id) {
        try {
            Optional<Usuario> usuarioDB = usuarioCrudRepository.findById(id);
            if (usuarioDB.isPresent()) {
                usuarioCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Usuario con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El usuario que intenta eliminar cuenta con objetos relacionados. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso el usuario no debe contar con objetos relacionados al mismo.");
        }
    }

    /**
     * Devuelve un UsuarioInfo que provee el repositorio
     * Para devolver un UsuarioInfo se realiza el proceso de conversión de objetos con la interfaz UsuarioMapper
     * Para guardar un usuario previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesta de la petición
     *
     * @param estatusId
     * @param interventorId
     * @param empresaId
     * @param id
     * @param usuarioDTO
     * @return UsuarioInfo
     */
    @Override
    public UsuarioInfo update(Integer estatusId, Integer interventorId, Integer empresaId, Integer id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioDB = usuarioCrudRepository.findById(id);
        if (usuarioDB.isPresent()) {
            Optional<Estatus> estatusDB = estatusCrudRepository.findById(estatusId);
            if (estatusDB.isPresent()) {
                Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
                if (interventorDB.isPresent()) {
                    Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
                    if (empresaDB.isPresent()) {
                        Usuario usuario = usuarioDB.get();
                        Usuario newUsuario = usuarioMapper.toUsuario(usuarioDTO);
                        usuario.setNombre(newUsuario.getNombre());
                        usuario.setEstatus(estatusDB.get());
                        usuario.setInterventor(interventorDB.get());
                        usuario.setEmpresa(empresaDB.get());
                        usuario.setRols(newUsuario.getRols());
                        usuario.setUpdatedAt(LocalDateTime.now());
                        return usuarioMapper.toUsuarioInfo(usuarioCrudRepository.save(usuario));
                    }
                    throw new BadRequestException("Empresa con id " + empresaId + " no encontrada para relacionar al usuario");
                }
                throw new BadRequestException("Interventor con id " + interventorId + " no encontrado para relacionar al usuario");
            }
            throw new BadRequestException("Estatus con id " + estatusId + " no encontrado para relacionar al usuario");
        }
        throw new BadRequestException("Usuario con id " + id + " no encontrado para actualizar");
    }

    /**
     * Devuelve un Lista de UsuarioReads que provee el repositorio
     * Para devolver un Lista de UsuarioRead se realiza el proceso de conversión de objetos con la interfaz UsuarioMapper
     *
     * @param nombre
     * @return lista de UsuarioRead
     */
    @Override
    public List<UsuarioRead> searchByNombre(String nombre) {
        return usuarioMapper.toUsuarioReads(usuarioCrudRepository.searchByNombre(nombre));
    }

    /**
     * Devuelve un UsuarioInfo que provee el repositorio
     * Para devolver un UsuarioInfo se realiza el proceso de conversión de objetos con la interfaz UsuarioMapper
     * Para actualizar un password previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * y ademas se realiza el proceso de encriptación del password y se responde con una excepcion de BadRequestException como respuesta de la petición
     *
     * @param id
     * @param password
     * @return UsuarioInfo
     */
    @Override
    public UsuarioInfo updatePassword(Integer id, String password) {
        Optional<Usuario> usuarioDB = usuarioCrudRepository.findById(id);
        if (usuarioDB.isPresent()) {
            Usuario us = usuarioDB.get();
            us.setPassword(passwordEncoder.encode(password));
            return usuarioMapper.toUsuarioInfo(usuarioCrudRepository.save(us));
        }
        throw new BadRequestException("Usuario con id " + id + " no encontrado para actualizar el password");
    }

    /**
     * Devuelve un Page de UsuarioInfos que provee el repositorio
     * Para devolver un page de UsuarioInfos se realiza el proceso de conversión de objetos con la interfaz UsuarioMapper
     *
     * @param id
     * @param pageable
     * @return page de UsuarioInfo
     */
    @Override
    public Page<UsuarioInfo> getAllByEmpresa(Integer id, Pageable pageable) {
        return usuarioCrudRepository.getAllByEmpresa(id, pageable).map(usuario -> usuarioMapper.toUsuarioInfo(usuario));
    }

    /**
     * Devuelve un boolean al agregar un rol al usuario mediante el repositorio
     * Para agregar un rol a un usuario previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesta de la petición
     *
     * @param usuarioId
     * @param rolId
     * @return boolean
     */
    public boolean addRol(Integer usuarioId, Integer rolId) {
        Optional<Rol> rolDB = rolCrudRepository.findById(rolId);
        if (rolDB.isPresent()) {
            Optional<Usuario> usuarioDB = usuarioCrudRepository.findById(usuarioId);
            if (usuarioDB.isPresent()) {
                Usuario usuario = usuarioDB.get();
                usuario.addRol(rolDB.get());
                usuarioCrudRepository.save(usuario);
                return true;
            }
            throw new BadRequestException("Usuario con id " + usuarioId + " no encontrado para relacionar el rol");
        }
        throw new BadRequestException("Rol con id " + rolId + " no encontrado para relacionar a usuario");
    }

    /**
     * Devuelve un boolean al remover un rol al usuario mediante el repositorio
     * Para remover un rol a un usuario previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesta de la petición
     *
     * @param usuarioId
     * @param rolId
     * @return boolean
     */
    public boolean removeRol(Integer usuarioId, Integer rolId) {
        Optional<Rol> rolDB = rolCrudRepository.findById(rolId);
        if (rolDB.isPresent()) {
            Optional<Usuario> usuarioDB = usuarioCrudRepository.findById(usuarioId);
            if (usuarioDB.isPresent()) {
                Usuario usuario = usuarioDB.get();
                usuario.removeRol(rolDB.get());
                usuarioCrudRepository.save(usuario);
                return true;
            }
            throw new BadRequestException("Usuario con id " + usuarioId + " no encontrado para remover el rol");
        }
        throw new BadRequestException("Rol con id " + rolId + " no encontrado para remover de usuario");
    }
}
