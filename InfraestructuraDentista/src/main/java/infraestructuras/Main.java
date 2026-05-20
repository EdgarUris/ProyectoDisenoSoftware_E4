/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package infraestructuras;

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
        ISistemaCorreoElectronico sce = new SistemaCorreoElectronico();
        sce.enviarCorreo("jenifer.alamea261147@potros.itson.edu.mx", "disque el proyecto nos está matando", 
                "aqui mandando mensajes por java para probar el sistema externo");
    }
    
}
