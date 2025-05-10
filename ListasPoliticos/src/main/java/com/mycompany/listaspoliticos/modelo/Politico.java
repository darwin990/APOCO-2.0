/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.modelo;

import java.time.LocalDate;
import java.time.Period;

public class Politico implements Comparable<Politico> {
    private int id;
    private int dineroRobado; // en millones de rubros
    private LocalDate fechaNacimiento;

    public Politico(int id, int dineroRobado, LocalDate fechaNacimiento) {
        this.id = id;
        this.dineroRobado = dineroRobado;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public int getDineroRobado() {
        return dineroRobado;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "ID: " + id +
               ", Dinero robado: " + dineroRobado +
               ", Edad: " + getEdad() +
               ", Fecha de nacimiento: " + fechaNacimiento;
    }

    @Override
    public int compareTo(Politico otro) {
        // Comparar por dinero robado (de mayor a menor)
        return Integer.compare(otro.dineroRobado, this.dineroRobado);
        
        // Otra opción: comparar por ID
        // return Integer.compare(this.id, otro.id);
        
        // Otra opción: comparar por edad
        // return Integer.compare(this.getEdad(), otro.getEdad());
    }
}