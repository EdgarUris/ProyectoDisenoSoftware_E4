/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package negociosservicioinventario;

import Entidades.Reporte;
import Entidades.Instrumento;
import Entidades.FachadaInventario;

/**
 *
 * @author manue
 */
public class NegociosServicioInventario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FachadaInventario sistema = new FachadaInventario();

        Instrumento i1 = new Instrumento(
                1,
                "Pinza",
                "Dental",
                5,
                "Disponible",
                "Limpio"
        );

        Instrumento i2 = new Instrumento(
                2,
                "Espejo",
                "Dental",
                0,
                "En uso",
                "Sucio"
        );

        sistema.agregarInstrumento(i1);

        sistema.agregarInstrumento(i2);

        sistema.consultarInventario();

        Reporte r = sistema.generarReporte();

        System.out.println(
                r.obtenerContenido()
        );
    }
}
