package DataAccesObject;

import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionMySQL {

    private static final String URL = "jdbc:mysql://localhost:3306/RecursosHumanos";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private Connection conexion;
    private static final Logger LOGGER = Logger.getLogger(ConexionMySQL.class.getName());

    public static void main(String[] args) {

        ConexionMySQL cn = new ConexionMySQL();
    }

    public ConexionMySQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            DriverManager.setLoginTimeout(300); 
            
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            
            if (conexion != null) {
                DatabaseMetaData dm = conexion.getMetaData();
                System.out.println("Product Name: " + dm.getDatabaseProductName());
                System.out.println("Product Version: " + dm.getDatabaseProductVersion());
            }
        } catch (ClassNotFoundException | SQLException e) {            
            LOGGER.log(Level.SEVERE, "Error al establecer la conexión con la base de datos", e);
        }
    }
    
    public Connection getConexion() {
        return conexion;
    }
    
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error al cerrar la conexión", e);
        }
    }
}
