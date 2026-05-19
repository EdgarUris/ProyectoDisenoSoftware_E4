/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import Exception.DAOException;
import entidades.Receta;
import java.util.Optional;
import org.bson.types.ObjectId;

/**
 *
 * @author HP
 */
public interface IRecetaDAO extends IGenericoDAO<Receta>{
    Optional<Receta> findByCita(ObjectId id_cita) throws DAOException;
}
