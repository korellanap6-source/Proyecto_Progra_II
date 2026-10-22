/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.math.BigDecimal;
import java.util.Map;

public class ResumenAnalisis {

    private int totalRecibidos;
    private int totalAceptados;
    private int totalRechazados;
    private int totalDuplicados;

    private BigDecimal montoTotalVendido;
    private BigDecimal promedioPorTransaccion;

    private Map<String, BigDecimal> ventasPorSucursal;
    private Map<String, BigDecimal> ventasPorMes;
    private Map<String, BigDecimal> ventasPorProducto;
    
    private Map<String, BigDecimal> ventasPorVendedor; 
    private String productoMayorMonto;
    private String productoMenorMonto;
    private String transaccionMayorMonto;
    private String transaccionMenorMonto;

    public ResumenAnalisis() {
    }

    public int getTotalRecibidos() {
        return totalRecibidos;
    }

    public void setTotalRecibidos(int totalRecibidos) {
        this.totalRecibidos = totalRecibidos;
    }

    public int getTotalAceptados() {
        return totalAceptados;
    }

    public void setTotalAceptados(int totalAceptados) {
        this.totalAceptados = totalAceptados;
    }

    public int getTotalRechazados() {
        return totalRechazados;
    }

    public void setTotalRechazados(int totalRechazados) {
        this.totalRechazados = totalRechazados;
    }

    public int getTotalDuplicados() {
        return totalDuplicados;
    }

    public void setTotalDuplicados(int totalDuplicados) {
        this.totalDuplicados = totalDuplicados;
    }

    public BigDecimal getMontoTotalVendido() {
        return montoTotalVendido;
    }

    public void setMontoTotalVendido(BigDecimal montoTotalVendido) {
        this.montoTotalVendido = montoTotalVendido;
    }

    public BigDecimal getPromedioPorTransaccion() {
        return promedioPorTransaccion;
    }

    public void setPromedioPorTransaccion(BigDecimal promedioPorTransaccion) {
        this.promedioPorTransaccion = promedioPorTransaccion;
    }

    public Map<String, BigDecimal> getVentasPorSucursal() {
        return ventasPorSucursal;
    }

    public void setVentasPorSucursal(Map<String, BigDecimal> ventasPorSucursal) {
        this.ventasPorSucursal = ventasPorSucursal;
    }

    public Map<String, BigDecimal> getVentasPorMes() {
        return ventasPorMes;
    }

    public void setVentasPorMes(Map<String, BigDecimal> ventasPorMes) {
        this.ventasPorMes = ventasPorMes;
    }

    public Map<String, BigDecimal> getVentasPorProducto() {
        return ventasPorProducto;
    }

    public void setVentasPorProducto(Map<String, BigDecimal> ventasPorProducto) {
        this.ventasPorProducto = ventasPorProducto;
    }

    public Map<String, BigDecimal> getVentasPorVendedor() {
        return ventasPorVendedor;
    }

    public void setVentasPorVendedor(Map<String, BigDecimal> ventasPorVendedor) {
        this.ventasPorVendedor = ventasPorVendedor;
    }

    public String getProductoMayorMonto() {
        return productoMayorMonto;
    }

    public void setProductoMayorMonto(String productoMayorMonto) {
        this.productoMayorMonto = productoMayorMonto;
    }

    public String getProductoMenorMonto() {
        return productoMenorMonto;
    }

    public void setProductoMenorMonto(String productoMenorMonto) {
        this.productoMenorMonto = productoMenorMonto;
    }

    public String getTransaccionMayorMonto() {
        return transaccionMayorMonto;
    }

    public void setTransaccionMayorMonto(String transaccionMayorMonto) {
        this.transaccionMayorMonto = transaccionMayorMonto;
    }

    public String getTransaccionMenorMonto() {
        return transaccionMenorMonto;
    }

    public void setTransaccionMenorMonto(String transaccionMenorMonto) {
        this.transaccionMenorMonto = transaccionMenorMonto;
    }
}
