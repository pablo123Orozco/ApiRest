/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprendec.dao.beans;
import com.aprendec.dao.beans.ProductoDTO;
import com.aprendec.util.MySQLConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Pablo
 */
/**
 *
 * @author XxkokoxXT
 */
public class MySqlProductoDAO implements ProductoDAO {
 
    public MySqlProductoDAO() {
    }
 
    @Override
    public String obtenerCodProd() {
        String cod = "image";
        try {
            Connection cn = MySqlConexion.obtenerConexion();
            String query = "SELECT CONCAT('P', LPAD(MAX(RIGHT(codProd, 1)+1), 3, '0')) FROM productos;";
            PreparedStatement ps = cn.prepareCall(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                cod = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("[MySqlProductoDAO] Error al intentar obtener el código del producto: " + e);
        }
        return cod;
    }
 
    @Override
    public ProductoDTO obtenerProducto(String codigo) {
        ProductoDTO objCli = null;
        Connection cn = MySqlConexion.obtenerConexion();
        try {
            String sentencia = "SELECT * FROM productos WHERE codProd = ?";
            PreparedStatement pst = cn.prepareStatement(sentencia);
            pst.setString(1, codigo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                objCli = new ProductoDTO(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getString(6));
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("[MySqlProductoDAO] Error al intentar obtener la información: " + e);
        }
        return objCli;
    }
 
    @Override
    public List<productodto> listarProductos(String nombre) {
        List<productodto> productosList = new ArrayList<productodto>();
        Connection cn = MySqlConexion.obtenerConexion();
        try {
            String query = "SELECT * FROM productos WHERE descripcion LIKE ?";
            PreparedStatement ps = cn.prepareCall(query);
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            ProductoDTO p;
            while (rs.next()) {
                p = new ProductoDTO(rs.getString(1), 
                                    rs.getString(2), 
                                    rs.getString(3), 
                                    rs.getInt(4), 
                                    rs.getDouble(5), 
                                    rs.getString(6));
                productosList.add(p);
            }
        } catch (SQLException e) {
            System.out.println("[MySqlProductoDAO] Error al intentrar listar la información: " + e);
        }
        return productosList;
    }
 
    @Override
    public int registrarProducto(ProductoDTO productoDTO) {
        int resultado = 0;
        String sql = "INSERT INTO productos (codProd, descripcion, detalle, stock, precio, imagen) "
                //+ "VALUES (select CONCAT('P', LPAD(MAX(RIGHT(codProd, 1)+1), 3, '0')) from productos, ?, ?, ?, ?, ? )";
        + "SELECT CONCAT('P', LPAD(MAX(RIGHT(codProd, 1)+1), 3, '0')), ?, ?, ?, ?, ? FROM productos";
        Connection cn = MySqlConexion.obtenerConexion();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, productoDTO.getDescripcion());
            ps.setString(2, productoDTO.getDetalle());
            ps.setInt(3, productoDTO.getStock());
            ps.setDouble(4, productoDTO.getPrecio());
            ps.setString(5, productoDTO.getImagen());
            resultado = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("[MySqlProductoDAO] Error al intentar almacenar la información: " + e);
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("[MySqlProductoDAO] Error al intentar cerrar la conexión: " + ex.getMessage());
            }
        }
        return resultado;
    }
 
    @Override
    public int actualizarProducto(ProductoDTO productoDTO) {
        int resultado = 0;
        String sql = "UPDATE productos SET descripcion = ?, detalle = ?, stock = ?, precio = ?, imagen = ? where codProd = ?";
        Connection cn = MySqlConexion.obtenerConexion();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, productoDTO.getDescripcion());
            ps.setString(2, productoDTO.getDetalle());
            ps.setInt(3, productoDTO.getStock());
            ps.setDouble(4, productoDTO.getPrecio());
            ps.setString(5, productoDTO.getImagen());
            ps.setString(6, productoDTO.getCodigo());
            resultado = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("[MySqlProductoDAO] Error al intentar actualizar la información: " + e);
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("[MySqlProductoDAO] Error al intentar cerrar la conexión: " + ex.getMessage());
            }
        }
        return resultado;
    }
 
    @Override
    public int eliminarProducto(String codigo) {
        int resultado = 0;
        String sql = "DELETE FROM productos WHERE codProd  = '" + codigo + "'";
        Connection cn = MySqlConexion.obtenerConexion();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            resultado = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("[MySqlProductoDAO] Error al intentar eliminar la información: " + e);
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("[MySqlProductoDAO] Error al intentar cerrar la conexión: " + ex.getMessage());
            }
        }
        return resultado;
    }
     
}