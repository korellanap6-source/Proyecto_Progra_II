
package lectura;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import modelo.Venta;
import modelo.ResumenAnalisis;
import modelo.ErrorRegistro;
import java.util.ArrayList;
import validacion.ProcesadorVentas;

public class LectorCSV implements LectorArchivo{
    private ResumenAnalisis resumen = new ResumenAnalisis();
    private ArrayList<ErrorRegistro> listaErrores = new ArrayList<>();
    private ProcesadorVentas procesador = new ProcesadorVentas();
    
        
    public void procesandoArchivo(){
        try{
            BufferedReader lector = new BufferedReader(new FileReader("C:\\Users\\kevin\\Documents\\NetBeansProjects\\Proyecto_Progra_II\\src\\main\\java\\Archivos\\Ventas_Mostrador.CSV"));
            lector.readLine();
            String linea;
            

            while ((linea = lector.readLine()) != null){
                String [] datos = linea.split(",");
                
                try{
                    Venta nuevaVenta = new Venta(
                        datos[0], //id_venta
                        datos[1], //fecha
                        datos[2], //sucursal
                        datos[3], //codigo_producto
                        datos[4], //producto
                        Integer.parseInt(datos[5]), //cantidad
                        new BigDecimal(datos[6]), //precio_u
                        datos[7] //vendedor
                    );
                    procesador.procesarRegistro(nuevaVenta);
                    resumen.registrarVentaExitosa(nuevaVenta);
                } catch (Exception e){
                    //si ocurre un errror al leer eo archivo se ejecutar esto
                    ErrorRegistro error = new ErrorRegistro(
                        "ventas_mostrador.csv", datos[0], "Dato invalido"+ e.getMessage());

                    listaErrores.add(error);
                }
            }
            
            lector.close();
        }catch (Exception e){
            System.out.println("Error general leyendo el archivo: " + e.getMessage());
        }
    }
    public ResumenAnalisis getResumen() {
        return resumen;
    }

    public ArrayList<ErrorRegistro> getListaErrores() {
        return listaErrores;
    }
}
