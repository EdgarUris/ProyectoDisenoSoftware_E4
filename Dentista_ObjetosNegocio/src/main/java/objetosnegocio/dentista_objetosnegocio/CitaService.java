/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosnegocio.dentista_objetosnegocio;

import DAOs.DentistaDAO;
import DAOs.ICitaDAO;
import DAOs.IDentistaDAO;
import DAOs.IPacienteDAO;
import DAOs.PacienteDAO;
import Exception.DAOException;
import entidades.Cita;
import entidades.Dentista;
import entidades.Paciente;
import entidades.Tratamiento;
import infraestructuras.ISistemaCorreoElectronico;
import infraestructuras.SistemaCorreoElectronico;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import objetosnegocio.Excepciones.BOException;



public class CitaService implements ICitaService {

    private final ICitaDAO cDAO;
    private final IPacienteDAO pDAO;
    private final IDentistaDAO dDAO;
    private final ISistemaCorreoElectronico correo;
    
    public CitaService(ICitaDAO dao){
        this.cDAO = dao;
        this.pDAO = new PacienteDAO();
        this.dDAO = new DentistaDAO();
        this.correo = new SistemaCorreoElectronico();
    }
    
    @Override
    public boolean agendar(String folioPaciente, String folioDentista, LocalDateTime fechaHora, String motivo, Tratamiento tratamiento) throws BOException {
        try {
            Optional<Paciente> p = pDAO.findByFolio(folioPaciente);
            Optional<Dentista> d = dDAO.findByFolio(folioDentista);
            if(!p.isPresent()){
                throw new BOException("Paciente no encontrado");
            }
            if(!d.isPresent()){
                throw new BOException("Dentista no encontrado");
            }
            if(tratamiento == null){
                
            }
            if(fechaHora.isBefore(LocalDateTime.now())){
                throw new BOException("No puede agendar una cita en el pasado");
            }
            if(motivo.trim().isEmpty()){
                throw new BOException("El motivo no puede estar vacio");
            }
            if(motivo.trim().isEmpty()){
                throw new BOException("El tratamiento no puede estar vacio");
            }
            
            if(cDAO.findCitaWithDentistaAndDateTime(d.get(), fechaHora).isPresent()){
                throw new BOException("El dentista ya tiene una cita agendada en esa hora");
            }
            
            Cita c = new Cita();
            c.setDentista_id(d.get().getId());
            c.setPaciente_id(p.get().getId());
            c.setMotivo(motivo);
            c.setFecha(fechaHora);
            c.setTratamiento(tratamiento);
            
            DateTimeFormatter formato24h = DateTimeFormatter.ofPattern("HH:mm");
            String horaTexto = c.getFecha().toLocalTime().format(formato24h);
            
            Locale es = Locale.forLanguageTag("es-MX");
            DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd 'de' MMMM 'del' yyyy", es);
            String fechaTexto = c.getFecha().toLocalDate().format(fecha);
            
            correo.enviarCorreo(p.get().getCorreo(), "Cita agendada", 
                    "Cita agendada el dia: " + fechaTexto + " a las " + horaTexto + "\n\n Con el/la dentista"
                    + d.get().getNombre() + " Con el motivo de tratamiento: " + c.getTratamiento().getNombre()
            );
            
            return cDAO.create(c);
            
        } catch (DAOException ex) {
            System.getLogger(CitaService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }

    @Override
    public boolean actualizar(String estado, LocalDateTime fechaHora) throws BOException {
        try {
            if(estado.trim().isEmpty()){
                throw new BOException("El estado no puede estar vacio");
            }
            //me estoy volviendo loco
            //mandar correo
            
            return cDAO.update(new Cita());
            
        } catch (DAOException ex) {
            System.getLogger(DentistaService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }

    @Override
    public boolean cancelar(String folioDentista, LocalDateTime fecha) throws BOException {
        return false;
    }

    @Override
    public List<Cita> obtenerPorDentistaYFecha(String folioDentista, LocalDate dia) throws BOException {
        try{
            Optional<Dentista> d = dDAO.findByFolio(folioDentista);
            if(d.get() == null){
                throw new BOException("Dentista no encontrado");
            }
            return cDAO.findCitasWithDentistaAndDate(d.get(), dia);
        } catch (DAOException ex) {
            System.getLogger(CitaService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Cita> obtenerPorFechaHora(LocalDateTime fechaHora) throws BOException {
        try {
            return cDAO.findCitaWithDateTime(fechaHora);
        } catch (DAOException ex) {
            System.getLogger(CitaService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return new ArrayList<>();
    }
    
    @Override
    public boolean existeCitaConMedicoEnHora(Dentista d, LocalDate dia, String hora) throws BOException{
        String[] horaString = hora.split(":");
        int[] horasplit = new int[2];
        for(int i = 0; i < 2; i++){
           horasplit[i] = Integer.parseInt(horaString[i]);
        }
        try {
            List<Cita> citas = cDAO.findCitasWithDentistaAndDate(d, dia);
            for (Cita cita : citas) {
                if(cita.getFecha().getHour() == horasplit[0] && cita.getFecha().getMinute() == horasplit[1]){
                    return true;
                }
            }
            return false;
        } catch (DAOException ex) {
            System.getLogger(CitaService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }
    
    @Override
    public Cita obtenerPorDentistaYFechaHora(String folioDentista, LocalDateTime fecha) throws BOException {
        try {
            Dentista d = dDAO.findByFolio(folioDentista)
                         .orElseThrow(() -> new BOException("Dentista no encontrado"));

            return cDAO.findCitaWithDentistaAndDateTime(d, fecha)
                   .orElseThrow(() -> new BOException("Cita no encontrada"));

        } catch (DAOException ex) {
            throw new BOException("Error en acceso a datos" + ex);
        }
    }   
    
}
