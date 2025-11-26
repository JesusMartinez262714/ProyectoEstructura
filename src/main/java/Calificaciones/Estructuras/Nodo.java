package Calificaciones.Estructuras;

/**
 * Clase genérica que representa un nodo para una estructura de datos enlazada simple.
 * Contiene un dato y una referencia al siguiente nodo.
 *
 * @param <T> El tipo de dato que almacena el nodo.
 */
public class Nodo <T>{
    private T dato;
    private Nodo<T> siguiente;

    /**
     * Constructor que inicializa un nodo con un dato específico.
     * La referencia al siguiente nodo se inicializa en null.
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
     */
    public T getDato(){
        return dato;
    }

    /**
     * Establece un nuevo valor para el dato del nodo.
     *
     * @param dato El nuevo dato a almacenar.
     */
    public void setDato(T dato){
        this.dato = dato;
    }

    /**
     * Obtiene la referencia al siguiente nodo en la estructura.
     *
     * @return El nodo siguiente, o null si no existe.
     */
    public Nodo<T> getSiguiente(){
        return siguiente;
    }

    /**
     * Establece la referencia al siguiente nodo.
     *
     * @param siguiente El nodo que será el siguiente en la secuencia.
     */
    public void setSiguiente(Nodo<T> siguiente){
        this.siguiente = siguiente;
    }
}
