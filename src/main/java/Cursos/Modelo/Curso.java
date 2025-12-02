package Cursos.Modelo;

import Cursos.Estructuras.ListaCircularSimple;
import Cursos.Estructuras.ListaDobleCircular;
import Cursos.Estructuras.ListaSimple;
import Estudiantes.Modelo.Estudiante;

public class Curso {
    
    private String clave;
    private String nombre;
    private int cupoMaximo;
    
    private ListaSimple<Estudiante> inscritos;
    private ListaDobleCircular<Estudiante> listaEspera;
    private ListaCircularSimple<Estudiante> roles;

    public Curso(String clave, String nombre, int cupoMaximo) {
        this.clave = clave;
        this.nombre = nombre;
        this.cupoMaximo = cupoMaximo;
        inscritos = new ListaSimple<>();
        listaEspera = new ListaDobleCircular<>();
        roles = new ListaCircularSimple<>();
    }
    
     public boolean hayCupo() {
        return inscritos.size() < cupoMaximo;
    }

    public void agregarInscrito(Estudiante e) {
        inscritos.agregarFinal(e);
    }

    //metodos
    public void agregarListaEspera(Estudiante e) {
        listaEspera.agregar(e);
    }

    public ListaSimple<Estudiante> getInscritos() {
        return inscritos;
    }

    public ListaDobleCircular<Estudiante> getListaEspera() {
        return listaEspera;
    }
    
    //metodos de roles
     public ListaCircularSimple<Estudiante> getRoles() {
        return roles;
    }

    public void agregarRol(Estudiante e) {
        roles.agregar(e);
    }

    public Estudiante rotarRol() {
        return roles.rotar();
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    @Override
    public String toString() {
        return "Curso{" + "clave=" + clave + ", nombre=" + nombre + ", cupoMaximo=" + cupoMaximo + '}';
    }
    
    
    
}
