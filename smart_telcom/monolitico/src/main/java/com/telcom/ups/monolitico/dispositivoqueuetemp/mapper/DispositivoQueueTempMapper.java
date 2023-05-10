package com.telcom.ups.monolitico.dispositivoqueuetemp.mapper;

import com.telcom.ups.data.entities.DispositivoQueueTemp;
import com.telcom.ups.data.info.DispositivoQueueTempInfo;
import com.telcom.ups.data.read.DispositivoQueueTempRead;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DispositivoQueueTempMapper {

    /**
     * Proceso de conversión de un DispositivoQueueTemp a un DispositivoQueueTempRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoQueueTemp
     * @return DispositivoQueueTempRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "deveui", target = "deveui"),
            @Mapping(source = "payload", target = "payload"),
            @Mapping(source = "fCnt", target = "fCnt"),
            @Mapping(source = "fPort", target = "fPort"),
    })
    DispositivoQueueTempRead toDispositivoQueueTempRead(DispositivoQueueTemp dispositivoQueueTemp);

    /**
     * Proceso de conversión de una lista de DispositivoQueueTemps a una lista de DispositivoQueueTempReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoQueueTemps
     * @return lista de DispositivoQueueTempRead
     */
    List<DispositivoQueueTempRead> toDispositivoQueueTempReads(List<DispositivoQueueTemp> dispositivoQueueTemps);

    /**
     * Proceso de conversión de un DispositivoQueueTemp a un DispositivoQueueTempInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoQueueTemp
     * @return DispositivoQueueTempInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "deveui", target = "deveui"),
            @Mapping(source = "payload", target = "payload"),
            @Mapping(source = "fCnt", target = "fCnt"),
            @Mapping(source = "fPort", target = "fPort"),
            @Mapping(source = "fecha", target = "fecha"),
            @Mapping(source = "cont", target = "cont"),
    })
    DispositivoQueueTempInfo toDispositivoQueueTempInfo(DispositivoQueueTemp dispositivoQueueTemp);

    /**
     * Proceso de conversión de una lista de DispositivoQueueTemps a una lista de DispositivoQueueTempInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoQueueTemps
     * @return lista de DispositivoQueueTempInfo
     */
    List<DispositivoQueueTempInfo> toDispositivoQueueTempInfos(List<DispositivoQueueTemp> dispositivoQueueTemps);
}
