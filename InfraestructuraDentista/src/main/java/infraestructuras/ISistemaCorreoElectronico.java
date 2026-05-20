/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package infraestructuras;

import java.io.File;

/**
 *
 * @author EdgarUris
 */
public interface ISistemaCorreoElectronico {
    void enviarCorreo(String correoPara, String mot, String cont);
    void enviarCorreoConArchivos(String correoPara, String mot, String cont, File[] archivos);
}
