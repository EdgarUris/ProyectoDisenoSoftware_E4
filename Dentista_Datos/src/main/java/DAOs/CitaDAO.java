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
import dominio.dentista_dominio.Cita;
import dominio.dentista_dominio.Dentista;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * 
 * @author EdgarUris
 */

public class CitaDAO implements ICitaDAO {
    
    private final MongoCollection<Cita> col;

    public CitaDAO() {
        this.col = MongoClientProvider.INSTANCE.getCollection("citas", Cita.class);
    }

    @Override
    public boolean create(Cita entity) throws DAOException {
        try {
            if (entity.getId()== null) {
                entity.setId(new ObjectId());
                col.insertOne(entity);
                return true;
            }
        } catch (MongoException e) {
            throw new DAOException("Error creando cita", e);
        }
        return false;
    }

    @Override
    public List<Cita> findAll(int limit) throws DAOException {
        throw new UnsupportedOperationException("Metodo no soportado");
    }

    @Override
    public boolean update(ObjectId id, Cita entityNew) throws DAOException {
        try {
            var result = col.replaceOne(Filters.eq("_id", id), entityNew);
            if (result.getMatchedCount() == 0) {
                throw new EntityNotFoundException("Cita no encontrada: " + id);
            }
            return result.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw new DAOException("Error actualizando cita", e);
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
                throw new EntityNotFoundException("Cita no encontrada: " + id);
            return true;
        } catch (MongoException e) {
            throw new DAOException("Error eliminando cita", e);
        } catch (EntityNotFoundException ex) {
            System.getLogger(PacienteDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }
    
    @Override
    public List<Cita> findCitasWithDentistaAndDate(Dentista d, LocalDate fecha) throws DAOException {
        try {
            LocalDateTime inicioDia = fecha.atStartOfDay(); //fecha a las 00:00:01
            LocalDateTime finDia = fecha.atTime(LocalTime.MAX); //fecha a las 23:59:59

            List<Document> pipeline = List.of(
                new Document("$match", new Document("dentista_id", d.getId())
                    .append("fecha", new Document("$gte", inicioDia).append("$lte", finDia)))
            );

            return col.aggregate(pipeline, Cita.class).into(new ArrayList<>());
        } catch (Exception e) {
            throw new DAOException("Error al obtener las citas", e);
        }
    }

    @Override
    public Optional<Cita> findCitaWithDateTime(LocalDateTime fechaHora) throws DAOException {
        try{
            return Optional.ofNullable(col.find(Filters.eq("fecha", fechaHora)).first());
        }
        catch(Exception e){
            throw new DAOException("Error al obtener las citas en una fecha", e);
        }
    }

    @Override
    public Optional<Cita> findByID(ObjectId id) throws DAOException {
        throw new UnsupportedOperationException("Metodo no soportado");
    }

    @Override
    public boolean update(Cita entity) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
