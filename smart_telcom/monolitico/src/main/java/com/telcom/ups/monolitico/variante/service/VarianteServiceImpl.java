package com.telcom.ups.monolitico.variante.service;

import com.telcom.ups.data.dto.VarianteDTO;
import com.telcom.ups.data.entities.Parametro;
import com.telcom.ups.data.entities.Variante;
import com.telcom.ups.data.read.VarianteRead;
import com.telcom.ups.monolitico.parametro.repository.ParametroCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import com.telcom.ups.monolitico.variante.mapper.VarianteMapper;
import com.telcom.ups.monolitico.variante.repository.VarianteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VarianteServiceImpl implements VarianteService{

    @Autowired
    private VarianteCrudRepository varianteCrudRepository;

    @Autowired
    private ParametroCrudRepository parametroCrudRepository;

    @Autowired
    private VarianteMapper mapper;


    /**
     * Devuelve un Page de VarianteReads que provee el repositorio CRUD
     * Para devolver un Page de VarianteRead se realiza el proceso de conversión de objetos con la interfaz VarianteMapper
     *
     * @param pageable
     * @return page de VarianteRead
     */
    @Override
    public Page<VarianteRead> findAll(Pageable pageable) {
        return varianteCrudRepository.findAll(pageable).map(variante -> mapper.toVarianteRead(variante));
    }

    /**
     * Devuelve un Optional de VarianteRead que provee el repositorio CRUD
     * Para devolver un optional de VarianteRead se realiza el proceso de conversión de objetos con la interfaz VarianteMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesta de la petición
     *
     * @param id
     * @return optional de VarianteRead
     */
    @Override
    public Optional<VarianteRead> getOne(Integer id) {
        Optional<Variante> varianteDB = varianteCrudRepository.findById(id);
        if (varianteDB.isPresent()) {
            return varianteDB.map(variante -> mapper.toVarianteRead(variante));
        }
        throw new BadRequestException("Variante con id " + id + " no encontrada");
    }

    /**
     * Devuelve un VarianteRead que provee el repositorio CRUD
     * Para devolver un VarianteRead se realiza el proceso de conversión de objetos con la interfaz VarianteMapper
     * Para guardar una variante previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesta de la petición
     *
     * @param parametroId
     * @param varianteDTO
     * @return VarianteRead
     */
    @Override
    public VarianteRead save(Integer parametroId, VarianteDTO varianteDTO) {
        Optional<Parametro> parametroDB = parametroCrudRepository.findById(parametroId);
        if (parametroDB.isPresent()) {
            Optional<Variante> varianteDB = varianteCrudRepository.findByNombre(varianteDTO.getNombre());
            if (!varianteDB.isPresent()) {
                Variante variante = mapper.toVariante(varianteDTO);
                variante.setParametro(parametroDB.get());
                return mapper.toVarianteRead(varianteCrudRepository.save(variante));
            }
            throw new BadRequestException("Variante con nombre " + varianteDTO.getNombre() + " ya registrada");
        }
        throw new BadRequestException("Parametro con id " + parametroId + " no encontrado para relacionar con la variante");
    }

    /**
     * Devuelve un boolean al borrar la informacion mediante el repositorio CRUD
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesta de la petición
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean delete(Integer id) {
        try {
            Optional<Variante> varianteDB = varianteCrudRepository.findById(id);
            if (varianteDB.isPresent()) {
                varianteCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Variante con id " + id + " no encontrada para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("La variante que intenta eliminar cuenta con objetos relacionados. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso la variante no debe contar con objetos relacionados al mismo.");
        }
    }

    /**
     * Devuelve un VarianteRead que provee el repositorio CRUD
     * Para devolver un VarianteRead se realiza el proceso de conversión de objetos con la interfaz VarianteMapper
     * Para actualizar una variante previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesta de la petición
     *
     * @param parametroId
     * @param varianteDTO
     * @param id
     * @return VarianteRead
     */
    @Override
    public VarianteRead update(Integer parametroId, VarianteDTO varianteDTO, Integer id) {
        Optional<Variante> varianteDB = varianteCrudRepository.findById(id);
        if (varianteDB.isPresent()) {
            Optional<Parametro> parametroDB = parametroCrudRepository.findById(parametroId);
            if (parametroDB.isPresent()) {
                Variante newVariante = mapper.toVariante(varianteDTO);
                Variante var = varianteDB.get();
                var.setNombre(newVariante.getNombre());
                var.setData(newVariante.getData());
                var.setParametro(parametroDB.get());
                return mapper.toVarianteRead(varianteCrudRepository.save(var));
            }
            throw new BadRequestException("Parametro con id " + parametroId + " no encontrado para relacionar con la variante");
        }
        throw new BadRequestException("Variante con id " + id + " no encontrada para actualizar");
    }
}
