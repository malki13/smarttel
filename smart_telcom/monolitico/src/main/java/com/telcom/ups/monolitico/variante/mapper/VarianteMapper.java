package com.telcom.ups.monolitico.variante.mapper;

import com.telcom.ups.data.dto.VarianteDTO;
import com.telcom.ups.data.entities.Variante;
import com.telcom.ups.data.read.VarianteRead;
import com.telcom.ups.monolitico.parametro.mapper.ParametroMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ParametroMapper.class})
public interface VarianteMapper {

    /**
     * Proceso de conversión de una Variante a una VarianteRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param variante
     * @return VarianteRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "data", target = "data")
    })
    VarianteRead toVarianteRead(Variante variante);

    /**
     * Proceso de conversión de una lista de Variantes a una lista de VarianteReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param variantes
     * @return lista de VarianteReads
     */
    List<VarianteRead> toVarianteReads(List<Variante> variantes);

    /**
     * Proceso de conversión de una VarianteDTO a una Variante
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param varianteDTO
     * @return Variante
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "parametro", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Variante toVariante(VarianteDTO varianteDTO);
}
