/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.modelo;

import java.util.Objects;

/**
 * Clase abstracta base para nodos de listas enlazadas con soporte para siguiente.
 * 
 * @param <T> El tipo de dato almacenado en el nodo
 * @param <N> El tipo concreto de nodo (para el método setSiguiente)
 */
public abstract class NodoBase<T, N extends NodoBase<T, N>> {
    /** El dato o elemento almacenado en este nodo. */
    protected T dato;

    /**
     * Constructor protegido para la clase base.
     * @param dato El dato a almacenar en el nodo
     */
    protected NodoBase(T dato) {
        this.dato = dato;
    }

    // --- Métodos comunes ---
    
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Establece el nodo siguiente.
     * Método abstracto que será implementado por las subclases.
     * @param siguiente El nodo que será el siguiente
     */
    public abstract void setSiguiente(N siguiente);

    /**
     * Obtiene el nodo siguiente.
     * Método abstracto que será implementado por las subclases.
     * @return El nodo siguiente
     */
    public abstract N getSiguiente();

    @Override
    public String toString() {
        return Objects.toString(dato, "null");
    }
}