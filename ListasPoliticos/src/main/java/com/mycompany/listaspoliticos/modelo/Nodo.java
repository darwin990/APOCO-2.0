package com.mycompany.listaspoliticos.modelo;

public class Nodo<T> extends NodoBase<T, Nodo<T>> {
    private Nodo<T> siguiente;

    public Nodo(T dato) {
        super(dato);
        this.siguiente = null;
    }

    public Nodo(T dato, Nodo<T> siguiente) {
        super(dato);
        this.siguiente = siguiente;
    }

    @Override
    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    @Override
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}