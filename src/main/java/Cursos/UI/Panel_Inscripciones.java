/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.UI;

import Cursos.Modelo.Curso;
import Cursos.Modelo.GestionarCursos;
import Estudiantes.ControlEstudiantes;
import Estudiantes.Modelo.Estudiante;
import Main.DatosGlobales;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Panel de interfaz gráfica para gestionar la inscripción de estudiantes a cursos.
 * Permite inscribir alumnos verificando cupos y gestionando listas de espera.
 * <p>Complejidad Espacial General: O(1) (Referente a la lógica de control).</p>
 */
public class Panel_Inscripciones extends JPanel {

    private GestionarCursos gestor;

    private JTextField txtClaveCurso;
    private JTextField txtMatricula;

    private JTable tablaInscritos;
    private JTable tablaEspera;

    private DefaultTableModel modeloInscritos;
    private DefaultTableModel modeloEspera;

    private JLabel lblEstado;

    /**
     * Constructor del panel de inscripciones.
     * Inicializa la referencia al gestor de cursos y construye la interfaz.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public Panel_Inscripciones() {
        gestor = DatosGlobales.cursos;
        initUI();
    }

    private void initUI() {

        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(245, 245, 245));

        // ==== ENCABEZADO ====
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        header.setBackground(new Color(45, 52, 54));
        JLabel titulo = new JLabel("MÓDULO DE INSCRIPCIONES");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.add(titulo);
        add(header, BorderLayout.NORTH);


        // ==== PANEL IZQUIERDO: FORMULARIO ====
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Inscribir estudiante"));
        panelForm.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo clave del curso
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        panelForm.add(new JLabel("Clave del curso:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtClaveCurso = new JTextField();
        panelForm.add(txtClaveCurso, gbc);

        // Campo matrícula
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panelForm.add(new JLabel("Matrícula estudiante:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtMatricula = new JTextField();
        panelForm.add(txtMatricula, gbc);

        // Botón inscribir
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Que ocupe todo el ancho disponible
        gbc.insets = new Insets(15, 8, 8, 8); // Un poco más de espacio arriba

        JButton btnInscribir = new JButton("INSCRIBIR");

        btnInscribir.setBackground(new Color(9, 132, 227)); // Color de fondo azul sólido
        btnInscribir.setForeground(Color.WHITE); // Texto blanco
        btnInscribir.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Fuente

        btnInscribir.setFocusPainted(false); // Quita el recuadro interno al hacer clic
        btnInscribir.setBorderPainted(false); // QUITA EL BORDE DEL BOTÓN
        btnInscribir.setOpaque(true); // Asegura que se pinte el color de fondo sólido

        btnInscribir.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));

        btnInscribir.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mano

        panelForm.add(btnInscribir, gbc);

        JPanel panelIzq = new JPanel(new BorderLayout());
        panelIzq.setPreferredSize(new Dimension(320, 0));
        panelIzq.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelIzq.setOpaque(false);
        panelIzq.add(panelForm, BorderLayout.NORTH);

        add(panelIzq, BorderLayout.WEST);


        // ==== PANEL CENTRAL: TABLAS ====

        JPanel panelCentral = new JPanel(new GridLayout(2, 1, 5, 5));
        panelCentral.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelCentral.setOpaque(false);

        // Inscritos
        modeloInscritos = new DefaultTableModel(
                new Object[]{"Matrícula", "Nombre"}, 0
        );
        tablaInscritos = new JTable(modeloInscritos);
        tablaInscritos.setRowHeight(25);

        JScrollPane scrollInscritos =
                new JScrollPane(tablaInscritos);
        scrollInscritos.setBorder(
                BorderFactory.createTitledBorder("Estudiantes inscritos")
        );
        panelCentral.add(scrollInscritos);

        // Lista de espera
        modeloEspera = new DefaultTableModel(
                new Object[]{"Matrícula", "Nombre"}, 0
        );
        tablaEspera = new JTable(modeloEspera);
        tablaEspera.setRowHeight(25);

        JScrollPane scrollEspera =
                new JScrollPane(tablaEspera);
        scrollEspera.setBorder(
                BorderFactory.createTitledBorder("Lista de espera")
        );
        panelCentral.add(scrollEspera);

        add(panelCentral, BorderLayout.CENTER);


        // ==== BARRA DE ESTADO ====
        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEstado.setBackground(Color.WHITE);

        lblEstado = new JLabel(" Sistema listo.");
        panelEstado.add(lblEstado);
        add(panelEstado, BorderLayout.SOUTH);

        // Listener
        btnInscribir.addActionListener(e -> {
            try {
                inscribir();
            } catch (Exception ex) {
                System.getLogger(Panel_Inscripciones.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
    }


    // ================================
    //  LÓGICA PRINCIPAL DEL PANEL
    // ================================

    /**
     * Realiza el proceso de inscripción de un estudiante a un curso.
     * Busca el curso y el estudiante, verifica cupos y actualiza las listas correspondientes.
     * <p>Complejidad Temporal: O(log n) [Búsqueda Estudiante] + O(1) [Búsqueda Curso] + O(m) [Agregar Rol, donde m son alumnos en el curso].</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private void inscribir() throws Exception {

        String claveCurso = txtClaveCurso.getText().trim();
        String matStr = txtMatricula.getText().trim();

        if (claveCurso.isEmpty() || matStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe llenar todos los datos");
            return;
        }

        Curso curso = gestor.obtenerCurso(claveCurso);

        if (curso == null) {
            JOptionPane.showMessageDialog(this,
                    "No existe un curso con clave " + claveCurso);
            return;
        }

        try {
            int matricula = Integer.parseInt(matStr);

            Estudiante est = ControlEstudiantes.buscar(matricula);
            if (est == null) {
                JOptionPane.showMessageDialog(this,
                        "No existe un estudiante con matrícula " + matricula);
                return;
            }

            if (curso.hayCupo()) {
                curso.agregarInscrito(est);
                curso.agregarRol(est);
                JOptionPane.showMessageDialog(this,
                        "Rol asignado al estudiante: " + est.getNombreCompleto());
                modeloInscritos.addRow(new Object[]{
                        est.getMatricula(),
                        est.getNombreCompleto()
                });

                lblEstado.setText(" Estudiante inscrito correctamente.");

            } else {
                curso.agregarListaEspera(est);
                modeloEspera.addRow(new Object[]{
                        est.getMatricula(),
                        est.getNombreCompleto()
                });

                lblEstado.setText(" Curso lleno. Agregado a lista de espera.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "La matrícula debe ser número entero.");
        }
    }



    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info :
                    UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            JFrame ventana = new JFrame("Prueba Inscripciones");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(950, 600);
            ventana.setLocationRelativeTo(null);

            Panel_Inscripciones panel = new Panel_Inscripciones();
            ventana.add(panel);

            ventana.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}