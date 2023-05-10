package com.telcom.ups.monolitico.zonaqueutemp.service;

import com.telcom.ups.data.dto.ComunicacionDTO;
import com.telcom.ups.data.dto.ZonaQueueTempDTO;
import com.telcom.ups.data.dto.ZonaQueueTempOneDTO;
import com.telcom.ups.data.entities.*;
import com.telcom.ups.data.info.ZonaQueueTempInfo;
import com.telcom.ups.data.read.ZonaQueueRead;
import com.telcom.ups.data.read.ZonaQueueReadTemp;
import com.telcom.ups.monolitico.dispositivo.repository.DeviceCrudRepository;
import com.telcom.ups.monolitico.dispositivoqueuetemp.repository.DispositivoQueueTempCrudRepository;
import com.telcom.ups.monolitico.usuario.repository.UsuarioCrudRepository;
import com.telcom.ups.monolitico.util.chirpstack.AuthorizationHeader;
import com.telcom.ups.monolitico.util.chirpstack.Url;
import com.telcom.ups.monolitico.util.chirpstack.authentication.InternalServiceChirpstack;
import com.telcom.ups.monolitico.util.chirpstack.connection.TestConnection;
import com.telcom.ups.monolitico.util.chirpstack.modelo.DeviceQueueItem;
import com.telcom.ups.monolitico.util.chirpstack.modelo.DeviceQueueItemClass;
import com.telcom.ups.monolitico.util.data.Conversion;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import com.telcom.ups.monolitico.zona.repository.ZonaCrudRepository;
import com.telcom.ups.monolitico.zonaqueue.mapper.ZonaQueueMapper;
import com.telcom.ups.monolitico.zonaqueue.repository.ZonaQueueCrudRepository;
import com.telcom.ups.monolitico.zonaqueutemp.mapper.ZonaQueueTempMapper;
import com.telcom.ups.monolitico.zonaqueutemp.repository.ZonaQueueTempCrudRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ZonaQueueTempServiceImpl implements ZonaQueueTempService {

    @Autowired
    private ZonaQueueTempCrudRepository zonaQueueTempCrudRepository;

    @Autowired
    private ZonaQueueCrudRepository zonaQueueCrudRepository;

    @Autowired
    private ZonaCrudRepository zonaCrudRepository;

    @Autowired
    private DeviceCrudRepository deviceCrudRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ZonaQueueTempMapper zonaQueueTempMapper;

    @Autowired
    private ZonaQueueMapper zonaQueueMapper;

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private TestConnection testConnection;

    @Autowired
    private DispositivoQueueTempCrudRepository dispositivoQueueTempCrudRepository;

    @Autowired
    private InternalServiceChirpstack internalServiceChirpstack;

    /**
     * Devuelve un Page de ZonaQueueReadTemps que provee el repositorio
     * Para devolver un Page de ZonaQueueReadTemps se realiza el proceso de conversión de objetos con la interfaz ZonaQueueTempMapper
     *
     * @param pageable
     * @return page de ZonaQueueReadTemp
     */
    @Override
    public Page<ZonaQueueReadTemp> findAll(Pageable pageable) {
        return null;
    }

    /**
     * Devuelve un Page de ZonaQueueReads que provee el repositorio
     * Para devolver un Page de ZonaQueueRead se realiza el proceso de conversión de objetos con la interfaz ZonaQueueMapper
     *
     * @param pageable
     * @return page de ZonaQueueReads
     */
    @Override
    public Page<ZonaQueueRead> findAllLogs(Pageable pageable) {
        return zonaQueueCrudRepository.findAll(pageable).map(zonaQueue -> zonaQueueMapper.toZonaQueueRead(zonaQueue));
    }

    /**
     * Devuelve un Page de ZonaQueueReadTemps que provee el repositorio
     * Para devolver un Page de ZonaQueueReadTemps se realiza el proceso de conversión de objetos con la interfaz ZonaQueueTempMapper
     *
     * @param pageable
     * @return page de ZonaQueueReadTemp
     */
    @Override
    public Page<ZonaQueueTempInfo> getAllJob(Pageable pageable) {
        return zonaQueueTempCrudRepository.findAll(pageable).map(zonaQueueTemp -> zonaQueueTempMapper.toZonaQueueTempInfo(zonaQueueTemp));
    }

    /**
     * Devuelve un Page de ZonaQueueReads que provee el repositorio
     * Para devolver un Page de ZonaQueueRead se realiza el proceso de conversión de objetos con la interfaz ZonaQueueMapper
     * Obtiene los logs de quien encolo el dato de bajada en la zona
     *
     * @param idZona
     * @param pageable
     * @return page de ZonaQueueReads
     */
    @Override
    public Page<ZonaQueueRead> findAllLogsByZona(Integer idZona, Pageable pageable) {
        return zonaQueueCrudRepository.getAllByZona(idZona, pageable).map(zonaQueue -> zonaQueueMapper.toZonaQueueRead(zonaQueue));
    }

    /**
     * Devuelve un Page de ZonaQueueReadTemps que provee el repositorio
     * Para devolver un Page de ZonaQueueReadTemps se realiza el proceso de conversión de objetos con la interfaz ZonaQueueTempMapper
     * Obtiene la cola de comunicación de una zona
     *
     * @param idZona
     * @param pageable
     * @return page de ZonaQueueTempInfo
     */
    @Override
    public Page<ZonaQueueTempInfo> findAllColaByZona(Integer idZona, Pageable pageable) {
        return zonaQueueTempCrudRepository.getAllByZona(idZona, pageable).map(zonaQueueTemp -> zonaQueueTempMapper.toZonaQueueTempInfo(zonaQueueTemp));
    }

    /**
     * No implementado
     *
     * @param id
     * @return
     */
    @Override
    public Optional<ZonaQueueReadTemp> getOne(Integer id) {
        return Optional.empty();
    }

    /**
     * Devuelve un ZonaQueueReadTemp que provee el repositorio
     * Para devolver un Page de ZonaQueueReadTemp se realiza el proceso de conversión de objetos con la interfaz ZonaQueueTempMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return ZonaQueueReadTemp
     */
    @Override
    public ZonaQueueReadTemp findByZonaId(Integer id) {
        List<ZonaQueueTemp> datos = zonaQueueTempCrudRepository.findAllByZonaId(id);
        if (datos.size() > 0) {
            ZonaQueueTemp zonaQueue = datos.get(0);
            return zonaQueueTempMapper.toZonaQueueReadTemp(zonaQueue);
        }
        throw new BadRequestException("Zona con id " + id + " no encontrada para verificar cola de bajada");
    }

    /**
     * Devuelve un ComunicacionDTO en base a una consulta al repositorio
     * El metodo se usa para saber en que estado se encuentra el proceso de comunicación
     *
     * @param id
     * @return ComunicacionDTO
     */
    @Override
    public ComunicacionDTO geEstado(Integer id) {
        List<ZonaQueueTemp> datos = zonaQueueTempCrudRepository.findAllByZonaId(id);
        if (datos.size() > 0) {
            return new ComunicacionDTO("PROCESANDO");
        }
        return new ComunicacionDTO("OK");
    }

    /**
     * Devuelve un boolean que provee el repositorio
     * Para encolar datos por zona previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición.
     * El metodo se comunica con el backend de chirpstack para el proceso de encolamiento zonal validando que los dispositivos se encuentren
     * previamente activos y que los mismos cuenten con su creacion tanto de lado del backend de chirpstack como de lado del backend de telcom caso contrario
     * el proceso de encolación no se puede realizar.
     *
     * @param zonaQueueTempDTO
     * @param usuarioId
     * @param zonaId
     * @return boolean
     * @throws ParseException
     */
    @Override
    @Transactional
    public boolean save(ZonaQueueTempDTO zonaQueueTempDTO, Integer usuarioId, Integer zonaId) throws ParseException {
        if (testConnection.testConnection()) {
            if (testConnection.testConnection()) {
                Optional<Usuario> usuarioDB = usuarioCrudRepository.findById(usuarioId);
                if (usuarioDB.isPresent()) {
                    Optional<Zona> zonaDB = zonaCrudRepository.findById(zonaId);
                    if (zonaDB.isPresent()) {
                        List<ZonaQueueTemp> datos = zonaQueueTempCrudRepository.findAllByZonaId(zonaId);
                        if (datos.isEmpty()) {
                            List<Dispositivo> disps = deviceCrudRepository.getAllByZonaId(zonaId);
                            if (disps.size() > 0) {

                                //VERIFICO QUE NINGUNO DE LOS DISPOSITIVOS TENGA UNA COMUNICACION INDIVIDUAL
                                for (int i = 0; i < disps.size(); i++) {
                                    Dispositivo dispositivoDB = disps.get(i);
                                    Optional<DispositivoQueueTemp> dispositivoQueueTempDB = dispositivoQueueTempCrudRepository.findByDeveui(dispositivoDB.getDeveui());
                                    if (dispositivoQueueTempDB.isPresent())
                                        throw new BadRequestException("Dispositivo con deveui " + dispositivoDB.getDeveui() + " actualmente esta encolado para una comunicacion individual en " + "curso para continuar con el proceso debe esperar a que este finalice");
                                }


                                //VERIFICO QUE TODOS LOS DISPOSITIVOS EXISTAN EN CHIRPSTACK
                                for (int i = 0; i < disps.size(); i++) {
                                    Dispositivo dispositivoDB = disps.get(i);
                                    try {
                                        ResponseEntity<String> resDeviceChirpstackExiste = restTemplate.exchange(
                                                Url.urlDevice + "/" + dispositivoDB.getDeveui(),
                                                HttpMethod.GET,
                                                AuthorizationHeader.getRequestEntityHeaders(internalServiceChirpstack.getTokenCS()),
                                                String.class
                                        );
                                        if (resDeviceChirpstackExiste.getStatusCode() != HttpStatus.OK)
                                            throw new BadRequestException("Dispositivo con deveui " + dispositivoDB.getDeveui() + " no existe en chirpstack para proceder con la encolacion");
                                    } catch (HttpClientErrorException exception) {
                                        throw new BadRequestException("Dispositivo con deveui " + dispositivoDB.getDeveui() + " no existe en chirpstack para proceder con la encolacion");
                                    }
                                }

                                //ENCOLO LA INFORMACION EN CADA DISPOSITIVO QUE TENGA UNA ACTIVIDAD COSTANTE
                                for (int i = 0; i < disps.size(); i++) {
                                    Dispositivo dispositivoDB = disps.get(i);
                                    ResponseEntity<String> resDeviceChirpstackExiste = restTemplate.exchange(
                                            Url.urlDevice + "/" + dispositivoDB.getDeveui(),
                                            HttpMethod.GET,
                                            AuthorizationHeader.getRequestEntityHeaders(internalServiceChirpstack.getTokenCS()),
                                            String.class
                                    );
                                    if (resDeviceChirpstackExiste.getStatusCode() == HttpStatus.OK) {
                                        JSONObject object = new JSONObject(resDeviceChirpstackExiste.getBody());
                                        if (!object.isNull("lastSeenAt") && Conversion.fechaValida(object.getString("lastSeenAt"))) {
                                            DeviceQueueItem data = new DeviceQueueItem();
                                            DeviceQueueItemClass deviceQueueItem = new DeviceQueueItemClass();
                                            deviceQueueItem.setDevEUI(dispositivoDB.getDeveui());
                                            deviceQueueItem.setConfirmed(true);
                                            deviceQueueItem.setfCnt(0);
                                            deviceQueueItem.setfPort(zonaQueueTempDTO.getfPort());
                                            deviceQueueItem.setData(zonaQueueTempDTO.getData());
                                            data.setDeviceQueueItem(deviceQueueItem);
                                            ResponseEntity<String> resDeviceChirpstack = restTemplate.exchange(Url.urlDevice + "/" + dispositivoDB.getDeveui() + "/queue", HttpMethod.POST, AuthorizationHeader.getRequestEntity(data, internalServiceChirpstack.getTokenCS()), String.class);
                                            if (resDeviceChirpstack.getStatusCode() == HttpStatus.OK) {
                                                JSONObject res = new JSONObject(resDeviceChirpstack.getBody());
                                                Integer fcnt = res.getInt("fCnt");

                                                //GUARDO COLA DE ZONA LOGS
                                                ZonaQueue zonaQueue = new ZonaQueue();
                                                zonaQueue.setDeveui(dispositivoDB.getDeveui());
                                                zonaQueue.setPayload(zonaQueueTempDTO.getData());
                                                zonaQueue.setfCnt(fcnt);
                                                zonaQueue.setfPort(zonaQueueTempDTO.getfPort());
                                                zonaQueue.setFecha(LocalDateTime.now());
                                                zonaQueue.setZona(zonaDB.get());
                                                zonaQueue.setUsuario(usuarioDB.get());
                                                zonaQueueCrudRepository.save(zonaQueue);

                                                //GUARDO COLA DE ZONA TEMPORAL
                                                ZonaQueueTemp zonaQueueTemp = new ZonaQueueTemp();
                                                zonaQueueTemp.setDeveui(dispositivoDB.getDeveui());
                                                zonaQueueTemp.setPayload(zonaQueueTempDTO.getData());
                                                zonaQueueTemp.setfCnt(fcnt);
                                                zonaQueueTemp.setfPort(zonaQueueTempDTO.getfPort());
                                                zonaQueueTemp.setFecha(LocalDateTime.now());
                                                zonaQueueTemp.setCont(1);
                                                zonaQueueTemp.setZonaId(zonaDB.get().getIden());
                                                zonaQueueTempCrudRepository.save(zonaQueueTemp);
                                            }
                                        }
                                    }
                                }
                                return true;
                            }
                            throw new BadRequestException("Zona con id " + zonaId + " no posee medidores para proceder al proceso de comunicacion");
                        }
                        throw new BadRequestException("Zona con id " + zonaId + " ya tiene medidores con datos en cola, debe esperar a que se complete el proceso de comunicacion en curso");
                    }
                    throw new BadRequestException("Zona con id " + zonaId + " no encontrada para proceder a la comunicacion");
                }
                throw new BadRequestException("Usuario con id " + usuarioId + " no encontrada para proceder a la comunicacion");
            }
            throw new BadRequestException("No se tiene acceso a chirpstack para proceder con la comunicacion");
        }
        throw new BadRequestException("No se tiene acceso a chirpstack para proceder a encolar datos");
    }

    /**
     * Devuelve un boolean al reencolar la informacion mediante el repositorio
     * Para reencolar datos en el dispositivo que pertenece a la zona encolada previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición.
     * El metodo se comunica con el backend de chirpstack para el proceso de reencolamiento zonal
     * el proceso de encolación no se puede realizar.
     *
     * @param zonaQueueTempOneDTO
     * @param usuarioId
     * @param zonaId
     * @return boolean
     */
    @Override
    @Transactional
    public boolean backEncolarMedidor(ZonaQueueTempOneDTO zonaQueueTempOneDTO, Integer usuarioId, Integer zonaId) {
        if (testConnection.testConnection()) {
            Optional<Usuario> usuarioDB = usuarioCrudRepository.findById(usuarioId);
            if (usuarioDB.isPresent()) {
                Optional<Zona> zonaDB = zonaCrudRepository.findById(zonaId);
                if (zonaDB.isPresent()) {
                    Optional<Dispositivo> dispositivoDB = deviceCrudRepository.findByDeveui(zonaQueueTempOneDTO.getDevEUI());
                    if (dispositivoDB.isPresent()) {
                        Optional<ZonaQueueTemp> zonaQueueTempDB = zonaQueueTempCrudRepository.findByDeveui(zonaQueueTempOneDTO.getDevEUI());
                        if (zonaQueueTempDB.isPresent()) {
                            ZonaQueueTemp zonaQueueTemp = zonaQueueTempDB.get();
                            Dispositivo dispositivo = dispositivoDB.get();
                            DeviceQueueItem data = new DeviceQueueItem();
                            DeviceQueueItemClass deviceQueueItem = new DeviceQueueItemClass();
                            deviceQueueItem.setDevEUI(dispositivo.getDeveui());
                            deviceQueueItem.setConfirmed(true);
                            deviceQueueItem.setfCnt(0);
                            deviceQueueItem.setfPort(zonaQueueTempOneDTO.getfPort());
                            deviceQueueItem.setData(zonaQueueTempOneDTO.getData());
                            data.setDeviceQueueItem(deviceQueueItem);
                            ResponseEntity<String> resDeviceChirpstack = restTemplate.exchange(Url.urlDevice + "/" + dispositivo.getDeveui() + "/queue", HttpMethod.POST, AuthorizationHeader.getRequestEntity(data, internalServiceChirpstack.getTokenCS()), String.class);
                            if (resDeviceChirpstack.getStatusCode() == HttpStatus.OK) {
                                JSONObject res = new JSONObject(resDeviceChirpstack.getBody());
                                Integer fcnt = res.getInt("fCnt");

                                //GUARDO COLA DE ZONA LOGS
                                ZonaQueue zonaQueue = new ZonaQueue();
                                zonaQueue.setDeveui(dispositivo.getDeveui());
                                zonaQueue.setPayload(zonaQueueTempOneDTO.getData());
                                zonaQueue.setfCnt(fcnt);
                                zonaQueue.setfPort(zonaQueueTempOneDTO.getfPort());
                                zonaQueue.setFecha(LocalDateTime.now());
                                zonaQueue.setZona(zonaDB.get());
                                zonaQueue.setUsuario(usuarioDB.get());
                                zonaQueueCrudRepository.save(zonaQueue);

                                //ACTUALIZO EL DATO ENCOLADO DE ZONA TEMPORAL
                                zonaQueueTemp.setfCnt(fcnt);
                                zonaQueueTemp.setFecha(LocalDateTime.now());
                                int cont = zonaQueueTemp.getCont();
                                zonaQueueTemp.setCont(cont + 1);
                                zonaQueueTempCrudRepository.save(zonaQueueTemp);
                            }
                            return true;
                        }
                        throw new BadRequestException("ZonaQueueTemp con deveui " + zonaQueueTempOneDTO.getDevEUI() + " no encontrado para proceder con la encolacion");
                    }
                    throw new BadRequestException("Dispositivo con deveui " + usuarioId + " no encontrado para proceder con la encolacion");
                }
                throw new BadRequestException("Zona con id " + zonaId + " no encontrada para proceder a la comunicacion");
            }
            throw new BadRequestException("Usuario con id " + usuarioId + " no encontrada para proceder a la comunicacion");
        }
        throw new BadRequestException("No se tiene acceso a chirpstack para proceder con la comunicacion");
    }

    /**
     * Devuelve un boolean al borrar la informacion mediante el repositorio
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     * El metodo se comunica con el backend de chirpstack para el proceso de eliminación del encolamiento zonal
     *
     * @param zonaId
     * @return boolean
     */
    @Override
    @Transactional
    public boolean delete(Integer zonaId) {
        if (testConnection.testConnection()) {
            Optional<Zona> zonaDB = zonaCrudRepository.findById(zonaId);
            if (zonaDB.isPresent()) {
                List<Dispositivo> disps = deviceCrudRepository.getAllByZonaId(zonaId);
                if (disps.size() > 0) {
                    for (int i = 0; i < disps.size(); i++) {
                        Dispositivo dispositivoDB = disps.get(i);
                        ResponseEntity<String> resDeviceChirpstack = restTemplate.exchange(Url.urlDevice + "/" + dispositivoDB.getDeveui() + "/queue", HttpMethod.DELETE, AuthorizationHeader.getRequestEntityHeaders(internalServiceChirpstack.getTokenCS()), String.class);
                        if (resDeviceChirpstack.getStatusCode() == HttpStatus.OK) {
                            zonaQueueTempCrudRepository.deleteByDeveui(dispositivoDB.getDeveui());
                        } else {
                            throw new BadRequestException("Chirpstack error para eliminar de la cola el dispositivo con deveui " + dispositivoDB.getDeveui() + " Codigo de ERROR: " + resDeviceChirpstack.getStatusCode() + " ERROR: " + resDeviceChirpstack.getBody());
                        }
                    }
                    return true;
                }
                throw new BadRequestException("Zona con id " + zonaId + " no posee medidores para proceder a desencolar");
            }
            throw new BadRequestException("Zona con id " + zonaId + " no encontrada para proceder a eliminar de la cola");
        }
        throw new BadRequestException("No se tiene acceso a chirpstack para proceder a eliminar datos de la cola");
    }

    /**
     * Devuelve un boolean al borrar la informacion mediante el repositorio
     * Si el objeto no esta presente en la base de datos se lanza un false.
     * El método el ayuda a la tarea programada en el proceso de comunicación, elimina un dato encolado cuando la terea programada verifica los intentos de comunicacion con el dispositivo,
     *
     * @param deveui
     * @return boolean
     */
    @Override
    @Transactional
    public boolean deleteByDeveui(String deveui) {
        Optional<ZonaQueueTemp> zonaQueueTempDB = zonaQueueTempCrudRepository.findByDeveui(deveui);
        if (zonaQueueTempDB.isPresent()) {
            zonaQueueTempCrudRepository.deleteByDeveui(deveui);
            return true;
        }
        return false;
    }

    /**
     * No implementado
     *
     * @param zonaQueueTempDTO
     * @param id
     * @return ZonaQueueReadTemp
     */
    @Override
    public ZonaQueueReadTemp update(ZonaQueueTempDTO zonaQueueTempDTO, Integer id) {
        return null;
    }


}
