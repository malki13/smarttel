package com.telcom.ups.monolitico.tarifa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telcom.ups.data.dto.TarifaDTO;
import com.telcom.ups.data.entities.Empresa;
import com.telcom.ups.data.entities.Tarifa;
import com.telcom.ups.data.info.TarifaInfo;
import com.telcom.ups.monolitico.empresa.repository.EmpresaCrudRepository;
import com.telcom.ups.monolitico.tarifa.mapper.TarifaMapper;
import com.telcom.ups.monolitico.tarifa.repository.TarifaCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TarifaServiceImpl implements TarifaService{

    @Autowired
    private TarifaCrudRepository tarifaCrudRepository;

    @Autowired
    private EmpresaCrudRepository empresaCrudRepository;

    @Autowired
    private TarifaMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Devuelve un Page de TarifaInfos que provee el repositorio CRUD
     * Para devolver un Page de TarifaInfos se realiza el proceso de conversión de objetos con la interfaz TarifaMapper
     *
     * @param pageable
     * @return page de TarifaInfos
     */
    @Override
    public Page<TarifaInfo> findAll(Pageable pageable) {
        return tarifaCrudRepository.findAll(pageable).map(tarifa -> mapper.toTarifaInfo(tarifa));
    }

    /**
     * Devuelve un Optional de TarifaInfo que provee el repositorio CRUD
     * Para devolver un optional de TarifaInfo se realiza el proceso de conversión de objetos con la interfaz TarifaMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return optional de TarifaInfo
     */
    @Override
    public Optional<TarifaInfo> getOne(Integer id) {
        Optional<Tarifa> tarifaDB = tarifaCrudRepository.findById(id);
        if (tarifaDB.isPresent()) {
            return tarifaDB.map(tarifa -> mapper.toTarifaInfo(tarifa));
        }
        throw new BadRequestException("Tarifa con id " + id + " no encontrada");
    }

    /**
     * Devuelve un TarifaInfo que provee el repositorio CRUD
     * Para devolver un TarifaInfo se realiza el proceso de conversión de objetos con la interfaz TarifaMapper
     * Para guardar una tarifa previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param empresaId
     * @param tarifaDTO
     * @return TarifaInfo
     */
    @Override
    public TarifaInfo save(Integer empresaId, TarifaDTO tarifaDTO) {
        Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
        if (empresaDB.isPresent()) {
            Optional<Tarifa> tarifaDB = tarifaCrudRepository.findByNombreCategoria(tarifaDTO.getNombreCategoria());
            if (!tarifaDB.isPresent()) {
                Tarifa tarifa = mapper.toTarifa(tarifaDTO);
                tarifa.setEmpresa(empresaDB.get());
                return mapper.toTarifaInfo(tarifaCrudRepository.save(tarifa));
            }
            throw new BadRequestException("Tarifa con nombre de categoria " + tarifaDTO.getNombreCategoria() + " ya esta registrada");
        }
        throw new BadRequestException("Empresa con id " + empresaId + " no encontrado para relacionar con el tarifa");
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
            Optional<Tarifa> tarifaDB = tarifaCrudRepository.findById(id);
            if (tarifaDB.isPresent()) {
                tarifaCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Tarifa con id " + id + " no encontrada para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("La tarifa que intenta eliminar cuenta con objetos relacionados. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso la tarifa no debe contar con objetos relacionados a la misma.");
        }
    }

    /**
     * Devuelve un TarifaInfo que provee el repositorio CRUD
     * Para devolver un TarifaInfo se realiza el proceso de conversión de objetos con la interfaz TarifaMapper
     * Para actualizar una tarifa previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param empresaId
     * @param tarifaDTO
     * @param id
     * @return TarifaInfo
     */
    @Override
    public TarifaInfo update(Integer empresaId, TarifaDTO tarifaDTO, Integer id) {
        Optional<Tarifa> tarifaDB = tarifaCrudRepository.findById(id);
        if (tarifaDB.isPresent()) {
            Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
            if (empresaDB.isPresent()) {
                Tarifa newTarifa = mapper.toTarifa(tarifaDTO);
                Tarifa tar = tarifaDB.get();
                tar.setNombreCategoria(newTarifa.getNombreCategoria());
                tar.setRangoConsumo(newTarifa.getRangoConsumo());
                tar.setCargoDisponibilidad(newTarifa.getCargoDisponibilidad());
                tar.setCargoVariable(newTarifa.getCargoVariable());
                tar.setEmpresa(empresaDB.get());
                tar.setCreatedAt(LocalDateTime.now());
                return mapper.toTarifaInfo(tarifaCrudRepository.save(tar));
            }
            throw new BadRequestException("Empresa con id " + empresaId + " no encontrado para relacionar con el tarifa");
        }
        throw new BadRequestException("Tarifa con id " + id + " no encontrada para actualizar");
    }

    /**
     * Devuelve un Page de TarifaInfos que provee el repositorio CRUD
     * Para devolver un page de TarifaInfos se realiza el proceso de conversión de objetos con la interfaz TarifaMapper
     *
     * @param idEmpresa
     * @param pageable
     * @return page de TarifaInfos
     */
    @Override
    public Page<TarifaInfo> getAllByEmpresa(Integer idEmpresa, Pageable pageable) {
        return tarifaCrudRepository.getAllByEmpresa(idEmpresa, pageable).map(tarifa -> mapper.toTarifaInfo(tarifa));
    }
}
