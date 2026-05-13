/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import Exception.DAOException;
import entidades.Cita;
import entidades.Dentista;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author EdgarUris
 */
public interface ICitaDAO extends IGenericoDAO<Cita>{
    List<Cita> findCitasWithDentistaAndDate(Dentista d, LocalDate fecha) throws DAOException;
    Optional<Cita> findCitaWithDateTime(LocalDateTime fechaHora) throws DAOException;
}
