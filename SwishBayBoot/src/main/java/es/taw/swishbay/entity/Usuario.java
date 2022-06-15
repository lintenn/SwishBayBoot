/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.swishbay.entity;

import es.taw.swishbay.dto.MensajeDTO;
import es.taw.swishbay.dto.UsuarioDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Luis
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
        , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
        , @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo")
        , @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password")
        , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
        , @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos")
        , @NamedQuery(name = "Usuario.findByDomicilio", query = "SELECT u FROM Usuario u WHERE u.domicilio = :domicilio")
        , @NamedQuery(name = "Usuario.findByFechaNacimiento", query = "SELECT u FROM Usuario u WHERE u.fechaNacimiento = :fechaNacimiento")
        , @NamedQuery(name = "Usuario.findBySexo", query = "SELECT u FROM Usuario u WHERE u.sexo = :sexo")
        , @NamedQuery(name = "Usuario.findByCiudad", query = "SELECT u FROM Usuario u WHERE u.ciudad = :ciudad")
        , @NamedQuery(name = "Usuario.findBySaldo", query = "SELECT u FROM Usuario u WHERE u.saldo = :saldo")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "CORREO", nullable = false, length = 45)
    private String correo;
    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false, length = 45)
    private String password;
    @Basic(optional = false)
    @Column(name = "NOMBRE", nullable = false, length = 45)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDOS", nullable = false, length = 45)
    private String apellidos;
    @Column(name = "DOMICILIO", length = 100)
    private String domicilio;
    @Basic(optional = false)
    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "SEXO", length = 5)
    private String sexo;
    @Column(name = "CIUDAD", length = 45)
    private String ciudad;
    @Basic(optional = false)
    @Column(name = "SALDO", nullable = false)
    private double saldo;
    @JoinTable(name = "FAVORITO", joinColumns = {
            @JoinColumn(name = "COMPRADOR", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "PRODUCTO", referencedColumnName = "ID")})
    @ManyToMany
    private List<Producto> productoList;
    @JoinTable(name = "GRUPOCOMPRADOR", joinColumns = {
            @JoinColumn(name = "COMPRADOR", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "GRUPO", referencedColumnName = "ID")})
    @ManyToMany
    private List<Grupo> grupoList;
    @JoinTable(name = "MENSAJECOMPRADOR", joinColumns = {
            @JoinColumn(name = "COMPRADOR", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "MENSAJE", referencedColumnName = "ID")})
    @ManyToMany
    private List<Mensaje> mensajeList;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Categoria> categoriaList;
    @OneToMany(mappedBy = "comprador")
    private List<Producto> productoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedor")
    private List<Producto> productoList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "marketing")
    private List<Grupo> grupoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Puja> pujaList;
    @JoinColumn(name = "ROL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private RolUsuario rol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "marketing")
    private List<Mensaje> mensajeList1;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String correo, String password, String nombre, String apellidos, Date fechaNacimiento, double saldo) {
        this.id = id;
        this.correo = correo;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.saldo = saldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @XmlTransient
    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    @XmlTransient
    public List<Mensaje> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<Mensaje> mensajeList) {
        this.mensajeList = mensajeList;
    }

    @XmlTransient
    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    @XmlTransient
    public List<Producto> getProductoList1() {
        return productoList1;
    }

    public void setProductoList1(List<Producto> productoList1) {
        this.productoList1 = productoList1;
    }

    @XmlTransient
    public List<Producto> getProductoList2() {
        return productoList2;
    }

    public void setProductoList2(List<Producto> productoList2) {
        this.productoList2 = productoList2;
    }

    @XmlTransient
    public List<Grupo> getGrupoList1() {
        return grupoList1;
    }

    public void setGrupoList1(List<Grupo> grupoList1) {
        this.grupoList1 = grupoList1;
    }

    @XmlTransient
    public List<Puja> getPujaList() {
        return pujaList;
    }

    public void setPujaList(List<Puja> pujaList) {
        this.pujaList = pujaList;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    @XmlTransient
    public List<Mensaje> getMensajeList1() {
        return mensajeList1;
    }

    public void setMensajeList1(List<Mensaje> mensajeList1) {
        this.mensajeList1 = mensajeList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "swishbay.entity.Usuario[ id=" + id + " ]";
    }
    
    public UsuarioDTO toDTO () {    // Luis
        UsuarioDTO dto = new UsuarioDTO();
        
        dto.setApellidos(apellidos);
        dto.setCiudad(ciudad);
        dto.setCorreo(correo);
        dto.setDomicilio(domicilio);
        dto.setFechaNacimiento(fechaNacimiento);
        dto.setId(id);
        dto.setNombre(nombre);
        dto.setPassword(password);
        dto.setRol(rol.toDTO());
        dto.setSaldo(saldo);
        dto.setSexo(sexo);
        List<Integer> listaDTO = null;
        if (categoriaList != null) {
            listaDTO = new ArrayList<>();
            for (Categoria categoria : categoriaList) {
                listaDTO.add(categoria.getId());
            }
        }
        dto.setCategoriaList(listaDTO);
        
        List<Integer> favoritos = null;
        if(productoList != null){
            favoritos = new ArrayList<>();
            for(Producto producto : productoList){
                favoritos.add(producto.getId());
            }
        }
        dto.setFavoritos(favoritos);
        List<MensajeDTO> mensajeDTOList = null;
        if(mensajeList != null){
            mensajeDTOList = new ArrayList<>();
            for(Mensaje mensaje : mensajeList){
                mensajeDTOList.add(mensaje.toDTO());
            }
        }
        dto.setMensajeList(mensajeDTOList);

        return dto;        
    }
}