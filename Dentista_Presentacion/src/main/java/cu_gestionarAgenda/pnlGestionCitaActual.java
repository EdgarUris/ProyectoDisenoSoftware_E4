/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_gestionarAgenda;

import DAOs.DentistaDAO;
import DAOs.PacienteDAO;
import entidades.Cita;
import entidades.Dentista;
import entidades.Paciente;
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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import objetosnegocio.Excepciones.BOException;
import objetosnegocio.dentista_objetosnegocio.DentistaService;
import objetosnegocio.dentista_objetosnegocio.IDentistaService;
import objetosnegocio.dentista_objetosnegocio.IPacienteService;
import objetosnegocio.dentista_objetosnegocio.PacienteService;

/**
 *
 * @author EdgarUris
 */
public class pnlGestionCitaActual extends JPanel{
    
    private JTextField txtFolio;
    private JTextField txtDentista;
    private JTextField txtFechaHora;
    private JComboBox<String> cbxTratamientos;
    private JTextField txtHora;
    private JTextField txtEstado;
    private Cita citaActual;
    private IPacienteService pServ;
    private IDentistaService dServ;
    
    // Botones de acción
    private JButton btnRegresar;

    public pnlGestionCitaActual(Controlador control, frmPadre frame){
        
        pServ = new PacienteService(new PacienteDAO());
        dServ = new DentistaService(new DentistaDAO());
        btnRegresar = new JButton();
        //configuración del panel principal
        setLayout(new BorderLayout(20, 30));
        setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        setBackground(new Color(245, 245, 245)); //fondo claro de la imagen

        // ==========================================
        // 1. TÍTULO SUPERIOR
        // ==========================================
        JLabel lblTitulo = new JLabel("Cita agendada", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 24));
        lblTitulo.setForeground(Color.BLACK);
        add(lblTitulo, BorderLayout.NORTH);

        // ==========================================
        // 2. PANEL CENTRAL (Formulario de 2 Columnas)
        // ==========================================
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Configuración común de espaciado para el GridBagLayout
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 15, 5, 15); // Margen entre elementos
        gbc.weightx = 0.5;

        Color colorCampos = new Color(158, 198, 198);
        Font fontEtiquetas = new Font("Arial", Font.PLAIN, 18);
        Font fontCampos = new Font("Arial", Font.PLAIN, 16);
        Dimension dimensionCampos = new Dimension(280, 35);

        // --- COLUMNA IZQUIERDA ---
        
        // Fila 0: Folio Paciente
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(crearEtiqueta("Folio paciente: ", fontEtiquetas), gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        txtFolio = crearCampoTexto("-", fontCampos, colorCampos, dimensionCampos);
        panelFormulario.add(txtFolio, gbc);

        // Fila 1: Fecha y hora
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(crearEtiqueta("Fecha y hora", fontEtiquetas), gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        txtFechaHora = crearCampoTexto("21 de abril del 2026", fontCampos, colorCampos, dimensionCampos);
        panelFormulario.add(txtFechaHora, gbc);

        // Fila 2: Hora
        gbc.gridx = 0; gbc.gridy = 4;
        panelFormulario.add(crearEtiqueta("Hora:", fontEtiquetas), gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        txtHora = crearCampoTexto("12:00 pm", fontCampos, colorCampos, dimensionCampos);
        panelFormulario.add(txtHora, gbc);
        
        // Fila 0: Nombre Dentista
        gbc.gridx = 1; gbc.gridy = 0;
        panelFormulario.add(crearEtiqueta("Nombre dentista:", fontEtiquetas), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtDentista = crearCampoTexto("José Torres", fontCampos, colorCampos, dimensionCampos);
        panelFormulario.add(txtDentista, gbc);

        // Fila 1: Tratamiento combobox
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

        // Fila 2: Estado
        gbc.gridx = 1; gbc.gridy = 4;
        panelFormulario.add(crearEtiqueta("Estado:", fontEtiquetas), gbc);
        
        gbc.gridx = 1; gbc.gridy = 5;
        txtEstado = crearCampoTexto("Sin paciente", fontCampos, colorCampos, dimensionCampos);
        panelFormulario.add(txtEstado, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // ==========================================
        // 3. PANEL INFERIOR (Botones de Acción)
        // ==========================================
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 10));
        panelBotones.setOpaque(false);
        
        btnRegresar.setText("Regresar");
        btnRegresar.setBackground(new Color(179, 179, 179));
        btnRegresar.addActionListener(e -> control.irAAgenda(LocalDate.now()));
        
        panelBotones.add(btnRegresar);
        

        add(panelBotones, BorderLayout.SOUTH);
    }

    // Métodos auxiliares para mantener el código limpio y legible
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
    
    protected void cambiarCita(Cita c){
        this.citaActual = c;
        cbxTratamientos.setSelectedItem(c.getTratamiento().getNombre());
        try {
            Paciente p = pServ.obtenerPorId(c.getPaciente_id());
            if(p == null) JOptionPane.showMessageDialog(this, "Paciente no encontrado");
            Dentista d = dServ.obtenerPorId(c.getDentista_id()); 
            if(d == null)JOptionPane.showMessageDialog(this, "Dentista no encontrado");
            
            txtFolio.setText(p.getNombre() + " " + p.getFolio());
            txtDentista.setText(d.getNombre());
            Locale espanol = Locale.forLanguageTag("es-ES");
            
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", espanol);
            String formatoFecha = c.getFecha().toLocalDate().format(formateador);
            txtFechaHora.setText(formatoFecha);
            
            
        } catch (BOException ex) {
            System.getLogger(pnlGestionCitaActual.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
