/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import com.mycompany.dto_negocios.citaDTO;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author EdgarUris
 */
public class ValidadorCita implements IValidadorGenerico<citaDTO>{

    /**
     * valida citas
     * @param cita
     * @return que la cita sea el 16 de abril del 2026 a las 15:30pm
     */
    @Override
    public boolean validar(citaDTO cita) {
        return cita.getPacienteId().equals(Long.valueOf(10)) 
                && cita.getFechaHora().equals(LocalDateTime.of(2026,Month.APRIL, 16,15,30));
    }
    
}
