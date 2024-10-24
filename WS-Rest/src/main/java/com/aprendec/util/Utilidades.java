/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aprendec.util;

import com.aprendec.service.ProductoService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.bind.DatatypeConverter;
 
public class Utilidades {
     
    private final String ruta = "E:\\";
    //private final String ruta = "/opt/glassfish/glassfish/domains/domain1/applications/ROOT-160/imagenes/";
    //private final String ruta = "web\\imagenes\\";
 
    /* Este método se encarga de decodificar una imagen en formato base 64 y de 
     * guardarla en una ruta del disco duro */
    public String decodificarImagen(String imgbase64, String codProd){
        //String base64String = "data:image/jpeg;base64,iVBORw0KGgoAAAANSUhEUgAAAHkAAAB5C...";
        String base64String = "data:image/jpg;base64," + imgbase64;
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) {//comprobar la extensión de la imagen
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://debería escribir más casos para más tipos de imágenes
                extension = "jpg";
                break;
        }
         
        // convertimos la cadena base64 a datos binarios
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        
        // obtenemos el codigo para el nuevo producto
        if (codProd == null || codProd.equals("")){
            codProd = new ProductoService().obtenerCodProd();
        }
         
        // generamos un nombre para la imagen
        String imagen = "image" + codProd + "." + extension;
        File file = new File(ruta + imagen);
        //System.out.println("[Utilidades] Ruta en donde se guardará la imagen: " + file.getAbsolutePath());
        
        // eliminamos el archivo para volverla a crear
        if (file.exists()){
            if (file.delete())
                System.out.println("[Utilidades] La imagen ha sido borrada satisfactoriamente");
            else
                System.out.println("[Utilidades] La imagen no puede ser borrada");
        }
         
        // siempre creará el archivo a pesar de que no se haya registrado el producto en la base de datos
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
            System.out.println("[Utilidades] La imagen se ha guardado satisfactoriamente");
        } catch (IOException e) {
            System.out.println("[Utilidades] Error al intentar guardar la imagen: ");
            e.printStackTrace();
        }
 
        return imagen;
    }
 
    public void eliminarImagen(String codigo){
        String imagen = ruta + ("image" + codigo + ".jpg");
        File fichero = new File(imagen);
        if (fichero.delete()) {
            System.out.println("[Utilidades] El fichero ha sido borrado satisfactoriamente");
        } else {
            System.out.println("[Utilidades] El fichero no puede ser borrado");
        }
    }
 
}
