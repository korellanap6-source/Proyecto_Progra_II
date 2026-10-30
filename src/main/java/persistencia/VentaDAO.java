package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import modelo.Venta;
import modelo.ErrorRegistro;

public class VentaDAO {

    public void guardarVentasValidas(List<Venta> ventas) {
        String sql = "INSERT INTO ventas_validas (id_venta, fecha, sucursal, codigo_producto, producto, cantidad, precio_unitario, vendedor) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) "
                   + "ON CONFLICT (id_venta) DO NOTHING;";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (Venta venta : ventas) {
                pstmt.setString(1, venta.getIdVenta());
                // Convertimos el String "YYYY-MM-DD" al formato Date que exige SQL
                pstmt.setDate(2, java.sql.Date.valueOf(venta.getFecha()));
                pstmt.setString(3, venta.getSucursal());
                pstmt.setString(4, venta.getCodigoProducto());
                pstmt.setString(5, venta.getProducto());
                pstmt.setInt(6, venta.getCantidad());
                pstmt.setBigDecimal(7, venta.getPrecioUnitario());
                pstmt.setString(8, venta.getVendedor());

                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("Ventas válidas sincronizadas con la base de datos.");

        } catch (SQLException e) {
            System.err.println("Error al guardar ventas válidas: " + e.getMessage());
        }
    }

    public void guardarErrores(List<ErrorRegistro> errores) {
        String sql = "INSERT INTO ventas_rechazadas (id_venta_original, archivo_origen, motivo_rechazo) "
                   + "VALUES (?, ?, ?);";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (ErrorRegistro error : errores) {
                pstmt.setString(1, error.getIdVenta());
                pstmt.setString(2, error.getArchivoOrigen());
                pstmt.setString(3, error.getMotivoRechazo());

                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("Errores de procesamiento guardados en la base de datos.");

        } catch (SQLException e) {
            System.err.println("Error al guardar registros rechazados: " + e.getMessage());
        }
    }
}