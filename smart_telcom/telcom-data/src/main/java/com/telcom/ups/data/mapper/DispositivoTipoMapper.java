package com.telcom.ups.data.mapper;

import com.telcom.ups.data.dto.DispositivoTipoDTO;
import com.telcom.ups.data.entities.DispositivoTipo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface DispositivoTipoMapper {

    @Mappings({
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
    })
    DispositivoTipoDTO toDispositivoTipoDto(DispositivoTipo dispositivoTipo);

    List<DispositivoTipoDTO> toDispositivoTipoDtos(List<DispositivoTipo> dispositivoTipos);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    DispositivoTipo toDispositivoTipo(DispositivoTipoDTO dispositivoTipoDTO);
}
