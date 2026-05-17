/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import Exception.DAOException;
import entidades.Dentista;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author EdgarUris
 */
public interface IDentistaDAO extends IGenericoDAO<Dentista>{
    Optional<Dentista> findByFolio(String folio) throws DAOException;
    List<Dentista> findByEspecialidad(String especialidad) throws DAOException;
}
