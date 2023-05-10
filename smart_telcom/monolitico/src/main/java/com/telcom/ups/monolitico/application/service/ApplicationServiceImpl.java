package com.telcom.ups.monolitico.application.service;

import com.telcom.ups.data.dto.ApplicationDTO;
import com.telcom.ups.data.entities.Application;
import com.telcom.ups.data.entities.Empresa;
import com.telcom.ups.data.entities.ProtocoloTipo;
import com.telcom.ups.data.info.ApplicationInfo;
import com.telcom.ups.data.read.ApplicationRead;
import com.telcom.ups.monolitico.application.mapper.ApplicationMapper;
import com.telcom.ups.monolitico.application.repository.ApplicationCrudRepository;
import com.telcom.ups.monolitico.empresa.repository.EmpresaCrudRepository;
import com.telcom.ups.monolitico.protocolotipo.repository.ProtocoloTipoCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationCrudRepository applicationCrudRepository;

    @Autowired
    private EmpresaCrudRepository empresaCrudRepository;

    @Autowired
    private ProtocoloTipoCrudRepository protocoloTipoCrudRepository;

    @Autowired
    private ApplicationMapper mapper;

    /**
     * Devuelve un Page de ApplicationInfos que provee el repositorio
     * Para devolver un Page de ApplicationInfos se realiza el proceso de conversión de objetos con la interfaz ApplicationMapper
     *
     * @param pageable
     * @return page de ApplicationInfos
     */
    @Override
    public Page<ApplicationInfo> findAll(Pageable pageable) {
        return applicationCrudRepository.findAll(pageable).map(application -> mapper.toApplicationInfo(application));
    }

    /**
     * Devuelve un Optional de ApplicationRead que provee el repositorio
     * Para devolver un Optional de ApplicationRead se realiza el proceso de conversión de objetos con la interfaz ApplicationMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return optional de ApplicationRead
     */
    @Override
    public Optional<ApplicationRead> getOne(Integer id) {
        Optional<Application> applicationDB = applicationCrudRepository.findById(id);
        if (applicationDB.isPresent()) {
            return applicationDB.map(application -> mapper.toApplicationRead(application));
        }
        throw new BadRequestException("Application con id " + id + " no encontrado");
    }

    /**
     * Devuelve un ApplicationRead que provee el repositorio
     * Para devolver un ApplicationRead se realiza el proceso de conversión de objetos con la interfaz ApplicationMapper
     * Para guardar un application previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param applicationDTO
     * @return ApplicationRead
     */
    @Override
    public ApplicationRead save(ApplicationDTO applicationDTO) {
        Optional<Application> applicationDBCod = applicationCrudRepository.findByCodigo(applicationDTO.getCodigo());
        if (applicationDBCod.isPresent())
            throw new BadRequestException("Application con codigo " + applicationDTO.getCodigo() + " ya esta registrado");
        Optional<Application> applicationDBNombre = applicationCrudRepository.findByNombre(applicationDTO.getNombre());
        if (applicationDBNombre.isPresent())
            throw new BadRequestException("Application con nombre " + applicationDTO.getNombre() + " ya esta registrado");
        Optional<Empresa> empresaDB = empresaCrudRepository.findById(applicationDTO.getEmpresaId());
        if (!empresaDB.isPresent())
            throw new BadRequestException("Empresa con id " + applicationDTO.getEmpresaId() + " no encontrado para relacionar a el Application");
        Optional<ProtocoloTipo> protocoloTipoDB = protocoloTipoCrudRepository.findById(applicationDTO.getProtocoloTipoId());
        if (!protocoloTipoDB.isPresent())
            throw new BadRequestException("ProtocoloTipo con id " + applicationDTO.getProtocoloTipoId() + " no encontrado para relacionar a el Application");

        Application application = mapper.toApplication(applicationDTO);
        application.setEmpresa(empresaDB.get());
        application.setProtocoloTipo(protocoloTipoDB.get());
        return mapper.toApplicationRead(applicationCrudRepository.save(application));
    }

    /**
     * Devuelve un boolean al borrar la informacion mediante el repositorio
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean delete(Integer id) {
        Optional<Application> applicationDB = applicationCrudRepository.findById(id);
        if (applicationDB.isPresent()) {
            applicationCrudRepository.deleteById(id);
            return true;
        }
        throw new BadRequestException("Application con id " + id + " no encontrado para eliminar");
    }

    /**
     * Devuelve un ApplicationRead que provee el repositorio
     * Para devolver un ApplicationRead se realiza el proceso de conversión de objetos con la interfaz ApplicationMapper
     * Para actualizar un application previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param applicationDTO
     * @param id
     * @return ApplicationRead
     */
    @Override
    public ApplicationRead update(ApplicationDTO applicationDTO, Integer id) {
        Optional<Application> applicationDB = applicationCrudRepository.findById(id);
        if (!applicationDB.isPresent())
            throw new BadRequestException("Application con id " + id + " no encontrado para actualizar");
        Optional<Empresa> empresaDB = empresaCrudRepository.findById(applicationDTO.getEmpresaId());
        if (!empresaDB.isPresent())
            throw new BadRequestException("Empresa con id " + applicationDTO.getEmpresaId() + " no encontrado para relacionar a el Application");
        Optional<ProtocoloTipo> protocoloTipoDB = protocoloTipoCrudRepository.findById(applicationDTO.getProtocoloTipoId());
        if (!protocoloTipoDB.isPresent())
            throw new BadRequestException("ProtocoloTipo con id " + applicationDTO.getProtocoloTipoId() + " no encontrado para relacionar a el Application");

        Application app = applicationDB.get();
        Application newApplication = mapper.toApplication(applicationDTO);
        app.setCodigo(newApplication.getCodigo());
        app.setNombre(newApplication.getNombre());
        app.setDescripcion(newApplication.getDescripcion());
        app.setIdServiceProfile(newApplication.getIdServiceProfile());
        app.setEmpresa(empresaDB.get());
        app.setProtocoloTipo(protocoloTipoDB.get());
        app.setUpdatedAt(LocalDateTime.now());
        return mapper.toApplicationRead(applicationCrudRepository.save(app));
    }

//    @Override
//    public Page<ApplicationInfo> getAllByPerfilServicio(Integer idPerfilServicio, Pageable pageable) {
//        return applicationCrudRepository.getAllByPerfilServicio(idPerfilServicio, pageable).map(application -> mapper.toApplicationInfo(application));
//    }

    @Override
    public Page<ApplicationInfo> getAllByEmpresa(Integer idEmpresa, Pageable pageable) {
        return applicationCrudRepository.getAllByEmpresa(idEmpresa, pageable).map(application -> mapper.toApplicationInfo(application));
    }

    @Override
    public List<ApplicationInfo> getApplicationsByEmpresaProtocolo(Integer idEmpresa, Integer idProtocoloTipo) {
        return mapper.toApplicationInfos(applicationCrudRepository.findByEmpresaIdenAndProtocoloTipoIden(idEmpresa, idProtocoloTipo));
    }

}
