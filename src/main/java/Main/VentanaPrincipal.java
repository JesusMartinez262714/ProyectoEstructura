package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Imports de tus módulos
import Estudiantes.UI.VentanaEstudiantes;
import Cursos.UI.PanelCursos;
import Cursos.UI.Panel_Inscripciones;
import Calificaciones.UI.PanelCalificaciones;

public class VentanaPrincipal extends JFrame {

    private final Color COLOR_FONDO = new Color(245, 245, 250);
    private final Color COLOR_HEADER = new Color(41, 128, 185);
    private final Color COLOR_BOTON = new Color(255, 255, 255);
    private final Color COLOR_TEXTO = new Color(50, 50, 50);

    public VentanaPrincipal() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Sistema Académico Institucional");
        setSize(1000, 600);

        // --- AQUÍ ESTÁ EL CAMBIO ---
        // Al presionar la X, se cierra la aplicación y termina el proceso.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_FONDO);
    }

    private void inicializarComponentes() {
        // --- 1. ENCABEZADO (HEADER) ---
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(COLOR_HEADER);
        headerPanel.setPreferredSize(new Dimension(1000, 80));
        headerPanel.setLayout(new GridBagLayout());

        JLabel lblTitulo = new JLabel("Panel de Control Académico");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        headerPanel.add(lblTitulo);

        add(headerPanel, BorderLayout.NORTH);

        // --- 2. PANEL CENTRAL (GRID DE BOTONES) ---
        JPanel mainContainer = new JPanel(new GridBagLayout());
        mainContainer.setBackground(COLOR_FONDO);

        JPanel gridPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        gridPanel.setBackground(COLOR_FONDO);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Creamos los botones
        JButton btnEstudiantes = crearBotonEstilizado("Gestión de Estudiantes", "👨‍🎓");
        JButton btnCursos = crearBotonEstilizado("Gestión de Cursos", "📚");
        JButton btnInscripciones = crearBotonEstilizado("Inscripciones", "📝");
        JButton btnCalificaciones = crearBotonEstilizado("Calificaciones", "📊");

        // Asignamos las acciones
        // Nota: Asegúrate de que las ventanas secundarias usen DISPOSE_ON_CLOSE para no cerrar la app principal
        btnEstudiantes.addActionListener(e -> new VentanaEstudiantes().setVisible(true));

        // Usamos los gestores globales definidos en Main.DatosGlobales (como acordamos antes)
        // O si volviste a la versión anterior, instancias aquí los paneles.
        btnCursos.addActionListener(e -> mostrarPanel(new PanelCursos(), "Gestión de Cursos"));
        btnInscripciones.addActionListener(e -> mostrarPanel(new Panel_Inscripciones(), "Inscripciones"));
        btnCalificaciones.addActionListener(e -> mostrarPanel(new PanelCalificaciones(), "Calificaciones"));

        gridPanel.add(btnEstudiantes);
        gridPanel.add(btnCursos);
        gridPanel.add(btnInscripciones);
        gridPanel.add(btnCalificaciones);

        mainContainer.add(gridPanel);
        add(mainContainer, BorderLayout.CENTER);


    }

    private JButton crearBotonEstilizado(String texto, String icono) {
        JButton boton = new JButton("<html><center><span style='font-size:20px'>" + icono + "</span><br>" + texto + "</center></html>");

        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setForeground(COLOR_TEXTO);
        boton.setBackground(COLOR_BOTON);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(15, 30, 15, 30)
        ));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(250, 120));

        // Efecto visual al pasar el mouse
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

    private void mostrarPanel(JPanel panel, String titulo) {
        JFrame f = new JFrame(titulo);
        f.setSize(900, 600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra la sub-ventana
        f.add(panel);
        f.setVisible(true);
    }


}