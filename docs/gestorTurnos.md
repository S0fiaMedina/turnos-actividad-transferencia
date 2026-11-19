# Clase `GestorTurnos`

**Paquete:** `org.universidad.logic`

## Descripción

`GestorTurnos` es la clase encargada de la **lógica de negocio** del sistema de turnos. Funciona como un controlador intermedio: recibe las acciones provenientes de la interfaz gráfica, valida los datos de entrada y delega las operaciones al repositorio de turnos (`TurnoRepositorio`), que es quien administra el almacenamiento y las estructuras de datos.

De este modo, la interfaz no necesita conocer cómo se almacenan los turnos, y el repositorio no se encarga de validar la información del usuario. Cada capa cumple una responsabilidad concreta.

## Dependencias

| Clase / Interfaz    | Paquete                       | Rol                                         |
|---------------------|-------------------------------|---------------------------------------------|
| `Turno`             | `org.universidad.model`       | Entidad que representa una solicitud        |
| `TurnoRepositorio`  | `org.universidad.repository`  | Acceso a los datos y estructuras del sistema |

## Atributos

| Nombre        | Tipo               | Modificador | Descripción                                      |
|---------------|--------------------|-------------|--------------------------------------------------|
| `repositorio` | `TurnoRepositorio` | `private final` | Repositorio al que se delegan las operaciones. |

## Constructores

### `GestorTurnos(TurnoRepositorio repositorio)`

Recibe el repositorio con el que trabajará el gestor. Al recibirlo por parámetro, la clase no depende de una implementación concreta, lo que facilita cambiar el almacenamiento (por ejemplo, de memoria a archivo) sin modificar la lógica.

## Métodos

### `solicitarTurno(String nombre, String motivo)`

Registra una nueva solicitud de asesoría.

- **Validaciones:** el nombre y el motivo no pueden ser nulos ni estar vacíos o compuestos solo por espacios. En caso contrario se lanza `IllegalArgumentException`.
- **Procesamiento:** elimina los espacios al inicio y al final de ambos valores antes de delegar en `repositorio.agregar`.
- **Retorna:** el `Turno` creado, con su código consecutivo asignado.

### `asignarOperario(String operario)`

Asigna el siguiente turno pendiente al operario indicado.

- **Validaciones:** el nombre del operario es obligatorio; de lo contrario se lanza `IllegalArgumentException`.
- **Procesamiento:** delega en `repositorio.asignarSiguiente`, que toma el primer turno de la cola (FIFO).
- **Retorna:** el `Turno` asignado, o `null` si no hay turnos pendientes.

### `finalizarAtencion(String codigo)`

Finaliza la atención de un turno previamente asignado.

- **Validaciones:** el código no puede ser nulo ni vacío.
- **Procesamiento:** elimina los espacios del código y delega en `repositorio.finalizar`.
- **Retorna:** el `Turno` finalizado.
- **Excepciones:** `IllegalArgumentException` si el código está vacío; `Exception` si el turno no existe o no ha sido asignado.

### `listarActivos()`

Retorna la lista de turnos activos, es decir, aquellos que aún no han sido atendidos. Se utiliza para poblar la tabla de la interfaz.

- **Retorna:** `List<Turno>`.

### `listarHistorial()`

Retorna la lista de turnos atendidos, en el orden en que la pila los entrega (del más reciente al más antiguo).

- **Retorna:** `List<Turno>`.

### `getPendientes()`

Indica cuántos turnos esperan asignación.

- **Retorna:** `int` con la cantidad de turnos pendientes.

### `getAtendidos()`

Indica cuántos turnos han sido atendidos.

- **Retorna:** `int` con la cantidad de turnos atendidos.


