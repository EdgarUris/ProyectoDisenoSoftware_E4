package Entidades;


import Entidades.ControlReporte;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author manue
 */
public class FachadaInventario {

        private Inventario inventario;

    private ControlReporte control;


    public FachadaInventario(){

        inventario =
                new Inventario(1);

        control =
                new ControlReporte();

    }


    public void agregarInstrumento(
            Instrumento i){

        inventario
                .agregarInstrumento(i);

    }


    public void consultarInventario(){

        inventario
                .consultarExistencias();

    }


    public Reporte generarReporte(){

        return control
                .generarReporte(
                        inventario
                );

    }


    public int totalEnUso(){

        return inventario
                .totalEnUso();

    }
}
