/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Estructuras;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Clase que implementa una Lista Simplemente Enlazada (Singly Linked List).
 * Permite almacenar una secuencia de elementos de cualquier tipo (T)
 * y soporta la iteración a través de la interfaz {@code Iterable}.
 *
 * @param <T> El tipo de elementos contenidos en la lista.
 * @author Leonel 
 */
public class ListaSimple<T> implements Iterable<T> {


    private NodoSimple inicio;
    private NodoSimple fin;
    private int size;
    /**
     * Agrega un nuevo elemento al principio (inicio) de la lista.
     * Esta operación es de tiempo constante O(1).
     * * @param dato El dato del tipo T a ser agregado.
     */
    public void agregarInicio(T dato) {
        NodoSimple n = new NodoSimple(dato);
        n.sig = inicio;
        inicio = n;
    }

    /**
     * Busca y elimina la primera ocurrencia de un elemento 
     * que cumpla con la condición definida por el Predicate.
     * * @param pred El objeto Predicate que define la condición de eliminación.
     * El método debe retornar true para el elemento que se desea eliminar.
     * @return true si se encontró y eliminó un elemento, false en caso contrario.
     */
    public boolean eliminarSi(Predicate<T> pred) {
        NodoSimple actual = inicio, anterior = null;

        while (actual != null) {
            if (pred.test((T) actual.dato)) {
                if (anterior == null) inicio = actual.sig;
                else anterior.sig = actual.sig;
                return true;
            }
            anterior = actual;
            actual = actual.sig;
        }
        return false;
    }

    /**
     * Retorna un iterador sobre los elementos de esta lista.
     * Esto permite el uso de la lista en bucles {@code for-each}.
     *
     * @return Un objeto {@code Iterator<T>} que permite recorrer la lista.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            NodoSimple actual = inicio;

            /**
             * Retorna {@code true} si la iteración tiene más elementos.
             * @return {@code true} si todavía hay nodos por visitar.
             */
            
            public boolean hasNext() { return actual != null; }

            /**
             * Retorna el siguiente elemento en la iteración.
             * @return El siguiente dato de tipo T en la lista.
             */
            
            public T next() {
                T dato = (T) actual.dato;
                actual = actual.sig;
                return dato;
            }
        };
    }
    
    

    public void agregarFinal(T dato) {
        NodoSimple n = new NodoSimple(dato);
        if (inicio == null) {
            inicio = fin = n;
        } else {
            fin.sig = n;
            fin = n;
        }
        size++;
    }

    public int size() { return size; }

    
}
