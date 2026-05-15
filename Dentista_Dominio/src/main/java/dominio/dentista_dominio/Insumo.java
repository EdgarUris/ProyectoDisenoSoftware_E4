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
public class Insumo {
    private ObjectId _id;
    private String nombre;
    private Integer stock;

    public Insumo() {
    }

    public Insumo(String nombre, Integer stock) {
        this.nombre = nombre;
        this.stock = stock;
    }

    public Insumo(ObjectId _id, String nombre, Integer stock) {
        this._id = _id;
        this.nombre = nombre;
        this.stock = stock;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    
    
}
