package Estudiantes.UI.Paneles;

import javax.swing.*;
import java.awt.*;
import Estudiantes.Modelo.*;
import Estudiantes.UI.VentanaEstudiantes;

/**
 * Panel que proporciona el formulario para registrar nuevos estudiantes.
 * Realiza validaciones de entrada y comunica la inserción al árbol BST global.
 * <p>Complejidad Espacial General: O(1) (Número fijo de componentes visuales).</p>
 */
public class PanelInsertar extends JPanel {

    /**
     * Constructor del panel.
     * Inicializa los componentes, el diseño (GridBagLayout) y los listeners de los botones.
     * <p>Complejidad Temporal: O(1) (Inicialización de componentes).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public PanelInsertar() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(245, 245, 245));

        JLabel titulo = new JLabel("Registrar Estudiante");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);

        JTextField tMatricula = new JTextField(12);
        JTextField tNombre = new JTextField(12);
        JTextField tTel = new JTextField(12);
        JTextField tCorreo = new JTextField(12);
        JTextField tCalle = new JTextField(12);
        JTextField tNumero = new JTextField(12);
        JTextField tColonia = new JTextField(12);
        JTextField tCiudad = new JTextField(12);

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

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setPreferredSize(new Dimension(120, 30));

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(245, 245, 245));
        panelBoton.add(btnGuardar);

        add(panelBoton, BorderLayout.SOUTH);

        // -- Listener del botón Guardar --
        // Complejidad Temporal: O(log n) por la inserción en BST + O(n) por el refresco de tabla.
        btnGuardar.addActionListener(e -> {

            try {

                if (tMatricula.getText().trim().isEmpty() ||
                        tNombre.getText().trim().isEmpty() ||
                        tTel.getText().trim().isEmpty() ||
                        tCorreo.getText().trim().isEmpty() ||
                        tCalle.getText().trim().isEmpty() ||
                        tNumero.getText().trim().isEmpty() ||
                        tColonia.getText().trim().isEmpty() ||
                        tCiudad.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                    return;
                }

                if (!tMatricula.getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "La matrícula debe ser numérica.");
                    return;
                }

                if (!tTel.getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "El teléfono debe ser numérico.");
                    return;
                }

                if (!tNumero.getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(this, "El número debe ser numérico.");
                    return;
                }

                if (!tCorreo.getText().contains("@")) {
                    JOptionPane.showMessageDialog(this, "El correo debe contener '@'.");
                    return;
                }

                Estudiante est = new Estudiante();
                est.setMatricula(Integer.parseInt(tMatricula.getText()));
                est.setNombreCompleto(tNombre.getText());
                est.setNumeroTelefono(tTel.getText());
                est.setCorreo(tCorreo.getText());

                Direccion dir = new Direccion(
                        tCalle.getText(),
                        tNumero.getText(),
                        tColonia.getText(),
                        tCiudad.getText()
                );

                est.setDireccion(dir);

                VentanaEstudiantes.arbolito.insertarEstudiante(est);

                VentanaEstudiantes.panelTabla.refrescar();

                JOptionPane.showMessageDialog(this, "Estudiante insertado.");

                tMatricula.setText("");
                tNombre.setText("");
                tTel.setText("");
                tCorreo.setText("");
                tCalle.setText("");
                tNumero.setText("");
                tColonia.setText("");
                tCiudad.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
    }

    /**
     * Método auxiliar para agregar una fila (etiqueta + campo de texto) al formulario.
     * * @param panel Panel contenedor.
     * @param gbc Restricciones del GridBagLayout.
     * @param fila Número de fila actual.
     * @param texto Texto de la etiqueta.
     * @param campo Componente de campo de texto.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private void addRow(JPanel panel, GridBagConstraints gbc, int fila, String texto, JComponent campo) {

        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(texto), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(campo, gbc);
    }
}