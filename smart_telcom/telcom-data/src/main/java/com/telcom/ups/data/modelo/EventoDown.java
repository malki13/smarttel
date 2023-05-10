package com.telcom.ups.data.modelo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EventoDown {

    private int idgen;
    private String iddb;
    private String nombreDispositivo;
    private Timestamp fecha;
    private String applicationId;
    private Integer fcnt;

    public EventoDown() {
    }
}
