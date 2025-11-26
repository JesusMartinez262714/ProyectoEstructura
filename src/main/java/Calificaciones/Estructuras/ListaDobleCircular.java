package Calificaciones.Estructuras;

/**
 * Clase genérica que implementa una Lista Doble Circular.
 * En esta estructura, cada nodo tiene referencias al siguiente y al anterior.
 * El último nodo apunta al primero y el primero apunta al último, formando un ciclo cerrado.
 *
 * @param <T> El tipo de dato que almacenará la lista.
 */
public class ListaDobleCircular <T>{

    private NodoDoble<T> inicio;
    private int tamanio;

    /**
     * Constructor por defecto.
     * Inicializa la lista como vacía.
     */
    public ListaDobleCircular(){
        this.inicio = null;
        this.tamanio = 0;
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si la lista no contiene nodos, false en caso contrario.
     */
    public boolean esVacia(){
        return inicio == null;
    }

    /**
     * Obtiene la cantidad actual de elementos en la lista.
     *
     * @return El tamaño de la lista.
     */
    public int getTamanio(){
        return tamanio;
    }

    // -- Agrega un nuevo nodo al final de la lista (Para la lista de espera)
    /**
     * Agrega un nuevo nodo al "final" de la lista circular.
     * El nuevo nodo se inserta entre el último nodo actual y el nodo de inicio.
     *
     * @param dato El dato a agregar a la lista.
     */
    public void agregar(T dato){

        NodoDoble<T> nuevo = new NodoDoble<>(dato);

        if(esVacia()){
            inicio = nuevo;
            inicio.setSiguiente(inicio);
            inicio.setAnterior(inicio);
        }else{
            NodoDoble<T> ultimo = inicio.getAnterior();

            // -- Conectamos el nuevo nodo con el anterior y el ultimo
            nuevo.setSiguiente(inicio); // -- El siguiente de nuevo es el primero(inicio)
            nuevo.setAnterior(ultimo); // -- El anterio de nuevo es el ultimo

            // -- Conectamos sus vecinos para que lo reconozcan
            ultimo.setSiguiente(nuevo); // -- El siguiente del ultimo es el nuevo
            inicio.setAnterior(nuevo); // -- El anterior del inicio es el nuevo
        }
        tamanio++;
    }

    /**
     * Elimina y devuelve el elemento que se encuentra al inicio de la lista (frente).
     * Ajusta los punteros del último nodo y del nuevo inicio para mantener la circularidad.
     *
     * @return El dato eliminado, o null si la lista estaba vacía.
     */
    public T eliminarFrente(){
        if(esVacia()) return null;

        T datoEliminado= inicio.getDato();
        if(tamanio == 1){
            inicio = null;
        }else{
            NodoDoble<T> ultimo= inicio.getAnterior();
            NodoDoble<T> siguiente = inicio.getSiguiente(); // -- Nuevo inicio

            ultimo.setSiguiente(siguiente);
            siguiente.setAnterior(ultimo);

            inicio = siguiente; // -- Nuevo inicio
        }
        tamanio--;
        return datoEliminado;
    }

    /**
     * Recorre e imprime los elementos de la lista en orden secuencial (hacia adelante),
     * comenzando desde el inicio hasta volver al principio.
     */
    public void mostrarAdelante(){
        if(esVacia()) {
            System.out.println("Lista vacia...");
            return;
        }
        NodoDoble<T> aux= inicio;
        System.out.println("Lista: ");
        // -- Recorremos la lista
        do{
            System.out.println(" -- "+aux.getDato()+ " -- --> ");
            aux= aux.getSiguiente();
        }while(aux!=inicio);
            System.out.println(" --> "+inicio.getDato()); // -- Mostramos que terminamos de recorrer la lista

        }

    /**
     * Recorre e imprime los elementos de la lista en orden inverso (hacia atrás),
     * comenzando desde el último nodo agregado hasta llegar al inicio.
     */
    public void mostrarAtras(){
        if(esVacia()) return;

        NodoDoble<T> aux= inicio.getAnterior();
        System.out.println("Inverso: ");

        do{
            System.out.println(" -- "+aux.getDato()+ " -- --> ");
            aux= aux.getAnterior();
        } while(aux!=inicio.getAnterior()); // -- Recorremos la lista inversa y paramos hasta llegar al inicio (final de la lista)
        System.out.println(" --> "+inicio.getAnterior().getDato());
    }
}
