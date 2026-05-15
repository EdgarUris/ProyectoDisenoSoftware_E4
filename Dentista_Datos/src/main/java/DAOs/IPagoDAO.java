/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import Exception.DAOException;
import dominio.dentista_dominio.Pago;
import java.util.List;
/**
 *
 * @author EdgarUris
 */
public interface IPagoDAO extends IGenericoDAO<Pago> {
    List<Pago> findByPacient(String folio) throws DAOException;
}
