package com.telcom.ups.monolitico.administrador.mapper;

import com.telcom.ups.data.entities.Administrador;
import com.telcom.ups.data.info.AdministradorInfo;
import com.telcom.ups.data.read.AdministradorRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdministradorMapper {

    /**
     * Proceso de conversión de un Administrador a un AdministradorRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param administrador
     * @return AdministradorRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "interventor", target = "interventor"),
            @Mapping(source = "estatus", target = "estatus")
    })
    AdministradorRead toAdministradorRead(Administrador administrador);

    /**
     * Proceso de conversión de una lista de Administradores a una lista de AdministradorReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param administradors
     * @return lista de AdministradorReads
     */
    List<AdministradorRead> toAdministradorReads(List<Administrador> administradors);

    /**
     * Proceso de conversión de un Administrador a un AdministradorInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param administrador
     * @return AdministradorInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "interventor", target = "interventor"),
            @Mapping(source = "estatus", target = "estatus"),
    })
    AdministradorInfo toAdministradorInfo(Administrador administrador);

    /**
     * Proceso de conversión de una lista de Administradores a una lista de AdministradorInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param administradors
     * @return lista de AdministradorInfos
     */
    List<AdministradorInfo> toAdministradorInfos(List<Administrador> administradors);

    /**
     * Proceso de conversión de un AdministradorRead a un Administrador
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param administradorRead
     * @return Administrador
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Administrador toAdministrador(AdministradorRead administradorRead);
}
