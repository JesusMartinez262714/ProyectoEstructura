package Cursos.Modelo;

import Cursos.Estructuras.ListaCircularSimple;
import Cursos.Estructuras.ListaDobleCircular;
import Cursos.Estructuras.ListaSimple;
import Estudiantes.Modelo.Estudiante;

/**
 * Clase que representa un Curso dentro del sistema académico.
 * Gestiona las listas de estudiantes inscritos, en lista de espera y los roles asignados.
 * <p>Complejidad Espacial General: O(1) por instancia (sin contar el espacio de las estructuras internas que crecen dinámicamente).</p>
 */
public class Curso {

    private String clave;
    private String nombre;
    private int cupoMaximo;

    private ListaSimple<Estudiante> inscritos;
    private ListaDobleCircular<Estudiante> listaEspera;
    private ListaCircularSimple<Estudiante> roles;

    /**
     * Constructor de la clase Curso.
     * Inicializa las listas vacías.
     *
     * @param clave      Clave única del curso.
     * @param nombre     Nombre del curso.
     * @param cupoMaximo Capacidad máxima de estudiantes.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public Curso(String clave, String nombre, int cupoMaximo) {
        this.clave = clave;
        this.nombre = nombre;
        this.cupoMaximo = cupoMaximo;
        inscritos = new ListaSimple<>();
        listaEspera = new ListaDobleCircular<>();
        roles = new ListaCircularSimple<>();
    }

    /**
     * Verifica si hay espacio disponible en el curso.
     *
     * @return true si la cantidad de inscritos es menor al cupo máximo.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public boolean hayCupo() {
        return inscritos.size() < cupoMaximo;
    }

    /**
     * Agrega un estudiante a la lista de inscritos.
     *
     * @param e El estudiante a inscribir.
     * <p>Complejidad Temporal: O(1) (Inserción al final con puntero 'fin').</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void agregarInscrito(Estudiante e) {
        inscritos.agregarFinal(e);
    }

    //metodos
    /**
     * Agrega un estudiante a la lista de espera.
     *
     * @param e El estudiante a agregar.
     * <p>Complejidad Temporal: O(1) (Inserción en lista doble circular).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void agregarListaEspera(Estudiante e) {
        listaEspera.agregar(e);
    }

    /**
     * Obtiene la lista simple de estudiantes inscritos.
     * @return La lista de inscritos.
     * <p>Complejidad Temporal: O(1)</p>
     */
    public ListaSimple<Estudiante> getInscritos() {
        return inscritos;
    }

    /**
     * Obtiene la lista doble circular de estudiantes en espera.
     * @return La lista de espera.
     * <p>Complejidad Temporal: O(1)</p>
     */
    public ListaDobleCircular<Estudiante> getListaEspera() {
        return listaEspera;
    }

    //metodos de roles
    /**
     * Obtiene la lista circular simple de roles.
     * @return La lista de roles.
     * <p>Complejidad Temporal: O(1)</p>
     */
    public ListaCircularSimple<Estudiante> getRoles() {
        return roles;
    }

    /**
     * Agrega un estudiante a la lista de roles.
     *
     * @param e El estudiante a agregar.
     * <p>Complejidad Temporal: O(n) (ListaCircularSimple recorre todo para insertar al final).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void agregarRol(Estudiante e) {
        roles.agregar(e);
    }

    /**
     * Rota el rol actual al siguiente estudiante en la lista circular.
     *
     * @return El estudiante que ahora tiene el rol activo.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public Estudiante rotarRol() {
        return roles.rotar();
    }

    /**
     * Obtiene la clave del curso.
     * @return La clave.
     * <p>Complejidad Temporal: O(1)</p>
     */
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    @Override
    public String toString() {
        return "Curso{" + "clave=" + clave + ", nombre=" + nombre + ", cupoMaximo=" + cupoMaximo + '}';
    }



}