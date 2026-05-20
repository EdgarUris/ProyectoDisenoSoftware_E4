/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.dentista_presentacion;

import DAOs.CitaDAO;
import DAOs.DentistaDAO;
import DAOs.PacienteDAO;
import config.MongoClientProvider;
import entidades.Cita;
import entidades.Dentista;
import entidades.Medicamento;
import entidades.Receta;
import entidades.Tratamiento;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import objetosnegocio.Excepciones.BOException;
import objetosnegocio.dentista_objetosnegocio.CitaService;
import objetosnegocio.dentista_objetosnegocio.DentistaService;
import objetosnegocio.dentista_objetosnegocio.PacienteService;
import objetosnegocio.dentista_objetosnegocio.RecetaBO;
import objetosnegocio.dentista_objetosnegocio.TratamientoBO;
import org.bson.types.ObjectId;

/**
 *
 * @author HP
 */
public class Seeder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BOException {
        MongoClientProvider.INSTANCE.init();
        DentistaService ds = new DentistaService(new DentistaDAO());
        PacienteService ps = new PacienteService(new PacienteDAO());
        RecetaBO rs = new RecetaBO();
        CitaService cs = new CitaService(new CitaDAO());
        TratamientoBO tServ = new TratamientoBO();
//        
//        
//        ds.registrar("Natanael Ruben Cano","odontologia");
//        ds.registrar("Jenifer Maribel Alamea Flores", "odontologia");
//        ds.registrar("Ayleen Guadalupe Urias", "odontologia");
//
//        ps.registrar("Edgar Israel Urias Berrelleza", "israel.edgar.ub@gmail.com", "6442064743", LocalDate.of(2005, Month.NOVEMBER, 30));
//        ps.registrar("Carlos Ignacio Quiroz Hernandez", "hodman@gmail.com", "6442434445", LocalDate.of(2005, Month.MARCH, 14));
//        ps.registrar("Diego Alejandro Velderrain Rabago", "velde@gmail.com", "6442064349", LocalDate.of(2005, Month.DECEMBER, 27));
//        
//        List<Dentista> dentistas = ds.listar(100);
//        for (Dentista dentista : dentistas) {
//            System.out.println(dentista.getNombre() + dentista.getFolio());
//        }
//
//        Tratamiento t1 = new Tratamiento("Cambio de ligas",500.0);
//        cs.agendar("EIUB8009", "JEMAALFL", LocalDateTime.of(2026, Month.MAY, 19, 18, 0), "cambio de ligas", "pendiente", t1);
//        
//        Tratamiento t2 = new Tratamiento("Limpieza",1200.0);
//        cs.agendar("CIQH7605", "NARUCA", LocalDateTime.of(2026, Month.MAY, 18, 17, 30), "limpieza", "pendiente", t2);
//
//        Medicamento m1 = new Medicamento("Paracetamol","50mg","2 al dia cada 8 horas","6 dias");
//        Medicamento m2 = new Medicamento("Ibuprofeno","50mg","2 al dia cada 10 horas","5 dias");
//        
//        List<Medicamento> medicamentos = new ArrayList<>();
//        medicamentos.add(m1);
//        medicamentos.add(m2);
//        
//        List<Cita> citas = cs.obtenerPorFechaHora(LocalDateTime.of(2026, Month.MAY, 19, 18, 0));
//        rs.guardar(citas.getFirst().getId(), medicamentos);
        
//        cbxTratamientos.addItem("Limpieza dental");
//        cbxTratamientos.addItem("Empaste");
//        cbxTratamientos.addItem("Endodoncia");
//        cbxTratamientos.addItem("Extracción");
//        cbxTratamientos.addItem("Blanqueamiento dental");
//        cbxTratamientos.addItem("Ortodoncia");
        
        tServ.agregar("Limpieza dental", 500.0);
        tServ.agregar("Empaste", 1500.0);
        tServ.agregar("Endodoncia", 1000.0);
        tServ.agregar("Extracción", 1500.0);
        tServ.agregar("Blanqueamiento dental", 400.0);
        tServ.agregar("Ortodoncia", 3000.0);
        tServ.agregar("Ajuste de ortodoncia", 400.0);
        
        List<Tratamiento> listaT = tServ.listarTodos();
        for (Tratamiento t : listaT) {
            System.out.println(t.getNombre());
        }
    }
    
}
