/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.proyectop1.accesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Anthony
 */
public class Conexion {
    private static String db = "integradordistri";
    private static String login = "root";
    private static String password = "";
    private static String url = "jdbc:mysql://localhost/" + db;
    private static Connection cnn = null;
    private Conexion() {
    }
    public static Connection getConexionDB() {
        if (cnn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //Drive manager gestor de los driver con metodo get conceccion par ala base
                cnn = DriverManager.getConnection(url, login, password);
                if (cnn != null) {
                    System.out.println("Coneccion a DB ok");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Ya existe una instancia");
        }
        return cnn;
    }
    public void desconectar() {
        Conexion.cnn = null;
    }

}
