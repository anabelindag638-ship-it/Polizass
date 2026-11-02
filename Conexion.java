package polizas.General;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    public Connection cnx;
    public PreparedStatement ps;
    public ResultSet rs;

    public void conectar() {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            
            cnx = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/trapy?useSSL=false&serverTimezone=UTC", 
                "root", 
                ""
            );
        } catch (ClassNotFoundException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error crítico: No se encontró la clase del Driver de MySQL en el proyecto.");
            System.out.println("Error ClassNotFound: " + e.getMessage());
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error de SQL al conectar: " + e.getMessage());
            System.out.println("Error de conexión SQL: " + e.getMessage());
        }
    }

    void close() {
        try {
            if (cnx != null) cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar: " + ex.getMessage());
        }
    }
}