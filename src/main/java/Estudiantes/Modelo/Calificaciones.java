package Estudiantes.Modelo;

public class Calificaciones<T> {
    private T[] calificaciones;
    private int tamanio;
    private int capacidad;
    @SuppressWarnings("unchecked")
    public Calificaciones(int capacidad) {
        this.tamanio = 0;
        this.capacidad = capacidad;
        this.calificaciones = (T[]) new Object[capacidad];

    }
    /*
    Metodo de agregar a un arreglo dinamico
     */
    public void agregarCalificacion(T calificacion) {
        if(tamanio == capacidad) {
            capacidad++;
            @SuppressWarnings("unchecked")
            T[] copia = (T[]) new Object[capacidad];
            for (int i = 0; i < tamanio; i++) {
                copia[i] = calificaciones[i];

            }
            calificaciones = copia;

         }
        calificaciones[tamanio] = calificacion;
        tamanio++;
    }

    public void eliminarCalificacion(int indice) {
        if(indice <0 || indice >= tamanio) {
            throw new ArrayIndexOutOfBoundsException("Indice fuera de rango");
        }
        capacidad--;
        @SuppressWarnings("unchecked")
        T[] nuevoArreglo = (T[]) new Object[capacidad];
        for (int i = 0,j=0; i < tamanio; i++) {
            if(i != indice) {
                nuevoArreglo[j] = calificaciones[i];
                j++;
            }
        }
        calificaciones = nuevoArreglo;
        tamanio--;
    }


    public void modificarCalificacion(int indice, T calificacion) {
        if(indice <0 || indice >= tamanio) {
            throw new ArrayIndexOutOfBoundsException("Indice fuera de rango");
        }
        calificaciones[indice] = calificacion;
    }


    public float calcularPromedio(){
        if(tamanio == 0) {
            return 0;
        }
        float suma = calcularRecursivamente(0);
        return suma/tamanio;
    }

    public float calcularRecursivamente(int indice) {
        if(indice == tamanio){
            return 0;
        }
        float valor = ((Number)calificaciones[indice]).floatValue();
        return valor + calcularRecursivamente(indice + 1) ;
    }

    public int getTamanio() {
        return tamanio;
    }

    public T obtenerCalificacion(int indice) {
        if(indice <0 || indice >= tamanio) {
            throw new ArrayIndexOutOfBoundsException("Indice fuera de rango");
        }
        return calificaciones[indice];
    }
}
