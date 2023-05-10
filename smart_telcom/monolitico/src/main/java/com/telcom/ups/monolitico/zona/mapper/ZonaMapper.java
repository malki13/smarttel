package com.telcom.ups.monolitico.zona.mapper;

import com.telcom.ups.data.dto.ZonaDTO;
import com.telcom.ups.data.entities.Zona;
import com.telcom.ups.data.info.ZonaInfo;
import com.telcom.ups.data.read.ZonaRead;
import com.telcom.ups.monolitico.interventor.mapper.InterventorMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InterventorMapper.class, InterventorMapper.class, InterventorMapper.class})
public interface ZonaMapper {

    /**
     * Proceso de conversión de un Zona a un ZonaRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param zona
     * @return ZonaRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "empresa", target = "empresa"),
            @Mapping(source = "administrador", target = "admin"),
            @Mapping(source = "tecnico", target = "tecnico"),
            @Mapping(source = "descServicio", target = "descServicio")
    })
    ZonaRead toZonaRead(Zona zona);

    /**
     * Proceso de conversión de una lista de Zonas a una lista de ZonaReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param zonas
     * @return lista de ZonaReads
     */
    List<ZonaRead> toZonaReads(List<Zona> zonas);

    /**
     * Proceso de conversión de un Zona a un ZonaInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param zona
     * @return ZonaInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "empresa", target = "empresa"),
            @Mapping(source = "administrador", target = "admin"),
            @Mapping(source = "tecnico", target = "tecnico"),
            @Mapping(source = "descServicio", target = "descServicio")
    })
    ZonaInfo toZonaInfo(Zona zona);

    /**
     * Proceso de conversión de una lista de Zonas a una lista de ZonaInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param zonas
     * @return lsiat de ZonaInfos
     */
    List<ZonaInfo> toZonaInfos(List<Zona> zonas);

    /**
     * Proceso de conversión de un ZonaDTO a un Zona
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param zonaDTO
     * @return Zona
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "administrador", ignore = true),
            @Mapping(target = "tecnico", ignore = true),
            @Mapping(target = "empresa", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Zona toZona(ZonaDTO zonaDTO);
}
