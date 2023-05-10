package com.telcom.ups.monolitico.detalle.service;

import com.telcom.ups.data.dto.DetalleDTO;
import com.telcom.ups.data.entities.Detalle;
import com.telcom.ups.data.read.DetalleRead;
import com.telcom.ups.monolitico.detalle.mapper.DetalleMapper;
import com.telcom.ups.monolitico.detalle.repository.DetalleCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetalleServiceImpl implements DetalleService{

    @Autowired
    private DetalleCrudRepository detalleCrudRepository;

    @Autowired
    private DetalleMapper mapper;

    /**
     * Devuelve un Page de DetalleReads que provee el repositorio CRUD
     * Para devolver un Page de DetalleReads se realiza el proceso de conversión de objetos con la interfaz DetalleMapper
     *
     * @param pageable
     * @return page de DetalleRead
     */
    @Override
    public Page<DetalleRead> findAll(Pageable pageable) {
        return detalleCrudRepository.findAll(pageable).map(detalle -> mapper.toDetalleRead(detalle));
    }

    /**
     * Devuelve un Optional de DetalleRead que provee el repositorio CRUD
     * Para devolver un optional de DetalleRead se realiza el proceso de conversión de objetos con la interfaz DetalleMapper
     *
     * @param id
     * @return optional de DetalleRead
     */
    @Override
    public Optional<DetalleRead> getOne(Integer id) {
        return detalleCrudRepository.findById(id).map(detalle -> mapper.toDetalleRead(detalle));
    }

    /**
     * Devuelve un DetalleRead que provee el repositorio CRUD
     * Para devolver un Page de DetalleReads se realiza el proceso de conversión de objetos con la interfaz DetalleMapper
     *
     * @param detalleDTO
     * @return DetalleRead
     */
    @Override
    public DetalleRead save(DetalleDTO detalleDTO) {
        Detalle detalle = mapper.toDetalle(detalleDTO);
        return mapper.toDetalleRead(detalleCrudRepository.save(detalle));
    }

    /**
     * Eliminar la informacion mediante el repositorio CRUD
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        detalleCrudRepository.deleteById(id);
    }

    /**
     * No implementado
     *
     * @param detalleDTO
     * @param id
     * @return
     */
    @Override
    public DetalleRead update(DetalleDTO detalleDTO, Integer id) {
        return null;
    }
}
