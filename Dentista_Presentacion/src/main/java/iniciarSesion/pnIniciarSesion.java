/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IniciarSesion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author manue
 */
public class pnIniciarSesion extends JFrame{
 
    public pnIniciarSesion() {


      setTitle("Clínica Dental Enríquez");
        setSize(1536,1024);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel fondo = new JPanel(null);
        fondo.setBackground(new Color(250,250,250));


        //---------------- TITULO ----------------

        JLabel titulo =
                new JLabel("Bienvenido al Sistema");

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        68));

        titulo.setForeground(
                new Color(
                        0,
                        92,
                        255));

        titulo.setBounds(
                430,
                90,
                800,
                70);


        JLabel sub =
                new JLabel(
                        "Seleccione una opción para continuar");

        sub.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        26));

        sub.setForeground(
                new Color(
                        90,
                        100,
                        120));

        sub.setBounds(
                560,
                180,
                500,
                40);



        //---------------- DIENTE ----------------

        ImageIcon icono =
                new ImageIcon(
                        "C:\\Users\\manue\\OneDrive\\Documentos\\NetBeansProjects\\ProyectoDisenoSoftware_E4-main\\Dentista_Presentacion\\src\\main\\resources\\recursos\\Diente.png");

        Image img =
                icono.getImage()
                        .getScaledInstance(
                                420,
                               500,
                                Image.SCALE_SMOOTH);

        JLabel diente =
                new JLabel(
                        new ImageIcon(img));

        diente.setBounds(
                80,
                330,
                420,
                420);



        //---------------- LOGIN ----------------

        JPanel login =
                crearTarjeta(
                        "C:\\Users\\manue\\OneDrive\\Documentos\\NetBeansProjects\\ProyectoDisenoSoftware_E4-main\\Dentista_Presentacion\\src\\main\\resources\\recursos\\login.png",
                        "Iniciar Sesión",
                        "Ingrese con su cuenta\npara continuar");

        login.setBounds(
                600,
                310,
                760,
                200);


        login.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e){

                        new InicioSesion()
                                .setVisible(true);

                        dispose();

                    }

                });



        //---------------- REGISTRO ----------------

        JPanel registro =
                crearTarjeta(
                        //"C:\\Users\\manue\\OneDrive\\Documentos\\NetBeansProjects\\ProyectoDisenoSoftware_E4-main\\Dentista_Presentacion\\src\\main\\resources\\recursos\\registro.png",
                     
                        "Registrarse\n",
                        "Crear una nueva cuenta fácilmente");

        registro.setBounds(
                600,
                600,
                760,
                200);
registro.addMouseListener(
        new MouseAdapter() {

    @Override
    public void mouseClicked(
            MouseEvent e){

        new Registro()
                .setVisible(true);

        dispose();

    }

});


        fondo.add(titulo);
        fondo.add(sub);
        fondo.add(diente);
        fondo.add(login);
        fondo.add(registro);

        add(fondo);
 
    }



    JPanel crearTarjeta(
            String ruta,
            String titulo,
            String texto){

        JPanel panel =
                new JPanel(null){

                    @Override
                    protected void paintComponent(
                            Graphics g){

                        Graphics2D g2 =
                                (Graphics2D) g;

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);

                        g2.setColor(
                                Color.WHITE);

                        g2.fillRoundRect(
                                0,
                                0,
                                getWidth(),
                                getHeight(),
                                35,
                                35);

                        g2.setColor(
                                new Color(
                                        220,
                                        230,
                                        250));

                        g2.drawRoundRect(
                                0,
                                0,
                                getWidth()-1,
                                getHeight()-1,
                                35,
                                35);

                    }

                };


        panel.setOpaque(false);



        // cuadro azul

        JPanel azul =
                new JPanel();

        azul.setBackground(
                new Color(
                       255, 255, 255));

        azul.setBounds(
                40,
                30,
                180,
                140);



        ImageIcon i =
                new ImageIcon(ruta);

        Image im =
                i.getImage()
                        .getScaledInstance(
                                250,
                                150,
                                Image.SCALE_SMOOTH);

        azul.add(
                new JLabel(
                        new ImageIcon(im)));



        JLabel txt =
                new JLabel(
                        titulo);

        txt.setBounds(
                260,
                55,
                400,
                45);

        txt.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        42));



        JLabel desc =
                new JLabel(
                        "<html>"+texto+"</html>");

        desc.setBounds(
                265,
                105,
                350,
                60);

        desc.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        22));

        desc.setForeground(
                new Color(
                        90,
                        100,
                        120));



        JLabel flecha =
                new JLabel("➜");

        flecha.setBounds(
                650,
                65,
                70,
                70);

        flecha.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        58));

        flecha.setForeground(
                new Color(
                        255,
                        255,
                        255));



        panel.add(azul);
        panel.add(txt);
        panel.add(desc);
        panel.add(flecha);



        panel.addMouseListener(
                new MouseAdapter(){

                    @Override
                    public void mouseEntered(
                            MouseEvent e){

                        panel.setCursor(
                                new Cursor(
                                        Cursor.HAND_CURSOR));

                    }

                });

        return panel;

    }

}
   
