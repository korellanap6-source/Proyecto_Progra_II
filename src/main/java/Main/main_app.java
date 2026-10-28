package Main;

import java.io.File;
import java.util.Scanner;
import lectura.LectorCSV;
import lectura.LectorJSON;
import lectura.LectorXML;

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
            System.out.println(" [1] Procesar directorio de archivos");
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
                System.out.print(" Ruta (ej. C:\\Users\\kevin\\Desktop\\Ventas): ");
                String rutaCarpeta = scanner.nextLine();
                
                File carpeta = new File(rutaCarpeta);
                
                // Verificamos que la ruta exista y sea una carpeta
                if (carpeta.exists() && carpeta.isDirectory()) {
                    File[] archivos = carpeta.listFiles();
                    
                    if (archivos != null && archivos.length > 0) {
                        System.out.println("\n--- Procesando archivos en la carpeta ---");
                        
                        
                        for (File archivo : archivos) {
                            if (archivo.isFile()) {
                                String nombre = archivo.getName().toLowerCase();
                                String rutaCompleta = archivo.getAbsolutePath();
                                
                               
                                if (nombre.endsWith(".csv")) {
                                    LectorCSV lectorCsv = new LectorCSV();
                                    lectorCsv.procesandoArchivo(rutaCompleta);
                                    System.out.println("Archivo CSV procesado (" + archivo.getName() + ") -> Exitosos: " + lectorCsv.getResumen().getTotalRegistros() + " | Monto: " + lectorCsv.getResumen().getMontoTotal());
                                } 
                                else if (nombre.endsWith(".json")) {
                                    LectorJSON lectorJson = new LectorJSON();
                                    lectorJson.procesandoArchivo(rutaCompleta);
                                    System.out.println("Archivo JSON procesado (" + archivo.getName() + ") -> Exitosos: " + lectorJson.getResumen().getTotalRegistros() + " | Monto: " + lectorJson.getResumen().getMontoTotal());
                                } 
                                else if (nombre.endsWith(".xml")) {
                                    LectorXML lectorXml = new LectorXML();
                                    lectorXml.procesandoArchivo(rutaCompleta);
                                    System.out.println("Archivo XML procesado (" + archivo.getName() + ") -> Exitosos: " + lectorXml.getResumen().getTotalRegistros() + " | Monto: " + lectorXml.getResumen().getMontoTotal());
                                }
                            }
                        }
                    } else {
                        System.out.println("\n La carpeta está vacía.");
                    }
                } else {
                    System.out.println("\n La ruta ingresada no existe o no es una carpeta válida.");
                }
                
                System.out.println("\n Procesamiento finalizado. Presione Enter para continuar...");
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