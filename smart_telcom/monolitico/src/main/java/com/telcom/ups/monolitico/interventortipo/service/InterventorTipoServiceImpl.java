package com.telcom.ups.monolitico.interventortipo.service;

import com.telcom.ups.data.dto.InterventorTipoDTO;
import com.telcom.ups.data.entities.InterventorTipo;
import com.telcom.ups.data.read.InterventorTipoRead;
import com.telcom.ups.monolitico.interventortipo.mapper.InterventorTipoMapper;
import com.telcom.ups.monolitico.interventortipo.repository.InterventorTipoCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InterventorTipoServiceImpl implements InterventorTipoService {

    @Autowired
    private InterventorTipoCrudRepository interventorTipoCrudRepository;

    @Autowired
    private InterventorTipoMapper interventorTipoMapper;


    /**
     * Devuelve un Page de InterventorTipoReads que provee el repositorio
     * Para devolver un Page de InterventorTipoReads se realiza el proceso de conversión de objetos con la interfaz InterventorTipoMapper
     *
     * @param pageable
     * @return page de InterventorTipoRead
     */
    @Override
    public Page<InterventorTipoRead> findAll(Pageable pageable) {
        return interventorTipoCrudRepository.findAll(pageable).map(interventorTipo -> interventorTipoMapper.toInterventorTipoRead(interventorTipo));
    }

    /**
     * Devuelve un Optional de InterventorTipoRead que provee el repositorio
     * Para devolver un Optional de InterventorTipoRead se realiza el proceso de conversión de objetos con la interfaz InterventorTipoMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return optional de InterventorTipoRead
     */
    @Override
    public Optional<InterventorTipoRead> getOne(Integer id) {
        Optional<InterventorTipo> interventorTipoDB = interventorTipoCrudRepository.findById(id);
        if (interventorTipoDB.isPresent()) {
            return interventorTipoDB.map(interventorTipo -> interventorTipoMapper.toInterventorTipoRead(interventorTipo));
        }
        throw new BadRequestException("InterventorTipo con id " + id + " no encontrado");
    }

    /**
     * Devuelve un InterventorTipoRead que provee el repositorio
     * Para devolver un InterventorTipoRead se realiza el proceso de conversión de objetos con la interfaz InterventorTipoMapper
     * Para guardar un tipo de interventor previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param interventorTipoDTO
     * @return InterventorTipoRead
     */
    @Override
    public InterventorTipoRead save(InterventorTipoDTO interventorTipoDTO) {
        Optional<InterventorTipo> interventorTipoDB = interventorTipoCrudRepository.findByNombre(interventorTipoDTO.getNombre());
        if (!interventorTipoDB.isPresent()) {
            InterventorTipo interventorTipo = interventorTipoMapper.toInterventorTipo(interventorTipoDTO);
            return interventorTipoMapper.toInterventorTipoRead(interventorTipoCrudRepository.save(interventorTipo));
        }
        throw new BadRequestException("InterventorTipo con nombre " + interventorTipoDTO.getNombre() + " ya esta registrado");
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
            Optional<InterventorTipo> interventorTipoDB = interventorTipoCrudRepository.findById(id);
            if (interventorTipoDB.isPresent()) {
                interventorTipoCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("InterventorTipo con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El tipo de interventor que intenta eliminar cuenta con objetos relacionados. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso el tipo de interventor no debe contar con objetos relacionados al mismo.");
        }
    }

    /**
     * Devuelve un InterventorTipoRead que provee el repositorio
     * Para devolver un InterventorTipoRead se realiza el proceso de conversión de objetos con la interfaz InterventorTipoMapper
     * Para actualizar un tipo de interventor previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param interventorTipoDTO
     * @param id
     * @return InterventorTipoRead
     */
    @Override
    public InterventorTipoRead update(InterventorTipoDTO interventorTipoDTO, Integer id) {
        Optional<InterventorTipo> interventorTipo = interventorTipoCrudRepository.findById(id);
        if (interventorTipo.isPresent()) {
            InterventorTipo newInterventorTipo = interventorTipoMapper.toInterventorTipo(interventorTipoDTO);
            InterventorTipo intTipo = interventorTipo.get();
            intTipo.setNombre(newInterventorTipo.getNombre());
            intTipo.setIdclientesri(newInterventorTipo.getIdclientesri());
            intTipo.setIdproveedorsri(newInterventorTipo.getIdproveedorsri());
            intTipo.setIdtarcredito(newInterventorTipo.getIdtarcredito());
            intTipo.setUpdatedAt(LocalDateTime.now());
            return interventorTipoMapper.toInterventorTipoRead(interventorTipoCrudRepository.save(intTipo));
        }
        throw new BadRequestException("InterventorTipo con id " + id + " no encontrado para actualizar");
    }
}
