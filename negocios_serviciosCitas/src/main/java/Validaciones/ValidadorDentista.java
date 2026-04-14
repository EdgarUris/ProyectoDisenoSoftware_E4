/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import com.mycompany.dto_negocios.dentistaDTO;

/**
 *
 * @author HP
 */
public class ValidadorDentista implements IValidadorGenerico<dentistaDTO>{

    /**
     * permite validar un dentista
     * @param dentista
     * @return si el dentista es manuel y su especialidad es ortodoncia
     */
    @Override
    public boolean validar(dentistaDTO dentista) {
        return "Manuel".equals(dentista.getNombre()) && "Ortodoncia".equals(dentista.getEspecialidad());
    }
    
}
