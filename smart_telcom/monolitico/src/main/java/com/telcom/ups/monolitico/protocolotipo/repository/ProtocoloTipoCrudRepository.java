package com.telcom.ups.monolitico.protocolotipo.repository;

import com.telcom.ups.data.entities.ProtocoloTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProtocoloTipoCrudRepository extends JpaRepository<ProtocoloTipo, Integer> {

    Optional<ProtocoloTipo> findByNombre(String nombre);

    @Query(
            value = "select tpt.* from tb_protocolo_tipos tpt " +
//                    "join tb_integraciones ti on ti.itg_prtipo_id = tpt.prt_iden " +
//                    "join tb_perfilservicio tp on tp.pes_itg_id = ti.itg_iden  " +
                    "join tb_applications ta2 on ta2.app_pti_id = tpt.prt_iden " +
                    "where ta2.app_iden = :idApplication",
            nativeQuery = true
    )
    ProtocoloTipo findByApplicationId(Integer idApplication);
}
