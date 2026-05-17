/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import Exception.DAOException;
import entidades.Insumo;
import java.util.Optional;

/**
 *
 * @author EdgarUris
 */
public interface iInventarioDAO extends IGenericoDAO<Insumo> {
    Optional<Insumo> findByName(String name) throws DAOException;
}
