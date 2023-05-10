package com.telcom.ups.monolitico.usuario.mapper;

import com.telcom.ups.data.dto.UsuarioDTO;
import com.telcom.ups.data.entities.Usuario;
import com.telcom.ups.data.info.UsuarioInfo;
import com.telcom.ups.data.read.UsuarioRead;
import com.telcom.ups.monolitico.empresa.mapper.EmpresaMapper;
import com.telcom.ups.monolitico.estatus.mapper.EstatusMapper;
import com.telcom.ups.monolitico.interventor.mapper.InterventorMapper;
import com.telcom.ups.monolitico.rol.mapper.RolMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EstatusMapper.class, InterventorMapper.class, RolMapper.class, EmpresaMapper.class})
public interface UsuarioMapper {

    /**
     * Proceso de conversión de un Usuario a un UsuarioRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param usuario
     * @return UsuarioRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "estatus", target = "estatus"),
            @Mapping(source = "interventor", target = "interventor"),
            @Mapping(source = "rols", target = "roles"),
            @Mapping(source = "empresa", target = "empresa"),
    })
    UsuarioRead toUsuarioRead(Usuario usuario);

    /**
     * Proceso de conversión de una lista de Usuarios a una lista de UsuarioReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param usuarios
     * @return lista de UsuarioRead
     */
    List<UsuarioRead> toUsuarioReads(List<Usuario> usuarios);

    /**
     * Proceso de conversión de un Usuario a un UsuarioInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param usuario
     * @return UsuarioInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "estatus", target = "estatus"),
            @Mapping(source = "interventor", target = "interventor"),
    })
    UsuarioInfo toUsuarioInfo(Usuario usuario);

    /**
     * Proceso de conversión de una lista de Usuarios a una lista de UsuarioInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param usuarios
     * @return lista de UsuarioInfos
     */
    List<UsuarioInfo> toUsuarioInfos(List<Usuario> usuarios);

    /**
     * Proceso de conversión de un UsuarioDTO a un Usuario
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param usuarioDTO
     * @return Usuario
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "roles", target = "rols"),
            @Mapping(target = "estatus", ignore = true),
            @Mapping(target = "interventor", ignore = true),
            @Mapping(target = "empresa", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Usuario toUsuario(UsuarioDTO usuarioDTO);
}
