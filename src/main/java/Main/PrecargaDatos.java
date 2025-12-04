package Main;

import Estudiantes.Modelo.Estudiante;
import Estudiantes.Modelo.Direccion;
import Estudiantes.ControlEstudiantes;

/**
 * Clase de utilidad estática encargada de poblar las estructuras de datos globales
 * (BST de estudiantes y Hash Map de cursos) con información inicial de prueba.
 * <p>Complejidad Espacial General: O(1) (No almacena datos, solo llama a la creación de los mismos).</p>
 */
public class PrecargaDatos {

    /**
     * Carga un conjunto de datos predefinidos para pruebas (cursos, estudiantes, calificaciones e inscripciones).
     * <p>Complejidad Temporal: O(1) [Llamadas a funciones hash/BST] + O(N log N) [Inserción en BST] + O(K) [Inserción de calificaciones] = O(N log N).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public static void cargar() {
        try {
            System.out.println("--- INICIANDO CARGA DE DATOS PRE-CARGADOS ---");


            // Inserción de cursos: O(1) promedio por curso (Tabla Hash)
            DatosGlobales.cursos.agregarCurso("MAT101", "Calculo Diferencial", 30);
            DatosGlobales.cursos.agregarCurso("PROG1", "Fundamentos de Programación", 25);
            DatosGlobales.cursos.agregarCurso("EST200", "Estructura de Datos", 40);
            DatosGlobales.cursos.agregarCurso("ING3", "Ingeniería de Software", 20);


            // -- Estudiante 1: Juan (O(log n) inserción en BST) --
            Estudiante e1 = crearEstudiante(100, "Juan Perez", "6441-101010");
            DatosGlobales.estudiantes.insertarEstudiante(e1);
            // Calificaciones O(K) por la copia de arreglos
            e1.getCalificaciones().agregarCalificacion(100.0f);
            e1.getCalificaciones().agregarCalificacion(95.0f);
            e1.getCalificaciones().agregarCalificacion(98.5f);

            // -- Estudiante 2: Maria (O(log n) inserción en BST) --
            Estudiante e2 = crearEstudiante(101, "Maria Lopez", "6441-202020");
            DatosGlobales.estudiantes.insertarEstudiante(e2);
            e2.getCalificaciones().agregarCalificacion(80.0f);
            e2.getCalificaciones().agregarCalificacion(85.0f);
            e2.getCalificaciones().agregarCalificacion(90.0f);

            // -- Estudiante 3: Pedro (O(log n) inserción en BST) --
            Estudiante e3 = crearEstudiante(102, "Pedro Sanchez", "6441-303030");
            DatosGlobales.estudiantes.insertarEstudiante(e3);
            e3.getCalificaciones().agregarCalificacion(70.0f);
            e3.getCalificaciones().agregarCalificacion(65.0f);
            e3.getCalificaciones().agregarCalificacion(60.0f);

            // -- Estudiante 4: Ana (O(log n) inserción en BST) --
            Estudiante e4 = crearEstudiante(103, "Ana Garcia", "6441-404040");
            DatosGlobales.estudiantes.insertarEstudiante(e4);


            // Inscripción manual: O(log n) buscar estudiante + O(1) buscar curso + O(1) insertar en lista simple.
            // O(k) para insertar rol (recorrido lista circular).
            inscribirManual(100, "EST200"); // Juan
            inscribirManual(101, "EST200"); // Maria
            inscribirManual(102, "EST200"); // Pedro
            inscribirManual(103, "EST200"); // Ana

            // Inscribimos a Juan en Programación también
            inscribirManual(100, "PROG1");

            System.out.println("--- DATOS DE PRUEBA CARGADOS EXITOSAMENTE ---");

        } catch (Exception e) {
            System.out.println("Error al cargar datos de prueba: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para crear una instancia de Estudiante con datos genéricos.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private static Estudiante crearEstudiante(int matricula, String nombre, String tel) {
        Direccion dir = new Direccion("Calle 5 de Febrero", "123", "Centro", "Cd. Obregon");
        return new Estudiante(matricula, nombre, tel, "alumno@itson.edu.mx", dir);
    }

    /**
     * Método auxiliar para inscribir directamente saltándose validaciones de UI.
     * <p>Complejidad Temporal: O(log n) [Buscar estudiante] + O(1) [Buscar curso] + O(k) [Insertar rol].</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    private static void inscribirManual(int matricula, String idCurso) {
        try {
            Estudiante est = ControlEstudiantes.buscar(matricula);
            Cursos.Modelo.Curso curso = DatosGlobales.cursos.obtenerCurso(idCurso);

            if (est != null && curso != null) {
                // Lo metemos a la lista de inscritos
                curso.agregarInscrito(est);
                // Lo metemos a la lista circular de roles (IMPORTANTE PARA ROTACIÓN)
                curso.agregarRol(est);
            }
        } catch (Exception e) {
            // Ignoramos errores aquí porque son datos controlados
        }
    }
}