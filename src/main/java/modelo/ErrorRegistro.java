//archivo para registrar los datos con errores
package modelo;

public class ErrorRegistro {
    private String ArchivoOrigen;
    private String IdVenta;
    private String motivoRechazo;
    
    public ErrorRegistro(String ArchivoOrigen, String IdVenta, String motivoRechazo){
        this.ArchivoOrigen = ArchivoOrigen;
        this.IdVenta = IdVenta;
        this.motivoRechazo = motivoRechazo;
    }

    public String getArchivoOrigen() {
        return ArchivoOrigen;
    }

    public void setArchivoOrigen(String ArchivoOrigen) {
        this.ArchivoOrigen = ArchivoOrigen;
    }

    public String getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(String IdVenta) {
        this.IdVenta = IdVenta;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }
    
}
