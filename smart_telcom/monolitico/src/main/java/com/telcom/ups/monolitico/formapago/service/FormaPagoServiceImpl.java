package com.telcom.ups.monolitico.formapago.service;

import com.telcom.ups.data.dto.FormaPagoDTO;
import com.telcom.ups.data.entities.FormaPago;
import com.telcom.ups.data.read.FormaPagoRead;
import com.telcom.ups.monolitico.formapago.mapper.FormaPagoMapper;
import com.telcom.ups.monolitico.formapago.repository.FormaPagoCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormaPagoServiceImpl implements FormaPagoService{

    @Autowired
    private FormaPagoCrudRepository formaPagoCrudRepository;

    @Autowired
    private FormaPagoMapper mapper;

    /**
     * Devuelve un Page de FormaPagoReads que provee el repositorio CRUD
     * Para devolver un Page de FormaPagoReads se realiza el proceso de conversión de objetos con la interfaz FormaPagoMapper
     *
     * @param pageable
     * @return page de FormaPagoReads
     */
    @Override
    public Page<FormaPagoRead> findAll(Pageable pageable) {
        return formaPagoCrudRepository.findAll(pageable).map(formaPago -> mapper.toFormaPagoRead(formaPago));
    }

    /**
     * Devuelve un Optional de FormaPagoRead que provee el repositorio CRUD
     * Para devolver un optional de FormaPagoRead se realiza el proceso de conversión de objetos con la interfaz FormaPagoMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesta de la petición
     *
     * @param id
     * @return optional de FormaPagoRead
     */
    @Override
    public Optional<FormaPagoRead> getOne(Integer id) {
        Optional<FormaPago> formaPagoDB = formaPagoCrudRepository.findById(id);
        if (formaPagoDB.isPresent()) {
            return formaPagoDB.map(formaPago -> mapper.toFormaPagoRead(formaPago));
        }
        throw new BadRequestException("FormaPago con id " + id + " no encontrada");
    }

    /**
     * Devuelve una FormaPagoRead que provee el repositorio CRUD
     * Para devolver una FormaPagoRead se realiza el proceso de conversión de objetos con la interfaz FormaPagoMapper
     * Para guardar una forma de pago se realizan las validaciones para el almacén de datos correctos respondiendo con una
     * excepcion de BadRequestException como respuesra de la petición
     *
     * @param formaPagoDTO
     * @return FormaPagoRead
     */
    @Override
    public FormaPagoRead save(FormaPagoDTO formaPagoDTO) {
        Optional<FormaPago> formaPagoDBNombre = formaPagoCrudRepository.findByNombre(formaPagoDTO.getNombre());
        if (!formaPagoDBNombre.isPresent()) {
            FormaPago formaPago = mapper.toFormaPago(formaPagoDTO);
            return mapper.toFormaPagoRead(formaPagoCrudRepository.save(formaPago));
        }
        throw new BadRequestException("FormaPago con nombre " + formaPagoDTO.getNombre() + " ya esta registrada");
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
            Optional<FormaPago> formaPagoDB = formaPagoCrudRepository.findById(id);
            if (formaPagoDB.isPresent()) {
                formaPagoCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("FormaPago con id " + id + " no encontrada para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("La forma de pago que intenta eliminar cuenta con facturas generadas. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso la forma de pago no debe contar con facturas generadas.");
        }
    }

    /**
     * Devuelve una FormaPagoRead que provee el repositorio CRUD
     * Para devolver una FormaPagoRead se realiza el proceso de conversión de objetos con la interfaz FormaPagoMapper
     * Para actualizar una forma de pago se realizan las validaciones para el almacén de datos correctos respondiendo con una
     * excepcion de BadRequestException como respuesra de la petición
     *
     * @param formaPagoDTO
     * @param id
     * @return FormaPagoRead
     */
    @Override
    public FormaPagoRead update(FormaPagoDTO formaPagoDTO, Integer id) {
        Optional<FormaPago> formaPagoDB = formaPagoCrudRepository.findById(id);
        if (formaPagoDB.isPresent()) {
            FormaPago newFormaPago = mapper.toFormaPago(formaPagoDTO);
            FormaPago fp = formaPagoDB.get();
            fp.setNombre(newFormaPago.getNombre());
            return mapper.toFormaPagoRead(formaPagoCrudRepository.save(fp));
        }
        throw new BadRequestException("FormaPago con id " + id + " no encontrada para actualizar");
    }
}
