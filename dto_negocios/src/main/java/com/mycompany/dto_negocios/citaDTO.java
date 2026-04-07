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
   

    private Long pacienteId;
    private Long dentistaId;
    private Long tratamientoId;

    public citaDTO() {
    }

    public citaDTO(Long id, LocalDateTime fechaHora, Long pacienteId, Long dentistaId, Long tratamientoId) {
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

    public Long getPacienteId() {
        return pacienteId;
    }

    public Long getDentistaId() {
        return dentistaId;
    }

    public Long getTratamientoId() {
        return tratamientoId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public void setDentistaId(Long dentistaId) {
        this.dentistaId = dentistaId;
    }

    public void setTratamientoId(Long tratamientoId) {
        this.tratamientoId = tratamientoId;
    }
    
    
    
    
    
}
