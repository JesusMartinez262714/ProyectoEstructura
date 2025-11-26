package Calificaciones.Estructuras;

/**
 * Clase genérica que implementa una estructura de datos tipo Pila (Stack).
 * Sigue el principio LIFO (Last In, First Out), donde el último elemento en entrar es el primero en salir.
 *
 * @param <T> El tipo de dato que almacenará la pila.
 */
public class Pila <T>{
    private Nodo<T> inicio;
    private int tamanio;

    /**
     * Constructor por defecto.
     * Inicializa una pila vacía.
     */
    public Pila(){
        this.inicio = null;
        this.tamanio = 0;
    }

    /**
     * Verifica si la pila está vacía.
     *
     * @return true si la pila no contiene elementos, false en caso contrario.
     */
    public boolean esVacia(){
        return inicio == null;
    }

    /**
     * Agrega un nuevo elemento a la cima de la pila (operación push).
     *
     * @param dato El elemento a agregar.
     */
    public void push(T dato){
        Nodo<T> nuevo= new Nodo<>(dato);
        nuevo.setSiguiente(inicio);
        inicio = nuevo;
        tamanio++;
    }

    /**
     * Elimina y devuelve el elemento que se encuentra en la cima de la pila (operación pop).
     *
     * @return El elemento en la cima de la pila, o null si la pila está vacía.
     */
    public T pop(){
        if(esVacia()) return null;

        T dato= inicio.getDato();
        inicio= inicio.getSiguiente();
        tamanio--;
        return dato;
    }

    /**
     * Obtiene el elemento que se encuentra en la cima de la pila sin eliminarlo (operación peek/top).
     *
     * @return El elemento en la cima de la pila, o null si la pila está vacía.
     */
    public T cima(){
        return esVacia()? null: inicio.getDato();
    }
}
