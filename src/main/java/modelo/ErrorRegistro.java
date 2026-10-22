
package modelo;

public class ErrorRegistro {

    private String archivoOrigen;
    private String idOriginal;
    private String motivoRechazo;

    public ErrorRegistro() {
    }
    
    public ErrorRegistro(String archivoOrigen, String idOriginal, String motivoRechazo) {
        this.archivoOrigen = archivoOrigen;
        this.idOriginal = idOriginal;
        this.motivoRechazo = motivoRechazo;
    }

    public String getArchivoOrigen() {
        return archivoOrigen;
    }

    public void setArchivoOrigen(String archivoOrigen) {
        this.archivoOrigen = archivoOrigen;
    }

    public String getIdOriginal() {
        return idOriginal;
    }

    public void setIdOriginal(String idOriginal) {
        this.idOriginal = idOriginal;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    @Override
    public String toString() {
        return "ErrorRegistro{" +
                "Archivo='" + archivoOrigen + '\'' +
                ", ID='" + idOriginal + '\'' +
                ", Motivo='" + motivoRechazo + '\'' +
                '}';
    }
}
