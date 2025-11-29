package Estudiantes.UI.Paneles;

import javax.swing.*;
import java.awt.*;
import Estudiantes.Modelo.*;
import Estudiantes.UI.VentanaEstudiantes;

public class PanelFormulario extends JPanel {

    private JTextField tMatricula, tNombre, tTel, tCorreo, tCalle, tNumero, tColonia, tCiudad;

    private Estudiante estudianteActual = null;

    public PanelFormulario() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(245, 245, 245));

        JLabel titulo = new JLabel("Formulario Estudiante");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        tMatricula = new JTextField(12);
        tNombre = new JTextField(12);
        tTel = new JTextField(12);
        tCorreo = new JTextField(12);
        tCalle = new JTextField(12);
        tNumero = new JTextField(12);
        tColonia = new JTextField(12);
        tCiudad = new JTextField(12);

        soloNumeros(tMatricula);
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

        JButton btnGuardar = new JButton("Guardar / Modificar");
        btnGuardar.setPreferredSize(new Dimension(160, 30));
        add(btnGuardar, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> procesar());
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int fila, String texto, JComponent campo) {

        gbc.gridx = 0; gbc.gridy = fila;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel(texto), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(campo, gbc);
    }

    private void soloNumeros(JTextField campo) {
        campo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override public void keyTyped(java.awt.event.KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) e.consume();
            }
        });
    }

    public void limpiarFormulario() {
        estudianteActual = null;

        tMatricula.setEditable(true);
        tMatricula.setText("");
        tNombre.setText("");
        tTel.setText("");
        tCorreo.setText("");
        tCalle.setText("");
        tNumero.setText("");
        tColonia.setText("");
        tCiudad.setText("");
    }

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

    private void procesar() {
        try {

            if (!tCorreo.getText().contains("@")) {
                JOptionPane.showMessageDialog(this, "Correo inválido.");
                return;
            }

            Direccion dir = new Direccion(
                    tCalle.getText(),
                    tNumero.getText(),
                    tColonia.getText(),
                    tCiudad.getText()
            );

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
            VentanaEstudiantes.panelTabla.refrescar();

            JOptionPane.showMessageDialog(this, "Estudiante insertado.");
            limpiarFormulario();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
