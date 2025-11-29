package Estudiantes.UI;

import javax.swing.*;
import java.awt.*;
import Estudiantes.Estructuras.BSTEstudiantes;

public class VentanaEstudiantes extends JFrame {

    public static BSTEstudiantes arbolito = new BSTEstudiantes();

    public VentanaEstudiantes() {

        setTitle("Gestión de Estudiantes (BST)");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Insertar",   new PanelInsertar());
        tabs.addTab("Buscar",     new PanelBuscar());
        tabs.addTab("Mostrar",    new PanelMostrar());
        tabs.addTab("Modificar",  new PanelModificar());
        tabs.addTab("Eliminar",   new PanelEliminar());

        add(tabs, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new VentanaEstudiantes().setVisible(true);
    }
}
