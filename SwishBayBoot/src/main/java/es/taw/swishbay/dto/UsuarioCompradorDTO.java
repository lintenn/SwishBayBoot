package es.taw.swishbay.dto;

public class UsuarioCompradorDTO {

    private Double saldoDesde;
    private Double saldoHasta;
    private String seleccionado;
    private String busqueda;

    public UsuarioCompradorDTO(String seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Double getSaldoDesde() {
        return saldoDesde;
    }

    public void setSaldoDesde(Double saldoDesde) {
        this.saldoDesde = saldoDesde;
    }

    public Double getSaldoHasta() {
        return saldoHasta;
    }

    public void setSaldoHasta(Double saldoHasta) {
        this.saldoHasta = saldoHasta;
    }

    public String getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(String seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
}
