package Estudiantes.UI.Paneles;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import Estudiantes.UI.ModelosTabla.ModeloTablaEstudiantes;
import Estudiantes.Estructuras.BSTEstudiantes;
import Estudiantes.Modelo.Estudiante;
import Estudiantes.UI.VentanaEstudiantes;
import Main.DatosGlobales;
import Cursos.Modelo.Curso;

/**
 * Panel que muestra la lista de estudiantes registrados en una tabla.
 * Permite eliminar estudiantes y visualizar su Kardex académico completo.
 * <p>Complejidad Espacial General: O(1) (Componentes visuales fijos).</p>
 */
public class PanelTablaEstudiantes extends JPanel {

    public JTable tabla;
    public ModeloTablaEstudiantes modelo;
    private BSTEstudiantes arbol; // Referencia local para uso en botones

    /**
     * Constructor del panel de tabla.
     * Configura la tabla, los botones y sus eventos.
     * @param arbol Referencia al árbol BST de estudiantes.
     * <p>Complejidad Temporal: O(1) inicialización, pero O(N) al cargar el modelo (contar nodos).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public PanelTablaEstudiantes(BSTEstudiantes arbol) {
        this.arbol = arbol;

        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modelo = new ModeloTablaEstudiantes(arbol);
        tabla = new JTable(modelo);
        tabla.setRowHeight(25);
        tabla.getTableHeader().setBackground(new Color(220, 220, 220));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabla.setDefaultEditor(Object.class, null); // Tabla de solo lectura

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(Color.WHITE);

        // --- PANEL DE BOTONES ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setOpaque(false);

        JButton btnEliminar = new JButton("Eliminar Estudiante");
        styleButton(btnEliminar, new Color(231, 76, 60)); // Rojo

        JButton btnKardex = new JButton("Ver Kardex Completo");
        styleButton(btnKardex, new Color(52, 152, 219)); // Azul

        panelBotones.add(btnEliminar);
        panelBotones.add(btnKardex);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // --- EVENTOS ---

        // 1. Eliminar
        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un estudiante de la tabla.");
                return;
            }

            try {
                // Obtenemos al estudiante (el modelo usa el índice in-order)
                Estudiante est = arbol.obtenerEnPosicion(fila);

                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Seguro que desea eliminar a " + est.getNombreCompleto() + "?",
                        "Confirmar Baja", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    arbol.eliminar(est.getMatricula());
                    refrescar();
                    JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente.");
                    VentanaEstudiantes.panelFormulario.limpiarFormulario();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // 2. Ver Kardex (Historial Mejorado)
        btnKardex.addActionListener(e -> mostrarKardex());

        // 3. Selección en tabla para llenar formulario
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    Estudiante est = arbol.obtenerEnPosicion(fila);
                    VentanaEstudiantes.panelFormulario.cargarParaModificar(est);
                }
            }
        });
    }

    /**
     * Genera y muestra un reporte detallado (Kardex) del estudiante seleccionado.
     * Busca en todos los cursos para ver en cuáles está inscrito y muestra sus calificaciones.
     * <p>Complejidad Temporal: O(N) [Buscar estudiante] + O(C * M) [Buscar cursos], donde C=Total Cursos, M=Alumnos por curso.</p>
     * <p>Complejidad Espacial: O(1) (Buffer de texto temporal).</p>
     */
    private void mostrarKardex() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un estudiante para ver su historial.");
            return;
        }

        Estudiante est = arbol.obtenerEnPosicion(fila);
        if (est == null) return;

        // Construimos el reporte
        StringBuilder sb = new StringBuilder();
        sb.append("================================================\n");
        sb.append("           KARDEX DEL ESTUDIANTE\n");
        sb.append("================================================\n\n");

        sb.append(" MATRÍCULA : ").append(est.getMatricula()).append("\n");
        sb.append(" NOMBRE    : ").append(est.getNombreCompleto()).append("\n");
        sb.append(" CONTACTO  : ").append(est.getCorreo()).append("\n\n");

        sb.append("------------------------------------------------\n");
        sb.append(" CURSOS INSCRITOS ACTUALMENTE:\n");
        sb.append("------------------------------------------------\n");

        // Lógica para buscar cursos (ya que Estudiante no guarda esa lista directamente)
        boolean tieneCursos = false;
        List<Curso> todosLosCursos = DatosGlobales.cursos.obtenerTodosLosCursos();

        for (Curso c : todosLosCursos) {
            // Recorremos la lista de inscritos de cada curso
            for (Estudiante inscrito : c.getInscritos()) {
                if (inscrito.getMatricula() == est.getMatricula()) {
                    sb.append(String.format(" • [%-6s] %s\n", c.getClave(), c.getNombre()));
                    tieneCursos = true;
                    break;
                }
            }
        }

        if (!tieneCursos) {
            sb.append(" (El estudiante no está inscrito en ningún curso)\n");
        }

        sb.append("\n------------------------------------------------\n");
        sb.append(" HISTORIAL DE CALIFICACIONES (GLOBAL):\n");
        sb.append("------------------------------------------------\n");

        // Mostramos las calificaciones del arreglo
        String notasStr = est.getCalificaciones().toString();
        // Limpiamos los corchetes para que se vea mejor
        if (notasStr.equals("[]")) {
            sb.append(" (Sin calificaciones registradas)\n");
        } else {
            sb.append(" Notas: ").append(notasStr).append("\n");
            sb.append(String.format(" PROMEDIO GENERAL: %.2f\n", est.getPromedio()));
        }

        sb.append("\n================================================");

        // Mostramos en un Área de Texto con Scroll
        JTextArea area = new JTextArea(sb.toString());
        area.setFont(new Font("Monospaced", Font.PLAIN, 13)); // Fuente tipo consola para alinear
        area.setEditable(false);
        area.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollKardex = new JScrollPane(area);
        scrollKardex.setPreferredSize(new Dimension(450, 400));

        JOptionPane.showMessageDialog(this, scrollKardex, "Expediente Académico", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Notifica a la tabla que los datos han cambiado para que se repinte.
     * <p>Complejidad Temporal: O(N) (Recalcula filas y obtiene valores visibles).</p>
     */
    public void refrescar() {
        modelo.fireTableDataChanged();
    }

    // Método auxiliar para botones bonitos
    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}