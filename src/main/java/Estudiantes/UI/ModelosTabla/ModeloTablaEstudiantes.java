package Estudiantes.UI.ModelosTabla;

import Estudiantes.Estructuras.BSTEstudiantes;
import Estudiantes.Modelo.Estudiante;

import javax.swing.table.AbstractTableModel;

/**
 * Modelo de datos personalizado para conectar el Árbol Binario de Búsqueda (BST) con un JTable.
 * Permite visualizar la información de los estudiantes en formato tabular.
 * <p>Complejidad Espacial General: O(1) (Solo mantiene una referencia al árbol, no duplica datos).</p>
 */
public class ModeloTablaEstudiantes extends AbstractTableModel {

    private BSTEstudiantes arbol;

    private String[] columnas = {
            "Matrícula",
            "Nombre",
            "Teléfono",
            "Correo",
            "Dirección"
    };

    /**
     * Constructor del modelo de tabla.
     *
     * @param arbol Referencia al árbol BST que contiene los datos.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public ModeloTablaEstudiantes(BSTEstudiantes arbol) {
        this.arbol = arbol;
    }

    /**
     * Obtiene el número total de filas (estudiantes).
     *
     * @return Cantidad de nodos en el árbol.
     * <p>Complejidad Temporal: O(n) (El método contarNodos recorre todo el árbol).</p>
     * <p>Complejidad Espacial: O(log n) promedio (Pila recursiva).</p>
     */
    @Override
    public int getRowCount() {
        return arbol.contarNodos();
    }

    /**
     * Obtiene el número de columnas.
     *
     * @return El tamaño del arreglo de columnas.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    /**
     * Obtiene el nombre de una columna específica.
     *
     * @param col Índice de la columna.
     * @return El nombre de la columna.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    @Override
    public String getColumnName(int col) {
        return columnas[col];
    }

    /**
     * Obtiene el valor para una celda específica (fila, columna).
     * Busca el estudiante en la posición 'rowIndex' recorriendo el árbol in-orden.
     *
     * @param rowIndex    Índice de la fila.
     * @param columnIndex Índice de la columna.
     * @return El valor del atributo correspondiente del estudiante.
     * <p>Complejidad Temporal: O(n) (En el peor caso, encontrar el nodo k-ésimo requiere recorrer k nodos).</p>
     * <p>Complejidad Espacial: O(log n) promedio (Pila recursiva de la búsqueda).</p>
     */
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