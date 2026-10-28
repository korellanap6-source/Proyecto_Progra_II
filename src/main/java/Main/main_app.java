package Main;

import lectura.LectorArchivo;
import lectura.LectorCSV;
import lectura.LectorJSON;
import lectura.LectorXML;

public class main_app {
    public static void main(String[] args) {
        
        System.out.println("--- Resultados CSV ---");
        LectorArchivo lectorCsv = new LectorCSV();
        lectorCsv.procesandoArchivo();
        System.out.println("Registros exitosos: " + lectorCsv.getResumen().getTotalRegistros());
        System.out.println("Monto total: " + lectorCsv.getResumen().getMontoTotal());

        System.out.println("\n--- Resultados JSON ---");
        LectorArchivo lectorJson = new LectorJSON();
        lectorJson.procesandoArchivo();
        System.out.println("Registros exitosos: " + lectorJson.getResumen().getTotalRegistros());
        System.out.println("Monto total: " + lectorJson.getResumen().getMontoTotal());
        
        System.out.println("\n--- Resultados XML ---");
        LectorArchivo lectorXml = new LectorXML();
        lectorXml.procesandoArchivo();
        System.out.println("Registros exitosos: " + lectorXml.getResumen().getTotalRegistros());
        System.out.println("Monto total: " + lectorXml.getResumen().getMontoTotal());
    }
}