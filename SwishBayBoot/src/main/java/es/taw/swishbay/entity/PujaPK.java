/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.swishbay.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Luis
 */
@Embeddable
public class PujaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "COMPRADOR")
    private int comprador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUCTO")
    private int producto;

    public PujaPK() {
    }

    public PujaPK(int comprador, int producto) {
        this.comprador = comprador;
        this.producto = producto;
    }

    public int getComprador() {
        return comprador;
    }

    public void setComprador(int comprador) {
        this.comprador = comprador;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) comprador;
        hash += (int) producto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PujaPK)) {
            return false;
        }
        PujaPK other = (PujaPK) object;
        if (this.comprador != other.comprador) {
            return false;
        }
        if (this.producto != other.producto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "swishbay.entity.PujaPK[ comprador=" + comprador + ", producto=" + producto + " ]";
    }
    
}
