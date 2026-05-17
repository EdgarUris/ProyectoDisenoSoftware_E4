/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 *
 * @author Jenifer Flores
 */
public class MainFrame extends JFrame{

    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    public MainFrame() {
        setTitle("Clínica Dental Enríquez");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Paneles
        JPanel menuPanel = new pnlMenu(this);
        JPanel agendaPanel = new JPanel();
        agendaPanel.add(new JLabel("Panel de Agenda Semanal"));

        contentPanel.add(menuPanel, "menu");
        contentPanel.add(agendaPanel, "agenda");

        add(contentPanel);
        cardLayout.show(contentPanel, "menu");
    }

    public void mostrarPanel(String nombre) {
        cardLayout.show(contentPanel, nombre);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }

}
