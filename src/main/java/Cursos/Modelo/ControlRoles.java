/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Modelo;

import Estudiantes.Modelo.Estudiante;

/**
 *
 * @author Leonel
 */
public class ControlRoles {
    private GestionarCursos gestorCursos;

    public ControlRoles(GestionarCursos gestorCursos) {
        this.gestorCursos = gestorCursos;
    }

    public void rotarRol(String claveCurso) {

        Curso c = gestorCursos.obtenerCurso(claveCurso);

        if (c == null) {
            System.out.println("Curso no encontrado.");
            return;
        }

        if (c.getRoles().estaVacia()) {
            System.out.println("No hay estudiantes para asignar roles.");
            return;
        }

        Estudiante nuevo = c.rotarRol();
        System.out.println("Nuevo tutor/líder en " + c.getNombre() + ": " + nuevo.getNombreCompleto());
    }
}
