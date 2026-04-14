/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import com.mycompany.dto_negocios.pacienteDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manue
 * @Deprecated
 */
public class Pacientes {
    List<pacienteDTO> paciente = new ArrayList<>();
    public void validadPacientes(){
    //paciente.add(new pacienteDTO(1L,"ED","ED_1@hotmail.com",12));
//    paciente.add(new pacienteDTO(1L,"EDD","EDD_2@hotmail.com",17));
//    paciente.add(new pacienteDTO(1L,"EDDY","EDDY_1@hotmail.com",15));
    
}
     public boolean disponibilidadPorId(long id){
        for(pacienteDTO dt : paciente){
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
