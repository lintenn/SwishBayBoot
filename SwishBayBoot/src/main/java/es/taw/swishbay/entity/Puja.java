/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.swishbay.entity;

import es.taw.swishbay.dto.PujaDTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "PUJA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puja.findAll", query = "SELECT p FROM Puja p")
    , @NamedQuery(name = "Puja.findByComprador", query = "SELECT p FROM Puja p WHERE p.pujaPK.comprador = :comprador")
    , @NamedQuery(name = "Puja.findByProducto", query = "SELECT p FROM Puja p WHERE p.pujaPK.producto = :producto")
    , @NamedQuery(name = "Puja.findByPrecio", query = "SELECT p FROM Puja p WHERE p.pujaPK.precio = :precio")
    , @NamedQuery(name = "Puja.findByFecha", query = "SELECT p FROM Puja p WHERE p.fecha = :fecha")})
public class Puja implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PujaPK pujaPK;
    @Basic(optional = false)
    @Column(name = "FECHA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "COMPRADOR", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "PRODUCTO", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto1;

    public Puja() {
    }

    public Puja(PujaPK pujaPK) {
        this.pujaPK = pujaPK;
    }

    public Puja(PujaPK pujaPK, Date fecha) {
        this.pujaPK = pujaPK;
        this.fecha = fecha;
    }

    public Puja(int comprador, int producto, double precio) {
        this.pujaPK = new PujaPK(comprador, producto, precio);
    }

    public PujaPK getPujaPK() {
        return pujaPK;
    }

    public void setPujaPK(PujaPK pujaPK) {
        this.pujaPK = pujaPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto1() {
        return producto1;
    }

    public void setProducto1(Producto producto1) {
        this.producto1 = producto1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pujaPK != null ? pujaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puja)) {
            return false;
        }
        Puja other = (Puja) object;
        if ((this.pujaPK == null && other.pujaPK != null) || (this.pujaPK != null && !this.pujaPK.equals(other.pujaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "swishbay.entity.Puja[ pujaPK=" + pujaPK + " ]";
    }

    public PujaDTO toDTO () {    // Galo
        PujaDTO dto = new PujaDTO();

        dto.setComprador(usuario.toDTO());
        dto.setFecha(fecha);
        dto.setPrecio(pujaPK.getPrecio());
        dto.setProducto(producto1.getId());


        return dto;
    }
    
}
