/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.algoritmos;

import com.mycompany.listaspoliticos.modelo.ListaEnlazadaBase;
import com.mycompany.listaspoliticos.modelo.NodoBase;
import java.util.Objects;

/**
 * Implementación de la {@link EstrategiaOrdenamiento} utilizando el algoritmo Quick Sort.
 * Ordena la lista intercambiando los datos en los nodos en lugar de reorganizar los nodos.
 *
 * @param <T> El tipo de elementos en la lista, debe ser {@link Comparable}
 * @param <N> El tipo de nodo utilizado en la lista (debe extender {@link NodoBase})
 */
public class OrdenamientoQuickSort<T extends Comparable<T>, N extends NodoBase<T, N>> 
    implements EstrategiaOrdenamiento<T, N> {

    @Override
    public void ordenar(ListaEnlazadaBase<T, N> lista) {
        Objects.requireNonNull(lista, "La lista a ordenar no puede ser null.");

        if (lista.getTamanno() <= 1) {
            return;
        }

        N cabeza = lista.getCabeza();
        N cola = encontrarCola(cabeza);

        quickSortRecursivo(cabeza, cola);

        // Asegurar que la lista mantenga sus referencias correctamente
        lista.setCabeza(cabeza);
    }

    private N encontrarCola(N nodo) {
        if (nodo == null) {
            return null;
        }
        while (nodo.getSiguiente() != null) {
            nodo = nodo.getSiguiente();
        }
        return nodo;
    }

    private void quickSortRecursivo(N cabezaSubLista, N colaSubLista) {
        if (cabezaSubLista == null || colaSubLista == null || 
            cabezaSubLista == colaSubLista || 
            cabezaSubLista == colaSubLista.getSiguiente()) {
            return;
        }

        N[] resultadoParticion = particionar(cabezaSubLista, colaSubLista);
        N nodoPivoteFinal = resultadoParticion[0];
        N nodoAntesPivote = resultadoParticion[1];

        if (nodoAntesPivote != null && nodoPivoteFinal != cabezaSubLista) {
            quickSortRecursivo(cabezaSubLista, nodoAntesPivote);
        }

        if (nodoPivoteFinal != null && nodoPivoteFinal != colaSubLista) {
            quickSortRecursivo(nodoPivoteFinal.getSiguiente(), colaSubLista);
        }
    }

    @SuppressWarnings("unchecked")
    private N[] particionar(N cabeza, N cola) {
        T valorPivote = cola.getDato();
        N i = null;
        N actual = cabeza;

        while (actual != cola) {
            if (actual.getDato().compareTo(valorPivote) < 0) {
                i = (i == null) ? cabeza : i.getSiguiente();
                intercambiarDatos(actual, i);
            }
            actual = actual.getSiguiente();
        }

        i = (i == null) ? cabeza : i.getSiguiente();
        intercambiarDatos(cola, i);

        N nodoAntesPivote = null;
        if (i != cabeza) {
            N buscador = cabeza;
            while (buscador != null && buscador.getSiguiente() != i) {
                buscador = buscador.getSiguiente();
            }
            nodoAntesPivote = buscador;
        }

        // Crear array genérico (esto es seguro porque solo lo usamos internamente)
        N[] resultado = (N[]) java.lang.reflect.Array.newInstance(cabeza.getClass(), 2);
        resultado[0] = i;
        resultado[1] = nodoAntesPivote;
        return resultado;
    }

    private void intercambiarDatos(N nodo1, N nodo2) {
        T temp = nodo1.getDato();
        nodo1.setDato(nodo2.getDato());
        nodo2.setDato(temp);
    }
}