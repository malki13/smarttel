package com.telcom.ups.data.mapper;

import com.telcom.ups.data.dto.ClienteDTO;
import com.telcom.ups.data.entities.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = {InterventorMapper.class, EstatusMapper.class, DeviceMapper.class})
public interface ClienteMapper {

    @Mappings({
            @Mapping(source = "dispositivos", target = "dispositivos"),
    })
    ClienteDTO toClienteDto(Cliente cliente);

    List<ClienteDTO> toClienteDtos(List<Cliente> clientes);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
    })
    Cliente toCliente(ClienteDTO clienteDTO);
}
