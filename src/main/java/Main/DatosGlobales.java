package Main;

import Estudiantes.Estructuras.BSTEstudiantes;
import Cursos.Modelo.GestionarCursos;

/**
 * Clase que actúa como un contenedor global de datos (Base de Datos en memoria).
 * Utiliza variables estáticas públicas para permitir que todas las ventanas
 * y paneles accedan a las mismas instancias únicas de los gestores.
 * <p>Complejidad Espacial General: O(1) (Solo mantiene las referencias estáticas, el peso real está en los objetos apuntados).</p>
 */
public class DatosGlobales {

    /**
     * Instancia única del Árbol Binario de Búsqueda para estudiantes.
     * <p>Complejidad Temporal de Inicialización: O(1)</p>
     */
    public static BSTEstudiantes estudiantes = new BSTEstudiantes();

    /**
     * Instancia única del Gestor de Cursos (Tabla Hash).
     * <p>Complejidad Temporal de Inicialización: O(1) (o O(M) donde M es el tamaño fijo de la tabla hash).</p>
     */
    public static GestionarCursos cursos = new GestionarCursos();
}