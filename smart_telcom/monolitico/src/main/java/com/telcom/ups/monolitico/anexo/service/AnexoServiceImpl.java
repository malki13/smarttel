package com.telcom.ups.monolitico.anexo.service;

import com.telcom.ups.data.dto.AnexoDTO;
import com.telcom.ups.data.entities.Anexo;
import com.telcom.ups.data.read.AnexoRead;
import com.telcom.ups.monolitico.anexo.mapper.AnexoMapper;
import com.telcom.ups.monolitico.anexo.repository.AnexoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnexoServiceImpl implements AnexoService{

    @Autowired
    private AnexoCrudRepository anexoCrudRepository;

    @Autowired
    private AnexoMapper mapper;

    /**
     * Devuelve un Page de AnexoReads que provee el repositorio
     * Para devolver un Page de AnexoReads se realiza el proceso de conversión de objetos con la interfaz AnexoMapper
     *
     * @param pageable
     * @return page de AnexoRead
     */
    @Override
    public Page<AnexoRead> findAll(Pageable pageable) {
        return anexoCrudRepository.findAll(pageable).map(anexo -> mapper.toAnexoRead(anexo));
    }

    /**
     * Devuelve un Optional de AnexoRead que provee el repositorio
     * Para devolver un Optional de AnexoRead se realiza el proceso de conversión de objetos con la interfaz AnexoMapper
     *
     * @param id
     * @return optional de AnexoRead
     */
    @Override
    public Optional<AnexoRead> getOne(Integer id) {
        return anexoCrudRepository.findById(id).map(anexo -> mapper.toAnexoRead(anexo));
    }

    /**
     * Devuelve un AnexoRead que provee el repositorio
     * Para devolver un AnexoRead se realiza el proceso de conversión de objetos con la interfaz AnexoMapper
     *
     * @param anexoDTO
     * @return AnexoRead
     */
    @Override
    public AnexoRead save(AnexoDTO anexoDTO) {
        Anexo anexo = mapper.toAnexo(anexoDTO);
        return mapper.toAnexoRead(anexoCrudRepository.save(anexo));
    }

    /**
     * Eliminar la informacion mediante el repositorio
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        anexoCrudRepository.deleteById(id);
    }

    /**
     * Devuelve un AnexoRead que provee el repositorio
     * Para devolver un AnexoRead se realiza el proceso de conversión de objetos con la interfaz AnexoMapper
     *
     * @param anexoDTO
     * @param id
     * @return AnexoRead
     */
    @Override
    public AnexoRead update(AnexoDTO anexoDTO, Integer id) {
        Anexo newAnexo = mapper.toAnexo(anexoDTO);
        Optional<Anexo> anexo = anexoCrudRepository.findById(id);
        if (anexo.isPresent()) {
            Anexo ane = anexo.get();
            ane.setNombre(newAnexo.getNombre());
            return mapper.toAnexoRead(anexoCrudRepository.save(ane));
        }
        return null;
    }
}
