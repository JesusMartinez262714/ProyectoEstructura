package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Imports de todos tus módulos
import Estudiantes.UI.VentanaEstudiantes;
import Cursos.UI.PanelCursos;
import Cursos.UI.Panel_Inscripciones;
import Calificaciones.UI.PanelCalificaciones;
import Estudiantes.UI.Paneles.PanelReportes; // Importante: El nuevo panel

/**
 * Ventana principal (Main Dashboard) del Sistema Académico.
 * Actúa como punto de navegación central para acceder a los diferentes módulos (Estudiantes, Cursos, etc.).
 * <p>Complejidad Espacial General: O(1) (Contenedor de referencias a los paneles persistentes).</p>
 */
public class VentanaPrincipal extends JFrame {


    private final Color COLOR_FONDO = new Color(245, 245, 250);
    private final Color COLOR_HEADER = new Color(41, 128, 185);
    private final Color COLOR_BOTON = new Color(255, 255, 255);
    private final Color COLOR_TEXTO = new Color(50, 50, 50);

    // --- 1. INSTANCIAS PERSISTENTES (Memoria) ---
    // Se declaran aquí para que vivan todo el tiempo que la app esté abierta
    private VentanaEstudiantes ventanaEstudiantes;
    private PanelCursos panelCursos;
    private Panel_Inscripciones panelInscripciones;
    private PanelCalificaciones panelCalificaciones;
    private PanelReportes panelReportes; // El nuevo panel

    /**
     * Constructor principal.
     * Inicializa las instancias de todos los paneles de módulos (inicialización única)
     * y configura la estructura de la ventana.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public VentanaPrincipal() {

        ventanaEstudiantes = new VentanaEstudiantes();
        panelCursos = new PanelCursos();
        panelInscripciones = new Panel_Inscripciones();
        panelCalificaciones = new PanelCalificaciones();
        panelReportes = new PanelReportes();

        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Sistema Académico Institucional");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_FONDO);
    }

    private void inicializarComponentes() {

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(COLOR_HEADER);
        headerPanel.setPreferredSize(new Dimension(1000, 90));
        headerPanel.setLayout(new GridBagLayout());

        JLabel lblTitulo = new JLabel("Panel de Control Académico");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        headerPanel.add(lblTitulo);

        add(headerPanel, BorderLayout.NORTH);


        JPanel mainContainer = new JPanel(new GridBagLayout());
        mainContainer.setBackground(COLOR_FONDO);


        JPanel gridPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        gridPanel.setBackground(COLOR_FONDO);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnEstudiantes = crearBotonEstilizado("Gestión de Estudiantes", "👨‍🎓");
        JButton btnCursos = crearBotonEstilizado("Gestión de Cursos", "📚");
        JButton btnInscripciones = crearBotonEstilizado("Inscripciones", "📝");
        JButton btnCalificaciones = crearBotonEstilizado("Calificaciones", "📊");
        JButton btnReportes = crearBotonEstilizado("Reportes y Roles", "📋"); // Nuevo botón



        // Estudiantes es un JFrame independiente
        btnEstudiantes.addActionListener(e -> {
            ventanaEstudiantes.setVisible(true);
            ventanaEstudiantes.toFront();
        });

        // Los demás son Paneles que metemos en una ventana temporal
        btnCursos.addActionListener(e -> mostrarPanel(panelCursos, "Gestión de Cursos"));
        btnInscripciones.addActionListener(e -> mostrarPanel(panelInscripciones, "Inscripciones"));
        btnCalificaciones.addActionListener(e -> mostrarPanel(panelCalificaciones, "Calificaciones"));
        btnReportes.addActionListener(e -> mostrarPanel(panelReportes, "Reportes y Utilidades"));


        gridPanel.add(btnEstudiantes);
        gridPanel.add(btnCursos);
        gridPanel.add(btnInscripciones);
        gridPanel.add(btnCalificaciones);
        gridPanel.add(btnReportes);

        mainContainer.add(gridPanel);
        add(mainContainer, BorderLayout.CENTER);

        // --- FOOTER ---
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(COLOR_FONDO);
        JLabel lblCopyright = new JLabel("Sistema Académico v1.0 - Estructuras de Datos");
        lblCopyright.setForeground(Color.GRAY);
        lblCopyright.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        footerPanel.add(lblCopyright);

        add(footerPanel, BorderLayout.SOUTH);
    }

    /**
     * Crea un botón visualmente estilizado con ícono y texto usando HTML.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private JButton crearBotonEstilizado(String texto, String icono) {
        // Usamos HTML para que el ícono quede arriba del texto
        JButton boton = new JButton("<html><center><span style='font-size:24px'>" + icono + "</span><br><br>" + texto + "</center></html>");

        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setForeground(COLOR_TEXTO);
        boton.setBackground(COLOR_BOTON);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(240, 130));

        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton.setBackground(new Color(230, 240, 250));
            }
            public void mouseExited(MouseEvent evt) {
                boton.setBackground(COLOR_BOTON);
            }
        });

        return boton;
    }

    /**
     * Método para mostrar los JPanels en una ventana flotante reutilizable.
     * @param panel El JPanel del módulo a mostrar.
     * @param titulo El título de la ventana flotante.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1) (Crea una nueva JFrame para la visualización).</p>
     */
    private void mostrarPanel(JPanel panel, String titulo) {
        JFrame f = new JFrame(titulo);
        f.setSize(950, 650);
        f.setLocationRelativeTo(null);

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        f.add(panel);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}