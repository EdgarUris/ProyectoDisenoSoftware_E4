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
import dominio.dentista_dominio.Paciente;
import java.util.List;
import java.util.Optional;
import config.MongoClientProvider;
import java.util.ArrayList;
import org.bson.types.ObjectId;


public class PacienteDAO implements IPacienteDAO {
    
    private final MongoCollection<Paciente> col;

    public PacienteDAO() {
        this.col = MongoClientProvider.INSTANCE.getCollection("pacientes", Paciente.class);
    }

    @Override
    public boolean create(Paciente entity) throws DAOException {
        try {
            if (entity.getId()== null) {
                entity.setId(new ObjectId());
                String folio = generarFolio(entity);
                
                if(findByFolio(folio) != null){
                    folio += String.valueOf((int) (Math.random() * 10));
                }
                
                entity.setFolio(folio);
                col.insertOne(entity);
                return true;
            }
        } catch (MongoException e) {
            throw new DAOException("Error insertando paciente", e);
        }
        return false;
    }

    @Override
    public Optional<Paciente> findByID(ObjectId id) throws DAOException {
        try {
            return Optional.ofNullable(col.find(Filters.eq("_id", id)).first());
        } catch (MongoException e) {
            throw new DAOException("Error consultando paciente por ID", e);
        }
    }
    
    @Override
    public List<Paciente> findAll(int limit) throws DAOException {
        try {
            return col.find().limit(limit).into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DAOException("Error consultando todos los pacientes", e);
        }
    }
    
    @Override
    public boolean update(Paciente entity) throws DAOException {
        try {
            var result = col.replaceOne(Filters.eq("_id", entity.getId()), entity);
                    
            if (result.getMatchedCount() == 0) {
                throw new EntityNotFoundException("Paciente no encontrado: " + entity.getId());
            }
            return result.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw new DAOException("Error actualizando paciente", e);
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
                throw new EntityNotFoundException("Paciente no encontrado: " + id);
            return true;
        } catch (MongoException e) {
            throw new DAOException("Error eliminando paciente", e);
        } catch (EntityNotFoundException ex) {
            System.getLogger(PacienteDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }

    @Override
    public Optional<Paciente> findByFolio(String folio) throws DAOException {
        try {
            return Optional.ofNullable(col.find(Filters.eq("folio", folio)).first());
        } catch (MongoException e) {
            throw new DAOException("Error consultando paciente por folio", e);
        }
    }

    private String generarFolio(Paciente p) {
        StringBuilder folio = new StringBuilder();
        
        //separar nombre en partes
        String[] partes = p.getNombre().trim().split("\\s+");
        for (String parte : partes) {
            folio.append(parte.substring(0,1));
        }
        
        //con el numero de telefono hacer el numerito del final
        int suma = 0;
        String telefono = p.getNumeroTelefono().replaceAll("\\D", "");
    
        for (char c : telefono.toCharArray()) {
            int num = Character.getNumericValue(c);
            if(num % 2 == 0){ 
                suma += num*3;
            }
            else{
                suma += num*7;
            }
        }
        folio.append(suma * 5);
    
        return folio.toString().toUpperCase();
    }
}
