/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicio;

import cu_gestionarAgenda.pnlAgendaDia;
import cu_gestionarAgenda.pnlCalendarioMes;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import objetosnegocio.Excepciones.BOException;
import cu_gestionarAgenda.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 *
 * @author EdgarUris
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
        JPanel pnlCalendarioMes = new pnlCalendarioMes(this);
        JPanel pnlAgendaDia = new pnlAgendaDia(this, LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        JPanel menuPanel = new pnlMenu(this);
        JPanel agendaPanel = new JPanel();
        agendaPanel.add(new JLabel("Panel de Agenda Semanal"));

        contentPanel.add(menuPanel, "menu");
        contentPanel.add(agendaPanel, "agenda");
        contentPanel.add(pnlCalendarioMes, "calendario");
        contentPanel.add(pnlAgendaDia, "agendaDia");

        add(contentPanel);
        cardLayout.show(contentPanel, "menu");
    }

    public void mostrarPanel(String nombre) {
        cardLayout.show(contentPanel, nombre);
    }
    
    public void setContentPanel(JPanel panel){
        this.contentPanel = panel;
    }

}
