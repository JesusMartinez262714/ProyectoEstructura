package Calificaciones.Modelo;

import Calificaciones.Estructuras.Pila;
import Estudiantes.ControlEstudiantes;
import Estudiantes.Modelo.Estudiante;
import Cursos.Modelo.ControlInscripciones;

/**
 * Clase controladora encargada de gestionar el historial de acciones y la lógica de "Deshacer".
 * Utiliza una Pila global para almacenar las acciones.
 * <p>Complejidad Espacial General: O(k), donde k es el número de acciones almacenadas en el historial.</p>
 */
public class ControlAcciones {

    // -- Pila estatica compartida
    private static final Pila<Accion> pilaGlobal = new Pila<>();

    private static ControlInscripciones gestorInscripciones;

    /**
     * Establece el gestor de inscripciones necesario para revertir acciones
     * relacionadas con cursos (inscripciones y bajas).
     *
     * @param gestor La instancia del controlador de inscripciones.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public static void setGestorInscripciones(ControlInscripciones gestor) {
        gestorInscripciones = gestor;
    }

    /**
     * Cualquier módulo (Estudiantes, Cursos, Calificaciones) llama a este método
     * para guardar lo que acaba de hacer.
     *
     * @param accion Objeto que contiene los detalles de la operación realizada.
     * <p>Complejidad Temporal: O(1) (Operación push en Pila)</p>
     * <p>Complejidad Espacial: O(1) (Nuevo nodo en la pila)</p>
     */
    public static void registrarAccion(Accion accion) {
        pilaGlobal.push(accion);
    }

    /**
     * Método maestro que decide cómo deshacer la última acción, sea cual sea.
     *
     * @return Mensaje de éxito para mostrar en pantalla.
     * <p>Complejidad Temporal: O(1) para sacar de la pila + Complejidad de la operación específica de deshacer.</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public static String deshacerUltima() throws Exception {
        if (pilaGlobal.esVacia()) {
            throw new Exception("El historial esta vacío. No hay nada que deshacer.");
        }

        //-- Sacamos la ultima accion de la pila (LIFO)
        Accion accion = pilaGlobal.pop();

        // -- Evaluamos que tipo de acción fue
        return switch (accion.getTipo()) {
            case CAMBIO_CALIFICACION -> deshacerCalificacion(accion);
            case REGISTRO_ESTUDIANTE -> deshacerRegistro(accion);
            case INSCRIPCION_CURSO -> deshacerInscripcion(accion);
            case BAJA_CURSO -> deshacerBaja(accion);
            default -> "Tipo de accion desconocida.";
        };
    }


    /**
     * Reinvierte una acción de cambio de calificación, eliminando la última
     * calificación agregada al historial del estudiante.
     *
     * @param accion La acción a deshacer, que contiene la solicitud de calificación.
     * @return Un mensaje indicando el éxito de la operación.
     * @throws Exception Si el estudiante no existe.
     * <p>Complejidad Temporal: O(log n) [búsqueda en BST] + O(c) [eliminación en arreglo de calificaciones].</p>
     */
    private static String deshacerCalificacion(Accion accion) throws Exception {
        // Recuperamos la solicitud que originó el cambio
        SolicitudCalificacion solicitud = (SolicitudCalificacion) accion.getDatoNuevo();

        int matricula = Integer.parseInt(solicitud.getMatriculaEstudiante());

        // Buscamos al estudiante
        Estudiante est = ControlEstudiantes.buscar(matricula);

        if (est == null) {
            throw new Exception("El estudiante " + matricula + " ya no existe.");
        }

        // -- LOGICA DE REVERSION:
        // -- Como procesar fue agregar al final, deshacer es eliminar la ultima calificacion.
        int totalCalificaciones = est.getCalificaciones().getTamanio();

        if (totalCalificaciones > 0) {
            // -- Borramos la ultima posicion (índice = tamaño - 1)
            ControlEstudiantes.eliminarCalificacion(matricula, totalCalificaciones - 1);
            return "Calififcacion eliminada del historial de " + matricula;
        } else {
            return "No se pudo deshacer: El estudiante no tiene calificaciones.";
        }
    }

    /**
     * Deshace el registro de un nuevo estudiante eliminándolo del sistema.
     *
     * @param accion La acción a deshacer, que contiene la matrícula del estudiante creado.
     * @return Mensaje confirmando la eliminación del estudiante.
     * @throws Exception Si ocurre un error al eliminar.
     * <p>Complejidad Temporal: O(log n) (Eliminación en Árbol Binario de Búsqueda).</p>
     */
    private static String deshacerRegistro(Accion accion) throws Exception {
        int mat = (int) accion.getDatoNuevo();
        ControlEstudiantes.eliminar(mat);
        return "Se deshizo el registro del estudiante: " + mat;
    }

    /**
     * Deshace la inscripción de un estudiante a un curso, dándolo de baja.
     *
     * @param accion La acción a deshacer, con los datos de matrícula y curso.
     * @return Mensaje confirmando la cancelación de la inscripción.
     * @throws Exception Si el módulo de cursos no está conectado.
     * <p>Complejidad Temporal: O(1) [hash map] + O(m) [recorrer lista de inscritos para eliminar], donde m es alumnos en curso.</p>
     */
    private static String deshacerInscripcion(Accion accion) throws Exception {
        if (gestorInscripciones == null) throw new Exception("Error: Modulo Cursos no conectado.");

        // Recuperamos datos guardados [0]=Matricula, [1]=CursoID
        Object[] datos = (Object[]) accion.getDatoNuevo();
        int mat = (int) datos[0];
        String curso = (String) datos[1];

        // Lógica Inversa: Si se inscribió, deshacemos dando de BAJA
        if (gestorInscripciones.darBaja(mat, curso)) {
            return "Se cancelo la inscripcion de " + mat + " en " + curso;
        } else {
            return "No se pudo deshacer la inscripción (El alumno ya no estaba en el curso).";
        }
    }

    /**
     * Deshace la baja de un estudiante de un curso, volviéndolo a inscribir.
     *
     * @param accion La acción a deshacer, con los datos de matrícula y curso.
     * @return Mensaje confirmando la restauración de la inscripción.
     * @throws Exception Si el módulo de cursos no está conectado.
     * <p>Complejidad Temporal: O(log n) [buscar estudiante] + O(1) [hash map curso] + O(1) [insertar lista].</p>
     */
    private static String deshacerBaja(Accion accion) throws Exception {
        if (gestorInscripciones == null) throw new Exception("Error: Modulo Cursos no conectado.");

        Object[] datos = (Object[]) accion.getDatoNuevo();
        int mat = (int) datos[0];
        String curso = (String) datos[1];

        // Lógica Inversa: Si se dio de baja, deshacemos volviendo a INSCRIBIR
        gestorInscripciones.inscribir(mat, curso);
        return "Se restauro la inscripcion de " + mat + " en " + curso;
    }
}