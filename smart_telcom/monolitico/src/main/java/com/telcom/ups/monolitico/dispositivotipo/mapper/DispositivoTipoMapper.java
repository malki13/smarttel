package com.telcom.ups.monolitico.dispositivotipo.mapper;

import com.telcom.ups.data.dto.DispositivoTipoDTO;
import com.telcom.ups.data.entities.DispositivoTipo;
import com.telcom.ups.data.info.DispositivoTipoInfo;
import com.telcom.ups.data.info.DispositivoTipoInfoOnly;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DispositivoTipoMapper {

    /**
     * Proceso de conversión de un DispositivoTipo a un DispositivoTipoDTO
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoTipo
     * @return DispositivoTipoDTO
     */
    @Mappings({
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion")
    })
    DispositivoTipoDTO toDispositivoTipoDto(DispositivoTipo dispositivoTipo);

    /**
     * Proceso de conversión de una lista de DispositivoTipos a una lista de DispositivoTipoDTOs
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoTipos
     * @return lista de DispositivoTipoDTOs
     */
    List<DispositivoTipoDTO> toDispositivoTipoDtos(List<DispositivoTipo> dispositivoTipos);

    /**
     * Proceso de conversión de un DispositivoTipo a un DispositivoTipoInfoOnly
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoTipo
     * @return DispositivoTipoInfoOnly
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
    })
    DispositivoTipoInfoOnly toDispositivoTipoInfoOnly(DispositivoTipo dispositivoTipo);

    /**
     * Proceso de conversión de una lista de DispositivoTipos a una lista de DispositivoTipoInfoOnlies
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoTipos
     * @return
     */
    List<DispositivoTipoInfoOnly> toDispositivoTipoInfoOnlies(List<DispositivoTipo> dispositivoTipos);


    /**
     * Proceso de conversión de un DispositivoTipo a un DispositivoTipoInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoTipo
     * @return DispositivoTipoInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "configuracions", target = "configuraciones"),
            @Mapping(source = "cadenas", target = "cadenas"),
    })
    DispositivoTipoInfo toDispositivoTipoInfo(DispositivoTipo dispositivoTipo);

    /**
     * Proceso de conversión de una lista de DispositivoTipos a una lista de DispositivoTipoInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoTipos
     * @return
     */
    List<DispositivoTipoInfo> toDispositivoTipoInfos(List<DispositivoTipo> dispositivoTipos);

    /**
     * Proceso de conversión de un DispositivoTipoDTO a un DispositivoTipo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoTipoDTO
     * @return DispositivoTipo
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    DispositivoTipo toDispositivoTipo(DispositivoTipoDTO dispositivoTipoDTO);
}
