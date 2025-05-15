package DataAccesObject;

import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Connection;

public class ConexionMySQL {

    //mysql://root:hkOhyTXrXlleZuWHDzPdPhNpARgmxlRt@caboose.proxy.rlwy.net:53754/railway
    private String StrConxMySQL = "jdbc:mysql://caboose.proxy.rlwy.net:53754/railway";
    private String StrUserMySQL = "root";
    private String StrPassMySQL = "hkOhyTXrXlleZuWHDzPdPhNpARgmxlRt";
    private Connection conexion;

    public static void main(String[] args) {
        ConexionMySQL cn = new ConexionMySQL();
    }

    public ConexionMySQL() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            DriverManager.setLoginTimeout(300);
            conexion = DriverManager.getConnection(StrConxMySQL, StrUserMySQL, StrPassMySQL);
            if (conexion != null) {
                DatabaseMetaData dm = conexion.getMetaData();
                System.out.println("Product Name:" + dm.getDatabaseProductName());
                System.out.println("Product Version:" + dm.getDatabaseProductVersion());
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }

}
