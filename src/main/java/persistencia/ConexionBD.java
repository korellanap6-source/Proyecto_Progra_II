
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/sistema_ventas_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "38006";
    
    public static Connection getConexion(){
        Connection conexion = null;
        try{
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e){
            System.err.println("Error al conectar a postgres: "+ e.getMessage());
        }
        return conexion;
    }
}
