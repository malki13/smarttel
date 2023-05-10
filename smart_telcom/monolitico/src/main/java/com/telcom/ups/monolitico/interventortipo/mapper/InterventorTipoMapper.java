package com.telcom.ups.monolitico.interventortipo.mapper;

import com.telcom.ups.data.dto.InterventorTipoDTO;
import com.telcom.ups.data.entities.InterventorTipo;
import com.telcom.ups.data.read.InterventorTipoRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InterventorTipoMapper {

    /**
     * Proceso de conversión de un InterventorTipo a un InterventorTipoRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param interventorTipo
     * @return InterventorTipoRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "idclientesri", target = "idclientesri"),
            @Mapping(source = "idproveedorsri", target = "idproveedorsri"),
            @Mapping(source = "idtarcredito", target = "idtarcredito"),
    })
    InterventorTipoRead toInterventorTipoRead(InterventorTipo interventorTipo);

    /**
     * Proceso de conversión de una lista de InterventorTipos a una lista de InterventorTipoReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param interventorTipos
     * @return listas de InterventorTipoReads
     */
    List<InterventorTipoRead> toInterventorTipoReads(List<InterventorTipo> interventorTipos);

    /**
     * Proceso de conversión de un InterventorTipoDTO a un InterventorTipo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param interventorTipoDTO
     * @return InterventorTipo
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    InterventorTipo toInterventorTipo(InterventorTipoDTO interventorTipoDTO);
}
