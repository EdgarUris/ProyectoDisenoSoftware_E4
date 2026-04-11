/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_negocios;

/**
 *
 * @author Roberto
 */
public class tratamientoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double costo;
    private Integer duracionMinutos;

    public tratamientoDTO() {
    }

    public tratamientoDTO(Long id, String nombre, String descripcion, Double costo, Integer duracionMinutos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.duracionMinutos = duracionMinutos;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getCosto() {
        return costo;
    }

    public Integer getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public void setDuracionMinutos(Integer duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }
    
    
    
    
}
