/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_agendarReceta;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Jenifer Flores
 */
public class CitaSeleccionada extends JFrame {
   
    private Point initialClick;
    private JPanel cardsContainer;
    private CardLayout cardLayout;
    
    public CitaSeleccionada() {
        setUndecorated(true);
        setSize(900, 640); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel Principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 247, 251)); 
        mainPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        //Barra de Título Personalizada
        mainPanel.add(createTitleBar(), BorderLayout.NORTH);

        //Contenido Central
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        //Título y Botón Historial
        contentPanel.add(createHeaderPanel(), BorderLayout.NORTH);

        // Tarjeta de Citas
        contentPanel.add(createAppointmentCard(), BorderLayout.CENTER);

        contentPanel.add(createFooterPanel(), BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
       
        // Contenedor de tarjetas con CardLayout
        cardLayout = new CardLayout();
        cardsContainer = new JPanel(cardLayout);
        cardsContainer.setOpaque(false);

        pnlDetallesCita detailsPanel = new pnlDetallesCita(this);
        pnlindicarMedicamentos pnlmedicamentos = new pnlindicarMedicamentos(this); 
        pnlIndicacionesAd pnlindicaciones = new pnlIndicacionesAd(this);
        
        cardsContainer.add(contentPanel, "select");              // Pantalla 1
        cardsContainer.add(detailsPanel, "details");            // Pantalla 2
        cardsContainer.add(pnlmedicamentos, "medicamentos");   // Pantalla 3
        cardsContainer.add(pnlindicaciones, "indicaciones");   // Pantalla 4


        // Mostrar la tarjeta inicial por defecto
        cardLayout.show(cardsContainer, "select");
        
        mainPanel.add(cardsContainer, BorderLayout.CENTER);
    }
    public void showCard(String name) {
    if (cardLayout != null && cardsContainer != null) {
        cardLayout.show(cardsContainer, name);
        cardsContainer.revalidate();
        cardsContainer.repaint();
    }
}

    //BARRA DE TÍTULO
    private JPanel createTitleBar() {
        JPanel titleBar = new JPanel(new BorderLayout());
        titleBar.setBackground(new Color(92, 225, 230)); // Color verde/teal de la imagen
        titleBar.setPreferredSize(new Dimension(900, 35));
        titleBar.setBorder(new EmptyBorder(0, 15, 0, 5));

        
        titleBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });
        titleBar.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int thisX = getLocation().x;
                int thisY = getLocation().y;
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;
                setLocation(thisX + xMoved, thisY + yMoved);
            }
        });

        JLabel titleLabel = new JLabel("Clinica Dental Enriquez");
        titleLabel.setFont(new Font("Serif", Font.ITALIC, 14));
        titleLabel.setForeground(Color.DARK_GRAY);
        titleBar.add(titleLabel, BorderLayout.WEST);

        // Botones de la ventana
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        buttonPanel.setOpaque(false);
        
        JButton btnMinimize = createTitleButton("-");
        JButton btnMaximize = createTitleButton("⬜");
        JButton btnClose = createTitleButton("X");
        btnClose.setBackground(new Color(200, 100, 100));
        btnClose.addActionListener(e -> System.exit(0));

        buttonPanel.add(btnMinimize);
        buttonPanel.add(btnMaximize);
        buttonPanel.add(btnClose);
        titleBar.add(buttonPanel, BorderLayout.EAST);

        return titleBar;
    }

    private JButton createTitleButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(30, 25));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBackground(new Color(163, 198, 192));
        btn.setMargin(new Insets(0,0,0,0));
        return btn;
    }

    //ENCABEZADO 
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Izquierda: Ícono y Títulos
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        JLabel mainTitle = new JLabel("📄 Consultorio Dental");
        mainTitle.setFont(new Font("Serif", Font.BOLD, 32));
        mainTitle.setForeground(new Color(40, 50, 70));

        JLabel subTitle = new JLabel("    Sistema de Generación de Recetas Médicas");
        subTitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subTitle.setForeground(new Color(100, 110, 120));

        titlePanel.add(mainTitle);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subTitle);

        // Botón Historial
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);
        
        JButton btnHistorial = new JButton("⏱ Historial de Recetas");
        btnHistorial.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnHistorial.setBackground(Color.WHITE);
        btnHistorial.setFocusPainted(false);
        btnHistorial.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHistorial.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                new EmptyBorder(8, 15, 8, 15)
            ));
        
        // ACCIÓN: Abrir el diálogo lateral del historial
        btnHistorial.addActionListener(e -> {
            HistorialDialog dialog = new HistorialDialog(this);
            dialog.setVisible(true);
        });
        
        rightPanel.add(btnHistorial);

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(rightPanel, BorderLayout.EAST);

        return headerPanel;
    }

    //TARJETA PRINCIPAL (Citas)
    private JPanel createAppointmentCard() {
        RoundedPanel cardPanel = new RoundedPanel(20, new Color(245, 250, 255));
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 210, 255), 2),
                new EmptyBorder(20, 25, 20, 25)
        ));

        JLabel cardTitle = new JLabel("📅 Seleccionar Cita");
        cardTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        cardTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        cardPanel.add(cardTitle, BorderLayout.NORTH);

        JPanel innerContent = new JPanel();
        innerContent.setLayout(new BoxLayout(innerContent, BoxLayout.Y_AXIS));
        innerContent.setOpaque(false);

        JLabel comboLabel = new JLabel("Cita Programada");
        comboLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        comboLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        String[] citas = {"Israel Urias - 17 may 2026 09:00"};
        JComboBox<String> citaCombo = new JComboBox<>(citas);
        citaCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        citaCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
        citaCombo.setBackground(new Color(245, 245, 245));

        innerContent.add(comboLabel);
        innerContent.add(Box.createVerticalStrut(5));
        innerContent.add(citaCombo);
        innerContent.add(Box.createVerticalStrut(20));

        RoundedPanel detailsPanel = new RoundedPanel(15, Color.WHITE);
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                new EmptyBorder(15, 15, 15, 15)
        ));

        detailsPanel.add(createInfoRow("👤 Paciente", "Manuel Rios", "Edad: 20"));
        detailsPanel.add(Box.createVerticalStrut(15));
        detailsPanel.add(createInfoRow("📅 Fecha y Hora", "17 may 2026 09:00", null));
        detailsPanel.add(Box.createVerticalStrut(15));
        detailsPanel.add(createInfoRow("⏱ Motivo de consulta", "Extracción de muela", null));

        innerContent.add(detailsPanel);
        innerContent.add(Box.createVerticalGlue());

        cardPanel.add(innerContent, BorderLayout.CENTER);
        return cardPanel;
    }

    private JPanel createInfoRow(String title, String mainText, String subText) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblTitle.setForeground(new Color(120, 120, 120));

        JLabel lblMain = new JLabel(mainText);
        lblMain.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblMain.setForeground(Color.BLACK);

        row.add(lblTitle);
        row.add(Box.createVerticalStrut(3));
        row.add(lblMain);

        if (subText != null) {
            JLabel lblSub = new JLabel(subText);
            lblSub.setFont(new Font("SansSerif", Font.PLAIN, 12));
            lblSub.setForeground(new Color(100, 100, 100));
            row.add(Box.createVerticalStrut(2));
            row.add(lblSub);
        }

        return row;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setOpaque(false);
        footerPanel.setBorder(new EmptyBorder(15, 0, 0, 0)); 

        JButton btnContinuar = new JButton("Continuar  ➔");
        btnContinuar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnContinuar.setBackground(new Color(23, 57, 227));
        btnContinuar.setForeground(Color.BLUE);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto visual plano con padding interno
        btnContinuar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(24, 119, 242), 1),
                new EmptyBorder(10, 25, 10, 25)
        ));

        // Evento de clic
        btnContinuar.addActionListener(e -> {
            showCard("details");
        });

        footerPanel.add(btnContinuar);
        return footerPanel;
    }

    class RoundedPanel extends JPanel {
        private Color backgroundColor;
        private int cornerRadius = 15;

        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        }
    }

    //CLASE SUB-VENTANA HISTORIAL DE RECETAS
    class HistorialDialog extends JDialog {
        public HistorialDialog(JFrame parent) {
            super(parent, true);
            setUndecorated(true);
            setSize(parent.getSize());
            setLocation(parent.getLocation());
            setBackground(new Color(0, 0, 0, 0));

            JPanel mainDialogPanel = new JPanel(new BorderLayout());
            mainDialogPanel.setOpaque(false);

            JPanel dimPanel = new JPanel();
            dimPanel.setBackground(new Color(0, 0, 0, 100));
            dimPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) { dispose(); }
            });

            JPanel sidebarPanel = new JPanel();
            sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
            sidebarPanel.setBackground(Color.WHITE);
            sidebarPanel.setPreferredSize(new Dimension(500, parent.getHeight()));
            sidebarPanel.setBorder(new EmptyBorder(30, 35, 30, 35));

            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setOpaque(false);

            JPanel titleGroup = new JPanel();
            titleGroup.setLayout(new BoxLayout(titleGroup, BoxLayout.Y_AXIS));
            titleGroup.setOpaque(false);

            JLabel titleLabel = new JLabel("⏱ Historial de Recetas");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

            JLabel subtitleLabel = new JLabel("Visualiza y gestiona todas las recetas médicas generadas");
            subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            subtitleLabel.setForeground(Color.GRAY);

            titleGroup.add(titleLabel);
            titleGroup.add(Box.createVerticalStrut(5));
            titleGroup.add(subtitleLabel);

            JButton btnCloseSide = new JButton("✕");
            btnCloseSide.setFont(new Font("SansSerif", Font.PLAIN, 18));
            btnCloseSide.setBorderPainted(false);
            btnCloseSide.setContentAreaFilled(false);
            btnCloseSide.setFocusPainted(false);
            btnCloseSide.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnCloseSide.addActionListener(e -> dispose());

            headerPanel.add(titleGroup, BorderLayout.WEST);
            headerPanel.add(btnCloseSide, BorderLayout.EAST);

            sidebarPanel.add(headerPanel);
            sidebarPanel.add(Box.createVerticalStrut(30));
            sidebarPanel.add(createHistoryCard());
            sidebarPanel.add(Box.createVerticalGlue());

            mainDialogPanel.add(dimPanel, BorderLayout.CENTER);
            mainDialogPanel.add(sidebarPanel, BorderLayout.EAST);
            add(mainDialogPanel);
        }

        private JPanel createHistoryCard() {
            RoundedPanel card = new RoundedPanel(15, Color.WHITE);
            card.setLayout(new BorderLayout());
            card.setMaximumSize(new Dimension(430, 230));
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(235, 235, 235), 1),
                    new EmptyBorder(18, 18, 18, 18)
            ));

            JPanel topRow = new JPanel(new BorderLayout());
            topRow.setOpaque(false);

            JLabel nameLabel = new JLabel("👤 Carlos Rodríguez");
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            nameLabel.setForeground(new Color(24, 119, 242));

            JButton btnDelete = new JButton("🗑");
            btnDelete.setFont(new Font("SansSerif", Font.PLAIN, 16));
            btnDelete.setForeground(new Color(220, 53, 69));
            btnDelete.setBorderPainted(false);
            btnDelete.setContentAreaFilled(false);
            btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));

            topRow.add(nameLabel, BorderLayout.WEST);
            topRow.add(btnDelete, BorderLayout.EAST);

            JPanel bodyRow = new JPanel();
            bodyRow.setLayout(new BoxLayout(bodyRow, BoxLayout.Y_AXIS));
            bodyRow.setOpaque(false);
            bodyRow.setBorder(new EmptyBorder(8, 0, 15, 0));

            JLabel dateLabel = new JLabel("📅 17 mar 2026, 22:36");
            dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            dateLabel.setForeground(Color.GRAY);

            JPanel dentistPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            dentistPanel.setOpaque(false);
            dentistPanel.add(new JLabel("Dentista: "));
            JLabel dName = new JLabel("Dra. Ana María Torres");
            dName.setFont(new Font("SansSerif", Font.BOLD, 13));
            dentistPanel.add(dName);

            JPanel treatmentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 4));
            treatmentPanel.setOpaque(false);
            treatmentPanel.add(new JLabel("Tratamientos:  "));
            RoundedPanel badge = new RoundedPanel(10, Color.WHITE);
            badge.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(210, 210, 210), 1),
                    new EmptyBorder(2, 8, 2, 8)
            ));
            badge.add(new JLabel("Extracción dental"));
            treatmentPanel.add(badge);

            JPanel medsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 4));
            medsPanel.setOpaque(false);
            medsPanel.add(new JLabel("Medicamentos: "));
            JLabel mCount = new JLabel("1");
            mCount.setFont(new Font("SansSerif", Font.BOLD, 13));
            medsPanel.add(mCount);

            bodyRow.add(dateLabel);
            bodyRow.add(Box.createVerticalStrut(8));
            bodyRow.add(dentistPanel);
            bodyRow.add(Box.createVerticalStrut(4));
            bodyRow.add(treatmentPanel);
            bodyRow.add(Box.createVerticalStrut(4));
            bodyRow.add(medsPanel);

            RoundedPanel btnContainer = new RoundedPanel(12, Color.WHITE);
            btnContainer.setLayout(new BorderLayout());
            btnContainer.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225), 1));
            
            JButton btnVerReceta = new JButton("📄  Ver Receta");
            btnVerReceta.setFont(new Font("SansSerif", Font.BOLD, 13));
            btnVerReceta.setContentAreaFilled(false);
            btnVerReceta.setBorderPainted(false);
            btnVerReceta.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnContainer.add(btnVerReceta, BorderLayout.CENTER);

            card.add(topRow, BorderLayout.NORTH);
            card.add(bodyRow, BorderLayout.CENTER);
            card.add(btnContainer, BorderLayout.SOUTH);

            return card;
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new CitaSeleccionada().setVisible(true);
        });
    }

}
