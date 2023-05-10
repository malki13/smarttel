package com.telcom.ups.monolitico.cliente.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telcom.ups.data.entities.Cliente;
import com.telcom.ups.data.entities.Empresa;
import com.telcom.ups.data.entities.Estatus;
import com.telcom.ups.data.entities.Interventor;
import com.telcom.ups.data.info.ClienteInfo;
import com.telcom.ups.data.info.ClienteInfoOnly;
import com.telcom.ups.data.read.ClienteRead;
import com.telcom.ups.data.read.EmpresaRead;
import com.telcom.ups.monolitico.cliente.mapper.ClienteMapper;
import com.telcom.ups.monolitico.cliente.repository.ClienteCrudRepository;
import com.telcom.ups.monolitico.empresa.mapper.EmpresaMapper;
import com.telcom.ups.monolitico.empresa.repository.EmpresaCrudRepository;
import com.telcom.ups.monolitico.estatus.repository.EstatusCrudRepository;
import com.telcom.ups.monolitico.interventor.repository.InterventorCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private EmpresaCrudRepository empresaCrudRepository;

    @Autowired
    private EstatusCrudRepository estatusCrudRepository;

    @Autowired
    private InterventorCrudRepository interventorCrudRepository;

    @Autowired
    private ClienteMapper mapper;

    @Autowired
    private EmpresaMapper empresaMapper;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Devuelve un Page de ClienteInfoOnlies que provee el repositorio CRUD
     * Para devolver un Page de ClienteInfoOnlies se realiza el proceso de conversión de objetos con la interfaz ClienteMapper
     *
     * @param pageable
     * @return page de ClienteInfoOnlies
     */
    @Override
    public Page<ClienteInfoOnly> findAll(Pageable pageable) {
        return clienteCrudRepository.findAll(pageable).map(cliente -> mapper.toClienteInfoOnly(cliente));
    }

    /**
     * Devuelve un Optional de ClienteInfo que provee el repositorio CRUD
     * Para devolver un optional de ClienteInfo se realiza el proceso de conversión de objetos con la interfaz ClienteMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return optional de ClienteInfo
     */
    @Override
    public Optional<ClienteInfo> getOne(Integer id) {
        Optional<Cliente> clienteDB = clienteCrudRepository.findById(id);
        if (clienteDB.isPresent()) {
            return clienteDB.map(cliente -> mapper.toClienteInfo(cliente));
        }
        throw new BadRequestException("Cliente con id " + id + " no encontrado");
    }

    /**
     * Devuelve un optional de ClienteInfoOnly que provee el repositorio CRUD
     * Para devolver un ClienteInfoOnly se realiza el proceso de conversión de objetos con la interfaz ClienteMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param codigo
     * @return optional de ClienteInfoOnly
     */
    @Override
    public Optional<ClienteInfoOnly> getOneByEmpresaCodigo(String codigo, Integer idEmpresa) {
        Optional<Cliente> clienteDB = clienteCrudRepository.findByInterventorCodigoAndEmpresaIden(codigo, idEmpresa);
        if (clienteDB.isPresent()) {
            return clienteDB.map(cliente -> mapper.toClienteInfoOnly(cliente));
        }
        throw new BadRequestException("Cliente con codigo " + codigo + " no encontrado");
    }

    /**
     * Devuelve una lista de EmpresaReads que provee el CRUD
     * Para devolver el listado empresas previamente se busca los clientes por el codigo de interventor esto con el fin
     * de obtener un listado de clientes y del mismo obtener la empresa a la que pertenece.
     *
     * @param codigo
     * @return lista de EmpresaReads
     */
    @Override
    public List<EmpresaRead> getEmpresasByCodigo(String codigo) {
        List<Cliente> clientes = clienteCrudRepository.findByInterventorCodigo(codigo);
        List<Empresa> empresas = new ArrayList<>();
        for (Cliente cliente : clientes) {
            empresas.add(cliente.getEmpresa());
        }
        return empresaMapper.toEmpresaReads(empresas);
    }

    /**
     * Devuelve un ClienteRead que provee el repositorio CRUD
     * Para devolver un ClienteRead se realiza el proceso de conversión de objetos con la interfaz ClienteMapper
     * Para guardar un cliente previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param interventorId
     * @param estatusId
     * @param empresaId
     * @return ClienteRead
     */
    @Override
    public ClienteRead save(Integer interventorId, Integer estatusId, Integer empresaId) {
        Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
        if (empresaDB.isPresent()) {
            Optional<Estatus> estatusDB = estatusCrudRepository.findById(estatusId);
            if (estatusDB.isPresent()) {
                Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
                if (interventorDB.isPresent()) {
                    Optional<Cliente> clienteDB = clienteCrudRepository.findByInterventorEmpresa(interventorId, empresaId);
                    if (!clienteDB.isPresent()) {
                        Cliente cliente = new Cliente();
                        cliente.setInterventor(interventorDB.get());
                        cliente.setEstatus(estatusDB.get());
                        cliente.setEmpresa(empresaDB.get());
                        return mapper.toClienteRead(clienteCrudRepository.save(cliente));
                    }
                    throw new BadRequestException("Cliente con el interventor " + interventorDB.get().getCodigo() + " para la empresa "
                            + empresaDB.get().getInterventor().getNombre() + empresaDB.get().getInterventor().getApellido() + " ya se encuentra registrado");
                }
                throw new BadRequestException("Interventor con id " + interventorId + " no encontrado para relacionar con el cliente");
            }
            throw new BadRequestException("Estatus con id " + estatusId + " no encontrado para relacionar con el cliente");
        }
        throw new BadRequestException("Empresa con id " + empresaId + " no encontrado para relacionar con el cliente");
    }

    /**
     * Devuelve un boolean al borrar la informacion mediante el repositorio CRUD
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean delete(Integer id) {
        try {
            Optional<Cliente> clienteDB = clienteCrudRepository.findById(id);
            if (clienteDB.isPresent()) {
                clienteCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Cliente con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El Cliente que intenta eliminar cuenta con dispositivos o facturas generadas, previo a esto, para que " +
                    "el proceso de eliminación sea exitoso el cliente no debe contar con dispositivos o facturas generadas.");
        }

    }

    /**
     * Devuelve un ClienteInfo que provee el repositorio CRUD
     * Para devolver un ClienteInfo se realiza el proceso de conversión de objetos con la interfaz ClienteMapper
     * Para actualizar un cliente previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param interventorId
     * @param estatusId
     * @param empresaId
     * @param id
     * @return ClienteInfo
     */
    @Override
    public ClienteInfo update(Integer interventorId, Integer estatusId, Integer empresaId, Integer id) {
        Optional<Cliente> clienteDB = clienteCrudRepository.findById(id);
        if (clienteDB.isPresent()) {
            Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
            if (empresaDB.isPresent()) {
                Optional<Estatus> estatusDB = estatusCrudRepository.findById(estatusId);
                if (estatusDB.isPresent()) {
                    Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
                    if (interventorDB.isPresent()) {
                        Cliente cli = clienteDB.get();
                        cli.setInterventor(interventorDB.get());
                        cli.setEstatus(estatusDB.get());
                        cli.setUpdatedAt(LocalDateTime.now());
                        cli.setEmpresa(empresaDB.get());
                        return mapper.toClienteInfo(clienteCrudRepository.save(cli));
                    }
                    throw new BadRequestException("Interventor con id " + interventorId + " no encontrado para relacionar con el cliente");
                }
                throw new BadRequestException("Estatus con id " + estatusId + " no encontrado para relacionar con el cliente");
            }
            throw new BadRequestException("Empresa con id " + empresaId + " no encontrado para relacionar con el cliente");
        }
        throw new BadRequestException("Cliente con id " + id + " no encontrado para actualizar");
    }

    /**
     * Devuelve un Page de ClienteInfoOnlies que provee el repositorio CRUD
     * Para devolver un page de ClienteInfoOnlies se realiza el proceso de conversión de objetos con la interfaz ClienteMapper
     *
     * @param idEmpresa
     * @param pageable
     * @return page de ClienteInfoOnlies
     */
    @Override
    public Page<ClienteInfoOnly> getAllByEmpresa(Integer idEmpresa, Pageable pageable) {
        return clienteCrudRepository.getAllByEmpresa(idEmpresa, pageable).map(cliente -> mapper.toClienteInfoOnly(cliente));
    }
}
