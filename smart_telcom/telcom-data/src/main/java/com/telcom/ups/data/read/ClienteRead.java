package com.telcom.ups.data.read;

import com.telcom.ups.data.dto.DispositivoDTO;
import com.telcom.ups.data.info.InterventorInfo;

import java.util.List;

public class ClienteRead {

    private Integer iden;
    private InterventorInfo interventor;
    private EmpresaRead estatus;
    private EmpresaRead empresa;
    private List<DispositivoDTO> dispositivos;

    public ClienteRead() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public InterventorInfo getInterventor() {
        return interventor;
    }

    public void setInterventor(InterventorInfo interventor) {
        this.interventor = interventor;
    }

    public EmpresaRead getEstatus() {
        return estatus;
    }

    public void setEstatus(EmpresaRead estatus) {
        this.estatus = estatus;
    }

    public EmpresaRead getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaRead empresa) {
        this.empresa = empresa;
    }

    public List<DispositivoDTO> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<DispositivoDTO> dispositivos) {
        this.dispositivos = dispositivos;
    }
}
