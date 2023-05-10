package com.telcom.ups.monolitico.dispositivo.mapper;

import com.telcom.ups.data.dto.DispositivoDTO;
import com.telcom.ups.data.entities.Dispositivo;
import com.telcom.ups.data.info.DispositivoInfo;
import com.telcom.ups.data.info.DispositivoInfoFactura;
import com.telcom.ups.data.info.DispositivoInfoOnly;
import com.telcom.ups.data.read.DispositivoRead;
import com.telcom.ups.monolitico.application.mapper.ApplicationMapper;
import com.telcom.ups.monolitico.cliente.mapper.ClienteMapper;
import com.telcom.ups.monolitico.dispositivotipo.mapper.DispositivoTipoMapper;
import com.telcom.ups.monolitico.tarifa.mapper.TarifaMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ApplicationMapper.class, DispositivoTipoMapper.class, TarifaMapper.class, ClienteMapper.class})
public interface DeviceMapper {

    /**
     * Proceso de conversión de un Dispositivo a un DispositivoRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivo
     * @return DispositivoRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "deveui", target = "deveui"),
            @Mapping(source = "deveuiEmpresa", target = "deveuiEmpresa"),
            @Mapping(source = "idDeviceProfile", target = "idDeviceProfile"),
            @Mapping(source = "idApplication", target = "idApplication"),
            @Mapping(source = "altitude", target = "altitude"),
            @Mapping(source = "latitude", target = "latitude"),
            @Mapping(source = "longitude", target = "longitude"),
            @Mapping(source = "dispositivoTipo", target = "dispositivoTipo"),
            @Mapping(source = "application", target = "application"),
            @Mapping(source = "tarifa", target = "tarifa"),
            @Mapping(target = "cliente", ignore = true),
    })
    DispositivoRead toDispositivoRead(Dispositivo dispositivo);

    /**
     * Proceso de conversión de una lista de Dispositivos a una lista de DispositivoReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivos
     * @return lista de DispositivoReads
     */
    List<DispositivoRead> toDispositivoReads(List<Dispositivo> dispositivos);

    /**
     * Proceso de conversión de un Dispositivo a un DispositivoInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivo
     * @return DispositivoInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "deveui", target = "deveui"),
            @Mapping(source = "deveuiEmpresa", target = "deveuiEmpresa"),
            @Mapping(source = "idDeviceProfile", target = "idDeviceProfile"),
            @Mapping(source = "idApplication", target = "idApplication"),
            @Mapping(source = "altitude", target = "altitude"),
            @Mapping(source = "latitude", target = "latitude"),
            @Mapping(source = "longitude", target = "longitude"),
            @Mapping(source = "topico", target = "topico"),
            @Mapping(source = "url", target = "url"),
            @Mapping(source = "dispositivoTipo", target = "dispositivoTipo"),
            @Mapping(source = "application", target = "application"),
            @Mapping(source = "tarifa", target = "tarifa"),
            @Mapping(source = "cliente", target = "cliente"),
            @Mapping(source = "detalleComunicacion", target = "detalleComunicacion"),
            @Mapping(source = "reporteComunicacion", target = "reporteComunicacion"),
    })
    DispositivoInfo toDispositivoInfo(Dispositivo dispositivo);

    /**
     * Proceso de conversión de una lista de Dispositivos a una lista de DispositivoInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivos
     * @return lista de DispositivoInfo
     */
    List<DispositivoInfo> toDispositivoInfos(List<Dispositivo> dispositivos);

    /**
     * Proceso de conversión de un Dispositivo a un DispositivoInfoOnly
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivo
     * @return DispositivoInfoOnly
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "deveui", target = "deveui"),
            @Mapping(source = "deveuiEmpresa", target = "deveuiEmpresa"),
            @Mapping(source = "idDeviceProfile", target = "idDeviceProfile"),
            @Mapping(source = "idApplication", target = "idApplication"),
            @Mapping(source = "altitude", target = "altitude"),
            @Mapping(source = "latitude", target = "latitude"),
            @Mapping(source = "longitude", target = "longitude"),
            @Mapping(source = "topico", target = "topico"),
            @Mapping(source = "url", target = "url"),
            @Mapping(source = "detalleComunicacion", target = "detalleComunicacion"),
            @Mapping(source = "reporteComunicacion", target = "reporteComunicacion"),
//            @Mapping(source = "perfilDispositivo", target = "perfilDispositivoInfo"),
//            @Mapping(source = "empresa", target = "empresa"),
//            @Mapping(source = "medicionTipos", target = "medicionTipos"),
    })
    DispositivoInfoOnly toDispositivoInfoOnly(Dispositivo dispositivo);

    /**
     * Proceso de conversión de una lista de Dispositivos a una lista de DispositivoInfoOnlies
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivos
     * @return lista de DispositivoInfoOnlies
     */
    List<DispositivoInfoOnly> toDispositivoInfoOnlies(List<Dispositivo> dispositivos);

    /**
     * Proceso de conversión de un Dispositivo a un DispositivoInfoFactura
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivo
     * @return DispositivoInfoFactura
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "deveui", target = "deveui"),
            @Mapping(source = "deveuiEmpresa", target = "deveuiEmpresa"),
            @Mapping(source = "idDeviceProfile", target = "idDeviceProfile"),
            @Mapping(source = "idApplication", target = "idApplication"),
            @Mapping(source = "altitude", target = "altitude"),
            @Mapping(source = "latitude", target = "latitude"),
            @Mapping(source = "longitude", target = "longitude"),
    })
    DispositivoInfoFactura toDispositivoInfoFactura(Dispositivo dispositivo);

    /**
     * Proceso de conversión de un DispositivoDTO a un Dispositivo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param dispositivoDTO
     * @return Dispositivo
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "dispositivoTipo", source = "dispositivoTipoId", ignore = true),
            @Mapping(target = "application", source = "applicationId", ignore = true),
            @Mapping(target = "tarifa", source = "tarifaId", ignore = true),
            @Mapping(target = "cliente", source = "clienteId", ignore = true),
            @Mapping(target = "zona", source = "zonaId", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Dispositivo toDispositivo(DispositivoDTO dispositivoDTO);
}
