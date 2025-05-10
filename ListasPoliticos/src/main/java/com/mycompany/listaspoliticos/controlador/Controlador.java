/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaspoliticos.controlador;

import com.mycompany.listaspoliticos.modelo.*;
import com.mycompany.listaspoliticos.algoritmos.*;
import java.time.LocalDate;
import java.util.*;

public class Controlador {
    private ListaEnlazadaBase<Politico, ? extends NodoBase<Politico, ?>> listaActual;
    private Map<Integer, EstrategiaOrdenamiento<Politico, ?>> algoritmos;
    private Map<Integer, String> nombresAlgoritmos;
    
    public Controlador() {
        this.listaActual = null;
        inicializarAlgoritmos();
    }
    
    private void inicializarAlgoritmos() {
        this.algoritmos = new HashMap<>();
        this.nombresAlgoritmos = new HashMap<>();
        
        // Registrar todos los algoritmos disponibles
        registrarAlgoritmo(1, "Burbuja", new OrdenamientoBurbuja<>());
        registrarAlgoritmo(2, "Inserción", new OrdenamientoInsercion<>());
        registrarAlgoritmo(3, "Merge Sort", new OrdenamientoMerge<>());
        registrarAlgoritmo(4, "QuickSort", new OrdenamientoQuickSort<>());
    }
    
    private void registrarAlgoritmo(int id, String nombre, EstrategiaOrdenamiento<Politico, ?> algoritmo) {
        this.algoritmos.put(id, algoritmo);
        this.nombresAlgoritmos.put(id, nombre);
    }
    
    /**
     * Crea una nueva lista del tipo especificado.
     * @param tipoLista 1=Simple, 2=Simple Circular, 3=Doble
     */
    @SuppressWarnings("unchecked")
    public void crearLista(int tipoLista) {
        switch (tipoLista) {
            case 1:
                listaActual = new ListaEnlazadaSimple<>();
                break;
            case 2:
                listaActual = new ListaEnlazadaSimpleCircular<>();
                break;
            case 3:
                listaActual = new ListaEnlazadaDoble<>();
                break;
            default:
                throw new IllegalArgumentException("Tipo de lista no válido");
        }
    }
    
    /**
     * Obtiene los nombres de los algoritmos de ordenamiento disponibles
     * @return Mapa con los IDs y nombres de los algoritmos
     */
    public Map<Integer, String> getNombresAlgoritmos() {
        return new HashMap<>(this.nombresAlgoritmos);
    }
    
    /**
     * Ordena la lista actual usando el algoritmo especificado.
     * @param idAlgoritmo ID del algoritmo (1=Burbuja, 2=Inserción, 3=Merge Sort, 4=QuickSort)
     */
    @SuppressWarnings("unchecked")
    public void ordenarLista(int idAlgoritmo) {
        verificarListaCreada();
        
        EstrategiaOrdenamiento<Politico, ?> algoritmo = algoritmos.get(idAlgoritmo);
        if (algoritmo == null) {
            throw new IllegalArgumentException("Algoritmo no válido");
        }
        
        if (listaActual instanceof ListaEnlazadaSimple) {
            ((ListaEnlazadaSimple<Politico>) listaActual).ordenar(
                (EstrategiaOrdenamiento<Politico, Nodo<Politico>>) algoritmo);
        } else if (listaActual instanceof ListaEnlazadaSimpleCircular) {
            ((ListaEnlazadaSimpleCircular<Politico>) listaActual).ordenar(
                (EstrategiaOrdenamiento<Politico, Nodo<Politico>>) algoritmo);
        } else if (listaActual instanceof ListaEnlazadaDoble) {
            ((ListaEnlazadaDoble<Politico>) listaActual).ordenar(
                (EstrategiaOrdenamiento<Politico, NodoDoble<Politico>>) algoritmo);
        }
    }
    
    /**
     * Inserta un político al inicio de la lista actual.
     */
    public void insertarAlInicio(int id, int dineroRobado, LocalDate fechaNacimiento) {
        verificarListaCreada();
        Politico politico = new Politico(id, dineroRobado, fechaNacimiento);
        listaActual.insertarAlInicio(politico);
    }
    
