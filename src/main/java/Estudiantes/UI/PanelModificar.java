package Estudiantes.UI;

import javax.swing.*;
import java.awt.*;
import Estudiantes.Modelo.*;
import Estudiantes.Estructuras.*;

public class PanelModificar extends JPanel {

    private JTextField tBuscarMat;
    private JTextField tNombre;
    private JTextField tTelefono;
    private JTextField tCorreo;
    private JTextField tCalle;
    private JTextField tNumero;
    private JTextField tColonia;
    private JTextField tCiudad;

    private Estudiante estudianteActual;

    public PanelModificar() {

        setLayout(new BorderLayout());

        JPanel arriba = new JPanel(new GridLayout(2,2));
        arriba.add(new JLabel("Matrícula a buscar:"));
        tBuscarMat = new JTextField();
        arriba.add(tBuscarMat);

        JButton btnBuscar = new JButton("Buscar");
        arriba.add(btnBuscar);

        add(arriba, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(8,2,5,5));

        centro.add(new JLabel("Nombre:"));
        tNombre = new JTextField();
        centro.add(tNombre);

        centro.add(new JLabel("Teléfono:"));
        tTelefono = new JTextField();
        centro.add(tTelefono);

        centro.add(new JLabel("Correo:"));
        tCorreo = new JTextField();
        centro.add(tCorreo);

        centro.add(new JLabel("Calle:"));
        tCalle = new JTextField();
        centro.add(tCalle);

        centro.add(new JLabel("Número:"));
        tNumero = new JTextField();
        centro.add(tNumero);

        centro.add(new JLabel("Colonia:"));
        tColonia = new JTextField();
        centro.add(tColonia);

        centro.add(new JLabel("Ciudad:"));
        tCiudad = new JTextField();
        centro.add(tCiudad);

        add(centro, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar Cambios");
        add(btnGuardar, BorderLayout.SOUTH);

        // Buscar estudiante
        btnBuscar.addActionListener(e -> {
            try {
                int m = Integer.parseInt(tBuscarMat.getText());
                NodoBST nodo = VentanaEstudiantes.arbolito.buscarEstudiante(m);

                if (nodo == null) {
                    JOptionPane.showMessageDialog(this, "No encontrado.");
                    return;
                }

                estudianteActual = nodo.getEstudiante();

                tNombre.setText(estudianteActual.getNombreCompleto());
                tTelefono.setText(estudianteActual.getNumeroTelefono());
                tCorreo.setText(estudianteActual.getCorreo());
                tCalle.setText(estudianteActual.getDireccion().getCalle());
                tNumero.setText(estudianteActual.getDireccion().getNumero());
                tColonia.setText(estudianteActual.getDireccion().getColonia());
                tCiudad.setText(estudianteActual.getDireccion().getCiudad());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        // Guardar cambios
        btnGuardar.addActionListener(e -> {
            if (estudianteActual == null) {
                JOptionPane.showMessageDialog(this, "Primero busque un estudiante.");
                return;
            }

            estudianteActual.setNombreCompleto(tNombre.getText());
            estudianteActual.setNumeroTelefono(tTelefono.getText());
            estudianteActual.setCorreo(tCorreo.getText());

            Direccion dir = new Direccion(
                    tCalle.getText(),
                    tNumero.getText(),
                    tColonia.getText(),
                    tCiudad.getText()
            );

            estudianteActual.setDireccion(dir);

            JOptionPane.showMessageDialog(this, "Estudiante actualizado.");
        });
    }
}
