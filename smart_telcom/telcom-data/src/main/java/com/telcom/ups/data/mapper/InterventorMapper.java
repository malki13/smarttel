package com.telcom.ups.data.mapper;

import com.telcom.ups.data.dto.InterventorDTO;
import com.telcom.ups.data.entities.Interventor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",uses = {InterventorTipoMapper.class})
public interface InterventorMapper {

    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "referencia", target = "referencia"),
            @Mapping(source = "fechaNacimiento", target = "fechaNacimiento"),
            @Mapping(source = "imagen", target = "imagen"),
    })
    InterventorDTO toInterventorDto(Interventor interventor);

    List<InterventorDTO> toInterventorDtos(List<Interventor> interventors);

    @InheritInverseConfiguration
    @Mappings({
            //@Mapping(target = "interventorTipo", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
            //@Mapping(target = "empresa", ignore = true),
            //@Mapping(target = "usuario", ignore = true),
    })
    Interventor toInterventor(InterventorDTO interventorDTO);
}
