/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Estructuras;

/**
 *
 * @author Leonel
 */
public class ListaCircularSimple<T> {
    
    private NodoSimple cabeza;
    private NodoSimple actual;   
    private int size;
    
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

    public boolean estaVacia() {
        return cabeza == null;
    }

    /** Devuelve el estudiante actual (quien tiene el rol actual) */
    public T obtenerActual() {
         return actual != null ? (T) actual.dato : null;
    }

    /** Avanza al siguiente estudiante en la lista circular */
    public T rotar() {
        if (actual != null) {
            actual = actual.sig;
            return (T) actual.dato;
        }
        return null;
    }
}

