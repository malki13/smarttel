package com.telcom.ups.monolitico.application.mapper;

import com.telcom.ups.data.dto.ApplicationDTO;
import com.telcom.ups.data.entities.Application;
import com.telcom.ups.data.info.ApplicationInfo;
import com.telcom.ups.data.read.ApplicationRead;
import com.telcom.ups.monolitico.gateway.mapper.GatewayMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GatewayMapper.class})
public interface ApplicationMapper {

    /**
     * Proceso de conversión de un Application a un ApplicationRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param application
     * @return ApplicationRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "idServiceProfile", target = "idServiceProfile"),
            @Mapping(source = "idApplication", target = "applicationId"),
            //@Mapping(source = "perfilServicio", target = "perfilServicio"),
            @Mapping(source = "empresa", target = "empresa"),
            @Mapping(source = "protocoloTipo", target = "protocoloTipo"),
    })
    ApplicationRead toApplicationRead(Application application);

    /**
     * Proceso de conversión de una lista de Applications a una lista de ApplicationReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param applications
     * @return lista de ApplicationReads
     */
    List<ApplicationRead> toApplicationReads(List<Application> applications);

    /**
     * Proceso de conversión de un Application a un ApplicationInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param application
     * @return ApplicationInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "idServiceProfile", target = "idServiceProfile"),
            @Mapping(source = "idApplication", target = "applicationId"),
    })
    ApplicationInfo toApplicationInfo(Application application);

    /**
     * Proceso de conversión de una lista de Applications a una lista de ApplicationInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param applications
     * @return lista de ApplicationInfos
     */
    List<ApplicationInfo> toApplicationInfos(List<Application> applications);

    /**
     * Proceso de conversión de un ApplicationDTO a un Application
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param applicationDTO
     * @return Application
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "applicationId", target = "idApplication"),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Application toApplication(ApplicationDTO applicationDTO);
}
