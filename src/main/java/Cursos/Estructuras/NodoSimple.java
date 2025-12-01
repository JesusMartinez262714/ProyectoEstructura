/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Estructuras;

/**
 *
 * @author Leonel
 */
public class NodoSimple<T> {
        T dato;
        NodoSimple sig;
        
    public NodoSimple(T d) { 
        dato = d; 
        }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoSimple getSig() {
        return sig;
    }

    public void setSig(NodoSimple sig) {
        this.sig = sig;
    }
        
        
}
