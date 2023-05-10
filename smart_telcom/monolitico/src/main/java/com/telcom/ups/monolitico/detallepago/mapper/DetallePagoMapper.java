package com.telcom.ups.monolitico.detallepago.mapper;

import com.telcom.ups.data.dto.DetallePagoDTO;
import com.telcom.ups.data.entities.DetallePago;
import com.telcom.ups.data.read.DetallePagoRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetallePagoMapper {

    /**
     * Proceso de conversión de un DetallePago a un DetallePagoRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param detallePago
     * @return DetallePagoRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "formaPago", target = "formaPago"),
            @Mapping(source = "valor", target = "valor"),
            @Mapping(source = "tiempo", target = "tiempo"),
            @Mapping(source = "plazo", target = "plazo")
    })
    DetallePagoRead toDetallePagoRead(DetallePago detallePago);

    /**
     * Proceso de conversión de una lista de DetallePagos a una lista de DetallePagoReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param detallePagos
     * @return lista de DetallePagoRead
     */
    List<DetallePagoRead> toDetallePagoReads(List<DetallePago> detallePagos);

    /**
     * Proceso de conversión de un DetallePagoDTO a un DetallePago
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param detallePagoDTO
     * @return
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    DetallePago toDetallePago(DetallePagoDTO detallePagoDTO);
}
