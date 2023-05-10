package com.telcom.ups.monolitico.anexo.mapper;

import com.telcom.ups.data.dto.AnexoDTO;
import com.telcom.ups.data.entities.Anexo;
import com.telcom.ups.data.read.AnexoRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnexoMapper {

    /**
     * Proceso de conversión de un Anexo a un AnexoRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param anexo
     * @return AnexoRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(target = "factura", ignore = true),
    })
    AnexoRead toAnexoRead(Anexo anexo);

    /**
     * Proceso de conversión de un lista Anexos a una lista de AnexoReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param anexos
     * @return lista de AnexoReads
     */
    List<AnexoRead> toAnexoReads(List<Anexo> anexos);

    /**
     * Proceso de conversión de un AnexoDTO a un Anexo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param anexoDTO
     * @return Anexo
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Anexo toAnexo(AnexoDTO anexoDTO);
}
