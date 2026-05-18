/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import entidades.Pago;
import objetosnegocio.Excepciones.BOException;

/**
 *
 * @author Roberto
 */
public class PagoBO implements IPagoBO {
    // ─── Singleton ────────────────────────────────────────────────────────────
    private static PagoBO instancia;
 
    // ─── Atributos del diagrama ───────────────────────────────────────────────
    private final IServicioPago servicioPago;   // -servicioPago : IServicioPago
    private final PagoMapper pagoMapper;        // -pagoMapper   : PagoMapper  ← agregado
    private final IPagoDAO pagoDAO;
 
    // ─── Constructor privado (Singleton) ─────────────────────────────────────
 
    private PagoBO(IPagoDAO pagoDAO, IServicioPago servicioPago) {
        this.pagoDAO      = pagoDAO;
        this.servicioPago = servicioPago;
        this.pagoMapper   = new PagoMapper();   // instanciado internamente
    }
 
    public static PagoBO getInstancia(IPagoDAO pagoDAO, IServicioPago servicioPago) {
        if (instancia == null) {
            instancia = new PagoBO(pagoDAO, servicioPago);
        }
        return instancia;
    }
 
    // ─── Implementación de IPagoBO ────────────────────────────────────────────
 
    /**
     * Procesa el pago: valida, convierte a DTO mediante pagoMapper,
     * lo envía al servicio externo y lo persiste si fue exitoso.
     *
     * @param pago Entidad con los datos del pago a procesar.
     * @return true si el pago fue procesado exitosamente.
     * @throws BOException si los datos son inválidos o el servicio falla.
     */
    @Override
    public boolean procesarPago(Pago pago) throws BOException {
        // 1. Validar datos del pago
        validarPago(pago);
 
        // 2. Convertir entidad → DTO usando pagoMapper antes de enviar al servicio
        PagoDTO pagoDTO = pagoMapper.toDTO(pago);
 
        try {
            // 3. Enviar al servicio externo de pagos (usa DTO)
            boolean resultado = servicioPago.enviar(pagoDTO);
 
            if (resultado) {
                // 4. Actualizar estado en la entidad y persistir con DAO
                pago.setEstado("PROCESADO");
                pagoDAO.create(pago);
            }
            return resultado;
 
        } catch (DAOException ex) {
            throw new BOException("Error al registrar el pago: " + ex.getMessage(), ex);
        }
    }
 
    /**
     * Valida que los datos del pago sean correctos antes de procesarlo.
     *
     * @param pago Entidad a validar.
     * @return true si los datos son válidos.
     * @throws BOException si el pago es nulo o sus datos son incorrectos.
     */
    @Override
    public boolean validarPago(Pago pago) throws BOException {
        if (pago == null) {
            throw new BOException("El pago no puede ser nulo.");
        }
        if (pago.getMonto() <= 0) {
            throw new BOException("El monto del pago debe ser mayor a cero.");
        }
        if (pago.getMetodoPago() == null || pago.getMetodoPago().trim().isEmpty()) {
            throw new BOException("El método de pago no puede estar vacío.");
        }
        return true;
    }
 
    /**
     * Confirma la recepción del pago en el sistema externo y actualiza su estado.
     * Convierte la entidad a DTO con pagoMapper antes de persistir.
     *
     * @param pago Entidad del pago a confirmar.
     * @throws BOException si el pago no tiene ID o falla la actualización.
     */
    @Override
    public void confirmarPago(Pago pago) throws BOException {
        if (pago == null || pago.getId() == null) {
            throw new BOException("No se puede confirmar un pago sin ID.");
        }
        try {
            // Actualizar estado en la entidad
            pago.setEstado("CONFIRMADO");
 
            // Convertir a DTO con pagoMapper para persistencia
            PagoDTO pagoDTO = pagoMapper.toDTO(pago);
 
            // Persistir via DAO (usando la entidad actualizada)
            pagoDAO.update(pago);
 
        } catch (DAOException ex) {
            throw new BOException("Error al confirmar el pago: " + ex.getMessage(), ex);
        }
    }
 
    /**
     * Obtiene el estado actual del pago desde el servicio externo de pagos.
     *
     * @return Estado del pago como String (ej. "PROCESADO", "RECHAZADO").
     * @throws BOException si no se puede consultar el estado.
     */
    @Override
    public String obtenerEstadoPago() throws BOException {
        try {
            return servicioPago.consultarEstado();
        } catch (Exception ex) {
            throw new BOException("Error al obtener el estado del pago: " + ex.getMessage(), ex);
        }
    }
}
