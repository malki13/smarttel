package com.telcom.ups.monolitico.factura.service;

import com.telcom.ups.data.dto.AnexoDTO;
import com.telcom.ups.data.dto.DetalleDTO;
import com.telcom.ups.data.dto.DetallePagoDTO;
import com.telcom.ups.data.dto.FacturaDTO;
import com.telcom.ups.data.entities.*;
import com.telcom.ups.data.info.FacturaInfo;
import com.telcom.ups.data.info.FacturaInfoOnly;
import com.telcom.ups.data.read.FacturaRead;
import com.telcom.ups.monolitico.anexo.repository.AnexoCrudRepository;
import com.telcom.ups.monolitico.cliente.repository.ClienteCrudRepository;
import com.telcom.ups.monolitico.detalle.repository.DetalleCrudRepository;
import com.telcom.ups.monolitico.detallepago.repository.DetallePagoCrudRepository;
import com.telcom.ups.monolitico.dispositivo.repository.DeviceCrudRepository;
import com.telcom.ups.monolitico.empresa.repository.EmpresaCrudRepository;
import com.telcom.ups.monolitico.factura.mapper.FacturaMapper;
import com.telcom.ups.monolitico.factura.repository.FacturaCrudRepository;
import com.telcom.ups.monolitico.formapago.repository.FormaPagoCrudRepository;
import com.telcom.ups.monolitico.unidad.repository.UnidadCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaCrudRepository facturaCrudRepository;

    @Autowired
    private UnidadCrudRepository unidadCrudRepository;

    @Autowired
    private DetalleCrudRepository detalleCrudRepository;

    @Autowired
    private FormaPagoCrudRepository formaPagoCrudRepository;

    @Autowired
    private AnexoCrudRepository anexoCrudRepository;

    @Autowired
    private DetallePagoCrudRepository detallePagoCrudRepository;

    @Autowired
    private FacturaMapper mapper;

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private EmpresaCrudRepository empresaCrudRepository;

    @Autowired
    private DeviceCrudRepository deviceCrudRepository;

    /**
     * Devuelve un Page de FacturaInfoOnlies que provee el repositorio CRUD
     * Para devolver un Page de FacturaInfoOnlies se realiza el proceso de conversión de objetos con la interfaz ClienteMapper.
     *
     * @param pageable
     * @return page de FacturaInfoOnly
     */
    @Override
    public Page<FacturaInfoOnly> findAll(Pageable pageable) {
        return facturaCrudRepository.findAll(pageable).map(factura -> mapper.toFacturaInfoOnly(factura));
    }

    /**
     * Devuelve una lista de FacturaInfoOnlies que provee el repositorio CRUD
     * Para devolver el listado de FacturaInfoOnlies se busca en la base de datos las facturas por Cliente y por Empresa.
     *
     * @param idCliente
     * @param idEmpresa
     * @return lista de FacturaInfoOnlys
     */
    @Override
    public List<FacturaInfoOnly> getFacturasByClienteEmpresa(Integer idCliente, Integer idEmpresa) {
        return mapper.toFacturaInfoOnlies(facturaCrudRepository.findByClienteIdenAndEmpresaIden(idCliente, idEmpresa));
    }

    /**
     * Devuelve un Optional de FacturaInfo que provee el repositorio CRUD
     * Para devolver un optional de FacturaInfo se realiza el proceso de conversión de objetos con la interfaz FacturaMapper.
     *
     * @param id
     * @return optional de FacturaInfo
     */
    @Override
    public Optional<FacturaInfo> getOne(Integer id) {
        Optional<Factura> facturaDB = facturaCrudRepository.findById(id);
        if (facturaDB.isPresent()) {
            return facturaDB.map(factura -> mapper.toFacturaInfo(factura));
        }
        throw new BadRequestException("Factura con id " + id + " no encontrada");
    }

    /**
     * Devuelve un boolean que provee el repositorio CRUD
     * Para guardar una factura previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición.
     *
     * @param empresaId
     * @param clienteId
     * @param facturaDTO
     * @return boolean
     */
    @Override
    public boolean save(Integer empresaId, Integer clienteId, FacturaDTO facturaDTO) {
        String validator = validarFactura(facturaDTO);
        if (validator.equals("OK")) {
            Optional<Empresa> empresaDB = empresaCrudRepository.findById(empresaId);
            if (empresaDB.isPresent()) {
                Optional<Cliente> clienteDB = clienteCrudRepository.findById(clienteId);
                if (clienteDB.isPresent()) {
                    Optional<Dispositivo> dispositivoDB = deviceCrudRepository.findByDeveui(facturaDTO.getDeveui());
                    if (dispositivoDB.isPresent()) {
                        String mes = String.valueOf(facturaDTO.getFechaEmision().getMonth().getValue());
                        if (Integer.parseInt(mes) < 10) mes = "0" + mes;
                        String anio = String.valueOf(facturaDTO.getFechaEmision().getYear());
                        Optional<Factura> facturaDB = facturaCrudRepository.findByFechaAnio(mes, anio, facturaDTO.getDeveui());
                        if (!facturaDB.isPresent()) {
                            Factura factura = mapper.toFactura(facturaDTO);
                            factura.setCliente(clienteDB.get());
                            factura.setEmpresa(empresaDB.get());
                            factura.setDeveui(dispositivoDB.get().getDeveui());
                            Factura facturaSave = facturaCrudRepository.save(factura);
                            if (facturaSave.getIden() > 0) {
                                //GUARDO DETALLES DE FACTURA
                                for (int i = 0; i < facturaDTO.getDetalles().size(); i++) {
                                    DetalleDTO detalleDTO = facturaDTO.getDetalles().get(i);
                                    Dispositivo dispositivo = new Dispositivo();
                                    dispositivo.setIden(detalleDTO.getDispositivoId());
                                    Detalle detalle = new Detalle();
                                    detalle.setCodigo(detalleDTO.getCodigo());
                                    detalle.setDescripcion(detalleDTO.getDescripcion());
                                    detalle.setCantidad(detalleDTO.getCantidad());
                                    detalle.setImpuesto(detalleDTO.getImpuesto());
                                    detalle.setDescuento(detalleDTO.getDescuento());
                                    detalle.setPrecio(detalleDTO.getPrecio());
                                    detalle.setTotal(detalleDTO.getTotal());
                                    detalle.setDispositivo(dispositivo);
                                    detalle.setFactura(facturaSave);
                                    Unidad unidad = new Unidad();
                                    unidad.setIden(detalleDTO.getUnidad().getIden());
                                    detalle.setUnidad(unidad);
                                    detalleCrudRepository.save(detalle);
                                }
                                //FIN GUARDO DETALLES DE FACTURA

                                //GUARDO DETALLES DE PAGO DE FACTURA
                                for (int i = 0; i < facturaDTO.getPagos().size(); i++) {
                                    DetallePagoDTO detallePagoDTO = facturaDTO.getPagos().get(i);
                                    FormaPago formaPago = new FormaPago();
                                    formaPago.setIden(detallePagoDTO.getFormaPago().getIden());
                                    DetallePago detallePago = new DetallePago();
                                    detallePago.setFormaPago(formaPago);
                                    detallePago.setValor(detallePagoDTO.getValor());
                                    detallePago.setTiempo(detallePagoDTO.getTiempo());
                                    detallePago.setPlazo(detallePagoDTO.getPlazo());
                                    detallePago.setFactura(facturaSave);
                                    detallePagoCrudRepository.save(detallePago);
                                }
                                //FIN GUARDO DETALLES DE PAGO DE FACTURA

                                //GUARDO ANEXOS DE FACTURA
                                if (facturaDTO.getAnexos().size() > 0) {
                                    for (int i = 0; i < facturaDTO.getAnexos().size(); i++) {
                                        AnexoDTO anexoDTO = facturaDTO.getAnexos().get(i);
                                        Anexo anexo = new Anexo();
                                        anexo.setNombre(anexoDTO.getNombre());
                                        anexo.setFactura(facturaSave);
                                        anexoCrudRepository.save(anexo);
                                    }
                                }
                                //FIN GUARDO ANEXOS DE FACTURA
                            }
                            return true;
                        }
                        throw new BadRequestException("Factura con mes " + mes + " del año " + anio + " ya se encuentra generada");
                    }
                    throw new BadRequestException("Dispositivo con deveui " + facturaDTO.getDeveui() + " no encontrado para relacionar con la factura");
                }
                throw new BadRequestException("Cliente con id " + clienteId + " no encontrado para relacionar con la factura");
            }
            throw new BadRequestException("Empresa con id " + empresaId + " no encontrado para relacionar con la factura");
        }
        throw new BadRequestException(validator);
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
        Optional<Factura> facturaDB = facturaCrudRepository.findById(id);
        if (facturaDB.isPresent()) {
            facturaCrudRepository.deleteById(id);
            return true;
        }
        throw new BadRequestException("Factura con id " + id + " no encontrada para eliminar");
    }

    /**
     * no implementado
     *
     * @param facturaDTO
     * @param id
     * @return
     */
    @Override
    public FacturaRead update(FacturaDTO facturaDTO, Integer id) {
        return null;
    }

    /**
     * Devuelve un Page de FacturaInfoOnlies que provee el repositorio CRUD
     * Para devolver un Page de FacturaInfoOnlies se realiza el proceso de conversión de objetos con la interfaz FacturaMapper
     *
     * @param idCliente
     * @param pageable
     * @return page de FacturaInfoOnly
     */
    @Override
    public Page<FacturaInfoOnly> getAllByCliente(Integer idCliente, Pageable pageable) {
        return facturaCrudRepository.getAllByCliente(idCliente, pageable).map(factura -> mapper.toFacturaInfoOnly(factura));
    }

    /**
     * Devuelve un Page de FacturaInfoOnlies que provee el repositorio CRUD
     * Para devolver un Page de FacturaInfoOnlies se realiza el proceso de conversión de objetos con la interfaz FacturaMapper
     *
     * @param idEmpresa
     * @param pageable
     * @return page de FacturaInfoOnly
     */
    @Override
    public Page<FacturaInfoOnly> getAllByEmpresa(Integer idEmpresa, Pageable pageable) {
        return facturaCrudRepository.getAllByEmpresa(idEmpresa, pageable).map(factura -> mapper.toFacturaInfoOnly(factura));
    }

    @Override
    public Page<FacturaInfoOnly> getAllByEmpProtZon(Integer idEmpresa, Integer idProtocolo, Integer idZona, Pageable pageable) {
        return facturaCrudRepository.getAllByEmpProtZon(idEmpresa, idProtocolo, idZona, pageable).map(factura -> mapper.toFacturaInfoOnly(factura));
    }

    @Override
    public Page<FacturaInfoOnly> getAllByEmpProtCli(Integer idEmpresa, Integer idProtocolo, Integer idCliente, Pageable pageable) {
        return facturaCrudRepository.getAllByEmpProtCli(idEmpresa, idProtocolo, idCliente, pageable).map(factura -> mapper.toFacturaInfoOnly(factura));
    }

    /**
     * Validaciones de los atributos de la cabecera de la factura
     *
     * @param facturaDTO
     * @return String
     */
    String validacionesFacturaDTO(FacturaDTO facturaDTO) {
        if (facturaDTO.getFechaEmision() == null || facturaDTO.getFechaEmision().equals("")) {
            return "La fechaEmision no debe ser nula o vacia";
        }
        if (facturaDTO.getDescripcion() == null || facturaDTO.getDescripcion().equals("")) {
            return "La descripcion no debe ser nula o vacia";
        }
        if (facturaDTO.getSubtotal() == null || facturaDTO.getSubtotal() == 0) {
            return "El subtotal no debe ser nulo, vacio o cero";
        }
        if (facturaDTO.getIva() == null) {
            return "El iva no debe ser nulo o vacio";
        }
        if (facturaDTO.getTotal() == null || facturaDTO.getTotal() == 0) {
            return "El total no debe ser nulo, vacio o cero";
        }
        if (facturaDTO.getDetalles() == null || facturaDTO.getDetalles().size() == 0) {
            return "Se necesitan detalles para crear una factura";
        }
        if (facturaDTO.getPagos() == null || facturaDTO.getPagos().size() == 0) {
            return "Se necesitan detalles de pago o pagos para crear una factura";
        }
        return "OK";
    }

    /**
     * Validaciones de los atributos de los detalles de la factura
     *
     * @param detalleDTO
     * @return String
     */
    String validacionesDetalleDTO(DetalleDTO detalleDTO) {
        if (detalleDTO.getCodigo() == null || detalleDTO.getCodigo().equals("")) {
            return "Se neceista el codigo del detalle para crear una factura";
        }
        if (detalleDTO.getDescripcion() == null || detalleDTO.getDescripcion().equals("")) {
            return "Se neceista la descripcion del detalle para crear una factura";
        }
        if (detalleDTO.getPrecio() == null || detalleDTO.getPrecio() == 0 || detalleDTO.getPrecio() == 0.0) {
            return "El precio del detalle debe ser diferente de nulo o cero para crear una factura";
        }
        if (detalleDTO.getTotal() == null || detalleDTO.getTotal() == 0 || detalleDTO.getTotal() == 0.0) {
            return "El total del detalle debe ser diferente de nulo o cero para crear una factura";
        }
        if (detalleDTO.getDispositivoId() == null || detalleDTO.getDispositivoId() == 0) {
            return "El id de dispositivo del detalle debe ser diferente de nulo o cero para crear una factura";
        }
        if (detalleDTO.getUnidad() == null || detalleDTO.getUnidad().getIden() == null || detalleDTO.getUnidad().getIden() == 0) {
            return "El detalle debe tener una unidad para crear una factura";
        }
        Optional<Dispositivo> dispositivoDB = deviceCrudRepository.findById(detalleDTO.getDispositivoId());
        if (!dispositivoDB.isPresent()) {
            return "Dispositivo con id " + detalleDTO.getDispositivoId() + " no encontrado para relacionar con el detalle de la factura";
        }
        Optional<Unidad> unidadDB = unidadCrudRepository.findById(detalleDTO.getUnidad().getIden());
        if (!unidadDB.isPresent()) {
            return "Unidad con id " + detalleDTO.getUnidad().getIden() + " no encontrada para relacionar con el detalle de la factura";
        }
        return "OK";
    }

    /**
     * Validaciones de los atirbutos de los detalles de pago de la factura
     *
     * @param detallePagoDTO
     * @return String
     */
    String validacionesDetallePagoDTO(DetallePagoDTO detallePagoDTO) {
        if (detallePagoDTO.getValor() == null || detallePagoDTO.getValor() == 0 || detallePagoDTO.getValor() == 0.0) {
            return "El valor del detalle de pago debe ser diferente de nulo o cero para crear una factura";
        }
        if (detallePagoDTO.getFormaPago() == null || detallePagoDTO.getFormaPago().getIden() == null || detallePagoDTO.getFormaPago().getIden() == 0) {
            return "El detalle de pago debe tener una forma de pago para crear una factura";
        }
        Optional<FormaPago> formaPagoDB = formaPagoCrudRepository.findById(detallePagoDTO.getFormaPago().getIden());
        if (!formaPagoDB.isPresent()) {
            return "FormaPago con id " + detallePagoDTO.getFormaPago().getIden() + " no encontrado para relacionar con el detalle de pago de la factura";
        }
        return "OK";
    }

    /**
     * Validaciones de los atributos de los anexos de la factura
     *
     * @param anexoDTO
     * @return String
     */
    String validacionesAnexoDTO(AnexoDTO anexoDTO) {
        if (anexoDTO.getNombre() == null || anexoDTO.getNombre().equals("")) {
            return "El anexo debe tener un nombre para poder crear una factura";
        }
        return "OK";
    }

    /**
     * Mediante un ciclo for valido cada objeto detalle a traves del metodo validacionesDetalleDTO
     *
     * @param facturaDTO
     * @return String
     */
    String validarDetalles(FacturaDTO facturaDTO) {
        String res = "";
        for (int i = 0; i < facturaDTO.getPagos().size(); i++) {
            res = validacionesDetalleDTO(facturaDTO.getDetalles().get(i));
            if (!res.equals("OK")) return res;
        }
        return res;
    }

    /**
     * Mediante un ciclo for valido cada objeto detallePago a traves del metodo validacionesDetallePagoDTO
     *
     * @param facturaDTO
     * @return String
     */
    String validarPagos(FacturaDTO facturaDTO) {
        String res = "";
        for (int i = 0; i < facturaDTO.getPagos().size(); i++) {
            res = validacionesDetallePagoDTO(facturaDTO.getPagos().get(i));
            if (!res.equals("OK")) return res;
        }
        return res;
    }

    /**
     * Mediante un ciclo for valido cada objeto anexo a traves del metodo validacionesAnexoDTO
     *
     * @param facturaDTO
     * @return String
     */
    String validarAnexos(FacturaDTO facturaDTO) {
        String res = "";
        for (int i = 0; i < facturaDTO.getAnexos().size(); i++) {
            res = validacionesAnexoDTO(facturaDTO.getAnexos().get(i));
            if (!res.equals("OK")) return res;
        }
        return res;
    }

    /**
     * Mediante comparaciones if valido todos los atributos y objetos que forman parte de una factura
     * esto se realiza mediante la llamada de todos los metodos anteriores de validación
     *
     * @param facturaDTO
     * @return String
     */
    String validarFactura(FacturaDTO facturaDTO) {
        String resFactura = validacionesFacturaDTO(facturaDTO);
        if (resFactura.equals("OK")) {
            String resDetalles = validarDetalles(facturaDTO);
            if (resDetalles.equals("OK")) {
                String resPagos = validarPagos(facturaDTO);
                if (resPagos.equals("OK")) {
                    if (facturaDTO.getAnexos().size() > 0 && facturaDTO.getAnexos() != null) {
                        String resAnexos = validarAnexos(facturaDTO);
                        if (resAnexos.equals("OK")) {
                            return "OK";
                        }
                        return resAnexos;
                    }
                    return "OK";
                }
                return resPagos;
            }
            return resDetalles;
        }
        return resFactura;
    }
}
