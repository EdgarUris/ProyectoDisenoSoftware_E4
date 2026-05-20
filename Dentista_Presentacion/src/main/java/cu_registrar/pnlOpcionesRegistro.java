/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_registrar;

import static java.awt.AWTEventMulticaster.add;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author HP
 */
public class pnlOpcionesRegistro extends JPanel{
    
    private JButton btnPacientes;
    private JButton btnDentistas;
    private JButton btnRegresar;
    private Container contenedorPrincipal; // Para tu CardLayout
    private CardLayout cardLayout;

    public pnlOpcionesRegistro(Container contenedorPrincipal, CardLayout cardLayout) {
        this.contenedorPrincipal = contenedorPrincipal;
        this.cardLayout = cardLayout;

        // Configuración estética del panel principal
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("¿Qué deseas registrar?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 26));
        lblTitulo.setForeground(Color.BLACK);
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        // --- BOTONES GRANDES DE SELECCIÓN ---
        Font fontBotones = new Font("Arial", Font.PLAIN, 18);
        Dimension dimensionBotones = new Dimension(220, 80);

        btnPacientes = new JButton("Registrar Paciente");
        btnPacientes.setFont(fontBotones);
        btnPacientes.setBackground(new Color(92, 225, 230));
        btnPacientes.setPreferredSize(dimensionBotones);
        btnPacientes.addActionListener(e -> {
            cardLayout.show(contenedorPrincipal, "REGISTRAR_PACIENTE"); 
        });

        btnDentistas = new JButton("Registrar Dentista");
        btnDentistas.setFont(fontBotones);
        btnDentistas.setBackground(new Color(158, 198, 198));
        btnDentistas.setPreferredSize(dimensionBotones);
        btnDentistas.addActionListener(e -> {
            cardLayout.show(contenedorPrincipal, "REGISTRAR_DENTISTA");
        });

        // Añadir botones al Grid
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        
        gbc.gridx = 0;
        add(btnPacientes, gbc);
        
        gbc.gridx = 1;
        add(btnDentistas, gbc);
        
        btnRegresar = new JButton("Regresar al menú");
        btnRegresar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnRegresar.setBackground(new Color(200, 200, 200));
        btnRegresar.setPreferredSize(new Dimension(260, 40));
        btnRegresar.addActionListener(e -> {
            if (contenedorPrincipal != null && cardLayout != null) {
                cardLayout.show(contenedorPrincipal, "menu");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(btnRegresar, gbc);
    }
}
