package com.telcom.ups.data.modelo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EventoJoin {

    private int idgen;
    private String iddb;
    private String nombreDispositivo;
    private Timestamp fecha;
    private String applicationId;

    public EventoJoin() {
    }
}
