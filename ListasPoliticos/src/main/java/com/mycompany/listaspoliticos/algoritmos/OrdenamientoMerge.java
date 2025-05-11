package com.mycompany.listaspoliticos.algoritmos;

import com.mycompany.listaspoliticos.modelo.*;
import java.util.Objects;

public class OrdenamientoMerge<T extends Comparable<T>, N extends NodoBase<T, N>>
        implements EstrategiaOrdenamiento<T, N> {

    private int iteraciones = 0;

    @Override
    public void ordenar(ListaEnlazadaBase<T, N> lista) {
        Objects.requireNonNull(lista, "La lista no puede ser null");
        iteraciones = 0;
        int tamanno = lista.getTamanno();

        if (tamanno <= 1) return;

        // Verificar si es circular
        boolean esCircular = lista instanceof ListaEnlazadaSimpleCircular;
        N cabeza = lista.getCabeza();
        N ultimo = null;

        if (esCircular) {
            // Usar el método seguro para encontrar el último nodo
            ultimo = encontrarUltimoNodoSeguro(cabeza);

            // Si no encontramos último válido o hay error, no continuar
            if (ultimo == null) {
                throw new IllegalStateException("La lista circular está mal formada");
            }

            // Romper circularidad temporalmente
            ultimo.setSiguiente(null);
        }

        // Ordenar la lista
        N nuevaCabeza = mergeSort(cabeza);

        // Restaurar circularidad si era originalmente circular
        if (esCircular && nuevaCabeza != null) {
            ultimo = encontrarUltimoNodoSeguro(nuevaCabeza);
            if (ultimo != null) {
                ultimo.setSiguiente(nuevaCabeza);
            } else {
                throw new IllegalStateException("No se pudo reconstruir la lista circular");
            }
        }

        lista.setCabeza(nuevaCabeza);
    }

    /**
     * Método seguro para encontrar el último nodo en listas lineales o circulares
     * con protección contra bucles infinitos
     */
    private N encontrarUltimoNodoSeguro(N cabeza) {
        if (cabeza == null) return null;

        N actual = cabeza;
        int contador = 0;
        int limiteSeguro = 100000; // Límite razonable para prevenir bucles infinitos

        // Avanzar hasta encontrar el último nodo
        while (actual.getSiguiente() != null && 
               actual.getSiguiente() != cabeza && 
               contador < limiteSeguro) {
            actual = actual.getSiguiente();
            contador++;
        }

        // Verificar si salió por límite seguro
        if (contador >= limiteSeguro) {
            return null; // Indica lista mal formada
        }

        return actual;
    }

    private N mergeSort(N cabeza) {
        if (cabeza == null || cabeza.getSiguiente() == null) {
            return cabeza;
        }

        // Encontrar el medio y dividir la lista
        N medio = encontrarMedio(cabeza);
        N siguienteDeMedio = medio.getSiguiente();
        medio.setSiguiente(null);

        // Ordenar recursivamente ambas mitades
        N izquierda = mergeSort(cabeza);
        N derecha = mergeSort(siguienteDeMedio);

        // Fusionar las mitades ordenadas
        return fusionar(izquierda, derecha);
    }

    private N encontrarMedio(N cabeza) {
        if (cabeza == null) return null;
        
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
        // Caso base
        if (izq == null) return der;
        if (der == null) return izq;

        N resultado;
        iteraciones++;
        
        // Elegir el menor valor
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