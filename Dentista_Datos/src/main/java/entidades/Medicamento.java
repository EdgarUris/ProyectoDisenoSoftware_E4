/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author HP
 */
public class Medicamento {
    private String nombre_medicamento; 
    private String dosis;
    private String frecuencia_ingerir;
    private String duracion;

    public Medicamento() {
    }

    public Medicamento(String nombre_medicamento, String dosis, String frecuencia_ingerir, String duracion) {
        this.nombre_medicamento = nombre_medicamento;
        this.dosis = dosis;
        this.frecuencia_ingerir = frecuencia_ingerir;
        this.duracion = duracion;
    }

    public String getNombre_medicamento() {
        return nombre_medicamento;
    }

    public void setNombre_medicamento(String nombre_medicamento) {
        this.nombre_medicamento = nombre_medicamento;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getFrecuencia_ingerir() {
        return frecuencia_ingerir;
    }

    public void setFrecuencia_ingerir(String frecuencia_ingerir) {
        this.frecuencia_ingerir = frecuencia_ingerir;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    
    
}
