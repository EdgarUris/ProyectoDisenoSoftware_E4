/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_agendarReceta;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Jenifer Flores
 */
public class pnlDetallesCita extends JPanel{

    private final CitaSeleccionada controlador;

    public pnlDetallesCita(CitaSeleccionada controlador) {
        this.controlador = controlador;
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(30, 40, 15, 40));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        // Datos del Dentista (Usando la clase RoundedPanel del controlador)
        CitaSeleccionada.RoundedPanel dentistBlock = controlador.new RoundedPanel(16, Color.WHITE);
        dentistBlock.setLayout(new BoxLayout(dentistBlock, BoxLayout.Y_AXIS));
        dentistBlock.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 235, 242), 1),
                new EmptyBorder(20, 25, 25, 25)
        ));

        JLabel titleDentist = new JLabel("Datos del Dentista");
        titleDentist.setFont(new Font("SansSerif", Font.BOLD, 15));
        titleDentist.setForeground(new Color(40, 45, 55));
        dentistBlock.add(titleDentist);
        dentistBlock.add(Box.createVerticalStrut(20));

        JPanel gridDentist = new JPanel(new GridLayout(2, 2, 30, 15));
        gridDentist.setOpaque(false);
        gridDentist.add(createInputField("Nombre del Dentista *", "Dra. Ana María Torres", false));
        gridDentist.add(createInputField("Cédula Profesional *", "8765432", false));
        gridDentist.add(createInputField("Teléfono", "555-4321", false));
        gridDentist.add(createInputField("Dirección del Consultorio", "Calle Juárez #123, Col. Jardines", false));
        dentistBlock.add(gridDentist);

        contentPanel.add(dentistBlock);
        contentPanel.add(Box.createVerticalStrut(25));

        // Datos del Paciente (Usando la clase RoundedPanel del controlador)
        CitaSeleccionada.RoundedPanel patientBlock = controlador.new RoundedPanel(16, Color.WHITE);
        patientBlock.setLayout(new BoxLayout(patientBlock, BoxLayout.Y_AXIS));
        patientBlock.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 235, 242), 1),
                new EmptyBorder(20, 25, 25, 25)
        ));

        JLabel titlePatient = new JLabel("Datos del Paciente");
        titlePatient.setFont(new Font("SansSerif", Font.BOLD, 15));
        titlePatient.setForeground(new Color(40, 45, 55));
        patientBlock.add(titlePatient);
        patientBlock.add(Box.createVerticalStrut(20));

        JPanel rowPatient = new JPanel(new GridBagLayout());
        rowPatient.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;

        c.weightx = 0.50;
        c.insets = new Insets(0, 0, 0, 20);
        rowPatient.add(createInputField("Nombre del Paciente *", "Carlos Rodríguez", false), c);

        c.weightx = 0.15;
        c.insets = new Insets(0, 0, 0, 20);
        rowPatient.add(createInputField("Edad", "28", false), c);

        c.weightx = 0.35;
        c.insets = new Insets(0, 0, 0, 0);
        rowPatient.add(createInputField("Fecha *", "19/03/2026", true), c);
        patientBlock.add(rowPatient);

        contentPanel.add(patientBlock);
        add(contentPanel, BorderLayout.CENTER);

        
        JPanel footerActionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        footerActionPanel.setOpaque(false);

        // Botón Regresar
        JButton btnRegresar = new JButton("✕  Regresar");
        btnRegresar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnRegresar.setBackground(Color.BLUE);
        btnRegresar.setForeground(new Color(23, 57, 227));
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 220, 228), 1, true),
                new EmptyBorder(10, 22, 10, 22)
        ));
        
        btnRegresar.addActionListener(e -> controlador.showCard("select"));

        // Botón Continuar
        JButton btnContinuar = new JButton("Continuar  ➔");
        btnContinuar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnContinuar.setBackground(new Color(23, 57, 227));
        btnContinuar.setForeground(Color.BLUE);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnContinuar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(24, 119, 242), 1),
                new EmptyBorder(10, 25, 10, 25)
        ));
        btnContinuar.addActionListener(e -> controlador.showCard("medicamentos"));

        footerActionPanel.add(btnRegresar);
        footerActionPanel.add(btnContinuar);
        add(footerActionPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputField(String title, String placeholder, boolean addCalendar) {
        JPanel block = new JPanel();
        block.setLayout(new BoxLayout(block, BoxLayout.Y_AXIS));
        block.setOpaque(false);

        JLabel label = new JLabel(title);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        label.setForeground(new Color(40, 40, 40));

        JPanel customTxtBox = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(242, 244, 247));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
            }
        };
        customTxtBox.setOpaque(false);
        customTxtBox.setBorder(new EmptyBorder(4, 12, 4, 12));
        customTxtBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        customTxtBox.setPreferredSize(new Dimension(100, 38));

        JTextField field = new JTextField(placeholder);
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.setForeground(new Color(60, 65, 75));
        field.setBorder(null);
        field.setOpaque(false);
        customTxtBox.add(field, BorderLayout.CENTER);

        if (addCalendar) {
            JLabel calIcon = new JLabel("📅");
            calIcon.setForeground(Color.LIGHT_GRAY);
            customTxtBox.add(calIcon, BorderLayout.EAST);
        }

        block.add(label);
        block.add(Box.createVerticalStrut(6));
        block.add(customTxtBox);
        return block;
    }
    
}


