package com.telcom.ups.data.mapper;

import com.telcom.ups.data.dto.TarifaDTO;
import com.telcom.ups.data.entities.Tarifa;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface TarifaMapper {

    @Mappings({
            @Mapping(source = "nombreCategoria", target = "nombreCategoria"),
            @Mapping(source = "rangoConsumo", target = "rangoConsumo"),
            @Mapping(source = "cargoDisponibilidad", target = "cargoDisponibilidad"),
            @Mapping(source = "cargoVariable", target = "cargoVariable"),
    })
    TarifaDTO toTarifaDto(Tarifa tarifa);

    List<TarifaDTO> toTarifaDtos(List<Tarifa> tarifas);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Tarifa toTarifa(TarifaDTO tarifaDTO);
}