    /**
     * Inserta un político al final de la lista actual.
     */
    public void insertarAlFinal(int id, int dineroRobado, LocalDate fechaNacimiento) {
        verificarListaCreada();
        Politico politico = new Politico(id, dineroRobado, fechaNacimiento);
        listaActual.insertarAlFinal(politico);
    }
    
    /**
     * Elimina el primer político de la lista.
     * @return El político eliminado
     */
    public Politico eliminarAlInicio() {
        verificarListaCreada();
        if (listaActual instanceof ListaEnlazadaSimple) {
            return ((ListaEnlazadaSimple<Politico>) listaActual).eliminarAlInicio();
        } else if (listaActual instanceof ListaEnlazadaSimpleCircular) {
            return ((ListaEnlazadaSimpleCircular<Politico>) listaActual).eliminarAlInicio();
        } else if (listaActual instanceof ListaEnlazadaDoble) {
            return ((ListaEnlazadaDoble<Politico>) listaActual).eliminarAlInicio();
        }
        throw new UnsupportedOperationException("Tipo de lista no soportado");
    }
    
    /**
     * Busca un político por su ID.
     * @return true si se encuentra, false en caso contrario
     */
    public boolean buscarPorId(int id) {
        verificarListaCreada();
        Politico politicoBusqueda = new Politico(id, 0, LocalDate.now());
        return listaActual.contiene(politicoBusqueda);
    }
    
    /**
     * Imprime la lista actual en la consola.
     */
    public void imprimirLista() {
        verificarListaCreada();
        listaActual.imprimir();
    }
    
    /**
     * Obtiene todos los políticos de la lista actual como una lista.
     * @return Lista de políticos
     */
    public List<Politico> obtenerPoliticos() {
        verificarListaCreada();
        List<Politico> politicos = new ArrayList<>();
        
        if (listaActual instanceof ListaEnlazadaSimple) {
            Nodo<Politico> actual = ((ListaEnlazadaSimple<Politico>) listaActual).getCabeza();
            while (actual != null) {
                politicos.add(actual.getDato());
                actual = actual.getSiguiente();
            }
        } else if (listaActual instanceof ListaEnlazadaSimpleCircular) {
            ListaEnlazadaSimpleCircular<Politico> lista = (ListaEnlazadaSimpleCircular<Politico>) listaActual;
            if (!lista.estaVacia()) {
                Nodo<Politico> actual = lista.getCabeza();
                for (int i = 0; i < lista.getTamanno(); i++) {
                    politicos.add(actual.getDato());
                    actual = actual.getSiguiente();
                }
            }
        } else if (listaActual instanceof ListaEnlazadaDoble) {
            NodoDoble<Politico> actual = ((ListaEnlazadaDoble<Politico>) listaActual).getCabeza();
            while (actual != null) {
                politicos.add(actual.getDato());
                actual = actual.getSiguiente();
            }
        }
        
        return politicos;
    }
    
    /**
     * Borra todos los elementos de la lista actual.
     */
    public void borrarLista() {
        verificarListaCreada();
        listaActual.borrarLista();
    }
    
    /**
     * Verifica si la lista actual está vacía.
     * @return true si está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return listaActual == null || listaActual.estaVacia();
    }
    
    /**
     * Obtiene el tamaño de la lista actual.
     * @return Número de elementos en la lista
     */
    public int getTamanno() {
        return listaActual == null ? 0 : listaActual.getTamanno();
    }
    
    /**
     * Genera políticos aleatorios para pruebas
     * @param cantidad Número de políticos a generar
     */
    public void generarPoliticosAleatorios(int cantidad) {
        verificarListaCreada();
        Random random = new Random();
        
        for (int i = 0; i < cantidad; i++) {
            int id = listaActual.getTamanno() + 1;
            int dineroRobado = 100 + random.nextInt(9901); // 100 a 10,000 millones
            int año = 1940 + random.nextInt(60); // Nacidos entre 1940 y 2000
            int mes = 1 + random.nextInt(12);
            int dia = 1 + random.nextInt(28);
            LocalDate fecha = LocalDate.of(año, mes, dia);
            
            listaActual.insertarAlFinal(new Politico(id, dineroRobado, fecha));
        }
    }
    
    private void verificarListaCreada() {
        if (listaActual == null) {
            throw new IllegalStateException("No se ha creado ninguna lista");
        }
    }
}