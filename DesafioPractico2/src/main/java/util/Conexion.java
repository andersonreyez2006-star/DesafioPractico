/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import java.sql.*;
/**
 *
 * @author ander
 */
public class Conexion {
     // Valores de conexion a MySql
    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // El puerto es opcional, si no se especifica se usa el 3306 por defecto
    private static String JDBC_URL = "jdbc:mysql://localhost:3306/biblioteca_db";
    private static String JDBC_USER = "root";
    private static String JDBC_PASS = "";
    private static Driver driver = null;
    private static Connection con = null;
    
    public static synchronized Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
 
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
 
    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
 
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    
}
