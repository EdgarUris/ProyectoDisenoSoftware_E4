/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_negocios;

import java.time.LocalDateTime;

/**
 *
 * @author Roberto
 */
public class citaDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private pacienteDTO pacienteId;
    private dentistaDTO dentistaId;
    private tratamientoDTO tratamientoId;
    private String estado;
    private Double costo;
    
    
    
    public citaDTO() {
    }

    public citaDTO(Long id, LocalDateTime fechaHora, pacienteDTO pacienteId, dentistaDTO dentistaId, tratamientoDTO tratamientoId) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.pacienteId = pacienteId;
        this.dentistaId = dentistaId;
        this.tratamientoId = tratamientoId;
        
    }

    

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

   

    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public pacienteDTO getPacienteId() {
        return pacienteId;
    }

    public dentistaDTO getDentistaId() {
        return dentistaId;
    }

    public tratamientoDTO getTratamientoId() {
        return tratamientoId;
    }

    public void setPacienteId(pacienteDTO pacienteId) {
        this.pacienteId = pacienteId;
    }

    public void setDentistaId(dentistaDTO dentistaId) {
        this.dentistaId = dentistaId;
    }

    public void setTratamientoId(tratamientoDTO tratamientoId) {
        this.tratamientoId = tratamientoId;
    }

   
    
    
    
    
    
}
