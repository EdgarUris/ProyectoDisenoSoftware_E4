/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import DAOs.IPacienteDAO;
import Exception.DAOException;
import entidades.Paciente;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import objetosnegocio.Excepciones.BOException;
import objetosnegocio.regex.Regex;


public class PacienteService implements IPacienteService {

    private final IPacienteDAO pDAO;
    private Regex rgx = new Regex();
    
    public PacienteService(IPacienteDAO dao){
        this.pDAO = dao;
    }

    @Override
    public boolean registrar(String nombre, String correo, String telefono, LocalDate fecha_nac) throws BOException {
        try {
            if(nombre.trim().isEmpty()){
                throw new BOException("El nombre del paciente está vacio");
            }
            if(!rgx.validarCorreo(correo) || correo.trim().isEmpty()){
                throw new BOException("Correo de paciente invalido");
            }
            if(pDAO.findByCorreo(correo).get() != null){
                throw new BOException("El correo ya está registrado");
            }
            if(!rgx.validarTelefono(telefono.trim()) || telefono.trim().isEmpty()){
                throw new BOException("El numero de paciente invalido");
            }
            if(pDAO.findByTelefono(telefono).get() != null){
                throw new BOException("El numero de telefono ya esta registrado");
            }
            
            Period edad = Period.between(fecha_nac, LocalDate.now());
            if(edad.getYears() < 10) {
                throw new BOException("El paciente debe tener al menos 10 años de edad");
            }
            try {
                return pDAO.create(new Paciente(nombre, correo, telefono, fecha_nac));
            } catch (DAOException ex) {
                return false;
            }
        } catch (DAOException ex) {
            System.getLogger(PacienteService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }

    @Override
    public boolean actualizar(String folio, String nombre, String correo, String telefono) throws BOException {
        try {
            Optional<Paciente> p = pDAO.findByFolio(folio);
            if(p.get() != null){
                throw new BOException("Paciente no encontrado con el folio: "+folio);
            }
            if(nombre.trim().isEmpty()){
                throw new BOException("El nombre del paciente está vacio");
            }
            if(!rgx.validarCorreo(correo) || correo.trim().isEmpty()){
                throw new BOException("Correo de paciente invalido");
            }
            if(!rgx.validarTelefono(telefono.trim()) || telefono.trim().isEmpty()){
                throw new BOException("El numero de paciente invalido");
            }
            if(pDAO.findByCorreo(correo).get() != null){
                throw new BOException("El correo ya esta registrado");
            }
            if(pDAO.findByTelefono(telefono).get() != null){
                throw new BOException("El telefono ya está registrado");
            }
            
            Paciente cambiado = p.get();
            cambiado.setNombre(nombre);
            cambiado.setNumeroTelefono(telefono);
            cambiado.setCorreo(correo);
            
            return pDAO.update(cambiado);
            
        } catch (DAOException ex) {
            return false;
        }
    }

    @Override
    public boolean eliminar(String folio) throws BOException {
        try {
            Optional<Paciente> p = pDAO.findByFolio(folio);
            Paciente eliminado = p.get();
            return pDAO.deleteById(eliminado.getId());
        } catch (DAOException ex) {
            return false;
        }
    }

    @Override
    public List<Paciente> obtenerTodos(int limite) throws BOException {
        try {
            return pDAO.findAll(limite);
        } catch (DAOException ex) {
            return new ArrayList<>();
        }
    }
    
    
    
}
