/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_negocios;

/**
 *
 * @author Roberto
 */
public class detallesFacturaDTO {
    private int cantidad;
    private String descripcion;
    private double precioUnitario;
    private double importe;

    public detallesFacturaDTO(int cantidad, String descripcion, double precioUnitario, double importe) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
    }

    public detallesFacturaDTO() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getImporte() {
        return importe;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
    
    
    
    
    
    
    
    
}



