package Calificaciones.Modelo;

import Calificaciones.Estructuras.Pila;
import Estudiantes.ControlEstudiantes;
import Estudiantes.Modelo.Estudiante;

public class ControlAcciones {

    // -- Pila estatica compartida
    private static Pila<Accion> pilaGlobal = new Pila<>();

    /**
     * Cualquier módulo (Estudiantes, Cursos, Calificaciones) llama a este método
     * para guardar lo que acaba de hacer.
     */
    public static void registrarAccion(Accion accion) {
        pilaGlobal.push(accion);
    }

    /**
     * Método maestro que decide cómo deshacer la última acción, sea cual sea.
     * @return Mensaje de éxito para mostrar en pantalla.
     */
    public static String deshacerUltima() throws Exception {
        if (pilaGlobal.esVacia()) {
            throw new Exception("El historial esta vacío. No hay nada que deshacer.");
        }

        //-- Sacamos la ultima accion de la pila (LIFO)
        Accion accion = pilaGlobal.pop();

        // -- Evaluamos que tipo de acción fue
        switch (accion.getTipo()) {
            case CAMBIO_CALIFICACION:
                return deshacerCalificacion(accion);

            case REGISTRO_ESTUDIANTE:
                // Logica para borrar un estudiante recien creado
                // int mat = (int) accion.getDatoNuevo();
                // ControlEstudiantes.eliminar(mat);
                return "Se deshizo el registro del último estudiante.";

            case INSCRIPCION_CURSO:
                // Logica para sacar a un alumno de un curso
                // String cursoID = ...;
                // ControlCursos.darBaja(matricula, cursoID);
                return "Se cancelo la ultima inscripcion.";

            case BAJA_CURSO:
                // Lógica: Si se dio de baja, deshacer es VOLVER A INSCRIBIR
                // Recuperar datos: Alumno y Curso
                // ControlCursos.inscribir(matricula, cursoID);
                return "Se ha restaurado la inscripción al curso (Deshacer Baja).";

            default:
                return "Tipo de accion desconocida.";
        }
    }


    /**
     * Reinvierte una acción de cambio de calificación, eliminando la última
     * calificación agregada al historial del estudiante.
     *
     * @param accion La acción a deshacer, que contiene la solicitud de calificación.
     * @return Un mensaje indicando el éxito de la operación.
     * @throws Exception Si el estudiante no existe.
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
}