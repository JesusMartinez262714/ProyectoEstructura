package Estudiantes.UI.Paneles;

import Cursos.Modelo.AVL_Promedios;
import Cursos.Modelo.Curso;
import Cursos.Estructuras.NodoAVL;
import Estudiantes.ControlEstudiantes;
import Main.DatosGlobales;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Panel de reportes y utilidades del sistema.
 * Permite visualizar el ranking de estudiantes (usando un Árbol AVL) y gestionar la rotación de roles (Lista Circular).
 * <p>Complejidad Espacial General: O(1) (Componentes visuales estáticos).</p>
 */
public class PanelReportes extends JPanel {

    private JTextArea areaReporte;
    private JComboBox<String> comboCursos;
    private JLabel lblLiderActual;

    /**
     * Constructor del panel de reportes.
     * Inicializa la interfaz gráfica y carga los datos iniciales en los selectores.
     * <p>Complejidad Temporal: O(C), donde C es la cantidad de cursos (para llenar el combo).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public PanelReportes() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Reportes y Utilidades");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(1, 2, 20, 0));
        centro.setOpaque(false);

        // SECCIÓN AVL
        JPanel panelAVL = new JPanel(new BorderLayout(5, 5));
        panelAVL.setBorder(BorderFactory.createTitledBorder("Ranking por Promedio (AVL)"));
        panelAVL.setBackground(Color.WHITE);

        areaReporte = new JTextArea();
        areaReporte.setEditable(false);
        areaReporte.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JButton btnGenerarAVL = new JButton("Generar Reporte Detallado");
        configurarBoton(btnGenerarAVL, new Color(46, 204, 113), new Color(39, 174, 96));
        btnGenerarAVL.addActionListener(e -> generarReportePromedios());

        panelAVL.add(new JScrollPane(areaReporte), BorderLayout.CENTER);
        panelAVL.add(btnGenerarAVL, BorderLayout.SOUTH);

        // SECCIÓN ROLES
        JPanel panelRoles = new JPanel(new GridBagLayout());
        panelRoles.setBorder(BorderFactory.createTitledBorder("Rotación de Líderes (Lista Circular)"));
        panelRoles.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;

        panelRoles.add(new JLabel("Seleccione un Curso:"), gbc);
        gbc.gridy = 1;
        comboCursos = new JComboBox<>();
        comboCursos.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) { cargarCursosEnCombo(); }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {}
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {}
        });
        panelRoles.add(comboCursos, gbc);

        gbc.gridy = 2;
        JButton btnRotar = new JButton("Rotar Rol de Líder");
        configurarBoton(btnRotar, new Color(52, 152, 219), new Color(41, 128, 185));
        btnRotar.addActionListener(e -> rotarRol());
        panelRoles.add(btnRotar, gbc);

        gbc.gridy = 3;
        lblLiderActual = new JLabel("Líder actual: -");
        lblLiderActual.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblLiderActual.setHorizontalAlignment(SwingConstants.CENTER);
        panelRoles.add(lblLiderActual, gbc);

        centro.add(panelAVL);
        centro.add(panelRoles);
        add(centro, BorderLayout.CENTER);
        cargarCursosEnCombo();
    }

    // --- MÉTODO PARA ESTILAR BOTONES ---
    private void configurarBoton(JButton btn, Color bgColor, Color borderColor) {
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bgColor);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { btn.setBackground(bgColor.brighter()); }
            public void mouseExited(MouseEvent evt) { btn.setBackground(bgColor); }
        });
    }

    /**
     * Genera un reporte de estudiantes ordenados por promedio.
     * Crea un árbol AVL a partir de los datos existentes y realiza un recorrido In-Orden.
     * <p>Complejidad Temporal: O(N log N) (Recorrer BST e insertar en AVL).</p>
     * <p>Complejidad Espacial: O(N) (Crea una estructura AVL temporal con todos los estudiantes).</p>
     */
    private void generarReportePromedios() {
        try {
            AVL_Promedios avl = ControlEstudiantes.generarAVLPromedios();
            areaReporte.setText("");
            areaReporte.append("=== RANKING DE ESTUDIANTES (Orden Ascendente) ===\n");
            areaReporte.append("Formato: Promedio | Nombre | Historial de Notas\n");
            areaReporte.append("--------------------------------------------------\n");
            if (avl == null || avl.getRaiz() == null) { areaReporte.append("No hay estudiantes registrados o calificados."); return; }
            recorrerYMostrar(avl.getRaiz());
        } catch (Exception e) { areaReporte.setText("Error al generar reporte: " + e.getMessage()); }
    }

    /**
     * Recorrido recursivo In-Orden del árbol AVL para mostrar los datos en el área de texto.
     * @param nodo Nodo actual del AVL.
     * <p>Complejidad Temporal: O(N) (Visita todos los nodos).</p>
     * <p>Complejidad Espacial: O(log N) (Pila de recursión).</p>
     */
    private void recorrerYMostrar(NodoAVL nodo) {
        if (nodo == null) return;
        recorrerYMostrar(nodo.izq);
        String notasTexto = nodo.estudiante.getCalificaciones().toString();
        String linea = String.format("Prom: %5.2f  |  %-20s  |  %s\n", nodo.promedio, nodo.estudiante.getNombreCompleto(), notasTexto);
        areaReporte.append(linea);
        recorrerYMostrar(nodo.der);
    }

    /**
     * Carga las claves de los cursos existentes en el JComboBox.
     * <p>Complejidad Temporal: O(C), donde C es el número de cursos.</p>
     * <p>Complejidad Espacial: O(C) (Lista temporal).</p>
     */
    private void cargarCursosEnCombo() {
        Object seleccionado = comboCursos.getSelectedItem();
        comboCursos.removeAllItems();
        List<Curso> cursos = DatosGlobales.cursos.obtenerTodosLosCursos();
        for (Curso c : cursos) { comboCursos.addItem(c.getClave()); }
        if (seleccionado != null) { comboCursos.setSelectedItem(seleccionado); }
    }

    /**
     * Ejecuta la rotación de roles en el curso seleccionado.
     * Utiliza la lista circular del curso para cambiar el puntero del líder.
     * <p>Complejidad Temporal: O(1) (Operación de rotación de punteros).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private void rotarRol() {
        String idCurso = (String) comboCursos.getSelectedItem();
        if (idCurso == null) { JOptionPane.showMessageDialog(this, "Seleccione un curso primero."); return; }
        Curso c = DatosGlobales.cursos.obtenerCurso(idCurso);
        if (c == null) return;
        if (c.getRoles().estaVacia()) { lblLiderActual.setText("Líder: (Nadie)"); JOptionPane.showMessageDialog(this, "No hay estudiantes inscritos para rotar."); return; }
        Estudiantes.Modelo.Estudiante nuevoLider = c.rotarRol();
        if (nuevoLider != null) {
            lblLiderActual.setText("Líder: " + nuevoLider.getNombreCompleto());
            JOptionPane.showMessageDialog(this, "¡Rotación Exitosa!\nNUEVO LÍDER: " + nuevoLider.getNombreCompleto());
        }
    }
}