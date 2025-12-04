package Estudiantes.UI;

import javax.swing.*;
import java.awt.*;
import Estudiantes.Estructuras.BSTEstudiantes;
import Estudiantes.UI.Paneles.PanelFormulario;
import Estudiantes.UI.Paneles.PanelTablaEstudiantes;
import Main.DatosGlobales;

/**
 * Ventana principal para la gestión del módulo de estudiantes.
 * Integra la visualización de datos (Tabla) y la manipulación de los mismos (Formulario)
 * utilizando un divisor de pantalla (JSplitPane).
 * <p>Complejidad Espacial General: O(1) (Contenedor de componentes visuales).</p>
 */
public class VentanaEstudiantes extends JFrame {

    public static BSTEstudiantes arbolito = DatosGlobales.estudiantes;
    public static PanelTablaEstudiantes panelTabla;
    public static PanelFormulario panelFormulario;

    /**
     * Constructor de la ventana.
     * Configura el tamaño, título y la disposición de los paneles internos.
     * <p>Complejidad Temporal: O(1) (Inicialización de componentes Swing).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
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