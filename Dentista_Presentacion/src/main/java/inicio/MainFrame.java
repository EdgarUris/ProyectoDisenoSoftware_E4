/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicio;

import cu_agendarReceta.CitaSeleccionada;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import cu_gestionarAgenda.pnlCalendarioMes;

import objetosnegocio.Excepciones.BOException;
import cu_gestionarAgenda.*;

/**
 *
 * @author EdgarUris
 * @author Jenifer Flores
 */
public class MainFrame extends JFrame{
    
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    public MainFrame() throws BOException {
        setTitle("Clínica Dental Enríquez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        //las cartas
        JPanel menuPanel = new pnlMenu(this);
        JPanel agendaPanel = new JPanel();
        agendaPanel.add(new JLabel("Panel de Agenda Semanal"));
        
        contentPanel.add(menuPanel, "menu");

        add(contentPanel);
        cardLayout.show(contentPanel, "menu");
    }

    public void mostrarPanel(String nombre) {
        cardLayout.show(contentPanel, nombre);
    }
    
    public void setContentPanel(JPanel panel){
        this.contentPanel = panel;
    }
    
    public void abrirAgenda(){
        frmPadre frame = new frmPadre();
        frame.setVisible(true);
        this.dispose();
    }
    
    public void abrirRecetas(){
        try {
            CitaSeleccionada frmRecetas = new CitaSeleccionada();
            frmRecetas.setVisible(true);
            this.dispose();
        } catch (BOException ex) {
            System.getLogger(MainFrame.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void abrirRegistro(){
        
    }
}
