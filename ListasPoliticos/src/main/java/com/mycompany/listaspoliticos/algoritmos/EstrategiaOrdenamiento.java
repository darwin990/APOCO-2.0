/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.algoritmos;
import com.mycompany.listaspoliticos.modelo.ListaEnlazadaBase;
import com.mycompany.listaspoliticos.modelo.NodoBase;

/**
 * Interfaz funcional que define el contrato para las estrategias de ordenación
 * aplicables a una lista enlazada.
 * Utiliza el patrón Strategy para permitir intercambiar algoritmos de ordenación.
 *
 * @param <T> El tipo de elementos en la lista, debe implementar {@link Comparable}
 * @param <N> El tipo de nodo utilizado en la lista (debe extender {@link NodoBase})
 * @author devapps
 * @version 1.2
 */
@FunctionalInterface
public interface EstrategiaOrdenamiento<T extends Comparable<T>, N extends NodoBase<T, N>> {

    /**
     * Ordena la lista enlazada proporcionada "in situ" (modificando la estructura original).
     * La implementación específica del algoritmo puede modificar la referencia a la cabeza de la lista.
     *
     * @param lista La lista que será ordenada. No debe ser null.
     * @throws NullPointerException si {@code lista} es null.
     * @throws IllegalStateException si los elementos de la lista no son comparables
     */
    void ordenar(ListaEnlazadaBase<T, N> lista);
}