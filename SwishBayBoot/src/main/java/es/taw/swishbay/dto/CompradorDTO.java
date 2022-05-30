package es.taw.swishbay.dto;

import java.util.List;

/**
 *
 * @author Miguel Oña Guerrero
 */
public class CompradorDTO {

    private UsuarioDTO usuario;
    private List<ProductoDTO> productos;
    private List<CategoriaDTO> categorias;
    private List<PujaDTO> mayoresPujas;
    private String filtroTitulo;
    private String filtroCategoria;
    private Double mayorPrecio;
    private Double filtroPrecio;
    private String mapping;

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

    public List<PujaDTO> getMayoresPujas() {
        return mayoresPujas;
    }

    public void setMayoresPujas(List<PujaDTO> mayoresPujas) {
        this.mayoresPujas = mayoresPujas;
    }

    public String getFiltroTitulo() {
        return filtroTitulo;
    }

    public void setFiltroTitulo(String filtroTitulo) {
        this.filtroTitulo = filtroTitulo;
    }

    public String getFiltroCategoria() {
        return filtroCategoria;
    }

    public void setFiltroCategoria(String filtroCategoria) {
        this.filtroCategoria = filtroCategoria;
    }

    public Double getMayorPrecio() {
        return mayorPrecio;
    }

    public void setMayorPrecio(Double mayorPrecio) {
        this.mayorPrecio = mayorPrecio;
    }

    public Double getFiltroPrecio() {
        return filtroPrecio;
    }

    public void setFiltroPrecio(Double filtroPrecio) {
        this.filtroPrecio = filtroPrecio;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }




}