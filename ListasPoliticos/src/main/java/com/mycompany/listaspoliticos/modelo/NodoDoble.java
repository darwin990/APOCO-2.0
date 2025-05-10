/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.modelo;

public class NodoDoble<T> extends NodoBase<T, NodoDoble<T>> {
    private NodoDoble<T> siguiente;
    private NodoDoble<T> anterior;

    public NodoDoble(T dato) {
        super(dato);
        this.siguiente = null;
        this.anterior = null;
    }

    public NodoDoble(T dato, NodoDoble<T> anterior, NodoDoble<T> siguiente) {
        super(dato);
        this.anterior = anterior;
        this.siguiente = siguiente;
    }

    @Override
    public NodoDoble<T> getSiguiente() {
        return siguiente;
    }

    @Override
    public void setSiguiente(NodoDoble<T> siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDoble<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble<T> anterior) {
        this.anterior = anterior;
    }
}