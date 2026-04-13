/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import com.mycompany.dto_negocios.dentistaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manue
 */
public class DentistaDisponibles {
    List<dentistaDTO> dentista = new ArrayList<>();
    public void ListaDentistas(){
    dentista.add(new dentistaDTO(1L,"Coraje","Ortodoncia"));
    dentista.add(new dentistaDTO(2L,"Justo","Periodoncia"));
    dentista.add(new dentistaDTO(3L,"Muriel","Endodoncia"));
    }
    public boolean disponibilidadPorId(long id){
        for(dentistaDTO dt : dentista){
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