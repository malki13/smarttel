package com.telcom.ups.monolitico.zonaqueue.mapper;

import com.telcom.ups.data.entities.ZonaQueue;
import com.telcom.ups.data.read.ZonaQueueRead;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ZonaQueueMapper {

    /**
     * Proceso de conversión de un ZonaQueue a un ZonaQueueRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param zonaQueue
     * @return ZonaQueueRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "payload", target = "payload"),
            @Mapping(source = "fCnt", target = "fCnt"),
            @Mapping(source = "fPort", target = "fPort"),
            @Mapping(source = "fecha", target = "fecha"),
            @Mapping(source = "zona", target = "zona"),
            @Mapping(source = "usuario", target = "usuario"),
    })
    ZonaQueueRead toZonaQueueRead(ZonaQueue zonaQueue);

    /**
     * Proceso de conversión de una lista de ZonaQueues a una lista de ZonaQueueReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param zonaQueues
     * @return lista de ZonaQueueRead
     */
    List<ZonaQueueRead> toZonaQueueReads(List<ZonaQueue> zonaQueues);
}
