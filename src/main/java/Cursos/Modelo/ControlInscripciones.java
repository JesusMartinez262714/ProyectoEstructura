/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Modelo;

import Estudiantes.ControlEstudiantes;
import Estudiantes.Modelo.Estudiante;

/**
 * Clase de control que gestiona el proceso de inscripción de estudiantes en cursos.
 * Actúa como intermediario entre el control de estudiantes y el control de cursos.

 * @author Leonel
 */
public class ControlInscripciones {
     private ControlEstudiantes controlEstudiantes;
    private GestionarCursos controlCursos;

    public ControlInscripciones(ControlEstudiantes ge, GestionarCursos gc) {
        this.controlEstudiantes = ge;
        this.controlCursos = gc;
    }

    /**
     * Realiza el proceso de inscripción de un estudiante a un curso.
     * Verifica la existencia del estudiante y del curso, y maneja la lógica de cupo.
     *
     * @param matricula La matrícula del estudiante que desea inscribirse.
     * @param claveCurso La clave única del curso al que se desea inscribir.
     */
    public void inscribir(int matricula, String claveCurso) throws Exception {

        Estudiante e = controlEstudiantes.buscar(matricula);
        if (e == null) {
            System.out.println("No existe un estudiante con matrícula " + matricula);
            return;
        }

        Curso c = controlCursos.obtenerCurso(claveCurso);
        if (c == null) {
            System.out.println("No existe un curso con clave " + claveCurso);
            return;
        }

        if (c.hayCupo()) {
            c.agregarInscrito(e);
            c.getRoles().agregar(e);
            System.out.println("Estudiante inscrito en curso: " + c.getNombre());
        } else {
            c.agregarListaEspera(e);
            System.out.println("Curso lleno. El estudiante fue agregado a la LISTA DE ESPERA.");
        }
    }

    public boolean darBaja(int matricula, String claveCurso) {

        Curso c = controlCursos.obtenerCurso(claveCurso);
        if (c == null) return false;


        boolean eliminado = c.getInscritos().eliminarSi(est -> est.getMatricula() == matricula);

        if (eliminado) {
            System.out.println(">> DESHACER: Alumno " + matricula + " eliminado del curso " + claveCurso);
        }
        return eliminado;
    }
}

