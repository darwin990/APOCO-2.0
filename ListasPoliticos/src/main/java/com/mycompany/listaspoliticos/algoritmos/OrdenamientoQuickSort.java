package com.mycompany.listaspoliticos.algoritmos;

import com.mycompany.listaspoliticos.modelo.*;
import java.util.Objects;

public class OrdenamientoQuickSort<T extends Comparable<T>, N extends NodoBase<T, N>>
        implements EstrategiaOrdenamiento<T, N> {

    private int iteraciones = 0;

    @Override
    public void ordenar(ListaEnlazadaBase<T, N> lista) {
        Objects.requireNonNull(lista);
        iteraciones = 0;
        if (lista.getTamanno() <= 1) return;

        N cabeza = lista.getCabeza();
        N cola = encontrarCola(cabeza);

        quickSortRecursivo(cabeza, cola);
        lista.setCabeza(cabeza);
    }

    private N encontrarCola(N nodo) {
        while (nodo.getSiguiente() != null) nodo = nodo.getSiguiente();
        return nodo;
    }

    private void quickSortRecursivo(N inicio, N fin) {
        if (inicio == null || fin == null || inicio == fin || inicio == fin.getSiguiente()) return;

        N[] particion = particionar(inicio, fin);
        N pivote = particion[0];
        N anteriorPivote = particion[1];

        quickSortRecursivo(inicio, anteriorPivote);
        quickSortRecursivo(pivote.getSiguiente(), fin);
    }

    private N[] particionar(N inicio, N fin) {
        T pivote = fin.getDato();
        N i = null;
        N actual = inicio;

        while (actual != fin) {
            iteraciones++;
            if (actual.getDato().compareTo(pivote) < 0) {
                i = (i == null) ? inicio : i.getSiguiente();
                intercambiar(actual, i);
            }
            actual = actual.getSiguiente();
        }

        i = (i == null) ? inicio : i.getSiguiente();
        intercambiar(fin, i);

        N anterior = null;
        if (i != inicio) {
            N temp = inicio;
            while (temp != null && temp.getSiguiente() != i) temp = temp.getSiguiente();
            anterior = temp;
        }

        @SuppressWarnings("unchecked")
        N[] resultado = (N[]) java.lang.reflect.Array.newInstance(inicio.getClass(), 2);
        resultado[0] = i;
        resultado[1] = anterior;
        return resultado;
    }

    private void intercambiar(N a, N b) {
        T temp = a.getDato();
        a.setDato(b.getDato());
        b.setDato(temp);
    }

    @Override
    public int getIteraciones() {
        return iteraciones;
    }

    @Override
    public void resetIteraciones() {
        iteraciones = 0;
    }
}