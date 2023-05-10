package com.telcom.ups.monolitico.dispositivoqueue.mapper;

import com.telcom.ups.data.entities.DispositivoQueue;
import com.telcom.ups.data.info.DispositivoQueueInfo;
import com.telcom.ups.data.read.DispositivoQueueRead;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DispositivoQueueMapper {

    /**
     * Proceso de conversión de un DispositivoQueue a un DispositivoQueueRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoQueue
     * @return DispositivoQueueRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "deveui", target = "deveui"),
            @Mapping(source = "payload", target = "payload"),
            @Mapping(source = "fCnt", target = "fCnt"),
            @Mapping(source = "fPort", target = "fPort"),
            @Mapping(source = "fecha", target = "fecha"),
            @Mapping(source = "usuario", target = "usuario"),
    })
    DispositivoQueueRead toDispositivoQueueRead(DispositivoQueue dispositivoQueue);

    /**
     * Proceso de conversión de una lista de DispositivoQueues a una lista de DispositivoQueueReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoQueues
     * @return lista DispositivoQueueRead
     */
    List<DispositivoQueueRead> toDispositivoQueues(List<DispositivoQueue> dispositivoQueues);

    /**
     * Proceso de conversión de un DispositivoQueue a un DispositivoQueueInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoQueue
     * @return DispositivoQueueInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "deveui", target = "deveui"),
            @Mapping(source = "payload", target = "payload"),
            @Mapping(source = "fCnt", target = "fCnt"),
            @Mapping(source = "fPort", target = "fPort"),
            @Mapping(source = "fecha", target = "fecha"),
            @Mapping(source = "usuario", target = "usuario"),
    })
    DispositivoQueueInfo toDispositivoQueueInfo(DispositivoQueue dispositivoQueue);

    /**
     * Proceso de conversión de una lista de DispositivoQueues a una lista de DispositivoQueueInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoQueues
     * @return lista de DispositivoQueueInfo
     */
    List<DispositivoQueueInfo> toDispositivoQueueInfos(List<DispositivoQueue> dispositivoQueues);
}
