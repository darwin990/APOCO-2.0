/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.modelo;

/**
 *
 * @author 57320
 */
public class Politico {
    int id;
    int dineroRobado;
    String fechaNacimiento;

    public Politico(int id, int dineroRobado, String fechaNacimiento) {
        this.id = id;
        this.dineroRobado = dineroRobado;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() { return id; }
    public int getDineroRobado() { return dineroRobado; }
    public String getFechaNacimiento() { return fechaNacimiento; }

    @Override
    public String toString() {
        return "ID: " + id + ", Dinero robado: " + dineroRobado + ", Fecha de nacimiento: " + fechaNacimiento;
    }
}


