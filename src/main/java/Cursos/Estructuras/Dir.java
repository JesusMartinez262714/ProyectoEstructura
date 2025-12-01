/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cursos.Estructuras;

/**
 * @param k se refirere a la llave de la entrada de la lista o elemento
 * @param v es el valor que tiene
 * @author Leonel
 */
public class Dir<K, V> {
    
    public K clave;
    public V valor;

    public Dir(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }

    
    public K getClave() {
        return clave;
    }

    public void setClave(K clave) {
        this.clave = clave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }
    
    
}
