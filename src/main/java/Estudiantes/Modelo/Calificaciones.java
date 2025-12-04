package Estudiantes.Modelo;

/**
 * Clase genérica que gestiona un arreglo dinámico de calificaciones.
 * Funciona como una lista personalizada que permite agregar, eliminar y calcular promedios.
 * <p>Complejidad Espacial General: O(n), donde n es el número de calificaciones almacenadas.</p>
 *
 * @param <T> El tipo de dato de la calificación (generalmente Number, Float, Double).
 */
public class Calificaciones<T> {

    private T[] calificaciones;
    private int tamanio;
    private int capacidad;

    /**
     * Constructor de la clase Calificaciones.
     * Inicializa el arreglo con una capacidad definida.
     *
     * @param capacidad Capacidad inicial del contenedor.
     * <p>Complejidad Temporal: O(1) (Asignación de espacio).</p>
     * <p>Complejidad Espacial: O(n) (Reserva de memoria para el arreglo).</p>
     */
    @SuppressWarnings("unchecked")
    public Calificaciones(int capacidad) {

        if (capacidad <= 0) {
            capacidad = 1;
        }

        this.capacidad = capacidad;
        this.tamanio = 0;
        this.calificaciones = (T[]) new Object[capacidad];
    }

    /**
     * Agrega una nueva calificación al arreglo.
     * Si el arreglo está lleno, crea uno nuevo con tamaño +1 y copia los elementos.
     *
     * @param calificacion La calificación a agregar.
     * <p>Complejidad Temporal: O(n) en el peor caso (cuando hay que redimensionar y copiar).</p>
     * <p>Complejidad Espacial: O(n) (Creación temporal de nuevo arreglo en redimensionamiento).</p>
     */
    public void agregarCalificacion(T calificacion) {

        if (tamanio == capacidad) {
            capacidad++;
            @SuppressWarnings("unchecked")
            T[] nuevo = (T[]) new Object[capacidad];

            for (int i = 0; i < tamanio; i++) {
                nuevo[i] = calificaciones[i];
            }

            calificaciones = nuevo;
        }

        calificaciones[tamanio] = calificacion;
        tamanio++;
    }

    /**
     * Elimina una calificación en una posición específica.
     * Crea un nuevo arreglo de tamaño n-1 y copia los elementos restantes.
     *
     * @param indice El índice del elemento a eliminar.
     * @throws ArrayIndexOutOfBoundsException Si el índice no es válido.
     * <p>Complejidad Temporal: O(n) (Debe copiar todos los elementos menos uno).</p>
     * <p>Complejidad Espacial: O(n) (Creación del nuevo arreglo reducido).</p>
     */
    public void eliminarCalificacion(int indice) {

        if (indice < 0 || indice >= tamanio) {
            throw new ArrayIndexOutOfBoundsException("Índice fuera de rango.");
        }

        if (tamanio == 1) {
            tamanio = 0;
            capacidad = 1;
            calificaciones = (T[]) new Object[1];
            return;
        }

        capacidad--;
        @SuppressWarnings("unchecked")
        T[] nuevo = (T[]) new Object[capacidad];

        int j = 0;
        for (int i = 0; i < tamanio; i++) {
            if (i != indice) {
                nuevo[j] = calificaciones[i];
                j++;
            }
        }

        calificaciones = nuevo;
        tamanio--;
    }

    /**
     * Modifica el valor de una calificación existente.
     *
     * @param indice El índice a modificar.
     * @param nueva El nuevo valor.
     * <p>Complejidad Temporal: O(1) (Acceso directo por índice).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void modificarCalificacion(int indice, T nueva) {
        if (indice < 0 || indice >= tamanio) {
            throw new ArrayIndexOutOfBoundsException("Índice fuera de rango.");
        }
        calificaciones[indice] = nueva;
    }


    /**
     * Obtiene la calificación en una posición dada.
     *
     * @param indice El índice a consultar.
     * @return El valor de la calificación.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public T obtenerCalificacion(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new ArrayIndexOutOfBoundsException("Índice fuera de rango.");
        }
        return calificaciones[indice];
    }


    public int getTamanio() {
        return tamanio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public T[] getArreglo() {
        return calificaciones;
    }


    /**
     * Calcula el promedio de las calificaciones almacenadas.
     *
     * @return El promedio como valor flotante.
     * <p>Complejidad Temporal: O(n) (Llama a la recursión que recorre todo).</p>
     * <p>Complejidad Espacial: O(n) (Debido a la pila de llamadas de la recursión).</p>
     */
    public float calcularPromedio() {
        if (tamanio == 0) return 0f;
        return calcularRecursivamente(0) / tamanio;
    }

    /**
     * Método auxiliar recursivo para sumar las calificaciones.
     *
     * @param indice Índice actual en la recursión.
     * @return La suma de las calificaciones desde el índice hasta el final.
     * <p>Complejidad Temporal: O(n)</p>
     * <p>Complejidad Espacial: O(n) (Profundidad de recursión igual al tamaño).</p>
     */
    private float calcularRecursivamente(int indice) {
        if (indice == tamanio) return 0f;
        float valor = ((Number) calificaciones[indice]).floatValue();
        return valor + calcularRecursivamente(indice + 1);
    }

    /**
     * Representación en cadena de las calificaciones.
     *
     * @return Cadena con el formato [calif1, calif2, ...].
     * <p>Complejidad Temporal: O(n)</p>
     * <p>Complejidad Espacial: O(n)</p>
     */
    @Override
    public String toString() {
        if (tamanio == 0) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < tamanio; i++) {
            sb.append(calificaciones[i]);
            if (i < tamanio - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}