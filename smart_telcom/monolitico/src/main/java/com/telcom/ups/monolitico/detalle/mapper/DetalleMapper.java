package com.telcom.ups.monolitico.detalle.mapper;

import com.telcom.ups.data.dto.DetalleDTO;
import com.telcom.ups.data.entities.Detalle;
import com.telcom.ups.data.info.DetalleInfo;
import com.telcom.ups.data.read.DetalleRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface DetalleMapper {

    /**
     * Proceso de conversión de un Detalle a un DetalleRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param detalle
     * @return DetalleRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "cantidad", target = "cantidad"),
            @Mapping(source = "impuesto", target = "impuesto"),
            @Mapping(source = "descuento", target = "descuento"),
            @Mapping(source = "precio", target = "precio"),
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "dispositivo", target = "dispositivo"),
            @Mapping(source = "unidad", target = "unidad"),
    })
    DetalleRead toDetalleRead(Detalle detalle);

    /**
     * Proceso de conversión de una lista de Detalles a una lista de DetalleReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param detalles
     * @return lista de DetalleReads
     */
    List<DetalleRead> toDetalleReads(List<Detalle> detalles);

    /**
     * Proceso de conversión de un Detalle a un DetalleInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param detalle
     * @return DetalleInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "cantidad", target = "cantidad"),
            @Mapping(source = "impuesto", target = "impuesto"),
            @Mapping(source = "descuento", target = "descuento"),
            @Mapping(source = "precio", target = "precio"),
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "unidad", target = "unidad"),
    })
    DetalleInfo toDetalleInfo(Detalle detalle);

    /**
     * Proceso de conversión de una lista de Detalles a una lista de DetalleInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param detalles
     * @return lista de DetalleInfo
     */
    List<DetalleInfo> toDetalleInfos(List<Detalle> detalles);

    /**
     * Proceso de conversión de un DetalleDTO a un Detalle
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param detalleDTO
     * @return Detalle
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Detalle toDetalle(DetalleDTO detalleDTO);
}
