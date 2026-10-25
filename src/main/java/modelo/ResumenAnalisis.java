
package modelo;

import java.math.BigDecimal;

public class ResumenAnalisis {
    private int totalRegistros;
    private BigDecimal montoTotal;
    
    public ResumenAnalisis(){
        this.totalRegistros = 0;
        this.montoTotal = BigDecimal.ZERO;
    }
    
    public void RegistrarVentaExitosa(Venta venta){
        this.totalRegistros++;
        
        this.montoTotal = this.montoTotal.add(venta.getPrecioUnitario());
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    } 
}