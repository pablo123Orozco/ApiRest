/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprendec.dao.factory;
import com aprendec.dao.beans.ProductoDAO;

/**
 *
 * @author Pablo
 */
public abstract class DAOFactory {
 
    // Definimos unas constantes por cada base de datos
    public static final int MYSQL = 1;
    public static final int SQLSERVER = 2;
 
    // Existirá un método get por cada DAO que exista en el sistema
    public abstract ProductoDAO getProductoDAO();
 
    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL:
                return new MySqlDAOFactory();
            case SQLSERVER:
                return new SqlServerDAOFactory();
            default:
                return null;
        }
    }
}
