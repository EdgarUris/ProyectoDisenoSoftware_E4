/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_negocios;

import java.time.LocalDate;

/**
 *
 * @author Roberto
 */
public class agendaDTO {
    private LocalDate fecha;
    private int citasDisponibles;
    private int citasOcupadas;

    public agendaDTO() {
    }

    public agendaDTO(LocalDate fecha, int citasDisponibles, int citasOcupadas) {
        this.fecha = fecha;
        this.citasDisponibles = citasDisponibles;
        this.citasOcupadas = citasOcupadas;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getCitasDisponibles() {
        return citasDisponibles;
    }

    public int getCitasOcupadas() {
        return citasOcupadas;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setCitasDisponibles(int citasDisponibles) {
        this.citasDisponibles = citasDisponibles;
    }

    public void setCitasOcupadas(int citasOcupadas) {
        this.citasOcupadas = citasOcupadas;
    }
    
    
    
    
}
