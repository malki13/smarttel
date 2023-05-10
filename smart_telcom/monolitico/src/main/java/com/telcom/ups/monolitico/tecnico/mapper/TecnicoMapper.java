package com.telcom.ups.monolitico.tecnico.mapper;

import com.telcom.ups.data.entities.Tecnico;
import com.telcom.ups.data.info.TecnicoInfo;
import com.telcom.ups.data.read.TecnicoRead;
import com.telcom.ups.monolitico.interventor.mapper.InterventorMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InterventorMapper.class})
public interface TecnicoMapper {

    /**
     * Proceso de conversión de un Tecnico a un TecnicoRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param tecnico
     * @return TecnicoRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "interventor", target = "interventor"),
            @Mapping(source = "estatus", target = "estatus")
    })
    TecnicoRead toTecnicoRead(Tecnico tecnico);

    /**
     * Proceso de conversión de una lista de Tecnicos a una lista de TecnicoReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param tecnicos
     * @return lista de TecnicoReads
     */
    List<TecnicoRead> toTecnicoReads(List<Tecnico> tecnicos);

    /**
     * Proceso de conversión de un Tecnico a un TecnicoInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param tecnico
     * @return TecnicoInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "interventor", target = "interventor"),
            @Mapping(source = "estatus", target = "estatus"),
    })
    TecnicoInfo toTecnicoInfo(Tecnico tecnico);

    /**
     * Proceso de conversión de una lista de Tecnicos a una lista de TecnicoInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param tecnicos
     * @return lista de TecnicoInfos
     */
    List<TecnicoInfo> toTecnicoInfos(List<Tecnico> tecnicos);

//    @InheritInverseConfiguration
//    @Mappings({
//            @Mapping(target = "createdAt", ignore = true),
//            @Mapping(target = "updatedAt", ignore = true),
//            @Mapping(target = "deletedAt", ignore = true),
//    })
//    Tecnico toTecnico(TecnicoDTO tecnicoDTO);
}
