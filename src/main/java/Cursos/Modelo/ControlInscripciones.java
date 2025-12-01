/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Modelo;

import Estudiantes.ControlEstudiantes;
import Estudiantes.Modelo.Estudiante;

/**
 *
 * @author Leonel
 */
public class ControlInscripciones {
     private ControlEstudiantes controlEstudiantes;
    private GestionarCursos controlCursos;

    public ControlInscripciones(ControlEstudiantes ge, GestionarCursos gc) {
        this.controlEstudiantes = ge;
        this.controlCursos = gc;
    }

    public void inscribir(int matricula, String claveCurso) throws Exception {

        Estudiante e = controlEstudiantes.buscar(matricula);
        if (e == null) {
            System.out.println("ERROR: No existe un estudiante con matrícula " + matricula);
            return;
        }

        Curso c = controlCursos.obtenerCurso(claveCurso);
        if (c == null) {
            System.out.println("ERROR: No existe un curso con clave " + claveCurso);
            return;
        }

        if (c.hayCupo()) {
            c.agregarInscrito(e);
            System.out.println("Estudiante inscrito en curso: " + c.getNombre());
        } else {
            c.agregarListaEspera(e);
            System.out.println("Curso lleno. El estudiante fue agregado a la LISTA DE ESPERA.");
        }
    }
}

