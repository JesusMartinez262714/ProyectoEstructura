package Calificaciones.Estructuras;

/**
 * Clase genérica que representa un nodo para una estructura de datos doblemente enlazada.
 * Contiene un dato, una referencia al siguiente nodo y una referencia al nodo anterior.
 * <p>Complejidad Espacial General: O(1) por cada instancia de NodoDoble creada.</p>
 *
 * @param <T> El tipo de dato que almacena el nodo.
 */
public class NodoDoble <T> {
    private T dato;
    private NodoDoble<T> siguiente;
    private NodoDoble<T> anterior;

    /**
     * Constructor que inicializa un nodo doble con un dato específico.
     * Las referencias al nodo siguiente y anterior se inicializan en null.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     *
     * @param dato El dato a almacenar en el nodo.
     */
    public NodoDoble(T dato){
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     *
     * @return El dato contenido en el nodo.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public T getDato() {
        return dato;
    }

    /**
     * Establece un nuevo valor para el dato del nodo.
     *
     * @param dato El nuevo dato a almacenar.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Obtiene la referencia al siguiente nodo en la estructura.
     *
     * @return El nodo siguiente, o null si no existe.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public NodoDoble<T> getSiguiente() {
        return siguiente;
    }

    /**
     * Establece la referencia al siguiente nodo.
     *
     * @param siguiente El nodo que será el siguiente en la secuencia.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setSiguiente(NodoDoble<T> siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Obtiene la referencia al nodo anterior en la estructura.
     *
     * @return El nodo anterior, o null si no existe.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public NodoDoble<T> getAnterior() {
        return anterior;
    }

    /**
     * Establece la referencia al nodo anterior.
     *
     * @param anterior El nodo que será el anterior en la secuencia.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setAnterior(NodoDoble<T> anterior) {
        this.anterior = anterior;
    }
}