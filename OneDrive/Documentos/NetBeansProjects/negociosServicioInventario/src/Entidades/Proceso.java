package Entidades;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author manue
 */
public class Proceso {

    private int idProceso;
    private String nombreProceso;

    public Proceso(int idProceso,String nombreProceso) {

        this.idProceso = idProceso;
        this.nombreProceso
                = nombreProceso;
    }

    public void esterilizar() {

        System.out.println(
                "Esterilizando..."
        );

    }

    public void disponible() {

        System.out.println(
                "Disponible"
        );

    }

    public void surtir() {

        System.out.println(
                "Surtiendo"
        );

    }

}
