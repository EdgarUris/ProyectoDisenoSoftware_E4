package Entidades;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author manue
 */
public class Instrumento {

    private int idInstrumento;
    private String nombre;
    private String tipo;
    private int stock;
    private String estado;
    private String proceso;

    public Instrumento(int idInstrumento,
            String nombre,
            String tipo,
            int stock,
            String estado,
            String proceso) {

        this.idInstrumento = idInstrumento;
        this.nombre = nombre;
        this.tipo = tipo;
        this.stock = stock;
        this.estado = estado;
        this.proceso = proceso;
    }

    public void actualizarEstado(String estado) {

        this.estado = estado;
    }

    public void actualizarProceso(String proceso) {

        this.proceso = proceso;
    }

    public int getStock() {
        return stock;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }

}
