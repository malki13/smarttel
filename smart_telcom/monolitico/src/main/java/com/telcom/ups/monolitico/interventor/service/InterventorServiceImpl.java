package com.telcom.ups.monolitico.interventor.service;

import com.telcom.ups.data.dto.InterventorDTO;
import com.telcom.ups.data.entities.Interventor;
import com.telcom.ups.data.entities.InterventorTipo;
import com.telcom.ups.data.entities.Usuario;
import com.telcom.ups.data.info.InterventorInfo;
import com.telcom.ups.data.read.InterventorRead;
import com.telcom.ups.monolitico.interventor.mapper.InterventorMapper;
import com.telcom.ups.monolitico.interventor.repository.InterventorCrudRepository;
import com.telcom.ups.monolitico.interventortipo.repository.InterventorTipoCrudRepository;
import com.telcom.ups.monolitico.usuario.repository.UsuarioCrudRepository;
import com.telcom.ups.monolitico.util.response.error.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class InterventorServiceImpl implements InterventorService {

    @Autowired
    private InterventorCrudRepository interventorCrudRepository;

    @Autowired
    private InterventorTipoCrudRepository interventorTipoCrudRepository;

    @Autowired
    private InterventorMapper interventorMapper;

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private Environment env;

    String rootPath = "/home/uploads";

    /**
     * Devuelve un Page de InterventorInfos que provee el repositorio
     * Para devolver un Page de InterventorInfos se realiza el proceso de conversión de objetos con la interfaz InterventorMapper
     *
     * @param pageable
     * @return page de InterventorInfos
     */
    @Override
    public Page<InterventorInfo> findAll(Pageable pageable) {
        return interventorCrudRepository.findAll(pageable).map(interventor -> interventorMapper.toInterventorInfo(interventor));
    }

    /**
     * Devuelve un Optional de InterventorInfo que provee el repositorio
     * Para devolver un Optional de InterventorInfo se realiza el proceso de conversión de objetos con la interfaz InterventorMapper
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return optional de InterventorInfo
     */
    @Override
    public Optional<InterventorInfo> getOne(Integer id) {
        Optional<Interventor> interventorDB = interventorCrudRepository.findById(id);
        if (interventorDB.isPresent()) {
            return interventorDB.map(interventor -> interventorMapper.toInterventorInfo(interventor));
        } else {
            throw new BadRequestException("Interventor con id " + id + " no encontrado");
        }
    }

    /**
     * Devuelve un InterventorRead que provee el repositorio
     * Para devolver un InterventorRead se realiza el proceso de conversión de objetos con la interfaz InterventorMapper
     * Para guardar un interventor previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     * El metodo realiza el proceso de conversion de base64 a una imagen dependiendo del tipo de extensión que se reciba en la petición
     *
     * @param interventorTipoId
     * @param interventorDTO
     * @return InterventorRead
     * @throws Exception
     */
    @Override
    public InterventorRead save(Integer interventorTipoId, InterventorDTO interventorDTO) throws Exception {
        Optional<InterventorTipo> interventorTipo = interventorTipoCrudRepository.findById(interventorTipoId);
        if (interventorTipo.isPresent()) {
            Optional<Interventor> interventoDB = interventorCrudRepository.findByCodigo(interventorDTO.getCodigo());
            if (!interventoDB.isPresent()) {
                if (interventorDTO.getBase64() != null) {
                    if (!interventorDTO.getBase64().isEmpty()) {
                        String[] base64 = interventorDTO.getBase64().split(",");
                        String extension;
                        switch (base64[0]) {
                            case "data:image/jpeg;base64":
                                extension = ".jpeg";
                                break;
                            case "data:image/png;base64":
                                extension = ".png";
                                break;
                            default:
                                extension = ".jpg";
                                break;
                        }
                        String uniqueFilename = UUID.randomUUID().toString() + extension;
                        String rutaCompleta = rootPath + "/" + uniqueFilename;
                        byte[] bytes = DatatypeConverter.parseBase64Binary(base64[1]);
                        File outputfile = new File(rutaCompleta);
                        try {
                            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputfile));
                            outputStream.write(bytes);
                            outputStream.close();
                        } catch (Exception exception) {
                            throw new Exception(exception.getMessage());
                        }
                        String url = getUrlRemota() + "/" + uniqueFilename;
                        interventorDTO.setNombreImagen(uniqueFilename);
                        interventorDTO.setImagen(url);
                    }
                }


                Interventor interventor = interventorMapper.toInterventor(interventorDTO);
                interventor.setInterventorTipo(interventorTipo.get());
                //interventor.setFechaNacimiento(sumarDiasFecha(interventor.getFechaNacimiento(), 1));
                //System.out.println("FECHA DE NACIMIENTO ANTES DE GUARDAR " + interventor.getFechaNacimiento());
                return interventorMapper.toInterventorRead(interventorCrudRepository.save(interventor));
            }
            throw new BadRequestException("Interventor con codigo " + interventoDB.get().getCodigo() + " ya esta registrado");
        }
        throw new BadRequestException("Tipo de Interventor con id " + interventorTipoId + " no encontrado  para relacionar al interventor");
    }

    /**
     * Devuelve un boolean al borrar la informacion mediante el repositorio
     * Si el objeto no esta presente en la base de datos se lanza una excepcion de BadRequestException como respuesra de la petición
     *
     * @param id
     * @return boolean
     */
    @Override
    public boolean delete(Integer id) {
        try {
            Optional<Interventor> interventorDB = interventorCrudRepository.findById(id);
            if (interventorDB.isPresent()) {
                String rootPath = "/home/uploads";
                Path rutaCompleta = Paths.get(rootPath + "/" + interventorDB.get().getNombreImagen());
                File image = rutaCompleta.toFile();
                if (image.exists()) image.delete();
                interventorCrudRepository.deleteById(id);
                return true;
            }
            throw new BadRequestException("Interventor con id " + id + " no encontrado para eliminar");
        } catch (Exception exception) {
            throw new BadRequestException("El interventor que intenta eliminar cuenta con objetos relacionados. Previo a esto, para que " +
                    "el proceso de eliminación sea exitoso el interventor no debe contar con objetos relacionados al mismo.");
        }
    }

    /**
     * Devuelve un InterventorInfo que provee el repositorio
     * Para devolver un InterventorInfo se realiza el proceso de conversión de objetos con la interfaz InterventorMapper
     * Para actualizar un interventor previo al proceso, se realizan las validaciones para el almacén de datos correctos
     * respondiendo con una excepcion de BadRequestException como respuesra de la petición
     * El metodo realiza el proceso de conversion de base64 a una imagen dependiendo del tipo de extensión que se reciba en la petición
     *
     * @param interventorId
     * @param interventorTipoId
     * @param interventorDTO
     * @return InterventorInfo
     * @throws Exception
     */
    @Override
    public InterventorInfo update(Integer interventorId, Integer interventorTipoId, InterventorDTO interventorDTO) throws Exception {
        Interventor newInterventor = interventorMapper.toInterventor(interventorDTO);
        Optional<Interventor> interventorDB = interventorCrudRepository.findById(interventorId);
        if (interventorDB.isPresent()) {
            if (interventorDTO.getBase64() != null) {
                if (!interventorDTO.getBase64().isEmpty()) {
                    Path rutaCompleta2 = Paths.get(rootPath + "/" + interventorDB.get().getNombreImagen());
                    File image = rutaCompleta2.toFile();
                    if (image.exists()) image.delete();
                    String[] base64 = interventorDTO.getBase64().split(",");
                    String extension;
                    switch (base64[0]) {
                        case "data:image/jpeg;base64":
                            extension = ".jpeg";
                            break;
                        case "data:image/png;base64":
                            extension = ".png";
                            break;
                        default:
                            extension = ".jpg";
                            break;
                    }
                    String uniqueFilename = UUID.randomUUID().toString() + extension;
                    String rutaCompleta = rootPath + "/" + uniqueFilename;
                    byte[] bytes = DatatypeConverter.parseBase64Binary(base64[1]);
                    File outputfile = new File(rutaCompleta);
                    try {
                        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputfile));
                        outputStream.write(bytes);
                        outputStream.close();
                    } catch (Exception exception) {
                        throw new Exception(exception.getMessage());
                    }
                    String url = getUrlRemota() + "/" + uniqueFilename;
                    newInterventor.setNombreImagen(uniqueFilename);
                    newInterventor.setImagen(url);
                } else {
                    newInterventor.setNombreImagen(interventorDB.get().getNombreImagen());
                    newInterventor.setImagen(interventorDB.get().getImagen());
                }
            } else {
                newInterventor.setNombreImagen(interventorDB.get().getNombreImagen());
                newInterventor.setImagen(interventorDB.get().getImagen());
            }
            Optional<InterventorTipo> interventorTipo = interventorTipoCrudRepository.findById(interventorTipoId);
            if (interventorTipo.isPresent()) {
                Interventor interv = interventorDB.get();
                Optional<Usuario> usuarioDB = usuarioCrudRepository.findByNombre(interv.getEmail());
                if (!usuarioDB.isPresent()) {
                    interv.setEmail(newInterventor.getEmail());
                }
                interv.setCodigo(newInterventor.getCodigo());
                interv.setInterventorTipo(interventorTipo.get());
                interv.setNombre(newInterventor.getNombre());
                interv.setApellido(newInterventor.getApellido());
                interv.setDireccion(newInterventor.getDireccion());
                interv.setTelefono(newInterventor.getTelefono());
                interv.setReferencia(newInterventor.getReferencia());
                interv.setUpdatedAt(LocalDateTime.now());
                interv.setFechaNacimiento(newInterventor.getFechaNacimiento());
                interv.setImagen(newInterventor.getImagen());
                interv.setNombreImagen(newInterventor.getNombreImagen());
                //interv.setFechaNacimiento(sumarDiasFecha(interv.getFechaNacimiento(), 1));
                return interventorMapper.toInterventorInfo(interventorCrudRepository.save(interv));
            }
            throw new BadRequestException("Tipo de Interventor con id " + interventorTipoId + " no encontrado para relacionar al interventor");
        }
        throw new BadRequestException("Interventor con id " + interventorId + " no encontrado para actualizar");
    }

    /**
     * Devuelve una lista de InterventorInfos que provee el repositorio
     * Para devolver una lista de InterventorInfos se realiza el proceso de conversión de objetos con la interfaz InterventorMapper
     *
     * @param codigo
     * @return lista de InterventorInfos
     */
    @Override
    public List<InterventorInfo> searchByCodigo(String codigo) {
        return interventorMapper.toInterventorInfos(interventorCrudRepository.searchByCodigo(codigo));
    }

    /**
     * Devuelve una lista de InterventorInfos que provee el repositorio
     * Para devolver una lista de InterventorInfos se realiza el proceso de conversión de objetos con la interfaz InterventorMapper
     *
     * @param apellido
     * @return lista de InterventorInfos
     */
    @Override
    public List<InterventorInfo> searchByApellido(String apellido) {
        return interventorMapper.toInterventorInfos(interventorCrudRepository.searchByApellido(apellido));
    }

    /**
     * Devuelve un optional de InterventorInfo que provee el repositorio
     * Para devolver un optional de InterventorInfo se realiza el proceso de conversión de objetos con la interfaz InterventorMapper
     *
     * @param codigo
     * @return optinal de InterventorInfo
     */
    @Override
    public Optional<InterventorInfo> findByCodigo(String codigo) {
        Optional<Interventor> interventorDB = interventorCrudRepository.findByCodigo(codigo);
        if (interventorDB.isPresent()) {
            return interventorDB.map(interventor -> interventorMapper.toInterventorInfo(interventor));
        }
        throw new BadRequestException("Interventor con codigo " + codigo + " no encontrado");

    }

    /**
     * Formo la url de una imagen en base al servidor local para hacer test de guardado de imagen
     *
     * @return String
     */
    public String getUrlLocal() {
        String puerto = env.getProperty("server.port");
        String servidor = env.getProperty("local");
        String context = env.getProperty("server.servlet.context-path");
        return "http://" + servidor + ":" + puerto + context + "/uploads";
    }

    /**
     * Formo la url de una imagen en base al servidor remoto para el guardado de imagen en producción
     *
     * @return String
     */
    public String getUrlRemota() {
        String puerto = env.getProperty("server.port");
        String servidor = env.getProperty("remoto");
        String context = env.getProperty("server.servlet.context-path");
        return "http://" + servidor + ":" + puerto + context + "/uploads";
    }

    public Date sumarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

}
