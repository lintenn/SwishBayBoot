/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.swishbay.dto;

import java.util.List;

/**
 *
 * @author angel
 */
public class GrupoDTO {
    
    private Integer id;
    private String nombre;
    private UsuarioDTO marketing;
    private List<UsuarioDTO> usuarioList;
    
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the marketing
     */
    public UsuarioDTO getMarketing() {
        return marketing;
    }

    /**
     * @param marketing the marketing to set
     */
    public void setMarketing(UsuarioDTO marketing) {
        this.marketing = marketing;
    }

    /**
     * @return the usuarioList
     */
    public List<UsuarioDTO> getUsuarioList() {
        return usuarioList;
    }

    /**
     * @param usuarioList the usuarioList to set
     */
    public void setUsuarioList(List<UsuarioDTO> usuarioList) {
        this.usuarioList = usuarioList;
    }
}
