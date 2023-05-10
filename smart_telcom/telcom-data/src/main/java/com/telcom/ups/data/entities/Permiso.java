package com.telcom.ups.data.entities;

//@Entity
//@Table(name = "tb_Permisos")
public class Permiso {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "per_iden", nullable = false, unique = true)
//    private Integer iden;
//
//    @Column(name = "per_accion", nullable = false, length = 50)
//    @NotBlank(message = "La acción no puede estar vacía")
//    @Size(max = 50, message = "La longitud máxima es de 50 caracteres")
//    private String accion;
//
//    @ManyToOne
//    @JoinColumn(name = "per_stat", insertable = true, updatable = true)
//    private Estatus estatus;
//
//    @JoinTable(
//            name = "tb_Permisos_Menus",
//            joinColumns = @JoinColumn(name = "pme_per_id", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "pme_men_id", nullable = false)
//    )
//    @ManyToMany(fetch = FetchType.LAZY)
//    private List<Menu> menus;
//
//    @Column(name = "per_rolid", nullable = false)
//    private Integer rolId;
//
//    @Column(name = "per_fecr", nullable = false)
//    private LocalDateTime createdAt = LocalDateTime.now();
//
//    @Column(name = "per_feac")
//    private LocalDateTime updatedAt;
//
//    @Column(name = "per_feeli")
//    private LocalDateTime deletedAt;
//
//    public Permiso() {
//    }
//
//    public Integer getIden() {
//        return iden;
//    }
//
//    public void setIden(Integer iden) {
//        this.iden = iden;
//    }
//
//    public String getAccion() {
//        return accion;
//    }
//
//    public void setAccion(String accion) {
//        this.accion = accion;
//    }
//
//    public Estatus getEstatus() {
//        return estatus;
//    }
//
//    public void setEstatus(Estatus estatus) {
//        this.estatus = estatus;
//    }
//
//    public List<Menu> getMenus() {
//        return menus;
//    }
//
//    public void setMenus(List<Menu> menus) {
//        this.menus = menus;
//    }
//
//    public Integer getRolId() {
//        return rolId;
//    }
//
//    public void setRolId(Integer rolId) {
//        this.rolId = rolId;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public LocalDateTime getDeletedAt() {
//        return deletedAt;
//    }
//
//    public void setDeletedAt(LocalDateTime deletedAt) {
//        this.deletedAt = deletedAt;
//    }
}
