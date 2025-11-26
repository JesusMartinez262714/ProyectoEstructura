package Calificaciones.Modelo;

/**
 * Representa una acción realizada en el sistema, utilizada para registrar cambios
 * y permitir operaciones de deshacer (undo) o auditoría.
 */
public class Accion {

        // -- La utilizacion de Enum es para saber que tipo de accion se realizo
    /**
     * Define los tipos de acciones que pueden ser registradas en el sistema.
     */
    public enum TipoAccion {
        REGISTRO_ESTUDIANTE,
        INSCRIPCION_CURSO,
        BAJA_CURSO,
        CAMBIO_CALIFICACION
    }

    private TipoAccion tipo;
    private Object datoAnterior; // Guardamos el estado anterior para poder restaurarlo
    private Object datoNuevo;    // Guardamos lo que se hizo (por si necesitamos referencia)

    /**
     * Constructor de la clase Accion.
     *
     * @param tipo         El tipo de acción realizada.
     * @param datoAnterior El estado o dato antes de realizar la acción (para revertir).
     * @param datoNuevo    El nuevo estado o dato resultante de la acción.
     */
    public Accion(TipoAccion tipo, Object datoAnterior, Object datoNuevo) {
        this.tipo = tipo;
        this.datoAnterior = datoAnterior;
        this.datoNuevo = datoNuevo;
    }

    /**
     * Obtiene el tipo de acción realizada.
     *
     * @return El tipo de acción.
     */
    public TipoAccion getTipo() {
        return tipo;
    }

    /**
     * Obtiene el dato anterior a la acción.
     *
     * @return El objeto que representa el estado previo.
     */
    public Object getDatoAnterior() {
        return datoAnterior;
    }

    /**
     * Obtiene el nuevo dato resultante de la acción.
     *
     * @return El objeto que representa el nuevo estado.
     */
    public Object getDatoNuevo() {
        return datoNuevo;
    }

}
