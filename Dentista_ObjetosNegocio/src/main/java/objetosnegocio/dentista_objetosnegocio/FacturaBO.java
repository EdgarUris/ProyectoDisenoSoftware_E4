/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import entidades.Factura;
import objetosnegocio.Excepciones.BOException;

/**
 *
 * @author Roberto
 */

//PONER IMPLEMENTS IFACTURABO
public class FacturaBO {
//    private static FacturaBO instancia;
// 
//    private final IAccesosDatos accesoDatos;
//    private final IFacturaDAO facturaDAO;
// 
//    private Factura facturaActual;
// 
//    private static final double TASA_IVA = 0.16;
// 
//    private FacturaBO(IAccesosDatos accesoDatos, IFacturaDAO facturaDAO) {
//        this.accesoDatos = accesoDatos;
//        this.facturaDAO = facturaDAO;
//    }
// 
//    public static FacturaBO getInstancia(IAccesosDatos accesoDatos, IFacturaDAO facturaDAO) {
//        if (instancia == null) {
//            instancia = new FacturaBO(accesoDatos, facturaDAO);
//        }
//        return instancia;
//    }
// 
//    // ─── Métodos de configuración ─────────────────────────────────────────────
// 
//    public void setFacturaActual(Factura factura) {
//        this.facturaActual = factura;
//    }
// 
//    public Factura getFacturaActual() {
//        return facturaActual;
//    }
// 
//    // ─── Implementación de IFacturaBO ─────────────────────────────────────────
// 
//    /**
//     * Genera la factura calculando todos los totales y preparándola para guardar.
//     *
//     * @return true si la factura fue generada correctamente.
//     * @throws BOException si los datos fiscales son inválidos.
//     */
//    @Override
//    public boolean generarFactura() throws BOException {
//        if (facturaActual == null) {
//            throw new BOException("No hay factura configurada para generar.");
//        }
//        if (!validarDatosFiscales()) {
//            throw new BOException("Los datos fiscales del paciente son inválidos.");
//        }
//        calcularSubtotal();
//        calcularIVA();
//        calcularTotal();
//        return true;
//    }
// 
//    /**
//     * Calcula el IVA (16%) sobre el subtotal de la factura.
//     *
//     * @return Monto del IVA calculado.
//     * @throws BOException si no hay factura activa.
//     */
//    @Override
//    public double calcularIVA() throws BOException {
//        if (facturaActual == null) {
//            throw new BOException("No hay factura activa para calcular el IVA.");
//        }
//        double iva = facturaActual.getSubtotal() * TASA_IVA;
//        facturaActual.setIva(iva);
//        return iva;
//    }
// 
//    /**
//     * Calcula el subtotal sumando los importes de todos los detalles.
//     *
//     * @return Subtotal calculado.
//     * @throws BOException si no hay factura activa o no tiene detalles.
//     */
//    @Override
//    public double calcularSubtotal() throws BOException {
//        if (facturaActual == null) {
//            throw new BOException("No hay factura activa para calcular el subtotal.");
//        }
//        List<DetalleFactura> detalles = facturaActual.getDetalles();
//        if (detalles == null || detalles.isEmpty()) {
//            throw new BOException("La factura no tiene detalles para calcular el subtotal.");
//        }
//        double subtotal = 0;
//        for (DetalleFactura detalle : detalles) {
//            subtotal += detalle.getImporte();
//        }
//        facturaActual.setSubtotal(subtotal);
//        return subtotal;
//    }
// 
//    /**
//     * Calcula el total sumando subtotal e IVA.
//     *
//     * @return Total de la factura.
//     * @throws BOException si no hay factura activa.
//     */
//    @Override
//    public double calcularTotal() throws BOException {
//        if (facturaActual == null) {
//            throw new BOException("No hay factura activa para calcular el total.");
//        }
//        double total = facturaActual.getSubtotal() + facturaActual.getIva();
//        facturaActual.setTotal(total);
//        return total;
//    }
// 
//    /**
//     * Valida que el RFC del paciente tenga formato correcto para facturar.
//     * RFC persona física: 13 caracteres. Persona moral: 12 caracteres.
//     *
//     * @return true si los datos fiscales son válidos.
//     * @throws BOException si no hay factura o paciente asociado.
//     */
//    @Override
//    public boolean validarDatosFiscales() throws BOException {
//        if (facturaActual == null) {
//            throw new BOException("No hay factura activa para validar datos fiscales.");
//        }
//        String rfc = facturaActual.getRfcPaciente();
//        if (rfc == null || rfc.trim().isEmpty()) {
//            throw new BOException("El RFC del paciente está vacío.");
//        }
//        if (rfc.length() != 12 && rfc.length() != 13) {
//            throw new BOException("El RFC '" + rfc + "' no tiene un formato válido.");
//        }
//        if (facturaActual.getUsoCFDI() == null || facturaActual.getUsoCFDI().trim().isEmpty()) {
//            throw new BOException("El uso de CFDI no puede estar vacío.");
//        }
//        return true;
//    }
// 
//    /**
//     * Persiste la factura en la base de datos.
//     *
//     * @return true si la factura fue guardada correctamente.
//     * @throws BOException si ocurre un error al guardar.
//     */
//    @Override
//    public boolean guardarFactura() throws BOException {
//        if (facturaActual == null) {
//            throw new BOException("No hay factura para guardar.");
//        }
//        try {
//            facturaActual.setEstado("EMITIDA");
//            return facturaDAO.create(facturaActual);
//        } catch (DAOException ex) {
//            throw new BOException("Error al guardar la factura: " + ex.getMessage(), ex);
//        }
//    }
}
