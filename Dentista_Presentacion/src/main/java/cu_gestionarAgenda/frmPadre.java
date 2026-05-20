/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_gestionarAgenda;

import cu_agendarCita.pnlAgendarCita;
import cu_registrar.pnlOpcionesRegistro;
import cu_registrar.pnlRegistrarDentista;
import cu_registrar.pnlRegistrarPaciente;
import entidades.Cita;
import entidades.Dentista;
import java.awt.CardLayout;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JPanel;
import inicio.MainFrame;
import java.time.LocalDateTime;
import objetosnegocio.Excepciones.BOException;

/**
 *
 * @author HP
 */
public class frmPadre extends JFrame{
    
    private CardLayout cardLayout;
    private JPanel contenedorPrincipal;
    private Controlador controlador;

    public frmPadre() {
        setTitle("");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contenedorPrincipal = new JPanel(cardLayout);

        controlador = new Controlador(contenedorPrincipal, cardLayout);

        try {
            pnlAgendaDia panelAgenda = new pnlAgendaDia(this, LocalDate.now(), controlador);
            pnlCalendarioMes panelCalendario = new pnlCalendarioMes(controlador);
            pnlGestionCitaActual panelGestion = new pnlGestionCitaActual(controlador, this);
            pnlAgendarCita panelAgendarC = new pnlAgendarCita(controlador, this);
            pnlOpcionesRegistro panelOpReg = new pnlOpcionesRegistro(contenedorPrincipal, cardLayout);
            pnlRegistrarPaciente panelRegPac = new pnlRegistrarPaciente(contenedorPrincipal, cardLayout);
            pnlRegistrarDentista panelRegDent = new pnlRegistrarDentista(contenedorPrincipal, cardLayout);
            
            controlador.setPanelAgenda(panelAgenda);
            controlador.setPanelCalendario(panelCalendario);
            controlador.setPanelAgendar(panelAgendarC);
            controlador.setPanelGestion(panelGestion);

            contenedorPrincipal.add(panelOpReg, "OPCIONES_REGISTRO");
            contenedorPrincipal.add(panelRegPac, "REGISTRAR_PACIENTE");
            contenedorPrincipal.add(panelRegDent, "REGISTRAR_DENTISTA");
            contenedorPrincipal.add(panelAgenda, "AGENDA");
            contenedorPrincipal.add(panelCalendario, "CALENDARIO");
            contenedorPrincipal.add(panelGestion, "GESTION_CITA");
            contenedorPrincipal.add(panelAgendarC, "AGENDAR_CITA");

        } catch (Exception e) {
            e.printStackTrace();
        }

        add(contenedorPrincipal);
        controlador.irACalendario(); // Pantalla por defecto
    }
    
    protected void volverAInicio() throws BOException{
        MainFrame inicio = new MainFrame();
        inicio.setVisible(true);
        this.dispose();
    }
}

