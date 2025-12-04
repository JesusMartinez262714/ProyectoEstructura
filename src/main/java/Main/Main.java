package Main;

import javax.swing.*;

/**
 * Clase principal que sirve como punto de entrada de la aplicación.
 * Configura la apariencia (Look and Feel) y lanza la ventana principal.
 * <p>Complejidad Espacial General: O(1) (Punto de entrada, la memoria de datos reside en DatosGlobales).</p>
 */
public class Main {

    /**
     * Método principal (main).
     * @param args Argumentos de línea de comandos.
     * <p>Complejidad Temporal: O(1) (Configuración inicial y lanzamiento de hilo de eventos).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Descomentarear para preecargar datos
       // PrecargaDatos.cargar();


        // Arrancar la ventana
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}