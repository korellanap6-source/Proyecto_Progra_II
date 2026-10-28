
package lectura;

import java.util.ArrayList;
import modelo.ErrorRegistro;
import modelo.ResumenAnalisis;

public interface LectorArchivo {
    void procesandoArchivo(String rutaArchivo);
    
    ResumenAnalisis getResumen();
    
    ArrayList<ErrorRegistro> getListaErrores();
    
}
