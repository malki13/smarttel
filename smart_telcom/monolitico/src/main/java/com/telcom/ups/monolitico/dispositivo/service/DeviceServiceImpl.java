package com.telcom.ups.monolitico.dispositivo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telcom.ups.data.dto.DetalleComunicacionDTO;
import com.telcom.ups.data.dto.DispositivoDTO;
import com.telcom.ups.data.dto.ReporteComunicacionDTO;
import com.telcom.ups.data.entities.*;
import com.telcom.ups.data.info.DispositivoInfo;
import com.telcom.ups.data.info.DispositivoInfoOnly;
import com.telcom.ups.data.read.DispositivoRead;
import com.telcom.ups.monolitico.application.repository.ApplicationCrudRepository;
import com.telcom.ups.monolitico.cliente.repository.ClienteCrudRepository;
import com.telcom.ups.monolitico.dispositivo.mapper.DeviceMapper;
import com.telcom.ups.monolitico.dispositivo.repository.DeviceCrudRepository;
import com.telcom.ups.monolitico.dispositivotipo.repository.DispositivoTipoCrudRepository;
import com.telcom.ups.monolitico.empresa.repository.EmpresaCrudRepository;
import com.telcom.ups.monolitico.mediciontipo.mapper.MedicionTipoMapper;
import com.telcom.ups.monolitico.protocolotipo.repository.ProtocoloTipoCrudRepository;
import com.telcom.ups.monolitico.tarifa.repository.TarifaCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import com.telcom.ups.monolitico.zona.repository.ZonaCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceCrudRepository deviceCrudRepository;

    @Autowired
    private ZonaCrudRepository zonaCrudRepository;

    @Autowired
    private DispositivoTipoCrudRepository dispositivoTipoCrudRepository;

    @Autowired
    private ApplicationCrudRepository applicationCrudRepository;

    @Autowired
    private TarifaCrudRepository tarifaCrudRepository;

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private EmpresaCrudRepository empresaCrudRepository;


    @Autowired
    private ProtocoloTipoCrudRepository protocoloTipoCrudRepository;

    @Autowired
    private DeviceMapper mapper;

    @Autowired
    private MedicionTipoMapper medicionTipoMapper;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Devuelve un Page de DispositivoInfoOnlies que provee el repositorio CRUD
     * Para devolver un Page de DispositivoInfoOnlies se realiza el proceso de conversión de objetos con la interfaz DeviceMapper
     *
     * @param pageable
     * @return pagen de DispositivoInfoOnlies
     */
    @Override
    public Page<DispositivoInfoOnly> findAll(Pageable pageable) {
        return deviceCrudRepository.findAll(pageable).map(dispositivo -> mapper.toDispositivoInfoOnly(dispositivo));
    }

    /**
     * Devuelve un Optional de DispositivoInfo que provee el repositorio CRUD
     * Para devolver un optional de DispositivoInfos se realiza el proceso de conversión de objetos con la interfaz DeviceMapper
     *
     * @param id
     * @return optional de DispositivoInfo
     */
    @Override
    public Optional<DispositivoInfo> getOne(Integer id) {
        Optional<Dispositivo> dispositivoDB = deviceCrudRepository.findById(id);
        if (dispositivoDB.isPresent()) {
            Dispositivo dispositivo = dispositivoDB.get();
            DispositivoInfo dispositivoInfo = mapper.toDispositivoInfo(dispositivo);
            ProtocoloTipo protocoloTipo = protocoloTipoCrudRepository.findByApplicationId(dispositivo.getApplication().getIden());
            if (protocoloTipo.getNombre().equals("MQTT")) {
                dispositivoInfo.setPuerto(1883);
            }
            if (protocoloTipo.getNombre().equals("HTTP")) {
                dispositivoInfo.setPuerto(4800);
            }
            return Optional.ofNullable(dispositivoInfo);
        }
        throw new BadRequestException("Dispositivo con id " + id + " no encontrado");
    }

    /**
     * Devuelve un Optional de DispositivoInfo que provee el repositorio CRUD
     * Para devolver un optional de DispositivoInfos se realiza el proceso de conversión de objetos con la interfaz DeviceMapper
     *
     * @param deveui
     * @return optional de DispositivoInfo
     */
    @Override
    public Optional<DispositivoInfo> getDeveui(String deveui) {
        Optional<Dispositivo> dispositivoDB = deviceCrudRepository.findByDeveui(deveui);
        if (dispositivoDB.isPresent()) {
            Dispositivo dispositivo = dispositivoDB.get();
            DispositivoInfo dispositivoInfo = mapper.toDispositivoInfo(dispositivo);
            ProtocoloTipo protocoloTipo = protocoloTipoCrudRepository.findByApplicationId(dispositivo.getApplication().getIden());
            if (protocoloTipo.getNombre().equals("MQTT")) {
                dispositivoInfo.setPuerto(1883);
            }
            if (protocoloTipo.getNombre().equals("HTTP")) {
                dispositivoInfo.setPuerto(4800);
            }
            return Optional.ofNullable(dispositivoInfo);
        }
        throw new BadRequestException("Dispositivo con deveui " + deveui + " no encontrado");
    }

    /**
     * Devuelve un DispositivoRead que provee el repositorio CRUD
     * Para devolver un DispositivoRead se realiza el proceso de conversión de objetos con la interfaz DeviceMapper
     * Para guardar un dispositivo previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param dispositivoDTO
     * @return DispositivoRead
     */
    @Override
    public DispositivoRead save(DispositivoDTO dispositivoDTO) {
        Optional<DispositivoTipo> dispositivoTipoDB = dispositivoTipoCrudRepository.findById(dispositivoDTO.getDispositivoTipoId());
        if (dispositivoTipoDB.isPresent()) {
            Optional<Application> applicationDB = applicationCrudRepository.findById(dispositivoDTO.getApplicationId());
            if (applicationDB.isPresent()) {
                Optional<Empresa> empresaDB = empresaCrudRepository.findById(dispositivoDTO.getEmpresaId());
                if (empresaDB.isPresent()) {

                    Optional<Cliente> clienteDB = clienteCrudRepository.findById(dispositivoDTO.getClienteId());
                    if (clienteDB.isPresent()) {
                        Optional<Zona> zonaDB = zonaCrudRepository.findById(dispositivoDTO.getZonaId());
                        if (zonaDB.isPresent()) {
                            Optional<Dispositivo> dispositivoDBDeveui = deviceCrudRepository.findByDeveui(dispositivoDTO.getDeveui());
                            if (!dispositivoDBDeveui.isPresent()) {
                                Optional<Dispositivo> dispositivoDBDeveuiEmpresa = deviceCrudRepository.findByDeveuiEmpresa(dispositivoDTO.getDeveuiEmpresa());
                                if (!dispositivoDBDeveuiEmpresa.isPresent()) {
                                    Integer numDispositivos = deviceCrudRepository.countByApplicationIden(dispositivoDTO.getApplicationId());
                                    Empresa empresaDB2 = empresaCrudRepository.findByIden(dispositivoDTO.getEmpresaId());
                                    if (numDispositivos < empresaDB2.getNumDevices()) {

                                        Dispositivo dispositivo = mapper.toDispositivo(dispositivoDTO);
                                        dispositivo.setDispositivoTipo(dispositivoTipoDB.get());
                                        dispositivo.setApplication(applicationDB.get());

                                        if (dispositivoDTO.getTarifaId() != null && dispositivoDTO.getTarifaId() > 0) {
                                            Optional<Tarifa> tarifaDB = tarifaCrudRepository.findById(dispositivoDTO.getTarifaId());
                                            if (tarifaDB.isPresent()) {
                                                dispositivo.setTarifa(tarifaDB.get());
                                            } else {
                                                throw new BadRequestException("Tarifa con id " + dispositivoDTO.getTarifaId() + " no encontrada para relacionar con el dispositivo");
                                            }
                                        }

                                        dispositivo.setCliente(clienteDB.get());
                                        dispositivo.setZona(zonaDB.get());
                                        dispositivo.setEmpresa(empresaDB.get());

                                        ProtocoloTipo protocoloTipoDB = protocoloTipoCrudRepository.findByApplicationId(dispositivoDTO.getApplicationId());
                                        if (protocoloTipoDB.getNombre() != null) {
                                            if (protocoloTipoDB.getNombre().equals("MQTT")) {
                                                dispositivo.setTopico("emp/" + empresaDB.get().getIden() + "/app/" + applicationDB.get().getIden() + "/dis/" + dispositivoDTO.getDeveui());
                                            }
                                            if (protocoloTipoDB.getNombre().equals("HTTP")) {
                                                dispositivo.setUrl("JSON: { 'dis': " + dispositivoDTO.getDeveui() + ", 'app': " + applicationDB.get().getIden() + ", 'emp': " + empresaDB.get().getIden() + ", 'data': 'Data e enviar' }");
                                            }
                                        }

                                        dispositivo.setMedicionTipos(medicionTipoMapper.toMedicionTipos(dispositivoDTO.getMedicionTipos()));
                                        return mapper.toDispositivoRead(deviceCrudRepository.save(dispositivo));
                                    }
                                    throw new BadRequestException("La empresa " + empresaDB2.getInterventor().getNombre() + " " + empresaDB2.getInterventor().getApellido()
                                            + " puede registrar máximo " + empresaDB2.getNumDevices() + " dispositivos.");
                                }
                                throw new BadRequestException("Dispositivo con deveuiEmpresa " + dispositivoDTO.getDeveuiEmpresa() + " ya esta registrado");
                            }
                            throw new BadRequestException("Dispositivo con deveui " + dispositivoDTO.getDeveui() + " ya esta registrado");
                        }
                        throw new BadRequestException("Zona con id " + dispositivoDTO.getZonaId() + " no encontrada para relacionar con el dispositivo");
                    }
                    throw new BadRequestException("Cliente con id " + dispositivoDTO.getClienteId() + " no encontrado para relacionar con el dispositivo");
                }
                throw new BadRequestException("Empresa con id " + dispositivoDTO.getEmpresaId() + " no encontrada para relacionar con el dispositivo");
            }
            throw new BadRequestException("Application con id " + dispositivoDTO.getApplicationId() + " no encontrada para relacionar con el dispositivo");
        }
        throw new BadRequestException("DispositivoTipo con id " + dispositivoDTO.getDispositivoTipoId() + " no encontrado para relacionar con el dispositivo");
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
            Optional<Dispositivo> dispositivoDB = deviceCrudRepository.findById(id);
            if (dispositivoDB.isPresent()) {
                deviceCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Dispositivo con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El Dispositivo que intenta eliminar cuenta con facturas generadas, previo a este proceso para que " +
                    "el proceso de eliminación sea exitoso el dispositivo no debe tener facturas generadas.");
        }
    }

    /**
     * Devuelve un boolean que provee el repositorio CRUD
     * Para actualizar un dispositivo previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param idCliente
     * @param id
     * @return boolean
     */
    @Override
    public boolean updatePropietario(Integer idCliente, Integer id) {
        Optional<Dispositivo> dispositivoDB = deviceCrudRepository.findById(id);
        if (dispositivoDB.isPresent()) {
            Optional<Cliente> clienteDB = clienteCrudRepository.findById(idCliente);
            if (clienteDB.isPresent()) {
                Dispositivo dispositivo = dispositivoDB.get();
                dispositivo.setCliente(clienteDB.get());
                deviceCrudRepository.save(dispositivo);
                return true;
            }
            throw new BadRequestException("Cliente con id " + idCliente + " no encontrado para actualizar el propietario");
        }
        throw new BadRequestException("Dispositivo con id " + id + " no encontrado para actualizar el propietario");
    }

    /**
     * Devuelve un DispositivoRead que provee el repositorio CRUD
     * Para devolver un DispositivoRead se realiza el proceso de conversión de objetos con la interfaz DeviceMapper
     * Para actualizar un cliente previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     *
     * @param dispositivoDTO
     * @param id
     * @return DispositivoRead
     */
    @Override
    public DispositivoRead update(DispositivoDTO dispositivoDTO, Integer id) {
        Optional<Dispositivo> dispositivoDB = deviceCrudRepository.findById(id);
        if (dispositivoDB.isPresent()) {
            Optional<DispositivoTipo> dispositivoTipoDB = dispositivoTipoCrudRepository.findById(dispositivoDTO.getDispositivoTipoId());
            if (dispositivoTipoDB.isPresent()) {
                Optional<Application> applicationDB = applicationCrudRepository.findById(dispositivoDTO.getApplicationId());
                if (applicationDB.isPresent()) {
                    Optional<Empresa> empresaDB = empresaCrudRepository.findById(dispositivoDTO.getEmpresaId());
                    if (empresaDB.isPresent()) {

                        Optional<Cliente> clienteDB = clienteCrudRepository.findById(dispositivoDTO.getClienteId());
                        if (clienteDB.isPresent()) {
                            Optional<Zona> zonaDB = zonaCrudRepository.findById(dispositivoDTO.getZonaId());
                            if (zonaDB.isPresent()) {
                                Dispositivo newDispositivo = mapper.toDispositivo(dispositivoDTO);
                                Dispositivo dis = dispositivoDB.get();
                                dis.setNombre(newDispositivo.getNombre());
                                dis.setDescripcion(newDispositivo.getDescripcion());
                                dis.setDeveui(newDispositivo.getDeveui());
                                dis.setDeveuiEmpresa(newDispositivo.getDeveuiEmpresa());
                                dis.setIdDeviceProfile(newDispositivo.getIdDeviceProfile());
                                dis.setIdApplication(newDispositivo.getIdApplication());
                                dis.setAltitude(newDispositivo.getAltitude());
                                dis.setLatitude(newDispositivo.getLatitude());
                                dis.setLongitude(newDispositivo.getLongitude());
                                dis.setDispositivoTipo(dispositivoTipoDB.get());
                                dis.setApplication(applicationDB.get());


                                if (dispositivoDTO.getTarifaId() != null && dispositivoDTO.getTarifaId() > 0) {
                                    Optional<Tarifa> tarifaDB = tarifaCrudRepository.findById(dispositivoDTO.getTarifaId());
                                    if (tarifaDB.isPresent()) {
                                        dis.setTarifa(tarifaDB.get());
                                    } else {
                                        throw new BadRequestException("Tarifa con id " + dispositivoDTO.getTarifaId() + " no encontrada para relacionar con el dispositivo");
                                    }
                                }

                                dis.setCliente(clienteDB.get());
                                dis.setZona(zonaDB.get());

                                ProtocoloTipo protocoloTipoDB = protocoloTipoCrudRepository.findByApplicationId(dispositivoDTO.getApplicationId());
                                if (protocoloTipoDB.getNombre() != null) {
                                    if (protocoloTipoDB.getNombre().equals("MQTT")) {
                                        dis.setTopico("emp/" + empresaDB.get().getIden() + "/app/" + applicationDB.get().getIden() + "/dis/" + dispositivoDTO.getDeveui());
                                    }
                                    if (protocoloTipoDB.getNombre().equals("HTTP")) {
                                        dis.setUrl("JSON: { 'dis': " + dispositivoDTO.getDeveui() + ", 'app': " + applicationDB.get().getIden() + ", 'emp': " + empresaDB.get().getIden() + ", 'data': 'Data e enviar' }");
                                    }
                                }

                                dis.setMedicionTipos(medicionTipoMapper.toMedicionTipos(dispositivoDTO.getMedicionTipos()));
                                dis.setEmpresa(empresaDB.get());
                                dis.setUpdatedAt(LocalDateTime.now());
                                return mapper.toDispositivoRead(deviceCrudRepository.save(dis));
                            }
                            throw new BadRequestException("Zona con id " + dispositivoDTO.getZonaId() + " no encontrada para relacionar con el dispositivo");
                        }
                        throw new BadRequestException("Cliente con id " + dispositivoDTO.getClienteId() + " no encontrado para relacionar con el dispositivo");

                    }
                    throw new BadRequestException("Empresa con id " + dispositivoDTO.getEmpresaId() + " no encontrada para relacionar con el dispositivo");
                }
                throw new BadRequestException("Application con id " + dispositivoDTO.getApplicationId() + " no encontrada para relacionar con el dispositivo");
            }
            throw new BadRequestException("DispositivoTipo con id " + dispositivoDTO.getDispositivoTipoId() + " no encontrado para relacionar con el dispositivo");
        }
        throw new BadRequestException("Dispositivo con id " + id + " no encontrado para actualizar");
    }

    /**
     * Devuelve un boolean que provee el repositorio CRUD
     * Para actualizar un detalle de comunicación del dispositivo previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     * El método es usado por la tarea programada, para realizar el proceso de comunicación
     *
     * @param detalleComunicacionDTO
     * @param deveui
     * @return boolean
     */
    @Override
    @Transactional
    public boolean updateDetalleComunicacion(DetalleComunicacionDTO detalleComunicacionDTO, String deveui) {
        Optional<Dispositivo> dispositivoDB = deviceCrudRepository.findByDeveui(deveui);
        if (dispositivoDB.isPresent()) {
            DetalleComunicacion detalleComunicacion = new DetalleComunicacion();
            Dispositivo dispositivo = dispositivoDB.get();
            if (dispositivo.getDetalleComunicacion() != null) {
                detalleComunicacion = dispositivo.getDetalleComunicacion();
            }
            if (detalleComunicacionDTO.getEstado().equals("OK"))
                detalleComunicacion.setEstado(detalleComunicacionDTO.getEstado());
            if (detalleComunicacionDTO.getEstado().equals("SinComunicacion"))
                detalleComunicacion.setEstado(detalleComunicacionDTO.getEstado());
            detalleComunicacion.setUpdatedAt(LocalDateTime.now());
            switch (detalleComunicacionDTO.getComando()) {
                case 1:
                    //SERVICE ===> CIERRE Y APERTURA
                    detalleComunicacion.setService(detalleComunicacionDTO.getService());
                    break;
                case 2:
                    //SAMPLING
                    detalleComunicacion.setSampler(detalleComunicacionDTO.getSampler());
                    break;
                case 3:
                    //FREQUENCY
                    detalleComunicacion.setFrequency(detalleComunicacionDTO.getFrequency());
                    break;
                case 4:
                    //CUTDAY
                    detalleComunicacion.setCutDay(detalleComunicacionDTO.getCutDay());
                    break;
                case 5:
                    //DAILY-TRASMISSIONS
                    detalleComunicacion.setDailyTransmissions(detalleComunicacionDTO.getDailyTransmissions());
                    break;
                case 6:
                    //UPDATE-TIME
                    detalleComunicacion.setUpdateTime(detalleComunicacionDTO.getUpdateTime());
                    break;
                case 7:
                    //ASYNC-SEND
                    detalleComunicacion.setAsyncSend(detalleComunicacionDTO.getAsyncSend());
                    break;
                case 8:
                    //LEAK-DETECT
                    detalleComunicacion.setLeakDetect(detalleComunicacionDTO.getLeakDetect());
                    break;
                case 9:
                    //GET-POSICION
                    detalleComunicacion.setLatitud(detalleComunicacionDTO.getLatitud());
                    detalleComunicacion.setLongitud(detalleComunicacionDTO.getLongitud());
                    break;
                case 10:
                    //GET-BATTERY
                    detalleComunicacion.setBattery(detalleComunicacionDTO.getBattery());
                    break;
                case 11:
                    //GET-RSSI
                    detalleComunicacion.setRssi(detalleComunicacionDTO.getRssi());
                    break;
                case 12:
                    //SET-POSICION
                    detalleComunicacion.setSetPosition(detalleComunicacionDTO.getSetPosition());
                    break;
                case 13:
                    //GET-REPORTE
                    break;
                case 14:
                    //SET-CNF
                    detalleComunicacion.setSetCNF(detalleComunicacionDTO.getSetCNF());
                    break;
                case 16:
                    //GET-TIME
                    detalleComunicacion.setHora(detalleComunicacionDTO.getHora());
                    break;
                default:
                    break;
            }
            dispositivo.setDetalleComunicacion(detalleComunicacion);
            deviceCrudRepository.save(dispositivo);
            return true;
        }
        throw new BadRequestException("Dispositivo con deveui " + deveui + " no encontrado para actualizar detalle de comunicacion");
    }

    /**
     * Devuelve un boolean que provee el repositorio CRUD
     * Para actualizar un reporte de comunicación del dispositivo previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     * El método es usado por la tarea programada, para realizar el proceso de comunicación
     *
     * @param reporteComunicacionDTO
     * @param deveui
     * @return boolean
     */
    @Override
    @Transactional
    public boolean updateReporteComunicacion(ReporteComunicacionDTO reporteComunicacionDTO, String deveui) {
        Optional<Dispositivo> dispositivoDB = deviceCrudRepository.findByDeveui(deveui);
        if (dispositivoDB.isPresent()) {
            ReporteComunicacion reporteComunicacion = new ReporteComunicacion();
            Dispositivo dispositivo = dispositivoDB.get();
            if (dispositivo.getReporteComunicacion() != null) {
                reporteComunicacion = dispositivo.getReporteComunicacion();
            }
            reporteComunicacion.setModeDailySchedule(reporteComunicacionDTO.getModeDailySchedule());
            reporteComunicacion.setModeNormalSampler(reporteComunicacionDTO.getModeNormalSampler());
            reporteComunicacion.setModeResumeSuspend(reporteComunicacionDTO.getModeResumeSuspend());
            reporteComunicacion.setCutOffDay(reporteComunicacionDTO.getCutOffDay());
            reporteComunicacion.setComLora(reporteComunicacionDTO.getComLora());
            reporteComunicacion.setRetries(reporteComunicacionDTO.getRetries());
            reporteComunicacion.setTimeAsyn(reporteComunicacionDTO.getTimeAsyn());
            reporteComunicacion.setTimeLeakDetect(reporteComunicacionDTO.getTimeLeakDetect());
            reporteComunicacion.setUpdatedAt(LocalDateTime.now());
            dispositivo.setReporteComunicacion(reporteComunicacion);
            deviceCrudRepository.save(dispositivo);
            return true;
        }
        throw new BadRequestException("Dispositivo con deveui " + deveui + " no encontrado para actualizar reporte de comunicacion");
    }

    /**
     * Devuelve un Page de DispositivoInfoOnlies que provee el repositorio CRUD
     * Para devolver un page de DispositivoInfoOnlies se realiza el proceso de conversión de objetos con la interfaz DeviceMapper
     *
     * @param idApplication
     * @param pageable
     * @return page de DispositivoInfoOnlies
     */
    @Override
    public Page<DispositivoInfoOnly> getAllByApplication(Integer idApplication, Pageable pageable) {
        return deviceCrudRepository.getAllByApplication(idApplication, pageable).map(dispositivo -> mapper.toDispositivoInfoOnly(dispositivo));
    }

    /**
     * Devuelve un Page de DispositivoInfoOnlies que provee el repositorio CRUD
     * Para devolver un page de DispositivoInfoOnlies se realiza el proceso de conversión de objetos con la interfaz DeviceMapper
     *
     * @param idCliente
     * @param pageable
     * @return page de DispositivoInfoOnlies
     */
    @Override
    public Page<DispositivoInfoOnly> getAllByCliente(Integer idCliente, Pageable pageable) {
        return deviceCrudRepository.getAllByCliente(idCliente, pageable).map(dispositivo -> mapper.toDispositivoInfoOnly(dispositivo));
    }

    /**
     * Devuelve un Page de DispositivoInfoOnlies que provee el repositorio CRUD
     * Para devolver un page de DispositivoInfoOnlies se realiza el proceso de conversión de objetos con la interfaz DeviceMapper
     *
     * @param idZona
     * @param pageable
     * @return page de DispositivoInfoOnlies
     */
    @Override
    public Page<DispositivoInfoOnly> getAllByZona(Integer idZona, Pageable pageable) {
        return deviceCrudRepository.getAllByZona(idZona, pageable).map(dispositivo -> mapper.toDispositivoInfoOnly(dispositivo));
    }

    @Override
    public Page<DispositivoInfoOnly> getAllByEmpresa(Integer idEmpresa, Pageable pageable) {
        return deviceCrudRepository.getAllByEmpresa(idEmpresa, pageable).map(dispositivo -> mapper.toDispositivoInfoOnly(dispositivo));
    }

    @Override
    public Page<DispositivoInfoOnly> getAllByTipoEmpZonProt(Integer idEmpresa, Integer idTipo, Integer idZona, Integer idProtocolo, Pageable pageable){
        return deviceCrudRepository.getAllByTipoEmpZonProt(idEmpresa, idTipo, idZona, idProtocolo, pageable).map(dispositivo -> mapper.toDispositivoInfoOnly(dispositivo));
    }

    @Override
    public Page<DispositivoInfoOnly> getAllByCienteProtocoloEmpresa(String codCliente, Integer idProtocolo, Integer idEmpresa, Pageable pageable) {
        return deviceCrudRepository.getAllByCienteProtocoloEmpresa(codCliente, idProtocolo, idEmpresa, pageable).map(dispositivo -> mapper.toDispositivoInfoOnly(dispositivo));
    }


}
