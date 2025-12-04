package Estudiantes.UI;

import javax.swing.*;
import java.awt.*;
import Estudiantes.Estructuras.BSTEstudiantes;
import Estudiantes.UI.Paneles.PanelFormulario;
import Estudiantes.UI.Paneles.PanelTablaEstudiantes;
import Main.DatosGlobales;

public class VentanaEstudiantes extends JFrame {

    public static BSTEstudiantes arbolito = DatosGlobales.estudiantes;
    public static PanelTablaEstudiantes panelTabla;
    public static PanelFormulario panelFormulario;

    public VentanaEstudiantes() {

        setTitle("Gestión de Estudiantes (BST)");
        setSize(1100, 600);
        setLocationRelativeTo(null);

        panelTabla = new PanelTablaEstudiantes(arbolito);
        panelFormulario = new PanelFormulario();

        JSplitPane split = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                panelTabla,
                panelFormulario
        );

        split.setDividerLocation(700);
        split.setResizeWeight(0.75);
        split.setOneTouchExpandable(true);

        add(split, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new VentanaEstudiantes().setVisible(true);
    }
}
