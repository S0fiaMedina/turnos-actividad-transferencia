# Clase `TurnoRepositorioImpl`

**Paquete:** `org.universidad.repository`

## Descripción

`TurnoRepositorioImpl` es la implementación en memoria de la interfaz `TurnoRepositorio`. Es la clase que administra el almacenamiento de los turnos y coordina las tres estructuras de datos del sistema: la lista enlazada de turnos activos, la cola de turnos pendientes y la pila de turnos atendidos.

Su responsabilidad es mantener la consistencia entre estas estructuras. Un mismo objeto `Turno` se registra en la lista y en la cola al momento de crearse; luego, al asignarse, sale de la cola; y al finalizarse, sale de la lista y se apila en el historial. La clase no valida los datos de entrada, tarea que corresponde a `GestorTurnos`, sino que se limita a ejecutar las operaciones de almacenamiento y a verificar el estado de los turnos cuando la operación lo requiere.

## Dependencias

| Clase / Interfaz    | Paquete                           | Rol                                                       |
|---------------------|-----------------------------------|-----------------------------------------------------------|
| `TurnoRepositorio`  | `org.universidad.repository`      | Contrato que la clase implementa                          |
| `ListaEnlazada<T>`  | `org.universidad.model.structures`| Almacena los turnos activos                               |
| `ColaDinamica<T>`   | `org.universidad.model.structures`| Organiza los turnos pendientes en orden FIFO              |
| `PilaDinamica<T>`   | `org.universidad.model.structures`| Conserva el historial de turnos atendidos en orden LIFO   |
| `Turno`             | `org.universidad.model`           | Entidad que se almacena en las estructuras                |
| `EstadoTurno`       | `org.universidad.utilities`       | Estados del turno: `PENDIENTE`, `ASIGNADO`, `ATENDIDO`    |

## Atributos

| Nombre      | Tipo                    | Modificador    | Descripción                                                              |
|-------------|-------------------------|----------------|--------------------------------------------------------------------------|
| `activos`   | `ListaEnlazada<Turno>`  | `private final`| Turnos que aún no han sido atendidos. Alimenta la tabla de la interfaz.  |
| `pendientes`| `ColaDinamica<Turno>`   | `private final`| Turnos que esperan asignación, en orden de llegada.                      |
| `atendidos` | `PilaDinamica<Turno>`   | `private final`| Turnos finalizados, con el más reciente en la cima.                      |
| `contador`  | `int`                   | `private`      | Último número consecutivo utilizado para generar códigos.                |

Los tres atributos de estructura son `final` porque la instancia de cada estructura se crea una sola vez y se mantiene durante toda la vida del repositorio.

## Constructores

La clase no declara constructores explícitos. Al instanciarla, las tres estructuras se crean vacías y `contador` se inicia en `0`, de modo que el primer turno generado tendrá el código `T0001`.

## Métodos

### `agregar(String nombre, String motivo)`

Registra una nueva solicitud de turno.

- **Procesamiento:**
    1. Incrementa `contador`.
    2. Genera el código con formato `T%04d` (por ejemplo, `T0001`).
    3. Crea un `Turno` en estado `PENDIENTE`.
    4. Lo agrega al final de `activos` y lo encola en `pendientes`.
- **Costo:** O(n) por el recorrido de `agregarAlFinal` en la lista, más O(1) por el encolado.
- **Retorna:** el `Turno` creado.

### `asignarSiguiente(String operario)`

Asigna el primer turno pendiente al operario indicado.

- **Procesamiento:** desencola el primer turno de `pendientes`, registra el operario y cambia su estado a `ASIGNADO`. El turno permanece en `activos` hasta que sea atendido.
- **Costo:** O(1).
- **Retorna:** el `Turno` asignado, o `null` si no hay turnos pendientes.

### `finalizar(String codigo)`

Finaliza la atención de un turno asignado.

- **Procesamiento:**
    1. Busca en `activos` el turno cuyo código coincida, sin distinguir mayúsculas y minúsculas.
    2. Si no existe, lanza `Exception` con el mensaje "no existe".
    3. Si su estado no es `ASIGNADO`, lanza `Exception` con el mensaje "no ha sido asignado".
    4. Cambia el estado a `ATENDIDO`, lo elimina de `activos` y lo apila en `atendidos`.
- **Costo:** O(n) por la búsqueda y la eliminación en la lista.
- **Retorna:** el `Turno` finalizado.
- **Excepciones:** `Exception` genérica en ambos casos de error.

### `listarActivos()`

Retorna una copia de los turnos activos en orden de registro.

- **Retorna:** `List<Turno>`, producto de `activos.toList()`.

### `listarHistorial()`

Retorna una copia del historial, con el turno más reciente primero.

- **Retorna:** `List<Turno>`, producto de `atendidos.toList()`.

### `getPendientes()`

Devuelve la cantidad de turnos que esperan asignación.

- **Retorna:** `int`.

### `getAtendidos()`

Devuelve la cantidad de turnos atendidos.

- **Retorna:** `int`.

## Flujo de vida de un turno

| Operación          | Lista `activos` | Cola `pendientes` | Pila `atendidos` | Estado   |
|--------------------|-----------------|-------------------|------------------|----------|
| `agregar`          | Se agrega       | Se encola         | —                | PENDIENTE|
| `asignarSiguiente` | Permanece       | Se desencola      | —                | ASIGNADO |
| `finalizar`        | Se elimina      | —                 | Se apila         | ATENDIDO |

