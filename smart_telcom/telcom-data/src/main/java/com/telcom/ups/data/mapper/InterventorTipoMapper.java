package com.telcom.ups.data.mapper;

import com.telcom.ups.data.dto.InterventorTipoDTO;
import com.telcom.ups.data.entities.InterventorTipo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface InterventorTipoMapper {

    @Mappings({
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "idclientesri", target = "idclientesri"),
            @Mapping(source = "idproveedorsri", target = "idproveedorsri"),
            @Mapping(source = "idtarcredito", target = "idtarcredito"),
    })
    InterventorTipoDTO toEstaInterventorTipoDto(InterventorTipo interventorTipo);

    List<InterventorTipoDTO> toInterventorTipoDtos(List<InterventorTipo> interventorTipos);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    InterventorTipo toInterventorTipo(InterventorTipoDTO interventorTipoDTO);
}
