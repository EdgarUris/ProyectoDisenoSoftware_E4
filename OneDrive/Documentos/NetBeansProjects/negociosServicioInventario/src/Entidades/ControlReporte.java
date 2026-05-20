package Entidades;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author manue
 */
public class ControlReporte {

    public Reporte generarReporte(
            Inventario inventario) {

        String datos
                = "Instrumentos:" + inventario.getLista().size();

        Reporte reporte = new Reporte(1, "Inventario", datos);

        return reporte;
    }

    public boolean validarFiltros(
            String filtro) {

        return !filtro.isEmpty();

    }

    public Inventario obtenerDatosInventario(
            Inventario inventario) {

        return inventario;

    }

}


