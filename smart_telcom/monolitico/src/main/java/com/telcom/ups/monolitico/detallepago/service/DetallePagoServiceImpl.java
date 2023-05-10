package com.telcom.ups.monolitico.detallepago.service;

import com.telcom.ups.data.dto.DetallePagoDTO;
import com.telcom.ups.data.entities.DetallePago;
import com.telcom.ups.data.read.DetallePagoRead;
import com.telcom.ups.monolitico.detallepago.mapper.DetallePagoMapper;
import com.telcom.ups.monolitico.detallepago.repository.DetallePagoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetallePagoServiceImpl implements DetallePagoService{

    @Autowired
    private DetallePagoCrudRepository detallePagoCrudRepository;

    @Autowired
    private DetallePagoMapper mapper;


    /**
     * Devuelve un Page de DetallePagoReads que provee el repositorio CRUD
     * Para devolver un Page de DetallePagoReads se realiza el proceso de conversión de objetos con la interfaz DetallePagoMapper
     *
     * @param pageable
     * @return page de DetallePagoRead
     */
    @Override
    public Page<DetallePagoRead> findAll(Pageable pageable) {
        return detallePagoCrudRepository.findAll(pageable).map(detallePago -> mapper.toDetallePagoRead(detallePago));
    }

    /**
     * Devuelve un Optional de DetallePagoRead que provee el repositorio CRUD
     * Para devolver un Page de DetallePagoReads se realiza el proceso de conversión de objetos con la interfaz DetallePagoMapper
     *
     * @param id
     * @return optional de DetallePagoRead
     */
    @Override
    public Optional<DetallePagoRead> getOne(Integer id) {
        return detallePagoCrudRepository.findById(id).map(detallePago -> mapper.toDetallePagoRead(detallePago));
    }

    /**
     * Devuelve un DetallePagoRead que provee el repositorio CRUD
     * Para devolver un Page de DetallePagoReads se realiza el proceso de conversión de objetos con la interfaz DetallePagoMapper
     * Guarda un detalle de pago
     *
     * @param detallePagoDTO
     * @return DetallePagoRead
     */
    @Override
    public DetallePagoRead save(DetallePagoDTO detallePagoDTO) {
        DetallePago detallePago = mapper.toDetallePago(detallePagoDTO);
        return mapper.toDetallePagoRead(detallePagoCrudRepository.save(detallePago));
    }

    /**
     * Eliminar la informacion mediante el repositorio CRUD
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        detallePagoCrudRepository.deleteById(id);
    }

    /**
     * No implementado
     *
     * @param detallePagoDTO
     * @param id
     * @return
     */
    @Override
    public DetallePagoRead update(DetallePagoDTO detallePagoDTO, Integer id) {
        return null;
    }
}
