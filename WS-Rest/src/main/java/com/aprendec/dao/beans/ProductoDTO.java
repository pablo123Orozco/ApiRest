/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprendec.dao.beans;

/**
 *
 * @author Pablo
 */
public class ProductoDTO {
    
    private String codigo;
    private String descripcion;
    private String detalle;
    private int stock;
    private double precio;
    private String imagen;
 
    public ProductoDTO(String codigo, String descripcion, String detalle, int stock, double precio, String imagen) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.stock = stock;
        this.precio = precio;
        this.imagen = imagen;
    }
 
    public ProductoDTO(String codigo) {
        this.codigo = codigo;
 
    }
    
     /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }
 
    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
 
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 
    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }
 
    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
 
    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }
 
    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
 
    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }
 
    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
 
    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }
 
    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
 
    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", descripcion=" + descripcion + ", detalle=" + detalle + ", stock=" + stock + ", precio=" + precio + ", imagen=" + imagen + '}';
    }
    
}
