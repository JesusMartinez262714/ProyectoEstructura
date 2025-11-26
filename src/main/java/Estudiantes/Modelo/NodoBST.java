package Estudiantes.Modelo;

public class NodoBST {
    private Estudiante estudiante;
    private NodoBST izquierdo;
    private NodoBST derecho;

    public NodoBST(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public NodoBST getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoBST izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoBST getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoBST derecho) {
        this.derecho = derecho;
    }
}
