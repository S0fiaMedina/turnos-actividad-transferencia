package org.universidad.model.estructuras;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/** Lista enlazada simple: almacena los turnos activos. */
public class ListaEnlazada<T> {
    private Nodo<T> cabeza;
    private int tamanio;

    public void agregarAlFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> aux = cabeza;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
        tamanio++;
    }

    public boolean eliminar(T dato) {
        if (cabeza == null) return false;
        if (cabeza.dato.equals(dato)) {
            cabeza = cabeza.siguiente;
            tamanio--;
            return true;
        }
        Nodo<T> aux = cabeza;
        while (aux.siguiente != null && !aux.siguiente.dato.equals(dato)) {
            aux = aux.siguiente;
        }
        if (aux.siguiente == null) return false;
        aux.siguiente = aux.siguiente.siguiente;
        tamanio--;
        return true;
    }

    public T buscar(Predicate<T> condicion) {
        Nodo<T> aux = cabeza;
        while (aux != null) {
            if (condicion.test(aux.dato)) return aux.dato;
            aux = aux.siguiente;
        }
        return null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public List<T> toList() {
        List<T> resultado = new ArrayList<>();
        Nodo<T> aux = cabeza;
        while (aux != null) {
            resultado.add(aux.dato);
            aux = aux.siguiente;
        }
        return resultado;
    }
}
