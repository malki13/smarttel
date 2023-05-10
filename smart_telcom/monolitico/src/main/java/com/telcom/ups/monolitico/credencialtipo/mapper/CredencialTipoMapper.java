package com.telcom.ups.monolitico.credencialtipo.mapper;

import com.telcom.ups.data.dto.CredencialTipoDTO;
import com.telcom.ups.data.entities.CredencialTipo;
import com.telcom.ups.data.read.CredencialTipoRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CredencialTipoMapper {

    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion")
    })
    CredencialTipoRead toCredencialTipoRead(CredencialTipo credencialTipo);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    CredencialTipo toCredencialTipo(CredencialTipoDTO credencialTipoDTO);
}
