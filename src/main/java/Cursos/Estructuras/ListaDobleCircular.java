/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Estructuras;

/**
 *
 * @author Leonel
 */
public class ListaDobleCircular <T> {

    private class Nodo {
        T dato;
        Nodo sig, ant;
        Nodo(T d) { dato = d; }
    }

    private Nodo cabeza;
    private int size;

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

    public int size() { return size; }

    /** Muestra los N primeros */
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

