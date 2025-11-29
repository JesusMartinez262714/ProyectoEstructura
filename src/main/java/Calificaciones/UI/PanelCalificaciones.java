package Calificaciones.UI;

import Calificaciones.Estructuras.*;
import Calificaciones.Modelo.*;
import Estudiantes.ControlEstudiantes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelCalificaciones extends JPanel {

    // -- Estructuras de datos
    private final Cola<SolicitudCalificacion> colaSolicitudes; // -- Requisito 4.1 (calificaciones) y 8 del documento
    private final Pila<Accion> pilaDeshacer;                   // -- Requisito 9 del documento

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

        initUI();
    }

        /**
         * Inicializa y configura la interfaz gráfica de usuario (GUI) del panel.
         * Define el layout, colores, paneles (encabezado, formulario, tabla)
         * y sus componentes internos, además de asignar los listeners a los botones.
         */
    private void initUI(){
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
        btnEncolar.addActionListener(e -> encolarSolicitud());
        // -- Boton Procesar
        btnProcesar.addActionListener(e -> procesarSiguienteSolicitud());
        // -- Boton Deshacer
        btnDeshacer.addActionListener(e -> deshacerAccion());
    }

        /**
         * Lee los datos del formulario, valida la entrada y crea una nueva solicitud.
         * Si los datos son válidos, la solicitud se agrega a la cola de pendientes
         * y se actualiza la tabla visual.
         */
        private void encolarSolicitud() {
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

       try{
    int matriculaInt= Integer.parseInt(solicitud.getMatriculaEstudiante());

    if(ControlEstudiantes.existe(matriculaInt)){
        ControlEstudiantes.agregarCalificacion(matriculaInt, solicitud.getNuevaCalificacion());

        Accion accion= new Accion(
                Accion.TipoAccion.CAMBIO_CALIFICACION,
                null,
                solicitud
        );
        ControlAcciones.registrarAccion(accion);
        modeloTabla.eliminarPrimeraSolicitud();
        lblEstado.setText("Procesado: Nota agregada a " + solicitud.getMatriculaEstudiante());
    JOptionPane.showMessageDialog(this, "Calificacion registrada correctamente");
    }else{
        JOptionPane.showMessageDialog(this, "Error: El estudiante"+matriculaInt  +"no existe");
    }

       }catch(Exception e){
           JOptionPane.showMessageDialog(this, "Error" + e.getMessage());
       }
 }

    /**
     * Deshace la última acción realizada utilizando la pila de historial (LIFO).
     * Recupera el estado anterior de la acción más reciente y revierte los cambios
     * (por ejemplo, restaurando una calificación anterior).
     */
    private void deshacerAccion() {
        try{
            String resultado= ControlAcciones.deshacerUltima();
            lblEstado.setText(resultado);
            JOptionPane.showMessageDialog(this, resultado);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Aviso: " +e.getMessage());
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

            // DATOS DE PRUEBA
            Estudiantes.Modelo.Estudiante est1 = new Estudiantes.Modelo.Estudiante(
                    111, "Maria Lopez", "555-999", "maria@test.com", null
            );
            ControlEstudiantes.insertar(est1);

            System.out.println(">> Estudiante prueba (111) insertado.");

            JFrame ventana = new JFrame("Prueba Sistema Centralizado");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(950, 600);
            ventana.setLocationRelativeTo(null);

            PanelCalificaciones panel = new PanelCalificaciones();
            ventana.add(panel);
            ventana.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}