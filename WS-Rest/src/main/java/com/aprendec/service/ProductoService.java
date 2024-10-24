/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprendec.service;

import com.aprendec.dao.factory.DAOFactory;
import com.aprendec.dao.beans.ProductoDAO;
import com.aprendec.dao.beans.ProductoDTO;
import com.aprendec.util.Constantes;
import java.util.List;
 
public class ProductoService {
 
    // Referenciamos la fábrica específica de daos y le indicamos nuestro origen de datos (MSSQL)
    DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGENDATOS);
 
    // Le pedimos a la fábrica específica el o los daos que necesitemos
    ProductoDAO objProductoDAO = fabrica.getProductoDAO();
 
    // Operaciones
    public String obtenerCodProd() {
        return objProductoDAO.obtenerCodProd();
    }
 
    public ProductoDTO obtenerProducto(String codigo) {
        return objProductoDAO.obtenerProducto(codigo);
    }
 
    public List<productodto> listarProductos(String nombre) {
        return objProductoDAO.listarProductos(nombre);
    }
 
    public int registrarProducto(ProductoDTO productoDTO) {
        return objProductoDAO.registrarProducto(productoDTO);
    }
 
    public int actualizarProducto(ProductoDTO productoDTO) {
        return objProductoDAO.actualizarProducto(productoDTO);
    }
 
    public int eliminarProducto(String codigo) {
        return objProductoDAO.eliminarProducto(codigo);
    }
     
}