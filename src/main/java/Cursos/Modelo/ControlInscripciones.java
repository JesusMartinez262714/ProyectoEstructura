package Cursos.Modelo;

import Calificaciones.Modelo.Accion;
import Calificaciones.Modelo.ControlAcciones;
import Estudiantes.ControlEstudiantes;
import Estudiantes.Modelo.Estudiante;
import Main.DatosGlobales; // Importante para el historial

/**
 * Clase encargada de meter a los alumnos a los cursos.
 * Checa que existan, que haya cupo y guarda el historial por si la regamos.
 * <p>Complejidad Espacial General: O(1) (No almacena datos propios, solo gestiona).</p>
 */
public class ControlInscripciones {

    // Usamos nombres claros para saber a quién le pedimos los datos
    private ControlEstudiantes controlDeEstudiantes;
    private GestionarCursos gestorDeCursos;

    /**
     * Constructor que recibe las instancias de los controladores.
     * @param controlRecibido Controlador de estudiantes.
     * @param gestorRecibido Gestor de cursos.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public ControlInscripciones(ControlEstudiantes controlRecibido, GestionarCursos gestorRecibido) {
        this.controlDeEstudiantes = controlRecibido;
        this.gestorDeCursos = gestorRecibido;
    }

    /**
     * Intenta inscribir a un alumno.
     * Si pasa, lo anota en el curso y guarda la acción para poder deshacerla luego.
     * <p>Complejidad Temporal: O(log n) [Buscar estudiante] + O(1) [Buscar curso] + O(1) [Insertar en lista].</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void inscribir(int matriculaDelEstudiante, String claveDelCurso) throws Exception {

        // 1. Buscamos al alumno con un nombre de variable que se entienda
        Estudiante estudianteAInscribir = controlDeEstudiantes.buscar(matriculaDelEstudiante);
        if (estudianteAInscribir == null) {
            // Lanzamos error en lugar de solo imprimir, para que la pantalla avise
            throw new Exception("Oye, no encontré a ningún estudiante con la matrícula " + matriculaDelEstudiante);
        }

        // 2. Buscamos el curso
        Curso cursoSeleccionado = gestorDeCursos.obtenerCurso(claveDelCurso);
        if (cursoSeleccionado == null) {
            throw new Exception("El curso " + claveDelCurso + " no existe, checa bien la clave.");
        }

        // 3. Verificamos si cabe
        if (cursoSeleccionado.hayCupo()) {

            // ¡Sí cupo! Lo metemos a la lista de inscritos
            cursoSeleccionado.agregarInscrito(estudianteAInscribir);

            // También lo agregamos a la lista de roles (para lo del líder/tutor)
            cursoSeleccionado.getRoles().agregar(estudianteAInscribir);

            // --- AQUÍ ESTÁ LA MAGIA DEL "DESHACER" ---
            // Guardamos qué hicimos: [Matrícula, Curso]
            Object[] datosDeLaInscripcion = { matriculaDelEstudiante, claveDelCurso };

            // Creamos la ficha de la acción
            Accion accionRealizada = new Accion(
                    Accion.TipoAccion.INSCRIPCION_CURSO, // Tipo de acción
                    null, // No había nada antes
                    datosDeLaInscripcion // Datos para poder borrarlo después si nos arrepentimos
            );

            // Lo mandamos a la pila del historial
            ControlAcciones.registrarAccion(accionRealizada);
            // ------------------------------------------

            System.out.println("Listo: " + estudianteAInscribir.getNombreCompleto() + " inscrito en " + cursoSeleccionado.getNombre());

        } else {
            // No cupo, va para la lista de espera
            cursoSeleccionado.agregarListaEspera(estudianteAInscribir);
            System.out.println("El curso estaba lleno. Se fue a lista de espera.");
        }
    }

    /**
     * Método para sacar a un alumno de un curso.
     * Este lo usa el botón "Deshacer" cuando queremos revertir una inscripción.
     * <p>Complejidad Temporal: O(m), donde m es el número de alumnos inscritos en el curso (debe buscar para eliminar).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public boolean darBaja(int matriculaDelEstudiante, String claveDelCurso) {

        Curso cursoObjetivo = gestorDeCursos.obtenerCurso(claveDelCurso);
        if (cursoObjetivo == null) return false;

        // Buscamos y eliminamos al alumno de la lista usando una expresión lambda (más pro)
        boolean sePudoEliminar = cursoObjetivo.getInscritos().eliminarSi(
                alumno -> alumno.getMatricula() == matriculaDelEstudiante
        );

        if (sePudoEliminar) {
            System.out.println(">> DESHACER: Se eliminó al alumno " + matriculaDelEstudiante + " del curso.");
        }

        return sePudoEliminar;
    }
}