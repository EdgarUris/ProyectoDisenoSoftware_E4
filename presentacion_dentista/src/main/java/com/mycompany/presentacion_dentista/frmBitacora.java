/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.presentacion_dentista;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.mycompany.dto_negocios.citaDTO;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class frmBitacora extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;

    public frmBitacora() {
        setTitle("Bitácora Clínica Dental");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //columnas de la bitacora
        String[] columnas = {"Hora", "Paciente", "Tratamiento", "Dentista"};
        Object[][] datos = {
            {"9:00 a.m.", "", "", ""},
            {"9:30 a.m.", "Chayanne", "Limpieza", "Natanael Cano"},
            {"10:00 a.m.", "", "", ""},
            {"10:30 a.m.", "", "", ""},
            {"11:00 a.m.", "", "", ""},
            {"11:30 a.m.", "", "", ""},
            {"12:00 p.m.", "", "", ""},
            {"12:30 p.m.", "", "", ""},
            {"1:00 p.m.", "", "", ""},
            {"1:30 p.m.", "", "", ""},
            {"2:00 p.m.", "", "", ""},
            {"2:30 p.m.", "", "", ""},
            {"3:00 p.m.", "", "", ""},
            {"3:30 p.m.", "", "", ""},
            {"4:00 p.m.", "", "", ""},
            {"4:30 p.m.", "", "", ""},
            {"5:00 p.m.", "", "", ""},
            {"5:30 p.m.", "", "", ""}
        };

        modelo = new DefaultTableModel(datos, columnas);
        tabla = new JTable(modelo);

        //estilizar todo acá
        tabla.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabla.setRowHeight(30);
        tabla.setGridColor(Color.LIGHT_GRAY);

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(230, 240, 250)); 
                } else {
                    c.setBackground(new Color(48, 128, 235)); 
                }
                return c;
            }
        });

        // Acción al hacer clic
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                /*int fila = tabla.getSelectedRow();
                if (modelo.getValueAt(fila, 1).toString().isEmpty()) { //solo funciona si esta vacio
                    citaDTO nueva = new citaDTO();
                    //abrir nueva ventana
                } else {
                    JOptionPane.showMessageDialog(null, "Este horario ya está ocupado.");
                }*/
                citaDTO nueva = new citaDTO();
                nueva.setFechaHora(LocalDateTime.of(2026,Month.APRIL,13,15,30));
                nueva.setId(Long.valueOf(1));
                
            }
        });

        add(new JScrollPane(tabla));
    }

    public void iniciar(){
        this.setVisible(true);
    }
}