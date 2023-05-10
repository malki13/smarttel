package com.telcom.ups.monolitico.topico.mapper;

import com.telcom.ups.data.dto.TopicoDTO;
import com.telcom.ups.data.entities.Topico;
import com.telcom.ups.data.read.TopicoRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TopicoMapper {

    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "topico", target = "topico")
    })
    TopicoRead topicoRead(Topico topico);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Topico toTopico(TopicoDTO topicoDTO);
}
