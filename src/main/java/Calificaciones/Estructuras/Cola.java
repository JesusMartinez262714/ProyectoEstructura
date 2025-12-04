package Calificaciones.Estructuras;

/**
 * Clase genérica que implementa una estructura de datos tipo Cola (Queue).
 * Utiliza nodos enlazados para almacenar los elementos.
 * * <p>Complejidad Espacial General: O(n), donde n es el número de elementos en la cola.</p>
 *
 * @param <T> El tipo de dato que almacenará la cola.
 */
public class Cola<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tamanio;

    /**
     * Constructor por defecto.
     * Inicializa una cola vacía.
     * * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public Cola() {
        this.inicio = null;
        this.fin = null;
        this.tamanio = 0;
    }

    /**
     * Verifica si la cola está vacía.
     *
     * @return true si la cola no contiene elementos, false en caso contrario.
     * * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public boolean esVacia() {
        return inicio == null;
    }

    /**
     * Agrega un nuevo elemento al final de la cola.
     * Se aprovecha el puntero 'fin' para realizar la inserción constante.
     *
     * @param dato El elemento a agregar.
     * * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1) (Crea un nuevo nodo)</p>
     */
    public void encolar(T dato) {
        Nodo<T> aux = new Nodo<>(dato);
        if(esVacia()) {
            inicio = aux;
            fin = aux;
        } else {
            fin.setSiguiente(aux);
            fin = aux;
        }
        tamanio++;
    }

    /**
     * Elimina y devuelve el elemento que se encuentra al frente de la cola.
     *
     * @return El elemento del frente de la cola, o null si la cola está vacía.
     * * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public T desencolar() {
        if (esVacia()) {
            return null;
        }

        T dato = inicio.getDato();
        inicio = inicio.getSiguiente();

        if (inicio == null) {
            fin = null;
        }
        tamanio--;
        return dato;
    }

    /**
     * Obtiene el elemento que se encuentra al frente de la cola sin eliminarlo.
     *
     * @return El elemento del frente de la cola, o null si la cola está vacía.
     * * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public T frente(){
        // -- Operacion ternaria si recibe true devolvemos null, si contiene algo mandamos el dato del nodo inicio
        return esVacia()? null: inicio.getDato();
    }
}