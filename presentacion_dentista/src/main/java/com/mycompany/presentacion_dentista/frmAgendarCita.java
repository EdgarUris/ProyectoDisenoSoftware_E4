/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.presentacion_dentista;

import Validaciones.Pacientes;
import com.mycompany.dto_negocios.citaDTO;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class frmAgendarCita extends JFrame{
    
    private JButton btnAgendar, btnRegresar;
    private JTextField txtPaciente;
    private JComboBox comboDentistas, comboTratamientos;
    private Pacientes validaPacientes;
    
    public frmAgendarCita(citaDTO cita){
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        validaPacientes = new Pacientes();

        JTextField txtPaciente = new JTextField();
        JTextField txtTratamiento = new JTextField();
        JTextField txtFechaHora = new JTextField();
        String texto = "";
        if(cita.getFechaHora().getMinute() == 0){
            texto = String.valueOf(cita.getFechaHora().getHour())+":"+String.valueOf(cita.getFechaHora().getMinute()+"0");
        }
        else{
            texto = String.valueOf(cita.getFechaHora().getHour())+":"+String.valueOf(cita.getFechaHora().getMinute());
        }
        txtFechaHora.setText(texto);
        txtFechaHora.setEditable(false);

        JComboBox<String> comboDentistas = new JComboBox<>(new String[]{
            "Selecciona", "Israel", "Jenifer", "Roberto"
        });
        
        JComboBox<String> comboTratamientos = new JComboBox<>(new String[]{
            "Selecciona","Endodoncia","Exodoncia","Ortodoncia","Implantologia dental","Empaste"
        });

        btnAgendar = new JButton("Agendar");
        btnAgendar.setEnabled(false); //deshabilitado hasta elegir dentista

        
        comboDentistas.addActionListener(e -> {
            btnAgendar.setEnabled(comboDentistas.getSelectedIndex() > 0);
        });
        
        comboTratamientos.addActionListener(e -> {
            btnAgendar.setEnabled(comboTratamientos.getSelectedIndex() > 0);
        });
        
        btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(e ->{
            frmBitacora bitacora = new frmBitacora();
            bitacora.iniciar();
            dispose();    
        });
        
        btnAgendar.addActionListener(e -> {
            validarCampos();
            validaPacientes.validadPacientes();
        
        });
        
        
        add(new JLabel("Paciente:"));
        add(txtPaciente);
        add(new JLabel("Tratamiento:"));
        add(comboTratamientos);
        add(new JLabel("Dentista:"));
        add(comboDentistas);
        add(new JLabel("Fecha y hora:"));
        add(txtFechaHora);
        add(btnAgendar);
        add(btnRegresar);
    }
    
    private boolean validarCampos(){
        String paciente = txtPaciente.getText().trim();
        String tratamiento = comboDentistas.getSelectedItem().toString();
        String dentista = comboDentistas.getSelectedItem().toString();
        if (paciente.isEmpty() || tratamiento.equals("Selecciona")|| dentista.equals("Selecciona")) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.");
            return false;
        }else{
            return true;
        }
    }
}
