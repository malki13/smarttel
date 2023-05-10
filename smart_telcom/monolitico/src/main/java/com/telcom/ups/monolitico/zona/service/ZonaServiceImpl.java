package com.telcom.ups.monolitico.zona.service;

import com.telcom.ups.data.dto.ZonaDTO;
import com.telcom.ups.data.entities.Administrador;
import com.telcom.ups.data.entities.Empresa;
import com.telcom.ups.data.entities.Tecnico;
import com.telcom.ups.data.entities.Zona;
import com.telcom.ups.data.info.ZonaInfo;
import com.telcom.ups.monolitico.administrador.repository.AdministradorCrudRepository;
import com.telcom.ups.monolitico.empresa.repository.EmpresaCrudRepository;
import com.telcom.ups.monolitico.tecnico.repository.TecnicoCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import com.telcom.ups.monolitico.zona.mapper.ZonaMapper;
import com.telcom.ups.monolitico.zona.repository.ZonaCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ZonaServiceImpl implements ZonaService {

    @Autowired
    private ZonaCrudRepository zonaCrudRepository;

    @Autowired
    private AdministradorCrudRepository administradorCrudRepository;

    @Autowired
    private TecnicoCrudRepository tecnicoCrudRepository;

    @Autowired
    private EmpresaCrudRepository empresaCrudRepository;

    @Autowired
    private ZonaMapper zonaMapper;


    /**
     * Devuelve un Page de ZonaInfos que provee el repositorio
     * Para devolver un Page de ZonaInfos se realiza el proceso de conversión de objetos con la interfaz ZonaMapper
     *
     * @param pageable
     * @return page de ZonaInfo
     */
    @Override
    public Page<ZonaInfo> findAll(Pageable pageable) {
        return zonaCrudRepository.findAll(pageable).map(zona -> zonaMapper.toZonaInfo(zona));
    }

    /**
     * Devuelve un Optional de ZonaInfo que provee el repositorio
     * Para devolver un optional de ZonaInfo se realiza el proceso de conversión de objetos con la interfaz ZonaMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesta de la petición
     *
     * @param id
     * @return optional de ZonaInfo
     */
    @Override
    public Optional<ZonaInfo> getOne(Integer id) {
        Optional<Zona> zonaDB = zonaCrudRepository.findById(id);
        if (zonaDB.isPresent()) {
            return zonaDB.map(zona -> zonaMapper.toZonaInfo(zona));
        }
        throw new BadRequestException("Zona con id " + id + " no encontrada");
    }

    /**
     * Devuelve un ZonaInfo que provee el repositorio
     * Para devolver un ZonaInfo se realiza el proceso de conversión de objetos con la interfaz ZonaMapper
     * Para guardar una zona previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesta de la petición
     *
     * @param empresaId
     * @param adminId
     * @param tecnicoId
     * @param zonaDTO
     * @return ZonaInfo
     */
    @Override
    @Transactional
    public ZonaInfo save(Integer empresaId, Integer adminId, Integer tecnicoId, ZonaDTO zonaDTO) {
        Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
        if (empresaDB.isPresent()) {
            Optional<Administrador> administradorDB = administradorCrudRepository.findById(adminId);
            if (administradorDB.isPresent()) {
                Optional<Tecnico> tecnicoDB = tecnicoCrudRepository.findById(tecnicoId);
                if (tecnicoDB.isPresent()) {
                    Optional<Zona> zonaDB = zonaCrudRepository.findByNombre(zonaDTO.getNombre());
                    if (!zonaDB.isPresent()) {
                        Zona zona = zonaMapper.toZona(zonaDTO);
                        zona.setEmpresa(empresaDB.get());
                        zona.setAdministrador(administradorDB.get());
                        zona.setTecnico(tecnicoDB.get());
                        return zonaMapper.toZonaInfo(zonaCrudRepository.save(zona));
                    }
                    throw new BadRequestException("Zona con nombre " + zonaDTO.getNombre() + " ya esta registrada");
                }
                throw new BadRequestException("Tecnico con id " + tecnicoId + " no encontrada para relacionar a la zona");
            }
            throw new BadRequestException("Administrador con id " + adminId + " no encontrada para relacionar a la zona");
        }
        throw new BadRequestException("Empresa con id " + empresaId + " no encontrada para relacionar a la zona");
    }


    /**
     * Devuelve un boolean al borrar la informacion mediante el repositorio
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesta de la petición
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean delete(Integer id) {
        try {
            Optional<Zona> zonaDB = zonaCrudRepository.findById(id);
            if (zonaDB.isPresent()) {
                zonaCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Zona con id " + id + " no encontrada para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("La zona que intenta eliminar cuenta con objetos relacionados. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso la zona no debe contar con objetos relacionados a la misma.");
        }
    }

    /**
     * Devuelve un ZonaInfo que provee el repositorio
     * Para devolver un ZonaInfo se realiza el proceso de conversión de objetos con la interfaz ZonaMapper
     * Para actualizar una zona previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesta de la petición
     *
     * @param empresaId
     * @param adminId
     * @param tecnicoId
     * @param id
     * @param zonaDTO
     * @return ZonaInfo
     */
    @Override
    public ZonaInfo update(Integer empresaId, Integer adminId, Integer tecnicoId, Integer id, ZonaDTO zonaDTO) {
        Optional<Zona> zonaDB = zonaCrudRepository.findById(id);
        if (zonaDB.isPresent()) {
            Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
            if (empresaDB.isPresent()) {
                Optional<Administrador> administradorDB = administradorCrudRepository.findById(adminId);
                if (administradorDB.isPresent()) {
                    Optional<Tecnico> tecnicoDB = tecnicoCrudRepository.findById(tecnicoId);
                    if (tecnicoDB.isPresent()) {
                        Zona zona = zonaDB.get();
                        Zona newZona = zonaMapper.toZona(zonaDTO);
                        zona.setNombre(newZona.getNombre());
                        zona.setEmpresa(empresaDB.get());
                        zona.setAdministrador(administradorDB.get());
                        zona.setTecnico(tecnicoDB.get());
                        zona.setDescServicio(newZona.getDescServicio());
                        zona.setUpdatedAt(LocalDateTime.now());
                        return zonaMapper.toZonaInfo(zonaCrudRepository.save(zona));
                    }
                    throw new BadRequestException("Tecnico con id " + tecnicoId + " no encontrada para relacionar a la zona");
                }
                throw new BadRequestException("Administrador con id " + adminId + " no encontrada para relacionar a la zona");
            }
            throw new BadRequestException("Empresa con id " + empresaId + " no encontrada para relacionar a la zona");
        }
        throw new BadRequestException("Zona con id " + id + " no encontrada para actualizar");
    }

    /**
     * Devuelve un Page de ZonaInfos que provee el repositorio
     * Para devolver un page de ZonaInfos se realiza el proceso de conversión de objetos con la interfaz ZonaMapper
     *
     * @param id
     * @param pageable
     * @return page de ZonaInfo
     */
    @Override
    public Page<ZonaInfo> getAllByEmpresa(Integer id, Pageable pageable) {
        return zonaCrudRepository.getAllByEmpresa(id, pageable).map(zona -> zonaMapper.toZonaInfo(zona));
    }
}
