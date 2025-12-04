/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Estructuras;

/**
 * Clase auxiliar genérica que representa un par Clave-Valor (Entry).
 * Se utiliza principalmente dentro del DiccionarioHash para manejar las colisiones por encadenamiento.
 * <p>Complejidad Espacial General: O(1) por cada instancia de Dir creada.</p>
 *
 * @param <K> El tipo de dato de la clave (Key).
 * @param <V> El tipo de dato del valor (Value).
 * @author Leonel
 */
public class Dir<K, V> {

    public K clave;
    public V valor;

    /**
     * Constructor que inicializa el par clave-valor.
     *
     * @param clave La clave única.
     * @param valor El valor asociado.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public Dir(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    /**
     * Obtiene la clave almacenada.
     * @return La clave.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public K getClave() {
        return clave;
    }

    /**
     * Establece una nueva clave.
     * @param clave La nueva clave.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setClave(K clave) {
        this.clave = clave;
    }

    /**
     * Obtiene el valor almacenado.
     * @return El valor.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public V getValor() {
        return valor;
    }

    /**
     * Establece un nuevo valor.
     * @param valor El nuevo valor.
     * <p>Complejidad Temporal: O(1)</p>
     * <p>Complejidad Espacial: O(1)</p>
     */
    public void setValor(V valor) {
        this.valor = valor;
    }


}