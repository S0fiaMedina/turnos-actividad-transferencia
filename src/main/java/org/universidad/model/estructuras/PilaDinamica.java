package org.universidad.model.estructuras;

import java.util.ArrayList;
import java.util.List;

/** Pila dinámica (LIFO): guarda el historial de turnos atendidos. */
public class PilaDinamica<T> {
    private Nodo<T> cima;
    private int tamanio;

    public void apilar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
        tamanio++;
    }

    public T desapilar() {
        if (cima == null) return null;
        T dato = cima.dato;
        cima = cima.siguiente;
        tamanio--;
        return dato;
    }

    public List<T> toList() {
        List<T> resultado = new ArrayList<>();
        Nodo<T> aux = cima;
        while (aux != null) {
            resultado.add(aux.dato);
            aux = aux.siguiente;
        }
        return resultado;
    }

    public int getTamanio() {
        return tamanio;
    }
}
