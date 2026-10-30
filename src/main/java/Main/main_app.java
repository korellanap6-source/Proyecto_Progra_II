package Main;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lectura.LectorCSV;
import lectura.LectorJSON;
import lectura.LectorXML;
import modelo.ErrorRegistro;
import modelo.Venta;
import persistencia.AnalisisDAO;
import persistencia.VentaDAO;
import reportes.GeneradorTxt;

public class main_app {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 2) {
            System.out.println("\n========================================");
            System.out.println("  ╔══════════════════════════════════╗");
            System.out.println("  ║  SISTEMA DE ANÁLISIS DE VENTAS   ║");
            System.out.println("  ╚══════════════════════════════════╝");
            System.out.println("========================================");
            System.out.println(" [1] Procesar archivos, Subir a BD y Reportar");
            System.out.println(" [2] Salir del programa");
            System.out.println("========================================");
            System.out.print(" Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            if (opcion == 1) {
                System.out.println("\n Ingrese la ruta de la carpeta donde están los archivos.");
                System.out.print(" Ruta (ej. C:\\Users\\kevin\\Desktop\\Archivos): ");
                String rutaCarpeta = scanner.nextLine();
                
                File carpeta = new File(rutaCarpeta);
                
                if (carpeta.exists() && carpeta.isDirectory()) {
                    File[] archivos = carpeta.listFiles();
                    
                    if (archivos != null && archivos.length > 0) {
                        System.out.println("\n--- FASE 1: Extracción y Limpieza ---");
                        
                        List<Venta> todasVentasValidas = new ArrayList<>();
                        List<ErrorRegistro> todosErrores = new ArrayList<>();
                        
                        for (File archivo : archivos) {
                            if (archivo.isFile()) {
                                String nombre = archivo.getName().toLowerCase();
                                String rutaCompleta = archivo.getAbsolutePath();
                                
                                if (nombre.endsWith(".csv")) {
                                    LectorCSV lectorCsv = new LectorCSV();
                                    lectorCsv.procesandoArchivo(rutaCompleta);
                                    todasVentasValidas.addAll(lectorCsv.getResumen().getVentasValidas());
                                    todosErrores.addAll(lectorCsv.getListaErrores());
                                    System.out.println("Procesado: " + archivo.getName());
                                } 
                                else if (nombre.endsWith(".json")) {
                                    LectorJSON lectorJson = new LectorJSON();
                                    lectorJson.procesandoArchivo(rutaCompleta);
                                    todasVentasValidas.addAll(lectorJson.getResumen().getVentasValidas());
                                    todosErrores.addAll(lectorJson.getListaErrores());
                                    System.out.println("Procesado: " + archivo.getName());
                                } 
                                else if (nombre.endsWith(".xml")) {
                                    LectorXML lectorXml = new LectorXML();
                                    lectorXml.procesandoArchivo(rutaCompleta);
                                    todasVentasValidas.addAll(lectorXml.getResumen().getVentasValidas());
                                    todosErrores.addAll(lectorXml.getListaErrores());
                                    System.out.println("Procesado: " + archivo.getName());
                                }
                            }
                        }
                        
                        System.out.println("\n--- FASE 2: Sincronización con PostgreSQL ---");
                        if (!todasVentasValidas.isEmpty() || !todosErrores.isEmpty()) {
                            VentaDAO ventaDao = new VentaDAO();
                            ventaDao.guardarVentasValidas(todasVentasValidas);
                            ventaDao.guardarErrores(todosErrores);
                            
                            System.out.println("\n--- FASE 3: Generación de Reportes ---");
                            AnalisisDAO analisisDao = new AnalisisDAO();
                            
                            Map<String, BigDecimal> porSucursal = analisisDao.getVentasPorSucursal();
                            Map<String, BigDecimal> porVendedor = analisisDao.getVentasPorVendedor();
                            Map<String, Integer> topProductos = analisisDao.getProductosMasVendidos();
                            
                            GeneradorTxt generador = new GeneradorTxt();
                            String rutaReporte = rutaCarpeta + "\\reporte_analisis.txt";
                            String rutaErrores = rutaCarpeta + "\\bitacora_errores.txt";
                            
                            generador.generarReporteAnalisis(porSucursal, porVendedor, topProductos, rutaReporte);
                            generador.generarReporteErrores(todosErrores, rutaErrores);
                        } else {
                            System.out.println("No se encontraron datos válidos ni errores para guardar.");
                        }
                        
                    } else {
                        System.out.println("\n La carpeta está vacía.");
                    }
                } else {
                    System.out.println("\n La ruta ingresada no existe o no es una carpeta válida.");
                }
                
                System.out.println("\n Flujo completo finalizado. Presione Enter para continuar...");
                scanner.nextLine();
                
            } else if (opcion == 2) {
                System.out.println("\n Saliendo del sistema...");
            } else {
                System.out.println("\n Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }
}