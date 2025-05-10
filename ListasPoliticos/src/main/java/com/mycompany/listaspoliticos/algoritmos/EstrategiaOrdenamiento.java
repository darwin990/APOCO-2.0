package com.mycompany.listaspoliticos.algoritmos;

import com.mycompany.listaspoliticos.modelo.ListaEnlazadaBase;
import com.mycompany.listaspoliticos.modelo.NodoBase;

public interface EstrategiaOrdenamiento<T extends Comparable<T>, N extends NodoBase<T, N>> {

    void ordenar(ListaEnlazadaBase<T, N> lista);

    int getIteraciones();

    void resetIteraciones();
}