package Cursos.UI;

import Cursos.Modelo.Curso;
import Cursos.Modelo.GestionarCursos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelCursos extends JPanel {

    private GestionarCursos gestor;

    // Campos del formulario
    private JTextField txtClave;
    private JTextField txtNombre;
    private JTextField txtCupoMax;

    private JTable tablaCursos;
    private DefaultTableModel modeloTabla;

    private JLabel lblEstado;

    public PanelCursos() {
        gestor = new GestionarCursos();
        initUI();
    }

    private void initUI() {

        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(245, 245, 245));

        // ==== ENCABEZADO ====
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        header.setBackground(new Color(45, 52, 54));
        JLabel titulo = new JLabel("GESTIÓN DE CURSOS");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.add(titulo);
        add(header, BorderLayout.NORTH);
        
        // -- PANEL IZQUIERDO (Formulario)
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setPreferredSize(new Dimension(320, 0));
        panelIzquierdo.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelIzquierdo.setOpaque(false);

        // ==== PANEL IZQUIERDO: FORMULARIO ====
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del curso"));
        panelForm.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo Clave
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.weightx = 0;
        panelForm.add(new JLabel("Clave:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtClave = new JTextField();
        panelForm.add(txtClave, gbc);
        

        // Campo Nombre
        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField();
        panelForm.add(txtNombre, gbc);

        // Campo Cupo Máximo
        gbc.gridx = 0; gbc.gridy = 2;
        panelForm.add(new JLabel("Cupo máximo:"), gbc);
        gbc.gridx = 1;
        txtCupoMax = new JTextField();
        panelForm.add(txtCupoMax, gbc);

        // Botón agregar
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton btnAgregar = new JButton("Agregar Curso");
        btnAgregar.setBackground(new Color(9, 132, 227));
        btnAgregar.setForeground(Color.WHITE);
        panelForm.add(btnAgregar, gbc);

        // Botón eliminar
        gbc.gridy = 4;
        JButton btnEliminar = new JButton("Eliminar Curso");
        btnEliminar.setBackground(new Color(214, 48, 49));
        btnEliminar.setForeground(Color.WHITE);
        panelForm.add(btnEliminar, gbc);

        // ==== Panel izquierdo contenedor ====
        JPanel panelIzq = new JPanel(new BorderLayout());
        panelIzq.setPreferredSize(new Dimension(320, 0));
        panelIzq.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelIzq.setOpaque(false);
        panelIzq.add(panelForm, BorderLayout.NORTH);

        add(panelIzq, BorderLayout.WEST);

        // ==== PANEL CENTRAL: TABLA ====

        modeloTabla = new DefaultTableModel(new Object[]{"Clave", "Nombre", "Cupo"}, 0);
        tablaCursos = new JTable(modeloTabla);
        tablaCursos.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(tablaCursos);
        scroll.setBorder(BorderFactory.createTitledBorder("Lista de cursos"));

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(new EmptyBorder(10, 0, 10, 10));
        panelCentral.setOpaque(false);
        panelCentral.add(scroll, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        // ==== BARRA DE ESTADO ====

        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEstado.setBackground(Color.WHITE);

        lblEstado = new JLabel(" Sistema listo.");
        panelEstado.add(lblEstado);

        add(panelEstado, BorderLayout.SOUTH);

        // ==== LISTENERS ====

        btnAgregar.addActionListener(e -> agregarCurso());
        btnEliminar.addActionListener(e -> eliminarCurso());
    }

    // ============================
    //  MÉTODOS DE FUNCIONALIDAD
    // ============================

    private void agregarCurso() {
        try {
            String clave = txtClave.getText().trim();
            String nombre = txtNombre.getText().trim();
            String cupoStr = txtCupoMax.getText().trim();

            if (clave.isEmpty() || nombre.isEmpty() || cupoStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
                return;
            }

            int cupo = Integer.parseInt(cupoStr);

            gestor.agregarCurso(clave, nombre, cupo);

            modeloTabla.addRow(new Object[]{
                    clave, nombre, cupo
            });

            lblEstado.setText(" Curso agregado: " + clave);

            limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El cupo debe ser un número entero");
        }
    }

    private void eliminarCurso() {
        int fila = tablaCursos.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un curso en la tabla");
            return;
        }

        String clave = modeloTabla.getValueAt(fila, 0).toString();

        gestor.eliminarCurso(clave);

        modeloTabla.removeRow(fila);

        lblEstado.setText(" Curso eliminado: " + clave);
    }

    private void limpiarCampos() {
        txtClave.setText("");
        txtNombre.setText("");
        txtCupoMax.setText("");
    }
    
    public static void main(String[] args) {
    try {
        // Aplicar Look and Feel Nimbus
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        // Ventana de prueba
        JFrame ventana = new JFrame("Prueba Panel de Cursos");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(950, 600);
        ventana.setLocationRelativeTo(null);

        PanelCursos panel = new PanelCursos();
        ventana.add(panel);

        ventana.setVisible(true);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}