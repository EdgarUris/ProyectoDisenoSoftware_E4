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
 * @author Jenifer Flores
 */
public class pnlMenu extends JPanel {
    
    public pnlMenu(MainFrame frame){
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 1. Encabezado del Menú
        JLabel titulo = new JLabel("Bienvenido al Sistema", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28)); 
        titulo.setForeground(new Color(23, 57, 227));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0)); 
        add(titulo, BorderLayout.NORTH);

        // 2. Panel de Botones Inteligente 
        JPanel botonesPanel = new JPanel(new GridLayout(0, 3, 25, 25)); 
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 40, 40));
        
        
        // Botón 1: Agenda
        JButton btnAgenda = crearBoton("Agenda Semanal", "agendarCita.png", frame, "calendario");
        btnAgenda.addActionListener(e -> {
            frame.abrirAgenda();
        });
        botonesPanel.add(btnAgenda);
        
        // Botón 2: Recetas
        JButton btnRecetas = crearBoton("Generar Receta", "generarReceta.png", frame, "recetas");
        btnRecetas.addActionListener(e -> {
            frame.abrirRecetas();
        });
        botonesPanel.add(btnRecetas);
        
        // Botón 3: Registrar Paciente / Dentista
        JButton btnRegistro = crearBoton("Registro Clínico", "registro.png", frame, "registro");
        btnRegistro.addActionListener(e -> frame.abrirRegistro()); 
        botonesPanel.add(btnRegistro);
        
        // Botón 4: Factura
        JButton btnFactura = crearBoton("Generar Factura", "generarFactura.png", frame, "factura");
        // btnFactura.addActionListener(e -> frame.abrirFactura());
        botonesPanel.add(btnFactura);

        // Botón 5: Inventario
        JButton btnInventario = crearBoton("Inventario", "inventario.png", frame, "inventario");
        // btnInventario.addActionListener(e -> frame.abrirInventario());
        botonesPanel.add(btnInventario);

        // 3. Agregar el panel de botones al centro
        add(botonesPanel, BorderLayout.CENTER);
    }

    // Método optimizado con diseño moderno y efecto Hover
    private JButton crearBoton(String texto, String rutaIcono, MainFrame frame, String panelDestino) {
        JButton boton = new JButton(texto);

        // Paleta de colores moderna
        Color colorBordeNormal = new Color(225, 230, 240); 
        Color colorBordeHover = new Color(23, 57, 227);    
        Color colorFondoNormal = Color.WHITE;
        Color colorFondoHover = new Color(246, 248, 255);  
        Color colorTexto = new Color(60, 64, 67);          

        // Cargar y escalar icono
        URL url = getClass().getResource("/recursos/" + rutaIcono);
        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            Image img = icono.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(img));
        } else {
            System.out.println("No se encontró la imagen: " + rutaIcono);
        }

        // Configurar posiciones de texto e icono
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        boton.setIconTextGap(15); // Separación elegante entre imagen y texto

        // Estilo visual de la "Tarjeta"
        boton.setBackground(colorFondoNormal);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        boton.setForeground(colorTexto);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(true);
        
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(colorBordeNormal, 1, true),
            BorderFactory.createEmptyBorder(20, 15, 20, 15) 
        ));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondoHover);
                boton.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(colorBordeHover, 1, true),
                    BorderFactory.createEmptyBorder(20, 15, 20, 15)
                ));
                boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorFondoNormal);
                boton.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(colorBordeNormal, 1, true),
                    BorderFactory.createEmptyBorder(20, 15, 20, 15)
                ));
            }
        });

        return boton;
    }
}