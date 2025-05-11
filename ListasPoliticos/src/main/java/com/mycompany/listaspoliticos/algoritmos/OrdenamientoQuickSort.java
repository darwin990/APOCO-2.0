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
        int tamanno = lista.getTamanno();
        
        if (tamanno <= 1) return;

        // Manejo especial para listas circulares
        boolean esCircular = lista instanceof ListaEnlazadaSimpleCircular;
        N cabeza = lista.getCabeza();
        N ultimo = null;

        if (esCircular) {
            ultimo = encontrarUltimoNodoSeguro(cabeza);
            if (ultimo == null) {
                throw new IllegalStateException("Lista circular mal formada");
            }
            // Romper circularidad temporalmente
            ultimo.setSiguiente(null);
        }

        // Ordenar la lista
        quickSortRecursivo(cabeza, encontrarCola(cabeza));

        // Restaurar circularidad si era necesario
        if (esCircular) {
            ultimo = encontrarCola(cabeza);
            if (ultimo != null) {
                ultimo.setSiguiente(cabeza);
            }
            lista.setCabeza(cabeza); // Asegurar que la cabeza sigue siendo correcta
        } else {
            lista.setCabeza(cabeza);
        }
    }

    private N encontrarUltimoNodoSeguro(N nodo) {
        if (nodo == null) return null;
        
        N actual = nodo;
        int contador = 0;
        int limiteSeguro = 100000; // Prevención de bucles infinitos
        
        while (actual.getSiguiente() != null && actual.getSiguiente() != nodo && contador < limiteSeguro) {
            actual = actual.getSiguiente();
            contador++;
        }
        
        return contador < limiteSeguro ? actual : null;
    }

    private N encontrarCola(N nodo) {
        if (nodo == null) return null;
        
        N actual = nodo;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    private void quickSortRecursivo(N inicio, N fin) {
        if (inicio == null || fin == null || inicio == fin) return;

        N[] particion = particionar(inicio, fin);
        N pivote = particion[0];
        N anteriorPivote = particion[1];

        if (anteriorPivote != null && inicio != anteriorPivote) {
            quickSortRecursivo(inicio, anteriorPivote);
        }
        if (pivote != fin && pivote.getSiguiente() != fin) {
            quickSortRecursivo(pivote.getSiguiente(), fin);
        }
    }

    private N[] particionar(N inicio, N fin) {
        T pivoteValor = fin.getDato();
        N i = null;
        N j = inicio;

        while (j != fin) {
            iteraciones++;
            if (j.getDato().compareTo(pivoteValor) < 0) {
                i = (i == null) ? inicio : i.getSiguiente();
                intercambiar(i, j);
            }
            j = j.getSiguiente();
        }

        i = (i == null) ? inicio : i.getSiguiente();
        intercambiar(i, fin);

        // Encontrar el nodo anterior al pivote
        N anterior = null;
        if (i != inicio) {
            anterior = inicio;
            while (anterior.getSiguiente() != i) {
                anterior = anterior.getSiguiente();
            }
        }

        @SuppressWarnings("unchecked")
        N[] resultado = (N[]) java.lang.reflect.Array.newInstance(inicio.getClass(), 2);
        resultado[0] = i;       // Pivote
        resultado[1] = anterior; // Nodo anterior al pivote
        return resultado;
    }

    private void intercambiar(N a, N b) {
        if (a == null || b == null) return;
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