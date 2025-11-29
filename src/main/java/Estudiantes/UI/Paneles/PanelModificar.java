package Estudiantes.UI.Paneles;

import javax.swing.*;
import java.awt.*;
import Estudiantes.Modelo.Estudiante;
import Estudiantes.Modelo.Direccion;
import Estudiantes.UI.VentanaEstudiantes;

public class PanelModificar extends JPanel {

    private JTextField tMatricula, tNombre, tTel, tCorreo, tCalle, tNumero, tColonia, tCiudad;
    private Estudiante estudianteActual;

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
        tMatricula.setEditable(false);

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

    private void addRow(JPanel panel, GridBagConstraints gbc, int fila, String texto, JComponent campo) {
        gbc.gridx = 0; gbc.gridy = fila; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(texto), gbc);

        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(campo, gbc);
    }

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
