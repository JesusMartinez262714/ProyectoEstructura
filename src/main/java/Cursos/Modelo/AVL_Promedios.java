/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Modelo;

import Cursos.Estructuras.NodoAVL;
import Estudiantes.Modelo.Estudiante;

/**
 *
 * @author Leonel
 */
public class AVL_Promedios {
    
    NodoAVL raiz;

    private int altura(NodoAVL n) {
        return (n == null) ? 0 : n.altura;
    }

    private int obtenerBalance(NodoAVL n) {
        return (n == null) ? 0 : altura(n.izq) - altura(n.der);
    }

    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izq;
        NodoAVL T2 = x.der;

        // Rotar
        x.der = y;
        y.izq = T2;

        y.altura = Math.max(altura(y.izq), altura(y.der)) + 1;
        x.altura = Math.max(altura(x.izq), altura(x.der)) + 1;

        return x; 
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.der;
        NodoAVL T2 = y.izq;

        y.izq = x;
        x.der = T2;

        x.altura = Math.max(altura(x.izq), altura(x.der)) + 1;
        y.altura = Math.max(altura(y.izq), altura(y.der)) + 1;

        return y; 
    }

    public void insertar(double promedio, Estudiante<?> estudiante) {
        raiz = insertarRec(raiz, promedio, estudiante);
    }

    private NodoAVL insertarRec(NodoAVL nodo, double promedio, Estudiante<?> estudiante) {

        if (nodo == null) return new NodoAVL(promedio, estudiante);

        if (promedio < nodo.promedio)
            nodo.izq = insertarRec(nodo.izq, promedio, estudiante);
        else
            nodo.der = insertarRec(nodo.der, promedio, estudiante);

        nodo.altura = 1 + Math.max(altura(nodo.izq), altura(nodo.der));

        int balance = obtenerBalance(nodo);

        // IZQ-IZQ
        if (balance > 1 && promedio < nodo.izq.promedio)
            return rotacionDerecha(nodo);

        // DER-DER
        if (balance < -1 && promedio > nodo.der.promedio)
            return rotacionIzquierda(nodo);

        // IZQ-DER
        if (balance > 1 && promedio > nodo.izq.promedio) {
            nodo.izq = rotacionIzquierda(nodo.izq);
            return rotacionDerecha(nodo);
        }

        // DER-IZQ
        if (balance < -1 && promedio < nodo.der.promedio) {
            nodo.der = rotacionDerecha(nodo.der);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    // Recorrido in-orden (ordenado)
    public void inOrden() {
        inOrdenRec(raiz);
    }

    private void inOrdenRec(NodoAVL nodo) {
        if (nodo == null) return;

        inOrdenRec(nodo.izq);
        System.out.println(
            nodo.promedio + "  -  " + nodo.estudiante.getNombreCompleto()
        );
        inOrdenRec(nodo.der);
    }
 }

