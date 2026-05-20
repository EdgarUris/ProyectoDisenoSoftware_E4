/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_gestionarAgenda;

import DAOs.CitaDAO;
import DAOs.DentistaDAO;
import DAOs.PacienteDAO;
import entidades.Cita;
import entidades.Dentista;
import entidades.Paciente;
import inicio.MainFrame;
import inicio.PanelFondo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import objetosnegocio.Excepciones.BOException;
import objetosnegocio.dentista_objetosnegocio.CitaService;
import objetosnegocio.dentista_objetosnegocio.DentistaService;
import objetosnegocio.dentista_objetosnegocio.ICitaService;
import objetosnegocio.dentista_objetosnegocio.IDentistaService;
import objetosnegocio.dentista_objetosnegocio.IPacienteService;
import objetosnegocio.dentista_objetosnegocio.PacienteService;

/**
 *
 * @author EdgarUris
 */
public class pnlAgendaDia extends JPanel {
    
    private JLabel lblFecha;
    private JComboBox<String> comboDentista;
    private JTable tablaCitas;
    private JButton btnGestionarEstado;
    private JButton btnAtras;
    private JButton btnDiaAnterior;
    private JButton btnDiaSiguiente;
    private final ICitaService cServ = new CitaService(new CitaDAO());
    private final IDentistaService dServ = new DentistaService(new DentistaDAO());
    private final IPacienteService pServ = new PacienteService(new PacienteDAO());
    private final List<Dentista> dentistas;
    private Dentista dentistaActual;
    private LocalDate fechaSeleccionada;
    private Controlador controlador;

    public pnlAgendaDia(frmPadre frame, LocalDate fechaSeleccionada, Controlador controlador) throws BOException {
        tablaCitas = new JTable();
        comboDentista = new JComboBox<>();
        this.controlador = controlador;
        
        this.fechaSeleccionada = fechaSeleccionada;
        this.dentistas = dServ.listar(100);
        dentistaActual = dentistas.get(0);
        List<String> cbx = new ArrayList<>();
        
        for (Dentista den : dentistas) {
            cbx.add(den.getNombre());
        }
        
        for (String nombre : cbx) {
            comboDentista.addItem(nombre);
        }
        
        tablaCitas.setModel(cargarCitasDeDentista(0));
        
        //cosas del panel
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(240, 244, 244)); // Fondo claro sutil

