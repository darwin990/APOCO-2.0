/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.vista;

import com.mycompany.listaspoliticos.controlador.Controlador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class VistaPoliticos extends JFrame {
    private Controlador controlador;
    
    // Componentes de entrada
    private JTextField txtTamanoInicial;
    private JTextField txtFactorCrecimiento;
    private JComboBox<String> cbTipoLista;
    private JComboBox<String> cbAlgoritmo;
    
    // Botones
    private JButton btnGenerarOrdenar;
    private JButton btnAumentarOrdenar;
    
    // Áreas de visualización
    private JTextArea txtResultados;
    private JTextArea txtConsola;
    
    // Variables de estado
    private int tamanoActual;
    private double factorCrecimiento;

    public VistaPoliticos(Controlador controlador) {
        this.controlador = controlador;
        configurarVentana();
        inicializarComponentes();
        organizarComponentes();
        agregarEventos();
    }

    private void configurarVentana() {
        setTitle("Simulador de Ordenamiento de Políticos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        // Campos de texto
        txtTamanoInicial = new JTextField("100", 10);
        txtFactorCrecimiento = new JTextField("0.5", 10); // 50% por defecto
        
        // ComboBoxes
        cbTipoLista = new JComboBox<>(new String[]{"Lista Simple", "Lista Circular", "Lista Doble"});
        
        // Obtener algoritmos del controlador
        Map<Integer, String> algoritmos = controlador.getNombresAlgoritmos();
        cbAlgoritmo = new JComboBox<>(algoritmos.values().toArray(new String[0]));
        
        // Botones
        btnGenerarOrdenar = new JButton("Generar y Ordenar");
        btnAumentarOrdenar = new JButton("Aumentar y Re-ordenar");
        
        // Áreas de texto
        txtResultados = new JTextArea(10, 50);
        txtResultados.setEditable(false);
        txtResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        txtConsola = new JTextArea(5, 50);
        txtConsola.setEditable(false);
        txtConsola.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    private void organizarComponentes() {
        // Panel superior para parámetros
        JPanel panelParametros = new JPanel(new GridLayout(4, 2, 5, 5));
        panelParametros.add(new JLabel("Tamaño inicial:"));
        panelParametros.add(txtTamanoInicial);
        panelParametros.add(new JLabel("Factor de crecimiento (0-1):"));
        panelParametros.add(txtFactorCrecimiento);
        panelParametros.add(new JLabel("Tipo de lista:"));
        panelParametros.add(cbTipoLista);
        panelParametros.add(new JLabel("Algoritmo:"));
        panelParametros.add(cbAlgoritmo);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnGenerarOrdenar);
        panelBotones.add(btnAumentarOrdenar);

        // Panel de resultados
        JPanel panelResultados = new JPanel(new BorderLayout());
        panelResultados.add(new JLabel("Resultados:"), BorderLayout.NORTH);
        panelResultados.add(new JScrollPane(txtResultados), BorderLayout.CENTER);

        // Panel de consola
        JPanel panelConsola = new JPanel(new BorderLayout());
        panelConsola.add(new JLabel("Consola:"), BorderLayout.NORTH);
        panelConsola.add(new JScrollPane(txtConsola), BorderLayout.CENTER);

        // Panel para agrupar resultados y consola
        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.add(panelResultados, BorderLayout.CENTER);
        panelSur.add(panelConsola, BorderLayout.SOUTH);

        // Organización principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(5, 5));
        panelPrincipal.add(panelParametros, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);  // Botones en el centro
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);       // Resultados y consola abajo

        getContentPane().add(panelPrincipal);
    }

    private void agregarEventos() {
        btnGenerarOrdenar.addActionListener(e -> generarYOrdenar());
        btnAumentarOrdenar.addActionListener(e -> aumentarYReordenar());
    }

    private void generarYOrdenar() {
        try {
            // Obtener parámetros
            tamanoActual = Integer.parseInt(txtTamanoInicial.getText());
            factorCrecimiento = Double.parseDouble(txtFactorCrecimiento.getText());
            int tipoLista = cbTipoLista.getSelectedIndex() + 1;
            int algoritmo = cbAlgoritmo.getSelectedIndex() + 1;
            
            // Validaciones
            if (tamanoActual <= 0) {
                throw new IllegalArgumentException("El tamaño debe ser mayor que cero");
            }
            if (factorCrecimiento < 0 || factorCrecimiento > 1) {
                throw new IllegalArgumentException("El factor debe estar entre 0 y 1");
            }
            
            // Crear lista y generar datos
            controlador.crearLista(tipoLista);
            controlador.generarPoliticosAleatorios(tamanoActual);
            
            // Ordenar y medir tiempo
            long inicio = System.currentTimeMillis();
            controlador.ordenarLista(algoritmo);
            long tiempo = System.currentTimeMillis() - inicio;
            
            // Mostrar resultados
            String tipoListaStr = (String) cbTipoLista.getSelectedItem();
            String algoritmoStr = (String) cbAlgoritmo.getSelectedItem();
            
            txtResultados.setText(String.format(
                "Tipo de lista: %s\n" +
                "Algoritmo: %s\n" +
                "Tamaño inicial: %d\n" +
                "Tiempo de ordenamiento: %d ms\n" +
                "---------------------------------\n",
                tipoListaStr, algoritmoStr, tamanoActual, tiempo));
            
            txtConsola.append("Lista generada y ordenada exitosamente\n");
            txtConsola.append(String.format("Tamaño: %d, Algoritmo: %s\n", tamanoActual, algoritmoStr));
            
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void aumentarYReordenar() {
        try {
            if (controlador.estaVacia()) {
                throw new IllegalStateException("Primero debe generar una lista");
            }
            
            // Calcular nuevo tamaño
            int incremento = (int) (tamanoActual * factorCrecimiento);
            int nuevoTamano = tamanoActual + incremento;
            int algoritmo = cbAlgoritmo.getSelectedIndex() + 1;
            
            // Generar datos adicionales
            controlador.generarPoliticosAleatorios(incremento);
            
            // Ordenar y medir tiempo
            long inicio = System.currentTimeMillis();
            controlador.ordenarLista(algoritmo);
            long tiempo = System.currentTimeMillis() - inicio;
            
            // Actualizar resultados
            String algoritmoStr = (String) cbAlgoritmo.getSelectedItem();
            
            txtResultados.append(String.format(
                "Nuevo tamaño: %d (+%d)\n" +
                "Tiempo de re-ordenamiento: %d ms\n" +
                "---------------------------------\n",
                nuevoTamano, incremento, tiempo));
            
            txtConsola.append(String.format(
                "Lista aumentada en %d elementos y re-ordenada\n" +
                "Nuevo tamaño: %d, Algoritmo: %s\n",
                incremento, nuevoTamano, algoritmoStr));
            
            // Actualizar tamaño actual
            tamanoActual = nuevoTamano;
            
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        txtConsola.append("ERROR: " + mensaje + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controlador controlador = new Controlador();
            new VistaPoliticos(controlador).setVisible(true);
        });
    }
}