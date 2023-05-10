package com.telcom.ups.data.mapper;

import com.telcom.ups.data.dto.ApplicationDTO;
import com.telcom.ups.data.entities.Application;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = {GatewayMapper.class})
public interface ApplicationMapper {

    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "codigo", target = "codigo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "idServiceProfile", target = "idServiceProfile"),
            @Mapping(source = "idApplication", target = "applicationId"),
    })
    ApplicationDTO toApplicationDto(Application application);

    List<ApplicationDTO> toApplicationDtos(List<Application> applications);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Application toApplication(ApplicationDTO applicationDTO);
}
