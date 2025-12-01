/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Estructuras;

/**
 * Clase que implementa una Tabla Hash (Hash Map) utilizando el método 
 * de encadenamiento separado (separate chaining) para manejar colisiones.
 *
 * @param <K> El tipo de la clave.
 * @param <V> El tipo del valor.
 * 
 * @author Leonel
 */
public class DiccionarioHash <K, V>{
    
     private ListaSimple<Dir<K,V>>[] tabla;
     private int capacidad;

    /**
     * Constructor para la clase DiccionarioHash.
     * Inicializa la tabla con la capacidad especificada.
     * @param capacidad El tamaño deseado para la tabla hash.
     */
    @SuppressWarnings("unchecked")
    public DiccionarioHash( int capacidad) {
        this.capacidad = capacidad;
        tabla = new ListaSimple[capacidad];
        
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new ListaSimple<>();
            
        }
        
    }
    /**
     * Calcula el índice de la tabla para una clave dada.
     * @param clave La clave a hashear.
     * @return El índice del arreglo (bucket) donde se debe almacenar/buscar.
     */
    private int hash(K clave) {
        return Math.abs(clave.hashCode() % capacidad);
    }
     
    /**
     * Agrega una pareja clave-valor al diccionario. Si la clave ya existe, 
     * actualiza su valor asociado.
     * @param clave La clave del elemento.
     * @param valor El valor a asociar con la clave.
     */
     public void insertar(K clave, V valor) {
        int indice = hash(clave);

        for (Dir<K,V> par : tabla[indice]) {
            if (par.clave.equals(clave)) {
                par.valor = valor;
                return;
            }
        }

        tabla[indice].agregarInicio(new Dir<>(clave, valor));
    }

     
     /**
     * Recupera el valor asociado a una clave.
     * @param clave La clave del elemento a buscar.
     * @return El valor V asociado, o {@code null} si la clave no se encuentra.
     */
     
    public V obtener(K clave) {
        int indice = hash(clave);

        for (Dir<K,V> dir : tabla[indice]) {
            if (dir.clave.equals(clave))
                return dir.valor;
        }

        return null;
    }

    /**
     * Elimina la pareja clave-valor asociada a una clave.
     * @param clave La clave del elemento a eliminar.
     * @return {@code true} si el elemento fue encontrado y eliminado, {@code false} en caso contrario.
     */
    
    public boolean eliminar(K clave) {
        int indice = hash(clave);
        return tabla[indice].eliminarSi(par -> par.clave.equals(clave));
    }

    /**
     * Imprime en consola todos los pares clave-valor almacenados en el diccionario.
     */
    public void mostrar() {
        for (ListaSimple<Dir<K,V>> lista : tabla) {
            for (Dir<K,V> par : lista)
                System.out.println(par.clave + " → " + par.valor);
        }
    }
}


