/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jenifer Flores
 */
public class pnlMenu extends JPanel {
    
    public pnlMenu(MainFrame frame){
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Encabezado
        JLabel titulo = new JLabel("Bienvenido al Sistema", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(new Color(23, 57, 227));
        add(titulo, BorderLayout.NORTH);

        // Panel de botones
        JPanel botonesPanel = new JPanel(new GridLayout(2, 3, 25, 25));
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        JButton btnAgenda = crearBoton("Agenda Semanal", "agendarCita.png", frame, "calendario");
        btnAgenda.addActionListener(e -> {
            frame.abrirAgenda();
        });
        
        JButton btnRecetas = crearBoton("Generar Receta", "generarReceta.png", frame, "recetas");
        btnRecetas.addActionListener(e -> {
            frame.abrirRecetas();
        });
        
        
        

        botonesPanel.add(btnAgenda);
        botonesPanel.add(btnRecetas);
        botonesPanel.add(crearBoton("Inventario", "inventario.png", frame, "inventario"));
        botonesPanel.add(crearBoton("Generar Factura", "generarFactura.png", frame, "factura"));

        add(botonesPanel, BorderLayout.CENTER);
    }

    private JButton crearBoton(String texto, String rutaIcono, MainFrame frame, String panelDestino) {
        JButton boton = new JButton(texto);

        //Buscar la imagen en el classpath
        URL url = getClass().getResource("/recursos/" + rutaIcono);
        System.out.println("Cargando: " + url); 

        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            Image img = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(img));
        } else {
            System.out.println("No se encontró la imagen: " + rutaIcono);
        }

        //Texto debajo de la imagen
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);

        //Estilo visual
        boton.setBackground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(0, 90, 200), 2), // azul más intenso
        BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        return boton;
    }
}
