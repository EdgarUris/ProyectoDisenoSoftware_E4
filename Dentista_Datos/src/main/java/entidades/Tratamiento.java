/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import org.bson.types.ObjectId;

/**
 *
 * @author EdgarUris
 */
public class Tratamiento {
    private ObjectId _id;
    private String nombre;
    private Double costo;

    public Tratamiento() {
    }

    public Tratamiento(String nombre, Double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    public Tratamiento(ObjectId _id, String nombre, Double costo) {
        this._id = _id;
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

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }
    
    
}
