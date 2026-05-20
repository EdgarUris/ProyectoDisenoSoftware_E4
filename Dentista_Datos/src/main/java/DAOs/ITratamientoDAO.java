/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import Exception.DAOException;
import entidades.Tratamiento;
import java.util.Optional;

/**
 *
 * @author HP
 */
public interface ITratamientoDAO extends IGenericoDAO<Tratamiento>{
    Optional<Tratamiento> findByNombre(String nombre) throws DAOException;
}
