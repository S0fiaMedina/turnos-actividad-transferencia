package org.universidad.model.structures;

/** Nodo genérico reutilizado por las estructuras dinámicas. */
public class Nodo<T> {

    /** Dato que almacena el nodo. (Se usa T porque puede representar cualquier cosa */
    T dato;

    /** Referencia al siguiente nodo. */
    Nodo<T> siguiente;

    /** Constructor que inicializa el nodo con un dato y establece el siguiente nodo como null. */
    Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}