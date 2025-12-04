package Estudiantes.Modelo;

/**
 * Clase que representa la dirección postal de un estudiante.
 * Objeto simple de transferencia de datos (POJO).
 * <p>Complejidad Espacial General: O(1) por cada instancia creada.</p>
 */
public class Direccion {
    private String calle;
    private String numero;
    private String colonia;
    private String ciudad;

    /**
     * Constructor que inicializa todos los campos de la dirección.
     *
     * @param calle   Nombre de la calle.
     * @param numero  Número exterior/interior.
     * @param colonia Nombre de la colonia.
     * @param ciudad  Nombre de la ciudad.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public Direccion(String calle, String numero, String colonia, String ciudad) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.ciudad = ciudad;
    }

    /**
     * Obtiene el nombre de la calle.
     * @return La calle.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Obtiene el número de la dirección.
     * @return El número.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Obtiene la colonia.
     * @return La colonia.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Obtiene la ciudad.
     * @return La ciudad.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece la ciudad.
     * @param ciudad La nueva ciudad.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Establece la colonia.
     * @param colonia La nueva colonia.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Establece el número.
     * @param numero El nuevo número.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Establece la calle.
     * @param calle La nueva calle.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Devuelve una representación en cadena de la dirección completa.
     * @return Cadena formateada: Calle Numero, Colonia, Ciudad.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    @Override
    public String toString() {
        return calle + " " + numero + ", " + colonia + ", " + ciudad;
    }

}