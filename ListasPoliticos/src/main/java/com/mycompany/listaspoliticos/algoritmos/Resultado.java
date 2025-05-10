/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.algoritmos;

/**
 *
 * @author moral
 */
public class Resultado {
    private long tiempo;
    private int iteraciones;

    public Resultado(long tiempo, int iteraciones) {
        this.tiempo = tiempo;
        this.iteraciones = iteraciones;
    }

    public long getTiempo() {
        return tiempo;
    }

    public int getIteraciones() {
        return iteraciones;
    }
}
