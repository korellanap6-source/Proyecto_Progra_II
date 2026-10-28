package lectura;

import java.util.ArrayList;
import modelo.ErrorRegistro;
import modelo.ResumenAnalisis;
import com.google.gson.Gson;
import java.io.FileReader;
import modelo.Venta;
import validacion.ProcesadorVentas;


public class LectorJSON implements LectorArchivo{
    private ResumenAnalisis resumen = new ResumenAnalisis();
    private ArrayList<ErrorRegistro> listaErrores = new ArrayList<>();
    private ProcesadorVentas procesador = new ProcesadorVentas();
        
    public void procesandoArchivo(String rutaArchivo){
        try{
            Gson gson = new Gson();
            FileReader lector = new FileReader(rutaArchivo);
            Venta [] ventas = gson.fromJson(lector, Venta[].class);
            for (Venta venta : ventas){
                try{
                    procesador.procesarRegistro(venta);
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