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
public interface IFacturaBO {
    boolean generarFactura() throws BOException;
 
    double calcularIVA() throws BOException;
 
    double calcularSubtotal() throws BOException;
 
    double calcularTotal() throws BOException;
 
    boolean validarDatosFiscales() throws BOException;
 
    boolean guardarFactura() throws BOException;
}
