package Estudiantes;

import Cursos.Modelo.AVL_Promedios;
import Estudiantes.Estructuras.BSTEstudiantes;
import Estudiantes.Modelo.Estudiante;
import Estudiantes.Modelo.NodoBST;
import Main.DatosGlobales;

/**
 * Clase controladora estática que sirve de intermediario entre la interfaz gráfica (UI)
 * y la estructura de datos principal (BST) de estudiantes.
 * Centraliza la lógica de negocio y validaciones.
 * <p>Complejidad Espacial General: O(1) (No almacena datos, solo accede a DatosGlobales).</p>
 */
public class ControlEstudiantes {


    private static final BSTEstudiantes arbol = DatosGlobales.estudiantes;

    /**
     * Cuenta el número total de estudiantes registrados.
     * @return Cantidad de nodos en el árbol.
     * <p>Complejidad Temporal: O(N) (Recorre todo el árbol).</p>
     * <p>Complejidad Espacial: O(log N) (Pila recursiva).</p>
     */
    public static int contar() {
        return arbol.contarNodos();
    }

    /** * Devuelve un estudiante en la posición específica según el recorrido In-Orden.
     * @param pos Posición (índice) deseada.
     * @return El estudiante encontrado o null.
     * <p>Complejidad Temporal: O(N) (En el peor caso recorre N nodos).</p>
     * <p>Complejidad Espacial: O(log N) promedio.</p>
     */
    public static Estudiante obtenerEnPosicion(int pos) {
        return arbol.obtenerEnPosicion(pos);
    }

    /** * Busca un estudiante por matrícula.
     * @param matricula Matrícula a buscar.
     * @return El estudiante o null si no existe.
     * @throws Exception Errores internos del árbol.
     * <p>Complejidad Temporal: O(log N) promedio, O(N) peor caso (árbol desbalanceado).</p>
     * <p>Complejidad Espacial: O(log N) promedio.</p>
     */
    public static Estudiante buscar(int matricula) throws Exception {
        NodoBST nodo = arbol.buscarEstudiante(matricula);
        return nodo == null ? null : nodo.getEstudiante();
    }

    /** * Verifica si existe un estudiante con la matrícula dada.
     * @param matricula Matrícula a verificar.
     * @return true si existe, false en caso contrario.
     * <p>Complejidad Temporal: O(log N) promedio.</p>
     * <p>Complejidad Espacial: O(log N) promedio.</p>
     */
    public static boolean existe(int matricula) throws Exception {
        return buscar(matricula) != null;
    }



    /** * Inserta un estudiante nuevo en el sistema (BST).
     * @param e Estudiante a insertar.
     * <p>Complejidad Temporal: O(log N) promedio.</p>
     * <p>Complejidad Espacial: O(log N) promedio.</p>
     */
    public static void insertar(Estudiante e) throws Exception {
        arbol.insertarEstudiante(e);
    }



    /** * Elimina un estudiante por matrícula.
     * @param matricula Matrícula del estudiante a borrar.
     * <p>Complejidad Temporal: O(log N) promedio.</p>
     * <p>Complejidad Espacial: O(log N) promedio.</p>
     */
    public static void eliminar(int matricula) throws Exception {
        arbol.eliminar(matricula);
    }




    /** * Modifica los datos PERSONALES de un estudiante.
     * Primero busca al estudiante y luego actualiza sus campos.
     * @param matricula Clave de búsqueda.
     * @param nombre Nuevo nombre.
     * @param telefono Nuevo teléfono.
     * @param correo Nuevo correo.
     * <p>Complejidad Temporal: O(log N) (Búsqueda).</p>
     * <p>Complejidad Espacial: O(log N).</p>
     */
    public static void modificarDatosPersonales(
            int matricula,
            String nombre,
            String telefono,
            String correo
    ) throws Exception {

        Estudiante est = buscar(matricula);
        if (est == null)
            throw new Exception("No existe estudiante con matrícula " + matricula);

        est.setNombreCompleto(nombre);
        est.setNumeroTelefono(telefono);
        est.setCorreo(correo);
    }


    /**
     * Modifica la dirección de un estudiante.
     * @param matricula Clave de búsqueda.
     * @param calle Nueva calle.
     * @param numero Nuevo número.
     * @param colonia Nueva colonia.
     * @param ciudad Nueva ciudad.
     * <p>Complejidad Temporal: O(log N) (Búsqueda).</p>
     * <p>Complejidad Espacial: O(log N).</p>
     */
    public static void modificarDireccion(
            int matricula,
            String calle,
            String numero,
            String colonia,
            String ciudad
    ) throws Exception {

        Estudiante est = buscar(matricula);
        if (est == null)
            throw new Exception("No existe estudiante con matrícula " + matricula);

        est.getDireccion().setCalle(calle);
        est.getDireccion().setNumero(numero);
        est.getDireccion().setColonia(colonia);
        est.getDireccion().setCiudad(ciudad);
    }




