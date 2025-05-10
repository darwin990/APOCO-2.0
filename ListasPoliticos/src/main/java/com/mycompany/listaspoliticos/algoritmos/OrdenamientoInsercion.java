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
        if (lista.getTamanno() <= 1) return;

        N ordenada = null;
        N actual = lista.getCabeza();

        while (actual != null) {
            iteraciones++;
            N siguiente = actual.getSiguiente();
            if (ordenada == null || ordenada.getDato().compareTo(actual.getDato()) >= 0) {
                actual.setSiguiente(ordenada);
                ordenada = actual;
            } else {
                N temp = ordenada;
                while (temp.getSiguiente() != null && temp.getSiguiente().getDato().compareTo(actual.getDato()) < 0) {
                    iteraciones++;
                    temp = temp.getSiguiente();
                }
                actual.setSiguiente(temp.getSiguiente());
                temp.setSiguiente(actual);
            }
            actual = siguiente;
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