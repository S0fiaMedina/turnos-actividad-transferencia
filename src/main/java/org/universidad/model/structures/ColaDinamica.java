package org.universidad.model.structures;

/** Cola dinámica (FIFO): organiza los turnos pendientes. */
public class ColaDinamica<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamanio;


    /**
     * Agrega un nuevo elemento al final de la cola.
     *
     * @param dato elemento que se desea encolar
     */
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

    /**
     * Elimina y retorna el elemento ubicado al frente de la cola.
     * @return elemento eliminado o null si la cola está vacía
     */
    public T desencolar() {
        if (frente == null) return null;
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        tamanio--;
        return dato;
    }


    /**
     * Verifica si la cola no contiene elementos.
     * @return true si la cola está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return frente == null;
    }

    /**
     * Obtiene la cantidad de elementos almacenados en la cola.
     * @return número de elementos de la cola
     */
    public int getTamanio() {
        return tamanio;
    }

    /**
     * Busca y elimina la primera aparición del elemento especificado.
     * Si el elemento se encuentra al frente, utiliza la operación de desencolado.
     *
     * @param dato elemento que se desea eliminar
     * @return true si el elemento fue encontrado y eliminado, false en caso contrario
     */
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