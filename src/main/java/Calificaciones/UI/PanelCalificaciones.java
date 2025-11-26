package Calificaciones.UI;

import Calificaciones.Estructuras.*;
import Calificaciones.Modelo.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelCalificaciones extends JPanel {

    // -- Estructuras de datos
    private Cola<SolicitudCalificacion> colaSolicitudes; // -- Requisito 4.1 (calificaciones) y 8 del documento
    private Pila<Accion> pilaDeshacer;                   // -- Requisito 9 del documento

    // -- Componentes visuales
    private JTextField txtMatricula;
    private JTextField txtCurso;
    private JTextField txtCalificacion;

    // -- Componentes de la tabla
    private JTable tablaCola;
    private ModeloTablaCalificaciones modeloTabla; // -- Clase personalizada para manejar la vista
    private JLabel lblEstado; // -- Barra de estado inferior

    /**
     * Constructor del panel.
     * Inicializa las estructuras de datos (Cola y Pila), configura el diseño (Layout),
     * crea los componentes visuales del formulario y la tabla, y asigna los listeners
     * a los botones.
     */
    public PanelCalificaciones() {
        // -- Inicializamos las estructuras de datos
        colaSolicitudes = new Cola<>();
        pilaDeshacer = new Pila<>();

        // -- Configuracion del disenio del panel (BorderLayout para evitar conflictos)
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(245, 245, 245)); // -- Fondo gris suave

        // -- ENCABEZADO (Titulo del modulo)
        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        panelHeader.setBackground(new Color(45, 52, 54));
        JLabel lblTitulo = new JLabel("MODULO DE CALIFICACIONES");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelHeader.add(lblTitulo);

        add(panelHeader, BorderLayout.NORTH);

        // -- PANEL IZQUIERDO (Formulario)
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setPreferredSize(new Dimension(320, 0));
        panelIzquierdo.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelIzquierdo.setOpaque(false);

        // -- Contenedor del formulario
        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setBorder(BorderFactory.createTitledBorder("Formulario de solicitud"));
        formContainer.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // -- Campo matricula
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        formContainer.add(new JLabel("Matricula:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtMatricula = new JTextField();
        formContainer.add(txtMatricula, gbc);

        // -- Campo curso
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formContainer.add(new JLabel("ID Curso:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtCurso = new JTextField();
        formContainer.add(txtCurso, gbc);

        // -- Campo calificacion
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        formContainer.add(new JLabel("Nueva Nota:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtCalificacion = new JTextField();
        formContainer.add(txtCalificacion, gbc);

        // -- Boton encolar
        JButton btnEncolar = new JButton("Encolar Solicitud");
        btnEncolar.setBackground(new Color(9, 132, 227)); // -- Azul institucional
        btnEncolar.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.ipady = 10;
        formContainer.add(btnEncolar, gbc);

        // -- Agregamos formulario al panel izquierdo
        panelIzquierdo.add(formContainer, BorderLayout.NORTH);

        // -- Panel de botones de accion (Procesar/Deshacer)
        JPanel panelAcciones = new JPanel(new GridLayout(2, 1, 0, 10));
        panelAcciones.setOpaque(false);
        panelAcciones.setBorder(new EmptyBorder(20, 0, 0, 0));

        JButton btnProcesar = new JButton("Procesar Siguiente (FIFO)");
        btnProcesar.setBackground(new Color(0, 184, 148)); // -- Verde
        btnProcesar.setForeground(Color.WHITE);

        JButton btnDeshacer = new JButton("Deshacer Ultima Accion (LIFO)");
        btnDeshacer.setBackground(new Color(214, 48, 49)); // -- Rojo
        btnDeshacer.setForeground(Color.WHITE);

        panelAcciones.add(btnProcesar);
        panelAcciones.add(btnDeshacer);

        panelIzquierdo.add(panelAcciones, BorderLayout.CENTER);
        add(panelIzquierdo, BorderLayout.WEST);

        // -- PANEL CENTRAL (Tabla de visualizacion)
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(new EmptyBorder(10, 0, 10, 10));
        panelCentral.setOpaque(false);

        // -- Configuracion de la tabla con el modelo personalizado
        modeloTabla = new ModeloTablaCalificaciones();
        tablaCola = new JTable(modeloTabla);
        tablaCola.setRowHeight(25);
        tablaCola.getTableHeader().setBackground(new Color(223, 230, 233));

        JScrollPane scrollTabla = new JScrollPane(tablaCola);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Cola de Solicitudes Pendientes"));
        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        // -- BARRA DE ESTADO
        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEstado.setBackground(Color.WHITE);
        lblEstado = new JLabel(" Sistema listo.");
        panelEstado.add(lblEstado);
        add(panelEstado, BorderLayout.SOUTH);

        // -- Listeners

        // -- Boton Encolar
        btnEncolar.addActionListener(e -> {
            try {
                String mat = txtMatricula.getText();
                String cur = txtCurso.getText();

                if (mat.isEmpty() || cur.isEmpty() || txtCalificacion.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
                    return;
                }

                float cal = Float.parseFloat(txtCalificacion.getText());

                // -- Creamos el objeto para poder meterlo a la cola
                SolicitudCalificacion solicitud = new SolicitudCalificacion(mat, cur, cal);
                colaSolicitudes.encolar(solicitud);

                // -- Actualizamos la vista (Tabla)
                modeloTabla.agregarSolicitud(solicitud);

                lblEstado.setText(" Solicitud agregada: " + mat);
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La calificacion debe ser un numero valido");
            }
        });

        // -- Boton Procesar
        btnProcesar.addActionListener(e -> procesarSiguienteSolicitud());

        // -- Boton Deshacer
        btnDeshacer.addActionListener(e -> deshacerUltimaAccion());
    }

    /**
     * Procesa la siguiente solicitud de calificación en la cola (FIFO).
     * Extrae la solicitud más antigua, simula su procesamiento y guarda una
     * acción de "historial" en la pila para permitir deshacer la operación posteriormente.
     */
    private void procesarSiguienteSolicitud() {
        if (colaSolicitudes.esVacia()) {
            JOptionPane.showMessageDialog(this, "No hay solicitudes pendientes");
            return;
        }

        // -- Sacamos de la cola (FIFO)
        SolicitudCalificacion solicitud = colaSolicitudes.desencolar();

        /** -- CALIFICACION SIMULADA (CAMBIAR LUEGO POR FAVOR)
         * Aqui deberiamos buscar al estudiante y obtener su calificacion actual
         * */
        float calificacionAnterior = 100.0f;

        Accion accionHistorial = new Accion(
                Accion.TipoAccion.CAMBIO_CALIFICACION,
                calificacionAnterior,
                solicitud
        );

        // -- Guardamos en la pila(deshacemos despues)
        pilaDeshacer.push(accionHistorial);

        // -- Actualizamos la pantalla (quitamos de la tabla)
        modeloTabla.eliminarPrimeraSolicitud();

        lblEstado.setText(" Procesado: " + solicitud.getMatriculaEstudiante());
        JOptionPane.showMessageDialog(this, "Solicitud procesada correctamente\n(Se guardo una copia en la pila)");
    }

    /**
     * Deshace la última acción realizada utilizando la pila de historial (LIFO).
     * Recupera el estado anterior de la acción más reciente y revierte los cambios
     * (por ejemplo, restaurando una calificación anterior).
     */
    private void deshacerUltimaAccion() {
        if (pilaDeshacer.esVacia()) {
            JOptionPane.showMessageDialog(this, "No hay acciones para deshacer");
            return;
        }

        // -- Sacamos de la pila (LIFO)
        Accion accionDeshacer = pilaDeshacer.pop();

        // -- Tomamos decision dependiendo del ENUM
        switch (accionDeshacer.getTipo()) {
            case CAMBIO_CALIFICACION:
                // -- Recuperamos los datos originales
                float calificacionOriginal = (float) accionDeshacer.getDatoAnterior();
                SolicitudCalificacion sol = (SolicitudCalificacion) accionDeshacer.getDatoNuevo();

                /** -- LOGICA DE REVERSION (CAMBIAR LUEGO POR FAVOR)
                 * X.buscar(sol.getMatricula()).setCalificacion(calificacionOriginal);
                 */

                lblEstado.setText(" Deshacer: Nota de " + sol.getMatriculaEstudiante() + " restaurada.");
                JOptionPane.showMessageDialog(this, "Accion deshecha: Nota revertida a " + calificacionOriginal);
                break;

            case REGISTRO_ESTUDIANTE:
                JOptionPane.showMessageDialog(this, "Registro deshecho (Pendiente)");
                break;
            case INSCRIPCION_CURSO:
                JOptionPane.showMessageDialog(this, "Inscripcion deshecha (Pendiente)");
                break;
        }
    }

    /**
     * Limpia los campos de texto del formulario de solicitud.
     * Se utiliza después de encolar exitosamente una solicitud.
     */
    private void limpiarCampos() {
        txtMatricula.setText("");
        txtCurso.setText("");
        txtCalificacion.setText("");
    }

    // -- Metodo main para pruebas unitarias

    /**
     * Método principal para ejecutar el panel de manera aislada.
     * Útil para pruebas unitarias de la interfaz gráfica sin iniciar toda la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar Nimbus: " + e.getMessage());
        }

        JFrame ventanaPrueba = new JFrame("Prueba Modulo Calificaciones (Bryan)");
        ventanaPrueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrueba.setSize(950, 600);
        ventanaPrueba.setLocationRelativeTo(null);

        PanelCalificaciones miPanel = new PanelCalificaciones();
        ventanaPrueba.add(miPanel);

        ventanaPrueba.setVisible(true);
    }
}