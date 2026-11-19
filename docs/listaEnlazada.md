# Clase `ListaEnlazada<T>`

**Paquete:** `org.universidad.model.structures`

## Descripción

`ListaEnlazada` es una estructura de datos genérica que implementa una **lista enlazada simple**. Cada elemento se almacena en un `Nodo<T>` que conoce únicamente al siguiente, de modo que el recorrido es unidireccional y siempre parte de la cabeza. En el sistema de turnos, esta estructura almacena los **turnos activos**, es decir, aquellos que todavía no han sido atendidos, y alimenta la tabla de la interfaz gráfica.

La clase mantiene una referencia a la **cabeza** de la lista y un contador de elementos. No dispone de referencia al último nodo, por lo que agregar un elemento requiere recorrer la lista completa.

## Dependencias

| Clase     | Paquete                            | Rol                                                                  |
|-----------|------------------------------------|----------------------------------------------------------------------|
| `Nodo<T>` | `org.universidad.model.structures` | Unidad básica que almacena el dato y la referencia al siguiente nodo |

Además utiliza `java.util.ArrayList`, `java.util.List` y `java.util.function.Predicate`. Estas clases de Java no implementan la estructura; sirven únicamente para devolver resultados y para expresar criterios de búsqueda.

## Atributos

| Nombre     | Tipo       | Modificador | Descripción                                                    |
|------------|------------|-------------|----------------------------------------------------------------|
| `cabeza`   | `Nodo<T>`  | `private`   | Primer nodo de la lista. Es `null` cuando la lista está vacía. |
| `tamanio`  | `int`      | `private`   | Cantidad de elementos almacenados.                             |

## Constructores

La clase no declara constructores explícitos, por lo que Java proporciona el constructor por defecto. Al crearse, la lista está vacía (`cabeza` en `null`, `tamanio` en `0`).

## Métodos

### `agregarAlFinal(T dato)`

Agrega un nuevo elemento al final de la lista.

- **Parámetro:** `dato`, el elemento que se desea insertar.
- **Procesamiento:** crea un nuevo nodo. Si la lista está vacía, el nodo pasa a ser la cabeza. En caso contrario, recorre la lista desde la cabeza hasta el último nodo y lo enlaza con el nuevo.
- **Costo:** O(n), porque requiere recorrer toda la lista.
- **Retorna:** `void`.

### `eliminar(T dato)`

Busca y elimina la primera aparición del elemento indicado.

- **Parámetro:** `dato`, el elemento que se desea eliminar.
- **Procesamiento:**
    1. Si la lista está vacía, retorna `false`.
    2. Si el elemento está en la cabeza, la cabeza avanza al siguiente nodo.
    3. En otro caso, recorre la lista manteniendo una referencia al nodo anterior al buscado, y lo desvincula.
- **Costo:** O(n) en el peor caso, debido a la búsqueda previa.
- **Retorna:** `true` si se eliminó, `false` si no se encontró.

### `buscar(Predicate<T> condicion)`

Recorre la lista y retorna el primer elemento que cumple la condición indicada.

- **Parámetro:** `condicion`, una expresión lambda que evalúa cada elemento.
- **Procesamiento:** recorre los nodos desde la cabeza y aplica `condicion.test` a cada dato hasta encontrar una coincidencia.
- **Costo:** O(n) en el peor caso.
- **Retorna:** el primer elemento que cumple la condición, o `null` si ninguno la cumple.

En el sistema se utiliza para localizar un turno por su código dentro de `RepositorioTurnosMemoria.finalizar`.

### `getTamanio()`

Devuelve la cantidad de elementos almacenados.

- **Retorna:** `int` con el número de elementos.

### `toList()`

Copia los elementos de la lista en un `java.util.List` nuevo, conservando el orden original.

- **Procesamiento:** recorre la lista y añade cada dato a un `ArrayList`.
- **Costo:** O(n).
- **Retorna:** `List<T>`. Como se trabaja con una copia, modificar el resultado no altera la lista enlazada.

En el sistema se utiliza para entregar los turnos activos a la interfaz sin exponer los nodos.



