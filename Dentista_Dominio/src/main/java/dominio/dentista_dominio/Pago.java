/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.dentista_dominio;

import java.time.Instant;
import org.bson.types.ObjectId;

/**
 *
 * @author EdgarUris
 */
public class Pago {
    
    private ObjectId _id;
    private ObjectId id_paciente;
    private Instant hecho_en;
    private Double monto;
    private String metodo_pago;
    private String estado_pago;
    private String referencia;

    public Pago() {
    }

    public Pago(Double monto, String metodo_pago, String estado_pago, String referencia) {
        this.monto = monto;
        this.metodo_pago = metodo_pago;
        this.estado_pago = estado_pago;
        this.referencia = referencia;
    }

    public Pago(ObjectId id_paciente, Double monto, String metodo_pago, String estado_pago, String referencia) {
        this.id_paciente = id_paciente;
        this.monto = monto;
        this.metodo_pago = metodo_pago;
        this.estado_pago = estado_pago;
        this.referencia = referencia;
    }
    
    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public Instant getHecho_en() {
        return hecho_en;
    }

    public void setHecho_en(Instant hecho_en) {
        this.hecho_en = hecho_en;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public String getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(String estado_pago) {
        this.estado_pago = estado_pago;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public ObjectId getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(ObjectId id_paciente) {
        this.id_paciente = id_paciente;
    }
}
