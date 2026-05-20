/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicio;

import cu_agendarReceta.CitaSeleccionada;
import cu_gestionarAgenda.frmPadre;
import cu_registrar.pnlOpcionesRegistro;
import cu_registrar.pnlRegistrarDentista;
import cu_registrar.pnlRegistrarPaciente;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import objetosnegocio.Excepciones.BOException;

/**
 * MainFrame corregido: registra las tarjetas del menú, opciones y formularios.
 */
public class MainFrame extends JFrame {

    private JPanel contentPanel;
    private CardLayout cardLayout;

    public MainFrame() throws BOException {
        setTitle("Clínica Dental Enríquez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Panel del menú (tu panel existente)
        JPanel menuPanel = new pnlMenu(this);

        // Panel de agenda (placeholder o tu panel real)
        JPanel agendaPanel = new JPanel();
        agendaPanel.add(new JLabel("Panel de Agenda Semanal"));

        // Instanciamos los paneles reales usando el contentPanel y cardLayout
        pnlRegistrarPaciente pnlPaciente = new pnlRegistrarPaciente(contentPanel, cardLayout);
        pnlOpcionesRegistro opcionesRegistro = new pnlOpcionesRegistro(contentPanel, cardLayout);

        // Intentamos crear pnlRegistrarDentista; si falla, añadimos un placeholder
        pnlRegistrarDentista pnlDentista = null;
        try {
            pnlDentista = new pnlRegistrarDentista(contentPanel, cardLayout);
        } catch (Exception ex) {
            ex.printStackTrace();
            JPanel placeholder = new JPanel();
            placeholder.add(new JLabel("Formulario Registrar Dentista no disponible"));
            // Añadimos el placeholder inmediatamente para asegurar que la clave exista
            contentPanel.add(placeholder, "REGISTRAR_DENTISTA");
        }

        // Agregar tarjetas al contentPanel con las claves que usarás para navegar
        contentPanel.add(menuPanel, "menu");
        contentPanel.add(agendaPanel, "agenda");
        contentPanel.add(opcionesRegistro, "OPCIONES_REGISTRO");
        contentPanel.add(pnlPaciente, "REGISTRAR_PACIENTE");

        if (pnlDentista != null) {
            contentPanel.add(pnlDentista, "REGISTRAR_DENTISTA");
        }

        add(contentPanel);
        cardLayout.show(contentPanel, "menu");
    }

    public void mostrarPanel(String nombre) {
        cardLayout.show(contentPanel, nombre);
    }

    public void setContentPanel(JPanel panel) {
        this.contentPanel = panel;
    }

    public void abrirAgenda() {
        frmPadre agendas = new frmPadre();
        agendas.setVisible(true);
        this.setVisible(false);
    }

    public void abrirRecetas() {
        try {
            CitaSeleccionada frmRecetas = new CitaSeleccionada();
            frmRecetas.setVisible(true);
            this.dispose();
        } catch (BOException ex) {
            System.getLogger(MainFrame.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void abrirRegistro() {
        // Muestra la tarjeta con las dos opciones (Paciente / Dentista)
        cardLayout.show(contentPanel, "OPCIONES_REGISTRO");
    }

    // Métodos auxiliares que pnlOpcionesRegistro podría llamar
    public void abrirRegistrarPaciente() {
        cardLayout.show(contentPanel, "REGISTRAR_PACIENTE");
    }

    public void abrirRegistrarDentista() {
        cardLayout.show(contentPanel, "REGISTRAR_DENTISTA");
    }
}
