package org.universidad.model.estructuras;

/** Cola dinámica (FIFO): organiza los turnos pendientes. */
public class ColaDinamica<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamanio;

    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (fin == null) {
            frente = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        tamanio++;
    }

    public T desencolar() {
        if (frente == null) return null;
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        tamanio--;
        return dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public boolean eliminar(T dato) {
        if (frente == null) return false;
        if (frente.dato.equals(dato)) {
            desencolar();
            return true;
        }
        Nodo<T> aux = frente;
        while (aux.siguiente != null && !aux.siguiente.dato.equals(dato)) {
            aux = aux.siguiente;
        }
        if (aux.siguiente == null) return false;
        if (aux.siguiente == fin) fin = aux;
        aux.siguiente = aux.siguiente.siguiente;
        tamanio--;
        return true;
    }
}