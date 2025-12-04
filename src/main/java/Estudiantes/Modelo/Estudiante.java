package Estudiantes.Modelo;

public class Estudiante<T> {
    private int matricula;
    private String nombreCompleto;
    private String numeroTelefono;
    private String correo;
    private Direccion direccion;
    private Calificaciones<T> calificaciones;

    public Estudiante(int matricula, String nombreCompleto, String numeroTelefono, String correo, Direccion direccion) {
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.numeroTelefono = numeroTelefono;
        this.correo = correo;
        this.direccion = direccion;
        this.calificaciones = new Calificaciones<T>(10);
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public float getPromedio(){
        return calificaciones.calcularPromedio();
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Estudiante() {
    }
    public Calificaciones<T> getCalificaciones() {
        return calificaciones;
    }


    @Override
    public String toString() {
        return "Estudiante{" +
                "matricula=" + matricula +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion=" + direccion +
                ", calificaciones=" + calificaciones +
                '}';
    }
}
