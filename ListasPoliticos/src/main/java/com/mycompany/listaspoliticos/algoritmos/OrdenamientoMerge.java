package com.mycompany.listaspoliticos.algoritmos;

import com.mycompany.listaspoliticos.modelo.*;
import java.util.Objects;

public class OrdenamientoMerge<T extends Comparable<T>, N extends NodoBase<T, N>>
        implements EstrategiaOrdenamiento<T, N> {

    private int iteraciones = 0;

    @Override
    public void ordenar(ListaEnlazadaBase<T, N> lista) {
        Objects.requireNonNull(lista);
        iteraciones = 0;
        if (lista.getTamanno() <= 1) return;

        N nuevaCabeza = mergeSort(lista.getCabeza());
        lista.setCabeza(nuevaCabeza);
    }

    private N mergeSort(N cabeza) {
        if (cabeza == null || cabeza.getSiguiente() == null) return cabeza;

        N medio = encontrarMedio(cabeza);
        N siguiente = medio.getSiguiente();
        medio.setSiguiente(null);

        N izquierda = mergeSort(cabeza);
        N derecha = mergeSort(siguiente);

        return fusionar(izquierda, derecha);
    }

    private N encontrarMedio(N cabeza) {
        N lento = cabeza;
        N rapido = cabeza.getSiguiente();

        while (rapido != null && rapido.getSiguiente() != null) {
            iteraciones++;
            lento = lento.getSiguiente();
            rapido = rapido.getSiguiente().getSiguiente();
        }
        return lento;
    }

    private N fusionar(N izq, N der) {
        if (izq == null) return der;
        if (der == null) return izq;

        N resultado;
        iteraciones++;
        if (izq.getDato().compareTo(der.getDato()) <= 0) {
            resultado = izq;
            resultado.setSiguiente(fusionar(izq.getSiguiente(), der));
        } else {
            resultado = der;
            resultado.setSiguiente(fusionar(izq, der.getSiguiente()));
        }
        return resultado;
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