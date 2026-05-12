/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package infraestructuras;

import java.io.File;

/**
 *
 * @author EdgarUris
 * @author JeniferFl
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SistemaCorreoElectronico sce = new SistemaCorreoElectronico();
               SistemaReceta receta = new SistemaReceta();
               receta.crearReceta();

                      File archivo = new File("RecetaDental.pdf");

sce.enviarCorreoConArchivos("jenifermaribelalamea@gmail.com", "Competencia por el edgar o que", "que apuestas por ese culito",  new File[]{ new File("RecetaDental.pdf")});
    


    }
    
}
