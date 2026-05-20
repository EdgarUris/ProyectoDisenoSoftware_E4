/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import entidades.Cita;
import entidades.Dentista;
import entidades.Tratamiento;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import objetosnegocio.Excepciones.BOException;

/**
 *
 * @author EdgarUris
 */
public interface ICitaService {
    boolean agendar(String folioPaciente, String folioDentista, LocalDateTime fechaHora, String motivo, String estado, Tratamiento tratamiento) throws BOException;
    boolean actualizar(String estado, LocalDateTime fechaHora) throws BOException;
    boolean cancelar(String folioDentista, LocalDateTime fecha) throws BOException;
    List<Cita> obtenerPorDentistaYFecha(String folioDentista, LocalDate dia) throws BOException;
    List<Cita> obtenerPorFechaHora(LocalDateTime fechaHora) throws BOException;
    public boolean existeCitaConMedicoEnHora(Dentista d, LocalDate dia, String hora) throws BOException;
    public Cita obtenerPorDentistaYFechaHora(String folioDentista, LocalDateTime fecha) throws BOException;
}
