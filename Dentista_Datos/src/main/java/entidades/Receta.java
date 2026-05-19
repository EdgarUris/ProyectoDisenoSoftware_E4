/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author HP
 */
public class Receta {
    private ObjectId _id;
    private ObjectId id_cita;
    private List<Medicamento> medicamentos;

    public Receta() {
    }

    public Receta(ObjectId id_cita, List<Medicamento> medicamentos) {
        this.id_cita = id_cita;
        this.medicamentos = medicamentos;
    }

    public Receta(ObjectId _id, ObjectId id_cita, List<Medicamento> medicamentos) {
        this._id = _id;
        this.id_cita = id_cita;
        this.medicamentos = medicamentos;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public ObjectId getId_cita() {
        return id_cita;
    }

    public void setId_cita(ObjectId id_cita) {
        this.id_cita = id_cita;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }
    
    
}
