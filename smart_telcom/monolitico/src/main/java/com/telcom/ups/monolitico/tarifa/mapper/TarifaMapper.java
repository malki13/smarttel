package com.telcom.ups.monolitico.tarifa.mapper;

import com.telcom.ups.data.dto.TarifaDTO;
import com.telcom.ups.data.entities.Tarifa;
import com.telcom.ups.data.info.TarifaInfo;
import com.telcom.ups.data.read.TarifaRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarifaMapper {

    /**
     * Proceso de conversión de un Tarifa a un TarifaRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param tarifa
     * @return TarifaRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombreCategoria", target = "nombreCategoria"),
            @Mapping(source = "rangoConsumo", target = "rangoConsumo"),
            @Mapping(source = "cargoDisponibilidad", target = "cargoDisponibilidad"),
            @Mapping(source = "cargoVariable", target = "cargoVariable"),
            @Mapping(source = "empresa", target = "empresa"),
    })
    TarifaRead toTarifaRead(Tarifa tarifa);

    /**
     * Proceso de conversión de una lista de Tarifas a una lista de TarifaReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param tarifas
     * @return lista de TarifaRead
     */
    List<TarifaRead> toTarifaReads(List<Tarifa> tarifas);

    /**
     * Proceso de conversión de un Tarifa a un TarifaInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param tarifa
     * @return TarifaInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombreCategoria", target = "nombreCategoria"),
            @Mapping(source = "rangoConsumo", target = "rangoConsumo"),
            @Mapping(source = "cargoDisponibilidad", target = "cargoDisponibilidad"),
            @Mapping(source = "cargoVariable", target = "cargoVariable"),
    })
    TarifaInfo toTarifaInfo(Tarifa tarifa);

    /**
     * Proceso de conversión de una lista de Tarifas a una lista de TarifaInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param tarifas
     * @return lista de TarifaInfos
     */
    List<TarifaInfo> toTarifaInfos(List<Tarifa> tarifas);

    /**
     * Proceso de conversión de un TarifaDTO a un Tarifa
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param tarifaDTO
     * @return Tarifa
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "empresa", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Tarifa toTarifa(TarifaDTO tarifaDTO);
}
