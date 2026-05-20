/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IniciarSesion;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author manue
 */
public class InicioSesion extends JFrame {
        public InicioSesion(){
    
      
        setTitle("Iniciar Sesión");
        setSize(1536,1024);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel fondo = new JPanel(null);
        fondo.setBackground(new Color(245,245,245));


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



        // ===== TITULO =====

        JLabel titulo =
                new JLabel(
                        "Iniciar Sesión");

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
                70,
                500,
                80);

        fondo.add(titulo);



        JLabel sub =
                new JLabel(
                        "Accede al sistema dental");

        sub.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        24));

        sub.setForeground(
                Color.GRAY);

        sub.setBounds(
                600,
                140,
                350,
                30);

        fondo.add(sub);



        // ===== DIENTE =====

        ImageIcon img =
                new ImageIcon(
                        "C:\\Users\\manue\\OneDrive\\Documentos\\NetBeansProjects\\ProyectoDisenoSoftware_E4-main\\Dentista_Presentacion\\src\\main\\resources\\recursos\\Diente.png");

        Image escalada =
                img.getImage()
                .getScaledInstance(
                        350,
                        450,
                        Image.SCALE_SMOOTH);

        JLabel diente =
                new JLabel(
                        new ImageIcon(
                                escalada));

        diente.setBounds(
                130,
                260,
                350,
                450);

        fondo.add(diente);



        // ===== CARD =====

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
                250,
                500,
                450);

        fondo.add(card);



        JLabel icono =
                new JLabel("👤");

        icono.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        50));

        icono.setBounds(
                220,
                20,
                80,
                80);

        card.add(icono);




        // ===== USUARIO =====

        JLabel user =
                new JLabel(
                        "Usuario");

        user.setBounds(
                70,
                100,
                100,
                20);

        card.add(user);


        JTextField usuario =
                new JTextField();

        usuario.setBounds(
                70,
                125,
                360,
                50);

        card.add(usuario);




        // ===== CONTRASEÑA =====

        JLabel pass =
                new JLabel(
                        "Contraseña");

        pass.setBounds(
                70,
                210,
                120,
                20);

        card.add(pass);


        JPasswordField contra =
                new JPasswordField();

        contra.setBounds(
                70,
                235,
                360,
                50);

        card.add(contra);




        // ===== BOTON INGRESAR =====

        JButton entrar =
                new JButton(
                        "Ingresar");

        entrar.setBounds(
                140,
                340,
                220,
                55);

        entrar.setBackground(
                new Color(
                        0,
                        102,
                        255));

        entrar.setForeground(
                Color.WHITE);

        entrar.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20));

        entrar.setFocusPainted(false);

        card.add(entrar);



        entrar.addActionListener(e -> {

            String u =
                    usuario.getText();

            String c =
                    String.valueOf(
                            contra.getPassword());


            if(u.equals("admin")
                    && c.equals("1234")){

                JOptionPane.showMessageDialog(
                        null,
                        "Bienvenido");

            }

            else{

                JOptionPane.showMessageDialog(
                        null,
                        "Datos incorrectos");

            }

        });



        add(fondo);

    }



    }

