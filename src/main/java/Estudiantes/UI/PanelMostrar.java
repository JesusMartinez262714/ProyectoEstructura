package Estudiantes.UI;

import javax.swing.*;
import java.awt.*;
import Estudiantes.Modelo.NodoBST;
import Estudiantes.Estructuras.BSTEstudiantes;

public class PanelMostrar extends JPanel {

    private JTextArea area;

    public PanelMostrar() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Listado de estudiantes (InOrden)", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));

        add(new JScrollPane(area), BorderLayout.CENTER);

        JButton btnMostrar = new JButton("Actualizar listado");
        add(btnMostrar, BorderLayout.SOUTH);

        btnMostrar.addActionListener(e -> mostrarEstudiantes());
    }

    private void mostrarEstudiantes() {
        area.setText(""); // limpiar

        VentanaEstudiantes.arbolito.inOrden(nodo -> {
            area.append(nodo.getEstudiante().toString());
            area.append("\n--------------------------\n");
        });
    }
}
