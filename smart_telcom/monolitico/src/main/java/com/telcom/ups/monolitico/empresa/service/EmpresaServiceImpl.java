package com.telcom.ups.monolitico.empresa.service;

import com.telcom.ups.data.dto.EmpresaDTO;
import com.telcom.ups.data.entities.Empresa;
import com.telcom.ups.data.entities.Interventor;
import com.telcom.ups.data.read.EmpresaRead;
import com.telcom.ups.monolitico.empresa.mapper.EmpresaMapper;
import com.telcom.ups.monolitico.empresa.repository.EmpresaCrudRepository;
import com.telcom.ups.monolitico.interventor.repository.InterventorCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {


    @Autowired
    private EmpresaCrudRepository empresaCrudRepository;

    @Autowired
    private InterventorCrudRepository interventorCrudRepository;

    @Autowired
    private EmpresaMapper empresaMapper;

    /**
     * Devuelve un Page de EmpresaReads que provee el repositorio
     * Para devolver un Page de EmpresaReads se realiza el proceso de conversión de objetos con la interfaz EmpresaMapper
     *
     * @param pageable
     * @return page de EmpresaReads
     */
    @Override
    public Page<EmpresaRead> findAll(Pageable pageable) {
        return empresaCrudRepository.findAll(pageable).map(empresa -> empresaMapper.toEmpresaRead(empresa));
    }

    /**
     * Devuelve un Optional de EmpresaRead que provee el repositorio
     * Para devolver un optional de EmpresaRead se realiza el proceso de conversión de objetos con la interfaz EmpresaMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param empresaId
     * @return
     */
    @Override
    public Optional<EmpresaRead> getOne(Integer empresaId) {
        Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
        if (empresaId == 0) {
            throw new BadRequestException("El id de la empresa debe ser diferente 0");
        }
        if (empresaDB.isPresent()) {
            return empresaDB.map(empresa -> empresaMapper.toEmpresaRead(empresa));
        }
        throw new BadRequestException("Empresa con id " + empresaId + " no encontrada");
    }

    /**
     * Devuelve uns EmpresaRead que provee el repositorio
     * Para devolver una EmpresaRead se realiza el proceso de conversión de objetos con la interfaz EmpresaMapper
     * Para guardar un empresa previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param interventorId
     * @param empresaDTO
     * @return EmpresaRead
     */
    @Override
    public EmpresaRead save(Integer interventorId, EmpresaDTO empresaDTO) {
        Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
        if (interventorDB.isPresent()) {
            Optional<Empresa> empresaDB = empresaCrudRepository.findByInterventorIden(interventorId);
            if (!empresaDB.isPresent()) {
                Empresa empresa = empresaMapper.toEmpresa(empresaDTO);
                empresa.setInterventor(interventorDB.get());
                return empresaMapper.toEmpresaRead(empresaCrudRepository.save(empresa));
            }
            throw new BadRequestException("Empresa con interventor " + interventorDB.get().getCodigo() + " ya esta registrada");
        }
        throw new BadRequestException("Interventor con id " + interventorId + " no encontrado para relacionar a la empresa");
    }

    /**
     * Devuelve un boolean al borrar la informacion mediante el repositorio
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param empresaId
     * @return boolean
     */
    @Override
    public boolean delete(Integer empresaId) {
        try {
            Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
            if (empresaDB.isPresent()) {
                empresaCrudRepository.deleteById(empresaId);
                return true;
            }
            throw new BadRequestException("Empresa con id " + empresaId + " no encontrada para eliminar");
        } catch (Exception exception){
            throw new BadRequestException("La empresa que intenta eliminar cuenta con gateways, usuarios, clientes, etc. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso la empresa no debe tener gateways, usuarios, clientes, etc. Relacionados con esta empresa.");
        }
    }

    /**
     * Devuelve uns EmpresaRead que provee el repositorio
     * Para devolver una EmpresaRead se realiza el proceso de conversión de objetos con la interfaz EmpresaMapper
     * Para actualizar un empresa previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param interventorId
     * @param empresaId
     * @param empresaDTO
     * @return
     */
    @Override
    public EmpresaRead update(Integer interventorId, Integer empresaId, EmpresaDTO empresaDTO) {
        Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
        if (empresaDB.isPresent()) {
            Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
            if (interventorDB.isPresent()) {
                Empresa empresa = empresaDB.get();
                Empresa newEmpresa = empresaMapper.toEmpresa(empresaDTO);
                empresa.setIdorganizationas(newEmpresa.getIdorganizationas());
                empresa.setNumDevices(newEmpresa.getNumDevices());
                empresa.setNumGateways(newEmpresa.getNumGateways());
                empresa.setUpdatedAt(LocalDateTime.now());
                empresa.setInterventor(interventorDB.get());
                return empresaMapper.toEmpresaRead(empresaCrudRepository.save(empresa));
            }
            throw new BadRequestException("Interventor con id " + interventorId + " no encontrado para actualizar");
        }
        throw new BadRequestException("Empresa con id " + empresaId + " no encontrada para actualizar");
    }

}