    /** * Agrega una calificación al estudiante.
     * Busca al estudiante en el árbol y luego inserta en su arreglo dinámico.
     * @param matricula Matrícula del estudiante.
     * @param calif Calificación a agregar.
     * <p>Complejidad Temporal: O(log N) [Buscar] + O(K) [Redimensionar arreglo de notas], donde K es cant. notas.</p>
     * <p>Complejidad Espacial: O(log N) + O(K) [Nuevo arreglo].</p>
     */
    public static void agregarCalificacion(int matricula, float calif) throws Exception {

        Estudiante est = buscar(matricula);
        if (est == null)
            throw new Exception("No existe estudiante con matrícula " + matricula);

        est.getCalificaciones().agregarCalificacion(calif);
    }

    /** * Modifica una calificación existente.
     * @param matricula Matrícula del estudiante.
     * @param indice Índice en el arreglo de notas.
     * @param nueva Nueva calificación.
     * <p>Complejidad Temporal: O(log N) [Buscar] + O(1) [Acceso directo arreglo].</p>
     * <p>Complejidad Espacial: O(log N).</p>
     */
    public static void modificarCalificacion(int matricula, int indice, float nueva) throws Exception {

        Estudiante est = buscar(matricula);
        if (est == null)
            throw new Exception("No existe estudiante");

        est.getCalificaciones().modificarCalificacion(indice, nueva);
    }

    /** * Elimina una calificación del estudiante.
     * @param matricula Matrícula del estudiante.
     * @param indice Índice de la nota a borrar.
     * <p>Complejidad Temporal: O(log N) [Buscar] + O(K) [Desplazar elementos arreglo].</p>
     * <p>Complejidad Espacial: O(log N) + O(K).</p>
     */
    public static void eliminarCalificacion(int matricula, int indice) throws Exception {

        Estudiante est = buscar(matricula);
        if (est == null)
            throw new Exception("No existe estudiante");

        est.getCalificaciones().eliminarCalificacion(indice);
    }

    /** * Obtiene el promedio final calculado.
     * @param matricula Matrícula del estudiante.
     * @return Promedio.
     * <p>Complejidad Temporal: O(log N) [Buscar] + O(K) [Suma recursiva de notas].</p>
     * <p>Complejidad Espacial: O(log N) + O(K) [Pila recursiva promedio].</p>
     */
    public static float obtenerPromedio(int matricula) throws Exception {

        Estudiante est = buscar(matricula);
        if (est == null)
            throw new Exception("No existe estudiante");

        return est.getCalificaciones().calcularPromedio();
    }

    /** * Devuelve una calificación específica.
     * @param matricula Matrícula del estudiante.
     * @param indice Índice de la nota.
     * <p>Complejidad Temporal: O(log N) + O(1).</p>
     * <p>Complejidad Espacial: O(log N).</p>
     */
    public static float obtenerCalificacion(int matricula, int indice) throws Exception {

        Estudiante est = buscar(matricula);
        if (est == null)
            throw new Exception("No existe estudiante");

        return ((Number) est.getCalificaciones().obtenerCalificacion(indice)).floatValue();
    }




    /**
     * Genera un nuevo Árbol AVL ordenado por promedios a partir de los datos del BST.
     * Recorre todo el BST y re-inserta los nodos en la nueva estructura balanceada.
     * @return Instancia de AVL_Promedios.
     * <p>Complejidad Temporal: O(N log N) (Recorre N nodos del BST e inserta cada uno en AVL con costo log N).</p>
     * <p>Complejidad Espacial: O(N) (Crea una nueva estructura que duplica las referencias).</p>
     */
    public static AVL_Promedios generarAVLPromedios() {
        AVL_Promedios avl = new AVL_Promedios();
        recorrerBSTyAgregar(arbol.getRaiz(), avl);
        return avl;
    }

    /**
     * Método auxiliar recursivo para transferir datos del BST al AVL.
     * @param nodo Nodo actual del BST.
     * @param avl Árbol destino AVL.
     * <p>Complejidad Temporal: O(N) (Visita todos los nodos).</p>
     * <p>Complejidad Espacial: O(log N) (Pila recursiva del recorrido).</p>
     */
    private static void recorrerBSTyAgregar(NodoBST nodo, AVL_Promedios avl) {
        if (nodo == null) return;

        Estudiante e = nodo.getEstudiante();
        avl.insertar(e.getPromedio(), e);

        recorrerBSTyAgregar(nodo.getIzquierdo(), avl);
        recorrerBSTyAgregar(nodo.getDerecho(), avl);
    }
}