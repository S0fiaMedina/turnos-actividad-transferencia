# Clase `PilaDinamica<T>`

**Paquete:** `org.universidad.model.structures`

## Descripción

`PilaDinamica` es una estructura de datos genérica que implementa una **pila** con disciplina **LIFO** (*Last In, First Out*): el último elemento en ingresar es el primero en salir. En el sistema de turnos, esta estructura conserva el **historial de turnos atendidos**, de modo que la atención más reciente queda al tope y es la primera en consultarse.

La pila se construye con nodos enlazados dinámicamente (`Nodo<T>`) y mantiene una única referencia, la **cima**, que es el punto donde ocurren todas las inserciones y extracciones. Esto permite apilar y desapilar en tiempo constante, O(1).

## Dependencias

| Clase     | Paquete                            | Rol                                                                  |
|-----------|------------------------------------|----------------------------------------------------------------------|
| `Nodo<T>` | `org.universidad.model.structures` | Unidad básica que almacena el dato y la referencia al siguiente nodo |

Además utiliza `java.util.ArrayList` y `java.util.List`, que solo sirven para devolver una copia de los elementos en `toList()`.

## Atributos

| Nombre    | Tipo      | Modificador | Descripción                                                       |
|-----------|-----------|-------------|-------------------------------------------------------------------|
| `cima`    | `Nodo<T>` | `private`   | Nodo del tope de la pila. Es `null` cuando la pila está vacía.    |
| `tamanio` | `int`     | `private`   | Cantidad de elementos almacenados.                                |

## Constructores

La clase no declara constructores explícitos, por lo que Java proporciona el constructor por defecto. Al crearse, la pila está vacía (`cima` en `null`, `tamanio` en `0`).

## Métodos

### `apilar(T dato)`

Inserta un nuevo elemento en la cima de la pila.

- **Parámetro:** `dato`, el elemento que se desea insertar.
- **Procesamiento:** crea un nuevo nodo, lo enlaza con la cima anterior y lo convierte en la nueva cima.
- **Costo:** O(1).
- **Retorna:** `void`.

### `desapilar()`

Elimina y retorna el elemento que se encuentra en la cima.

- **Procesamiento:** toma el dato de `cima` y avanza la cima al nodo siguiente.
- **Costo:** O(1).
- **Retorna:** el dato eliminado, o `null` si la pila está vacía.

### `toList()`

Copia los elementos de la pila en un `java.util.List` nuevo, desde la cima hasta la base.

- **Procesamiento:** recorre los nodos desde `cima` y añade cada dato a un `ArrayList`.
- **Costo:** O(n).
- **Retorna:** `List<T>` con el elemento del tope en la primera posición. Como se trabaja con una copia, modificar el resultado no altera la pila.

En el sistema se utiliza para mostrar el historial en el orden LIFO, de modo que el último turno atendido aparece primero.

### `getTamanio()`

Devuelve la cantidad de elementos almacenados.

- **Retorna:** `int` con el número de elementos.

