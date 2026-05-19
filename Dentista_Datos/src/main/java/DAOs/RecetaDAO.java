/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Exception.DAOException;
import Exception.EntityNotFoundException;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import config.MongoClientProvider;
import entidades.Receta;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
/*
 * 
 * @author EdgarUris
 */

public class RecetaDAO implements IRecetaDAO {

    MongoCollection<Receta> col;

    public RecetaDAO(){
        this.col = MongoClientProvider.INSTANCE.getCollection("recetas", Receta.class);
    }
    
    @Override
    public Optional<Receta> findByCita(ObjectId id_cita) throws DAOException {
        try{
            return Optional.ofNullable(col.find(Filters.eq("id_cita", id_cita)).first());
        } catch (MongoException e) {
            throw new DAOException("Error consultando receta por ID de cita", e);
        }
    }

    @Override
    public boolean create(Receta entity) throws DAOException {
        try {
            if (entity.getId()== null) {
                entity.setId(new ObjectId());
                col.insertOne(entity);
                return true;
            }
        } catch (MongoException e) {
            System.out.println("Error guardando receta");
            return false;
        }
        return false;
    }

    @Override
    public Optional<Receta> findByID(ObjectId id) throws DAOException {
        try{
            return Optional.ofNullable(col.find(Filters.eq("_id", id)).first());
        } catch (MongoException e) {
            throw new DAOException("Error consultando receta por ID", e);
        }
    }

    @Override
    public List<Receta> findAll(int limit) throws DAOException {
        try {
            return col.find().limit(limit).into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DAOException("Error consultando las recetas", e);
        }
    }

    //tomas receta original, cambias lo que debes menos el ID, la vuelves a insertar
    @Override
    public boolean update(Receta entity) throws DAOException {
        try {
            var result = col.replaceOne(Filters.eq("_id", entity.getId()), entity);
                    
            if (result.getMatchedCount() == 0) {
                throw new EntityNotFoundException("Receta no encontrada: " + entity.getId());
            }
            return result.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw new DAOException("Error actualizando receta", e);
        } catch (EntityNotFoundException ex) {
            System.getLogger(PacienteDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteById(ObjectId id) throws DAOException {
        try {
            var result = col.deleteOne(Filters.eq("_id", id));
            if (result.getDeletedCount() == 0)
                throw new EntityNotFoundException("Receta no encontrada: " + id);
            return true;
        } catch (MongoException e) {
            throw new DAOException("Error eliminando receta", e);
        } catch (EntityNotFoundException ex) {
            System.getLogger(PacienteDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }
    
}
