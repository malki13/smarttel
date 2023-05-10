package com.telcom.ups.monolitico.unidad.mapper;

import com.telcom.ups.data.dto.UnidadDTO;
import com.telcom.ups.data.entities.Unidad;
import com.telcom.ups.data.read.UnidadRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadMapper {

    /**
     * Proceso de conversión de un Unidad a un UnidadRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param unidad
     * @return UnidadRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
    })
    UnidadRead toUnidadRead(Unidad unidad);

    /**
     * Proceso de conversión de una lista de Unidades a una lista de UnidadReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param unidads
     * @return lista de UnidadReads
     */
    List<UnidadRead> toUnidadReads(List<Unidad> unidads);

    /**
     * Proceso de conversión de un UnidadDTO a un Unidad
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param unidadDTO
     * @return Unidad
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "iden", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Unidad toUnidad(UnidadDTO unidadDTO);
}
