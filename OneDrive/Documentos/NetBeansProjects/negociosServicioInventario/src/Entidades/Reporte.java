package Entidades;


import java.time.LocalDate;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author manue
 */
public class Reporte {
    private int idReporte;

    private LocalDate fecha;

    private String tipoReporte;

    private String contenido;


    public Reporte(int idReporte,
                   String tipoReporte,
                   String contenido){

        this.idReporte=idReporte;

        this.fecha=
                LocalDate.now();

        this.tipoReporte=
                tipoReporte;

        this.contenido=
                contenido;
    }

    public void generarPdf(){

        System.out.println(
                "PDF generado"
        );

    }

    public String obtenerContenido(){

        return contenido;

    }
}
