package com.telcom.ups.monolitico.factura.mapper;

import com.telcom.ups.data.dto.FacturaDTO;
import com.telcom.ups.data.entities.Factura;
import com.telcom.ups.data.info.FacturaInfo;
import com.telcom.ups.data.info.FacturaInfoOnly;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacturaMapper {

//    @Mappings({
//            @Mapping(source = "iden", target = "iden"),
//            @Mapping(source = "fechaEmision", target = "fechaEmision"),
//            @Mapping(source = "descripcion", target = "descripcion"),
//            @Mapping(source = "secuencial", target = "secuencial"),
//            @Mapping(source = "claveacceso", target = "claveacceso"),
//            @Mapping(source = "fechaAutorizacion", target = "fechaAutorizacion"),
//            @Mapping(source = "numReferencia", target = "numReferencia"),
//            @Mapping(target = "cliente", ignore = true),
//            @Mapping(source = "subtotal", target = "subtotal"),
//            @Mapping(source = "iva", target = "iva"),
//            @Mapping(source = "total", target = "total"),
//            @Mapping(source = "detalles", target = "detalles"),
//            @Mapping(source = "pagos",target = "pagos"),
//            @Mapping(source = "anexos", target = "anexos"),
//    })
//    FacturaRead toFacturaRead(Factura factura);
//
//    List<FacturaRead> toFacturaReads(List<Factura> facturas);

    /**
     * Proceso de conversión de una Factura a un FacturaInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param factura
     * @return FacturaInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "fechaEmision", target = "fechaEmision"),
            @Mapping(source = "tarifa", target = "tarifa"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "secuencial", target = "secuencial"),
            @Mapping(source = "claveacceso", target = "claveacceso"),
            @Mapping(source = "deveui", target = "deveui"),
            @Mapping(source = "fechaAutorizacion", target = "fechaAutorizacion"),
            @Mapping(source = "numReferencia", target = "numReferencia"),
            @Mapping(source = "cliente", target = "clienteInfo"),
            @Mapping(source = "subtotal", target = "subtotal"),
            @Mapping(source = "iva", target = "iva"),
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "detalles", target = "detalles"),
            @Mapping(source = "pagos", target = "pagos"),
            @Mapping(source = "anexos", target = "anexos"),
            @Mapping(source = "empresa", target = "empresa"),
    })
    FacturaInfo toFacturaInfo(Factura factura);

    /**
     * Proceso de conversión de una lista de Facturas a una lista de FacturaInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param facturas
     * @return lista de FacturaInfo
     */
    List<FacturaInfo> toFacturaInfos(List<Factura> facturas);

    /**
     * Proceso de conversión de una Factura a un FacturaInfoOnlies
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param factura
     * @return
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "fechaEmision", target = "fechaEmision"),
            @Mapping(source = "tarifa", target = "tarifa"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "secuencial", target = "secuencial"),
            @Mapping(source = "claveacceso", target = "claveacceso"),
            @Mapping(source = "deveui", target = "deveui"),
            @Mapping(source = "fechaAutorizacion", target = "fechaAutorizacion"),
            @Mapping(source = "numReferencia", target = "numReferencia"),
            @Mapping(source = "subtotal", target = "subtotal"),
            @Mapping(source = "iva", target = "iva"),
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "empresa", target = "empresa"),
    })
    FacturaInfoOnly toFacturaInfoOnly(Factura factura);

    /**
     * Proceso de conversión de una lista de Facturas a una lista de FacturaInfoOnlies
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param facturas
     * @return lista de FacturaInfoOnlies
     */
    List<FacturaInfoOnly> toFacturaInfoOnlies(List<Factura> facturas);

    /**
     * Proceso de conversión de una FacturaDTO a un Factura
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param facturaDTO
     * @return Factura
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "iden", ignore = true),
            @Mapping(target = "cliente", ignore = true),
            @Mapping(target = "detalles", ignore = true),
            @Mapping(target = "pagos", ignore = true),
            @Mapping(target = "anexos", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Factura toFactura(FacturaDTO facturaDTO);
}
