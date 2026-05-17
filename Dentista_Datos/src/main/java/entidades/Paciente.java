/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;
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
    private String numero_telefono;
    private LocalDate fecha_nacimiento;
    private RFC
    public Paciente() {
    }

    public Paciente(String nombre, String correo, String numero_telefono, LocalDate fecha_nacimiento) {
        this.nombre = nombre;
        this.correo = correo;
        this.numero_telefono = numero_telefono;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Paciente(String nombre, String folio, String correo, String numero_telefono, LocalDate fecha_nacimiento) {
        this.nombre = nombre;
        this.folio = folio;
        this.correo = correo;
        this.numero_telefono = numero_telefono;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Paciente(ObjectId _id, String nombre, String folio, String correo, String numero_telefono, LocalDate fecha_nacimiento) {
        this._id = _id;
        this.nombre = nombre;
        this.folio = folio;
        this.correo = correo;
        this.numero_telefono = numero_telefono;
        this.fecha_nacimiento = fecha_nacimiento;
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
        return numero_telefono;
    }

    public void setNumeroTelefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
