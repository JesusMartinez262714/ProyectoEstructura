package Estudiantes.UI.Paneles;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Calificaciones.Modelo.Accion;
import Estudiantes.Modelo.*;
import Estudiantes.UI.VentanaEstudiantes;

import static Calificaciones.Modelo.Accion.TipoAccion.REGISTRO_ESTUDIANTE;
import static Calificaciones.Modelo.ControlAcciones.registrarAccion;

/**
 * Panel que contiene el formulario para el registro y modificación de estudiantes.
 * Gestiona la captura de datos, validaciones visuales y la comunicación con el Árbol BST.
 * <p>Complejidad Espacial General: O(1) (Número fijo de componentes visuales).</p>
 */
public class PanelFormulario extends JPanel {

    // Componentes lógicos (Mismos nombres para no romper nada)
    private JTextField tMatricula, tNombre, tTel, tCorreo, tCalle, tNumero, tColonia, tCiudad;
    private Estudiante estudianteActual = null;

    /**
     * Constructor del panel de formulario.
     * Inicializa la interfaz gráfica.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public PanelFormulario() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(245, 245, 245));

        // ==========================================
        // 1. ENCABEZADO (Estilo consistente)
        // ==========================================
        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        panelHeader.setBackground(new Color(45, 52, 54));
        JLabel lblTitulo = new JLabel("GESTIÓN DE DATOS");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelHeader.add(lblTitulo);
        add(panelHeader, BorderLayout.NORTH);

        // ==========================================
        // 2. PANEL CENTRAL (Formulario)
        // ==========================================
        // Panel contenedor para dar márgenes
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelCentral.setOpaque(false);

        // Panel del formulario en sí (Fondo blanco)
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createTitledBorder("Información del Estudiante"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Inicialización de campos (Lógica original)
        tMatricula = new JTextField(15);
        tNombre = new JTextField(15);
        tTel = new JTextField(15);
        tCorreo = new JTextField(15);
        tCalle = new JTextField(15);
        tNumero = new JTextField(15);
        tColonia = new JTextField(15);
        tCiudad = new JTextField(15);

        soloNumeros(tMatricula);
        soloNumeros(tTel);
        soloNumeros(tNumero);

        // Agregando filas (Diseño mejorado)
        int fila = 0;
        agregarCampo(form, gbc, fila++, "Matrícula:", tMatricula);
        agregarCampo(form, gbc, fila++, "Nombre Completo:", tNombre);
        agregarCampo(form, gbc, fila++, "Teléfono:", tTel);
        agregarCampo(form, gbc, fila++, "Correo Electrónico:", tCorreo);

        // Separador visual para dirección
        JSeparator sep = new JSeparator();
        gbc.gridx = 0; gbc.gridy = fila++; gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 5, 10);
        form.add(sep, gbc);
        gbc.gridwidth = 1; gbc.insets = new Insets(10, 10, 10, 10); // Reset

        agregarCampo(form, gbc, fila++, "Calle:", tCalle);
        agregarCampo(form, gbc, fila++, "Número:", tNumero);
        agregarCampo(form, gbc, fila++, "Colonia:", tColonia);
        agregarCampo(form, gbc, fila++, "Ciudad:", tCiudad);

        // Agregamos el formulario al centro
        panelCentral.add(form, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        // ==========================================
        // 3. PANEL INFERIOR (Botón Guardar)
        // ==========================================
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        panelSur.setOpaque(false);

        JButton btnGuardar = new JButton("Guardar / Modificar Datos");
        btnGuardar.setPreferredSize(new Dimension(200, 45));

        // Aplicamos el estilo "Anti-Nimbus" (Azul bonito)
        configurarBoton(btnGuardar, new Color(9, 132, 227), new Color(41, 128, 185));

        // Acción lógica original
        btnGuardar.addActionListener(e -> procesar());

        panelSur.add(btnGuardar);
        add(panelSur, BorderLayout.SOUTH);
    }

    // Método auxiliar visual
    private void agregarCampo(JPanel panel, GridBagConstraints gbc, int fila, String texto, JComponent campo) {
        gbc.gridx = 0; gbc.gridy = fila;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panel.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(campo, gbc);
    }

    // --- MÉTODO PARA ESTILAR BOTONES (Fix Nimbus) ---
    private void configurarBoton(JButton btn, Color bgColor, Color borderColor) {
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
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

    // =======================================================
    //       LÓGICA ORIGINAL (INTACTA)
    // =======================================================

    private void soloNumeros(JTextField campo) {
        campo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override public void keyTyped(java.awt.event.KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) e.consume();
            }
        });
    }

    /**
     * Limpia los campos de texto y resetea el estado del formulario para un nuevo registro.
     * <p>Complejidad Temporal: O(1)</p>
     */
    public void limpiarFormulario() {
        estudianteActual = null;
        tMatricula.setEditable(true);
        tMatricula.setText(""); tNombre.setText(""); tTel.setText(""); tCorreo.setText("");
        tCalle.setText(""); tNumero.setText(""); tColonia.setText(""); tCiudad.setText("");
    }

    /**
     * Carga los datos de un estudiante existente en los campos para su edición.
     * @param est El estudiante a editar.
     * <p>Complejidad Temporal: O(1)</p>
     */
    public void cargarParaModificar(Estudiante est) {
        estudianteActual = est;
        tMatricula.setText(String.valueOf(est.getMatricula()));
        tMatricula.setEditable(false);
        tNombre.setText(est.getNombreCompleto());
        tTel.setText(est.getNumeroTelefono());
        tCorreo.setText(est.getCorreo());
        tCalle.setText(est.getDireccion().getCalle());
        tNumero.setText(est.getDireccion().getNumero());
        tColonia.setText(est.getDireccion().getColonia());
        tCiudad.setText(est.getDireccion().getCiudad());
    }

    /**
     * Procesa la acción del botón guardar.
     * Si hay un estudiante seleccionado, lo modifica. Si no, crea uno nuevo.
     * Inserta en el árbol BST y registra la acción en el historial.
     * <p>Complejidad Temporal: O(log n) (Inserción en BST) + O(n) (Refrescar tabla visual).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private void procesar() {
        try {
            if (!tCorreo.getText().contains("@")) { JOptionPane.showMessageDialog(this, "Correo inválido."); return; }
            Direccion dir = new Direccion(tCalle.getText(), tNumero.getText(), tColonia.getText(), tCiudad.getText());

            if (estudianteActual != null) {
                estudianteActual.setNombreCompleto(tNombre.getText());
                estudianteActual.setNumeroTelefono(tTel.getText());
                estudianteActual.setCorreo(tCorreo.getText());
                estudianteActual.setDireccion(dir);
                VentanaEstudiantes.panelTabla.refrescar();
                JOptionPane.showMessageDialog(this, "Estudiante modificado.");
                return;
            }

            Estudiante nuevo = new Estudiante();
            nuevo.setMatricula(Integer.parseInt(tMatricula.getText()));
            nuevo.setNombreCompleto(tNombre.getText());
            nuevo.setNumeroTelefono(tTel.getText());
            nuevo.setCorreo(tCorreo.getText());
            nuevo.setDireccion(dir);

            VentanaEstudiantes.arbolito.insertarEstudiante(nuevo);

            // Registrar acción para UNDO
            Accion accion = new Accion(
                    REGISTRO_ESTUDIANTE, null, nuevo);
            registrarAccion(accion);

            VentanaEstudiantes.panelTabla.refrescar();
            JOptionPane.showMessageDialog(this, "Estudiante insertado.");
            limpiarFormulario();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}