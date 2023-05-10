package com.telcom.ups.monolitico.empresa.mapper;

import com.telcom.ups.data.dto.EmpresaDTO;
import com.telcom.ups.data.entities.Empresa;
import com.telcom.ups.data.read.EmpresaRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {

    /**
     * Proceso de conversión de una Empresa a una EmpresaRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param empresa
     * @return EmpresaRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "idorganizationas", target = "idorganizationas"),
            @Mapping(source = "numGateways", target = "numGateways"),
            @Mapping(source = "numDevices", target = "numDevices"),
            @Mapping(source = "interventor", target = "interventor")
    })
    EmpresaRead toEmpresaRead(Empresa empresa);

    /**
     * Proceso de conversión de una lista de Empresas a una lista de EmpresaReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param empresas
     * @return lista de EmpresaReads
     */
    List<EmpresaRead> toEmpresaReads(List<Empresa> empresas);

    /**
     * Proceso de conversión de una EmpresaDTO a una Empresa
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param empresaDTO
     * @return Empresa
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "interventor", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Empresa toEmpresa(EmpresaDTO empresaDTO);
}
