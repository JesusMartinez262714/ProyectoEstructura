package Estudiantes.Estructuras;

import Calificaciones.Estructuras.Nodo;
import Estudiantes.Modelo.Estudiante;
import Estudiantes.Modelo.NodoBST;

/**
 * Clase que implementa un Árbol Binario de Búsqueda (BST) para gestionar estudiantes.
 * Permite inserción, eliminación, búsqueda y recorridos.
 * <p>Complejidad Espacial General: O(n), donde n es el número de estudiantes.</p>
 */
public class BSTEstudiantes {
    private NodoBST raiz;

    /**
     * Inserta un nuevo estudiante en el árbol manteniendo el orden por matrícula.
     * @param estudiante El estudiante a insertar.
     * @throws Exception Si el estudiante ya existe.
     * <p>Complejidad Temporal: O(log n) promedio, O(n) peor caso.</p>
     * <p>Complejidad Espacial: O(log n) promedio (pila recursiva).</p>
     */
    public void insertarEstudiante(Estudiante estudiante) throws Exception {
        raiz = insertarRecursivo(raiz,estudiante);
    }

    /**
     * Elimina un estudiante del árbol por su matrícula.
     * @param matricula La matrícula del estudiante a eliminar.
     * @throws Exception Si el estudiante no se encuentra.
     * <p>Complejidad Temporal: O(log n) promedio, O(n) peor caso.</p>
     * <p>Complejidad Espacial: O(log n) promedio (pila recursiva).</p>
     */
    public void eliminar(int matricula) throws Exception {
        raiz = eliminarRec(raiz, matricula);
    }

    /**
     * Busca un nodo que contenga al estudiante con la matrícula dada.
     * @param matricula La matrícula a buscar.
     * @return El nodo encontrado o null.
     * @throws Exception Si no existe (aunque el método retorna null en ese caso interno).
     * <p>Complejidad Temporal: O(log n) promedio, O(n) peor caso.</p>
     * <p>Complejidad Espacial: O(log n) promedio (pila recursiva).</p>
     */
    public NodoBST buscarEstudiante(int matricula) throws Exception {
        return buscarRecursivo(raiz,matricula);
    }

    /**
     * Realiza un recorrido In-Orden (Izquierda - Raíz - Derecha).
     * @param accion La acción a realizar con cada nodo visitado.
     * <p>Complejidad Temporal: O(n) (Visita todos los nodos).</p>
     * <p>Complejidad Espacial: O(log n) promedio (pila recursiva).</p>
     */
    public void inOrden(java.util.function.Consumer<NodoBST> accion) {
        inOrdenRecursivo(raiz, accion);
    }

    /**
     * Realiza un recorrido Pre-Orden (Raíz - Izquierda - Derecha).
     * <p>Complejidad Temporal: O(n)</p>
     * <p>Complejidad Espacial: O(log n) promedio.</p>
     */
    public void preOrden() {
        preOrdenRecursivo(raiz);
    }

    /**
     * Realiza un recorrido Post-Orden (Izquierda - Derecha - Raíz).
     * <p>Complejidad Temporal: O(n)</p>
     * <p>Complejidad Espacial: O(log n) promedio.</p>
     */
    public void postOrden() {
        postOrdenRecursivo(raiz);
    }

    private NodoBST insertarRecursivo(NodoBST nodoActual,Estudiante estudiante) throws Exception {
        if(nodoActual == null){
            return new NodoBST(estudiante);
        }
        if(estudiante.getMatricula()<nodoActual.getEstudiante().getMatricula()){
            nodoActual.setIzquierdo(insertarRecursivo(nodoActual.getIzquierdo(),estudiante));
        }else if(estudiante.getMatricula() > nodoActual.getEstudiante().getMatricula()){
            nodoActual.setDerecho(insertarRecursivo(nodoActual.getDerecho(),estudiante));
        }else{
            throw new Exception("Ya existe este estudiante");
        }
        return nodoActual;
    }

