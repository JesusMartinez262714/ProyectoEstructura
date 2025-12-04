package Estudiantes.Modelo;

/**
 * Clase que representa a un estudiante dentro del sistema.
 * Contiene su información personal y una estructura de calificaciones.
 * <p>Complejidad Espacial General: O(n), donde n es el número de calificaciones almacenadas en su historial.</p>
 *
 * @param <T> El tipo de dato para las calificaciones (numérico).
 */
public class Estudiante<T> {
    private int matricula;
    private String nombreCompleto;
    private String numeroTelefono;
    private String correo;
    private Direccion direccion;
    private Calificaciones<T> calificaciones;

    /**
     * Constructor completo para inicializar un estudiante.
     *
     * @param matricula      Matrícula única del estudiante.
     * @param nombreCompleto Nombre y apellidos.
     * @param numeroTelefono Teléfono de contacto.
     * @param correo         Correo electrónico.
     * @param direccion      Objeto con la dirección postal.
     * <p>Complejidad Temporal: O(1) (Inicialización de atributos y creación de estructura vacía).</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public Estudiante(int matricula, String nombreCompleto, String numeroTelefono, String correo, Direccion direccion) {
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.numeroTelefono = numeroTelefono;
        this.correo = correo;
        this.direccion = direccion;
        this.calificaciones = new Calificaciones<T>(0);
    }

    /**
     * Constructor vacío.
     * Inicializa la estructura de calificaciones con una capacidad por defecto de 10.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public Estudiante() {
        this.calificaciones = new Calificaciones<T>(10);
    }

    /**
     * Obtiene la matrícula.
     * @return La matrícula.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public int getMatricula() {
        return matricula;
    }

    /**
     * Establece la matrícula.
     * @param matricula La nueva matrícula.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    /**
     * Obtiene el nombre completo.
     * @return El nombre.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Calcula y obtiene el promedio actual del estudiante.
     * Delega la operación a la clase Calificaciones.
     * @return El promedio aritmético.
     * <p>Complejidad Temporal: O(n), donde n es el número de calificaciones (debe recorrerlas todas para sumar).</p>
     * <p>Complejidad Espacial: O(n) (Debido a la recursión en el método de cálculo).</p>
     */
    public float getPromedio(){
        return calificaciones.calcularPromedio();
    }

    /**
     * Establece el nombre completo.
     * @param nombreCompleto El nuevo nombre.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Obtiene el número de teléfono.
     * @return El teléfono.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    /**
     * Establece el número de teléfono.
     * @param numeroTelefono El nuevo teléfono.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    /**
     * Obtiene el correo electrónico.
     * @return El correo.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico.
     * @param correo El nuevo correo.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el objeto dirección.
     * @return La dirección.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección.
     * @param direccion La nueva dirección.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }


    /**
     * Obtiene la estructura que contiene las calificaciones.
     * @return El objeto Calificaciones.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public Calificaciones<T> getCalificaciones() {
        return calificaciones;
    }


    /**
     * Devuelve una representación en cadena del estudiante.
     * Incluye datos personales y la lista de calificaciones.
     * @return Cadena con los datos del estudiante.
     * <p>Complejidad Temporal: O(n) (Debe convertir a String todas las calificaciones).</p>
     * <p>Complejidad Espacial: O(n)</p>
     */
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