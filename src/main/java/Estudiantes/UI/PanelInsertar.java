package Estudiantes.UI;

import javax.swing.*;
import java.awt.*;
import Estudiantes.Modelo.*;
import Estudiantes.Estructuras.BSTEstudiantes;

public class PanelInsertar extends JPanel {

    public PanelInsertar() {
        setLayout(new GridLayout(8, 2, 5, 5));

        JTextField tMatricula = new JTextField();
        JTextField tNombre = new JTextField();
        JTextField tTel = new JTextField();
        JTextField tCorreo = new JTextField();
        JTextField tCalle = new JTextField();
        JTextField tNumero = new JTextField();
        JTextField tColonia = new JTextField();
        JTextField tCiudad = new JTextField();

        JButton btnGuardar = new JButton("Guardar");

        add(new JLabel("Matrícula:"));
        add(tMatricula);

        add(new JLabel("Nombre:"));
        add(tNombre);

        add(new JLabel("Teléfono:"));
        add(tTel);

        add(new JLabel("Correo:"));
        add(tCorreo);

        add(new JLabel("Calle:"));
        add(tCalle);

        add(new JLabel("Número:"));
        add(tNumero);

        add(new JLabel("Colonia:"));
        add(tColonia);

        add(new JLabel("Ciudad:"));
        add(tCiudad);

        add(btnGuardar);

        btnGuardar.addActionListener(e -> {
            try {
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

                JOptionPane.showMessageDialog(this, "Estudiante insertado.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
    }
}
