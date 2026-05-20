package inicio;

import javax.swing.*;
import java.awt.*;
import java.net.URL; 

public class NotificacionFlotante extends JDialog {

    public static final int EXITO = 1;
    public static final int ERROR = 2;

    private NotificacionFlotante(JFrame parent, String mensaje, int tipo) {
        super(parent, false); 
        setUndecorated(true); 
        setBackground(new Color(0, 0, 0, 0)); 
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(new Color(244, 248, 252));
        panelPrincipal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 230), 1),
                BorderFactory.createEmptyBorder(20, 40, 20, 40)
        ));

        // --- 1. SE ADAPTA A TU CARPETA DE RECURSOS ---
        String rutaImagen = "";
        if (tipo == EXITO) {
            rutaImagen = "/recursos/exito.png"; // <-- Verifica que tu archivo verde se llame así dentro de 'recursos'
        } else {
            rutaImagen = "/recursos/error.png"; // <-- Verifica que tu archivo rojo se llame así dentro de 'recursos'
        }
        
        // --- 2. CARGAR LA IMAGEN TAL CUAL COMO LO HACES EN TU OTRO PANEL ---
        JLabel lblIcono = new JLabel();
        URL url = getClass().getResource(rutaImagen); 

        if (url != null) {
            ImageIcon iconoOriginal = new ImageIcon(url);
            // Redimensionamos a 80x80 para que quede perfecto en la tarjeta
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            lblIcono.setIcon(new ImageIcon(imagenEscalada));
        } else {
            // Si no la encuentra, te imprimirá en consola el error exacto
            System.out.println("No se encontró la imagen en: " + rutaImagen);
            lblIcono.setText("❌ Imagen no encontrada"); 
            lblIcono.setForeground(Color.RED);
        }
        
        lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(lblIcono);
        panelPrincipal.add(Box.createVerticalStrut(15)); 

        // Mensaje
        JLabel lblMensaje = new JLabel(mensaje);
        lblMensaje.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblMensaje.setForeground(new Color(40, 45, 55));
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(lblMensaje);

        add(panelPrincipal);
        pack(); 

        if (parent != null) {
            setLocationRelativeTo(parent);
        } else {
            setLocationRelativeTo(null);
        }

        // Temporizador para cerrar la alerta en 2.5 segundos
        Timer timer = new Timer(2500, e -> dispose());
        timer.setRepeats(false);
        timer.start();
    }

    public static void mostrarExito(JFrame parent, String mensaje) {
        NotificacionFlotante notificacion = new NotificacionFlotante(parent, mensaje, EXITO);
        notificacion.setVisible(true);
    }

    public static void mostrarError(JFrame parent, String mensaje) {
        NotificacionFlotante notificacion = new NotificacionFlotante(parent, mensaje, ERROR);
        notificacion.setVisible(true);
    }
}