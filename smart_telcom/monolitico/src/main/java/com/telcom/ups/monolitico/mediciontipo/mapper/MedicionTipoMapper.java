package com.telcom.ups.monolitico.mediciontipo.mapper;

import com.telcom.ups.data.dto.MedicionTipoDTO;
import com.telcom.ups.data.entities.MedicionTipo;
import com.telcom.ups.data.read.MedicionTipoRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicionTipoMapper {

    @Mappings({
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion")
    })
    MedicionTipoDTO toMedicionTipoDto(MedicionTipo medicionTipo);

    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
    })
    MedicionTipoRead toMedicionTipoRead(MedicionTipo medicionTipo);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    MedicionTipo toMedicionTipo(MedicionTipoDTO medicionTipoDTO);

    List<MedicionTipo> toMedicionTipos(List<MedicionTipoRead> medicionTipoReads);
}
