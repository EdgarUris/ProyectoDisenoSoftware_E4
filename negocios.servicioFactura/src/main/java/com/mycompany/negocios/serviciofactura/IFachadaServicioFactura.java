/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.negocios.serviciofactura;

/**
 *
 * @author Roberto
 */
public interface IFachadaServicioFactura {
     List<Cita> cargarCitasFacturables() throws BOException;
 
    /**
     * Ejecuta el flujo completo de generación de factura para una cita:
     * validar cita → obtener paciente → validar datos fiscales →
     * crear factura → calcular totales → guardar → generar PDF → enviar correo.
     *
     * @param citaId   ID de la cita a facturar.
     * @param usoCFDI  Clave de uso CFDI.
     * @param detalles Detalles (conceptos) de la factura.
     * @return Factura generada y persistida.
     * @throws BOException si cualquier paso del flujo falla.
     */
    Factura generarFactura(Long citaId, String usoCFDI,
                           List<DetalleFactura> detalles) throws BOException;
 
    /**
     * Descarga el PDF de una factura ya generada.
     *
     * @param idFactura ID de la factura.
     * @return Ruta o URL de descarga del PDF.
     * @throws BOException si la factura no existe o falla la generación del PDF.
     */
    String descargarPDF(Long idFactura) throws BOException;
 
    /**
     * Reenvía la factura por correo electrónico al paciente.
     *
     * @param idFactura     ID de la factura a reenviar.
     * @param correoDestino Correo electrónico destino.
     * @return true si el correo fue enviado correctamente.
     * @throws BOException si el correo no es válido o falla el envío.
     */
    boolean reenviarPorCorreo(Long idFactura, String correoDestino) throws BOException;
}
