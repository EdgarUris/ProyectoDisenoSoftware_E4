/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frames;

import java.awt.Image;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author manue
 */

public class PanelFondo extends JPanel {

    private Image imagen;

    public PanelFondo() {
        imagen = new ImageIcon("C:\\Users\\manue\\OneDrive\\Documentos\\NetBeansProjects\\presentacio_dentista\\src\\imagenes\\consultorio_600x400.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                             RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2d.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
}