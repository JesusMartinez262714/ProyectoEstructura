package Cursos.Estructuras;

import Estudiantes.Modelo.Estudiante;

/**
 * Clase que representa un nodo para el Árbol AVL.
 * Contiene la información del estudiante, el promedio (clave de ordenamiento),
 * la altura del nodo para el balanceo y referencias a los hijos izquierdo y derecho.
 * <p>Complejidad Espacial General: O(1) por cada instancia de NodoAVL creada.</p>
 */
public class NodoAVL {

    public double promedio;
    public Estudiante<?> estudiante;
    public int altura;

    public NodoAVL izq;
    public NodoAVL der;

    /**
     * Constructor que inicializa un nuevo nodo AVL.
     * La altura inicial de un nodo hoja siempre es 1.
     *
     * @param promedio   El promedio del estudiante (clave de búsqueda/ordenamiento).
     * @param estudiante El objeto estudiante asociado.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public NodoAVL(double promedio, Estudiante<?> estudiante) {
        this.promedio = promedio;
        this.estudiante = estudiante;
        this.altura = 1;
    }
}