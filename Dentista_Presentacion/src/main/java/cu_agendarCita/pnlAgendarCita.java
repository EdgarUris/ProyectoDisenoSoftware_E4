/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_agendarCita;

import DAOs.CitaDAO;
import DAOs.PacienteDAO;
import cu_gestionarAgenda.Controlador;
import cu_gestionarAgenda.frmPadre;
import entidades.Cita;
import entidades.Dentista;
import entidades.Tratamiento;
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
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import objetosnegocio.Excepciones.BOException;
import objetosnegocio.dentista_objetosnegocio.CitaService;
import objetosnegocio.dentista_objetosnegocio.ICitaService;
import objetosnegocio.dentista_objetosnegocio.IPacienteService;
import objetosnegocio.dentista_objetosnegocio.ITratamientoBO;
import objetosnegocio.dentista_objetosnegocio.PacienteService;
import objetosnegocio.dentista_objetosnegocio.TratamientoBO;

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
    private ICitaService cServ = new CitaService(new CitaDAO());
    private ITratamientoBO tServ = new TratamientoBO();
    private IPacienteService pServ = new PacienteService(new PacienteDAO());
    private Dentista dentista;
    
    // Botones de acción
    private JButton btnRegresar;
    private JButton btnAgendar;

    public pnlAgendarCita(Controlador control, frmPadre frame){
        
        this.dentista = new Dentista();
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
        txtFechaHora = crearCampoTexto("", fontCampos, colorCampos, dimensionCampos);
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
        panelFormulario.add(crearEtiqueta("Dentista:", fontEtiquetas), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtDentista = crearCampoTexto(dentista.getNombre(), fontCampos, colorCampos, dimensionCampos);
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
        
        cbxEstado.setSelectedIndex(2);
        
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
        
        btnAgendar.addActionListener(e -> {
            Tratamiento t;
            try {
                if(pServ.obtenerPorFolio(txtFolio.getText().trim()) == null){
                    JOptionPane.showMessageDialog(this, "Paciente con folio "+txtFolio.getText() + " no encontrado"
                    , "Paciente no encontrado",JOptionPane.WARNING_MESSAGE);
                }
                
                String horacompleta = txtHora.getText();
                String[] split1 = horacompleta.split(" ");
                String[] split2 = split1[0].split(":");
                
                int hora = Integer.parseInt(split2[0]);
                int min = Integer.parseInt(split2[1]);
                
                LocalDateTime fechacita = LocalDateTime.of(fechaSeleccionada, LocalTime.of(hora, min));
                
                t = tServ.buscarPorNombre(String.valueOf(cbxTratamientos.getSelectedItem()));
                if(cServ.agendar(txtFolio.getText(), 
                        dentista.getFolio(), 
                        fechacita, 
                        t.getNombre(), 
                        t)){
                    JOptionPane.showMessageDialog(this, "Cita agendada con exito, en breve se mandará el correo al paciente"
                    , "Cita agendada",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Cita no agendada, intente de nuevo más tarde"
                    , "Cita no agendada",JOptionPane.ERROR_MESSAGE);
                }
            } catch (BOException ex) {
                System.getLogger(pnlAgendarCita.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
        
        
        txtFechaHora.setEditable(false);
        txtDentista.setEditable(false);
        txtHora.setEditable(false);
        
        panelBotones.add(btnRegresar);
        panelBotones.add(btnAgendar);

        add(panelBotones, BorderLayout.SOUTH);
        
        //System.out.println("Fecha actual: "+fechaSeleccionada.toString());
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
    
    protected void volverAAgendaDia(){
        controlador.irAAgenda(this.fechaSeleccionada);
    }

    public void setFechaSeleccionada(LocalDate fechaSeleccionada, String hora) {
        if (fechaSeleccionada != null) {
            this.fechaSeleccionada = fechaSeleccionada;

            Locale espanol = Locale.forLanguageTag("es-ES");
            
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", espanol);
            String formatoFecha = fechaSeleccionada.format(formateador);
            txtFechaHora.setText(formatoFecha);
            
            txtHora.setText(hora);
        }
    }

    public void setDentista(Dentista dentista) {
        if(dentista != null){
            this.dentista = dentista;
            txtDentista.setText(dentista.getNombre());
        }
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

