/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import com.mycompany.dto_negocios.tratamientoDTO;

/**
 *
 * @author HP
 */
public class ValidadorTratamiento implements IValidadorGenerico<tratamientoDTO>{

    /**
     * valida el tratamiento
     * @param tratamiento
     * @return si el tratamiento es colocación de brackets
     */
    @Override
    public boolean validar(tratamientoDTO tratamiento) {
        return "Colocacion de Brackets".equals(tratamiento.getNombre());
    }
    
}
