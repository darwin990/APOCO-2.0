/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.modelo;

import java.util.NoSuchElementException;
import com.mycompany.listaspoliticos.algoritmos.EstrategiaOrdenamiento;
import java.util.Objects;
    
/**
 * Implementación de una lista enlazada simple genérica.
 * Permite almacenar una secuencia ordenada de elementos de tipo {@code T}.
 * Soporta operaciones básicas de inserción, eliminación, búsqueda y clonación.
 * Mantiene referencias a la cabeza y la cola para optimizar algunas operaciones.
 * Puede ser ordenada usando el patrón Strategy si los elementos son {@link Comparable}.
 *
 * @param <T> El tipo de elementos almacenados en la lista.
 * @see Nodo
 * @see EstrategiaOrdenamiento
 * @author devapps
 * @version 1.2
 */
public class ListaEnlazadaSimple<T extends Comparable<T>> extends ListaEnlazadaBase<T, Nodo<T>> {

    private Nodo<T> cabeza;
    private Nodo<T> cola;
    // Eliminamos la redeclaración de tamanno (ya está en la clase base)

    public ListaEnlazadaSimple() {
        this.cabeza = null;
        this.cola = null;
        this.tamanno = 0; // Usamos el tamanno de la clase base
    }

    // --- Métodos sobrescritos ---
    @Override
    public Nodo<T> getCabeza() {
        return cabeza;
    }

    @Override
    public void setCabeza(Nodo<T> cabeza) {
        this.cabeza = cabeza;
        if (cabeza == null) {
            this.cola = null;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            this.cola = actual;
        }
    }

    // --- Métodos de inserción ---
    @Override
    public void insertarAlInicio(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato, this.cabeza);
        this.cabeza = nuevoNodo;
        if (this.cola == null) {
            this.cola = this.cabeza;
        }
        this.tamanno++;
    }

    @Override
    public void insertarAlFinal(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (estaVacia()) {
            this.cabeza = nuevoNodo;
            this.cola = nuevoNodo;
        } else {
            this.cola.setSiguiente(nuevoNodo);
            this.cola = nuevoNodo;
        }
        this.tamanno++;
    }

    // ... (resto de métodos de inserción se mantienen igual)

    // --- Métodos de eliminación ---
    public T eliminarAlInicio() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        T datoEliminado = this.cabeza.getDato();
        this.cabeza = this.cabeza.getSiguiente();
        this.tamanno--;
        if (estaVacia()) {
            this.cola = null;
        }
        return datoEliminado;
    }

    // ... (resto de métodos de eliminación se mantienen igual)

    // --- Otras utilidades ---
    @Override
    public void borrarLista() {
        this.cabeza = null;
        this.cola = null;
        this.tamanno = 0;
    }

    @Override
    public void imprimir() {
        if (estaVacia()) {
            System.out.println("Lista Simple Vacía");
            return;
        }
        StringBuilder sb = new StringBuilder("HEAD -> ");
        Nodo<T> actual = this.cabeza;
        while (actual != null) {
            sb.append(actual);
            actual = actual.getSiguiente();
            sb.append(actual != null ? " -> " : " -> NULL");
        }
        sb.append(" (TAIL: ").append(this.cola != null ? this.cola : "null").append(")");
        System.out.println(sb);
    }

    @Override
    public boolean contiene(T dato) {
        return buscarNodo(dato) != null;
    }

    // --- Métodos auxiliares privados ---
    private Nodo<T> buscarNodo(T datoBusqueda) {
        Nodo<T> actual = this.cabeza;
        while (actual != null) {
            if (Objects.equals(actual.getDato(), datoBusqueda)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    private Nodo<T> buscarNodoAnterior(T datoBusqueda) {
        if (estaVacia() || this.tamanno == 1 || Objects.equals(this.cabeza.getDato(), datoBusqueda)) {
            return null;
        }

        Nodo<T> actual = this.cabeza;
        while (actual.getSiguiente() != null) {
            if (Objects.equals(actual.getSiguiente().getDato(), datoBusqueda)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    protected T getPrimerElemento() {
        return estaVacia() ? null : cabeza.getDato();
    }
}