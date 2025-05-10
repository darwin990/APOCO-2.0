/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author 57320
 */
package com.mycompany.listaspoliticos.main;

import com.mycompany.listaspoliticos.controlador.Controlador;
import com.mycompany.listaspoliticos.vista.VistaPoliticos;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // Crear el controlador
        Controlador controlador = new Controlador();
        
        // Crear y mostrar la vista
        javax.swing.SwingUtilities.invokeLater(() -> {
            VistaPoliticos vista = new VistaPoliticos(controlador);
            configurarVentana(vista);
        });
    }
    
    private static void configurarVentana(VistaPoliticos vista) {
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setTitle("Simulador de Ordenamiento de Políticos");
        vista.pack();
        vista.setLocationRelativeTo(null); // Centrar en la pantalla
        vista.setVisible(true);
        
//        // Mostrar mensaje de bienvenida
//        vista.getTxtConsola().append("=== SIMULADOR DE ORDENAMIENTO DE POLÍTICOS ===\n");
//        vista.getTxtConsola().append("Sistema listo para operar\n");
//        vista.getTxtConsola().append("1. Configure los parámetros iniciales\n");
//        vista.getTxtConsola().append("2. Haga clic en 'Generar y Ordenar'\n");
//        vista.getTxtConsola().append("3. Use 'Aumentar y Re-ordenar' para pruebas\n");
    }
}