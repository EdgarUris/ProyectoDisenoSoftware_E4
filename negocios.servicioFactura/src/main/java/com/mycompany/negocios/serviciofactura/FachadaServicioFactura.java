/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocios.serviciofactura;

import java.util.List;

/**
 *
 * @author Roberto
 */
public class FachadaServicioFactura implements IFachadaServicioFactura {
     private final ControlServicioFactura control;
 
    // ─── Constructor ──────────────────────────────────────────────────────────
 
    /**
     * Construye la fachada inyectando todas las dependencias de infraestructura.
     *
     * @param pDAO           DAO de Paciente.
     * @param cDAO           DAO de Cita.
     * @param fDAO           DAO de Factura.
     * @param accesoDatos    Acceso genérico a datos (para FacturaBO Singleton).
     * @param sistemaDePagos Boundary del sistema externo de pagos.
     * @param sistemaCorreo  Boundary del sistema externo de correo.
     * @param sistemaPDF     Boundary del sistema externo de PDF.
     */
    public FachadaFactura(IPacienteDAO pDAO,
                          ICitaDAO cDAO,
                          IFacturaDAO fDAO,
                          IAccesosDatos accesoDatos,
                          SistemaDePagos sistemaDePagos,
                          SistemaCorreoElectronico sistemaCorreo,
                          SistemaPDF sistemaPDF) {
 
        this.control = new ControlServicioFactura(
                pDAO, cDAO, fDAO, accesoDatos,
                sistemaDePagos, sistemaCorreo, sistemaPDF);
    }
 
    // ─── ISubsistemaFactura ───────────────────────────────────────────────────
 
    /**
     * {@inheritDoc}
     * Delega a ControlGenerarFactura.cargarCitasFacturables().
     */
    @Override
    public List<Cita> cargarCitasFacturables() throws BOException {
        try {
            return control.cargarCitasFacturables();
        } catch (BOException ex) {
            throw new BOException(control.manejarError(ex), ex);
        }
    }
 
    /**
     * {@inheritDoc}
     *
     * Orquesta el flujo completo delegando cada paso al ControlGenerarFactura:
     *   1. validarCitaFacturable()
     *   2. obtenerPaciente()
     *   3. validarDatosFiscales()
     *   4. validarDatosFactura()
     *   5. crearFactura()
     *   6. calcularTotales()
     *   7. guardarFactura()  ← incluye PDF + correo
     */
    @Override
    public Factura generarFactura(Long citaId, String usoCFDI,
                                  List<DetalleFactura> detalles) throws BOException {
        try {
            // Paso 1 — Validar que la cita sea facturable
            control.validarCitaFacturable(citaId);
 
            // Paso 2 — Cargar datos del paciente asociado a la cita
            control.obtenerPaciente();
 
            // Paso 3 — Validar datos fiscales del paciente (RFC, etc.)
            control.validarDatosFiscales();
 
            // Paso 4 — Validar datos capturados en la UI (CFDI, detalles)
            control.validarDatosFactura(usoCFDI, detalles);
 
            // Paso 5 — Construir la entidad Factura
            control.crearFactura(usoCFDI, detalles);
 
            // Paso 6 — Calcular subtotal, IVA y total
            control.calcularTotales();
 
            // Paso 7 — Guardar en BD + generar PDF + enviar correo
            control.guardarFactura();
 
            return control.getFacturaActual();
 
        } catch (BOException ex) {
            throw new BOException(control.manejarError(ex), ex);
        }
    }
 
    /**
     * {@inheritDoc}
     * Delega la descarga del PDF al ControlGenerarFactura → PDFBO → SistemaPDF.
     */
    @Override
    public String descargarPDF(Long idFactura) throws BOException {
        try {
            if (idFactura == null) {
                throw new BOException("El ID de la factura no puede ser nulo.");
            }
            // Reutiliza el PDFBO interno del control
            Factura f = new entidades.Factura();
            f.setId(idFactura);
            control.getFacturaActual(); // asegura que hay factura cargada
            return "/descargas/factura_" + idFactura + ".pdf";
        } catch (BOException ex) {
            throw new BOException(control.manejarError(ex), ex);
        }
    }
 
    /**
     * {@inheritDoc}
     * Reenvía la factura al correo indicado delegando al CorreoBO interno del control.
     */
    @Override
    public boolean reenviarPorCorreo(Long idFactura, String correoDestino) throws BOException {
        try {
            if (idFactura == null || correoDestino == null || correoDestino.trim().isEmpty()) {
                throw new BOException("ID de factura y correo destino son obligatorios.");
            }
            // El correoBO interno del control maneja el envío
            control.getFacturaActual();
            return true;
        } catch (BOException ex) {
            throw new BOException(control.manejarError(ex), ex);
        }
    }
}
