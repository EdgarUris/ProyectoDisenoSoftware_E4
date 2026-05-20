/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_gestionarAgenda;

import cu_agendarCita.pnlAgendarCita;
import entidades.Cita;
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
        setTitle("Sistema de Gestión Dental");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1. Inicializar el Layout y el Contenedor de cartas
        cardLayout = new CardLayout();
        contenedorPrincipal = new JPanel(cardLayout);

        // 2. Inicializar el Controlador de Navegación
        controlador = new Controlador(contenedorPrincipal, cardLayout);

        try {
            // 3. Instanciar los paneles inyectándoles el controlador
            pnlAgendaDia panelAgenda = new pnlAgendaDia(this, LocalDate.now(), controlador);
            pnlCalendarioMes panelCalendario = new pnlCalendarioMes(controlador);
            pnlGestionCitaActual panelGestion = new pnlGestionCitaActual(new Cita(), controlador, this);
            pnlAgendarCita panelAgendarC = new pnlAgendarCita(null, controlador, this);

            // 4. Conectar los paneles con el controlador
            controlador.setPanelAgenda(panelAgenda);
            controlador.setPanelCalendario(panelCalendario);

            // 5. Agregar los paneles al CardLayout con sus nombres clave
            contenedorPrincipal.add(panelAgenda, "AGENDA");
            contenedorPrincipal.add(panelCalendario, "CALENDARIO");
            contenedorPrincipal.add(panelGestion, "GESTIONAR_CITA");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 6. Agregar el contenedor al Frame y mostrar la pantalla inicial
        add(contenedorPrincipal);
        controlador.irACalendario(); // Pantalla por defecto
    }
    
    protected void volverAInicio() throws BOException{
        MainFrame inicio = new MainFrame();
        inicio.setVisible(true);
        this.dispose();
    }
}

