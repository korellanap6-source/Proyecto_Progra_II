package lectura;

import java.util.ArrayList;
import modelo.ErrorRegistro;
import modelo.ResumenAnalisis;
import com.google.gson.Gson;
import java.io.FileReader;
import modelo.Venta;


public class LectorJSON implements LectorArchivo{
    private ResumenAnalisis resumen = new ResumenAnalisis();
    private ArrayList<ErrorRegistro> listaErrores = new ArrayList<>();
        
    public void procesandoArchivo(){
        try{
            Gson gson = new Gson();
            FileReader lector = new FileReader(
                    "C:\\Users\\kevin\\Documents\\NetBeansProjects\\Proyecto_Progra_II\\src\\main\\java\\Archivos\\Ventas_Ecommerce.JSON");
            Venta [] ventas = gson.fromJson(lector, Venta[].class);
            for (Venta venta : ventas){
                try{
                    resumen.registrarVentaExitosa(venta);                   
                }catch(Exception e){
                    ErrorRegistro error = new ErrorRegistro("Ventas_Ecommerce.JSON", "Dato invalido", e.getMessage());
                    listaErrores.add(error);   
                }
            }
            lector.close();
        }catch (Exception e){
            System.out.println("Error"+e.getMessage());
        }
    }

    public ResumenAnalisis getResumen() {
        return resumen;
    }

    public ArrayList<ErrorRegistro> getListaErrores() {
        return listaErrores;
    }
    
}