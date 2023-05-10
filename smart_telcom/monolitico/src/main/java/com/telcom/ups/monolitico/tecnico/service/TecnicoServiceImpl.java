package com.telcom.ups.monolitico.tecnico.service;

import com.telcom.ups.data.entities.Empresa;
import com.telcom.ups.data.entities.Estatus;
import com.telcom.ups.data.entities.Interventor;
import com.telcom.ups.data.entities.Tecnico;
import com.telcom.ups.data.info.TecnicoInfo;
import com.telcom.ups.data.read.TecnicoRead;
import com.telcom.ups.monolitico.empresa.repository.EmpresaCrudRepository;
import com.telcom.ups.monolitico.estatus.repository.EstatusCrudRepository;
import com.telcom.ups.monolitico.interventor.repository.InterventorCrudRepository;
import com.telcom.ups.monolitico.tecnico.mapper.TecnicoMapper;
import com.telcom.ups.monolitico.tecnico.repository.TecnicoCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TecnicoServiceImpl implements TecnicoService {

    @Autowired
    private TecnicoCrudRepository tecnicoCrudRepository;

    @Autowired
    private EstatusCrudRepository estatusCrudRepository;

    @Autowired
    private InterventorCrudRepository interventorCrudRepository;

    @Autowired
    private EmpresaCrudRepository empresaCrudRepository;

    @Autowired
    private TecnicoMapper tecnicoMapper;


    /**
     * Devuelve un Page de TecnicoInfos que provee el repositorio
     * Para devolver un Page de TecnicoInfos se realiza el proceso de conversión de objetos con la interfaz TecnicoMapper
     *
     * @param pageable
     * @return pade de TecnicoInfos
     */
    @Override
    public Page<TecnicoInfo> findAll(Pageable pageable) {
        return tecnicoCrudRepository.findAll(pageable).map(tecnico -> tecnicoMapper.toTecnicoInfo(tecnico));
    }

    /**
     * Devuelve un Optional de TecnicoRead que provee el repositorio
     * Para devolver un optional de TecnicoRead se realiza el proceso de conversión de objetos con la interfaz TecnicoMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return optional de TecnicoRead
     */
    @Override
    public Optional<TecnicoRead> getOne(Integer id) {
        Optional<Tecnico> tenicoDB = tecnicoCrudRepository.findById(id);
        if (tenicoDB.isPresent()) {
            return tenicoDB.map(tecnico -> tecnicoMapper.toTecnicoRead(tecnico));
        }
        throw new BadRequestException("Tecnico con id " + id + " no encontrado");
    }

    /**
     * Devuelve un TecnicoInfo que provee el repositorio
     * Para devolver un TecnicoInfo se realiza el proceso de conversión de objetos con la interfaz TecnicoMapper
     * Para guardar un tecnico previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param estatusId
     * @param interventorId
     * @param empresaId
     * @return TecnicoInfo
     */
    @Override
    public TecnicoInfo save(Integer estatusId, Integer interventorId, Integer empresaId) {
        Optional<Estatus> estatusDB = estatusCrudRepository.findById(estatusId);
        if (estatusDB.isPresent()) {
            Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
            if (interventorDB.isPresent()) {
                Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
                if (empresaDB.isPresent()) {
                    Optional<Tecnico> tecnicoDBInterventor = tecnicoCrudRepository.findByInterventorIden(interventorId);
                    if (!tecnicoDBInterventor.isPresent()) {
                        Optional<Tecnico> tecnicoDB = tecnicoCrudRepository.findByInterventorEmpresa(interventorId, empresaId);
                        if (!tecnicoDB.isPresent()) {
                            Tecnico tecnico = new Tecnico();
                            tecnico.setEstatus(estatusDB.get());
                            tecnico.setInterventor(interventorDB.get());
                            tecnico.setEmpresa(empresaDB.get());
                            return tecnicoMapper.toTecnicoInfo(tecnicoCrudRepository.save(tecnico));
                        }
                        throw new BadRequestException("Técnico con interventor " + interventorDB.get().getCodigo() + " para la empresa "
                                + empresaDB.get().getInterventor().getNombre() + empresaDB.get().getInterventor().getApellido() + " ya se encuentra registrado");
                    }
                    throw new BadRequestException("Técnico con interventor " + interventorDB.get().getCodigo() + " ya se encuentra registrado en la empresa "
                            + tecnicoDBInterventor.get().getEmpresa().getInterventor().getNombre() + tecnicoDBInterventor.get().getEmpresa().getInterventor().getApellido());
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
            Optional<Tecnico> tecnico = tecnicoCrudRepository.findById(id);
            if (tecnico.isPresent()) {
                tecnicoCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Tecnico con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El técnico que intenta eliminar pertenece a una zona, previo a esto para que el proceso de eliminación " +
                    "sea exitoso, debe eliminar la zona a la cual pertenece dicho técnico");
        }
    }

    /**
     * Devuelve un TecnicoInfo que provee el repositorio
     * Para devolver un TecnicoInfo se realiza el proceso de conversión de objetos con la interfaz TecnicoMapper
     * Para actualizar un tecnico previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param estatusId
     * @param interventorId
     * @param empresaId
     * @param id
     * @return TecnicoInfo
     */
    @Override
    public TecnicoInfo update(Integer estatusId, Integer interventorId, Integer empresaId, Integer id) {
        Optional<Tecnico> tecnicoDB = tecnicoCrudRepository.findById(id);
        if (tecnicoDB.isPresent()) {
            Optional<Estatus> estatusDB = estatusCrudRepository.findById(estatusId);
            if (estatusDB.isPresent()) {
                Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
                if (interventorDB.isPresent()) {
                    Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
                    if (empresaDB.isPresent()) {
                        Tecnico tecnico = tecnicoDB.get();
                        tecnico.setEstatus(estatusDB.get());
                        tecnico.setInterventor(interventorDB.get());
                        tecnico.setEmpresa(empresaDB.get());
                        return tecnicoMapper.toTecnicoInfo(tecnicoCrudRepository.save(tecnico));
                    }
                    throw new BadRequestException("Empresa con id " + empresaId + " no encontrada para relacionar con tecnico");
                }
                throw new BadRequestException("Interventor con id " + interventorId + " no encontrado para relacionar con tecnico");
            }
            throw new BadRequestException("Estatus con id " + estatusId + " no encontrado para relacionar con tecnico");
        }
        throw new BadRequestException("Tecnico con id " + id + " no encontrado para actualizar");
    }

    /**
     * Devuelve un Page de TecnicoInfos que provee el repositorio
     * Para devolver un page de TecnicoInfos se realiza el proceso de conversión de objetos con la interfaz TecnicoMapper
     *
     * @param idEmpresa
     * @param pageable
     * @return page de TecnicoInfos
     */
    @Override
    public Page<TecnicoInfo> getAllByEmpresa(Integer idEmpresa, Pageable pageable) {
        return tecnicoCrudRepository.getAllByEmpresa(idEmpresa, pageable).map(tecnico -> tecnicoMapper.toTecnicoInfo(tecnico));
    }
}
