package com.telcom.ups.data.dto;

public class PermisoMenuDTO {


    private Integer iden;
    private MenuDTO menu;
    private RolDTO rol;
    private PermisoDTO permiso;

    public PermisoMenuDTO() {
    }

    public Integer getIden() {
        return iden;
    }

    public void setIden(Integer iden) {
        this.iden = iden;
    }

    public MenuDTO getMenu() {
        return menu;
    }

    public void setMenu(MenuDTO menu) {
        this.menu = menu;
    }

    public RolDTO getRol() {
        return rol;
    }

    public void setRol(RolDTO rol) {
        this.rol = rol;
    }

    public PermisoDTO getPermiso() {
        return permiso;
    }

    public void setPermiso(PermisoDTO permiso) {
        this.permiso = permiso;
    }
}
