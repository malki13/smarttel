package com.telcom.ups.monolitico.gateway.mapper;

import com.telcom.ups.data.dto.GatewayDTO;
import com.telcom.ups.data.entities.Gateway;
import com.telcom.ups.data.info.GatewayInfo;
import com.telcom.ups.data.read.GatewayRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GatewayMapper {

    /**
     * Proceso de conversión de un Gateway a un GatewayRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param gateway
     * @return GatewayRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "idGateway", target = "idGateway"),
            @Mapping(source = "idNetworkServer", target = "idNetworkServer"),
            @Mapping(source = "idServiceProfile", target = "idServiceProfile"),
            @Mapping(source = "idGatewayProfile", target = "idGatewayProfile"),
            @Mapping(source = "idOrganization", target = "idOrganization"),
            @Mapping(source = "altitude", target = "altitude"),
            @Mapping(source = "latitude", target = "latitude"),
            @Mapping(source = "longitude", target = "longitude"),
            //@Mapping(target = "empresa", ignore = true),
    })
    GatewayRead toGatewayRead(Gateway gateway);

    /**
     * Proceso de conversión de una lista de Gateways a una lista de GatewayReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param gateways
     * @return lista de GatewayReads
     */
    List<GatewayRead> toGatewayReads(List<Gateway> gateways);

    /**
     * Proceso de conversión de un Gateway a un GatewayInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param gateway
     * @return GatewayInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "idGateway", target = "idGateway"),
            @Mapping(source = "idNetworkServer", target = "idNetworkServer"),
            @Mapping(source = "idServiceProfile", target = "idServiceProfile"),
            @Mapping(source = "idGatewayProfile", target = "idGatewayProfile"),
            @Mapping(source = "idOrganization", target = "idOrganization"),
            @Mapping(source = "altitude", target = "altitude"),
            @Mapping(source = "latitude", target = "latitude"),
            @Mapping(source = "longitude", target = "longitude"),
    })
    GatewayInfo toGatewayInfo(Gateway gateway);

    /**
     * Proceso de conversión de una lista de Gateways a una lista de GatewayInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param gateways
     * @return lista de GatewayInfos
     */
    List<GatewayInfo> toGatewayInfos(List<Gateway> gateways);

    /**
     * Proceso de conversión de un GatewayDTO a un Gateway
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param gatewayDTO
     * @return Gateway
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "empresa", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Gateway toGateway(GatewayDTO gatewayDTO);
}
