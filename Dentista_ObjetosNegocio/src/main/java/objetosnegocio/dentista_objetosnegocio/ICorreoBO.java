/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import objetosnegocio.Excepciones.BOException;

/**
 *
 * @author Roberto
 */
public interface ICorreoBO {
     boolean enviarFactura() throws BOException;
 
    boolean enviarCancelacion() throws BOException;
 
    String generarMensaje() throws BOException;
 
    boolean adjuntarPDF() throws BOException;
 
    boolean validarCorreo() throws BOException;
}
