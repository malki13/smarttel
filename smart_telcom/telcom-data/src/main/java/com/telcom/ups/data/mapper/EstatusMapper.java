package com.telcom.ups.data.mapper;

import com.telcom.ups.data.dto.EstatusDTO;
import com.telcom.ups.data.entities.Estatus;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface EstatusMapper {

    @Mappings({
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "nombre", target = "nombre"),
    })
    EstatusDTO toEstatusDto(Estatus estatus);

    List<EstatusDTO> toEstatussEstatusDtos(List<Estatus> estatuses);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
            //@Mapping(target = "empresas", ignore = true)
    })
    Estatus toEstatus(EstatusDTO estatusDTO);
}
