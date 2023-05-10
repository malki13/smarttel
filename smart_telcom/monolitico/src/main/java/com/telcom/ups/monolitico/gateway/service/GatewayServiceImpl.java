package com.telcom.ups.monolitico.gateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.telcom.ups.data.dto.GatewayDTO;
import com.telcom.ups.data.entities.Empresa;
import com.telcom.ups.data.entities.Gateway;
import com.telcom.ups.data.info.GatewayInfo;
import com.telcom.ups.data.read.GatewayRead;
import com.telcom.ups.monolitico.empresa.repository.EmpresaCrudRepository;
import com.telcom.ups.monolitico.gateway.mapper.GatewayMapper;
import com.telcom.ups.monolitico.gateway.repository.GatewayCrudRepository;
import com.telcom.ups.monolitico.integracion.repository.IntegracionCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GatewayServiceImpl implements GatewayService {

    @Autowired
    private GatewayCrudRepository gatewayCrudRepository;

    @Autowired
    private EmpresaCrudRepository empresaCrudRepository;

    @Autowired
    private GatewayMapper mapper;

    @Autowired
    private Gson gson;

    @Autowired
    private IntegracionCrudRepository integracionCrudRepository;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Devuelve un Page de GatewayInfos que provee el repositorio CRUD
     * Para devolver un Page de GatewayInfos se realiza el proceso de conversión de objetos con la interfaz GatewayMapper
     *
     * @param pageable
     * @return page de GatewayInfos
     */
    @Override
    public Page<GatewayInfo> findAll(Pageable pageable) {
        return gatewayCrudRepository.findAll(pageable).map(gateway -> mapper.toGatewayInfo(gateway));
    }

    /**
     * Devuelve un Optional de GatewayRead que provee el repositorio CRUD
     * Para devolver un optional de GatewayRead se realiza el proceso de conversión de objetos con la interfaz GatewayMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesta de la petición
     *
     * @param id
     * @return optional de GatewayRead
     */
    @Override
    public Optional<GatewayRead> getOne(Integer id) {
        Optional<Gateway> gatewayDB = gatewayCrudRepository.findById(id);
        if (gatewayDB.isPresent()) {
            return gatewayCrudRepository.findById(id).map(gateway -> mapper.toGatewayRead(gateway));
        }
        throw new BadRequestException("Gateway con id " + id + " no encontrado");
    }

    /**
     * Devuelve un GatewayRead que provee el repositorio CRUD
     * Para devolver un GatewayRead se realiza el proceso de conversión de objetos con la interfaz GatewayMapper
     * Para guardar un gateway previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesta de la petición
     *
     * @param empresaId
     * @param gatewayDTO
     * @return GatewayRead
     */
    @Override
    public GatewayRead save(Integer empresaId, GatewayDTO gatewayDTO) {
        Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
        if (empresaDB.isPresent()) {
            Empresa empresa = empresaDB.get();
            Optional<Gateway> gatewayDB = gatewayCrudRepository.findByIdGateway(gatewayDTO.getIdGateway());
            if (!gatewayDB.isPresent()) {
                Integer numGateways = gatewayCrudRepository.countByEmpresaIden(empresaId);
                if (numGateways < empresa.getNumGateways()) {
                    Gateway gateway = mapper.toGateway(gatewayDTO);
                    gateway.setEmpresa(empresa);
                    return mapper.toGatewayRead(gatewayCrudRepository.save(gateway));
                }
                throw new BadRequestException("La empresa " + empresa.getInterventor().getNombre() + " " + empresa.getInterventor().getApellido()
                        + " puede registrar máximo " + empresa.getNumGateways() + " gateways.");
            }
            throw new BadRequestException("Gateway con id " + gatewayDTO.getIdGateway() + " ya esta registrado");
        }
        throw new BadRequestException("Empresa con id " + empresaId + " no encontrado para relacionar con el gateway");
    }

    /**
     * Devuelve un boolean al borrar la informacion mediante el repositorio CRUD
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesta de la petición
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean delete(Integer id) {
        try {
            Optional<Gateway> gatewayDB = gatewayCrudRepository.findById(id);
            if (gatewayDB.isPresent()) {
                gatewayCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Gateway con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El gateway que intenta eliminar cuenta con objetos relacionados. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso el gateway no debe contar con objetos relacionados al mismo.");
        }
    }

    /**
     * Devuelve un GatewayRead que provee el repositorio CRUD
     * Para devolver un GatewayRead se realiza el proceso de conversión de objetos con la interfaz GatewayMapper
     * Para actualizar un gateway previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesta de la petición
     *
     * @param empresaId
     * @param gatewayDTO
     * @param id
     * @return GatewayRead
     */
    @Override
    public GatewayRead update(Integer empresaId, GatewayDTO gatewayDTO, Integer id) {
        Optional<Gateway> gatewayDB = gatewayCrudRepository.findById(id);
        if (gatewayDB.isPresent()) {
            Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
            if (empresaDB.isPresent()) {
                Gateway newGateway = mapper.toGateway(gatewayDTO);
                Gateway gat = gatewayDB.get();
                gat.setNombre(newGateway.getNombre());
                gat.setDescripcion(newGateway.getDescripcion());
                gat.setIdGateway(newGateway.getIdGateway());
                gat.setIdNetworkServer(newGateway.getIdNetworkServer());
                gat.setIdServiceProfile(newGateway.getIdServiceProfile());
                gat.setIdGatewayProfile(newGateway.getIdGatewayProfile());
                gat.setAltitude(newGateway.getAltitude());
                gat.setLatitude(newGateway.getLatitude());
                gat.setLongitude(newGateway.getLongitude());
                gat.setEmpresa(empresaDB.get());
                gat.setUpdatedAt(LocalDateTime.now());
                return mapper.toGatewayRead(gatewayCrudRepository.save(gat));
            }
            throw new BadRequestException("Empresa con id " + empresaId + " no encontrado para relacionar con el gateway");
        }
        throw new BadRequestException("Gateway con id " + id + " no encontrado para actualizar");
    }

    /**
     * Devuelve un Page de GatewayInfos que provee el repositorio CRUD
     * Para devolver un page de GatewayInfos se realiza el proceso de conversión de objetos con la interfaz GatewayMapper
     *
     * @param nombre
     * @return lista de GatewayInfos
     */
    @Override
    public List<GatewayInfo> searchByNombre(String nombre) {
        return mapper.toGatewayInfos(gatewayCrudRepository.searchByNombre(nombre));
    }

    /**
     * Devuelve un Page de GatewayInfos que provee el repositorio CRUD
     * Para devolver un page de GatewayInfos se realiza el proceso de conversión de objetos con la interfaz GatewayMapper
     *
     * @param id
     * @return lista de GatewayInfos
     */
    @Override
    public List<GatewayInfo> searchById(String id) {
        return mapper.toGatewayInfos(gatewayCrudRepository.searchById(id));
    }

    /**
     * Devuelve un Page de GatewayInfos que provee el repositorio CRUD
     * Para devolver un page de GatewayInfos se realiza el proceso de conversión de objetos con la interfaz GatewayMapper
     *
     * @param idEmpresa
     * @param pageable
     * @return lista de GatewayInfos
     */
    @Override
    public Page<GatewayInfo> getAllByEmpresa(Integer idEmpresa, Pageable pageable) {
        return gatewayCrudRepository.getAllByEmpresa(idEmpresa, pageable).map(gateway -> mapper.toGatewayInfo(gateway));
    }

//    @Override
//    public Page<GatewayInfo> getAllByIntegracion(Integer idIntegracion, Pageable pageable) {
//        return gatewayCrudRepository.getAllByIntegracion(idIntegracion, pageable).map(gateway -> mapper.toGatewayInfo(gateway));
//    }
}
