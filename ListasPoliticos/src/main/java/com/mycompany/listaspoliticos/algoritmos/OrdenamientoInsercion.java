/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.algoritmos;

import com.mycompany.listaspoliticos.modelo.ListaEnlazadaBase;
import com.mycompany.listaspoliticos.modelo.NodoBase;
import java.util.Objects;

/**
 * Implementación de la {@link EstrategiaOrdenamiento} utilizando el algoritmo de Inserción (Insertion Sort).
 * 
 * <p>Este algoritmo construye la lista ordenada final un elemento a la vez, insertando cada elemento
 * en su posición correcta dentro de la sublista ya ordenada. Es particularmente eficiente para:</p>
 * <ul>
 *   <li>Listas pequeñas</li>
 *   <li>Listas casi ordenadas</li>
 *   <li>Entornos con limitaciones de memoria</li>
 * </ul>
 *
 * <p><b>Características de esta implementación:</b>
 * <ul>
 *   <li>Reorganiza los nodos modificando sus enlaces (no intercambia datos)</li>
 *   <li>Mantiene un seguimiento explícito de la lista ordenada</li>
 *   <li>Incluye optimizaciones para reducir comparaciones</li>
 * </ul>
 *
 * <p><b>Complejidad:</b>
 * <ul>
 *   <li>Temporal: O(n²) en peor caso y caso promedio, O(n) en mejor caso (lista ya ordenada)</li>
 *   <li>Espacial: O(1) (ordenación in situ)</li>
 * </ul>
 *
 * @param <T> El tipo de elementos en la lista, debe implementar {@link Comparable}
 * @param <N> El tipo de nodo utilizado en la lista
 * @author devapps
 * @version 1.2
 */
public class OrdenamientoInsercion<T extends Comparable<T>, N extends NodoBase<T, N>> 
    implements EstrategiaOrdenamiento<T, N> {

    /**
     * Ordena la lista dada usando el algoritmo de Inserción, reenlazando nodos.
     *
     * @param lista La lista a ordenar. No debe ser null.
     * @throws NullPointerException si {@code lista} es null.
     * @throws IllegalStateException si los elementos no son comparables
     */
    @Override
    public void ordenar(ListaEnlazadaBase<T, N> lista) {
        Objects.requireNonNull(lista, "La lista a ordenar no puede ser null");
        
        if (lista.getTamanno() <= 1) {
            return; // Lista vacía o con un solo elemento ya está ordenada
        }

        N cabezaOrdenada = null; // Cabeza de la lista ordenada
        N actual = lista.getCabeza(); // Nodo actual de la lista original

        while (actual != null) {
            // Guardar referencia al siguiente nodo antes de modificar los enlaces
            N siguiente = actual.getSiguiente();
            
            // Caso especial para el primer elemento de la lista ordenada
            if (cabezaOrdenada == null || cabezaOrdenada.getDato().compareTo(actual.getDato()) >= 0) {
                actual.setSiguiente(cabezaOrdenada);
                cabezaOrdenada = actual;
            } else {
                // Buscar el punto de inserción en la lista ordenada
                N temp = cabezaOrdenada;
                while (temp.getSiguiente() != null && 
                       temp.getSiguiente().getDato().compareTo(actual.getDato()) < 0) {
                    temp = temp.getSiguiente();
                }
                
                // Insertar el nodo actual en la posición correcta
                actual.setSiguiente(temp.getSiguiente());
                temp.setSiguiente(actual);
            }
            
            actual = siguiente; // Mover al siguiente nodo en la lista original
        }

        // Actualizar la cabeza (y posiblemente cola) de la lista
        lista.setCabeza(cabezaOrdenada);
    }
}