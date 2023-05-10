package com.telcom.ups.data.modelo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EventoError {

    private int idgen;
    private String iddb;
    private String nombreDispositivo;
    private Timestamp fecha;
    private String applicationId;
    private String type;
    private String error;
    private Integer fcnt;

    public EventoError() {
    }
}
