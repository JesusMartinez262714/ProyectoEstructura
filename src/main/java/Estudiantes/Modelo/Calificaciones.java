package Estudiantes.Modelo;

public class Calificaciones<T> {

    private T[] calificaciones;
    private int tamanio;
    private int capacidad;

    @SuppressWarnings("unchecked")
    public Calificaciones(int capacidad) {

        if (capacidad <= 0) {
            capacidad = 1;
        }

        this.capacidad = capacidad;
        this.tamanio = 0;
        this.calificaciones = (T[]) new Object[capacidad];
    }

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

    public void modificarCalificacion(int indice, T nueva) {
        if (indice < 0 || indice >= tamanio) {
            throw new ArrayIndexOutOfBoundsException("Índice fuera de rango.");
        }
        calificaciones[indice] = nueva;
    }


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


    public float calcularPromedio() {
        if (tamanio == 0) return 0f;
        return calcularRecursivamente(0) / tamanio;
    }

    private float calcularRecursivamente(int indice) {
        if (indice == tamanio) return 0f;
        float valor = ((Number) calificaciones[indice]).floatValue();
        return valor + calcularRecursivamente(indice + 1);
    }

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
