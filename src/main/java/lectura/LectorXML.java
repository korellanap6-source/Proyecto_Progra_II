
package lectura;

import java.util.ArrayList;
import modelo.ErrorRegistro;
import modelo.ResumenAnalisis;
import modelo.Venta;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.math.BigDecimal;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import validacion.ProcesadorVentas;


public class LectorXML implements LectorArchivo{
    private ResumenAnalisis resumen = new ResumenAnalisis();
    private ArrayList<ErrorRegistro> listaErrores = new ArrayList<>();
    private ProcesadorVentas procesador = new ProcesadorVentas();
    
    public void procesandoArchivo(String rutaArchivo){
        try{
            File lector = new File(rutaArchivo);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document documento = builder.parse(lector);
            NodeList listaVentas = documento.getElementsByTagName("venta");
            
            for(int i = 0; i<listaVentas.getLength(); i++){
                Node nodoVenta = listaVentas.item(i);
                if(nodoVenta.getNodeType() == Node.ELEMENT_NODE){
                    Element elementoVenta = (Element) nodoVenta;
                    //elemtos del arcivo
                    String idVenta = elementoVenta.getElementsByTagName("id_venta").item(0).getTextContent();
                    try{
                        String fecha = elementoVenta.getElementsByTagName("fecha").item(0).getTextContent();
                        String sucursal = elementoVenta.getElementsByTagName("sucursal").item(0).getTextContent();
                        String codigoProducto = elementoVenta.getElementsByTagName("codigo_producto").item(0).getTextContent();
                        String producto = elementoVenta.getElementsByTagName("producto").item(0).getTextContent();
                        int cantidad = Integer.parseInt(elementoVenta.getElementsByTagName("cantidad").item(0).getTextContent());
                        BigDecimal precioUnitario = new BigDecimal(elementoVenta.getElementsByTagName("precio_unitario").item(0).getTextContent());
                        String vendedor = elementoVenta.getElementsByTagName("vendedor").item(0).getTextContent();

                        Venta nuevaVenta = new Venta(idVenta, fecha, sucursal ,codigoProducto,producto, cantidad, precioUnitario, vendedor);
                        procesador.procesarRegistro(nuevaVenta);
                        resumen.registrarVentaExitosa(nuevaVenta);
                    }catch (Exception e) {
                        ErrorRegistro error = new ErrorRegistro("Ventas_Mayoristas.XML", idVenta, "Dato invalido: " + e.getMessage());
                        listaErrores.add(error);
                    }
                }
            }
        }catch (Exception e){
            System.out.println("Error" + e.getMessage());
        }
    }

    public ResumenAnalisis getResumen() {
        return resumen;
    }

    public ArrayList<ErrorRegistro> getListaErrores() {
        return listaErrores;
    }
}