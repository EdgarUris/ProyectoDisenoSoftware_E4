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
    private ObjectId paciente_id;
    private ObjectId dentista_id;
    private LocalDateTime fecha;
    private String motivo;
    private Tratamiento tratamiento;
    private String estado;

    public Cita() {
    }

    public Cita(ObjectId paciente_id, ObjectId dentista_id, LocalDateTime fecha, String motivo, Tratamiento tratamiento, String estado) {
        this.paciente_id = paciente_id;
        this.dentista_id = dentista_id;
        this.fecha = fecha;
        this.motivo = motivo;
        this.tratamiento = tratamiento;
        this.estado = estado;
    }

    public Cita(ObjectId _id, ObjectId paciente_id, ObjectId dentista_id, LocalDateTime fecha, String motivo, Tratamiento tratamiento, String estado) {
        this._id = _id;
        this.paciente_id = paciente_id;
        this.dentista_id = dentista_id;
        this.fecha = fecha;
        this.motivo = motivo;
        this.tratamiento = tratamiento;
        this.estado = estado;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public ObjectId getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(ObjectId paciente_id) {
        this.paciente_id = paciente_id;
    }

    public ObjectId getDentista_id() {
        return dentista_id;
    }

    public void setDentista_id(ObjectId dentista_id) {
        this.dentista_id = dentista_id;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Cita{" + "_id=" + _id + ", paciente_id=" + paciente_id + ", dentista_id=" + dentista_id + ", fecha=" + fecha + ", motivo=" + motivo + ", tratamiento=" + tratamiento + '}';
    }
    
    
}
