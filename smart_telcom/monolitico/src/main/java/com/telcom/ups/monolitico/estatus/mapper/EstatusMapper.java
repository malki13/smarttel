package com.telcom.ups.monolitico.estatus.mapper;

import com.telcom.ups.data.dto.EstatusDTO;
import com.telcom.ups.data.entities.Estatus;
import com.telcom.ups.data.read.EstatusRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstatusMapper {

    /**
     * Proceso de conversión de un Estatus a un EstatusRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param estatus
     * @return EstatusRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "nombre", target = "nombre"),
    })
    EstatusRead toEstatusRead(Estatus estatus);

    /**
     * Proceso de conversión de una lista de Estatuss a una lista de EstatusReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param estatuses
     * @return lista de EstatusReads
     */
    List<EstatusRead> toEstatusReads(List<Estatus> estatuses);

    /**
     * Proceso de conversión de un EstatusDTO a un Estatus
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param estatusDTO
     * @return Estatus
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Estatus toEstatus(EstatusDTO estatusDTO);
}
