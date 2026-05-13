/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDateTime;
import java.time.LocalDate;
import org.bson.types.ObjectId;

/**
 *
 * @author jeniferfl
 */
public class Cita {
    
    private ObjectId _id;
    private Paciente paciente;
    private Dentista dentista;
    private LocalDateTime fecha;
    private String motivo;
    private Tratamiento tratamiento;

    public Cita() {
    }

    public Cita(Paciente paciente, Dentista dentista, LocalDateTime fecha, String motivo, Tratamiento tratamiento) {
        this.paciente = paciente;
        this.dentista = dentista;
        this.fecha = fecha;
        this.motivo = motivo;
        this.tratamiento = tratamiento;
    }

    public Cita(ObjectId _id, Paciente paciente, Dentista dentista, LocalDateTime fecha, String motivo, Tratamiento tratamiento) {
        this._id = _id;
        this.paciente = paciente;
        this.dentista = dentista;
        this.fecha = fecha;
        this.motivo = motivo;
        this.tratamiento = tratamiento;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }
    
    public LocalDate getLocalDate(){
        return fecha.toLocalDate();
    }
}
