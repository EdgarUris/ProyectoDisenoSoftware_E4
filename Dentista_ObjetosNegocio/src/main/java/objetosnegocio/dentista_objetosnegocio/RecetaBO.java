/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import DAOs.CitaDAO;
import DAOs.ICitaDAO;
import DAOs.IRecetaDAO;
import DAOs.RecetaDAO;
import entidades.Medicamento;
import entidades.Receta;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import objetosnegocio.Excepciones.BOException;
import org.bson.types.ObjectId;


public class RecetaBO implements IRecetaBO {
    
    private IRecetaDAO rDAO;
    private ICitaDAO cDAO;
    
    public RecetaBO(){
        rDAO = new RecetaDAO();
        cDAO = new CitaDAO();
    }

    @Override
    public boolean guardar(ObjectId id_cita, List<Medicamento> medicamentos) throws BOException {
        try{
            if(!cDAO.findByID(id_cita).isPresent()){
                throw new BOException("Cita no encontrada");
            }
            if(medicamentos.isEmpty()){
                throw new BOException("La lista de medicamentos esta vacia");
            }
            Receta r = new Receta();
            r.setId_cita(id_cita);
            r.setMedicamentos(medicamentos);
            return rDAO.create(r);
        }catch(Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println("Error al guardar la receta");
            return false;
        }
    }

    @Override
    public Receta buscarPorIdCita(ObjectId id_cita) throws BOException {
        try{
            Optional<Receta> recetaEncontrada = rDAO.findByCita(id_cita);
            if(recetaEncontrada.isEmpty()){
                throw new BOException("La receta de la cita no existe");
            }
            return recetaEncontrada.get();
        }catch(Exception e){
            System.out.println("Error al encontrar la receta");
            return null;
        }
    }
    
}
