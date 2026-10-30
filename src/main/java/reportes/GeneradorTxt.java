package reportes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import modelo.ErrorRegistro;

public class GeneradorTxt {

    public void generarReporteAnalisis(Map<String, BigDecimal> ventasPorSucursal,
                                       Map<String, BigDecimal> ventasPorVendedor,
                                       Map<String, Integer> topProductos,
                                       String rutaArchivo) {
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write("========================================\n");
            writer.write("      REPORTE DE ANÁLISIS DE VENTAS     \n");
            writer.write("========================================\n\n");

            writer.write("--- VENTAS POR SUCURSAL ---\n");
            for (Map.Entry<String, BigDecimal> entry : ventasPorSucursal.entrySet()) {
                writer.write(String.format("- %-20s : Q %,.2f\n", entry.getKey(), entry.getValue()));
            }
            writer.write("\n");

            writer.write("--- RENDIMIENTO DE VENDEDORES ---\n");
            for (Map.Entry<String, BigDecimal> entry : ventasPorVendedor.entrySet()) {
                writer.write(String.format("- %-20s : Q %,.2f\n", entry.getKey(), entry.getValue()));
            }
            writer.write("\n");

            writer.write("--- TOP PRODUCTOS MÁS VENDIDOS ---\n");
            for (Map.Entry<String, Integer> entry : topProductos.entrySet()) {
                writer.write(String.format("- %-20s : %d unidades\n", entry.getKey(), entry.getValue()));
            }
            writer.write("\n");
            
            writer.write("========================================\n");
            writer.write("          FIN DEL REPORTE               \n");
            writer.write("========================================\n");
            
            System.out.println("Reporte de análisis generado exitosamente en: " + rutaArchivo);

        } catch (IOException e) {
            System.err.println("Error al generar el reporte de análisis: " + e.getMessage());
        }
    }

  
    public void generarReporteErrores(List<ErrorRegistro> errores, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write("========================================\n");
            writer.write("   BITÁCORA DE ERRORES DE PROCESAMIENTO \n");
            writer.write("========================================\n\n");

            if (errores.isEmpty()) {
                writer.write("No se encontraron errores durante el procesamiento.\n");
            } else {
                for (ErrorRegistro error : errores) {
                    writer.write("Archivo Origen: " + error.getArchivoOrigen() + "\n");
                    writer.write("ID de Venta   : " + error.getIdVenta() + "\n");
                    writer.write("Motivo Rechazo: " + error.getMotivoRechazo() + "\n");
                    writer.write("----------------------------------------\n");
                }
            }
            
            System.out.println("Bitácora de errores generada exitosamente en: " + rutaArchivo);

        } catch (IOException e) {
            System.err.println("Error al generar la bitácora de errores: " + e.getMessage());
        }
    }
}