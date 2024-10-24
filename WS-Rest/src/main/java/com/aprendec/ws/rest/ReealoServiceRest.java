/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprendec.ws.rest;

import com.aprendec.dao.beans.ProductoDTO;
import com.aprendec.service.ProductoService;
import com.aprendec.util.Utilidades;
import com.google.gson.Gson;
import java.io.File;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
 
@Path("aprendec")
public class ReealoServiceRest {
     
    public ReealoServiceRest() {
    }
 
    /* TABLA PRODUCTO */
    @GET
    @Path("obtenerProducto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerProducto(@QueryParam("codigo") String codigo) {
        try {
            ProductoDTO producto = new ProductoService().obtenerProducto(codigo);
            String json = new Gson().toJson(producto);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SEE_OTHER).entity("Error: " + e.toString()).build();
        }
    }
 
    @GET
    @Path("listarProductos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProductos(@QueryParam("descripcion") String nombre) {
        try {
            List<productodto> productos = new ProductoService().listarProductos(nombre);
            String json = new Gson().toJson(productos);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SEE_OTHER).entity("Error: " + e.toString()).build();
        }
    }
     
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("registrarProducto")
    public int registrarProducto(String json){
        try {
            Gson gson = new Gson();
            ProductoDTO producto = (ProductoDTO) gson.fromJson(json, ProductoDTO.class);
            // antes de mandar el producto para su registro en la bd, decodificamos y guardamos la imagen que viene en base64
            Utilidades util = new Utilidades();
            String nombreImagen = util.decodificarImagen(producto.getImagen(), null);
            producto.setImagen(nombreImagen);// end
            return new ProductoService().registrarProducto(producto);
        } catch (Exception e) {
            return -1;
        }
    }
 
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("actualizarProducto")
    public int actualizarProducto(String json) {
        try {
            Gson gson = new Gson();
            ProductoDTO producto = (ProductoDTO) gson.fromJson(json, ProductoDTO.class);
            // antes de mandar el producto para su actualización en la bd, decodificamos y guardamos la imagen que viene en base64
            Utilidades util = new Utilidades();
            String nombreImagen = util.decodificarImagen(producto.getImagen(), producto.getCodigo());
            producto.setImagen(nombreImagen);//end
            return new ProductoService().actualizarProducto(producto);
        } catch (Exception e) {
            return -1;
        }
    }
 
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("eliminarProducto")
    public int eliminarProducto(@QueryParam("codigo")String codigo) {
        try {
            int resultado = new ProductoService().eliminarProducto(codigo);
            if (resultado == 1) {
                // eliminar también la imagen del disco duro
                Utilidades utilidades = new Utilidades();
                utilidades.eliminarImagen(codigo);
            }
            return resultado;
        } catch (Exception e) {
            return -1;
        }
    }
 
}
