package Estudiantes.UI.Paneles;

import javax.swing.*;
import java.awt.*;
import Estudiantes.Modelo.Estudiante;
import Estudiantes.Modelo.Direccion;
import Estudiantes.UI.VentanaEstudiantes;

/**
 * Panel dedicado a la modificación de datos de un estudiante existente.
 * Permite editar la información y actualizarla en el modelo de datos.
 * <p>Complejidad Espacial General: O(1) (Número fijo de componentes visuales).</p>
 */
public class PanelModificar extends JPanel {

    private JTextField tMatricula, tNombre, tTel, tCorreo, tCalle, tNumero, tColonia, tCiudad;
    private Estudiante estudianteActual;

    /**
     * Constructor del panel de modificación.
     * Inicializa los componentes gráficos y los listeners.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public PanelModificar() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Modificar Estudiante");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        tMatricula = new JTextField(12);
        tMatricula.setEditable(false); // La matrícula no se debe editar, es la clave primaria

        tNombre = new JTextField(12);
        tTel = new JTextField(12);
        tCorreo = new JTextField(12);
        tCalle = new JTextField(12);
        tNumero = new JTextField(12);
        tColonia = new JTextField(12);
        tCiudad = new JTextField(12);

        soloNumeros(tTel);
        soloNumeros(tNumero);

        int fila = 0;
        addRow(form, gbc, fila++, "Matrícula:", tMatricula);
        addRow(form, gbc, fila++, "Nombre:", tNombre);
        addRow(form, gbc, fila++, "Teléfono:", tTel);
        addRow(form, gbc, fila++, "Correo:", tCorreo);
        addRow(form, gbc, fila++, "Calle:", tCalle);
        addRow(form, gbc, fila++, "Número:", tNumero);
        addRow(form, gbc, fila++, "Colonia:", tColonia);
        addRow(form, gbc, fila++, "Ciudad:", tCiudad);

        add(form, BorderLayout.CENTER);

        JButton btnGuardarCambios = new JButton("Guardar cambios");
        btnGuardarCambios.setPreferredSize(new Dimension(150, 30));
        add(btnGuardarCambios, BorderLayout.SOUTH);

        btnGuardarCambios.addActionListener(e -> guardarCambios());
    }

    /**
     * Método auxiliar para agregar componentes al GridBagLayout.
     * <p>Complejidad Temporal: O(1)</p>
     */
    private void addRow(JPanel panel, GridBagConstraints gbc, int fila, String texto, JComponent campo) {
        gbc.gridx = 0; gbc.gridy = fila; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(texto), gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(campo, gbc);
    }

    /**
     * Carga los datos de un objeto Estudiante en los campos de texto del formulario.
     * @param est El estudiante a editar.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void cargarEstudiante(Estudiante est) {

        estudianteActual = est;

        tMatricula.setText(String.valueOf(est.getMatricula()));
        tNombre.setText(est.getNombreCompleto());
        tTel.setText(est.getNumeroTelefono());
        tCorreo.setText(est.getCorreo());

        if (est.getDireccion() != null) {
            tCalle.setText(est.getDireccion().getCalle());
            tNumero.setText(est.getDireccion().getNumero());
            tColonia.setText(est.getDireccion().getColonia());
            tCiudad.setText(est.getDireccion().getCiudad());
        }
    }

    /**
     * Valida los campos y actualiza la información del objeto Estudiante actual.
     * Refresca la tabla principal para reflejar los cambios.
     * <p>Complejidad Temporal: O(n) (Debido al refresco de la tabla visual que recorre los nodos).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private void guardarCambios() {
        try {
            if (estudianteActual == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un estudiante en la tabla primero.");
                return;
            }

            if (tNombre.getText().trim().isEmpty() ||
                    tTel.getText().trim().isEmpty() ||
                    tCorreo.getText().trim().isEmpty() ||
                    tCalle.getText().trim().isEmpty() ||
                    tNumero.getText().trim().isEmpty() ||
                    tColonia.getText().trim().isEmpty() ||
                    tCiudad.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                return;
            }

            if (!tCorreo.getText().contains("@")) {
                JOptionPane.showMessageDialog(this, "El correo debe contener '@'.");
                return;
            }

            Direccion dir = new Direccion(
                    tCalle.getText(),
                    tNumero.getText(),
                    tColonia.getText(),
                    tCiudad.getText()
            );

            estudianteActual.setNombreCompleto(tNombre.getText());
            estudianteActual.setNumeroTelefono(tTel.getText());
            estudianteActual.setCorreo(tCorreo.getText());
            estudianteActual.setDireccion(dir);

            VentanaEstudiantes.panelTabla.refrescar();

            JOptionPane.showMessageDialog(this, "Cambios guardados.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    /**
     * Agrega un KeyListener para restringir la entrada a solo números.
     * @param campo El JTextField a restringir.
     * <p>Complejidad Temporal: O(1)</p>
     */
    private void soloNumeros(JTextField campo) {
        campo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }
}