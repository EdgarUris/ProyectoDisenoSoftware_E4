/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import entidades.Medicamento;
import entidades.Receta;
import java.util.List;
import objetosnegocio.Excepciones.BOException;
import org.bson.types.ObjectId;

/**
 *
 * @author HP
 */
public interface IRecetaBO {
    boolean guardar(ObjectId id_cita, List<Medicamento> medicamentos) throws BOException;
    Receta buscarPorIdCita(ObjectId id_cita) throws BOException;
}
