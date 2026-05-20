/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_registrar;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author HP
 */
public class pnlRegistrarPaciente extends JPanel{
    private JTextField txtNombre, txtFechaNacimiento, txtTelefono, txtCorreo;
    private JButton btnRegistrar, btnCancelar;
    private Container contenedorPrincipal;
    private CardLayout cardLayout;

    public pnlRegistrarPaciente(Container contenedorPrincipal, CardLayout cardLayout) {
        this.contenedorPrincipal = contenedorPrincipal;
        this.cardLayout = cardLayout;

        setLayout(new BorderLayout(20, 30));
        setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        setBackground(new Color(245, 245, 245));

        // Título Superior
        JLabel lblTitulo = new JLabel("Registrar Nuevo Paciente", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 24));
        add(lblTitulo, BorderLayout.NORTH);

        // Formulario Central
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 15, 8, 15);

        Color colorCampos = new Color(158, 198, 198);
        Font fontEtiquetas = new Font("Arial", Font.PLAIN, 18);
        Font fontCampos = new Font("Arial", Font.PLAIN, 16);
        Dimension dimCampos = new Dimension(280, 35);

        // Fila 0: Nombre Completo
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(crearEtiqueta("Nombre completo:", fontEtiquetas), gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        txtNombre = crearCampoTexto("", fontCampos, colorCampos, dimCampos);
        panelFormulario.add(txtNombre, gbc);

        // Fila 1: Fecha Nacimiento
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(crearEtiqueta("Fecha de nacimiento (DD/MM/AAAA):", fontEtiquetas), gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        txtFechaNacimiento = crearCampoTexto("", fontCampos, colorCampos, dimCampos);
        panelFormulario.add(txtFechaNacimiento, gbc);

        // Fila 2: Teléfono
        gbc.gridx = 0; gbc.gridy = 4;
        panelFormulario.add(crearEtiqueta("Número de teléfono:", fontEtiquetas), gbc);
        gbc.gridx = 0; gbc.gridy = 5;
        txtTelefono = crearCampoTexto("", fontCampos, colorCampos, dimCampos);
        panelFormulario.add(txtTelefono, gbc);

        // Fila 3: Correo Electrónico
        gbc.gridx = 0; gbc.gridy = 6;
        panelFormulario.add(crearEtiqueta("Correo electrónico:", fontEtiquetas), gbc);
        gbc.gridx = 0; gbc.gridy = 7;
        txtCorreo = crearCampoTexto("", fontCampos, colorCampos, dimCampos);
        panelFormulario.add(txtCorreo, gbc);

        add(panelFormulario, BorderLayout.CENTER);

        // Panel Inferior de Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        panelBotones.setOpaque(false);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(179, 179, 179));
        btnCancelar.setFont(fontCampos);
        btnCancelar.setPreferredSize(new Dimension(140, 38));
        btnCancelar.addActionListener(e -> limpiarYRegresar());

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(new Color(92, 225, 230));
        btnRegistrar.setFont(fontCampos);
        btnRegistrar.setPreferredSize(new Dimension(140, 38));
        btnRegistrar.addActionListener(e -> ejecutarRegistro());

        panelBotones.add(btnCancelar);
        panelBotones.add(btnRegistrar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void ejecutarRegistro() {
        // Aquí conectas tu lógica de base de datos o servicios (ej: pServ.guardar(...))
        String nombre = txtNombre.getText();
        String fecha = txtFechaNacimiento.getText();
        String tel = txtTelefono.getText();
        String correo = txtCorreo.getText();

        if (nombre.isEmpty() || fecha.isEmpty() || tel.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Paciente registrado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarYRegresar();
    }

    private void limpiarYRegresar() {
        txtNombre.setText("");
        txtFechaNacimiento.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        cardLayout.show(contenedorPrincipal, "OPCIONES_REGISTRO"); // Regresa al menú de opciones
    }

    // Auxiliares de interfaz idénticos a tus especificaciones
    private JLabel crearEtiqueta(String texto, Font fuente) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(fuente);
        lbl.setForeground(Color.BLACK);
        return lbl;
    }

    private JTextField crearCampoTexto(String texto, Font fuente, Color fondo, Dimension dim) {
        JTextField txt = new JTextField(texto);
        txt.setFont(fuente);
        txt.setBackground(fondo);
        txt.setPreferredSize(dim);
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(0, 8, 0, 8)
        ));
        return txt;
    }
}
