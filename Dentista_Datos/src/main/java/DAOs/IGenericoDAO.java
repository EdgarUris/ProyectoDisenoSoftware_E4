/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

/**
 *
 * @author EdgarUris
 * Interfaz generica para los DAOs
 */
public interface IGenericoDAO<T> {
    ObjectId create(T entity);
    Optional<T> findByID(ObjectId id);
    List<T> findAll();
    boolean update(T entity);
    boolean deleteById(ObjectId id);
}
