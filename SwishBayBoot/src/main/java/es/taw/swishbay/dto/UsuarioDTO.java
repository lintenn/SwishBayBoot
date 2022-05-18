/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.swishbay.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Luis
 */
public class UsuarioDTO {
    private Integer id;
    private String correo;
    private String password;
    private String nombre;
    private String apellidos;
    private String domicilio;
    private Date fechaNacimiento;
    private String sexo;
    private String ciudad;
    private double saldo;
    private RolUsuarioDTO rol;
    private List<Integer> categoriaList;
    private List<Integer> favoritos;
    private List<MensajeDTO> mensajeList;
    
    public UsuarioDTO() {
    }

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
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the domicilio
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * @param domicilio the domicilio to set
     */
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the rol
     */
    public RolUsuarioDTO getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(RolUsuarioDTO rol) {
        this.rol = rol;
    }
    
    public List<Integer> getCategoriaList() {
        return categoriaList;
    }
    
    public void setCategoriaList(List<Integer> categoriaList) {
        this.categoriaList = categoriaList;
    }
    
    public List<Integer> getFavoritos() {
        return favoritos;
    }
    
    public void setFavoritos(List<Integer> favoritos) {
        this.favoritos = favoritos;
    }

    public List<MensajeDTO> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<MensajeDTO> mensajeList) {
        this.mensajeList = mensajeList;
    }
}
