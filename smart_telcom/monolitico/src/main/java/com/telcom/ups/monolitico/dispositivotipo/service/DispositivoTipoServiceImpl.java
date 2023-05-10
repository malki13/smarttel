package com.telcom.ups.monolitico.dispositivotipo.service;

import com.telcom.ups.data.dto.DispositivoTipoDTO;
import com.telcom.ups.data.entities.DispositivoTipo;
import com.telcom.ups.data.info.DispositivoTipoInfo;
import com.telcom.ups.data.info.DispositivoTipoInfoOnly;
import com.telcom.ups.monolitico.dispositivotipo.mapper.DispositivoTipoMapper;
import com.telcom.ups.monolitico.dispositivotipo.repository.DispositivoTipoCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DispositivoTipoServiceImpl implements DispositivoTipoService{

    @Autowired
    private DispositivoTipoCrudRepository dispositivoTipoCrudRepository;

    @Autowired
    private DispositivoTipoMapper mapper;

    /**
     * Devuelve un Page de DispositivoTipoInfoOnlies que provee el repositorio CRUD
     * Para devolver un Page de DispositivoTipoInfoOnlies se realiza el proceso de conversión de objetos con la interfaz DispositivoTipoMapper
     *
     * @param pageable
     * @return page de DispositivoTipoInfoOnlies
     */
    @Override
    public Page<DispositivoTipoInfoOnly> findAll(Pageable pageable) {
        return dispositivoTipoCrudRepository.findAll(pageable).map(dispositivoTipo -> mapper.toDispositivoTipoInfoOnly(dispositivoTipo));
    }

    /**
     * Devuelve un Optional de DispositivoTipoInfo que provee el repositorio CRUD
     * Para devolver un optional de DispositivoTipoInfo se realiza el proceso de conversión de objetos con la interfaz DispositivoTipoMapper
     *
     * @param id
     * @return optional de DispositivoTipoInfo
     */
    @Override
    public Optional<DispositivoTipoInfo> getOne(Integer id) {
        Optional<DispositivoTipo> dispositivoTipoDB = dispositivoTipoCrudRepository.findById(id);
        if (dispositivoTipoDB.isPresent()) {
            return dispositivoTipoDB.map(dispositivoTipo -> mapper.toDispositivoTipoInfo(dispositivoTipo));
        }
        throw new BadRequestException("DispositivoTipo con id " + id + " no encontrado");
    }

    /**
     * Devuelve un DispositivoTipoDTO que provee el repositorio CRUD
     * Para devolver DispositivoTipoDTO se realiza el proceso de conversión de objetos con la interfaz DispositivoTipoMapper
     * Para guardar un tipo de dispositivo previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param dispositivoTipoDTO
     * @return DispositivoTipoDTO
     */
    @Override
    public DispositivoTipoDTO save(DispositivoTipoDTO dispositivoTipoDTO) {
        Optional<DispositivoTipo> dispositivoTipoCodigo = dispositivoTipoCrudRepository.findByCodigo(dispositivoTipoDTO.getCodigo());
        if (!dispositivoTipoCodigo.isPresent()) {
            Optional<DispositivoTipo> dispositivoTipoNombre = dispositivoTipoCrudRepository.findByNombre(dispositivoTipoDTO.getNombre());
            if (!dispositivoTipoNombre.isPresent()) {
                DispositivoTipo dispositivoTipo = mapper.toDispositivoTipo(dispositivoTipoDTO);
                return mapper.toDispositivoTipoDto(dispositivoTipoCrudRepository.save(dispositivoTipo));
            }
            throw new BadRequestException("DispositivoTipo con nombre " + dispositivoTipoDTO.getNombre() + " ya esta registrado");
        }
        throw new BadRequestException("DispositivoTipo con codigo " + dispositivoTipoDTO.getCodigo() + " ya esta registrado");
    }

    /**
     * Devuelve un boolean al borrar la informacion mediante el repositorio CRUD
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        try {
            Optional<DispositivoTipo> dispositivoTipoDB = dispositivoTipoCrudRepository.findById(id);
            if (dispositivoTipoDB.isPresent()) {
                dispositivoTipoCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("DispositivoTipo con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El tipo de dispositivo que intenta eliminar cuenta con dispositivos y configuraciones generadas, previo a esto, para que " +
                    "el proceso de eliminación sea exitoso el tipo de dispositivo no debe tener dispositivos y configuraciones generadas con este tipo de dispositivo.");
        }
    }

    /**
     * Devuelve un DispositivoTipoDTO que provee el repositorio CRUD
     * Para devolver DispositivoTipoDTO se realiza el proceso de conversión de objetos con la interfaz DispositivoTipoMapper
     * Para actualizar un tipo de dispositivo previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param dispositivoTipoDTO
     * @param id
     * @return DispositivoTipoDTO
     */
    @Override
    public DispositivoTipoDTO update(DispositivoTipoDTO dispositivoTipoDTO, Integer id) {
        Optional<DispositivoTipo> dispositivoTipoDB = dispositivoTipoCrudRepository.findById(id);
        if (dispositivoTipoDB.isPresent()) {
            DispositivoTipo dis = dispositivoTipoDB.get();
            DispositivoTipo newDispositivoTipo = mapper.toDispositivoTipo(dispositivoTipoDTO);
            dis.setCodigo(newDispositivoTipo.getCodigo());
            dis.setNombre(newDispositivoTipo.getNombre());
            dis.setDescripcion(newDispositivoTipo.getDescripcion());
            dis.setUpdatedAt(LocalDateTime.now());
            return mapper.toDispositivoTipoDto(dispositivoTipoCrudRepository.save(dis));
        }
        throw new BadRequestException("DispositivoTipo con id " + id + " no encontrado para actualizar");
    }
}
