/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Estructuras;

/**
 * Clase que implementa una Lista Simplemente Enlazada y Circular (Singly Linked Circular List).
 * El último nodo apunta al primer nodo (cabeza), formando un círculo. 
 * Incluye un puntero {@code actual} para facilitar la rotación y el acceso secuencial.
 * <p>Complejidad Espacial General: O(n), donde n es el número de elementos en la lista.</p>
 *
 * @param <T> El tipo de elementos contenidos en la lista.
 * @author Leonel
 */
public class ListaCircularSimple<T> {

    private NodoSimple cabeza;
    private NodoSimple actual;
    private int size;

    /**
     * Agrega un nuevo elemento al final de la lista.
     * En una lista circular, esto significa insertarlo justo antes de la {@code cabeza}.
     * Como no se mantiene un puntero al final, se debe recorrer toda la lista.
     *
     * @param dato El dato del tipo T a ser agregado.
     * <p>Complejidad Temporal: O(n) (Debe recorrer la lista para encontrar el último nodo).</p>
     * <p>Complejidad Espacial: O(1) (Crea un nuevo nodo).</p>
     */
    public void agregar(T dato) {
        NodoSimple n = new NodoSimple(dato);

        if (cabeza == null) {
            cabeza = n;
            n.sig = n;
            actual = cabeza;
        } else {
            NodoSimple aux = cabeza;
            while (aux.sig != cabeza) {
                aux = aux.sig;
            }
            aux.sig = n;
            n.sig = cabeza;
        }
        size++;
    }

    /**
     * Verifica si la lista se encuentra vacía.
     * @return {@code true} si la lista no contiene elementos (cabeza es null), {@code false} en caso contrario.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Obtiene el dato del nodo actualmente referenciado por el puntero {@code actual}.
     *
     * @return El dato de tipo T del nodo actual, o {@code null} si la lista está vacía.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public T obtenerActual() {
        return actual != null ? (T) actual.dato : null;
    }

    /**
     * Rota el puntero {@code actual} al siguiente nodo de la lista circular
     * y retorna el dato del nuevo nodo actual.
     *
     * @return El dato de tipo T del nodo después de la rotación, o {@code null} si la lista está vacía.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public T rotar() {
        if (actual != null) {
            actual = actual.sig;
            return (T) actual.dato;
        }
        return null;
    }
}