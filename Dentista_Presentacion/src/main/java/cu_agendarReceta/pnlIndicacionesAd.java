/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_agendarReceta;


import inicio.NotificacionFlotante;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 *
 * @author Jenifer Flores
 */


/**
 * Panel final para ingresar indicaciones adicionales y generar la receta médica.
 */
public class pnlIndicacionesAd extends JPanel {

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
        contentPanel.add(cardIndicaciones);
        add(contentPanel, BorderLayout.CENTER);

        JPanel footerActionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        footerActionPanel.setOpaque(false);

        // 1. BOTÓN REGRESAR 
        JButton btnRegresar = new JButton("✕  Regresar");
        btnRegresar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setForeground(new Color(23, 57, 227)); // Azul
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegresar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 220, 228), 1, true),
                new EmptyBorder(10, 22, 10, 22)
        ));
        btnRegresar.addActionListener(e -> controlador.showCard("medicamentos"));

        // 2. BOTÓN GENERAR RECETA 
        JButton btnGenerarReceta = new JButton("Generar Receta");
        btnGenerarReceta.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnGenerarReceta.setBackground(new Color(23, 57, 227)); // Mismo azul que el texto de regresar
        btnGenerarReceta.setForeground(Color.WHITE);
        btnGenerarReceta.setFocusPainted(false);
        btnGenerarReceta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Sin bordes redondeados complejos, solo un padding igual al de regresar para que tengan el mismo tamaño
        btnGenerarReceta.setBorder(new EmptyBorder(11, 23, 11, 23)); 
        
        btnGenerarReceta.addActionListener(e -> {
            // 1. Obtener el texto limpio (añadimos .trim() para ignorar si solo pusieron espacios en blanco)
            String textoFinal = txtIndicaciones.getText().equals(placeholder) ? "" : txtIndicaciones.getText().trim();
            
            // Obtener la ventana principal para centrar la alerta
            JFrame ventanaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
            
            // 2. FILTRO DE VALIDACIÓN
            if (textoFinal.isEmpty()) {
                // SI NO HAY DATOS: Frenamos el proceso y mostramos la alerta de ERROR
                NotificacionFlotante.mostrarError(ventanaPrincipal, "Por favor, ingrese las indicaciones médicas.");
            } else {
                // SI SÍ HAY DATOS: Se realiza la acción y mostramos ÉXITO
                // (Aquí puedes colocar tu lógica para guardar en la base de datos si la tienes)
                
                NotificacionFlotante.mostrarExito(ventanaPrincipal, "¡Receta Generada Exitosamente!");
                
                // Opcional: Dejar el campo limpio con su placeholder para la siguiente receta
                txtIndicaciones.setText(placeholder);
                txtIndicaciones.setForeground(Color.GRAY);
            }
        });
        footerActionPanel.add(btnRegresar);
        footerActionPanel.add(btnGenerarReceta);
        
        add(footerActionPanel, BorderLayout.SOUTH);
    }
    
}
