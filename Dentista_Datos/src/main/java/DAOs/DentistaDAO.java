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
import entidades.Dentista;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * 
 * @author EdgarUris
 */

public class DentistaDAO implements IDentistaDAO {
    
    private final MongoCollection<Dentista> col;

    public DentistaDAO() {
        this.col = MongoClientProvider.INSTANCE.getCollection("dentistas", Dentista.class);
    }

    @Override
    public boolean create(Dentista entity) throws DAOException {
        try {
            if (entity.getId()== null) {
                entity.setId(new ObjectId());
                String folio = generarFolio(entity);
                entity.setFolio(folio);
                col.insertOne(entity);
                return true;
            }
        } catch (MongoException e) {
            throw new DAOException("Error insertando dentista", e);
        }
        return false;
    }

    @Override
    public Optional<Dentista> findByID(ObjectId id) throws DAOException{
        try{
            return Optional.ofNullable(col.find(Filters.eq("_id", id)).first());
        } catch (MongoException e) {
            throw new DAOException("Error consultando dentista por ID", e);
        }
    }
    
    @Override
    public Optional<Dentista> findByFolio(String folio) throws DAOException {
        try {
            return Optional.ofNullable(col.find(Filters.eq("folio", folio)).first());
        } catch (MongoException e) {
            throw new DAOException("Error consultando dentista por folio", e);
        }
    }

    @Override
    public List<Dentista> findAll(int limit) throws DAOException{
        try {
            return col.find().into(new ArrayList<>());
        } catch (Exception e) {
            throw new DAOException("Error consultando todos los dentistas", e);
        }
    }
    
    @Override
    public List<Dentista> findByEspecialidad(String especialidad) throws DAOException {
            List<Document> pipeline = List.of(
                new Document("$match", new Document("especialidad", especialidad)));

            return col.aggregate(pipeline, Dentista.class).into(new ArrayList<>());
    }

    @Override
    public boolean update(Dentista entity) throws DAOException{
        try {
            var result = col.replaceOne(Filters.eq("_id", entity.getId()), entity);
                    
            if (result.getMatchedCount() == 0) {
                throw new EntityNotFoundException("Dentista no encontrado: " + entity.getId());
            }
            return result.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw new DAOException("Error actualizando dentista", e);
        } catch (EntityNotFoundException ex) {
            System.getLogger(PacienteDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteById(ObjectId id) throws DAOException{
        try {
            var result = col.deleteOne(Filters.eq("_id", id));
            if (result.getDeletedCount() == 0)
                throw new EntityNotFoundException("Dentista no encontrado: " + id);
            return true;
        } catch (MongoException e) {
            throw new DAOException("Error eliminando dentista", e);
        } catch (EntityNotFoundException ex) {
            System.getLogger(PacienteDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }
    
    
    
    private String generarFolio(Dentista d) {
        StringBuilder folio = new StringBuilder();
        
        //separar nombre en partes
        String[] partes = d.getNombre().trim().split("\\s+");
        for (String parte : partes) {
            folio.append(parte.substring(0,2));
        }
        
        return folio.toString().toUpperCase();
    }
    
}
