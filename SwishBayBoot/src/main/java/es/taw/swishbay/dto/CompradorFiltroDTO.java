package es.taw.swishbay.dto;

/**
 *
 * @author Miguel Oña Guerrero
 */

public class CompradorFiltroDTO {

    private String filtroTitulo;
    private String filtroCategoria;
    private Double filtroPrecio;
    private Double mayorPrecio;
    private String goTo;

    public CompradorFiltroDTO(Double mayorPrecio, String goTo){
        this.filtroTitulo = "";
        this.filtroCategoria = "Categoria";
        this.filtroPrecio = mayorPrecio;
        this.mayorPrecio = mayorPrecio;
        this.goTo = goTo;
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

    public Double getFiltroPrecio() {
        return filtroPrecio;
    }

    public void setFiltroPrecio(Double filtroPrecio) {
        this.filtroPrecio = filtroPrecio;
    }

    public Double getMayorPrecio() {
        return mayorPrecio;
    }

    public void setMayorPrecio(Double mayorPrecio) {
        this.mayorPrecio = mayorPrecio;
    }

    public String getGoTo() {
        return goTo;
    }

    public void setGoTo(String goTo) {
        this.goTo = goTo;
    }
}
