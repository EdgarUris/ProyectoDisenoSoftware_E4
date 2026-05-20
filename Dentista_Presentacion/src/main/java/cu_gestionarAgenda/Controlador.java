/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_gestionarAgenda;

import java.awt.CardLayout;
import java.time.LocalDate;
import cu_agendarCita.pnlAgendarCita;
import entidades.Cita;
import entidades.Dentista;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.JPanel;

/**
 *
 * @author EdgarUris
 */
public class Controlador {

    private final JPanel contenedorPrincipal;
    private final CardLayout cardLayout;
    
    private pnlAgendaDia panelAgenda;
    private pnlCalendarioMes panelCalendario;
    private pnlAgendarCita panelAgendarCita;
    private pnlGestionCitaActual panelGestion;

    public Controlador(JPanel contenedorPrincipal, CardLayout cardLayout) {
        this.contenedorPrincipal = contenedorPrincipal;
        this.cardLayout = cardLayout;
    }
    
    public void setPanelAgenda(pnlAgendaDia panelAgenda){
        this.panelAgenda = panelAgenda;
    }
    public void setPanelCalendario(pnlCalendarioMes panelCalendario){
        this.panelCalendario = panelCalendario;
    }
    
    public void setPanelGestion(pnlGestionCitaActual panelG){
        this.panelGestion = panelG;
    }
    
    public void setPanelAgendar(pnlAgendarCita panelAgendar){
        this.panelAgendarCita = panelAgendar;
    }

    public void irAAgenda(LocalDate dia) {
        if (panelAgenda != null && dia != null) {
            // Le avisamos al panel de la agenda que cambie su fecha interna y visual
            panelAgenda.setFechaSeleccionada(dia); 
        }
        // Ahora sí, mostramos la pantalla
        cardLayout.show(contenedorPrincipal, "AGENDA");
    }
    
    
    public void irACalendario() {
        cardLayout.show(contenedorPrincipal, "CALENDARIO");
    }
    
    public void irAGestionarCita(Cita c){
        cardLayout.show(contenedorPrincipal, "GESTIONAR_CITA");
    }
    
    public void irAAgendarCita(Dentista d, LocalDate fechaSelecc, String hora){
        String[] horaSplt = hora.split(":");
        LocalTime tiempo = LocalTime.of(
            Integer.parseInt(horaSplt[0].trim()),
            Integer.parseInt(horaSplt[1].trim())
        );
        LocalDateTime fechaHoraCompleta = LocalDateTime.of(fechaSelecc, tiempo);
        
        panelAgendarCita.setDentista(d);
        panelAgendarCita.setFechaSeleccionada(fechaHoraCompleta);
        
        cardLayout.show(contenedorPrincipal, "AGENDAR_CITA");
    }
}

