package Cursos.Estructuras;

import Estudiantes.Modelo.Estudiante;

public class NodoAVL {

    public double promedio;
    public Estudiante<?> estudiante;
    public int altura;

    public NodoAVL izq;
    public NodoAVL der;

    public NodoAVL(double promedio, Estudiante<?> estudiante) {
        this.promedio = promedio;
        this.estudiante = estudiante;
        this.altura = 1;
    }
}

