/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Estructuras;

/**
 * Clase que implementa una Lista Doblemente Enlazada y Circular (Doubly Linked Circular List).
 * Cada nodo mantiene referencias al siguiente y al anterior, y el último nodo 
 * enlaza con el primero (cabeza), formando un círculo.
 * <p>Complejidad Espacial General: O(n), donde n es el número de elementos en la lista.</p>
 *
 * @param <T> El tipo de elementos contenidos en la lista.
 * @author Leonel
 */
public class ListaDobleCircular <T> {

    /**
     * Clase interna que representa un nodo en la lista doblemente enlazada.
     * <p>Complejidad Espacial: O(1) por nodo.</p>
     */
    private class Nodo {
        T dato;
        Nodo sig, ant;

        /**
         * Constructor del nodo interno.
         * <p>Complejidad Temporal: O(1)</p>
         */
        Nodo(T d) { dato = d; }
    }

    private Nodo cabeza;  // Referencia al nodo principal de la lista
    private int size; // Contador del número de elementos en la lista.

    /**
     * Agrega un nuevo elemento al final de la lista.
     * Como es circular, se inserta justo antes de la {@code cabeza}.
     * Aprovechando la referencia 'ant' de la cabeza, accedemos al último nodo en tiempo constante.
     *
     * @param dato El dato a agregar.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1) (Crea un nuevo nodo)</p>
     */
    public void agregar(T dato) {
        Nodo n = new Nodo(dato);

        if (cabeza == null) {
            cabeza = n;
            n.sig = n;
            n.ant = n;
        } else {
            Nodo ultimo = cabeza.ant;
            ultimo.sig = n;
            n.ant = ultimo;
            n.sig = cabeza;
            cabeza.ant = n;
        }
        size++;
    }

    /**
     * Retorna el número de elementos contenidos en la lista.
     * @return El tamaño actual de la lista.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public int size() { return size; }

    /** * Muestra los primeros N elementos de la lista en orden,
     * comenzando por la {@code cabeza}. Si la lista es menor a N, muestra todos.
     * Si la lista está vacía, imprime un mensaje.
     *
     * @param n El número máximo de elementos a mostrar.
     * <p>Complejidad Temporal: O(n) (o O(k) donde k es el mínimo entre n y el tamaño).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void mostrarN(int n) {
        if (cabeza == null) {
            System.out.println("Lista de espera vacía");
            return;
        }

        Nodo aux = cabeza;
        int count = 0;

        do {
            System.out.println(aux.dato);
            aux = aux.sig;
            count++;
        } while (aux != cabeza && count < n);
    }
}