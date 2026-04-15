/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import com.mycompany.dto_negocios.citaDTO;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 *
 * @author EdgarUris
 */
public class validarCita implements IValidadorCita{

    @Override
    public boolean validarCita(citaDTO cita) {
        return cita.getFechaHora().equals(LocalDateTime.of(2026, Month.APRIL, 16,15,30)) &&
                cita.getDentistaId().equals(Long.valueOf(10)) &&
                cita.getPacienteId().equals(Long.valueOf(10)) &&
                cita.getTratamientoId().equals(Long.valueOf(10));
    }
    
}