        //panel superior que dice fecha y dentista seleccionado
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);
        
        JPanel panelIzquierdoSup = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelIzquierdoSup.setOpaque(false);
        
        
        lblFecha = new JLabel("Cargando");
        lblFecha.setOpaque(true);
        lblFecha.setBackground(new Color(92, 225, 230));
        lblFecha.setForeground(Color.BLACK);
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 16));
        lblFecha.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        if (fechaSeleccionada != null) {
            agregarDia(0);
        }
        
        // Botón Día Anterior (<)
        btnDiaAnterior = new JButton("<");
        btnDiaAnterior.setBackground(new Color(92, 225, 230));
        btnDiaAnterior.setFont(new Font("Arial", Font.BOLD, 16));
        btnDiaAnterior.setPreferredSize(new Dimension(45, 38));
        btnDiaAnterior.setFocusable(false);

        // Botón Día Siguiente (>)
        btnDiaSiguiente = new JButton(">");
        btnDiaSiguiente.setBackground(new Color(92, 225, 230));
        btnDiaSiguiente.setFont(new Font("Arial", Font.BOLD, 16));
        btnDiaSiguiente.setPreferredSize(new Dimension(45, 38));
        btnDiaSiguiente.setFocusable(false);
        
        btnDiaAnterior.addActionListener(e -> agregarDia(-1));
        btnDiaSiguiente.addActionListener(e -> agregarDia(1));
        
        panelIzquierdoSup.add(lblFecha);
        panelIzquierdoSup.add(btnDiaAnterior);
        panelIzquierdoSup.add(btnDiaSiguiente);

        // Subpanel para el combobox de la derecha
        JPanel panelDerechoSup = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelDerechoSup.setOpaque(false);
        
        JLabel lblDentistaTexto = new JLabel("Dentista:");
        lblDentistaTexto.setFont(new Font("Arial", Font.BOLD, 16));
        
        comboDentista.setBackground(new Color(92, 225, 230));
        comboDentista.setFont(new Font("Arial", Font.BOLD, 14));
        comboDentista.setPreferredSize(new Dimension(150, 35));
        comboDentista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = comboDentista.getSelectedIndex();
                tablaCitas.setModel(cargarCitasDeDentista(indice));
            }});
        
        panelDerechoSup.add(lblDentistaTexto);
        panelDerechoSup.add(comboDentista);

        panelSuperior.add(panelIzquierdoSup, BorderLayout.WEST);
        panelSuperior.add(panelDerechoSup, BorderLayout.EAST);

        // ==========================================
        // 2. PANEL CENTRAL (Tabla limpia sin datos)
        // ==========================================
        // La primera columna vacía simula el espacio de las horas de tu mockup
        String[] columnas = {"Hora", "Paciente", "Tratamiento", "Notas"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        tablaCitas = new JTable(modelo);
        tablaCitas.setRowHeight(30);
        tablaCitas.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaCitas.setGridColor(Color.WHITE); // Líneas de división blancas
        
        tablaCitas.setModel(cargarCitasDeDentista(comboDentista.getSelectedIndex()));

        // Estilizar los encabezados de la tabla
        JTableHeader header = tablaCitas.getTableHeader();
        header.setBackground(new Color(92, 225, 230));
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        JScrollPane scrollPane = new JScrollPane(tablaCitas);
        scrollPane.getViewport().setBackground(Color.WHITE);

        //panel inferior
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setOpaque(false);

        // Botón Atrás (Esquina inferior izquierda)
        btnAtras = new JButton("Atrás");
        btnAtras.setBackground(new Color(165, 195, 195)); // Tono gris de la imagen
        btnAtras.setFont(new Font("Arial", Font.PLAIN, 16));
        btnAtras.setFocusable(false);
        btnAtras.setPreferredSize(new Dimension(100, 40));
        btnAtras.addActionListener(e -> {
            controlador.irACalendario();
        });

        // Botón Gestionar Estado (Centrado abajo)
        btnGestionarEstado = new JButton("Gestionar estado de cita");
        btnGestionarEstado.setBackground(new Color(92, 225, 230));
        btnGestionarEstado.setFont(new Font("Arial", Font.PLAIN, 18));
        btnGestionarEstado.setFocusable(false);
        btnGestionarEstado.setPreferredSize(new Dimension(300, 45));
        
        btnGestionarEstado.addActionListener(e -> {
            decidirQueAbrir();
        });

        // Panel intermedio para obligar al botón de gestionar a quedarse al centro
        JPanel panelCentroBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCentroBoton.setOpaque(false);
        panelCentroBoton.add(btnGestionarEstado);

        panelInferior.add(btnAtras, BorderLayout.WEST);
        panelInferior.add(panelCentroBoton, BorderLayout.CENTER);

        // ==========================================
        // INTEGRACIÓN AL PANEL PRINCIPAL
        // ==========================================
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }
    
        private DefaultTableModel cargarCitasDeDentista(int indice) {
        String[] columnas = {"Hora", "Paciente", "Tratamiento", "Costo"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        try {
        List<Cita> citasDeDentista = cServ.obtenerPorDentistaYFecha(
            dentistas.get(indice).getFolio(), 
            fechaSeleccionada
        );

        String[] horas = {
            "9:00", "9:30", "10:00", "10:30", "11:00", "11:30",
            "12:00", "12:30", "13:00", "14:00", "14:30",
            "15:00", "15:30", "16:00", "16:30", "17:00", "17:30"
        };

        // 2. Recorremos las horas fijas de la agenda
        for (String hora : horas) {
            
            // Buscamos en la lista local si hay alguna cita que coincida con esta hora
            Cita citaEnEstaHora = null;
            if (citasDeDentista != null) {
                for (Cita c : citasDeDentista) {
                    String horacita = "";
                    int horaci = c.getFecha().getHour();
                    int minci = c.getFecha().getMinute();
                    if(minci == 0){
                        horacita = String.valueOf(horaci + ":00");
                    }else{
                        horacita = String.valueOf(horaci + ":" + minci);
                    }
                    if (horacita.equals(hora)) { 
                        citaEnEstaHora = c;
                        break;
                    }
                }
            }

            // 3. Evaluamos si encontramos una cita para esta hora
            if (citaEnEstaHora != null) {
                // Buscamos el nombre del paciente usando su ID
                String nombrePaciente = "Desconocido";
                Paciente paciente = pServ.obtenerPorId(citaEnEstaHora.getPaciente_id());
                if (paciente != null) {
                    nombrePaciente = paciente.getNombre();
                }

                // Agregamos la fila con los datos de la cita
                modelo.addRow(new Object[]{
                    hora,
                    nombrePaciente,
                    citaEnEstaHora.getTratamiento().getNombre(),
                    String.valueOf(citaEnEstaHora.getTratamiento().getCosto())
                });
            } else {
                // Si no hay cita, agregamos la fila vacía con la hora correspondiente
                modelo.addRow(new Object[]{hora, "", "", ""});
            }
        }

        return modelo;

    } catch (Exception ex) {
        System.getLogger(pnlAgendaDia.class.getName()).log(System.Logger.Level.ERROR, "Error cargando tabla", ex);
    }
    
    return modelo;
    }
    
    private void agregarDia(int dias){
        this.fechaSeleccionada = this.fechaSeleccionada.plusDays(dias);
        
        DateTimeFormatter formato = DateTimeFormatter
                .ofPattern("EEEE, d 'de' MMMM 'de' yyyy", new Locale("es"));
        String nuevoTexto = this.fechaSeleccionada.format(formato);
        
        lblFecha.setText(nuevoTexto);
        cargarCitasDeDentista(comboDentista.getSelectedIndex());
    }
    
    public void setFechaSeleccionada(LocalDate fecha){
        this.fechaSeleccionada = fecha;
        agregarDia(0);
        cargarCitasDeDentista(0);
    }
    
    private void decidirQueAbrir(){
        try {
            int filaSeleccionada = tablaCitas.getSelectedRow();
            if(filaSeleccionada < 0){
                JOptionPane.showMessageDialog(this, "Selecciona una cita de la tabla","Hay que seleccionar una cita",JOptionPane.ERROR_MESSAGE);
            }
            if(cServ.existeCitaConMedicoEnHora(dentistaActual, 
                    fechaSeleccionada, 
                    String.valueOf(tablaCitas.getValueAt(filaSeleccionada, 0))))
            {
                Dentista d = dServ.obtenerPorId(dentistas.get(comboDentista.getSelectedIndex()).getId());
                String horaStr = String.valueOf(tablaCitas.getValueAt(filaSeleccionada, 0));
                String[] horaSplt = horaStr.split(":");
                LocalTime hora = LocalTime.of(
                        Integer.parseInt(horaSplt[0]),
                        Integer.parseInt(horaSplt[1])
                );
                LocalDateTime fecha = LocalDateTime.of(fechaSeleccionada, hora);
                Cita c = cServ.obtenerPorDentistaYFechaHora(d.getFolio(), fecha);
                if(c == null){
                    controlador.irAAgendarCita();
                }
                else{
                    controlador.irAGestionarCita(c);
                }
            }
        } catch (BOException ex) {
            System.getLogger(pnlAgendaDia.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
