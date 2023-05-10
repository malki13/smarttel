package com.telcom.ups.monolitico.resquestresponse.mapper;

import com.telcom.ups.data.dto.RequestResponseDTO;
import com.telcom.ups.data.entities.RequestResponse;
import com.telcom.ups.data.read.RequestResponseRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ResquestResponseMapper {

    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "url", target = "url")
    })
    RequestResponseRead toRequestResponseRead(RequestResponse requestResponse);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    RequestResponse toRequestResponse(RequestResponseDTO requestResponseDTO);
}
