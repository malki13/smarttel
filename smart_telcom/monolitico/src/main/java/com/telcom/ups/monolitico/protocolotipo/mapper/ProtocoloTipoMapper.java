package com.telcom.ups.monolitico.protocolotipo.mapper;

import com.telcom.ups.data.dto.ProtocoloTipoDTO;
import com.telcom.ups.data.entities.ProtocoloTipo;
import com.telcom.ups.data.read.ProtocoloTipoRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProtocoloTipoMapper {


    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion")
    })
    ProtocoloTipoRead toProtocoloTipoRead(ProtocoloTipo protocoloTipo);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    ProtocoloTipo toProtocoloTipo(ProtocoloTipoDTO protocoloTipoDTO);
}
