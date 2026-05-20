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
import entidades.Cita;
import entidades.Dentista;
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
    
    /**
     * 
     * @param d el dentista
     * @param fecha la fecha de la cita (sin hora)
     * @return las citas de ese dia
     * @throws DAOException si no se puede acceder a la bd
     */
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

    /**
     * 
     * @param fechaHora la fecha y hora de la citas
     * @return la cita, null si no
     * @throws DAOException si no puede acceder a la bd
     */
    @Override
    public List<Cita> findCitaWithDateTime(LocalDateTime fechaHora) throws DAOException {
        try{
            return col.find(Filters.eq("fecha", fechaHora)).into(new ArrayList<>());
        }
        catch(Exception e){
            throw new DAOException("Error al obtener las citas en una fecha", e);
        }
    }

    @Override
    public Optional<Cita> findByID(ObjectId id) throws DAOException {
        try {
            return Optional.ofNullable(col.find(Filters.eq("_id", id)).first());
        } catch (MongoException e) {
            throw new DAOException("Error consultando cita por ID", e);
        }
    }

    @Override
    public boolean update(Cita entity) throws DAOException {
        throw new UnsupportedOperationException("Metodo no soportado, necesitas el id"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

@Override
public Optional<Cita> findCitaWithDentistaAndDateTime(Dentista d, LocalDateTime fecha) throws DAOException {
    try {
        List<Document> pipeline = List.of(
            new Document("$match", new Document("dentista_id", d.getId()).append("fecha", fecha))
        );
        
        Cita cita = col.aggregate(pipeline, Cita.class).first();
        
        return Optional.ofNullable(cita);
        
    } catch (Exception e) {
        throw new DAOException("Error al buscar la cita por dentista y fecha", e);
    }
}
    
}
