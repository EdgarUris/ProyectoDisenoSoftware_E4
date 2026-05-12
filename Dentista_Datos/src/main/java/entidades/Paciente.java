/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import org.bson.types.ObjectId;

/**
 *
 * @author jeniferfl
 * 
 */
public class Paciente {
      
    private ObjectId _id;
    private String nombre;
    private String folio;
    private String correo;
    private String numeroTelefono;

    public Paciente() {
    }

    public Paciente(String nombre, String folio, String correo, String numeroTelefono) {
        this.nombre = nombre;
        this.folio = folio;
        this.correo = correo;
        this.numeroTelefono = numeroTelefono;
    }

    public Paciente(ObjectId _id, String nombre, String folio, String correo, String numeroTelefono) {
        this._id = _id;
        this.nombre = nombre;
        this.folio = folio;
        this.correo = correo;
        this.numeroTelefono = numeroTelefono;
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

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
}
