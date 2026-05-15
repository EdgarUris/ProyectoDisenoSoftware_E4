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
import dominio.dentista_dominio.Insumo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;


public class InventarioDAO implements iInventarioDAO {
    
    private MongoCollection<Insumo> col;
    
    public InventarioDAO(){
        this.col = MongoClientProvider.INSTANCE.getCollection("insumos", Insumo.class);
    }

    @Override
    public Optional<Insumo> findByName(String name) throws DAOException {
        try {
            return Optional.ofNullable(col.find(Filters.eq("nombre", name)).first());
        } catch (MongoException e) {
            throw new DAOException("Error consultando insumo por nombre", e);
        }
    }

    @Override
    public boolean create(Insumo entity) throws DAOException {
        try {
            if (entity.getId()== null) {
                entity.setId(new ObjectId());
                col.insertOne(entity);
                return true;
            }
        } catch (MongoException e) {
            throw new DAOException("Error insertando paciente", e);
        }
        return false;
    }

    @Override
    public List<Insumo> findAll(int limit) throws DAOException {
        try {
            return col.find().limit(limit).into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DAOException("Error consultando todos los pacientes", e);
        }
    }

    @Override
    public boolean update(Insumo entity) throws DAOException {
        try {
            var result = col.replaceOne(Filters.eq("_id", entity.getId()), entity);
                    
            if (result.getMatchedCount() == 0) {
                throw new EntityNotFoundException("Insumo no encontrado: " + entity.getId());
            }
            return result.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw new DAOException("Error actualizando insumo", e);
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
                throw new EntityNotFoundException("Insumo no encontrado: " + id);
            return true;
        } catch (MongoException e) {
            throw new DAOException("Error eliminando insumo", e);
        } catch (EntityNotFoundException ex) {
            System.getLogger(PacienteDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }

    @Override
    public Optional<Insumo> findByID(ObjectId id) throws DAOException {
        throw new UnsupportedOperationException("Metodo no soportado"); 
    }
    
}
