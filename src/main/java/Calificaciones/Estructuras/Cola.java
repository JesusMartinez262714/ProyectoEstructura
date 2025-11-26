package Calificaciones.Estructuras;

/**
 * Clase genérica que implementa una estructura de datos tipo Cola (Queue).
 * Utiliza nodos enlazados para almacenar los elementos.
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
     */
    public boolean esVacia() {
        return inicio == null;
    }

    /**
     * Agrega un nuevo elemento al final de la cola.
     *
     * @param dato El elemento a agregar.
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
     */
    public T frente(){
        // -- Operacion ternaria si recibe true devolvemos null, si contiene algo mandamos el dato del nodo inicio
        return esVacia()? null: inicio.getDato();
    }
}
