/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Modelo;

import Cursos.Estructuras.DiccionarioHash;

/**
 * Clase que gestiona la administración y el almacenamiento de objetos {@code Curso}.
 * Utiliza una estructura de datos de Tabla Hash (DiccionarioHash) para
 * almacenar los cursos, permitiendo operaciones rápidas de inserción,
 * búsqueda y eliminación mediante la clave única del curso.
 * <p>Complejidad Espacial General: O(n), donde n es el número de cursos registrados.</p>
 * * @author Leonel
 */
public class GestionarCursos {

    private DiccionarioHash<String, Curso> cursos;

    /**
     * Constructor para la clase GestionarCursos.
     * Inicializa el DiccionarioHash con un tamaño de tabla de 37.
     * El número 37 es elegido típicamente  para optimizar la distribución de los elementos en la tabla.
     * <p>Complejidad Temporal: O(1) (Inicialización de arreglo de tamaño fijo).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public GestionarCursos() {
        cursos = new DiccionarioHash<>(37);
    }

    /**
     * Agrega un nuevo curso al diccionario.
     * Verifica si la clave del curso ya está registrada para evitar duplicados.
     *
     * @param clave La clave única (identificador) del curso (String).
     * @param nombre El nombre del curso (String).
     * @param cupoMaximo El número máximo de estudiantes que puede tener el curso (int).
     * <p>Complejidad Temporal: O(1) promedio (Gracias a la tabla hash).</p>
     * <p>Complejidad Espacial: O(1) (Crea una nueva instancia de Curso y Nodo).</p>
     */
    public void agregarCurso(String clave, String nombre, int cupoMaximo) {
        if (cursos.obtener(clave) != null) {
            System.out.println("ERROR: La clave " + clave + " ya está registrada.");
            return;
        }

        Curso nuevo = new Curso(clave, nombre, cupoMaximo);
        cursos.insertar(clave, nuevo);
        System.out.println("Curso agregado: " + nuevo);
    }

    /**
     * Elimina un curso del diccionario usando su clave.
     *
     * @param clave La clave del curso a eliminar.
     * <p>Complejidad Temporal: O(1) promedio.</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void eliminarCurso(String clave) {
        boolean eliminado = cursos.eliminar(clave);

        if (eliminado)
            System.out.println("Curso eliminado: " + clave);
        else
            System.out.println("ERROR: No existe un curso con clave " + clave);
    }

    /**
     * Obtiene una lista de Java con todos los cursos.
     * @return Lista de cursos.
     * <p>Complejidad Temporal: O(n) (Debe recorrer toda la tabla hash).</p>
     * <p>Complejidad Espacial: O(n) (Para construir la lista de retorno).</p>
     */
    public java.util.List<Curso> obtenerTodosLosCursos() {
        return cursos.obtenerValores();
    }

    /**
     * Muestra la lista de todos los cursos registrados en el diccionario.
     * La implementación real de la impresión reside en el método {@code mostrar()}
     * de la clase {@code DiccionarioHash}.
     * <p>Complejidad Temporal: O(n)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */

    public void listarCursos() {
        cursos.mostrar();
    }

    /**
     * Recupera un objeto {@code Curso} específico a partir de su clave.
     *
     * @param clave La clave del curso que se desea obtener.
     * @return El objeto {@code Curso} asociado a la clave, o {@code null} si
     * la clave no se encuentra en el diccionario.
     * <p>Complejidad Temporal: O(1) promedio.</p>
     * <p>Complejidad Espacial: O(1)</p>
     */

    public Curso obtenerCurso(String clave) {
        return cursos.obtener(clave);
    }
}