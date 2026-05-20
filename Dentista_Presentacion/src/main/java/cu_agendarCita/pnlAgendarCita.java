/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_agendarCita;

import cu_gestionarAgenda.Controlador;
import cu_gestionarAgenda.frmPadre;
import entidades.Cita;
import entidades.Dentista;
import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author HP
 */

public class pnlAgendarCita extends JPanel {
    
    private JTextField txtFolio;
    private JTextField txtDentista;
    private JTextField txtFechaHora;
    private JTextField txtHora;
    private JComboBox<String> cbxTratamientos;
    private JComboBox<String> cbxEstado;
    private Cita citaActual;
    private Controlador controlador;
    private LocalDate fechaSeleccionada;
    
    // Botones de acción
    private JButton btnRegresar;
    private JButton btnAgendar;

    public pnlAgendarCita(Dentista d, LocalDate fecha, Controlador control, frmPadre frame){
        
        this.fechaSeleccionada = fecha;
        this.controlador = control;
        
        btnRegresar = new JButton("Regresar");
        btnAgendar = new JButton("Agendar Cita"); // Inicialización del nuevo botón
        
        // Configuración del panel principal
        setLayout(new BorderLayout(20, 30));
        setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        setBackground(new Color(245, 245, 245)); 

        // ==========================================
        // 1. TÍTULO SUPERIOR
        // ==========================================
        JLabel lblTitulo = new JLabel("Agendar Nueva Cita", SwingConstants.CENTER); // Título actualizado
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 24));
        lblTitulo.setForeground(Color.BLACK);
        add(lblTitulo, BorderLayout.NORTH);

        // ==========================================
        // 2. PANEL CENTRAL (Formulario de 2 Columnas)
        // ==========================================
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 15, 5, 15); 
        gbc.weightx = 0.5;

        Color colorCampos = new Color(158, 198, 198); 
        Font fontEtiquetas = new Font("Arial", Font.PLAIN, 18);
        Font fontCampos = new Font("Arial", Font.PLAIN, 16);
        Dimension dimensionCampos = new Dimension(280, 35);

        // --- COLUMNA IZQUIERDA ---
        
        // Fila 0: Folio Paciente (Vacío por defecto)
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(crearEtiqueta("Folio paciente:", fontEtiquetas), gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        txtFolio = crearCampoTexto("", fontCampos, colorCampos, dimensionCampos);
        panelFormulario.add(txtFolio, gbc);

        // Fila 1: Fecha
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(crearEtiqueta("Fecha:", fontEtiquetas), gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        txtFechaHora = crearCampoTexto("21 de abril del 2026", fontCampos, colorCampos, dimensionCampos);
        panelFormulario.add(txtFechaHora, gbc);

        // Fila 2: Hora
        gbc.gridx = 0; gbc.gridy = 4;
        panelFormulario.add(crearEtiqueta("Hora:", fontEtiquetas), gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        txtHora = crearCampoTexto("12:00 pm", fontCampos, colorCampos, dimensionCampos);
        panelFormulario.add(txtHora, gbc);

        // --- COLUMNA DERECHA ---
        
        // Fila 0: Nombre Dentista
        gbc.gridx = 1; gbc.gridy = 0;
        panelFormulario.add(crearEtiqueta("Nombre dentista:", fontEtiquetas), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtDentista = crearCampoTexto("José Torres", fontCampos, colorCampos, dimensionCampos);
        panelFormulario.add(txtDentista, gbc);

        // Fila 1: Tratamiento JComboBox
        gbc.gridx = 1; gbc.gridy = 2;
        panelFormulario.add(crearEtiqueta("Tratamiento:", fontEtiquetas), gbc);
        
        cbxTratamientos = new JComboBox<>();
        cbxTratamientos.addItem("Limpieza dental");
        cbxTratamientos.addItem("Empaste");
        cbxTratamientos.addItem("Endodoncia");
        cbxTratamientos.addItem("Extracción");
        cbxTratamientos.addItem("Blanqueamiento dental");
        cbxTratamientos.addItem("Ortodoncia (Brackets)");
        
        cbxTratamientos.setFont(fontEtiquetas);
        cbxTratamientos.setBackground(colorCampos);
        cbxTratamientos.setForeground(new Color(60, 60, 60));
        cbxTratamientos.setPreferredSize(dimensionCampos);
        cbxTratamientos.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(0, 8, 0, 8) 
        ));
        
        gbc.gridx = 1; gbc.gridy = 3;
        panelFormulario.add(cbxTratamientos, gbc);

        // Fila 2: Estado JComboBox (Adaptado con los nuevos requerimientos)
        gbc.gridx = 1; gbc.gridy = 4;
        panelFormulario.add(crearEtiqueta("Estado:", fontEtiquetas), gbc);
        
        cbxEstado = new JComboBox<>();
        cbxEstado.addItem("Sin paciente");
        cbxEstado.addItem("No asistió");
        cbxEstado.addItem("Cita pendiente");
        cbxEstado.addItem("Atendida");
        
        // Estilo idéntico a cbxTratamientos para mantener simetría visual
        cbxEstado.setFont(fontEtiquetas);
        cbxEstado.setBackground(colorCampos);
        cbxEstado.setForeground(new Color(60, 60, 60));
        cbxEstado.setPreferredSize(dimensionCampos);
        cbxEstado.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(0, 8, 0, 8) 
        ));
        
        gbc.gridx = 1; gbc.gridy = 5;
        panelFormulario.add(cbxEstado, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // ==========================================
        // 3. PANEL INFERIOR (Botones de Acción)
        // ==========================================
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        panelBotones.setOpaque(false);
        
        // Estilo del botón regresar
        btnRegresar.setBackground(new Color(179, 179, 179));
        btnRegresar.setFont(fontCampos);
        btnRegresar.setPreferredSize(new Dimension(140, 38));
        
        btnRegresar.addActionListener(e -> {
            volverAAgendaDia();
        });
        
        // Estilo del nuevo botón agendar
        btnAgendar.setBackground(new Color(92, 225, 230)); // Cyan característico de tu app
        btnAgendar.setFont(fontCampos);
        btnAgendar.setPreferredSize(new Dimension(140, 38));
        
        txtFechaHora.setEditable(false);
        txtDentista.setEditable(false);
        txtHora.setEditable(false);
        
        panelBotones.add(btnRegresar);
        panelBotones.add(btnAgendar); // Ambos botones añadidos al flujo inferior

        add(panelBotones, BorderLayout.SOUTH);
    }

    // Métodos auxiliares
    private JLabel crearEtiqueta(String texto, Font fuente) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        label.setForeground(Color.BLACK);
        return label;
    }

    private JTextField crearCampoTexto(String textoPorDefecto, Font fuente, Color colorFondo, Dimension dimension) {
        JTextField campo = new JTextField(textoPorDefecto);
        campo.setFont(fuente);
        campo.setBackground(colorFondo);
        campo.setForeground(new Color(60, 60, 60));
        campo.setPreferredSize(dimension);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(0, 8, 0, 8) 
        ));
        return campo;
    }
    
    protected void cambiarFecha(Cita c){
        this.citaActual = c;
        txtDentista.setText("");
        txtFechaHora.setText(String.valueOf(c.getFecha().toLocalDate()));
        txtHora.setText(String.valueOf(c.getFecha().toLocalTime()));
    }
    
    protected void volverAAgendaDia(){
        controlador.irAAgenda(fechaSeleccionada);
    }
    
    // Getters públicos útiles para recuperar la información desde tu Controlador o Frame Padre
    public String getFolio() { return txtFolio.getText().trim(); }
    public String getDentista() { return txtDentista.getText().trim(); }
    public String getFecha() { return txtFechaHora.getText().trim(); }
    public String getHora() { return txtHora.getText().trim(); }
    public String getTratamientoSeleccionado() { return (String) cbxTratamientos.getSelectedItem(); }
    public String getEstadoSeleccionado() { return (String) cbxEstado.getSelectedItem(); }
    public JButton getBtnRegresar() { return btnRegresar; }
    public JButton getBtnAgendar() { return btnAgendar; }
}

