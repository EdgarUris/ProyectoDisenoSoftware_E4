/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import Exception.DAOException;
import dominio.dentista_dominio.Paciente;
import java.util.Optional;

/**
 *
 * @author EdgarUris
 */
public interface IPacienteDAO extends IGenericoDAO<Paciente> {
    Optional<Paciente> findByFolio(String folio) throws DAOException;
}
