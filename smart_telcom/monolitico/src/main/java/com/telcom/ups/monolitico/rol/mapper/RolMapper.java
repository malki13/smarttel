package com.telcom.ups.monolitico.rol.mapper;

import com.telcom.ups.data.dto.RolDTO;
import com.telcom.ups.data.entities.Rol;
import com.telcom.ups.data.read.RolRead;
import com.telcom.ups.monolitico.estatus.mapper.EstatusMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EstatusMapper.class})
public interface RolMapper {

    /**
     * Proceso de conversión de un Rol a un RolRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param rol
     * @return RolRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "estatus", target = "estatus"),
    })
    RolRead toRolRead(Rol rol);

    /**
     * Proceso de conversión de una lista de Rols a una lista de RolReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param rols
     * @return lista de RolReads
     */
    List<RolRead> toRolReads(List<Rol> rols);

    /**
     * Proceso de conversión de un RolDTO a un Rol
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param rolDTO
     * @return Rol
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "estatus", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Rol toRol(RolDTO rolDTO);

    /**
     * Proceso de conversión de una lista de RolDTOs a una lista de Rols
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param rolDTOS
     * @return lista de Rol
     */
    List<Rol> toRols(List<RolDTO> rolDTOS);

}
