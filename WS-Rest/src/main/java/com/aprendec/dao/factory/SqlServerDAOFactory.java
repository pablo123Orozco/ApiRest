/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprendec.dao.factory;
import com.aprendec.dao.beans.ProductoDAO;
import com.aprendec.dao.SqlServerProductoDAO;
/**
 *
 * @author Pablo
 */
public class SqlServerDAOFactory extends DAOFactory{
    public SqlServerDAOFactory() {
    }
 
    // Estamos aplicando la 2da forma de polimorfismo.
    // Una clase puede ser enmascarada con la interface que implementa
    @Override
    public ProductoDAO getProductoDAO() {
        return new SqlServerProductoDAO();
    }
}
