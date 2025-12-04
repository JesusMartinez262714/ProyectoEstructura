package Calificaciones.Modelo;

/**
 * Representa una solicitud para asignar o modificar la calificación de un estudiante en un curso específico.
 * <p>Complejidad Espacial General: O(1) por cada instancia creada.</p>
 */
public class SolicitudCalificacion {

    private String matriculaEstudiante;
    private String codigoCurso;
    private float nuevaCalificacion;

    /**
     * Crea una nueva solicitud de calificación.
     *
     * @param matriculaEstudiante La matrícula del estudiante al que se le asignará la calificación.
     * @param codigoCurso         El código del curso asociado a la calificación.
     * @param nuevaCalificacion   El valor de la calificación a asignar.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public SolicitudCalificacion(String matriculaEstudiante, String codigoCurso, float nuevaCalificacion) {
        this.matriculaEstudiante = matriculaEstudiante;
        this.codigoCurso = codigoCurso;
        this.nuevaCalificacion = nuevaCalificacion;
    }

    /**
     * Obtiene la matrícula del estudiante.
     *
     * @return La matrícula del estudiante.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public String getMatriculaEstudiante() {
        return matriculaEstudiante;
    }

    /**
     * Obtiene el código del curso.
     *
     * @return El código del curso.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public String getCodigoCurso() {
        return codigoCurso;
    }

    /**
     * Obtiene la nueva calificación propuesta en la solicitud.
     *
     * @return El valor de la nueva calificación.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public float getNuevaCalificacion() {
        return nuevaCalificacion;
    }

    /**
     * Actualiza el valor de la calificación en la solicitud.
     *
     * @param nuevaCalificacion La nueva calificación a establecer.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setNuevaCalificacion(float nuevaCalificacion) {
        this.nuevaCalificacion = nuevaCalificacion;
    }

    /**
     * Devuelve una representación en cadena de la solicitud de calificación.
     *
     * @return Una cadena con los detalles de la solicitud.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1) (Auxiliar para la cadena resultante)</p>
     */
    @Override
    public String toString() {
        return "SolicitudCalificacion{" + "matriculaEstudiante=" + matriculaEstudiante + ", codigoCurso=" + codigoCurso + ", nuevaCalificacion=" + nuevaCalificacion + '}';
    }
}