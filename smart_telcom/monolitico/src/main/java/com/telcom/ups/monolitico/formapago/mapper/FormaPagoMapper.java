package com.telcom.ups.monolitico.formapago.mapper;

import com.telcom.ups.data.dto.FormaPagoDTO;
import com.telcom.ups.data.entities.FormaPago;
import com.telcom.ups.data.read.FormaPagoRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormaPagoMapper {

    /**
     * Proceso de conversión de un FormaPago a un FormaPagoRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param formaPago
     * @return FormaPagoRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
    })
    FormaPagoRead toFormaPagoRead(FormaPago formaPago);

    /**
     * Proceso de conversión de una lista de FormaPagos a una lista de FormaPagoReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param formaPagos
     * @return lista de FormaPagoRead
     */
    List<FormaPagoRead> toFormaPagoReads(List<FormaPago> formaPagos);

    /**
     * Proceso de conversión de un FormaPagoDTO a un FormaPago
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param formaPagoDTO
     * @return
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "iden", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    FormaPago toFormaPago(FormaPagoDTO formaPagoDTO);
}
