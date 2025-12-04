/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Modelo;

import Cursos.Estructuras.NodoAVL;
import Estudiantes.Modelo.Estudiante;

/**
 * Clase que implementa un Árbol Binario de Búsqueda Auto-Balanceado AVL (Adelson-Velsky y Landis).
 * Este árbol almacena información de estudiantes, siendo ordenado por su promedio (key).
 * Garantiza que la altura del árbol sea O(log n) mediante rotaciones.
 * <p>Complejidad Espacial General: O(n), donde n es el número de estudiantes en el árbol.</p>
 * @author Leonel
 */
public class AVL_Promedios {

    NodoAVL raiz;

    /**
     * Obtiene la altura de un nodo. Si el nodo es null, su altura es 0.
     * @param n El nodo cuya altura se desea conocer.
     * @return La altura del nodo.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private int altura(NodoAVL n) {
        return (n == null) ? 0 : n.altura;
    }

    /**
     * Calcula el factor de balance de un nodo.
     * Factor de Balance = Altura del subárbol izquierdo - Altura del subárbol derecho.
     * @param n El nodo cuyo balance se desea calcular.
     * @return El factor de balance. Un valor fuera de [-1, 1] indica desbalance.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private int obtenerBalance(NodoAVL n) {
        return (n == null) ? 0 : altura(n.izq) - altura(n.der);
    }

    /**
     * Realiza una Rotación Simple a la Derecha (Right Rotation).
     * Se usa para corregir desbalances IZQ-IZQ.
     * * @param y El nodo desbalanceado (pivot original).
     * @return La nueva raíz del subárbol después de la rotación (el nodo x).
     * <p>Complejidad Temporal: O(1) (Solo se reasignan punteros).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
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

    /**
     * Realiza una Rotación Simple a la Izquierda (Left Rotation).
     * Se usa para corregir desbalances DER-DER.
     *
     * @param x El nodo desbalanceado (pivot original).
     * @return La nueva raíz del subárbol después de la rotación (el nodo y).
     * <p>Complejidad Temporal: O(1) (Solo se reasignan punteros).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.der;
        NodoAVL T2 = y.izq;

        y.izq = x;
        x.der = T2;

        x.altura = Math.max(altura(x.izq), altura(x.der)) + 1;
        y.altura = Math.max(altura(y.izq), altura(y.der)) + 1;

        return y;
    }

    /**
     * Método público para iniciar la inserción de un nuevo estudiante.
     * @param promedio La clave de ordenamiento (promedio del estudiante).
     * @param estudiante El objeto Estudiante asociado a la clave.
     * <p>Complejidad Temporal: O(log n) gracias al balanceo AVL.</p>
     * <p>Complejidad Espacial: O(log n) debido a la recursión en la pila de llamadas.</p>
     */
    public void insertar(double promedio, Estudiante<?> estudiante) {
        raiz = insertarRec(raiz, promedio, estudiante);
    }

    /**
     * Método recursivo auxiliar para la inserción y el balanceo del árbol.
     *
     * @param nodo La raíz del subárbol actual.
     * @param promedio La clave a insertar.
     * @param estudiante El estudiante asociado.
     * @return La raíz del subárbol después de la inserción y las posibles rotaciones.
     * <p>Complejidad Temporal: O(log n)</p>
     * <p>Complejidad Espacial: O(log n) (Recursión)</p>
     */
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
    /**
     * Método público para acceder a la raíz desde otros paquetes (como los reportes).
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public NodoAVL getRaiz() {
        return raiz;
    }
    /**
     * Método público para iniciar el recorrido In-Orden.
     * El recorrido In-Orden produce los elementos ordenados por clave (promedio).
     * <p>Complejidad Temporal: O(n) (Visita todos los nodos).</p>
     * <p>Complejidad Espacial: O(n) en el peor caso (árbol degenerado) o O(log n) promedio (pila recursiva).</p>
     */
    public void inOrden() {
        inOrdenRec(raiz);
    }

    /**
     * Método recursivo auxiliar para el recorrido In-Orden.
     * Recorre: Izquierda -> Nodo -> Derecha.
     * @param nodo La raíz del subárbol actual a recorrer.
     * <p>Complejidad Temporal: O(n)</p>
     * <p>Complejidad Espacial: O(log n) (Altura del árbol balanceado en la pila de recursión).</p>
     */
    private void inOrdenRec(NodoAVL nodo) {
        if (nodo == null) return;

        inOrdenRec(nodo.izq);
        System.out.println(
                nodo.promedio + "  -  " + nodo.estudiante.getNombreCompleto()
        );
        inOrdenRec(nodo.der);
    }
}