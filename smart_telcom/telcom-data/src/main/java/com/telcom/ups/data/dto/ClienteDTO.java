package com.telcom.ups.data.dto;

import java.util.List;

public class ClienteDTO {

    private List<DispositivoDTO> dispositivos;

    public ClienteDTO() {
    }

    public List<DispositivoDTO> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<DispositivoDTO> dispositivos) {
        this.dispositivos = dispositivos;
    }
}
