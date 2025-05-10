/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.algoritmos;

import com.mycompany.listaspoliticos.modelo.ListaEnlazadaBase;
import com.mycompany.listaspoliticos.modelo.NodoBase;
import java.util.Objects;

/**
 * Implementación de la {@link EstrategiaOrdenamiento} utilizando el algoritmo Burbuja (Bubble Sort).
 * Este algoritmo itera repetidamente sobre la lista, comparando elementos adyacentes
 * e intercambiándolos si están en el orden incorrecto.
 *
 * <p><b>Características:</b>
 * <ul>
 *   <li>Ordena intercambiando los datos de los nodos (no los nodos mismos)</li>
 *   <li>Incluye optimización para detectar lista ya ordenada</li>
 *   <li>Compatible con cualquier implementación de ListaEnlazadaBase</li>
 * </ul>
 *
 * <p><b>Complejidad:</b>
 * <ul>
 *   <li>Temporal: O(n²) en peor caso y caso promedio, O(n) en mejor caso (lista ya ordenada)</li>
 *   <li>Espacial: O(1)</li>
 * </ul>
 *
 * @param <T> El tipo de elementos en la lista, debe implementar {@link Comparable}
 * @param <N> El tipo de nodo utilizado en la lista
 * @author devapps
 * @version 1.2
 */
public class OrdenamientoBurbuja<T extends Comparable<T>, N extends NodoBase<T, N>> 
    implements EstrategiaOrdenamiento<T, N> {

    /**
     * Ordena la lista usando el algoritmo de Burbuja mediante intercambio de datos.
     *
     * @param lista La lista a ordenar. No debe ser null.
     * @throws NullPointerException si {@code lista} es null.
     * @throws IllegalStateException si los elementos no son comparables
     * @throws UnsupportedOperationException si los nodos no permiten modificar sus datos
     */
    @Override
    public void ordenar(ListaEnlazadaBase<T, N> lista) {
        Objects.requireNonNull(lista, "La lista a ordenar no puede ser null");
        
        if (lista.getTamanno() <= 1) {
            return;
        }

        boolean intercambiado;
        N cabezaActual = lista.getCabeza();

        // Bucle externo: controla las pasadas
        do {
            N actual = cabezaActual;
            N anterior = null;
            intercambiado = false;

            // Bucle interno: comparaciones e intercambios
            while (actual != null && actual.getSiguiente() != null) {
                N siguiente = actual.getSiguiente();
                
                // Comparar los elementos
                if (actual.getDato().compareTo(siguiente.getDato()) > 0) {
                    // Intercambiar datos
                    swapDatos(actual, siguiente);
                    intercambiado = true;
                }
                
                anterior = actual;
                actual = siguiente;
            }
            
            // El último elemento en esta pasada está en su posición correcta
            if (anterior != null && anterior.getSiguiente() != null) {
                actual = anterior;
            }
            
        } while (intercambiado);

        // Actualizar la cabeza por si cambió (aunque con intercambio de datos no debería ser necesario)
        lista.setCabeza(cabezaActual);
    }

    /**
     * Intercambia los datos entre dos nodos.
     * 
     * @param nodo1 Primer nodo
     * @param nodo2 Segundo nodo
     * @throws UnsupportedOperationException si no se puede modificar los datos
     */
    private void swapDatos(N nodo1, N nodo2) {
        try {
            T temp = nodo1.getDato();
            nodo1.setDato(nodo2.getDato());
            nodo2.setDato(temp);
        } catch (Exception e) {
            throw new UnsupportedOperationException(
                "El ordenamiento Burbuja requiere nodos con datos modificables", e);
        }
    }
}