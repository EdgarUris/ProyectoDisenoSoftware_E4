/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import Exception.DAOException;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

/**
 *
 * @author EdgarUris
 * Interfaz generica para los DAOs
 */
public interface IGenericoDAO<T> {
    boolean create(T entity) throws DAOException;
    Optional<T> findByID(ObjectId id) throws DAOException;
    List<T> findAll(int limit) throws DAOException;
    boolean update(T entity) throws DAOException;
    boolean deleteById(ObjectId id) throws DAOException;
}
