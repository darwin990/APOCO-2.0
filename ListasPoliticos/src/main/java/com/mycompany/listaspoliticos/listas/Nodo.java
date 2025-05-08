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

public class Nodo {
    public Politico dato;
    public Nodo siguiente;

    public Nodo(Politico dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
