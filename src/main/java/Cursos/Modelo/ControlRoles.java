/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Modelo;

import Estudiantes.Modelo.Estudiante;

/**
 * Clase de control que gestiona la asignación y rotación de roles 
 * (como tutores, líderes de equipo, etc.) entre los estudiantes de un curso.
 * Depende de {@code GestionarCursos} para acceder a los datos de los cursos.
 * @author Leonel
 */
public class ControlRoles {
    private GestionarCursos gestorCursos;

    public ControlRoles(GestionarCursos gestorCursos) {
        this.gestorCursos = gestorCursos;
    }

    /**
     * Realiza la rotación del rol activo dentro de la lista circular de roles de un curso.
     * El nuevo estudiante seleccionado es quien asume el rol (tutor, líder, etc.).
     *
     * @param claveCurso La clave única del curso al cual se le rotará el rol.
     */
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
