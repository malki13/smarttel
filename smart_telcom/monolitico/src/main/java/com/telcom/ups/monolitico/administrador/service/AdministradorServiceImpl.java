package com.telcom.ups.monolitico.administrador.service;

import com.telcom.ups.data.entities.Administrador;
import com.telcom.ups.data.entities.Empresa;
import com.telcom.ups.data.entities.Estatus;
import com.telcom.ups.data.entities.Interventor;
import com.telcom.ups.data.info.AdministradorInfo;
import com.telcom.ups.data.read.AdministradorRead;
import com.telcom.ups.monolitico.administrador.mapper.AdministradorMapper;
import com.telcom.ups.monolitico.administrador.repository.AdministradorCrudRepository;
import com.telcom.ups.monolitico.empresa.repository.EmpresaCrudRepository;
import com.telcom.ups.monolitico.estatus.repository.EstatusCrudRepository;
import com.telcom.ups.monolitico.interventor.repository.InterventorCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    @Autowired
    private AdministradorCrudRepository administradorCrudRepository;

    @Autowired
    private EstatusCrudRepository estatusCrudRepository;

    @Autowired
    private InterventorCrudRepository interventorCrudRepository;

    @Autowired
    private EmpresaCrudRepository empresaCrudRepository;

    @Autowired
    private AdministradorMapper administradorMapper;


    /**
     * Devuelve un Page de AdministradorInfos que provee el repositorio
     * Para devolver un Page de AdministradorInfos se realiza el proceso de conversión de objetos con la interfaz AdministradorMapper
     *
     * @param pageable
     * @return page de AdministradorInfos
     */
    @Override
    public Page<AdministradorInfo> findAll(Pageable pageable) {
        return administradorCrudRepository.findAll(pageable).map(administrador -> administradorMapper.toAdministradorInfo(administrador));
    }

    /**
     * Devuelve un Optional de AdministradorRead que provee el repositorio
     * Para devolver un Optional de AdministradorRead se realiza el proceso de conversión de objetos con la interfaz AdministradorMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return optional de AdministradorRead
     */
    @Override
    public Optional<AdministradorRead> getOne(Integer id) {
        Optional<Administrador> administradorDB = administradorCrudRepository.findById(id);
        if (administradorDB.isPresent()) {
            return administradorDB.map(tecnico -> administradorMapper.toAdministradorRead(tecnico));
        }
        throw new BadRequestException("Administrador con id " + id + " no encontrado");
    }

    /**
     * Devuelve un AdministradorInfo que provee el repositorio
     * Para devolver un AdministradorInfo se realiza el proceso de conversión de objetos con la interfaz AdministradorMapper
     * Para guardar un administrador previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param estatusId
     * @param interventorId
     * @param empresaId
     * @return AdministradorInfo
     */
    @Override
    public AdministradorInfo save(Integer estatusId, Integer interventorId, Integer empresaId) {
        Optional<Estatus> estatusDB = estatusCrudRepository.findById(estatusId);
        if (estatusDB.isPresent()) {
            Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
            if (interventorDB.isPresent()) {
                Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
                if (empresaDB.isPresent()) {
                    Optional<Administrador> administradorDBInterventor = administradorCrudRepository.findByInterventorIden(interventorId);
                    if (!administradorDBInterventor.isPresent()) {
                        Optional<Administrador> administradorDBEmpresaInterventor = administradorCrudRepository.findByInterventorEmpresa(interventorId, empresaId);
                        if (!administradorDBEmpresaInterventor.isPresent()) {
                            Administrador administrador = new Administrador();
                            administrador.setEstatus(estatusDB.get());
                            administrador.setInterventor(interventorDB.get());
                            administrador.setEmpresa(empresaDB.get());
                            return administradorMapper.toAdministradorInfo(administradorCrudRepository.save(administrador));
                        }
                        throw new BadRequestException("Administrador con interventor " + interventorDB.get().getCodigo() + " para la empresa "
                                + empresaDB.get().getInterventor().getNombre() + empresaDB.get().getInterventor().getApellido() + " ya se encuentra registrado");
                    }
                    throw new BadRequestException("Administrador con interventor " + interventorDB.get().getCodigo() + " ya se encuentra registrado en la empresa "
                            + administradorDBInterventor.get().getEmpresa().getInterventor().getNombre() + administradorDBInterventor.get().getEmpresa().getInterventor().getApellido());
                }
                throw new BadRequestException("Empresa con id " + empresaId + " no encontrada para relacionar con tecnico");
            }
            throw new BadRequestException("Interventor con id " + interventorId + " no encontrado para relacionar con tecnico");
        }
        throw new BadRequestException("Estatus con id " + estatusId + " no encontrado para relacionar con tecnico");
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
        try {
            Optional<Administrador> administrador = administradorCrudRepository.findById(id);
            if (administrador.isPresent()) {
                administradorCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Administrador con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El administrador que intenta eliminar pertenece a una zona, previo a esto para que el proceso de eliminación " +
                    "sea exitoso, debe eliminar la zona a la cual pertenece dicho administrador");
        }

    }

    /**
     * Devuelve un AdministradorInfo que provee el repositorio
     * Para devolver un AdministradorInfo se realiza el proceso de conversión de objetos con la interfaz AdministradorMapper
     * Para actualizar un administrador previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param estatusId
     * @param interventorId
     * @param empresaId
     * @param id
     * @return AdministradorInfo
     */
    @Override
    public AdministradorInfo update(Integer estatusId, Integer interventorId, Integer empresaId, Integer id) {
        Optional<Administrador> administradorDB = administradorCrudRepository.findById(id);
        if (administradorDB.isPresent()) {
            Optional<Estatus> estatusDB = estatusCrudRepository.findById(estatusId);
            if (estatusDB.isPresent()) {
                Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
                if (interventorDB.isPresent()) {
                    Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
                    if (empresaDB.isPresent()) {
                        Administrador administrador = administradorDB.get();
                        administrador.setEstatus(estatusDB.get());
                        administrador.setInterventor(interventorDB.get());
                        administrador.setEmpresa(empresaDB.get());
                        return administradorMapper.toAdministradorInfo(administradorCrudRepository.save(administrador));
                    }
                    throw new BadRequestException("Empresa con id " + empresaId + " no encontrada para relacionar con tecnico");
                }
                throw new BadRequestException("Interventor con id " + interventorId + " no encontrado para relacionar con tecnico");
            }
            throw new BadRequestException("Estatus con id " + estatusId + " no encontrado para relacionar con tecnico");
        }
        throw new BadRequestException("Administrador con id " + id + " no encontrado para actualizar");
    }

    /**
     * Devuelve un Page de AdministradorInfos que provee el repositorio
     * Para devolver un Page de AdministradorInfos se realiza el proceso de conversión de objetos con la interfaz AdministradorMapper
     *
     * @param idEmpresa
     * @param pageable
     * @return page de AdministradorInfos
     */
    @Override
    public Page<AdministradorInfo> getAllByEmpresa(Integer idEmpresa, Pageable pageable) {
        return administradorCrudRepository.getAllByEmpresa(idEmpresa, pageable).map(administrador -> administradorMapper.toAdministradorInfo(administrador));
    }
}
