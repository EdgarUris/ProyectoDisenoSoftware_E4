/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import entidades.Dentista;
import java.util.List;
import objetosnegocio.Excepciones.BOException;

/**
 *
 * @author EdgarUris
 */
public interface IDentistaService {
    public boolean registrar(String nombre, String especialidad) throws BOException;
    public boolean editar(String folio, String nombre, String especialidad) throws BOException;
    public boolean eliminar(String folio) throws BOException;
    public List<Dentista> listar(int limite) throws BOException;
    public List<Dentista> listarPorEspecialidad(String especialidad) throws BOException;
}
