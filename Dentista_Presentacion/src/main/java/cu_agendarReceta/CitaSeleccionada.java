/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_agendarReceta;

import DAOs.CitaDAO;
import DAOs.DentistaDAO;
import DAOs.PacienteDAO;
import config.MongoClientProvider;
import entidades.Cita;
import entidades.Dentista;
import entidades.Paciente;
import inicio.MainFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import objetosnegocio.Excepciones.BOException;
import objetosnegocio.dentista_objetosnegocio.CitaService;
import objetosnegocio.dentista_objetosnegocio.DentistaService;
import objetosnegocio.dentista_objetosnegocio.ICitaService;
import objetosnegocio.dentista_objetosnegocio.IDentistaService;
import objetosnegocio.dentista_objetosnegocio.IPacienteService;
import objetosnegocio.dentista_objetosnegocio.PacienteService;

/**
 *
 * @author Jenifer Flores
 */
public class CitaSeleccionada extends JFrame {
   
    private Point initialClick;
    private JPanel cardsContainer;
    private CardLayout cardLayout;
    private final ICitaService citaService;
    private final IDentistaService dServ;
    private final IPacienteService pServ;

    private List<Cita> citas; 
    
    
    public CitaSeleccionada() throws BOException {
        citaService = new CitaService(new CitaDAO());
        dServ = new DentistaService(new DentistaDAO());
        pServ = new PacienteService(new PacienteDAO());
        java.util.List<Dentista> dentistas = dServ.listar(100);
        this.citas = citaService.obtenerPorDentistaYFecha(dentistas.get(0).getFolio(), 
                LocalDate.ofInstant(Instant.now(), 
                        ZoneId.systemDefault()));
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
        titleBar.setBackground(new Color(92, 225, 230)); 
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
        return headerPanel;
    }

    // TARJETA PRINCIPAL (Citas)
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

    // Crear arreglo de Strings con datos de las citas
    String[] citasArray = new String[citas.size()];

    for (int i = 0; i < citas.size(); i++) {
        Cita cita = citas.get(i);

        // Aquí concatenamos paciente, dentista, fecha, tratamiento y motivo
        citasArray[i] = "👤 Paciente: " + cita.getPaciente_id() +
                        " | 🦷 Dentista: " + cita.getDentista_id() +
                        " | 📅 Fecha: " + cita.getFecha() +
                        " | 💊 Tratamiento: " + cita.getTratamiento() +
                        " | ⏱ Motivo: " + cita.getMotivo();
    }

    // Crear el combo con el arreglo
    JComboBox<String> citaCombo = new JComboBox<>(citasArray);
    citaCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
    citaCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
    citaCombo.setBackground(new Color(245, 245, 245));


    innerContent.add(comboLabel);
    innerContent.add(Box.createVerticalStrut(5));
    innerContent.add(citaCombo);
    innerContent.add(Box.createVerticalStrut(20));

    // Panel de detalles dinámico
    RoundedPanel detailsPanel = new RoundedPanel(15, Color.WHITE);
    detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
    detailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    detailsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            new EmptyBorder(15, 15, 15, 15)
    ));

    JLabel lblPaciente = new JLabel();
    JLabel lblFecha = new JLabel();
    JLabel lblMotivo = new JLabel();

    // Método para actualizar detalles
    Runnable actualizarDetalles = () -> {
        int index = citaCombo.getSelectedIndex();
        if (index >= 0) {
            Cita citaSeleccionada = citas.get(index);
            lblPaciente.setText("👤 Paciente: " + citaSeleccionada.getPaciente_id() +
                                " (Edad: " + citaSeleccionada.getPaciente_id() + ")");
            lblFecha.setText("📅 Fecha y Hora: " + citaSeleccionada.getFecha().toString());
            lblMotivo.setText("⏱ Motivo de consulta: " + citaSeleccionada.getMotivo());
        }
    };

    // Inicializar con la primera cita
    if (!citas.isEmpty()) {
        citaCombo.setSelectedIndex(0);
        actualizarDetalles.run();
    }

    // Listener para cambios en el combo
    citaCombo.addActionListener(e -> actualizarDetalles.run());

    // Agregar labels al panel
    detailsPanel.add(lblPaciente);
    detailsPanel.add(Box.createVerticalStrut(15));
    detailsPanel.add(lblFecha);
    detailsPanel.add(Box.createVerticalStrut(15));
    detailsPanel.add(lblMotivo);

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
       JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    footerPanel.setOpaque(false);
    footerPanel.setBorder(new EmptyBorder(15, 0, 0, 0)); 

    // Botón Regresar
    JButton btnRegresar = new JButton("✕  Regresar");
    btnRegresar.setFont(new Font("SansSerif", Font.BOLD, 13));
    btnRegresar.setBackground(Color.WHITE);
    btnRegresar.setForeground(new Color(23, 57, 227));
    btnRegresar.setFocusPainted(false);
    btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnRegresar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(215, 220, 228), 1, true),
            new EmptyBorder(10, 22, 10, 22)
    ));
    btnRegresar.addActionListener(e -> volverAInicio());

    // Botón Continuar
    JButton btnContinuar = new JButton("Continuar  ➔");
    btnContinuar.setFont(new Font("SansSerif", Font.BOLD, 14));
    btnContinuar.setBackground(new Color(23, 57, 227));
    btnContinuar.setForeground(Color.WHITE);
    btnContinuar.setFocusPainted(false);
    btnContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btnContinuar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(24, 119, 242), 1),
            new EmptyBorder(10, 25, 10, 25)
    ));
    btnContinuar.addActionListener(e -> showCard("details"));

    // Agregar ambos botones al mismo panel
    footerPanel.add(btnRegresar);
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
    public static void main(String[] args) {
        MongoClientProvider.INSTANCE.init();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            try {
                new CitaSeleccionada().setVisible(true);
            } catch (BOException ex) {
                System.getLogger(CitaSeleccionada.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
    }
    
    protected void volverAInicio(){
        try {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            this.dispose();
        } catch (BOException ex) {
            System.getLogger(CitaSeleccionada.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

}
