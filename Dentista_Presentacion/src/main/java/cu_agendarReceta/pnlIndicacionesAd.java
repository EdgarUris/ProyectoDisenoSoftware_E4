/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_agendarReceta;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 *
 * @author Jenifer Flores
 */
public class pnlIndicacionesAd extends JPanel {

/**
 * Panel final para ingresar indicaciones adicionales y generar la receta médica.
 */

    private final CitaSeleccionada controlador;
    private JTextArea txtIndicaciones;

    public pnlIndicacionesAd(CitaSeleccionada controlador) {
        this.controlador = controlador;
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(30, 40, 15, 40));

        // Contenedor principal vertical
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        // --- TARJETA BLANCA PRINCIPAL ---
        CitaSeleccionada.RoundedPanel cardIndicaciones = controlador.new RoundedPanel(16, Color.WHITE);
        cardIndicaciones.setLayout(new BoxLayout(cardIndicaciones, BoxLayout.Y_AXIS));
        cardIndicaciones.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 235, 242), 1),
                new EmptyBorder(25, 30, 30, 30)
        ));

        // Título de la sección
        JLabel titleLabel = new JLabel("Indicaciones Adicionales");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titleLabel.setForeground(new Color(40, 45, 55));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardIndicaciones.add(titleLabel);
        cardIndicaciones.add(Box.createVerticalStrut(18));

        // --- ÁREA DE TEXTO ESTILIZADA CON BORDES REDONDEADOS ---
        String placeholder = "Escriba aquí las indicaciones adicionales para el paciente...";
        
        JPanel wrapperTextArea = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(245, 246, 250)); // Mismo fondo gris que los inputs previos
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
                g2.dispose();
            }
        };
        wrapperTextArea.setOpaque(false);
        wrapperTextArea.setBorder(new EmptyBorder(12, 15, 12, 15));
        wrapperTextArea.setPreferredSize(new Dimension(100, 180)); // Altura cómoda para escribir
        wrapperTextArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        wrapperTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        txtIndicaciones = new JTextArea(placeholder);
        txtIndicaciones.setFont(new Font("SansSerif", Font.PLAIN, 13));
        txtIndicaciones.setForeground(Color.GRAY);
        txtIndicaciones.setLineWrap(true);
        txtIndicaciones.setWrapStyleWord(true);
        txtIndicaciones.setOpaque(false);
        txtIndicaciones.setBorder(null);

        txtIndicaciones.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtIndicaciones.getText().equals(placeholder)) {
                    txtIndicaciones.setText("");
                    txtIndicaciones.setForeground(new Color(50, 55, 65));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtIndicaciones.getText().trim().isEmpty()) {
                    txtIndicaciones.setForeground(Color.GRAY);
                    txtIndicaciones.setText(placeholder);
                }
            }
        });

        wrapperTextArea.add(txtIndicaciones, BorderLayout.CENTER);
        cardIndicaciones.add(wrapperTextArea);
        cardIndicaciones.add(Box.createVerticalStrut(25));

        // BOTÓN GENERAR RECETA 
        JButton btnGenerarReceta = new JButton("Generar Receta") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(11, 11, 26)); // Color oscuro/negro elegante de la imagen
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnGenerarReceta.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnGenerarReceta.setForeground(Color.WHITE);
        btnGenerarReceta.setOpaque(false);
        btnGenerarReceta.setContentAreaFilled(false);
        btnGenerarReceta.setBorderPainted(false);
        btnGenerarReceta.setFocusPainted(false);
        btnGenerarReceta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGenerarReceta.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnGenerarReceta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        btnGenerarReceta.setPreferredSize(new Dimension(100, 46));
        
        btnGenerarReceta.addActionListener(e -> {
            String textoFinal = txtIndicaciones.getText().equals(placeholder) ? "" : txtIndicaciones.getText();
            JOptionPane.showMessageDialog(this, 
                    "¡Receta Médica Generada Exitosamente!",
                    "✅",
                    JOptionPane.INFORMATION_MESSAGE
                    );
        });

        cardIndicaciones.add(btnGenerarReceta);
        
        contentPanel.add(cardIndicaciones);
        add(contentPanel, BorderLayout.CENTER);

        //BOTÓN REGRESAR 
        JPanel footerActionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 15));
        footerActionPanel.setOpaque(false);

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
        btnRegresar.addActionListener(e -> controlador.showCard("medicamentos"));

        footerActionPanel.add(btnRegresar);
        add(footerActionPanel, BorderLayout.SOUTH);
    }
    
}
