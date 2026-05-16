/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import dominio.dentista_dominio.Dentista;
import java.util.List;
import objetosnegocio.Excepciones.BOException;


public class DentistaService implements IDentistaService {

    @Override
    public boolean registrar(String nombre, String especialidad) throws BOException {
        throw new BOException("Falta implementar el metodo");
    }

    @Override
    public boolean editar(String nombre, String especialidad) throws BOException {
        throw new BOException("Falta implementar el metodo");
    }

    @Override
    public boolean eliminar(String folio) throws BOException {
        throw new BOException("Falta implementar el metodo");
    }

    @Override
    public List<Dentista> listar(int limite) throws BOException {
        throw new BOException("Falta implementar el metodo");
    }
    
}
