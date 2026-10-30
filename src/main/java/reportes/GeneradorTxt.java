package reportes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import modelo.ErrorRegistro;

public class GeneradorTxt {

    public void generarReporteAnalisis(
        Map<String, BigDecimal> ventasPorSucursal, Map<String, BigDecimal> ventasPorVendedor, 
        Map<String, BigDecimal> top3Vendedores, Map<String, Integer> topProductos, 
        Map<String, BigDecimal> porMes, Map<String, BigDecimal> statsTransacciones, 
        Map<String, String> prodsExtremos, int aceptados, int rechazados, 
        int totalRecibidos, long duplicados, double porcentajeRechazo, String rutaArchivo) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write("========================================\n");
            writer.write("      REPORTE DE ANÁLISIS DE VENTAS     \n");
            writer.write("========================================\n\n");

            writer.write("--- ESTADÍSTICAS DEL PROCESAMIENTO DE ARCHIVOS ---\n");
            writer.write(String.format("- Total Registros Recibidos: %d\n", totalRecibidos));
            writer.write(String.format("- Registros Aceptados: %d\n", aceptados));
            writer.write(String.format("- Registros Rechazados: %d\n", rechazados));
            writer.write(String.format("- Registros Duplicados: %d\n", duplicados));
            writer.write(String.format("- Porcentaje de Rechazo: %.2f%%\n\n", porcentajeRechazo));

            writer.write("--- INDICADORES GENERALES DE LA BASE DE DATOS ---\n");
            if (statsTransacciones.containsKey("Promedio")) {
                writer.write(String.format("- Promedio por Transacción: Q %.2f\n", statsTransacciones.get("Promedio")));
                writer.write(String.format("- Transacción de Mayor Monto: Q %.2f\n", statsTransacciones.get("Mayor Transaccion")));
                writer.write(String.format("- Transacción de Menor Monto: Q %.2f\n\n", statsTransacciones.get("Menor Transaccion")));
            }

            writer.write("--- VENTAS POR MES ---\n");
            for (Map.Entry<String, BigDecimal> entry : porMes.entrySet()) {
                writer.write(String.format("- Mes %-10s : Q %,.2f\n", entry.getKey(), entry.getValue()));
            }
            writer.write("\n");

            writer.write("--- VENTAS POR SUCURSAL ---\n");
            for (Map.Entry<String, BigDecimal> entry : ventasPorSucursal.entrySet()) {
                writer.write(String.format("- %-20s : Q %,.2f\n", entry.getKey(), entry.getValue()));
            }
            writer.write("\n");

            writer.write("--- TOP 3 VENDEDORES POR MONTO TOTAL ---\n");
            for (Map.Entry<String, BigDecimal> entry : top3Vendedores.entrySet()) {
                writer.write(String.format("- %-20s : Q %,.2f\n", entry.getKey(), entry.getValue()));
            }
            writer.write("\n");

            writer.write("--- VENTAS POR PRODUCTO Y EXTREMOS ---\n");
            if (prodsExtremos.containsKey("Mayor Monto")) {
                writer.write(String.format("- Producto con Mayor Monto Vendido: %s\n", prodsExtremos.get("Mayor Monto")));
                writer.write(String.format("- Producto con Menor Monto Vendido: %s\n\n", prodsExtremos.get("Menor Monto")));
            }

            writer.write("--- CONCLUSIONES DEL EQUIPO ---\n");
            writer.write("Conclusiones de Kevin Orellana:\n");
            writer.write("1. Al unir los archivos, notamos que los que vienen de internet traen mas errores y espacios vacios. Por eso fue clave limpiar bien los textos antes de guardarlos en la base de datos.\n");
            writer.write("2. Vimos que el producto mas vendido no siempre es el que deja mas ganancia. El kit limpiador se vende mucho, pero las botas industriales son las que realmente levantan el dinero total.\n");
            writer.write("3. Los datos repetidos son un problema muy comun al juntar varios archivos. Gracias a que filtramos esto en el codigo y pusimos reglas en PostgreSQL, evitamos que las ventas se sumaran doble.\n\n");

            writer.write("Conclusiones de Erick Garcia:\n");
            writer.write("1. Al revisar las ventas por empleado, vimos que casi todo el ingreso lo logran solo los 3 mejores. La empresa depende mucho de ellos y deberian capacitar al resto del equipo.\n");
            writer.write("2. Las sucursales funcionan muy diferente: la Central es la que tiene mas movimiento en el dia a dia, pero los canales mayoristas son los que hacen las compras mas grandes de una sola vez.\n");
            writer.write("3. Hacer este proceso en automatico ayuda mucho a la empresa. En lugar de buscar precios negativos o cantidades nulas a mano, el programa hace el trabajo sucio y deja los datos listos para usarse.\n\n");

            writer.write("========================================\n");
            writer.write("          FIN DEL REPORTE               \n");
            writer.write("========================================\n");

            System.out.println("Reporte de análisis generado exitosamente en: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al generar el reporte: " + e.getMessage());
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