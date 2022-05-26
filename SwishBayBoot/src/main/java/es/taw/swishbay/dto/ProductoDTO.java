/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.taw.swishbay.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author galop
 */
public class ProductoDTO {
    
    private Integer id;
    private String titulo;
    private String descripcion;
    private double precioSalida;
    private Date finPuja;
    private String foto;
    private short enPuja;
    private int categoria;
    private UsuarioDTO comprador;
    private UsuarioDTO vendedor;
    private List<PujaDTO> pujaList;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the precioSalida
     */
    public Double getPrecioSalida() {
        return precioSalida;
    }

    /**
     * @param precioSalida the precioSalida to set
     */
    public void setPrecioSalida(Double precioSalida) {
        this.precioSalida = precioSalida;
    }

    /**
     * @return the finPuja
     */
    public Date getFinPuja() {
        return finPuja;
    }

    /**
     * @param finPuja the finPuja to set
     */
    public void setFinPuja(Date finPuja) {
        this.finPuja = finPuja;
    }

    /**
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @return the enPuja
     */
    public short getEnPuja() {
        return enPuja;
    }

    /**
     * @param enPuja the enPuja to set
     */
    public void setEnPuja(short enPuja) {
        this.enPuja = enPuja;
    }

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
     * @return the vendedor
     */
    public UsuarioDTO getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(UsuarioDTO vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * @return the pujaList
     */
    public List<PujaDTO> getPujaList() {
        return pujaList;
    }

    /**
     * @param pujaList the pujaList to set
     */
    public void setPujaList(List<PujaDTO> pujaList) {
        this.pujaList = pujaList;
    }

    /**
     * @return the categoria
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

}
