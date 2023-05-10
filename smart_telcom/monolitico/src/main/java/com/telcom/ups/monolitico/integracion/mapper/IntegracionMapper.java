package com.telcom.ups.monolitico.integracion.mapper;

import com.telcom.ups.data.dto.IntegracionDTO;
import com.telcom.ups.data.entities.Integracion;
import com.telcom.ups.data.info.IntegracionInfo;
import com.telcom.ups.data.read.IntegracionRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IntegracionMapper {

    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "activo", target = "activo"),
            @Mapping(source = "servidor", target = "servidor"),
            @Mapping(source = "puerto", target = "puerto"),
            @Mapping(source = "timeout", target = "timeout"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "baseurl", target = "baseurl"),
            @Mapping(source = "appServerUrl", target = "appServerUrl"),
            @Mapping(source = "appServerToken", target = "appServerToken"),
            @Mapping(source = "qos", target = "qos"),
            @Mapping(source = "protocoloTipo", target = "protocoloTipo"),
            @Mapping(source = "credencialTipo", target = "credencialTipo"),
    })
    IntegracionInfo toIntegracionInfo(Integracion integracion);

    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "activo", target = "activo"),
            @Mapping(source = "servidor", target = "servidor"),
            @Mapping(source = "puerto", target = "puerto"),
            @Mapping(source = "timeout", target = "timeout"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "baseurl", target = "baseurl"),
            @Mapping(source = "appServerUrl", target = "appServerUrl"),
            @Mapping(source = "appServerToken", target = "appServerToken"),
            @Mapping(source = "qos", target = "qos"),
            @Mapping(source = "protocoloTipo", target = "protocoloTipo"),
            @Mapping(source = "credencialTipo", target = "credencialTipo"),
            @Mapping(source = "topicos", target = "topicos"),
            @Mapping(source = "requestResponses", target = "urls"),
    })
    IntegracionRead toIntegracionRead(Integracion integracion);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "iden", ignore = true),
            @Mapping(target = "codigo", ignore = true),
            @Mapping(target = "protocoloTipo", ignore = true),
            @Mapping(target = "credencialTipo", ignore = true),
            @Mapping(target = "topicos", ignore = true),
            @Mapping(target = "requestResponses", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Integracion toIntegracion(IntegracionDTO integracionDTO);
}
