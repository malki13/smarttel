package com.telcom.ups.data.modelo;

import java.sql.Date;

public class Error {

    private int id;
    private Date fecha;
    private int dataUpRetransmission;
    private int dataUpMic;
    private int dataOta;
    private int dataCodec;

    public Error() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDataUpRetransmission() {
        return dataUpRetransmission;
    }

    public void setDataUpRetransmission(int dataUpRetransmission) {
        this.dataUpRetransmission = dataUpRetransmission;
    }

    public int getDataUpMic() {
        return dataUpMic;
    }

    public void setDataUpMic(int dataUpMic) {
        this.dataUpMic = dataUpMic;
    }

    public int getDataOta() {
        return dataOta;
    }

    public void setDataOta(int dataOta) {
        this.dataOta = dataOta;
    }

    public int getDataCodec() {
        return dataCodec;
    }

    public void setDataCodec(int dataCodec) {
        this.dataCodec = dataCodec;
    }
}
