/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import entidades.Tratamiento;
import java.util.List;
import objetosnegocio.Excepciones.BOException;

/**
 *
 * @author HP
 */
public interface ITratamientoBO {
    boolean agregar(String nombre, Double costo) throws BOException;
    List<Tratamiento> listarTodos() throws BOException;
    Tratamiento buscarPorNombre(String nombre) throws BOException;
}
