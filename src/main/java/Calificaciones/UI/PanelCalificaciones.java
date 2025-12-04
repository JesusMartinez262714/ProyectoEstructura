package Calificaciones.UI;

import Calificaciones.Estructuras.*;
import Calificaciones.Modelo.*;
import Estudiantes.ControlEstudiantes;
import Main.DatosGlobales; // Importante para validar cursos

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel de interfaz gráfica para la gestión de calificaciones.
 * Permite encolar solicitudes de cambio de nota y procesarlas secuencialmente.
 * <p>Complejidad Espacial General: O(1) (Referente a la lógica de control, sin contar la memoria de los componentes gráficos de Swing)</p>
 */
public class PanelCalificaciones extends JPanel {

    // -- Estructuras de datos
    private final Cola<SolicitudCalificacion> colaSolicitudes;
    private final Pila<Accion> pilaDeshacer;

    // -- Componentes visuales
    private JTextField txtMatricula;
    private JTextField txtCurso;
    private JTextField txtCalificacion;

    // -- Componentes de la tabla
    private JTable tablaCola;
    private ModeloTablaCalificaciones modeloTabla;
    private JLabel lblEstado;

    /**
     * Constructor del panel.
     * Inicializa las estructuras de datos y la interfaz gráfica.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public PanelCalificaciones() {
        // Inicializamos las estructuras
        colaSolicitudes = new Cola<>();
        pilaDeshacer = new Pila<>();

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(245, 245, 245));

        // ==========================================
        // 1. ENCABEZADO
        // ==========================================
        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        panelHeader.setBackground(new Color(45, 52, 54));
        JLabel lblTitulo = new JLabel("MODULO DE CALIFICACIONES");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelHeader.add(lblTitulo);
        add(panelHeader, BorderLayout.NORTH);

        // ==========================================
        // 2. PANEL IZQUIERDO (Formulario y Botones)
        // ==========================================
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setPreferredSize(new Dimension(320, 0));
        panelIzquierdo.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelIzquierdo.setOpaque(false);

        // -- Formulario --
        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setBorder(BorderFactory.createTitledBorder("Formulario de solicitud"));
        formContainer.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Matricula
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        formContainer.add(new JLabel("Matricula:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtMatricula = new JTextField();
        formContainer.add(txtMatricula, gbc);

        // Curso (ID)
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        formContainer.add(new JLabel("ID Curso:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtCurso = new JTextField();
        formContainer.add(txtCurso, gbc);

        // Calificación
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        formContainer.add(new JLabel("Nueva Nota:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        txtCalificacion = new JTextField();
        formContainer.add(txtCalificacion, gbc);

        // Botón Encolar (Azul)
        JButton btnEncolar = new JButton("Encolar Solicitud");
        configurarBotonColor(btnEncolar, new Color(9, 132, 227), new Color(9, 132, 227));

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.ipady = 10;
        formContainer.add(btnEncolar, gbc);

        panelIzquierdo.add(formContainer, BorderLayout.NORTH);

        // -- Panel de Acciones (Procesar/Deshacer) --
        JPanel panelAcciones = new JPanel(new GridLayout(2, 1, 0, 15));
        panelAcciones.setOpaque(false);
        panelAcciones.setBorder(new EmptyBorder(20, 0, 0, 0));

        JButton btnProcesar = new JButton("Procesar Siguiente (FIFO)");
        // Color Verde intenso, borde verde oscuro
        configurarBotonColor(btnProcesar, new Color(39, 174, 96), new Color(30, 130, 76));

        JButton btnDeshacer = new JButton("Deshacer Ultima Accion (LIFO)");
        // Color Rojo intenso, borde rojo oscuro
        configurarBotonColor(btnDeshacer, new Color(231, 76, 60), new Color(192, 57, 43));

        panelAcciones.add(btnProcesar);
        panelAcciones.add(btnDeshacer);

        panelIzquierdo.add(panelAcciones, BorderLayout.CENTER);
        add(panelIzquierdo, BorderLayout.WEST);

        // ==========================================
        // 3. PANEL CENTRAL (Tabla)
        // ==========================================
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(new EmptyBorder(10, 0, 10, 10));
        panelCentral.setOpaque(false);

        modeloTabla = new ModeloTablaCalificaciones();
        tablaCola = new JTable(modeloTabla);
        tablaCola.setRowHeight(25);
        tablaCola.getTableHeader().setBackground(new Color(223, 230, 233));

        JScrollPane scrollTabla = new JScrollPane(tablaCola);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Cola de Solicitudes Pendientes"));
        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        // ==========================================
        // 4. BARRA DE ESTADO
        // ==========================================
        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEstado.setBackground(Color.WHITE);
        lblEstado = new JLabel(" Sistema listo.");
        panelEstado.add(lblEstado);
        add(panelEstado, BorderLayout.SOUTH);

        // -- Listeners --
        btnEncolar.addActionListener(e -> encolarSolicitud());
        btnProcesar.addActionListener(e -> procesarSiguienteSolicitud());
        btnDeshacer.addActionListener(e -> deshacerAccion());
    }

    // --- MÉTODO MÁGICO PARA ARREGLAR COLORES EN NIMBUS ---
    private void configurarBotonColor(JButton btn, Color bgColor, Color borderColor) {
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bgColor);

        // Trucos para anular el estilo de Nimbus:
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto Hover simple
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btn.setBackground(bgColor.brighter()); // Un poco más claro al pasar mouse
            }
            public void mouseExited(MouseEvent evt) {
                btn.setBackground(bgColor); // Volver al original
            }
        });
    }

    /**
     * Valida los datos del formulario y agrega una solicitud a la cola.
     * Realiza búsquedas en el Hash Map de cursos para validar existencia.
     * <p>Complejidad Temporal: O(1) (Búsqueda en Hash Map y Encolado son constantes)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private void encolarSolicitud() {
        try {
            String mat = txtMatricula.getText().trim();
            String idCurso = txtCurso.getText().trim();

            // 1. Validaciones básicas
            if (mat.isEmpty() || idCurso.isEmpty() || txtCalificacion.getText().isEmpty()) {
                mostrarError("Todos los campos son obligatorios");
                return;
            }

            // 2. VALIDACIÓN DEL ID DEL CURSO (Importante)
            if (DatosGlobales.cursos.obtenerCurso(idCurso) == null) {
                mostrarError("Error: No existe ningún curso con el ID '" + idCurso + "'");
                return;
            }

            // 3. Validación de la nota
            float cal = Float.parseFloat(txtCalificacion.getText());
            if (cal < 0 || cal > 100) {
                mostrarError("La calificación debe estar entre 0 y 100");
                return;
            }

            // 4. Encolar
            SolicitudCalificacion solicitud = new SolicitudCalificacion(mat, idCurso, cal);
            colaSolicitudes.encolar(solicitud);
            modeloTabla.agregarSolicitud(solicitud);

            lblEstado.setText(" Solicitud agregada para: " + mat + " en curso " + idCurso);
            limpiarCampos();

        } catch (NumberFormatException ex) {
            mostrarError("La calificación debe ser un número válido");
        }
    }

    /**
     * Procesa la solicitud al frente de la cola (FIFO).
     * Busca al estudiante en el BST y agrega la calificación.
     * <p>Complejidad Temporal: O(log n) por la búsqueda en el BST de estudiantes + O(k) por eliminar de la tabla visual (donde k son las solicitudes pendientes).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private void procesarSiguienteSolicitud() {
        if (colaSolicitudes.esVacia()) {
            JOptionPane.showMessageDialog(this, "No hay solicitudes pendientes");
            return;
        }

        SolicitudCalificacion solicitud = colaSolicitudes.desencolar();

        try {
            int matriculaInt = Integer.parseInt(solicitud.getMatriculaEstudiante());

            if (ControlEstudiantes.existe(matriculaInt)) {
                // Agregar nota
                ControlEstudiantes.agregarCalificacion(matriculaInt, solicitud.getNuevaCalificacion());

                // Guardar para Undo
                Accion accion = new Accion(
                        Accion.TipoAccion.CAMBIO_CALIFICACION,
                        null,
                        solicitud
                );
                ControlAcciones.registrarAccion(accion);

                modeloTabla.eliminarPrimeraSolicitud();
                lblEstado.setText("Procesado: Nota agregada a " + solicitud.getMatriculaEstudiante());
                JOptionPane.showMessageDialog(this, "Calificación registrada correctamente");

            } else {
                mostrarError("Error: El estudiante con matrícula " + matriculaInt + " no existe");
            }

        } catch (Exception e) {
            mostrarError("Error al procesar: " + e.getMessage());
        }
    }

    /**
     * Deshace la última acción registrada en la pila global.
     * <p>Complejidad Temporal: Variable según la acción (Generalmente O(log n) si implica búsqueda en BST).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private void deshacerAccion() {
        try {
            String resultado = ControlAcciones.deshacerUltima();
            lblEstado.setText(resultado);
            JOptionPane.showMessageDialog(this, resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Aviso: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtMatricula.setText("");
        txtCurso.setText("");
        txtCalificacion.setText("");
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Validación", JOptionPane.ERROR_MESSAGE);
    }
}