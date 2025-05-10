/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.algoritmos;

import com.mycompany.listaspoliticos.modelo.ListaEnlazadaBase;
import com.mycompany.listaspoliticos.modelo.NodoBase;
import java.util.Objects;

/**
 * Implementación de la {@link EstrategiaOrdenamiento} utilizando el algoritmo Merge Sort.
 * 
 * <p>Este algoritmo divide la lista en mitades recursivamente hasta que cada sublista
 * tenga un solo elemento, luego fusiona las sublistas en orden. Es ideal para listas
 * enlazadas porque no requiere acceso aleatorio a los elementos.</p>
 *
 * <p><b>Características:</b>
 * <ul>
 *   <li>Algoritmo estable (mantiene el orden relativo de elementos iguales)</li>
 *   <li>Reorganiza los nodos modificando sus enlaces (no intercambia datos)</li>
 *   <li>Implementación recursiva clásica con optimizaciones</li>
 * </ul>
 *
 * <p><b>Complejidad:</b>
 * <ul>
 *   <li>Temporal: O(n log n) en todos los casos</li>
 *   <li>Espacial: O(log n) por la recursión (pila de llamadas)</li>
 * </ul>
 *
 * @param <T> El tipo de elementos en la lista, debe implementar {@link Comparable}
 * @param <N> El tipo de nodo utilizado en la lista
 * @author devapps
 * @version 1.2
 */
public class OrdenamientoMerge<T extends Comparable<T>, N extends NodoBase<T, N>> 
    implements EstrategiaOrdenamiento<T, N> {

    /**
     * Ordena la lista usando el algoritmo Merge Sort.
     *
     * @param lista La lista a ordenar. No debe ser null.
     * @throws NullPointerException si {@code lista} es null.
     * @throws IllegalStateException si los elementos no son comparables
     */
    @Override
    public void ordenar(ListaEnlazadaBase<T, N> lista) {
        Objects.requireNonNull(lista, "La lista a ordenar no puede ser null");
        
        if (lista.getTamanno() <= 1) {
            return;
        }

        N nuevaCabeza = mergeSortRecursivo(lista.getCabeza());
        lista.setCabeza(nuevaCabeza);
    }

    /**
     * Implementación recursiva del Merge Sort.
     *
     * @param cabeza Nodo cabeza de la sublista a ordenar
     * @return Nodo cabeza de la sublista ordenada
     */
    private N mergeSortRecursivo(N cabeza) {
        // Caso base: lista vacía o con un solo elemento
        if (cabeza == null || cabeza.getSiguiente() == null) {
            return cabeza;
        }

        // Dividir la lista en dos mitades
        N medio = encontrarMedio(cabeza);
        N segundaMitad = medio.getSiguiente();
        medio.setSiguiente(null); // Separar las dos mitades

        // Ordenar recursivamente cada mitad
        N izquierda = mergeSortRecursivo(cabeza);
        N derecha = mergeSortRecursivo(segundaMitad);

        // Fusionar las dos mitades ordenadas
        return fusionar(izquierda, derecha);
    }

    /**
     * Encuentra el nodo medio de la lista usando el algoritmo de tortuga y liebre.
     *
     * @param cabeza Nodo inicial de la lista
     * @return Nodo medio (último nodo de la primera mitad)
     */
    private N encontrarMedio(N cabeza) {
        if (cabeza == null) return cabeza;

        N lento = cabeza;
        N rapido = cabeza.getSiguiente();

        while (rapido != null && rapido.getSiguiente() != null) {
            lento = lento.getSiguiente();
            rapido = rapido.getSiguiente().getSiguiente();
        }
        
        return lento;
    }

    /**
     * Fusiona dos listas ordenadas en una sola lista ordenada.
     *
     * @param izquierda Cabeza de la primera lista ordenada
     * @param derecha Cabeza de la segunda lista ordenada
     * @return Cabeza de la lista fusionada
     */
    private N fusionar(N izquierda, N derecha) {
        // Casos base
        if (izquierda == null) return derecha;
        if (derecha == null) return izquierda;

        N resultado;
        
        // Elegir el menor elemento como cabeza
        if (izquierda.getDato().compareTo(derecha.getDato()) <= 0) {
            resultado = izquierda;
            resultado.setSiguiente(fusionar(izquierda.getSiguiente(), derecha));
        } else {
            resultado = derecha;
            resultado.setSiguiente(fusionar(izquierda, derecha.getSiguiente()));
        }
        
        return resultado;
    }
}