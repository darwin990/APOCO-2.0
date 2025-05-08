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

public class BubbleSort {
    public static void ordenarPorDineroRobado(ListaEnlazada lista) {
        if (lista.getCabeza() == null) return;

        boolean huboCambios;
        do {
            huboCambios = false;
            Nodo actual = lista.getCabeza();
            while (actual != null && actual.siguiente != null) {
                if (actual.dato.getDineroRobado() > actual.siguiente.dato.getDineroRobado()) {
                    Politico temp = actual.dato;
                    actual.dato = actual.siguiente.dato;
                    actual.siguiente.dato = temp;
                    huboCambios = true;
                }
                actual = actual.siguiente;
            }
        } while (huboCambios);
    }
}
