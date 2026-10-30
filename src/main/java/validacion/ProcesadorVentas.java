
package validacion;

import java.util.HashSet;
import java.util.Set;
import modelo.Venta;
import java.math.BigDecimal;

public class ProcesadorVentas {
    private Set<String> idsProcesados = new HashSet<>();
    
    public void procesarRegistro(Venta venta) throws Exception {
        venta.setSucursal(venta.getSucursal().trim().toUpperCase());
        venta.setCodigoProducto(venta.getCodigoProducto().trim().toUpperCase());
        venta.setProducto(venta.getProducto().trim().toUpperCase());
        
        if (venta.getFecha() != null) {
            venta.setFecha(venta.getFecha().trim());
        }
        
        try {
            java.sql.Date.valueOf(venta.getFecha());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new Exception("Formato de fecha inválido o inexistente: '" + venta.getFecha() + "'");
        }
        
        if (venta.getCantidad() < 0){
            throw new Exception("La cantidad no puede ser negativa");
        }
        if (venta.getPrecioUnitario().compareTo(BigDecimal.ZERO) < 0){
            throw new Exception("El precio no puede ser negativo");
        }
        
        if (idsProcesados.contains(venta.getIdVenta())){
            throw new Exception("ID de venta duplicado");
        } 
        
        idsProcesados.add(venta.getIdVenta());
    }
}
