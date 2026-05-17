/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_gestionarAgenda;

import inicio.MainFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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

    public pnlAgendaDia(MainFrame frame, Date fechaSeleccionada) {
        //cosas del panel
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(240, 244, 244)); // Fondo claro sutil

        //panel superior que dice fecha y dentista seleccionado
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);

        //formateo de fecha recibida
        String textoFecha = "Dia: -- de -- del --- - -:--:--"; // Respaldar por si viene nulo
        if (fechaSeleccionada != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("'Dia:' d 'de' MMMM 'del' yyyy - HH:mm:ss", new Locale("es", "ES"));
            textoFecha = sdf.format(fechaSeleccionada);
        }

        lblFecha = new JLabel(textoFecha);
        lblFecha.setOpaque(true);
        lblFecha.setBackground(new Color(92, 225, 230));
        lblFecha.setForeground(Color.BLACK);
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 16));
        lblFecha.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        // Subpanel para el combobox de la derecha
        JPanel panelDerechoSup = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelDerechoSup.setOpaque(false);
        
        JLabel lblDentistaTexto = new JLabel("Dentista:");
        lblDentistaTexto.setFont(new Font("Arial", Font.BOLD, 16));
        
        comboDentista = new JComboBox<>(new String[]{"José Torres", "Dra. García", "Dr. Mendiola"});
        comboDentista.setBackground(new Color(92, 225, 230));
        comboDentista.setFont(new Font("Arial", Font.BOLD, 14));
        comboDentista.setPreferredSize(new Dimension(150, 35));

        panelDerechoSup.add(lblDentistaTexto);
        panelDerechoSup.add(comboDentista);

        panelSuperior.add(lblFecha, BorderLayout.WEST);
        panelSuperior.add(panelDerechoSup, BorderLayout.EAST);

        // ==========================================
        // 2. PANEL CENTRAL (Tabla limpia sin datos)
        // ==========================================
        // La primera columna vacía simula el espacio de las horas de tu mockup
        String[] columnas = {"", "Paciente", "Tratamiento", "Notas"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        // Agregamos las filas solo con las horas configuradas en la primera columna
        String[] horas = {
            "9:00 a.m", "9:30 a.m", "10:00 a.m", "10:30 a.m", "11:00 a.m", "11:30 a.m",
            "12:00 p.m", "12:30 p.m", "13:00 p.m", "14:00 p.m", "14:30 p.m",
            "15:00 p.m", "15:30 p.m", "16:00 p.m", "16:30 p.m", "17:00 p.m", "17:30 p.m"
        };
        for (String hora : horas) {
            modelo.addRow(new Object[]{hora, "", "", ""}); // Celdas vacías como pides
        }

        tablaCitas = new JTable(modelo);
        tablaCitas.setRowHeight(30);
        tablaCitas.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaCitas.setGridColor(Color.WHITE); // Líneas de división blancas

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

        // Botón Gestionar Estado (Centrado abajo)
        btnGestionarEstado = new JButton("Gestionar estado de cita actual");
        btnGestionarEstado.setBackground(new Color(92, 225, 230));
        btnGestionarEstado.setFont(new Font("Arial", Font.PLAIN, 18));
        btnGestionarEstado.setFocusable(false);
        btnGestionarEstado.setPreferredSize(new Dimension(300, 45));

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
}
