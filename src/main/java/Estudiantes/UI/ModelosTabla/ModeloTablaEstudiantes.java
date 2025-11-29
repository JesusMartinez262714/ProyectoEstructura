package Estudiantes.UI.ModelosTabla;

import Estudiantes.Estructuras.BSTEstudiantes;
import Estudiantes.Modelo.Estudiante;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaEstudiantes extends AbstractTableModel {

    private BSTEstudiantes arbol;

    private String[] columnas = {
            "Matrícula",
            "Nombre",
            "Teléfono",
            "Correo",
            "Dirección"
    };

    public ModeloTablaEstudiantes(BSTEstudiantes arbol) {
        this.arbol = arbol;
    }

    @Override
    public int getRowCount() {
        return arbol.contarNodos();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnas[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Estudiante est = arbol.obtenerEnPosicion(rowIndex);

        switch (columnIndex) {
            case 0: return est.getMatricula();
            case 1: return est.getNombreCompleto();
            case 2: return est.getNumeroTelefono();
            case 3: return est.getCorreo();
            case 4: return est.getDireccion() == null ? "" : est.getDireccion().toString();
        }
        return null;
    }
}
