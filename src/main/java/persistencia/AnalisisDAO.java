package persistencia;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnalisisDAO {

    public Map<String, BigDecimal> getVentasPorSucursal() {
        Map<String, BigDecimal> reporte = new LinkedHashMap<>();
        String sql = "SELECT sucursal, SUM(cantidad * precio_unitario) as total_ingresos "
                   + "FROM ventas_validas "
                   + "GROUP BY sucursal "
                   + "ORDER BY total_ingresos DESC;";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                reporte.put(rs.getString("sucursal"), rs.getBigDecimal("total_ingresos"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ventas por sucursal: " + e.getMessage());
        }
        return reporte;
    }

    public Map<String, BigDecimal> getVentasPorVendedor() {
        Map<String, BigDecimal> reporte = new LinkedHashMap<>();
        String sql = "SELECT vendedor, SUM(cantidad * precio_unitario) as total_ingresos "
                   + "FROM ventas_validas "
                   + "GROUP BY vendedor "
                   + "ORDER BY total_ingresos DESC;";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                reporte.put(rs.getString("vendedor"), rs.getBigDecimal("total_ingresos"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ventas por vendedor: " + e.getMessage());
        }
        return reporte;
    }

    public Map<String, Integer> getProductosMasVendidos() {
        Map<String, Integer> reporte = new LinkedHashMap<>();
        String sql = "SELECT producto, SUM(cantidad) as total_unidades "
                   + "FROM ventas_validas "
                   + "GROUP BY producto "
                   + "ORDER BY total_unidades DESC;";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                reporte.put(rs.getString("producto"), rs.getInt("total_unidades"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos más vendidos: " + e.getMessage());
        }
        return reporte;
    }
}