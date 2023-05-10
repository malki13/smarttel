package com.telcom.ups.data.mapper;

import com.telcom.ups.data.dto.GatewayDTO;
import com.telcom.ups.data.entities.Gateway;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface GatewayMapper {

    @Mappings({
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "idGateway", target = "idGateway"),
            @Mapping(source = "idNetworkServer", target = "idNetworkServer"),
            @Mapping(source = "idServiceProfile", target = "idServiceProfile"),
            @Mapping(source = "idGatewayProfile", target = "idGatewayProfile"),
            @Mapping(source = "idOrganization", target = "idOrganization"),
            @Mapping(source = "altitude", target = "altitude"),
            @Mapping(source = "latitude", target = "latitude"),
            @Mapping(source = "longitude", target = "longitude")
    })
    GatewayDTO toGatewayDto(Gateway gateway);

    List<GatewayDTO> toGatewayDtos(List<Gateway> gateways);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Gateway toGateway(GatewayDTO gatewayDTO);
}
