package com.telcom.ups.monolitico.configuracioncadena.mapper;

import com.telcom.ups.data.dto.ConfiguracionCadenaDTO;
import com.telcom.ups.data.entities.ConfiguracionCadena;
import com.telcom.ups.data.info.ConfiguracionCadenaInfo;
import com.telcom.ups.data.read.ConfiguracionCadenaRead;
import com.telcom.ups.monolitico.dispositivotipo.mapper.DispositivoTipoMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DispositivoTipoMapper.class})
public interface ConfiguracionCadenaMapper {

    /**
     * Proceso de conversión de una ConfiguracionCadena a un ConfiguracionCadenaRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param configuracionCadena
     * @return ConfiguracionCadenaRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "data", target = "data"),
            @Mapping(source = "hexadecimal", target = "hexadecimal"),
            @Mapping(source = "base64", target = "base64"),
            @Mapping(target = "dispositivoTipo", ignore = true),
    })
    ConfiguracionCadenaRead toConfiguracionCadenaRead(ConfiguracionCadena configuracionCadena);

    /**
     * Proceso de conversión de una lista de ConfiguracionCadenas a una lista de ConfiguracionCadenaReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param configuracionCadenas
     * @return lista de ConfiguracionCadenaReads
     */
    List<ConfiguracionCadenaRead> toConfiguracionCadenaReads(List<ConfiguracionCadena> configuracionCadenas);

    /**
     * Proceso de conversión de una ConfiguracionCadena a un ConfiguracionCadenaInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param configuracionCadena
     * @return ConfiguracionCadenaInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "data", target = "data"),
            @Mapping(source = "hexadecimal", target = "hexadecimal"),
            @Mapping(source = "base64", target = "base64"),
    })
    ConfiguracionCadenaInfo toConfiguracionCadenaInfo(ConfiguracionCadena configuracionCadena);

    /**
     * Proceso de conversión de una lista de ConfiguracionCadenas a una lista de ConfiguracionCadenaInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param configuracionCadenas
     * @return lista de ConfiguracionCadenaInfos
     */
    List<ConfiguracionCadenaInfo> toConfiguracionCadenaInfos(List<ConfiguracionCadena> configuracionCadenas);

    /**
     * Proceso de conversión de una ConfiguracionCadenaDTO a un ConfiguracionCadena
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param configuracionCadenaDTO
     * @return ConfiguracionCadena
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "dispositivoTipo", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    ConfiguracionCadena toConfiguracionCadena(ConfiguracionCadenaDTO configuracionCadenaDTO);
}
