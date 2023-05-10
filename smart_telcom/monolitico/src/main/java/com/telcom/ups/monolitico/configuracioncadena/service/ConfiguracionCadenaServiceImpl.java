package com.telcom.ups.monolitico.configuracioncadena.service;

import com.telcom.ups.data.dto.ConfiguracionCadenaDTO;
import com.telcom.ups.data.entities.ConfiguracionCadena;
import com.telcom.ups.data.entities.DispositivoTipo;
import com.telcom.ups.data.info.ConfiguracionCadenaInfo;
import com.telcom.ups.data.read.ConfiguracionCadenaRead;
import com.telcom.ups.monolitico.configuracioncadena.mapper.ConfiguracionCadenaMapper;
import com.telcom.ups.monolitico.configuracioncadena.repository.ConfiguracionCadenaCrudRepository;
import com.telcom.ups.monolitico.dispositivotipo.repository.DispositivoTipoCrudRepository;
import com.telcom.ups.monolitico.util.data.Conversion;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.apache.commons.codec.DecoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfiguracionCadenaServiceImpl implements ConfiguracionCadenaService{

    @Autowired
    private ConfiguracionCadenaCrudRepository configuracionCadenaCrudRepository;

    @Autowired
    private DispositivoTipoCrudRepository dispositivoTipoCrudRepository;

    @Autowired
    private ConfiguracionCadenaMapper mapper;

    /**
     * Devuelve un Page de ConfiguracionCadenaInfos que provee el repositorio CRUD
     * Para devolver un Page de ConfiguracionCadenaInfos se realiza el proceso de conversión de objetos con la interfaz ConfiguracionCadenaMapper
     *
     * @param pageable
     * @return page de ConfiguracionCadenaInfos
     */
    @Override
    public Page<ConfiguracionCadenaInfo> findAll(Pageable pageable) {
        return configuracionCadenaCrudRepository.findAll(pageable).map(configuracionCadena -> mapper.toConfiguracionCadenaInfo(configuracionCadena));
    }

    /**
     * Devuelve un Optional de ConfiguracionCadenaRead que provee el repositorio CRUD
     * Para devolver un Optional de ConfiguracionCadenaRead se realiza el proceso de conversión de objetos con la interfaz ConfiguracionCadenaMapper
     *
     * @param id
     * @return optional de ConfiguracionCadenaRead
     */
    @Override
    public Optional<ConfiguracionCadenaRead> getOne(Integer id) {
        Optional<ConfiguracionCadena> configuracionCadenaDB = configuracionCadenaCrudRepository.findById(id);
        if (configuracionCadenaDB.isPresent()) {
            return configuracionCadenaDB.map(configuracionCadena -> mapper.toConfiguracionCadenaRead(configuracionCadena));
        }
        throw new BadRequestException("ConfiguracionCadena con id " + id + " no encontrada");
    }

    /**
     * Devuelve un ConfiguracionCadenaRead que provee el repositorio CRUD
     * Para devolver una ConfiguracionCadenaRead se realiza el proceso de conversión de objetos con la interfaz ConfiguracionCadenaMapper
     * Para guardar una cadena de configuración por conversion de decimal a hexadecimal y base64 previo al proceso, se realizan las validaciones
     * para el almacén de datos correctos respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param dispositivoTipoId
     * @param configuracionCadenaDTO
     * @return ConfiguracionCadenaRead
     * @throws DecoderException
     */
    @Override
    public ConfiguracionCadenaRead save(Integer dispositivoTipoId, ConfiguracionCadenaDTO configuracionCadenaDTO) throws DecoderException {
        Optional<DispositivoTipo> dispositivoTipoDB = dispositivoTipoCrudRepository.findById(dispositivoTipoId);
        if (dispositivoTipoDB.isPresent()) {
            Optional<ConfiguracionCadena> configuracionCadenaDBNombre = configuracionCadenaCrudRepository.findByNombre(configuracionCadenaDTO.getNombre());
            if (!configuracionCadenaDBNombre.isPresent()) {
                if (configuracionCadenaDTO.getData().length() <= 9) {
                    ConfiguracionCadena configuracionCadena = mapper.toConfiguracionCadena(configuracionCadenaDTO);
                    configuracionCadena.setHexadecimal(Conversion.toHexadecimal(configuracionCadena.getData()));
                    configuracionCadena.setBase64(Conversion.toBase64(configuracionCadena.getData()));
                    configuracionCadena.setDispositivoTipo(dispositivoTipoDB.get());
                    return mapper.toConfiguracionCadenaRead(configuracionCadenaCrudRepository.save(configuracionCadena));
                }
                throw new BadRequestException("La longitud de la data de la cadena debe ser menor o igual a 9 caracteres");
            }
            throw new BadRequestException("ConfiguracionCadena con nombre " + configuracionCadenaDTO.getNombre() + " ya esta registrada");
        }
        throw new BadRequestException("DispositivoTipo con id " + dispositivoTipoId + " no encontrado para relacionar con la cadena de configuracion");
    }

    /**
     * Devuelve un ConfiguracionCadenaRead que provee el repositorio CRUD
     * Para devolver una ConfiguracionCadenaRead se realiza el proceso de conversión de objetos con la interfaz ConfiguracionCadenaMapper
     * Para guardar una cadena de configuración por conversion de hexadecimal a base64 previo al proceso, se realizan las validaciones
     * para el almacén de datos correctos respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param dispositivoTipoId
     * @param configuracionCadenaDTO
     * @return ConfiguracionCadenaRead
     * @throws DecoderException
     */
    @Override
    public ConfiguracionCadenaRead saveBase64OnlyFromHexa(Integer dispositivoTipoId, ConfiguracionCadenaDTO configuracionCadenaDTO) throws DecoderException {
        Optional<DispositivoTipo> dispositivoTipoDB = dispositivoTipoCrudRepository.findById(dispositivoTipoId);
        if (dispositivoTipoDB.isPresent()) {
            Optional<ConfiguracionCadena> configuracionCadenaDBNombre = configuracionCadenaCrudRepository.findByNombre(configuracionCadenaDTO.getNombre());
            if (!configuracionCadenaDBNombre.isPresent()) {
                ConfiguracionCadena configuracionCadena = new ConfiguracionCadena();
                configuracionCadena.setData("----");
                configuracionCadena.setNombre(configuracionCadenaDTO.getNombre());
                configuracionCadena.setHexadecimal(configuracionCadenaDTO.getData());
                configuracionCadena.setBase64(Conversion.toBase64FromCadenaHex(configuracionCadena.getHexadecimal()));
                configuracionCadena.setDispositivoTipo(dispositivoTipoDB.get());
                return mapper.toConfiguracionCadenaRead(configuracionCadenaCrudRepository.save(configuracionCadena));
            }
            throw new BadRequestException("ConfiguracionCadena con nombre " + configuracionCadenaDTO.getNombre() + " ya esta registrada");
        }
        throw new BadRequestException("DispositivoTipo con id " + dispositivoTipoId + " no encontrado para relacionar con la cadena de configuracion");
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
            Optional<ConfiguracionCadena> configuracionCadenaDB = configuracionCadenaCrudRepository.findById(id);
            if (configuracionCadenaDB.isPresent()) {
                configuracionCadenaCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("ConfiguracionCadena con id " + id + " no encontrada para eliminar");
        } catch (Exception exception){
            throw new BadRequestException("La cadena de configuración que intenta eliminar presenta errores en el proceso de eliminación.");
        }
    }

    /**
     * Devuelve una ConfiguracionCadenaRead que provee el repositorio CRUD
     * Para devolver una ConfiguracionCadenaRead se realiza el proceso de conversión de objetos con la interfaz ConfiguracionCadenaMapper
     * Para actualizar una cadena de configuracion previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param dispositivoTipoId
     * @param configuracionCadenaDTO
     * @param id
     * @return ConfiguracionCadenaRead
     * @throws DecoderException
     */
    @Override
    public ConfiguracionCadenaRead update(Integer dispositivoTipoId, ConfiguracionCadenaDTO configuracionCadenaDTO, Integer id) throws DecoderException {
        Optional<ConfiguracionCadena> configuracionCadenaDB = configuracionCadenaCrudRepository.findById(id);
        if (configuracionCadenaDB.isPresent()) {
            Optional<DispositivoTipo> dispositivoTipoDB = dispositivoTipoCrudRepository.findById(dispositivoTipoId);
            if (dispositivoTipoDB.isPresent()) {
                ConfiguracionCadena newConfiguracionCadena = mapper.toConfiguracionCadena(configuracionCadenaDTO);
                ConfiguracionCadena conf = configuracionCadenaDB.get();
                conf.setNombre(newConfiguracionCadena.getNombre());
                conf.setData(newConfiguracionCadena.getData());
                conf.setHexadecimal(Conversion.toHexadecimal(newConfiguracionCadena.getData()));
                conf.setBase64(Conversion.toBase64(newConfiguracionCadena.getData()));
                conf.setDispositivoTipo(dispositivoTipoDB.get());
                return mapper.toConfiguracionCadenaRead(configuracionCadenaCrudRepository.save(conf));
            }
            throw new BadRequestException("DispositivoTipo con id " + dispositivoTipoId + " no encontrado para relacionar con la cadena de configuracion");
        }
        throw new BadRequestException("ConfiguracionCadena con id " + id + " no encontrada para actualizar");
    }

    /**
     * Devuelve una ConfiguracionCadenaRead que provee el repositorio CRUD
     * Para devolver una ConfiguracionCadenaRead se realiza el proceso de conversión de objetos con la interfaz ConfiguracionCadenaMapper
     * Para actualizar una cadena de configuracion de manera manual previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param dispositivoTipoId
     * @param configuracionCadenaDTO
     * @param id
     * @return ConfiguracionCadenaRead
     * @throws DecoderException
     */
    @Override
    public ConfiguracionCadenaRead updateManual(Integer dispositivoTipoId, ConfiguracionCadenaDTO configuracionCadenaDTO, Integer id) throws DecoderException {
        Optional<ConfiguracionCadena> configuracionCadenaDB = configuracionCadenaCrudRepository.findById(id);
        if (configuracionCadenaDB.isPresent()) {
            Optional<DispositivoTipo> dispositivoTipoDB = dispositivoTipoCrudRepository.findById(dispositivoTipoId);
            if (dispositivoTipoDB.isPresent()) {
                ConfiguracionCadena newConfiguracionCadena = mapper.toConfiguracionCadena(configuracionCadenaDTO);
                ConfiguracionCadena conf = configuracionCadenaDB.get();
                conf.setNombre(newConfiguracionCadena.getNombre());
                conf.setData("----");
                conf.setHexadecimal(newConfiguracionCadena.getData());
                conf.setBase64(Conversion.toBase64FromCadenaHex(conf.getHexadecimal()));
                conf.setDispositivoTipo(dispositivoTipoDB.get());
                return mapper.toConfiguracionCadenaRead(configuracionCadenaCrudRepository.save(conf));
            }
            throw new BadRequestException("DispositivoTipo con id " + dispositivoTipoId + " no encontrado para relacionar con la cadena de configuracion");
        }
        throw new BadRequestException("ConfiguracionCadena con id " + id + " no encontrada para actualizar");
    }

    /**
     * Devuelve un Page de ConfiguracionCadenaInfos que provee el repositorio CRUD
     * Para devolver un page de ConfiguracionCadenaInfos se realiza el proceso de conversión de objetos con la interfaz ConfiguracionCadenaMapper
     *
     * @param idTipo
     * @param pageable
     * @return page de ConfiguracionCadenaInfos
     */
    @Override
    public Page<ConfiguracionCadenaInfo> getAllByDispositivoTipo(Integer idTipo, Pageable pageable) {
        return configuracionCadenaCrudRepository.getAllByTipoDispositivo(idTipo, pageable).map(configuracionCadena -> mapper.toConfiguracionCadenaInfo(configuracionCadena));
    }
}
