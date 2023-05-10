package com.telcom.ups.data.mapper;

import com.telcom.ups.data.dto.DispositivoDTO;
import com.telcom.ups.data.entities.Dispositivo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = {ApplicationMapper.class, DispositivoTipoMapper.class, TarifaMapper.class, ClienteMapper.class})
public interface DeviceMapper {

    @Mappings({
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "deveui", target = "deveui"),
            @Mapping(source = "idDeviceProfile", target =  "idDeviceProfile"),
            @Mapping(source = "idApplication", target = "idApplication"),
            @Mapping(source = "altitude", target = "altitude")
    })
    DispositivoDTO toDispositivoDto(Dispositivo dispositivo);

    List<DispositivoDTO> toDispositivoDtos(List<Dispositivo> dispositivos);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Dispositivo toDispositivo(DispositivoDTO dispositivoDTO);
}
