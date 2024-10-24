/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprendec.util;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Pablo
 */
public class MySQLConexion {
    static {
        try {
            // CARGAR EL CONTROLADOR DE BD
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("[MySqlConexion] Error al cargar el driver de conexión");
            e.printStackTrace();
        }
    }
 
    // Glassfish 4.2.1 y MYSQL 8.0 (que ahora tiene caching_sha2_password) no son compatible entre sí. Actualice su versión de Glassfish o use Apache
    // https://stackoverflow.com/questions/51586401/glassfish-keystore-error-after-adding-mysql-connector/52129961
    public static Connection obtenerConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ReealoDB2019?useTimezone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "root");
        } catch (Exception e) {
            System.out.println("[MySqlConexion] Error al obtener la conexión: ");
            e.printStackTrace();
        }
        return con;
    }
}
