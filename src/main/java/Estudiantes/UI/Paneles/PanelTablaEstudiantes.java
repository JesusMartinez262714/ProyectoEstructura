package Estudiantes.UI.Paneles;

import javax.swing.*;
import java.awt.*;

import Estudiantes.UI.ModelosTabla.ModeloTablaEstudiantes;
import Estudiantes.Estructuras.BSTEstudiantes;
import Estudiantes.Modelo.Estudiante;
import Estudiantes.UI.VentanaEstudiantes;

public class PanelTablaEstudiantes extends JPanel {

    public JTable tabla;
    public ModeloTablaEstudiantes modelo;

    public PanelTablaEstudiantes(BSTEstudiantes arbol) {

        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        modelo = new ModeloTablaEstudiantes(arbol);
        tabla = new JTable(modelo);
        tabla.setRowHeight(25);
        tabla.setDefaultEditor(Object.class, null);

        JScrollPane scroll = new JScrollPane(tabla);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una fila.");
                return;
            }

            try {
                Estudiante est = arbol.obtenerEnPosicion(fila);
                arbol.eliminar(est.getMatricula());
                refrescar();
                JOptionPane.showMessageDialog(this, "Estudiante eliminado.");
                VentanaEstudiantes.panelFormulario.limpiarFormulario();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        JPanel abajo = new JPanel();
        abajo.add(btnEliminar);

        add(scroll, BorderLayout.CENTER);
        add(abajo, BorderLayout.SOUTH);

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {

                int fila = tabla.getSelectedRow();
                if (fila < 0) return;

                Estudiante est = arbol.obtenerEnPosicion(fila);
                VentanaEstudiantes.panelFormulario.cargarParaModificar(est);
            }
        });
    }

    public void refrescar() {
        modelo.fireTableDataChanged();
    }
}
