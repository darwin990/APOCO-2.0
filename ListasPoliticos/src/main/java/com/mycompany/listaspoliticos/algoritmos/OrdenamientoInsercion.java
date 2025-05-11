package com.mycompany.listaspoliticos.algoritmos;

import com.mycompany.listaspoliticos.modelo.*;
import java.util.Objects;

public class OrdenamientoInsercion<T extends Comparable<T>, N extends NodoBase<T, N>>
        implements EstrategiaOrdenamiento<T, N> {

    private int iteraciones = 0;

    @Override
    public void ordenar(ListaEnlazadaBase<T, N> lista) {
        Objects.requireNonNull(lista);
        iteraciones = 0;
        int tamanno = lista.getTamanno();

        if (tamanno <= 1) return;

        // 1. Convertir temporalmente la lista circular en lineal
        N cabeza = lista.getCabeza();
        if (lista instanceof ListaEnlazadaSimpleCircular) {
            N ultimo = cabeza;
            for (int i = 0; i < tamanno - 1; i++) {
                ultimo = ultimo.getSiguiente();
            }
            ultimo.setSiguiente(null); // Rompe la circularidad
        }

        // 2. Ordenar la lista ahora lineal
        N ordenada = null;
        N actual = cabeza;
        int procesados = 0;

        while (actual != null && procesados < tamanno) {
            iteraciones++;
            N siguiente = actual.getSiguiente();

            // Desconectar completamente el nodo actual
            actual.setSiguiente(null);

            if (ordenada == null || ordenada.getDato().compareTo(actual.getDato()) >= 0) {
                actual.setSiguiente(ordenada);
                ordenada = actual;
            } else {
                N temp = ordenada;
                while (temp.getSiguiente() != null && 
                       temp.getSiguiente().getDato().compareTo(actual.getDato()) < 0) {
                    iteraciones++;
                    temp = temp.getSiguiente();
                }
                actual.setSiguiente(temp.getSiguiente());
                temp.setSiguiente(actual);
            }

            actual = siguiente;
            procesados++;
        }

        // 3. Restaurar circularidad si era originalmente circular
        if (lista instanceof ListaEnlazadaSimpleCircular && tamanno > 0) {
            N ultimo = ordenada;
            while (ultimo.getSiguiente() != null) {
                ultimo = ultimo.getSiguiente();
            }
            ultimo.setSiguiente(ordenada);
        }

        lista.setCabeza(ordenada);
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