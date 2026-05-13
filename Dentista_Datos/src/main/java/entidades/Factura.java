/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;

/**
 *
 * @author EdgarUris
 */
public class Factura {
    
    private LocalDate fecha;
    private double subtotal;
    private double iva;
    private double total;
    private String estado;
    private boolean pdfGenerado;
    private boolean enviadoCorreo;

    public Factura() {
    }

    public Factura(LocalDate fecha, double subtotal, double iva, double total, String estado, boolean pdfGenerado, boolean enviadoCorreo) {
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.estado = estado;
        this.pdfGenerado = pdfGenerado;
        this.enviadoCorreo = enviadoCorreo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isPdfGenerado() {
        return pdfGenerado;
    }

    public void setPdfGenerado(boolean pdfGenerado) {
        this.pdfGenerado = pdfGenerado;
    }

    public boolean isEnviadoCorreo() {
        return enviadoCorreo;
    }

    public void setEnviadoCorreo(boolean enviadoCorreo) {
        this.enviadoCorreo = enviadoCorreo;
    }
}
