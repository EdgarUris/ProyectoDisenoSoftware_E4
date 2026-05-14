/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.dentista_datos;

import DAOs.CitaDAO;
import DAOs.DentistaDAO;
import DAOs.ICitaDAO;
import DAOs.IDentistaDAO;
import DAOs.IPacienteDAO;
import DAOs.PacienteDAO;
import Exception.DAOException;
import config.MongoClientProvider;
import entidades.Cita;
import entidades.Dentista;
import entidades.Paciente;
import entidades.Tratamiento;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author jeniferfl
 */
public class Dentista_Datos {

    public static void main(String[] args) throws DAOException {
        
        MongoClientProvider.INSTANCE.init();
        
        IDentistaDAO ddao = new DentistaDAO();
        IPacienteDAO pdao = new PacienteDAO();
        ICitaDAO cdao = new CitaDAO();
        
        Paciente p = new Paciente();
        p.setNombre("Edgar Urias");
        p.setCorreo("edgar.urias@gmail.com");
        p.setNumeroTelefono("6442064743");
        pdao.create(p);
        
        Paciente p1 = new Paciente();
        p1.setNombre("Jenifer Flores");
        p1.setCorreo("natanaelcano@gmail.com");
        p1.setNumeroTelefono("6442479933");
        pdao.create(p1);
        
        Dentista d = new Dentista();
        d.setEspecialidad("odontologo");
        d.setNombre("Manuel Rios");
        ddao.create(d);
        
        Cita c = new Cita();
        c.setDentista_id(d.getId());
        c.setPaciente_id(p.getId());
        c.setMotivo("Instalacion de brackets");
        c.setTratamiento(new Tratamiento("Instalacion de brackets",2500.0));
        c.setFecha(LocalDateTime.of(2026, Month.MAY, 14, 13, 30));
        
        cdao.create(c);
        
        Cita c2 = new Cita();
        c2.setDentista_id(d.getId());
        c2.setPaciente_id(p1.getId());
        c2.setMotivo("Dolor de diente");
        c2.setTratamiento(new Tratamiento("Anestesia",100.0));
        c2.setFecha(LocalDateTime.of(2026, Month.MAY, 14, 12, 30));
        
        cdao.create(c2);
        
//        List<Cita> citas = cdao.findCitasWithDentistaAndDate(d, LocalDate.of(2026, Month.MAY, 14));
//        
//        System.out.println("Citas el 14 de mayo con el medico "+d.getNombre());
//        for (Cita cita : citas) {
//            System.out.println(cita);
//        }
        
        
        
    }
}
