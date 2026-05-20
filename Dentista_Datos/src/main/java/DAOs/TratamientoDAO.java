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
import entidades.Tratamiento;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;


public class TratamientoDAO implements ITratamientoDAO {
    
    MongoCollection<Tratamiento> col;

    public TratamientoDAO(){
        this.col = MongoClientProvider.INSTANCE.getCollection("tratamientos", Tratamiento.class);
    }

    @Override
    public Optional<Tratamiento> findByNombre(String nombre) throws DAOException {
        try{
            return Optional.ofNullable(col.find(Filters.eq("nombre", nombre)).first());
        } catch (MongoException e) {
            throw new DAOException("Error consultando tratamiento por nombre", e);
        }
    }

    @Override
    public boolean create(Tratamiento entity) throws DAOException {
        try {
            if (entity.getId()== null) {
                entity.setId(new ObjectId());
                col.insertOne(entity);
                return true;
            }
        } catch (MongoException e) {
            System.out.println("Error guardando tratamiento");
            return false;
        }
        return false;
    }

    @Override
    public Optional<Tratamiento> findByID(ObjectId id) throws DAOException {
        try{
            return Optional.ofNullable(col.find(Filters.eq("_id", id)).first());
        } catch (MongoException e) {
            throw new DAOException("Error consultando tratamiento por ID", e);
        }
    }

    @Override
    public List<Tratamiento> findAll(int limit) throws DAOException {
        try {
            return col.find().limit(limit).into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DAOException("Error consultando lso tratamientos", e);
        }
    }

    @Override
    public boolean update(Tratamiento entity) throws DAOException {
        try {
            var result = col.replaceOne(Filters.eq("_id", entity.getId()), entity);
                    
            if (result.getMatchedCount() == 0) {
                throw new EntityNotFoundException("Tratamiento no encontrado: " + entity.getId());
            }
            return result.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw new DAOException("Error actualizando tratamiento", e);
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
                throw new EntityNotFoundException("Tratamiento no encontrado: " + id);
            return true;
        } catch (MongoException e) {
            throw new DAOException("Error eliminando tratamiento", e);
        } catch (EntityNotFoundException ex) {
            System.getLogger(PacienteDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }
    
}
