/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocios.serviciofactura;

/**
 *
 * @author Roberto
 */


import DAOs.ICitaDAO;
import DAOs.IFacturaDAO;
import DAOs.IAccesosDatos;
import DAOs.IPacienteDAO;
import entidades.Cita;
import entidades.DetalleFactura;
import entidades.Factura;
import entidades.Paciente;
import objetosnegocio.CitaBO;
import objetosnegocio.CorreoBO;
import objetosnegocio.FacturaBO;
import objetosnegocio.PacienteBO;
import objetosnegocio.PDFBO;
import objetosnegocio.Excepciones.BOException;

import Dentista_dtoNegocios.PacienteDTO;
import java.util.List;


public class ControlServicioFactura {
     // ─── BOs usados por este control ─────────────────────────────────────────
    private final CitaBO citaBO;
    private final PacienteBO pacienteBO;
    private final FacturaBO facturaBO;
    private final PDFBO pdfBO;
    private final CorreoBO correoBO;
 
    // ─── Boundaries de infraestructura ───────────────────────────────────────
    private final SistemaDePagos sistemaDePagos;
    private final SistemaCorreoElectronico sistemaCorreo;
    private final SistemaPDF sistemaPDF;
 
    // ─── Estado interno del flujo ─────────────────────────────────────────────
    private Cita citaSeleccionada;
    private Paciente pacienteActual;
    private Factura facturaActual;
 
    // ─── Constructor ──────────────────────────────────────────────────────────
 
    public ControlGenerarFactura(IPacienteDAO pDAO,
                                 ICitaDAO cDAO,
                                 IFacturaDAO fDAO,
                                 IAccesosDatos accesoDatos,
                                 SistemaDePagos sistemaDePagos,
                                 SistemaCorreoElectronico sistemaCorreo,
                                 SistemaPDF sistemaPDF) {
 
        this.citaBO         = new CitaBO(cDAO, pDAO, null);
        this.pacienteBO     = new PacienteBO(pDAO);
        this.facturaBO      = FacturaBO.getInstancia(accesoDatos, fDAO);
        this.pdfBO          = new PDFBO();
        this.correoBO       = new CorreoBO(sistemaCorreo);
        this.sistemaDePagos = sistemaDePagos;
        this.sistemaCorreo  = sistemaCorreo;
        this.sistemaPDF     = sistemaPDF;
    }
 
    // ─── Métodos exactos del diagrama ─────────────────────────────────────────
 
    /**
     * Carga la lista de citas en estado COMPLETADA disponibles para facturar.
     * Llamado desde el boundary ListadeCitasparaFacturar.
     *
     * @return Lista de citas facturables.
     * @throws BOException si ocurre un error al consultar.
     */
    public List<Cita> cargarCitasFacturables() throws BOException {
        return citaBO.obtenerCitasFacturables();
    }
 
    /**
     * Valida que la cita seleccionada esté en estado COMPLETADA y no haya sido
     * facturada previamente.
     *
     * @param citaId ID de la cita a validar.
     * @throws BOException si la cita no es facturable.
     */
    public void validarCitaFacturable(Long citaId) throws BOException {
        List<Cita> facturables = citaBO.obtenerCitasFacturables();
        this.citaSeleccionada = facturables.stream()
                .filter(c -> citaId.equals(c.getId()))
                .findFirst()
                .orElseThrow(() -> new BOException(
                        "La cita con ID " + citaId + " no está disponible para facturar."));
    }
 
    /**
     * Obtiene y carga los datos del paciente asociado a la cita seleccionada.
     *
     * @throws BOException si el paciente no existe.
     */
    public void obtenerPaciente() throws BOException {
        if (citaSeleccionada == null) {
            throw new BOException("Debe seleccionar una cita antes de obtener el paciente.");
        }
        pacienteBO.setId(String.valueOf(citaSeleccionada.getPacienteId()));
        PacienteDTO dto = pacienteBO.obtenerDatosPaciente();
        this.pacienteActual = dto.toEntidad();
    }
 
    /**
     * Valida que el paciente tenga RFC y uso de CFDI correctos para facturar.
     *
     * @throws BOException si los datos fiscales son inválidos.
     */
    public void validarDatosFiscales() throws BOException {
        if (pacienteActual == null) {
            throw new BOException("No hay paciente cargado para validar datos fiscales.");
        }
        String rfc = pacienteActual.getRfc();
        if (rfc == null || rfc.trim().isEmpty()) {
            throw new BOException("El RFC del paciente está vacío.");
        }
        if (rfc.length() != 12 && rfc.length() != 13) {
            throw new BOException("El RFC '" + rfc + "' no tiene formato válido.");
        }
    }
 
