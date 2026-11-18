package org.universidad.model.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/** Lista enlazada simple: almacena los turnos activos. */
public class ListaEnlazada<T> {
    private Nodo<T> cabeza;
    private int tamanio;


    /**
     * Agrega un nuevo elemento al final de la lista enlazada.
     * @param dato elemento que se desea agregar
     */
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

    /**
     * Busca y elimina la primera aparición del elemento especificado.
     *
     * @param dato elemento que se desea eliminar
     * @return true si el elemento fue encontrado y eliminado, false en caso contrario
     */
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

    /**
     * Busca y retorna el primer elemento que cumple con la condición especificada.
     *
     * @param condicion criterio utilizado para evaluar los elementos de la lista
     * @return primer elemento que cumple la condición o null si no se encuentra
     */
    public T buscar(Predicate<T> condicion) {
        Nodo<T> aux = cabeza;
        while (aux != null) {
            if (condicion.test(aux.dato)) return aux.dato;
            aux = aux.siguiente;
        }
        return null;
    }


    /**
     * Obtiene la cantidad de elementos almacenados en la lista.
     *
     * @return número de elementos de la lista
     */
    public int getTamanio() {
        return tamanio;
    }


    /**
     * Convierte los elementos de la lista enlazada en una lista de tipo List.
     *
     * @return lista con los elementos almacenados, conservando su orden
     */
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
