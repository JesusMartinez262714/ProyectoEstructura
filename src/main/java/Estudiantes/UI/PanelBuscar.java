package Estudiantes.UI;

import javax.swing.*;
import java.awt.*;
import Estudiantes.Modelo.NodoBST;

public class PanelBuscar extends JPanel {

    public PanelBuscar() {

        setLayout(new BorderLayout());

        JPanel arriba = new JPanel(new GridLayout(1,3,5,5));
        JTextField tMatricula = new JTextField();
        JButton btnBuscar = new JButton("Buscar");

        arriba.add(new JLabel("Matrícula:"));
        arriba.add(tMatricula);
        arriba.add(btnBuscar);

        add(arriba, BorderLayout.NORTH);

        JTextArea resultado = new JTextArea();
        resultado.setEditable(false);
        resultado.setFont(new Font("Monospaced", Font.PLAIN, 14));

        add(new JScrollPane(resultado), BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> {
            try {
                int m = Integer.parseInt(tMatricula.getText());

                // CORRECTO: llamamos al árbol del módulo estudiantes
                NodoBST nodo = VentanaEstudiantes.arbolito.buscarEstudiante(m);

                if (nodo == null) {
                    resultado.setText("No encontrado.");
                } else {
                    resultado.setText(nodo.getEstudiante().toString());
                }

            } catch (Exception ex) {
                resultado.setText("Error: " + ex.getMessage());
            }
        });
    }
}
