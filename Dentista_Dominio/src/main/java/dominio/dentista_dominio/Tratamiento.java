/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.dentista_dominio;

import org.bson.types.ObjectId;

/**
 *
 * @author EdgarUris
 */
public class Tratamiento {
    private String nombre;
    private Double costo;

    public Tratamiento() {
    }

    public Tratamiento(String nombre, Double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }
}
