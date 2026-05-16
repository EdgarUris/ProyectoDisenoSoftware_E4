/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jeniferfl
 */
public class Dentista {
    
    private ObjectId _id;
    private String nombre;
    private String especialidad;
    private String folio;

    public Dentista() {
    }

    public Dentista(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public Dentista(ObjectId _id, String nombre, String especialidad) {
        this._id = _id;
        this.nombre = nombre;
        this.especialidad = especialidad;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
}
