package Calificaciones.UI;

import Calificaciones.Modelo.SolicitudCalificacion;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de tabla personalizado para mostrar una lista de objetos {@link SolicitudCalificacion}.
 * Extiende {@link AbstractTableModel} para proporcionar datos a un JTable.
 * Administra una lista interna de solicitudes y notifica a la vista cuando los datos cambian.
 */
public class ModeloTablaCalificaciones extends AbstractTableModel {

    private final String[] nombresColumnas= {
            "Turno", "Matricula", "Curso", "Nueva Nota", "Estatus"};

    private List<SolicitudCalificacion> listaSolicitudes;

    /**
     * Constructor del modelo.
     * Inicializa la lista de solicitudes como una lista vacía.
     */
    public ModeloTablaCalificaciones(){
        this.listaSolicitudes = new ArrayList<>();
    }


    /**
     * Devuelve el número de filas en el modelo, que corresponde al número de solicitudes en la lista.
     *
     * @return El número de filas.
     */
    @Override
    public int getRowCount() {
        return listaSolicitudes.size();    }

    /**
     * Devuelve el número de columnas en el modelo.
     *
     * @return El número de columnas definidas en los encabezados.
     */
    @Override
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    /**
     * Devuelve el nombre de la columna en el índice especificado.
     *
     * @param column El índice de la columna (0 basado).
     * @return El nombre de la columna.
     */
    @Override
    public String getColumnName(int column) {
        return nombresColumnas[column];
    }

    /**
     * Obtiene el valor para la celda ubicada en la fila y columna especificadas.
     *
     * @param rowIndex    El índice de la fila.
     * @param columnIndex El índice de la columna.
     * @return El valor que se mostrará en la celda.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      SolicitudCalificacion solicitud = listaSolicitudes.get(rowIndex);

      switch (columnIndex) {
          case 0: return rowIndex +1; // Turno (1-based)
          case 1: return solicitud.getMatriculaEstudiante();
          case 2: return solicitud.getCodigoCurso();
          case 3:  return solicitud.getNuevaCalificacion();
          case 4: return "Pendiente..."; // Regresaremos el estatus en un furuto
      default: return null;
      }
    }

    /**
     * Agrega una nueva solicitud al modelo y notifica a la tabla que se ha insertado una fila.
     *
     * @param solicitud La solicitud de calificación a agregar.
     */
    public void agregarSolicitud(SolicitudCalificacion solicitud){
        listaSolicitudes.add(solicitud);
        fireTableRowsInserted(listaSolicitudes.size()-1, listaSolicitudes.size()-1);
    }

    /**
     * Elimina la primera solicitud de la lista (FIFO) y notifica a la tabla.
     * Se utiliza cuando una solicitud ha sido procesada.
     */
    public void eliminarPrimeraSolicitud(){
    if(!listaSolicitudes.isEmpty()){
        listaSolicitudes.remove(0);
        fireTableRowsDeleted(0,0);
    }
    }

    /**
     * Elimina todas las solicitudes de la lista y refresca la tabla completa.
     */
    public void limpiarTodo(){
        listaSolicitudes.clear();
        fireTableDataChanged();
    }
}