    private NodoBST eliminarRec(NodoBST nodo, int matricula) throws Exception {
        if (nodo == null) {
            throw new Exception("No se encontró el estudiante con matrícula " + matricula);
        }

        int actual = nodo.getEstudiante().getMatricula();

        if (matricula < actual) {
            nodo.setIzquierdo(eliminarRec(nodo.getIzquierdo(), matricula));
        }
        else if (matricula > actual) {
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), matricula));
        }
        else {

            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                return null;
            }


            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            }
            if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }


            NodoBST sucesor = encontrarMin(nodo.getDerecho());
            nodo.setEstudiante(sucesor.getEstudiante());
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), sucesor.getEstudiante().getMatricula()));
        }

        return nodo;
    }


    private NodoBST buscarRecursivo(NodoBST nodoActual,int  matricula) throws Exception {
        if(nodoActual == null){
            System.out.println("No existe estudiante");
            return null;
        }
        if(nodoActual.getEstudiante().getMatricula() == matricula){
            System.out.println("Estudiante encontrado");
            return nodoActual;
        }else if(matricula < nodoActual.getEstudiante().getMatricula()){
            return buscarRecursivo(nodoActual.getIzquierdo(),matricula);
        }

        return (buscarRecursivo(nodoActual.getDerecho(),matricula));
    }

    private void inOrdenRecursivo(NodoBST nodoActual, java.util.function.Consumer<NodoBST> accion) {
        if (nodoActual == null) {
            return;
        }
        inOrdenRecursivo(nodoActual.getIzquierdo(), accion);
        accion.accept(nodoActual);
        inOrdenRecursivo(nodoActual.getDerecho(), accion);
    }

    private void preOrdenRecursivo(NodoBST nodoActual) {
        if (nodoActual == null) {
            return;
        }
        System.out.println(nodoActual.getEstudiante());
        preOrdenRecursivo(nodoActual.getIzquierdo());
        preOrdenRecursivo(nodoActual.getDerecho());
    }
    private void postOrdenRecursivo(NodoBST nodoActual) {
        if (nodoActual == null) {
            return;
        }

        postOrdenRecursivo(nodoActual.getIzquierdo());
        postOrdenRecursivo(nodoActual.getDerecho());
        System.out.println(nodoActual.getEstudiante());
    }

    /**
     * Encuentra el nodo con el valor mínimo en un subárbol (el más a la izquierda).
     * @param nodo Raíz del subárbol.
     * @return El nodo mínimo.
     * <p>Complejidad Temporal: O(h) donde h es la altura.</p>
     * <p>Complejidad Espacial: O(1) (Iterativo).</p>
     */
    private NodoBST encontrarMin(NodoBST nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }


    /**
     * Cuenta el número total de nodos en el árbol.
     * @return Cantidad de nodos.
     * <p>Complejidad Temporal: O(n)</p>
     * <p>Complejidad Espacial: O(log n) promedio.</p>
     */
    public int contarNodos() {
        return contarNodosRec(raiz);
    }

    private int contarNodosRec(NodoBST nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodosRec(nodo.getIzquierdo()) + contarNodosRec(nodo.getDerecho());
    }

    /**
     * Obtiene el estudiante que se encuentra en una posición específica 
     * según el recorrido In-Orden (útil para tablas).
     * @param pos La posición deseada (0-indexed).
     * @return El estudiante en esa posición.
     * <p>Complejidad Temporal: O(n) (en el peor caso debe recorrer todo el árbol).</p>
     * <p>Complejidad Espacial: O(log n) promedio.</p>
     */
    public Estudiante obtenerEnPosicion(int pos) {
        Contador c = new Contador();
        return obtenerEnPosicionRec(raiz, pos, c);
    }

    private Estudiante obtenerEnPosicionRec(NodoBST nodo, int pos, Contador c) {
        if (nodo == null) return null;

        Estudiante izq = obtenerEnPosicionRec(nodo.getIzquierdo(), pos, c);
        if (izq != null) return izq;

        if (c.valor == pos) return nodo.getEstudiante();
        c.valor++;

        return obtenerEnPosicionRec(nodo.getDerecho(), pos, c);
    }

    private static class Contador {
        int valor = 0;
    }



    public NodoBST getRaiz() {
        return raiz;
    }

}