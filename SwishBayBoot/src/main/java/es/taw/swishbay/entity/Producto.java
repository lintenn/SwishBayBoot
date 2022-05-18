/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.swishbay.entity;

import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.dto.PujaDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findById", query = "SELECT p FROM Producto p WHERE p.id = :id")
    , @NamedQuery(name = "Producto.findByTitulo", query = "SELECT p FROM Producto p WHERE p.titulo = :titulo")
    , @NamedQuery(name = "Producto.findByPrecioSalida", query = "SELECT p FROM Producto p WHERE p.precioSalida = :precioSalida")
    , @NamedQuery(name = "Producto.findByFinPuja", query = "SELECT p FROM Producto p WHERE p.finPuja = :finPuja")
    , @NamedQuery(name = "Producto.findByEnPuja", query = "SELECT p FROM Producto p WHERE p.enPuja = :enPuja")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TITULO")
    private String titulo;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_SALIDA")
    private double precioSalida;
    @Column(name = "FIN_PUJA")
    @Temporal(TemporalType.DATE)
    private Date finPuja;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "FOTO")
    private String foto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EN_PUJA")
    private short enPuja;
    @ManyToMany(mappedBy = "productoList")
    private List<Usuario> usuarioList;
    @JoinColumn(name = "CATEGORIA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Categoria categoria;
    @JoinColumn(name = "COMPRADOR", referencedColumnName = "ID")
    @ManyToOne
    private Usuario comprador;
    @JoinColumn(name = "VENDEDOR", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario vendedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto1")
    private List<Puja> pujaList;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(Integer id, String titulo, double precioSalida, short enPuja) {
        this.id = id;
        this.titulo = titulo;
        this.precioSalida = precioSalida;
        this.enPuja = enPuja;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioSalida() {
        return precioSalida;
    }

    public void setPrecioSalida(double precioSalida) {
        this.precioSalida = precioSalida;
    }

    public Date getFinPuja() {
        return finPuja;
    }

    public void setFinPuja(Date finPuja) {
        this.finPuja = finPuja;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public short getEnPuja() {
        return enPuja;
    }

    public void setEnPuja(short enPuja) {
        this.enPuja = enPuja;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    @XmlTransient
    public List<Puja> getPujaList() {
        return pujaList;
    }

    public void setPujaList(List<Puja> pujaList) {
        this.pujaList = pujaList;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "swishbay.entity.Producto[ id=" + id + " ]";
    }
    
    public ProductoDTO toDTO () { // Galo
        ProductoDTO dto = new ProductoDTO();
        
        dto.setId(id);
        dto.setTitulo(titulo);
        dto.setDescripcion(descripcion);
        dto.setFinPuja(finPuja);
        dto.setPrecioSalida(precioSalida);
        dto.setFoto(foto);
        dto.setEnPuja(enPuja);
        dto.setCategoria(categoria.toDTO());
        if(comprador!=null)
            dto.setComprador(comprador.toDTO());
        dto.setVendedor(vendedor.toDTO());
        List<PujaDTO> listaDTO = null;
        if (pujaList != null) {
            listaDTO = new ArrayList<>();
            for (Puja puja : pujaList) {
                listaDTO.add(puja.toDTO());
            }
        }
        dto.setPujaList(listaDTO);
        
        return dto;
    }
    
}
