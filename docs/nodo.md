# Clase `Nodo<T>`

**Paquete:** `org.universidad.model.structures`

## Descripción

`Nodo` es la unidad básica sobre la que se construyen las estructuras dinámicas del sistema: `ListaEnlazada`, `ColaDinamica` y `PilaDinamica`. Cada nodo contiene un dato de tipo genérico `T` y una referencia al siguiente nodo de la estructura. Al enlazar varios nodos entre sí se forma la cadena que representa la lista, la cola o la pila.

La clase es genérica porque las estructuras que la utilizan deben poder almacenar cualquier tipo de objeto. En el proyecto, el tipo concreto que se guarda es `Turno`.

## Dependencias

La clase no depende de otras clases del proyecto. Es utilizada por:

| Clase              | Uso                                                        |
|--------------------|------------------------------------------------------------|
| `ListaEnlazada<T>` | Crea nodos al agregar elementos y recorre sus enlaces.     |
| `ColaDinamica<T>`  | Enlaza nodos al encolar y avanza el frente al desencolar.  |
| `PilaDinamica<T>`  | Enlaza cada nuevo nodo con la cima anterior.               |

## Atributos

| Nombre      | Tipo      | Modificador | Descripción                                                              |
|-------------|-----------|-------------|--------------------------------------------------------------------------|
| `dato`      | `T`       | (paquete)   | Elemento almacenado en el nodo.                                          |
| `siguiente` | `Nodo<T>` | (paquete)   | Referencia al siguiente nodo. Es `null` si este es el último de la estructura. |

Los atributos no tienen modificador de acceso `public` ni `private`, por lo que solo son visibles dentro del paquete `org.universidad.model.structures`. Esto permite que las estructuras manipulen los enlaces directamente sin exponer los nodos al resto del sistema.

## Constructores

### `Nodo(T dato)`

Crea un nodo que contiene el dato indicado. La referencia `siguiente` se inicializa en `null`, de modo que el nodo nace desconectado de cualquier otro.

- **Parámetro:** `dato`, el elemento que almacenará el nodo.

El constructor también tiene visibilidad de paquete, por lo que solo las estructuras del mismo paquete pueden crear nodos. Ninguna clase de otros paquetes, como `RepositorioTurnosMemoria`, puede instanciarlos directamente.

## Métodos

La clase no define métodos. Toda la lógica de enlace se encuentra en las estructuras que la usan.

