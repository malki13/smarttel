package com.telcom.ups.monolitico.configuracion.mapper;

import com.telcom.ups.data.dto.ConfiguracionDTO;
import com.telcom.ups.data.entities.Configuracion;
import com.telcom.ups.data.info.ConfiguracionInfo;
import com.telcom.ups.data.read.ConfiguracionRead;
import com.telcom.ups.monolitico.dispositivotipo.mapper.DispositivoTipoMapper;
import com.telcom.ups.monolitico.parametro.mapper.ParametroMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ParametroMapper.class, DispositivoTipoMapper.class})
public interface ConfiguracionMapper {

    /**
     * Proceso de conversión de una Configuracion a un ConfiguracionRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param configuracion
     * @return ConfiguracionRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "comando", target = "comando"),
            @Mapping(source = "parametros", target = "parametros")
    })
    ConfiguracionRead toConfiguracionRead(Configuracion configuracion);

    /**
     * Proceso de conversión de una lista de Configuraciones a una lista de ConfiguracionReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param configuraciones
     * @return lista de ConfiguracionReads
     */
    List<ConfiguracionRead> toConfiguracionReads(List<Configuracion> configuraciones);

    /**
     * Proceso de conversión de una Configuracion a un ConfiguracionInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param configuracion
     * @return ConfiguracionInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "comando", target = "comando"),
            //@Mapping(source = "dispositivoTipo", target = "dispositivoTipo"),
    })
    ConfiguracionInfo toConfiguracionInfo(Configuracion configuracion);

    /**
     * Proceso de conversión de una lista de Configuraciones a una lista de ConfiguracionInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param configuraciones
     * @return ConfiguracionInfo
     */
    List<ConfiguracionInfo> toConfiguracionInfos(List<Configuracion> configuraciones);

    /**
     * Proceso de conversión de una ConfiguracionDTO a una Configuracion
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param configuracionDTO
     * @return Configuracion
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "dispositivoTipo", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Configuracion toConfiguracion(ConfiguracionDTO configuracionDTO);
}
