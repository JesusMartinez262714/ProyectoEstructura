/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Estructuras;

/**
 * Clase que representa un nodo para una lista simplemente enlazada.
 * Contiene el dato y la referencia al siguiente nodo.
 * <p>Complejidad Espacial General: O(1) por cada instancia de NodoSimple creada.</p>
 *
 * @author Leonel
 */
public class NodoSimple<T> {
    T dato;
    NodoSimple sig;

    /**
     * Constructor que inicializa el nodo con un dato.
     * @param d El dato a almacenar.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public NodoSimple(T d) {
        dato = d;
    }

    /**
     * Obtiene el dato almacenado.
     * @return El dato.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public T getDato() {
        return dato;
    }

    /**
     * Asigna un nuevo dato al nodo.
     * @param dato El nuevo dato.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Obtiene la referencia al siguiente nodo.
     * @return El nodo siguiente.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public NodoSimple getSig() {
        return sig;
    }

    /**
     * Establece la referencia al siguiente nodo.
     * @param sig El nuevo nodo siguiente.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setSig(NodoSimple sig) {
        this.sig = sig;
    }


}