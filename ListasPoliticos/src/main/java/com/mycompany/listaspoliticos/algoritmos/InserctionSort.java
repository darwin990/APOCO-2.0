/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.algoritmos;

/**
 *
 * @author 57320
 */
import com.mycompany.listaspoliticos.listas.ListaEnlazada;
import com.mycompany.listaspoliticos.listas.Nodo;
import com.mycompany.listaspoliticos.modelo.Politico;

public class InserctionSort {
    public static void ordenarPorDineroRobado(ListaEnlazada lista) {
        Nodo cabeza = lista.getCabeza();
        if (cabeza == null || cabeza.siguiente == null) return;

        Nodo ordenada = null;

        while (cabeza != null) {
            Nodo actual = cabeza;
            cabeza = cabeza.siguiente;
            if (ordenada == null || actual.dato.getDineroRobado() < ordenada.dato.getDineroRobado()) {
                actual.siguiente = ordenada;
                ordenada = actual;
            } else {
                Nodo temp = ordenada;
                while (temp.siguiente != null && temp.siguiente.dato.getDineroRobado() < actual.dato.getDineroRobado()) {
                    temp = temp.siguiente;
                }
                actual.siguiente = temp.siguiente;
                temp.siguiente = actual;
            }
        }

        try {
            java.lang.reflect.Field field = ListaEnlazada.class.getDeclaredField("cabeza");
            field.setAccessible(true);
            field.set(lista, ordenada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

