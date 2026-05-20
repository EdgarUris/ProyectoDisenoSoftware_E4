/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_registrar;

import DAOs.DentistaDAO;
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
import objetosnegocio.Excepciones.BOException;
import objetosnegocio.dentista_objetosnegocio.DentistaService;
import objetosnegocio.dentista_objetosnegocio.IDentistaService;

/**
 *
 * @author HP
 */
public class pnlRegistrarDentista extends JPanel{
    private JTextField txtNombre, txtEspecialidad;
    private JButton btnRegistrar, btnCancelar;
    private Container contenedorPrincipal;
    private CardLayout cardLayout;
    private IDentistaService dServ;

    public pnlRegistrarDentista(Container contenedorPrincipal, CardLayout cardLayout) {
        this.contenedorPrincipal = contenedorPrincipal;
        this.cardLayout = cardLayout;
        dServ = new DentistaService(new DentistaDAO());

        setLayout(new BorderLayout(20, 30));
        setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        setBackground(new Color(245, 245, 245));

        // Título Superior
        JLabel lblTitulo = new JLabel("Registrar Nuevo Dentista", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 24));
        add(lblTitulo, BorderLayout.NORTH);

        // Formulario Central
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 15, 10, 15);

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

        // Fila 1: Especialidad
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(crearEtiqueta("Especialidad:", fontEtiquetas), gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        txtEspecialidad = crearCampoTexto("", fontCampos, colorCampos, dimCampos);
        panelFormulario.add(txtEspecialidad, gbc);

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
        String nombre = txtNombre.getText();
        String especialidad = txtEspecialidad.getText();

        if (nombre.isEmpty() || especialidad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llene todos los campos", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            if(dServ.registrar(nombre, especialidad)){
                JOptionPane.showMessageDialog(this, "Dentista registrado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(this, "No se pudo registrar el dentista", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (BOException ex) {
            System.getLogger(pnlRegistrarDentista.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        limpiarYRegresar();
    }

    private void limpiarYRegresar() {
        txtNombre.setText("");
        txtEspecialidad.setText("");
        cardLayout.show(contenedorPrincipal, "OPCIONES_REGISTRO");
    }

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
