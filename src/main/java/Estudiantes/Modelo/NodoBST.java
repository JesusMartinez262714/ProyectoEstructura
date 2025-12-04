package Estudiantes.Modelo;

/**
 * Clase que representa un nodo para el Árbol Binario de Búsqueda (BST).
 * Contiene el objeto Estudiante y referencias a los subárboles izquierdo y derecho.
 * <p>Complejidad Espacial General: O(1) por cada instancia de NodoBST creada.</p>
 */
public class NodoBST {
    public Estudiante estudiante;
    private NodoBST izquierdo;
    private NodoBST derecho;

    /**
     * Constructor que inicializa un nodo con un estudiante.
     * Los hijos se inicializan en null.
     *
     * @param estudiante El estudiante a almacenar.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public NodoBST(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.izquierdo = null;
        this.derecho = null;
    }

    /**
     * Obtiene el estudiante almacenado.
     * @return El estudiante.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**
     * Asigna un nuevo estudiante al nodo.
     * @param estudiante El nuevo estudiante.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    /**
     * Obtiene la referencia al hijo izquierdo.
     * @return El nodo izquierdo.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public NodoBST getIzquierdo() {
        return izquierdo;
    }

    /**
     * Establece el hijo izquierdo.
     * @param izquierdo El nuevo nodo izquierdo.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setIzquierdo(NodoBST izquierdo) {
        this.izquierdo = izquierdo;
    }

    /**
     * Obtiene la referencia al hijo derecho.
     * @return El nodo derecho.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public NodoBST getDerecho() {
        return derecho;
    }

    /**
     * Establece el hijo derecho.
     * @param derecho El nuevo nodo derecho.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setDerecho(NodoBST derecho) {
        this.derecho = derecho;
    }


    /**
     * Verifica si el nodo es una hoja (no tiene hijos).
     * @return true si ambos hijos son null.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public boolean esHoja(){
        return izquierdo == null && derecho == null;
    }

    /**
     * Verifica si el nodo tiene exactamente un hijo.
     * @return true si tiene solo izquierdo o solo derecho.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public boolean tieneUnHijo(){
        return (izquierdo != null && derecho == null)||(izquierdo == null && derecho != null);
    }

    /**
     * Verifica si el nodo tiene ambos hijos.
     * @return true si tiene izquierdo y derecho.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public boolean tieneDosHijos(){
        return izquierdo != null && derecho != null;
    }


}