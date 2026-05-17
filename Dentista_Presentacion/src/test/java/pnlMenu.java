/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 *
 * @author Jenifer Flores
 */
public class pnlMenu extends JPanel{
    public pnlMenu(MainFrame frame){
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Encabezado
        JLabel titulo = new JLabel("Bienvenido al Sistema", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(new Color(0, 90, 90));
        add(titulo, BorderLayout.NORTH);

        // Panel de botones
        JPanel botonesPanel = new JPanel(new GridLayout(2, 3, 25, 25));
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        botonesPanel.add(crearBoton("Agenda Semanal", "prueba.png", frame, "agenda"));
        botonesPanel.add(crearBoton("Registro Clínico", "prueba.png", frame, "registro"));
        botonesPanel.add(crearBoton("Generar Receta", "prueba.png", frame, "receta"));
        botonesPanel.add(crearBoton("Bitácora", "prueba.png", frame, "bitacora"));
        botonesPanel.add(crearBoton("Inventario", "prueba.png", frame, "inventario"));
        botonesPanel.add(crearBoton("Generar Factura", "prueba.png", frame, "factura"));

        add(botonesPanel, BorderLayout.CENTER);
    }

    private JButton crearBoton(String texto, String rutaIcono, MainFrame frame, String panelDestino) {
        JButton boton = new JButton(texto);

        // Buscar la imagen en el classpath
        URL url = getClass().getResource("/prueba.png");
        System.out.println("Cargando: " + url); // Verificación en consola

        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            Image img = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(img));
        } else {
            System.out.println("No se encontró la imagen: " + rutaIcono);
        }

        // Texto debajo de la imagen
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);

        // Estilo visual
        boton.setBackground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Acción al hacer clic
        boton.addActionListener(e -> frame.mostrarPanel(panelDestino));

        return boton;
    }
}
