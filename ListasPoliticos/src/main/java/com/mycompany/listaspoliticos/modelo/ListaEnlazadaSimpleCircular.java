/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.modelo;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ListaEnlazadaSimpleCircular<T extends Comparable<T>> extends ListaEnlazadaBase<T, Nodo<T>>{
    private Nodo<T> ultimo;  // Referencia al último nodo (que apunta a la cabeza)

    public ListaEnlazadaSimpleCircular() {
        this.ultimo = null;
        this.tamanno = 0;
    }

    @Override
    public Nodo<T> getCabeza() {
        return estaVacia() ? null : this.ultimo.getSiguiente();
    }

    @Override
    public void setCabeza(Nodo<T> cabeza) {
        if (estaVacia()) {
            if (cabeza != null) {
                throw new IllegalStateException("No se puede establecer cabeza en lista vacía");
            }
            return;
        }
        
        if (cabeza == null) {
            this.ultimo = null;
            this.tamanno = 0;
            return;
        }

        // Reconstruir la lista circular con la nueva cabeza
        Nodo<T> nuevaCabeza = cabeza;
        Nodo<T> actual = nuevaCabeza;
        Nodo<T> nuevoUltimo = this.ultimo;
        
        // Buscar el nuevo último nodo (el que apunta a la nueva cabeza)
        while (actual.getSiguiente() != nuevaCabeza && actual.getSiguiente() != null) {
            if (actual.getSiguiente() == this.ultimo) {
                nuevoUltimo = actual;
            }
            actual = actual.getSiguiente();
            
            // Prevenir bucles infinitos en caso de nodos mal formados
            if (actual == nuevaCabeza) {
                break;
            }
        }
        
        this.ultimo = nuevoUltimo;
        if (this.ultimo != null) {
            this.ultimo.setSiguiente(nuevaCabeza);
        }
    }

    @Override
    public void borrarLista() {
        this.ultimo = null;
        this.tamanno = 0;
    }

    @Override
    public void imprimir() {
        if (estaVacia()) {
            System.out.println("Lista Circular Vacía");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("HEAD -> ");
        Nodo<T> actual = getCabeza();
        for (int i = 0; i < this.tamanno; i++) {
            sb.append(actual);
            actual = actual.getSiguiente();
            if (i < this.tamanno - 1) {
                sb.append(" -> ");
            }
        }
        sb.append(" -> (HEAD)");
        System.out.println(sb.toString());
    }

    @Override
    public void insertarAlInicio(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (estaVacia()) {
            this.ultimo = nuevoNodo;
            this.ultimo.setSiguiente(this.ultimo);
        } else {
            nuevoNodo.setSiguiente(this.ultimo.getSiguiente());
            this.ultimo.setSiguiente(nuevoNodo);
        }
        this.tamanno++;
    }

    @Override
    public void insertarAlFinal(T dato) {
        insertarAlInicio(dato);
        if (this.tamanno > 1) {
            this.ultimo = this.ultimo.getSiguiente();
        }
    }

    @Override
    public boolean contiene(T dato) {
        return buscarNodo(dato) != null;
    }

    // Métodos específicos de la lista circular
    public boolean insertarDespuesDe(T datoExistente, T datoNuevo) {
        Nodo<T> nodoExistente = buscarNodo(datoExistente);
        if (nodoExistente == null) {
            return false;
        }

        Nodo<T> nuevoNodo = new Nodo<>(datoNuevo, nodoExistente.getSiguiente());
        nodoExistente.setSiguiente(nuevoNodo);

        if (nodoExistente == this.ultimo) {
            this.ultimo = nuevoNodo;
        }
        this.tamanno++;
        return true;
    }

    public T eliminarAlInicio() {
        if (estaVacia()) {
            throw new NoSuchElementException("No se puede eliminar de una lista circular vacía.");
        }

        Nodo<T> cabeza = getCabeza();
        T datoEliminado = cabeza.getDato();

        if (this.ultimo == cabeza) {
            this.ultimo = null;
        } else {
            this.ultimo.setSiguiente(cabeza.getSiguiente());
        }
        this.tamanno--;
        return datoEliminado;
    }

    // Métodos auxiliares privados
    private Nodo<T> buscarNodo(T datoBusqueda) {
        if (estaVacia()) {
            return null;
        }
        
        Nodo<T> actual = getCabeza();
        for (int i = 0; i < this.tamanno; i++) {
            if (Objects.equals(actual.getDato(), datoBusqueda)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}