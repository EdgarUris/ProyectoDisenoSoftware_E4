/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import dominio.dentista_dominio.Paciente;
import java.time.LocalDate;
import java.util.List;
import objetosnegocio.Excepciones.BOException;

/**
 *
 * @author EdgarUris
 */
public interface IPacienteService {
    boolean registrar(String nombre, String correo, String telefono, LocalDate fecha_nac) throws BOException;
    boolean actualizar(String folio, String nombre, String correo, String telefono) throws BOException;
    boolean eliminar(String folio) throws BOException;
    List<Paciente> obtenerTodos(int limite) throws BOException;
}
