/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import objetosnegocio.Excepciones.BOException;

/**
 *
 * @author EdgarUris
 */
public interface IDentistaService {
    public boolean registrar(String nombre, String especialidad) throws BOException;
    
}
