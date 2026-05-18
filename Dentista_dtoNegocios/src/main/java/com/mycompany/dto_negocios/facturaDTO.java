/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_negocios;

import java.time.LocalDate;

/**
 *
 * @author Roberto
 */
public class facturaDTO {
    private String idFactura;
    private LocalDate fecha;
    private double subtotal;
    private double iva;
    private double total;
    private String estado;
    private boolean pdfGenerado;
    private boolean enviadaCorreo;

    public facturaDTO() {
    }

    public facturaDTO(String idFactura, LocalDate fecha, double subtotal, double iva, double total, String estado, boolean pdfGenerado, boolean enviadaCorreo) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.estado = estado;
        this.pdfGenerado = pdfGenerado;
        this.enviadaCorreo = enviadaCorreo;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPdfGenerado(boolean pdfGenerado) {
        this.pdfGenerado = pdfGenerado;
    }

    public void setEnviadaCorreo(boolean enviadaCorreo) {
        this.enviadaCorreo = enviadaCorreo;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIva() {
        return iva;
    }

    public double getTotal() {
        return total;
    }

    public String getEstado() {
        return estado;
    }

    public boolean isPdfGenerado() {
        return pdfGenerado;
    }

    public boolean isEnviadaCorreo() {
        return enviadaCorreo;
    }
    
    
    
    
    
}
