package Cursos.UI;

import Cursos.Modelo.GestionarCursos;
import Main.DatosGlobales;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel de interfaz gráfica para la gestión del catálogo de cursos (Altas y Bajas).
 * Permite agregar nuevos cursos y eliminarlos, visualizando los datos en una tabla.
 * <p>Complejidad Espacial General: O(1) (Referente a la lógica de control, sin contar componentes Swing).</p>
 */
public class PanelCursos extends JPanel {

    private GestionarCursos gestor;

    // Campos del formulario
    private JTextField txtClave;
    private JTextField txtNombre;
    private JTextField txtCupoMax;

    private JTable tablaCursos;
    private DefaultTableModel modeloTabla;
    private JLabel lblEstado;

    /**
     * Constructor del panel de cursos.
     * Inicializa los componentes y carga los datos existentes en la tabla.
     * <p>Complejidad Temporal: O(1) (Inicialización) + O(n) (Carga inicial de datos).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public PanelCursos() {
        this.gestor = DatosGlobales.cursos;
        initUI();
        cargarDatosTabla();
    }

    private void initUI() {
        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(245, 245, 245));

        // ==== ENCABEZADO ====
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        header.setBackground(new Color(45, 52, 54));
        JLabel titulo = new JLabel("GESTIÓN DE CURSOS");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.add(titulo);
        add(header, BorderLayout.NORTH);

        // ==== PANEL IZQUIERDO: FORMULARIO ====
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setPreferredSize(new Dimension(320, 0));
        panelIzquierdo.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelIzquierdo.setOpaque(false);

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del curso"));
        panelForm.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelForm.add(new JLabel("Clave:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtClave = new JTextField();
        panelForm.add(txtClave, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField();
        panelForm.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelForm.add(new JLabel("Cupo máximo:"), gbc);
        gbc.gridx = 1;
        txtCupoMax = new JTextField();
        panelForm.add(txtCupoMax, gbc);

        // --- BOTÓN AGREGAR (AZUL) ---
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        JButton btnAgregar = new JButton("Agregar Curso");
        configurarBoton(btnAgregar, new Color(9, 132, 227), new Color(9, 132, 227));
        panelForm.add(btnAgregar, gbc);

        // --- BOTÓN ELIMINAR (ROJO) ---
        gbc.gridy = 4;
        JButton btnEliminar = new JButton("Eliminar Curso");
        configurarBoton(btnEliminar, new Color(231, 76, 60), new Color(192, 57, 43));
        panelForm.add(btnEliminar, gbc);

        panelIzquierdo.add(panelForm, BorderLayout.NORTH);
        add(panelIzquierdo, BorderLayout.WEST);

        // ==== PANEL CENTRAL: TABLA ====
        modeloTabla = new DefaultTableModel(new Object[]{"Clave", "Nombre", "Cupo"}, 0);
        tablaCursos = new JTable(modeloTabla);
        tablaCursos.setRowHeight(25);
        tablaCursos.getTableHeader().setBackground(new Color(223, 230, 233));

        JScrollPane scroll = new JScrollPane(tablaCursos);
        scroll.setBorder(BorderFactory.createTitledBorder("Lista de cursos"));

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(new EmptyBorder(10, 0, 10, 10));
        panelCentral.setOpaque(false);
        panelCentral.add(scroll, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        // ==== BARRA DE ESTADO ====
        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEstado.setBackground(Color.WHITE);
        lblEstado = new JLabel(" Sistema listo.");
        panelEstado.add(lblEstado);
        add(panelEstado, BorderLayout.SOUTH);

        // ==== LISTENERS ====
        btnAgregar.addActionListener(e -> agregarCurso());
        btnEliminar.addActionListener(e -> eliminarCurso());
    }

    // --- MÉTODO PARA ESTILAR BOTONES (EL QUE ARREGLA NIMBUS) ---
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
     * Valida los datos y agrega un nuevo curso al sistema.
     * <p>Complejidad Temporal: O(n) (La inserción es O(1), pero se refresca la tabla completa O(n)).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private void agregarCurso() {
        try {
            String clave = txtClave.getText().trim();
            String nombre = txtNombre.getText().trim();
            String cupoStr = txtCupoMax.getText().trim();

            if (clave.isEmpty() || nombre.isEmpty() || cupoStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
                return;
            }
            int cupo = Integer.parseInt(cupoStr);
            gestor.agregarCurso(clave, nombre, cupo);
            cargarDatosTabla();
            lblEstado.setText(" Curso agregado: " + clave);
            txtClave.setText(""); txtNombre.setText(""); txtCupoMax.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El cupo debe ser un número entero");
        }
    }

    /**
     * Elimina el curso seleccionado en la tabla.
     * <p>Complejidad Temporal: O(n) (La eliminación es O(1), pero se refresca la tabla completa O(n)).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private void eliminarCurso() {
        int fila = tablaCursos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un curso en la tabla");
            return;
        }
        String clave = modeloTabla.getValueAt(fila, 0).toString();
        gestor.eliminarCurso(clave);
        cargarDatosTabla();
        lblEstado.setText(" Curso eliminado: " + clave);
    }

    /**
     * Recarga todos los datos de los cursos en la tabla visual.
     * <p>Complejidad Temporal: O(n), donde n es el número de cursos registrados (debe recorrer el hash map y llenar la tabla).</p>
     * <p>Complejidad Espacial: O(n) (Lista temporal para obtener valores).</p>
     */
    private void cargarDatosTabla() {
        modeloTabla.setRowCount(0);
        for(Cursos.Modelo.Curso c : gestor.obtenerTodosLosCursos()) {
            modeloTabla.addRow(new Object[]{c.getClave(), c.getNombre(), c.getCupoMaximo()});
        }
    }
}