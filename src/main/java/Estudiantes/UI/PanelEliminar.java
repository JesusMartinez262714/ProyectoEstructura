package Estudiantes.UI;

import javax.swing.*;
import java.awt.*;

public class PanelEliminar extends JPanel {

    public PanelEliminar() {

        setLayout(new GridLayout(3,1));

        JLabel lbl = new JLabel("Matrícula a eliminar:");
        JTextField tMat = new JTextField();
        JButton btnEliminar = new JButton("Eliminar");

        add(lbl);
        add(tMat);
        add(btnEliminar);

        btnEliminar.addActionListener(e -> {
            try {
                int m = Integer.parseInt(tMat.getText());

                // CUANDO IMPLEMENTES delete:
                // VentanaTabsEstudiantes.arbol.eliminar(m);

                JOptionPane.showMessageDialog(this, "Función eliminar se conectará más tarde.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}