    /**
     * Valida los datos capturados en el boundary DetallesdelaFactura
     * (uso de CFDI, detalles de servicios).
     *
     * @param usoCFDI  Clave de uso CFDI capturada por la UI.
     * @param detalles Detalles de la factura capturados por la UI.
     * @throws BOException si algún dato es inválido.
     */
    public void validarDatosFactura(String usoCFDI, List<DetalleFactura> detalles) throws BOException {
        if (usoCFDI == null || usoCFDI.trim().isEmpty()) {
            throw new BOException("El uso de CFDI es obligatorio.");
        }
        if (detalles == null || detalles.isEmpty()) {
            throw new BOException("Debe agregar al menos un detalle a la factura.");
        }
        for (DetalleFactura d : detalles) {
            if (d.getDescripcion() == null || d.getDescripcion().trim().isEmpty()) {
                throw new BOException("La descripción de un detalle no puede estar vacía.");
            }
            if (d.getPrecioUnitario() <= 0) {
                throw new BOException("El precio unitario de '" + d.getDescripcion() + "' debe ser mayor a cero.");
            }
        }
    }
 
    /**
     * Construye la entidad Factura con los datos del paciente, la cita y los detalles.
     *
     * @param usoCFDI  Clave CFDI.
     * @param detalles Lista de detalles de la factura.
     * @throws BOException si faltan datos para construir la factura.
     */
    public void crearFactura(String usoCFDI, List<DetalleFactura> detalles) throws BOException {
        if (citaSeleccionada == null || pacienteActual == null) {
            throw new BOException("Faltan datos de cita o paciente para crear la factura.");
        }
        this.facturaActual = new Factura();
        facturaActual.setCitaId(citaSeleccionada.getId());
        facturaActual.setPacienteId(pacienteActual.getId());
        facturaActual.setRfcPaciente(pacienteActual.getRfc());
        facturaActual.setUsoCFDI(usoCFDI);
        for (DetalleFactura d : detalles) {
            d.calcularImporte();
            facturaActual.agregarDetalle(d);
        }
        facturaBO.setFacturaActual(facturaActual);
    }
 
    /**
     * Calcula subtotal, IVA y total de la factura usando FacturaBO.
     *
     * @throws BOException si no hay factura activa.
     */
    public void calcularTotales() throws BOException {
        facturaBO.calcularSubtotal();
        facturaBO.calcularIVA();
        facturaBO.calcularTotal();
        // Sincronizar con la entidad local
        this.facturaActual = facturaBO.getFacturaActual();
    }
 
    /**
     * Persiste la factura en la base de datos y actualiza su estado a EMITIDA.
     * Después genera el PDF y envía el correo al paciente.
     *
     * @throws BOException si ocurre un error al guardar, generar PDF o enviar correo.
     */
    public void guardarFactura() throws BOException {
        // 1. Validar datos fiscales en FacturaBO
        facturaBO.validarDatosFiscales();
 
        // 2. Guardar con FacturaBO
        if (!facturaBO.guardarFactura()) {
            throw new BOException("No se pudo guardar la factura en la base de datos.");
        }
        this.facturaActual = facturaBO.getFacturaActual();
 
        // 3. Generar PDF via PDFBO → SistemaPDF
        pdfBO.setFactura(facturaActual);
        pdfBO.generarPDF();
        String rutaPDF = pdfBO.descargarPDF();
 
        // 4. Enviar correo via CorreoBO → SistemaCorreoElectrónico
        correoBO.setDestinatario(pacienteActual.getCorreo());
        correoBO.setArchivoPDF(facturaActual.getPdfBytes());
        correoBO.generarMensaje();
        correoBO.adjuntarPDF();
        correoBO.enviarFactura();
    }
 
    /**
     * Maneja los errores del flujo mostrando información al boundary ErrorGenerarFactura.
     *
     * @param ex Excepción capturada.
     * @return Mensaje de error formateado para mostrar en la UI.
     */
    public String manejarError(BOException ex) {
        System.getLogger(ControlServicioFactura.class.getName())
                .log(System.Logger.Level.ERROR, ex.getMessage(), ex);
        return "Error en el proceso de facturación: " + ex.getMessage();
    }
 
    // ─── Getters del estado del flujo ─────────────────────────────────────────
 
    public Factura getFacturaActual() { return facturaActual; }
    public Cita getCitaSeleccionada() { return citaSeleccionada; }
    public Paciente getPacienteActual() { return pacienteActual; }
}
