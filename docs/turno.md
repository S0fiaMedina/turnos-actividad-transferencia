# Clase `Turno`

**Paquete:** `org.universidad.model`

## Descripción

`Turno` es la entidad principal del sistema. Representa una solicitud de asesoría realizada por un estudiante, con su código consecutivo, el motivo de la consulta, el operario que la atiende y el estado en que se encuentra dentro del flujo de atención.

Un mismo objeto `Turno` circula por las tres estructuras de datos del sistema: la lista enlazada de turnos activos, la cola de pendientes y la pila de atendidos. Por esta razón, la identidad del turno se define por su código y no por la posición que ocupe en cada estructura.

## Dependencias

| Clase / Enumeración | Paquete                    | Rol                                            |
|---------------------|----------------------------|------------------------------------------------|
| `EstadoTurno`       | `org.universidad.utilities` | Define los estados posibles del turno          |

## Atributos

| Nombre              | Tipo          | Modificador    | Descripción                                                                 |
|---------------------|---------------|----------------|-----------------------------------------------------------------------------|
| `codigo`            | `String`      | `private final`| Identificador único del turno, con formato `T0001`, `T0002`, etc.           |
| `nombreEstudiante`  | `String`      | `private final`| Nombre del estudiante que solicita la asesoría.                             |
| `motivo`            | `String`      | `private final`| Motivo de la asesoría.                                                      |
| `operario`          | `String`      | `private`      | Nombre del operario asignado. Su valor inicial es `"-"`.                    |
| `estado`            | `EstadoTurno` | `private`      | Estado actual del turno: `PENDIENTE`, `ASIGNADO` o `ATENDIDO`.              |

Los atributos `codigo`, `nombreEstudiante` y `motivo` son `final` porque describen la solicitud original y no deben cambiar una vez creada. Solo `operario` y `estado` varían durante el ciclo de atención.

## Constructores

### `Turno(String codigo, String nombreEstudiante, String motivo)`

Crea un turno nuevo. Al construirse:

- El `estado` se establece en `PENDIENTE`.
- El `operario` se establece en `"-"`, indicando que aún no tiene un funcionario asignado.

- **Parámetros:**
    - `codigo`: identificador generado por el repositorio.
    - `nombreEstudiante`: nombre del solicitante.
    - `motivo`: motivo de la asesoría.

## Métodos

### Getters

| Método                 | Retorna       | Descripción                                   |
|------------------------|---------------|-----------------------------------------------|
| `getCodigo()`          | `String`      | Código del turno.                             |
| `getNombreEstudiante()`| `String`      | Nombre del estudiante.                        |
| `getMotivo()`          | `String`      | Motivo de la asesoría.                        |
| `getOperario()`        | `String`      | Operario asignado, o `"-"` si no tiene.       |
| `getEstado()`          | `EstadoTurno` | Estado actual del turno.                      |

### Setters

### `setOperario(String operario)`

Registra el nombre del operario que atenderá el turno. Lo invoca `RepositorioTurnosMemoria.asignarSiguiente`.

### `setEstado(EstadoTurno estado)`

Actualiza el estado del turno. Lo utiliza el repositorio al asignar (`ASIGNADO`) y al finalizar la atención (`ATENDIDO`).

Los setters son los únicos puntos de modificación del objeto. No hay validación de transiciones: la clase acepta cualquier estado, de modo que el control de que un turno pase por `PENDIENTE → ASIGNADO → ATENDIDO` recae en la capa de datos.

### `equals(Object obj)`

Dos turnos son iguales si tienen el mismo `codigo`. Este criterio es el que usan `ListaEnlazada.eliminar` y `ColaDinamica.eliminar` para localizar elementos.

- **Retorna:** `true` si los objetos son la misma referencia o si `obj` es un `Turno` con el mismo código.

### `hashCode()`

Calcula el código hash a partir de `codigo`, en coherencia con `equals`. Dos turnos iguales producen el mismo hash, lo que garantiza un comportamiento correcto en colecciones basadas en hash.

