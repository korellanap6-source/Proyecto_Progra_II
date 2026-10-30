package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResumenAnalisis {
    private int totalRegistros;
    private BigDecimal montoTotal;
    private List<Venta> ventasValidas;
    
    public ResumenAnalisis(){
        this.totalRegistros = 0;
        this.montoTotal = BigDecimal.ZERO;
        this.ventasValidas = new ArrayList<>();
    }
    
    public void registrarVentaExitosa(Venta venta){
        this.totalRegistros++;
        BigDecimal cantidadDecimal = new BigDecimal(venta.getCantidad());
        BigDecimal subtotalVenta = venta.getPrecioUnitario().multiply(cantidadDecimal);
        
        this.montoTotal = this.montoTotal.add(subtotalVenta);
        this.ventasValidas.add(venta);
    }

    public int getTotalRegistros() {
        return totalRegistros;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    } 
    
    public List<Venta> getVentasValidas() {
        return ventasValidas;
    }
}