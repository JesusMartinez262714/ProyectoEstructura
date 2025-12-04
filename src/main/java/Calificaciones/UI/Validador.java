package Calificaciones.UI;

import java.util.regex.Pattern;

/**
 * Clase de utilidad que proporciona métodos estáticos para la validación de formatos de datos.
 * Utiliza expresiones regulares precompiladas para optimizar el rendimiento.
 * <p>Complejidad Espacial General: O(1) (Las instancias de Pattern son estáticas y reutilizables).</p>
 */
public class Validador {

    private static final Pattern PATRON_MATRICULA= Pattern.compile("^\\d+$");
    private static final Pattern PATRON_CURSO= Pattern.compile("^[A-Z0-9]{3,7}$");

    /**
     * Verifica si una matrícula es válida.
     * Una matrícula se considera válida si no es nula y está compuesta únicamente por dígitos.
     *
     * @param matricula La cadena que representa la matrícula a validar.
     * @return {@code true} si la matrícula cumple con el patrón numérico, {@code false} en caso contrario.
     * <p>Complejidad Temporal: O(n), donde n es la longitud de la cadena.</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public static boolean esMatriculaValida(String matricula) {
        return matricula != null && PATRON_MATRICULA.matcher(matricula).matches();
    }

    /**
     * Verifica si un código de curso es válido.
     * Un curso válido debe tener entre 3 y 7 caracteres alfanuméricos (mayúsculas y números).
     *
     * @param curso La cadena que representa el código del curso.
     * @return {@code true} si el código cumple con el patrón, {@code false} en caso contrario.
     * <p>Complejidad Temporal: O(n), donde n es la longitud de la cadena.</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public static boolean esCursoValido(String curso) {
        return curso != null && PATRON_CURSO.matcher(curso).matches();
    }

    /**
     * Verifica si una nota (calificación) es válida.
     * La nota debe ser un valor numérico (flotante) comprendido entre 0.0 y 100.0.
     *
     * @param textoNota La cadena de texto que contiene la nota.
     * @return {@code true} si el texto es un número válido en el rango [0, 100], {@code false} si no es un número o está fuera de rango.
     * <p>Complejidad Temporal: O(n) (Parseo de la cadena a float).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public static boolean esNotaValida(String textoNota){
        try{
            float nota= Float.parseFloat(textoNota);
            return nota>=0.0f && nota<=100.0f;
        }catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Comprueba si una cadena de texto contiene información útil (no es nula ni está vacía).
     *
     * @param s La cadena a evaluar.
     * @return {@code true} si la cadena contiene al menos un carácter que no sea espacio en blanco.
     * <p>Complejidad Temporal: O(n) (trim recorre la cadena).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public static boolean hayTexto(String s){
        return s!=null && !s.trim().isEmpty();
    }
}