package com.telcom.ups.monolitico.dispositivoqueuetemp.service;

import com.telcom.ups.data.dto.ComunicacionDTO;
import com.telcom.ups.data.dto.DispositivoQueueTempDTO;
import com.telcom.ups.data.entities.*;
import com.telcom.ups.data.info.DispositivoQueueInfo;
import com.telcom.ups.data.info.DispositivoQueueTempInfo;
import com.telcom.ups.data.read.DispositivoQueueTempRead;
import com.telcom.ups.monolitico.dispositivo.repository.DeviceCrudRepository;
import com.telcom.ups.monolitico.dispositivoqueue.mapper.DispositivoQueueMapper;
import com.telcom.ups.monolitico.dispositivoqueue.repository.DispositivoQueueCrudRepository;
import com.telcom.ups.monolitico.dispositivoqueuetemp.mapper.DispositivoQueueTempMapper;
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
import com.telcom.ups.monolitico.zonaqueutemp.repository.ZonaQueueTempCrudRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DispositivoQueueTempServiceImpl implements DispositivoQueueTempService {

    @Autowired
    private DispositivoQueueTempCrudRepository dispositivoQueueTempCrudRepository;

    @Autowired
    private DispositivoQueueCrudRepository dispositivoQueueCrudRepository;

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DispositivoQueueTempMapper dispositivoQueueTempMapper;

    @Autowired
    private DispositivoQueueMapper dispositivoQueueMapper;

    @Autowired
    private DeviceCrudRepository deviceCrudRepository;

    @Autowired
    private TestConnection testConnection;

    @Autowired
    private ZonaQueueTempCrudRepository zonaQueueTempCrudRepository;

    @Autowired
    private InternalServiceChirpstack internalServiceChirpstack;


    /**
     * Devuelve un Page de DispositivoQueueTempInfos que provee el repositorio
     * Para devolver un Page de AdministradorInfos se realiza el proceso de conversión de objetos con la interfaz DispositivoQueueTempMapper
     *
     * @param pageable
     * @return page de DispositivoQueueTempInfo
     */
    @Override
    public Page<DispositivoQueueTempInfo> findAll(Pageable pageable) {
        return dispositivoQueueTempCrudRepository.findAll(pageable).map(dispositivoQueueTemp -> dispositivoQueueTempMapper.toDispositivoQueueTempInfo(dispositivoQueueTemp));
    }

    /**
     * Devuelve un Page de DispositivoQueueInfo que provee el repositorio
     * Para devolver un Page de AdministradorInfos se realiza el proceso de conversión de objetos con la interfaz DispositivoQueueMapper
     *
     * @param pageable
     * @return page de DispositivoQueueInfo
     */
    @Override
    public Page<DispositivoQueueInfo> findAllLogs(Pageable pageable) {
        return dispositivoQueueCrudRepository.findAll(pageable).map(dispositivoQueue -> dispositivoQueueMapper.toDispositivoQueueInfo(dispositivoQueue));
    }

    /**
     * Devuelve un Page de DispositivoQueueTempInfos que provee el repositorio
     * Para devolver un Page de AdministradorInfos se realiza el proceso de conversión de objetos con la interfaz DispositivoQueueTempMapper
     *
     * @param pageable
     * @return page de DispositivoQueueTempInfo
     */
    @Override
    public Page<DispositivoQueueTempInfo> getAllJob(Pageable pageable) {
        return dispositivoQueueTempCrudRepository.findAll(pageable).map(zonaQueueTemp -> dispositivoQueueTempMapper.toDispositivoQueueTempInfo(zonaQueueTemp));
    }

    /**
     * Devuelve un Page de DispositivoQueueInfo que provee el repositorio
     * Para devolver un Page de DispositivoQueueInfos se realiza el proceso de conversión de objetos con la interfaz DispositivoQueueMapper
     *
     * @param deveui
     * @param pageable
     * @return page de DispositivoQueueInfo
     */
    @Override
    public Page<DispositivoQueueInfo> getAllByDeveui(String deveui, Pageable pageable) {
        return dispositivoQueueCrudRepository.getAllByDeveui(deveui, pageable).map(dispositivoQueue -> dispositivoQueueMapper.toDispositivoQueueInfo(dispositivoQueue));
    }

    /**
     * No implementado
     *
     * @param id
     * @return optional de DispositivoQueueTempRead
     */
    @Override
    public Optional<DispositivoQueueTempRead> getOne(Integer id) {
        return Optional.empty();
    }

    /**
     * Devuelve un Optional de DispositivoQueueTempRead que provee el repositorio
     * Para devolver un optional de DispositivoQueueTempRead se realiza el proceso de conversión de objetos con la interfaz DispositivoQueueTempMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param deveui
     * @return DispositivoQueueTempRead
     */
    @Override
    public Optional<DispositivoQueueTempRead> findByDeveui(String deveui) {
        Optional<DispositivoQueueTemp> dispositivoQueueTempDB = dispositivoQueueTempCrudRepository.findByDeveui(deveui);
        if (dispositivoQueueTempDB.isPresent()) {
            return dispositivoQueueTempDB.map(dispositivoQueue -> dispositivoQueueTempMapper.toDispositivoQueueTempRead(dispositivoQueue));
        }
        throw new BadRequestException("Dispositivo con deveui " + deveui + " no encontrado para verificar cola de bajada");

    }

    /**
     * Devuelve un ComunicacionDTO en base a una consulta al repositorio
     * El metodo se usa para saber en que estado se encuentra el proceso de comunicación
     *
     * @param deveui
     * @return ComunicacionDTO
     */
    @Override
    public ComunicacionDTO geEstado(String deveui) {
        Optional<DispositivoQueueTemp> dispositivoQueueTempDB = dispositivoQueueTempCrudRepository.findByDeveui(deveui);
        if (dispositivoQueueTempDB.isPresent()) {
            return new ComunicacionDTO("PROCESANDO");
        }
        return new ComunicacionDTO("OK");
    }

    /**
     * Devuelve un boolean que provee el repositorio
     * Para encolar datos en cada dispositivo previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición.
     * El metodo se comunica con el backend de chirpstack para el proceso de encolamiento individual por medidor validando que los dispositivos se encuentren
     * previamente activos y que los mismos cuente con su creacion tanto de lado del backend de chirpstack como de lado del backend de telcom caso contrario
     * el proceso de encolación no se puede realizar.
     *
     * @param dispositivoQueueTempDTO
     * @param usuarioId
     * @param deveui
     * @return boolean
     * @throws ParseException
     */
    @Override
    @Transactional
    public boolean save(DispositivoQueueTempDTO dispositivoQueueTempDTO, Integer usuarioId, String deveui) throws ParseException {
        try {
            if (testConnection.testConnection()) {
                Optional<ZonaQueueTemp> zonaQueueTempDB = zonaQueueTempCrudRepository.findByDeveui(deveui);
                if (!zonaQueueTempDB.isPresent()) {
                    Optional<Usuario> usuarioDB = usuarioCrudRepository.findById(usuarioId);
                    if (usuarioDB.isPresent()) {
                        Optional<Dispositivo> dispositivoDBC = deviceCrudRepository.findByDeveui(deveui);
                        if (dispositivoDBC.isPresent()) {
                            ResponseEntity<String> resDeviceChirpstackExiste = restTemplate.exchange(
                                    Url.urlDevice + "/" + deveui,
                                    HttpMethod.GET,
                                    AuthorizationHeader.getRequestEntityHeaders(internalServiceChirpstack.getTokenCS()),
                                    String.class
                            );

                            if (resDeviceChirpstackExiste.getStatusCode() == HttpStatus.OK) {
                                JSONObject object = new JSONObject(resDeviceChirpstackExiste.getBody());
                                if (object.isNull("lastSeenAt") || !Conversion.fechaValida(object.getString("lastSeenAt"))) {
                                    throw new BadRequestException("Dispositivo con deveui " + deveui + " tiene un extenso tiempo de inactividad, para que el proceso de comunicación sea exitoso el dispositivo debe estar constatemente activo");
                                }

                                Optional<DispositivoQueueTemp> dispositivoQueueTempDB = dispositivoQueueTempCrudRepository.findByDeveui(deveui);
                                if (!dispositivoQueueTempDB.isPresent()) {
                                    DeviceQueueItem data = new DeviceQueueItem();
                                    DeviceQueueItemClass deviceQueueItem = new DeviceQueueItemClass();
                                    deviceQueueItem.setDevEUI(deveui);
                                    deviceQueueItem.setConfirmed(true);
                                    deviceQueueItem.setfCnt(0);
                                    deviceQueueItem.setfPort(dispositivoQueueTempDTO.getfPort());
                                    deviceQueueItem.setData(dispositivoQueueTempDTO.getData());
                                    data.setDeviceQueueItem(deviceQueueItem);
                                    ResponseEntity<String> resDeviceChirpstack = restTemplate.exchange(
                                            Url.urlDevice + "/" + deveui + "/queue",
                                            HttpMethod.POST,
                                            AuthorizationHeader.getRequestEntity(data, internalServiceChirpstack.getTokenCS()),
                                            String.class
                                    );
                                    if (resDeviceChirpstack.getStatusCode() == HttpStatus.OK) {
                                        JSONObject res = new JSONObject(resDeviceChirpstack.getBody());
                                        Integer fcnt = res.getInt("fCnt");

                                        //CONSTRUYO Y GUARDO COLA DE LOG
                                        DispositivoQueue dispositivoQueue = new DispositivoQueue();
                                        dispositivoQueue.setDeveui(deveui);
                                        dispositivoQueue.setPayload(dispositivoQueueTempDTO.getData());
                                        dispositivoQueue.setfCnt(fcnt);
                                        dispositivoQueue.setfPort(dispositivoQueueTempDTO.getfPort());
                                        dispositivoQueue.setFecha(LocalDateTime.now());
                                        dispositivoQueue.setUsuario(usuarioDB.get());
                                        dispositivoQueueCrudRepository.save(dispositivoQueue);

                                        //CONSTRUYO Y GUARDO COLA TEMPORAL
                                        DispositivoQueueTemp dispositivoQueueTemp = new DispositivoQueueTemp();
                                        dispositivoQueueTemp.setDeveui(deveui);
                                        dispositivoQueueTemp.setPayload(dispositivoQueueTempDTO.getData());
                                        dispositivoQueueTemp.setfCnt(fcnt);
                                        dispositivoQueueTemp.setfPort(dispositivoQueueTempDTO.getfPort());
                                        dispositivoQueueTemp.setFecha(LocalDateTime.now());
                                        dispositivoQueueTemp.setCont(1);
                                        dispositivoQueueTempCrudRepository.save(dispositivoQueueTemp);

                                        return true;
                                    }
                                    throw new BadRequestException("Chirpstack error para encolar datos el medidor con deveui " + deveui + " Codigo de ERROR: " + resDeviceChirpstack.getStatusCode() + " ERROR: " + resDeviceChirpstack.getBody());
                                }
                                throw new BadRequestException("Dispositivo con deveui " + deveui + " ya tiene un dato encolado debe esperar a que se complete el proceso de comunicacion en curso");
                            }
                            throw new BadRequestException("Dispositivo con deveui " + deveui + " no existe en chirpstack para proceder con la encolacion");
                        }
                        throw new BadRequestException("Dispositivo con deveui " + deveui + " no encontrado para proceder con la encolacion");
                    }
                    throw new BadRequestException("Usuario con id " + usuarioId + " no encontrado para proceder con la encolacion");
                }
                throw new BadRequestException("Dispositivo con deveui " + deveui + " actualmente esta encolado para una comunicacion zonal en " +
                        "curso para continuar con el proceso debe esperar a que este finalice");
            }
            throw new BadRequestException("No se tiene acceso a chirpstack para proceder a encolar datos");
        } catch (HttpClientErrorException exception) {
            throw new BadRequestException("Dispositivo con deveui " + deveui + " no existe en chirpstack para proceder con la encolacion");
        }
    }

    /**
     * Devuelve un boolean al borrar la informacion mediante el repositorio
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     * El metodo se comunica con el backend de chirpstack para el proceso de eliminación del encolamiento individual
     *
     * @param deveui
     * @return boolean
     */
    @Override
    @Transactional
    public boolean delete(String deveui) {
        if (testConnection.testConnection()) {
            Optional<DispositivoQueueTemp> dispositivoQueueTempDB = dispositivoQueueTempCrudRepository.findByDeveui(deveui);
            if (dispositivoQueueTempDB.isPresent()) {
                ResponseEntity<String> resDeviceChirpstack = restTemplate.exchange(Url.urlDevice + "/" + deveui + "/queue", HttpMethod.DELETE, AuthorizationHeader.getRequestEntityHeaders(internalServiceChirpstack.getTokenCS()), String.class);
                if (resDeviceChirpstack.getStatusCode() == HttpStatus.OK) {
                    dispositivoQueueTempCrudRepository.deleteByDeveui(deveui);
                    return true;
                }
                throw new BadRequestException("Chirpstack error para encolar datos el medidor con deveui " + deveui + " Codigo de ERROR: " + resDeviceChirpstack.getStatusCode() + " ERROR: " + resDeviceChirpstack.getBody());
            }
            throw new BadRequestException("Dispositivo con deveui " + deveui + " no tiene datos encolados para eliminar");
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
        Optional<DispositivoQueueTemp> dispositivoQueueTempDB = dispositivoQueueTempCrudRepository.findByDeveui(deveui);
        if (dispositivoQueueTempDB.isPresent()) {
            dispositivoQueueTempCrudRepository.deleteByDeveui(deveui);
            return true;
        }
        return false;
    }

    /**
     * No implementado
     *
     * @param dispositivoQueueTempDTO
     * @param id
     * @return DispositivoQueueTempRead
     */
    @Override
    public DispositivoQueueTempRead update(DispositivoQueueTempDTO dispositivoQueueTempDTO, Integer id) {
        return null;
    }

    /**
     * Devuelve un boolean al reencolar la informacion mediante el repositorio
     * Para reencolar datos en el dispositivo previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición.
     * El metodo se comunica con el backend de chirpstack para el proceso de reencolamiento individual
     * el proceso de encolación no se puede realizar.
     *
     * @param dispositivoQueueTempDTO
     * @param usuarioId
     * @param deveui
     * @return boolean
     */
    @Override
    public boolean backEncolarMedidor(DispositivoQueueTempDTO dispositivoQueueTempDTO, Integer usuarioId, String deveui) {
        if (testConnection.testConnection()) {
            Optional<Usuario> usuarioDB = usuarioCrudRepository.findById(usuarioId);
            if (usuarioDB.isPresent()) {
                Optional<Dispositivo> dispositivoDB = deviceCrudRepository.findByDeveui(deveui);
                if (dispositivoDB.isPresent()) {
                    Optional<DispositivoQueueTemp> dispositivoQueueTempDB = dispositivoQueueTempCrudRepository.findByDeveui(deveui);
                    if (dispositivoQueueTempDB.isPresent()) {
                        DispositivoQueueTemp dispositivoQueueTemp = dispositivoQueueTempDB.get();
                        DeviceQueueItem data = new DeviceQueueItem();
                        DeviceQueueItemClass deviceQueueItem = new DeviceQueueItemClass();
                        deviceQueueItem.setDevEUI(dispositivoQueueTemp.getDeveui());
                        deviceQueueItem.setConfirmed(true);
                        deviceQueueItem.setfCnt(0);
                        deviceQueueItem.setfPort(dispositivoQueueTemp.getfPort());
                        deviceQueueItem.setData(dispositivoQueueTemp.getPayload());
                        data.setDeviceQueueItem(deviceQueueItem);
                        ResponseEntity<String> resDeviceChirpstack = restTemplate.exchange(Url.urlDevice + "/" + deveui + "/queue", HttpMethod.POST, AuthorizationHeader.getRequestEntity(data, internalServiceChirpstack.getTokenCS()), String.class);
                        if (resDeviceChirpstack.getStatusCode() == HttpStatus.OK) {
                            JSONObject res = new JSONObject(resDeviceChirpstack.getBody());
                            Integer fcnt = res.getInt("fCnt");

                            //CONSTRUYO Y GUARDO COLA DE LOG
                            DispositivoQueue dispositivoQueue = new DispositivoQueue();
                            dispositivoQueue.setDeveui(deveui);
                            dispositivoQueue.setPayload(dispositivoQueueTempDTO.getData());
                            dispositivoQueue.setfCnt(fcnt);
                            dispositivoQueue.setfPort(dispositivoQueueTempDTO.getfPort());
                            dispositivoQueue.setFecha(LocalDateTime.now());
                            dispositivoQueue.setUsuario(usuarioDB.get());
                            dispositivoQueueCrudRepository.save(dispositivoQueue);

                            //ACTUALIZO EL DATO DE LA COLA TEMPORAL
                            dispositivoQueueTemp.setfCnt(fcnt);
                            dispositivoQueueTemp.setFecha(LocalDateTime.now());
                            int cont = dispositivoQueueTemp.getCont();
                            dispositivoQueueTemp.setCont(cont + 1);
                            dispositivoQueueTempCrudRepository.save(dispositivoQueueTemp);

                            return true;
                        }
                        throw new BadRequestException("Chirpstack error para encolar datos el medidor con deveui " + deveui + " Codigo de ERROR: " + resDeviceChirpstack.getStatusCode() + " ERROR: " + resDeviceChirpstack.getBody());
                    }
                    throw new BadRequestException("Dispositivo con deveui " + deveui + " ya tiene un dato encolado debe esperar a que se complete el proceso de comunicacion en curso");
                }
                throw new BadRequestException("Dispositivo con deveui " + usuarioId + " no encontrado para proceder con la encolacion");
            }
            throw new BadRequestException("Usuario con id " + usuarioId + " no encontrado para proceder con la encolacion");
        }
        throw new BadRequestException("No se tiene acceso a chirpstack para proceder a encolar datos");
    }
}
