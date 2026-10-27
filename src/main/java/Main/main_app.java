package Main;

import lectura.LectorArchivo;
import lectura.LectorCSV;
import lectura.LectorJSON;
import lectura.LectorXML;

public class main_app {
    public static void main(String[] args) {
        
        LectorArchivo lectorCsv = new LectorCSV();
        lectorCsv.procesandoArchivo();
        System.out.println("Registros CSV exitosos: " + lectorCsv.getResumen().getTotalRegistros());

        LectorArchivo lectorJson = new LectorJSON();
        lectorJson.procesandoArchivo();
        System.out.println("Registros JSON exitosos: " + lectorJson.getResumen().getTotalRegistros());
        
        LectorArchivo lectorXml = new LectorXML();
        lectorXml.procesandoArchivo();
        System.out.println("Registros XML exitosos: " + lectorXml.getResumen().getTotalRegistros());
    }
}