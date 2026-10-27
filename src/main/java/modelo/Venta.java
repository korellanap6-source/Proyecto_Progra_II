//archivo modelo para los datos que recivira el programa
package modelo;

import java.math.BigDecimal;
import com.google.gson.annotations.SerializedName;

public class Venta {
    @SerializedName("id_venta")
    private String idVenta;
    private String fecha;
    private String sucursal;
    @SerializedName("codigo_producto")
    private String codigoProducto;
    private String producto;
    private int cantidad;
    @SerializedName("precio_unitario")
    private BigDecimal precioUnitario;
    private String vendedor;
    
    public Venta(String idVenta, String fecha, String sucursal, String codigoProducto,
                String producto, int cantidad, BigDecimal precioUnitario, String vendedor){
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.sucursal = sucursal;
        this.codigoProducto = codigoProducto;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.vendedor = vendedor;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", fecha=" + fecha + ", sucursal=" + sucursal + ", codigoProducto=" + codigoProducto + ", producto=" + producto + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", vendedor=" + vendedor + '}';
    }
    
}

