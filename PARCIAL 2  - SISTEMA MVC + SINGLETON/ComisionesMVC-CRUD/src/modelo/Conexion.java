package modelo;

import java.sql.*;

public class Conexion {
    private final String base = "usuarios";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String user = "root";
    private final String password = "elmaspro123";
    private final String host = "localhost";
    private final String port = "3306";
    private final String url = "jdbc:mysql://"+host+":"+port+"/"+base;
    private Connection con = null;
    
    public Connection getConexion(){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        return con;
    }
    
    public Connection getConexion(String dbNombre){
        String dbUrl = "jdbc:mysql://"+host+":"+port+"/"+dbNombre;
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(dbUrl, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        return con;
    }
    
}
