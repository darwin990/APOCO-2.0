/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.modelo;

import java.util.NoSuchElementException;
import com.mycompany.listaspoliticos.algoritmos.EstrategiaOrdenamiento;
import java.util.Objects;

/**
 * Clase abstracta base para listas enlazadas que comparten funcionalidad común,
 * especialmente el soporte para estrategias de ordenamiento.
 *
 * @param <T> El tipo de elementos almacenados en la lista.
 * @param <N> El tipo de nodo utilizado en la lista (debe extender NodoBase<T, N>)
 */
public abstract class ListaEnlazadaBase<T extends Comparable<T>, N extends NodoBase<T, N>> {
    protected int tamanno;
    
    // Métodos comunes a ambas listas
    public int getTamanno() {
        return tamanno;
    }
    
    public boolean estaVacia() {
        return tamanno == 0;
    }
    
    public abstract void borrarLista();
    public abstract void imprimir();
    public abstract N getCabeza();
    public abstract void setCabeza(N cabeza);
    
    /**
     * Ordena esta lista utilizando la estrategia de ordenación proporcionada.
     * La lista se modifica "in situ".
     * Requiere que el tipo de los elementos {@code T} implemente {@link Comparable}.
     * @param estrategia
     */
    @SuppressWarnings("unchecked")
    public void ordenar(EstrategiaOrdenamiento<T, N> estrategia) {
        Objects.requireNonNull(estrategia, "La estrategia de ordenamiento no puede ser null.");

        if (this.tamanno <= 1) {
            return;
        }

        if (!(getPrimerElemento() instanceof Comparable)) {
            throw new IllegalStateException("Los elementos de la lista deben implementar Comparable para ser ordenados.");
        }

        estrategia.ordenar(this);
    }
    protected T getPrimerElemento() {
        return estaVacia() ? null : getCabeza().getDato();
    }
    
    // Métodos adicionales que podrían ser comunes
    public abstract void insertarAlInicio(T dato);
    public abstract void insertarAlFinal(T dato);
    public abstract boolean contiene(T dato);
}