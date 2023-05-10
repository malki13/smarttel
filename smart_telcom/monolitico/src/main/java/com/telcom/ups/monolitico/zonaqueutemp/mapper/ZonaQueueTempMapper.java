package com.telcom.ups.monolitico.zonaqueutemp.mapper;

import com.telcom.ups.data.entities.ZonaQueueTemp;
import com.telcom.ups.data.info.ZonaQueueTempInfo;
import com.telcom.ups.data.read.ZonaQueueReadTemp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ZonaQueueTempMapper {

    /**
     * Proceso de conversión de un ZonaQueueTemp a un ZonaQueueReadTemp
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param zonaQueueTemp
     * @return ZonaQueueReadTemp
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "payload", target = "payload"),
            @Mapping(source = "fCnt", target = "fCnt"),
            @Mapping(source = "fPort", target = "fPort"),
            @Mapping(source = "zonaId", target = "zonaId"),
    })
    ZonaQueueReadTemp toZonaQueueReadTemp(ZonaQueueTemp zonaQueueTemp);

    /**
     * Proceso de conversión de una lista de ZonaQueueTemps a una lista de ZonaQueueReadTemps
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param zonaQueueTemps
     * @return lista de ZonaQueueReadTemps
     */
    List<ZonaQueueReadTemp> toZonaQueueReadTemps(List<ZonaQueueTemp> zonaQueueTemps);

    /**
     * Proceso de conversión de un ZonaQueueTemp a un ZonaQueueTempInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param zonaQueueTemp
     * @return ZonaQueueTempInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "deveui", target = "deveui"),
            @Mapping(source = "payload", target = "payload"),
            @Mapping(source = "fCnt", target = "fCnt"),
            @Mapping(source = "fPort", target = "fPort"),
            @Mapping(source = "fecha", target = "fecha"),
            @Mapping(source = "cont", target = "cont"),
            @Mapping(source = "zonaId", target = "zonaId"),
    })
    ZonaQueueTempInfo toZonaQueueTempInfo(ZonaQueueTemp zonaQueueTemp);

    /**
     * Proceso de conversión de una lista de ZonaQueueTemps a una lista de ZonaQueueTempInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param zonaQueueTemps
     * @return lista de ZonaQueueTempInfos
     */
    List<ZonaQueueTempInfo> toZonaQueueTempInfos(List<ZonaQueueTemp> zonaQueueTemps);
}
