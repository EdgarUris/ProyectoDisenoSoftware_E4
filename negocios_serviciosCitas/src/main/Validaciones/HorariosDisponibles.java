/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import com.mycompany.dto_negocios.citaDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manue
 */
public class HorariosDisponibles {
    List<citaDTO> cita = new ArrayList<>();
    public void Horarios(){
        cita.add(new citaDTO(1L,LocalDateTime.now(),1L,1L,1L));
        cita.add(new citaDTO(1L,LocalDateTime.now(),2L,2L,2L));
        cita.add(new citaDTO(1L,LocalDateTime.now(),2L,2L,2L));
    }
     public boolean disponibilidadPorId(long id){
        for(citaDTO dt : cita){
            if(dt.getId()==id){
                System.out.println("Disponible");
            }
            else{
                System.out.println("No Disponible");
            }
        }
        return true;
    }
}
