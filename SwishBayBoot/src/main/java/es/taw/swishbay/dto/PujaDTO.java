/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.taw.swishbay.dto;

import java.util.Date;

/**
 *
 * @author Galo
 */
public class PujaDTO {
    
    private UsuarioDTO comprador;
    private int producto;
    private double precio;
    private Date fecha;

    /**
     * @return the comprador
     */
    public UsuarioDTO getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(UsuarioDTO comprador) {
        this.comprador = comprador;
    }

    /**
     * @return the producto
     */
    public int getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(int producto) {
        this.producto = producto;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
    
}
