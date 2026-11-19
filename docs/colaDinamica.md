# Clase `ColaDinamica<T>`

**Paquete:** `org.universidad.model.structures`

## Descripción

`ColaDinamica` es una estructura de datos genérica que implementa una **cola** con disciplina **FIFO** (*First In, First Out*): el primer elemento en ingresar es el primero en salir. En el sistema de turnos, esta estructura organiza los **turnos pendientes** de asignación, de modo que el primer estudiante que solicita una asesoría es el primero en ser atendido.

La cola se construye con nodos enlazados dinámicamente (`Nodo<T>`) y mantiene referencias al **frente** y al **fin**, lo que permite encolar y desencolar en tiempo constante, O(1).

## Dependencias

| Clase    | Paquete                        | Rol                                      |
|----------|--------------------------------|------------------------------------------|
| `Nodo<T>`| `org.universidad.model.structures` | Unidad básica que almacena el dato y la referencia al siguiente nodo |

## Atributos

| Nombre    | Tipo       | Modificador | Descripción                                                  |
|-----------|------------|-------------|--------------------------------------------------------------|
| `frente`  | `Nodo<T>`  | `private`   | Primer nodo de la cola; es el próximo elemento en salir.     |
| `fin`     | `Nodo<T>`  | `private`   | Último nodo de la cola; es el punto de inserción.            |
| `tamanio` | `int`      | `private`   | Cantidad de elementos almacenados.                           |

## Constructores

La clase no declara constructores explícitos, por lo que Java proporciona el constructor por defecto sin parámetros. Al crearse, la cola está vacía (`frente` y `fin` en `null`, `tamanio` en `0`).

## Métodos

### `encolar(T dato)`

Agrega un elemento al final de la cola.

- **Parámetro:** `dato`, el elemento que se desea insertar.
- **Procesamiento:** crea un nuevo nodo. Si la cola está vacía, el nodo es a la vez `frente` y `fin`. En caso contrario, se enlaza al nodo `fin` actual y se actualiza la referencia `fin`.
- **Costo:** O(1).
- **Retorna:** `void`.

### `desencolar()`

Elimina y retorna el elemento del frente de la cola.

- **Procesamiento:** toma el dato de `frente`, avanza `frente` al siguiente nodo y, si la cola queda vacía, anula también `fin`.
- **Costo:** O(1).
- **Retorna:** el dato eliminado, o `null` si la cola está vacía.

### `estaVacia()`

Indica si la cola no contiene elementos.

- **Retorna:** `true` si `frente` es `null`; `false` en caso contrario.

### `getTamanio()`

Devuelve la cantidad de elementos almacenados.

- **Retorna:** `int` con el número de elementos.

### `eliminar(T dato)`

Busca la primera aparición del elemento indicado y lo retira de la cola, sin alterar el orden de los demás.

- **Parámetro:** `dato`, el elemento que se desea eliminar.
- **Procesamiento:**
    1. Si la cola está vacía, retorna `false`.
    2. Si el elemento está al frente, delega en `desencolar()`.
    3. En otro caso, recorre la cola manteniendo una referencia al nodo anterior al buscado. Al encontrarlo, reconecta el enlace para omitirlo. Si el nodo eliminado era el último, actualiza `fin`.
- **Costo:** O(n) en el peor caso, porque requiere localizar el elemento.
- **Retorna:** `true` si se eliminó, `false` si no se encontró.

## Flujo de uso en el sistema

1. `RepositorioTurnosMemoria.agregar` llama a `encolar` para registrar cada nuevo turno.
2. `RepositorioTurnosMemoria.asignarSiguiente` llama a `desencolar`, de modo que el turno asignado sale de los pendientes.
3. `getTamanio` alimenta el indicador de turnos pendientes de la interfaz.


