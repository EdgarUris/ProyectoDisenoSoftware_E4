/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.dentista_presentacion;

import DAOs.CitaDAO;
import DAOs.DentistaDAO;
import DAOs.PacienteDAO;
import config.MongoClientProvider;
import java.time.LocalDate;
import java.time.Month;
import objetosnegocio.Excepciones.BOException;
import objetosnegocio.dentista_objetosnegocio.CitaService;
import objetosnegocio.dentista_objetosnegocio.DentistaService;
import objetosnegocio.dentista_objetosnegocio.PacienteService;

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
        CitaService cs = new CitaService(new CitaDAO());
        
        
        ds.registrar("Natanael Ruben Cano","odontologia");
        ds.registrar("Jenifer Maribel Alamea Flores", "odontologia");
        ds.registrar("Ayleen Guadalupe Urias", "odontologia");
        
        ps.registrar("Edgar Israel Urias Berrelleza", "israel.edgar.ub@gmail.com", "6442064743", LocalDate.of(2005, Month.NOVEMBER, 30));
        ps.registrar("Carlos Ignacio Quiroz Hernandez", "hodman@gmail.com", "6442434445", LocalDate.of(2005, Month.MARCH, 14));
        ps.registrar("Diego Alejandro Velderrain Rabago", "velde@gmail.com", "6442064349", LocalDate.of(2005, Month.DECEMBER, 27));
        
        
    }
    
}
