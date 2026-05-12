/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_negocios;

/**
 *
 * @author Roberto
 */
public class pacienteDTO {
    private Long id;
    private String nombre;
    private String folio;
//    private String telefono;
    private String correo;
    private Integer edad;

    public pacienteDTO() {
    }

    public pacienteDTO(Long id, String nombre, String correo, String folio, Integer edad) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.folio = folio;
        this.edad = edad;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
    
    
    
    
    
}
