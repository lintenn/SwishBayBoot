package es.taw.swishbay.dto;

/**
 *
 * @author angel
 */

public class MensajeFiltroDTO {

    private String filtro;
    private String busqueda;

    public MensajeFiltroDTO(String filtro) {
        this.filtro = filtro;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
}
