/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.vista;

/**
 *
 * @author 57320
 */
import com.mycompany.listaspoliticos.listas.ListaEnlazada;

public class Vista {
    public static void mostrarResultado(String mensaje, ListaEnlazada lista) {
        System.out.println("\n" + mensaje);
        lista.imprimir();
    }
}

