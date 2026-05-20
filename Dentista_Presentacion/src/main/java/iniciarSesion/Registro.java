/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IniciarSesion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author manue
 */
public class Registro extends JFrame{
     public Registro(){



        setTitle("Registro");
        setSize(1536,1024);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel fondo =
                new JPanel(null);

        fondo.setBackground(
                new Color(245,245,245));



        // ===== TITULO =====

        JLabel titulo =
                new JLabel("Registrarse");

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        55));

        titulo.setForeground(
                new Color(
                        0,
                        102,
                        255));

        titulo.setBounds(
                560,
                40,
                500,
                70);

        fondo.add(titulo);



      
        // ===== BOTON REGRESAR =====

        JButton regresar =
                new JButton("← Regresar");

        regresar.setBounds(
                40,
                40,
                150,
                45);

        regresar.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        regresar.setFocusPainted(false);

        regresar.setBackground(
                Color.WHITE);

        regresar.setBorder(
                new LineBorder(
                        new Color(
                                220,
                                220,
                                220),
                        1,
                        true));

        fondo.add(regresar);



        regresar.addActionListener(e -> {

            dispose();

            // abrir ventana anterior

            // new MenuPrincipal()
            // .setVisible(true);

        });
        // ===== DIENTE =====

        ImageIcon icono =
                new ImageIcon(
                        "C:\\Users\\manue\\OneDrive\\Documentos\\NetBeansProjects\\ProyectoDisenoSoftware_E4-main\\Dentista_Presentacion\\src\\main\\resources\\recursos\\Diente.png");

        Image img =
                icono.getImage()
                        .getScaledInstance(
                                350,
                                450,
                                Image.SCALE_SMOOTH);

        JLabel diente =
                new JLabel(
                        new ImageIcon(img));

        diente.setBounds(
                120,
                250,
                350,
                450);

        fondo.add(diente);



        // ===== TARJETA =====

        JPanel card =
                new JPanel(null){

            protected void paintComponent(
                    Graphics g){

                Graphics2D g2 =
                        (Graphics2D) g;

                g2.setColor(
                        Color.WHITE);

                g2.fillRoundRect(
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        40,
                        40);

            }

        };

        card.setOpaque(false);

        card.setBounds(
                620,
                170,
                520,
                620);

        fondo.add(card);



        // ===== NOMBRE =====

        JTextField nombre =
                new JTextField();

        nombre.setBounds(
                70,
                80,
                360,
                50);

        nombre.setBorder(
                BorderFactory.createTitledBorder(
                        "Nombre"));

        card.add(nombre);



        // ===== USUARIO =====

        JTextField usuario =
                new JTextField();

        usuario.setBounds(
                70,
                160,
                360,
                50);

        usuario.setBorder(
                BorderFactory.createTitledBorder(
                        "Usuario"));

        card.add(usuario);



        // ===== CORREO =====

        JTextField correo =
                new JTextField();

        correo.setBounds(
                70,
                240,
                360,
                50);

        correo.setBorder(
                BorderFactory.createTitledBorder(
                        "Correo"));

        card.add(correo);



        // ===== ROL =====
JComboBox<String> rol =
        new JComboBox<>(
                new String[]{
                        "Doctor",
                        "Recepcionista"
                });

rol.setBounds(
        70,
        320,
        360,
        50);

rol.setFont(
        new Font(
                "Segoe UI",
                Font.PLAIN,
                18));

rol.setBackground(
        Color.WHITE);

rol.setForeground(
        new Color(
                70,
                70,
                70));

rol.setBorder(null); // QUITA BORDE

rol.setFocusable(false);




// cursor mano

rol.setCursor(
        new Cursor(
                Cursor.HAND_CURSOR));

card.add(rol);


        // ===== CONTRASEÑA =====

        JPasswordField contra =
                new JPasswordField();

        contra.setBounds(
                70,
                400,
                360,
                50);

        contra.setBorder(
                BorderFactory.createTitledBorder(
                        "Contraseña"));

        card.add(contra);



        // ===== BOTON =====

        JButton registrar =
                new JButton(
                        "Registrarse");

        registrar.setBounds(
                145,
                510,
                220,
                55);

        registrar.setBackground(
                new Color(
                        0,
                        102,
                        255));

        registrar.setForeground(
                Color.WHITE);

        registrar.setFocusPainted(false);

        registrar.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        card.add(registrar);



        add(fondo);

    }


}