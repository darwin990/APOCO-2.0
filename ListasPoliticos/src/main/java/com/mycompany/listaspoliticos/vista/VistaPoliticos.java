package com.mycompany.listaspoliticos.vista;

import com.mycompany.listaspoliticos.controlador.Controlador;
import com.mycompany.listaspoliticos.modelo.Politico;
import com.mycompany.listaspoliticos.algoritmos.Resultado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class VistaPoliticos extends JFrame {
    private Controlador controlador;

    // Entradas
    private JTextField txtTamanoInicial;
    private JTextField txtFactorCrecimiento;
    private JComboBox<String> cbTipoLista;
    private JComboBox<String> cbAlgoritmo;

    // Tabla
    private JTable tablaResultados;
    private DefaultTableModel modeloTabla;

    // Consola
    private JTextArea txtConsola;

    // Botones
    private JButton btnGenerarOrdenar;

    // Estado
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
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        txtTamanoInicial = new JTextField("100", 10);
        txtFactorCrecimiento = new JTextField("0.5", 10);

        cbTipoLista = new JComboBox<>(new String[]{"Lista Simple", "Lista Circular", "Lista Doble"});

        Map<Integer, String> algoritmos = controlador.getNombresAlgoritmos();
        cbAlgoritmo = new JComboBox<>(algoritmos.values().toArray(new String[0]));

        btnGenerarOrdenar = new JButton("Ejecutar Ordenamiento");

        modeloTabla = new DefaultTableModel(new Object[]{"Algoritmo", "Caso", "Tiempo (ms)", "Iteraciones"}, 0);
        tablaResultados = new JTable(modeloTabla);

        txtConsola = new JTextArea(5, 50);
        txtConsola.setEditable(false);
        txtConsola.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    private void organizarComponentes() {
        JPanel panelParametros = new JPanel(new GridLayout(4, 2, 5, 5));
        panelParametros.add(new JLabel("Tamaño Inicial:"));
        panelParametros.add(txtTamanoInicial);
        panelParametros.add(new JLabel("Delta (crecimiento):"));
        panelParametros.add(txtFactorCrecimiento);
        panelParametros.add(new JLabel("Tipo de Lista:"));
        panelParametros.add(cbTipoLista);
        panelParametros.add(new JLabel("Algoritmo:"));
        panelParametros.add(cbAlgoritmo);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGenerarOrdenar);

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.add(new JScrollPane(tablaResultados), BorderLayout.CENTER);
        panelTabla.setBorder(BorderFactory.createTitledBorder("Resultados"));

        JPanel panelConsola = new JPanel(new BorderLayout());
        panelConsola.add(new JScrollPane(txtConsola), BorderLayout.CENTER);
        panelConsola.setBorder(BorderFactory.createTitledBorder("Consola"));

        JPanel panelPrincipal = new JPanel(new BorderLayout(5, 5));
        panelPrincipal.add(panelParametros, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(panelTabla, BorderLayout.SOUTH);

        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        getContentPane().add(panelConsola, BorderLayout.SOUTH);
    }

    private void agregarEventos() {
        btnGenerarOrdenar.addActionListener(e -> generarYOrdenar());
    }

    private void generarYOrdenar() {
        try {
        	modeloTabla.setRowCount(0);
        	
            // Entradas
            tamanoActual = Integer.parseInt(txtTamanoInicial.getText());
            factorCrecimiento = Double.parseDouble(txtFactorCrecimiento.getText());
            int tipoLista = cbTipoLista.getSelectedIndex() + 1;
            int algoritmo = cbAlgoritmo.getSelectedIndex() + 1;

            if (tamanoActual <= 0 || factorCrecimiento < 0 || factorCrecimiento > 1) {
                throw new IllegalArgumentException("Verifique los valores ingresados.");
            }

            // DESORDENADO
            controlador.crearLista(tipoLista);
            controlador.generarPoliticosAleatorios(tamanoActual);
            Resultado r1 = controlador.ordenarYMedir(algoritmo, "Desordenado");
            modeloTabla.addRow(new Object[]{cbAlgoritmo.getSelectedItem(), "Desordenado", r1.getTiempo(), r1.getIteraciones()});

            // ORDENADO
            Resultado r2 = controlador.ordenarYMedir(algoritmo, "Ordenado");
            modeloTabla.addRow(new Object[]{cbAlgoritmo.getSelectedItem(), "Ordenado", r2.getTiempo(), r2.getIteraciones()});

            // ORDEN INVERSO
            List<Politico> listaInvertida = controlador.obtenerPoliticos();
            Collections.reverse(listaInvertida);

            controlador.crearLista(tipoLista);
            controlador.insertarPoliticos(listaInvertida);

            Resultado r3 = controlador.ordenarYMedir(algoritmo, "Orden inverso");
            modeloTabla.addRow(new Object[]{cbAlgoritmo.getSelectedItem(), "Orden inverso", r3.getTiempo(), r3.getIteraciones()});

            txtConsola.append("✅ Pruebas completadas para: " + cbAlgoritmo.getSelectedItem() + "\n");

        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        txtConsola.append("❌ ERROR: " + mensaje + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controlador controlador = new Controlador();
            new VistaPoliticos(controlador).setVisible(true);
        });
    }
}
