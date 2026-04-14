/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import com.mycompany.dto_negocios.pacienteDTO;

/**
 *
 * @author EdgarUris
 */
public class ValidadorUsuario implements IValidadorGenerico<pacienteDTO>{

    /**
     * @param paciente paciente a validar
     * @return si el paciente se llama roberto y su id es 10
     */
    @Override
    public boolean validar(pacienteDTO paciente) {
        return paciente.getFolio().equals("ROCO1") && paciente.getNombre().equals("Roberto Cortez");
    }
    
}
