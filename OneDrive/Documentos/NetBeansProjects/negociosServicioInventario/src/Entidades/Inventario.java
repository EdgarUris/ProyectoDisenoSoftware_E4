package Entidades;


import Entidades.Instrumento;
import java.util.ArrayList;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author manue
 */
public class Inventario {

    private int idInventario;

    private ArrayList<Instrumento> lista
            = new ArrayList<>();

    public Inventario(int idInventario) {

        this.idInventario = idInventario;
    }

    public void agregarInstrumento(
            Instrumento i) {

        lista.add(i);

    }

    public void consultarExistencias() {

        for (Instrumento i : lista) {

            System.out.println(
                    i.getNombre()
            );

        }

    }

    public int totalEnUso() {

        int contador = 0;

        for (Instrumento i : lista) {

            if (i.getEstado()
                    .equalsIgnoreCase(
                            "En uso")) {

                contador++;

            }

        }

        return contador;
    }

    public int totalSinExistencias() {

        int contador = 0;

        for (Instrumento i : lista) {

            if (i.getStock() == 0) {

                contador++;

            }

        }

        return contador;
    }

    public ArrayList<Instrumento>
            getLista() {

        return lista;

    }
}
