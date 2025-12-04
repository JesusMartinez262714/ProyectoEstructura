package Estudiantes;

import Cursos.Modelo.AVL_Promedios;
import Estudiantes.Estructuras.BSTEstudiantes;
import Estudiantes.Modelo.Estudiante;
import Estudiantes.Modelo.NodoBST;
import Main.DatosGlobales;

public class ControlEstudiantes {


    private static final BSTEstudiantes arbol = DatosGlobales.estudiantes;

    public static int contar() {
        return arbol.contarNodos();
    }

    /** Devuelve un estudiante en la posición (según inorden) */
    public static Estudiante obtenerEnPosicion(int pos) {
        return arbol.obtenerEnPosicion(pos);
    }

    /** Busca un estudiante por matrícula */
    public static Estudiante buscar(int matricula) throws Exception {
        NodoBST nodo = arbol.buscarEstudiante(matricula);
        return nodo == null ? null : nodo.getEstudiante();
    }

    /** Devuelve true si existe un estudiante con la matrícula dada */
    public static boolean existe(int matricula) throws Exception {
        return buscar(matricula) != null;
    }



    /** Inserta un estudiante nuevo */
    public static void insertar(Estudiante e) throws Exception {
        arbol.insertarEstudiante(e);
    }



    /** Elimina un estudiante por matrícula */
    public static void eliminar(int matricula) throws Exception {
        arbol.eliminar(matricula);
    }




    /** Modifica los datos PERSONALES de un estudiante */
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




    /** Agrega una calificación al estudiante */
    public static void agregarCalificacion(int matricula, float calif) throws Exception {

        Estudiante est = buscar(matricula);
        if (est == null)
            throw new Exception("No existe estudiante con matrícula " + matricula);

        est.getCalificaciones().agregarCalificacion(calif);
    }

    /** Modifica una calificación existente */
    public static void modificarCalificacion(int matricula, int indice, float nueva) throws Exception {

        Estudiante est = buscar(matricula);
        if (est == null)
            throw new Exception("No existe estudiante");

        est.getCalificaciones().modificarCalificacion(indice, nueva);
    }

    /** Elimina una calificación del estudiante */
    public static void eliminarCalificacion(int matricula, int indice) throws Exception {

        Estudiante est = buscar(matricula);
        if (est == null)
            throw new Exception("No existe estudiante");

        est.getCalificaciones().eliminarCalificacion(indice);
    }

    /** Obtiene el promedio final */
    public static float obtenerPromedio(int matricula) throws Exception {

        Estudiante est = buscar(matricula);
        if (est == null)
            throw new Exception("No existe estudiante");

        return est.getCalificaciones().calcularPromedio();
    }

    /** Devuelve una calificación específica */
    public static float obtenerCalificacion(int matricula, int indice) throws Exception {

        Estudiante est = buscar(matricula);
        if (est == null)
            throw new Exception("No existe estudiante");

        return ((Number) est.getCalificaciones().obtenerCalificacion(indice)).floatValue();
    }




        public static AVL_Promedios generarAVLPromedios() {
        AVL_Promedios avl = new AVL_Promedios();
        recorrerBSTyAgregar(arbol.getRaiz(), avl);
        return avl;
    }

    private static void recorrerBSTyAgregar(NodoBST nodo, AVL_Promedios avl) {
        if (nodo == null) return;

        Estudiante e = nodo.getEstudiante();
        avl.insertar(e.getPromedio(), e);

        recorrerBSTyAgregar(nodo.getIzquierdo(), avl);
        recorrerBSTyAgregar(nodo.getDerecho(), avl);
    }
}
