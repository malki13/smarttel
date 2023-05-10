package com.telcom.ups.monolitico.cliente.mapper;

import com.telcom.ups.data.dto.ClienteDTO;
import com.telcom.ups.data.entities.Cliente;
import com.telcom.ups.data.info.ClienteInfo;
import com.telcom.ups.data.info.ClienteInfoOnly;
import com.telcom.ups.data.read.ClienteRead;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    /**
     * Proceso de conversión de un Cliente a un ClienteRead
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param cliente
     * @return ClienteRead
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "interventor", target = "interventor"),
            @Mapping(source = "estatus", target = "estatus"),
            @Mapping(source = "empresa", target = "empresa"),
            @Mapping(target = "dispositivos", ignore = true),
    })
    ClienteRead toClienteRead(Cliente cliente);

    /**
     * Proceso de conversión de una lista de Clientes a una lista de ClienteReads
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param clientes
     * @return lista de ClienteReads
     */
    List<ClienteRead> toClienteReads(List<Cliente> clientes);

    /**
     * Proceso de conversión de un Cliente a un ClienteInfo
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param cliente
     * @return ClienteInfo
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "interventor", target = "interventor"),
            @Mapping(source = "estatus", target = "estatus"),
            @Mapping(source = "dispositivos", target = "dispositivos"),
    })
    ClienteInfo toClienteInfo(Cliente cliente);

    /**
     * Proceso de conversión de una lista de Clientes a una lista de ClienteInfos
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param clientes
     * @return lista de ClienteInfos
     */
    List<ClienteInfo> toClienteInfos(List<Cliente> clientes);

    /**
     * Proceso de conversión de un Cliente a un ClienteInfoOnly
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param cliente
     * @return ClienteInfoOnly
     */
    @Mappings({
            @Mapping(source = "iden", target = "iden"),
            @Mapping(source = "interventor", target = "interventor"),
            @Mapping(source = "estatus", target = "estatus"),
    })
    ClienteInfoOnly toClienteInfoOnly(Cliente cliente);

    /**
     * Proceso de conversión de una lista de Clientes a una lista de ClienteInfoOnlies
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param clientes
     * @return lista de ClienteInfoOnlies
     */
    List<ClienteInfoOnly> toClienteInfoOnlies(List<Cliente> clientes);

    /**
     * Proceso de conversión de un ClienteDTO a un Cliente
     * Mappings(mapeo de los atributos del objeto origen hacia el objeto destino, es opcional el mapeo de todos, se pueden ignorar ciertos atributos)
     *
     * @param clienteDTO
     * @return Cliente
     */
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "interventor", ignore = true),
            @Mapping(target = "estatus", ignore = true),
            @Mapping(target = "empresa", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Cliente toCliente(ClienteDTO clienteDTO);
}
