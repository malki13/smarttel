package com.telcom.ups.monolitico.interventor.mapper;

import com.telcom.ups.data.dto.InterventorDTO;
import com.telcom.ups.data.entities.Interventor;
import com.telcom.ups.data.info.InterventorInfo;
import com.telcom.ups.data.read.InterventorRead;
import com.telcom.ups.monolitico.interventortipo.mapper.InterventorTipoMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InterventorTipoMapper.class})
public interface InterventorMapper {

//    @Mappings({
//            @Mapping(source = "iden", target = "iden"),
//            @Mapping(source = "codigo", target = "codigo"),
//            @Mapping(target = "interventorTipo", ignore = true),
//            @Mapping(source = "nombre", target = "nombre"),
//            @Mapping(source = "apellido", target = "apellido"),
//            @Mapping(source = "direccion", target = "direccion"),
//            @Mapping(source = "email", target = "email"),
//            @Mapping(source = "telefono", target = "telefono"),
//            @Mapping(source = "referencia", target = "referencia"),
//            @Mapping(source = "fechaNacimiento", target = "fechaNacimiento"),
//            @Mapping(source = "nombreImagen", target = "nombreImagen"),
//            @Mapping(source = "imagen", target = "imagen"),
//            @Mapping(target = "base64", ignore = true),
//    })
//    InterventorDTO toInterventorDto(Interventor interventor);
//
//    List<InterventorDTO> toInterventorDtos(List<Interventor> interventors);

    /**
     * Proceso de conversión de un Interventor a un InterventorRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param interventor
     * @return InterventorRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "interventorTipo", target = "interventorTipo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "referencia", target = "referencia"),
            @Mapping(source = "fechaNacimiento", target = "fechaNacimiento"),
            @Mapping(source = "nombreImagen", target = "nombreImagen"),
            @Mapping(source = "imagen", target = "imagen")
    })
    InterventorRead toInterventorRead(Interventor interventor);

    /**
     * Proceso de conversión de una lista de Interventores a una lista de InterventorReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param interventors
     * @return lista de InterventorRead
     */
    List<InterventorRead> toInterventorReads(List<Interventor> interventors);

    /**
     * Proceso de conversión de un Interventor a un InterventorInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param interventor
     * @return InterventorInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "interventorTipo", target = "interventorTipo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "referencia", target = "referencia"),
            @Mapping(source = "fechaNacimiento", target = "fechaNacimiento"),
            @Mapping(source = "imagen", target = "imagen"),
    })
    InterventorInfo toInterventorInfo(Interventor interventor);

    /**
     * Proceso de conversión de una lista de Interventores a una lista de InterventorInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param interventors
     * @return lista de InterventorInfos
     */
    List<InterventorInfo> toInterventorInfos(List<Interventor> interventors);

    /**
     * Proceso de conversión de un InterventorDTO a un Interventor
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param interventorDTO
     * @return Interventor
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "interventorTipo", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
            //@Mapping(target = "empresa", ignore = true),
            //@Mapping(target = "usuario", ignore = true),
    })
    Interventor toInterventor(InterventorDTO interventorDTO);

}
