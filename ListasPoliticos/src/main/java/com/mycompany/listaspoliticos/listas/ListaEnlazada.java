/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.listas;

/**
 *
 * @author 57320
 */
import com.mycompany.listaspoliticos.modelo.Politico;
public class ListaEnlazada {
    private Nodo cabeza;

    public void agregar(Politico p) {
        Nodo nuevo = new Nodo(p);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void imprimir() {
        Nodo actual = cabeza;
        StringBuilder sb = new StringBuilder();
        while (actual != null) {
            sb.append(actual.dato.toString());
            if (actual.siguiente != null) {
                sb.append(", ");
            }
            actual = actual.siguiente;
        }
        System.out.println(sb.toString());
    }

    public ListaEnlazada clonar() {
        ListaEnlazada clon = new ListaEnlazada();
        Nodo actual = cabeza;
        while (actual != null) {
            Politico p = actual.dato;
            clon.agregar(new Politico(p.getId(), p.getDineroRobado(), p.getFechaNacimiento()));
            actual = actual.siguiente;
        }
        return clon;
    }
}

