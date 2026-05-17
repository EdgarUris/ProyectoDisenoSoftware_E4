/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import entidades.Pago;
import objetosnegocio.Excepciones.BOException;

/**
 *
 * @author Roberto
 */
public interface IPagoBO {
    boolean procesarPago(Pago pago) throws BOException;
 
    boolean validarPago(Pago pago) throws BOException;
 
    void confirmarPago(Pago pago) throws BOException;
 
    String obtenerEstadoPago() throws BOException;
}
