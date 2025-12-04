package Calificaciones.Estructuras;

/**
 * Clase genérica que representa un nodo para una estructura de datos enlazada simple.
 * Contiene un dato y una referencia al siguiente nodo.
 * <p>Complejidad Espacial General: O(1) por cada instancia de Nodo creada.</p>
 *
 * @param <T> El tipo de dato que almacena el nodo.
 */
public class Nodo <T>{
    private T dato;
    private Nodo<T> siguiente;

    /**
     * Constructor que inicializa un nodo con un dato específico.
     * La referencia al siguiente nodo se inicializa en null.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     *
     * @param dato El dato a almacenar en el nodo.
     */
    public Nodo(T dato){
        this.dato = dato;
        this.siguiente = null;
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     *
     * @return El dato contenido en el nodo.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public T getDato(){
        return dato;
    }

    /**
     * Establece un nuevo valor para el dato del nodo.
     *
     * @param dato El nuevo dato a almacenar.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setDato(T dato){
        this.dato = dato;
    }

    /**
     * Obtiene la referencia al siguiente nodo en la estructura.
     *
     * @return El nodo siguiente, o null si no existe.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public Nodo<T> getSiguiente(){
        return siguiente;
    }

    /**
     * Establece la referencia al siguiente nodo.
     *
     * @param siguiente El nodo que será el siguiente en la secuencia.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setSiguiente(Nodo<T> siguiente){
        this.siguiente = siguiente;
    }
}