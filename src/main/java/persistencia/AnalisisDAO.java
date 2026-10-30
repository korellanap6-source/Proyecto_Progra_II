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
    
    public Map<String, BigDecimal> getTop3Vendedores() {
        Map<String, BigDecimal> reporte = new LinkedHashMap<>();
        String sql = "SELECT vendedor, SUM(cantidad * precio_unitario) as total_ingresos "
                   + "FROM ventas_validas GROUP BY vendedor ORDER BY total_ingresos DESC LIMIT 3;";
        try (Connection conn = ConexionBD.getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) { reporte.put(rs.getString("vendedor"), rs.getBigDecimal("total_ingresos")); }
        } catch (SQLException e) { System.err.println("Error en Top 3 Vendedores: " + e.getMessage()); }
        return reporte;
    }

    public Map<String, BigDecimal> getVentasPorMes() {
        Map<String, BigDecimal> reporte = new LinkedHashMap<>();
        String sql = "SELECT TO_CHAR(fecha, 'YYYY-MM') as mes, SUM(cantidad * precio_unitario) as total "
                   + "FROM ventas_validas GROUP BY TO_CHAR(fecha, 'YYYY-MM') ORDER BY mes;";
        try (Connection conn = ConexionBD.getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) { reporte.put(rs.getString("mes"), rs.getBigDecimal("total")); }
        } catch (SQLException e) { System.err.println("Error en ventas por mes: " + e.getMessage()); }
        return reporte;
    }

    public Map<String, BigDecimal> getEstadisticasTransacciones() {
        Map<String, BigDecimal> stats = new LinkedHashMap<>();
        String sql = "SELECT AVG(cantidad * precio_unitario) as promedio, "
                   + "MAX(cantidad * precio_unitario) as maximo, "
                   + "MIN(cantidad * precio_unitario) as minimo FROM ventas_validas;";
        try (Connection conn = ConexionBD.getConexion(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                stats.put("Promedio", rs.getBigDecimal("promedio"));
                stats.put("Mayor Transaccion", rs.getBigDecimal("maximo"));
                stats.put("Menor Transaccion", rs.getBigDecimal("minimo"));
            }
        } catch (SQLException e) { System.err.println("Error en estadísticas: " + e.getMessage()); }
        return stats;
    }

    public Map<String, String> getProductosExtremos() {
        Map<String, String> extremos = new LinkedHashMap<>();
        String sqlMax = "SELECT producto, SUM(cantidad * precio_unitario) as total FROM ventas_validas GROUP BY producto ORDER BY total DESC LIMIT 1;";
        String sqlMin = "SELECT producto, SUM(cantidad * precio_unitario) as total FROM ventas_validas GROUP BY producto ORDER BY total ASC LIMIT 1;";
        
        try (Connection conn = ConexionBD.getConexion()) {
            try (PreparedStatement pstMax = conn.prepareStatement(sqlMax); ResultSet rsMax = pstMax.executeQuery()) {
                if (rsMax.next()) extremos.put("Mayor Monto", rsMax.getString("producto") + " (Q " + rsMax.getBigDecimal("total") + ")");
            }
            try (PreparedStatement pstMin = conn.prepareStatement(sqlMin); ResultSet rsMin = pstMin.executeQuery()) {
                if (rsMin.next()) extremos.put("Menor Monto", rsMin.getString("producto") + " (Q " + rsMin.getBigDecimal("total") + ")");
            }
        } catch (SQLException e) { System.err.println("Error en productos extremos: " + e.getMessage()); }
        return extremos;
    }
}