package es.taw.swishbay.dto;

/**
 *
 * @author angel
 */

public class MensajeFiltroDTO {

    private String filtroTitulo;
    private String filtroMensaje;

    public MensajeFiltroDTO(String filtroTitulo) {
        this.filtroTitulo = filtroTitulo;
    }

    public String getFiltroTitulo() {
        return filtroTitulo;
    }

    public void setFiltroTitulo(String filtroTitulo) {
        this.filtroTitulo = filtroTitulo;
    }

    public String getFiltroMensaje() {
        return filtroMensaje;
    }

    public void setFiltroMensaje(String filtroMensaje) {
        this.filtroMensaje = filtroMensaje;
    }
}
