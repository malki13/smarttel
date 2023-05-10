package com.telcom.ups.monolitico.parametro.mapper;

import com.telcom.ups.data.dto.ParametroDTO;
import com.telcom.ups.data.entities.Parametro;
import com.telcom.ups.data.read.ParametroRead;
import com.telcom.ups.monolitico.configuracion.mapper.ConfiguracionMapper;
import com.telcom.ups.monolitico.variante.mapper.VarianteMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ConfiguracionMapper.class, VarianteMapper.class})
public interface ParametroMapper {

    /**
     * Proceso de conversión de un Parametro a un ParametroRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param parametro
     * @return ParametroRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "variantes", target = "variantes")
    })
    ParametroRead toParametroRead(Parametro parametro);

    /**
     * Proceso de conversión de una lista de Parametros a una lista de ParametroReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param parametros
     * @return lista de ParametroReads
     */
    List<ParametroRead> toParametroReads(List<Parametro> parametros);

    /**
     * Proceso de conversión de un ParametroDTO a un Parametro
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param parametroDTO
     * @return Parametro
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "configuracion", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Parametro toParametro(ParametroDTO parametroDTO);
}
