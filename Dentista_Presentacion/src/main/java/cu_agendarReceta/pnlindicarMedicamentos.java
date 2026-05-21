/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cu_agendarReceta;

import inicio.NotificacionFlotante;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 *
 * @author Jenifer Flores
 */

/**
 * Panel dinámico para indicar medicamentos.
 * Permite agregar múltiples prescripciones desglosadas y eliminarlas visualmente.
 */
public class pnlindicarMedicamentos extends JPanel {

    private final CitaSeleccionada controlador;
    private final JPanel contenedorListaMedicamentos; 
    private int contadorMedicamentos = 0; // Para llevar la cuenta (ej: Medicamento 1, Medicamento 2)

    public pnlindicarMedicamentos(CitaSeleccionada controlador) {
        this.controlador = controlador;
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(30, 40, 15, 40));

        // Contenedor principal vertical
        JPanel mainVerticalPanel = new JPanel();
        mainVerticalPanel.setLayout(new BoxLayout(mainVerticalPanel, BoxLayout.Y_AXIS));
        mainVerticalPanel.setOpaque(false);

        // TARJETA CONTENEDORA PRINCIPAL DE MEDICAMENTOS
        CitaSeleccionada.RoundedPanel cardRaizMedicamentos = controlador.new RoundedPanel(16, Color.WHITE);
        cardRaizMedicamentos.setLayout(new BorderLayout());
        cardRaizMedicamentos.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 235, 242), 1),
                new EmptyBorder(20, 25, 25, 25)
        ));

        // Título y Botón Agregar
        JPanel headerCard = new JPanel(new BorderLayout());
        headerCard.setOpaque(false);
        headerCard.setBorder(new EmptyBorder(0, 0, 15, 0)); 

        JLabel titleLabel = new JLabel("Medicamentos");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titleLabel.setForeground(new Color(40, 45, 55));
        headerCard.add(titleLabel, BorderLayout.WEST);

        JButton btnAgregar = new JButton("+  Agregar");
        btnAgregar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnAgregar.setBackground(Color.WHITE);
        btnAgregar.setForeground(Color.BLACK);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 220, 228), 1, true),
                new EmptyBorder(7, 18, 7, 18)
        ));
        
        // Agregar un nuevo bloque de medicamento 
        btnAgregar.addActionListener(e -> agregarNuevoBloqueMedicamento());

        headerCard.add(btnAgregar, BorderLayout.EAST);
        cardRaizMedicamentos.add(headerCard, BorderLayout.NORTH);

        // Usamos BoxLayout vertical para que los nuevos medicamentos se apilen hacia abajo
        contenedorListaMedicamentos = new JPanel();
        contenedorListaMedicamentos.setLayout(new BoxLayout(contenedorListaMedicamentos, BoxLayout.Y_AXIS));
        contenedorListaMedicamentos.setOpaque(false);

        // JScrollPane para scroll vertical si hay muchos medicamentos
        JScrollPane scrollPane = new JScrollPane(contenedorListaMedicamentos);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);

        cardRaizMedicamentos.add(scrollPane, BorderLayout.CENTER);
        mainVerticalPanel.add(cardRaizMedicamentos);
        add(mainVerticalPanel, BorderLayout.CENTER);

        JPanel footerActionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        footerActionPanel.setOpaque(false);

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
        btnRegresar.addActionListener(e -> controlador.showCard("details"));

        JButton btnContinuarG = new JButton("Continuar y Guardar");
        btnContinuarG.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnContinuarG.setBackground(new Color(23, 57, 227));
        btnContinuarG.setForeground(Color.WHITE);
        btnContinuarG.setFocusPainted(false);
        btnContinuarG.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnContinuarG.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(40, 167, 69), 1),
                new EmptyBorder(10, 25, 10, 25)
        ));
        btnContinuarG.addActionListener(e -> {
            // Obtener el JFrame principal para centrar la alerta flotante
            JFrame ventanaPrincipal = (JFrame) SwingUtilities.getWindowAncestor(this);
            
            // Evaluamos si el formulario de medicamentos es válido
            if (!validarMedicamentos()) {
                NotificacionFlotante.mostrarError(ventanaPrincipal, "Por favor, agregue al menos un medicamento y llene todos sus campos.");
            } else {
                controlador.showCard("indicaciones");
            }
        });
        footerActionPanel.add(btnRegresar);
        footerActionPanel.add(btnContinuarG);
        add(footerActionPanel, BorderLayout.SOUTH);

        // Agregamos el primer medicamento por defecto al iniciar
        agregarNuevoBloqueMedicamento();
    }

    /**
     * Recrea visualmente el desglosado de un medicamento individual (como Medicamento 1, Medicamento 2).
     * Incluye la lógica para auto-eliminarse.
     */
    private void agregarNuevoBloqueMedicamento() {
        contadorMedicamentos++;
        String nombreBloque = "Medicamento " + contadorMedicamentos + " *";

        
        CitaSeleccionada.RoundedPanel subCard = controlador.new RoundedPanel(12, new Color(250, 251, 253));
        subCard.setLayout(new BoxLayout(subCard, BoxLayout.Y_AXIS));
        subCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(235, 238, 242), 1),
                new EmptyBorder(15, 20, 20, 20)
        ));
        subCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260)); 
        
        // Espaciador superior entre bloques
        if (contadorMedicamentos > 1) {
             contenedorListaMedicamentos.add(Box.createVerticalStrut(20));
        }

        // Fila de Encabezado de Sub-sección (Nombre del medicamento y Papelera)
        JPanel subHeader = new JPanel(new BorderLayout());
        subHeader.setOpaque(false);
        subHeader.setBorder(new EmptyBorder(0, 0, 15, 0));

        JLabel lblNomMed = new JLabel(nombreBloque);
        lblNomMed.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblNomMed.setForeground(new Color(50, 55, 65));
        subHeader.add(lblNomMed, BorderLayout.WEST);

        // Botón Papelera (🗑) estilizado en rojo para eliminar
        JButton btnEliminar = new JButton("🗑");
        btnEliminar.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnEliminar.setForeground(new Color(220, 53, 69)); // Rojo basura
        btnEliminar.setOpaque(false);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnEliminar.addActionListener(e -> {
            int index = getComponentIndex(contenedorListaMedicamentos, subCard);
            contenedorListaMedicamentos.remove(subCard);
            if (index > 0) {
                 contenedorListaMedicamentos.remove(index - 1);
            }
            
            actualizarNumeracionDinamica(); 
            
            contenedorListaMedicamentos.revalidate();
            contenedorListaMedicamentos.repaint();
        });

        subHeader.add(btnEliminar, BorderLayout.EAST);
        subCard.add(subHeader);

        // Fila 1: Nombre del medicamento full-width
        subCard.add(createInputField("", "Ej: Ibuprofeno 400mg"));
        subCard.add(Box.createVerticalStrut(15));

        // Fila 2: Dosis y Frecuencia en paralelo (Grid 1x2)
        JPanel rowDosisFrecuencia = new JPanel(new GridLayout(1, 2, 20, 0));
        rowDosisFrecuencia.setOpaque(false);
        rowDosisFrecuencia.add(createInputField("Dosis *", "Ej: 1 tableta"));
        rowDosisFrecuencia.add(createInputField("Frecuencia *", "Ej: Cada 8 horas"));
        subCard.add(rowDosisFrecuencia);
        subCard.add(Box.createVerticalStrut(15));

        // Fila 3: Duración (alineada a la izquierda simulando el layout de la imagen)
        JPanel rowDuracion = new JPanel(new GridBagLayout());
        rowDuracion.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.49; // Simula la mitad del espacio de la fila
        rowDuracion.add(createInputField("Duración *", "Ej: 5 días"), gbc);
        
        gbc.weightx = 0.51; // Espacio vacío para empujar a la izquierda
        rowDuracion.add(Box.createGlue(), gbc);
        subCard.add(rowDuracion);

        contenedorListaMedicamentos.add(subCard);
        
        actualizarNumeracionDinamica(); 
        
        contenedorListaMedicamentos.revalidate();
        contenedorListaMedicamentos.repaint();
    }


    /**
     * Helper para fabricar dinámicamente las cajas de texto customizadas con placeholders modernos.
     */
    private JPanel createInputField(String title, String placeholder) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);

        if (title != null && !title.isEmpty()) {
            JLabel label = new JLabel(title);
            label.setFont(new Font("SansSerif", Font.BOLD, 12));
            label.setForeground(new Color(60, 60, 60));
            label.setBorder(new EmptyBorder(0, 0, 6, 0));
            container.add(label);
        }

        // Caja contenedora del campo con bordes redondeados y fondo gris plano (F5F6FA)
        JPanel txtBoxWrapper = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(245, 246, 250)); // Fondo gris suave de los inputs de tu imagen
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
            }
        };
        txtBoxWrapper.setOpaque(false);
        txtBoxWrapper.setBorder(new EmptyBorder(4, 12, 4, 12));
        txtBoxWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        txtBoxWrapper.setPreferredSize(new Dimension(100, 38));

        JTextField field = new JTextField();
        field.setText(placeholder);
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.setForeground(Color.GRAY); // Color de placeholder inicial
        field.setBorder(null);
        field.setOpaque(false);

        // Comportamiento básico de focus para limpiar/restaurar el placeholder simulado
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(new Color(50, 55, 65));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                }
            }
        });

        txtBoxWrapper.add(field, BorderLayout.CENTER);
        container.add(txtBoxWrapper);
        return container;
    }
    
    /**
     * Helper para obtener el índice de un componente dentro de un contenedor.
     */
    private int getComponentIndex(Container container, Component component) {
        Component[] components = container.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] == component) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Valida que existan medicamentos en la lista y que ningún campo obligatorio esté vacío.
     */
    private boolean validarMedicamentos() {
        java.util.List<JTextField> camposDeTexto = new java.util.ArrayList<>();
        buscarTextFieldsOcultos(contenedorListaMedicamentos, camposDeTexto);
        
        if (camposDeTexto.isEmpty()) {
            return false; 
        }
        
        // Caso 2: Verificar que ninguno esté vacío o se haya quedado con su placeholder
        for (JTextField field : camposDeTexto) {
            String placeholder = (String) field.getClientProperty("placeholder");
            String textoUsuario = field.getText().trim();
            
            if (textoUsuario.isEmpty() || textoUsuario.equals(placeholder)) {
                return false; // Validación fallida: hay un campo sin llenar
            }
        }
        return true; // Todo está perfecto
    }

    /**
     * Método de apoyo que recorre los paneles buscando de forma inteligente 
     * todos los JTextFields sin importar cuántos medicamentos agregue el usuario.
     */
    private void buscarTextFieldsOcultos(Container container, java.util.List<JTextField> lista) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JTextField) {
                lista.add((JTextField) comp);
            } else if (comp instanceof Container) {
                buscarTextFieldsOcultos((Container) comp, lista);
            }
        }
    }
    
    /**
     * Recorre todos los bloques de medicamentos activos y vuelve a numerarlos 
     * secuencialmente desde el 1. También sincroniza el contador global.
     */
    private void actualizarNumeracionDinamica() {
        int numeroReal = 1;
        for (Component comp : contenedorListaMedicamentos.getComponents()) {
            // Filtramos únicamente los paneles de medicamentos (ignorando los separadores invisibles)
            if (comp instanceof JPanel) { 
                JLabel lblTitulo = buscarLabelTitulo((Container) comp);
                if (lblTitulo != null) {
                    lblTitulo.setText("Medicamento " + numeroReal + " *");
                    numeroReal++;
                }
            }
        }
        // Sincronizamos el contador general con la cantidad real actual
        contadorMedicamentos = numeroReal - 1;
    }

    /**
     * Busca de forma recursiva la etiqueta que contiene el título del medicamento.
     */
    private JLabel buscarLabelTitulo(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JLabel && "tituloMedicamento".equals(c.getName())) {
                return (JLabel) c;
            }
            if (c instanceof Container) {
                JLabel hallado = buscarLabelTitulo((Container) c);
                if (hallado != null) return hallado;
            }
        }
        return null;
    }
}