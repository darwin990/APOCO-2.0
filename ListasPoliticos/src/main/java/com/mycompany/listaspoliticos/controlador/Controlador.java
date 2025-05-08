/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.controlador;

/**
 *
 * @author 57320
 */
import com.mycompany.listaspoliticos.algoritmos.BubbleSort;
import com.mycompany.listaspoliticos.algoritmos.InserctionSort;
import com.mycompany.listaspoliticos.listas.ListaEnlazada;
import com.mycompany.listaspoliticos.modelo.Politico;
import com.mycompany.listaspoliticos.vista.Vista;

public class Controlador {

    private ListaEnlazada listaOriginal;

    public Controlador() {
        listaOriginal = new ListaEnlazada();
        cargarDatos();
    }

    private void cargarDatos() {
        listaOriginal.agregar(new Politico(1, 5000, "1970-01-01"));
        listaOriginal.agregar(new Politico(2, 2000, "1980-03-22"));
        listaOriginal.agregar(new Politico(3, 9000, "1965-11-10"));
        listaOriginal.agregar(new Politico(4, 1000, "1990-06-15"));
        listaOriginal.agregar(new Politico(5, 3000, "1985-09-09"));
    }

    public void ejecutar() {
        ListaEnlazada clonBubble = listaOriginal.clonar();
        ListaEnlazada clonInsercion = listaOriginal.clonar();

        BubbleSort.ordenarPorDineroRobado(clonBubble);
        InserctionSort.ordenarPorDineroRobado(clonInsercion);

        Vista.mostrarResultado("Ordenamiento por Bubble Sort:", clonBubble);
        Vista.mostrarResultado("Ordenamiento por Inserción:", clonInsercion);
    }
}
