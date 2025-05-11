package com.mycompany.listaspoliticos.algoritmos;

import com.mycompany.listaspoliticos.modelo.ListaEnlazadaBase;
import com.mycompany.listaspoliticos.modelo.NodoBase;
import java.util.Objects;

public class OrdenamientoBurbuja<T extends Comparable<T>, N extends NodoBase<T, N>>
        implements EstrategiaOrdenamiento<T, N> {

    private int iteraciones = 0;

    @Override
    public void ordenar(ListaEnlazadaBase<T, N> lista) {
        Objects.requireNonNull(lista);
        iteraciones = 0;
        int tamanno = lista.getTamanno();

        if (tamanno <= 1) return;

        boolean intercambiado;

        do {
            intercambiado = false;
            N actual = lista.getCabeza();

            for (int i = 0; i < tamanno - 1; i++) {
                iteraciones++;
                N siguiente = actual.getSiguiente();

                // Solo comparar si no hemos llegado al final de esta pasada
                if (i < tamanno - 1) {
                    if (actual.getDato().compareTo(siguiente.getDato()) > 0) {
                        swapDatos(actual, siguiente);
                        intercambiado = true;
                    }
                }

                actual = siguiente;
            }

            // Después de cada pasada completa, el último elemento está en su lugar
            tamanno--;
        } while (intercambiado && tamanno > 1);
    }

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

    @Override
    public int getIteraciones() {
        return iteraciones;
    }

    @Override
    public void resetIteraciones() {
        iteraciones = 0;
    }
}