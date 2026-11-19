# Clase `VentanaTurnos`

**Paquete:** `org.universidad.view`

## Descripción

`VentanaTurnos` es la ventana principal del sistema. Extiende `JFrame` y constituye la capa de presentación: muestra el encabezado con los indicadores de turnos pendientes y atendidos, la tabla de turnos activos y el menú de operaciones. Recibe las acciones del usuario, las delega a `GestorTurnos` y actualiza la vista con los resultados.

La clase no contiene reglas de negocio ni accede directamente a las estructuras de datos. Toda la validación y el almacenamiento se realizan en las capas inferiores, de modo que la ventana se limita a solicitar datos, mostrar mensajes y reflejar el estado del sistema.

## Dependencias

| Clase                | Paquete                       | Rol                                                          |
|----------------------|-------------------------------|--------------------------------------------------------------|
| `GestorTurnos`       | `org.universidad.logic`       | Ejecuta las operaciones de negocio solicitadas desde la ventana |
| `Turno`              | `org.universidad.model`       | Entidad que se muestra en la tabla y en el historial         |
| `ViewUtilities`      | `org.universidad.utilities`   | Colores compartidos y utilidades para crear componentes y mensajes |

Además utiliza componentes de `javax.swing`, `java.awt` y `java.util.List`.

## Atributos

| Nombre           | Tipo                 | Modificador    | Descripción                                                        |
|------------------|----------------------|----------------|--------------------------------------------------------------------|
| `gestor`         | `GestorTurnos`       | `private final`| Gestor al que se delegan las operaciones de los turnos.            |
| `modeloTabla`    | `DefaultTableModel`  | `private final`| Modelo que almacena las filas mostradas en la tabla de activos.    |
| `lblPendientes`  | `JLabel`             | `private final`| Indicador con la cantidad de turnos pendientes.                    |
| `lblAtendidos`   | `JLabel`             | `private final`| Indicador con la cantidad de turnos atendidos.                     |

## Constructores

### `VentanaTurnos(GestorTurnos gestor)`

Construye la ventana y la configura en cuatro partes:

1. **Propiedades generales:** título, tamaño de 850 × 550, centrado en pantalla, cierre de la aplicación al cerrar la ventana y fondo blanco.
2. **Encabezado (`NORTH`):** dos indicadores, uno para turnos pendientes y otro para atendidos, sobre fondo naranja.
3. **Tabla (`CENTER`):** un `JTable` con las columnas Código, Estudiante, Motivo, Operario y Estado. Las celdas no son editables.
4. **Menú (`EAST`):** seis botones, cada uno asociado a una opción mediante una expresión lambda que llama a `ejecutarOpcion`.

Al final se invoca `actualizarVista()` para mostrar el estado inicial.

- **Parámetro:** `gestor`, la instancia de `GestorTurnos` que administra la lógica del sistema.

## Métodos

### `ejecutarOpcion(int opcion)`

Ejecuta la acción asociada a la opción elegida en el menú. Utiliza una sentencia `switch` con la sintaxis de flechas de Java 14 o superior.

| Opción | Acción                                    |
|--------|-------------------------------------------|
| 1      | `solicitarTurno()`                        |
| 2      | `asignarOperario()`                       |
| 3      | `atenderTurno()`                          |
| 4      | `actualizarVista()`                       |
| 5      | `verHistorial()`                          |
| 6      | Confirma y, si se acepta, cierra el programa con `System.exit(0)` |

Las excepciones se capturan en este método y se muestran al usuario:

- `IllegalArgumentException` se presenta como advertencia, porque corresponde a datos de entrada no válidos.
- Cualquier otra `Exception` se presenta como error.

- **Parámetro:** `opcion`, número de la opción seleccionada.

### `solicitarTurno()`

Pide el nombre del estudiante y el motivo de la asesoría mediante cuadros de diálogo. Si el usuario cancela alguno de los dos, el método termina sin registrar nada. Si ambos se capturan, llama a `gestor.solicitarTurno`, actualiza la vista y muestra el código generado.

### `asignarOperario()`

Pide el nombre del operario y solicita al gestor asignar el siguiente turno pendiente. Si el gestor devuelve `null`, muestra una advertencia indicando que no hay turnos en espera. En caso contrario, actualiza la vista y confirma la asignación.

### `atenderTurno()`

Pide el código del turno y lo envía a `gestor.finalizarAtencion`. Si la operación se completa, actualiza la vista y confirma que el turno pasó al historial. Declara `throws Exception` porque `finalizarAtencion` puede lanzar excepciones; estas son capturadas por `ejecutarOpcion`.

### `verHistorial()`

Obtiene el historial desde el gestor y lo muestra en un `JTextArea` dentro de un `JScrollPane`, con una línea por turno: código, estudiante, motivo y operario. Si no hay turnos atendidos, muestra un mensaje indicándolo. El orden es el que entrega la pila, del más reciente al más antiguo.

### `actualizarVista()`

Recarga la tabla y los indicadores:

1. Vacía el `modeloTabla` con `setRowCount(0)`.
2. Agrega una fila por cada turno activo.
3. Actualiza los textos de `lblPendientes` y `lblAtendidos`.

Es el único método que sincroniza la interfaz con el estado del sistema, por lo que se invoca después de cada operación que lo modifique.

## Flujo de interacción típico

1. El usuario pulsa un botón del menú.
2. `ejecutarOpcion` determina la acción según la opción.
3. El método correspondiente solicita datos mediante diálogos y los envía al gestor.
4. El gestor responde con un turno, `null` o una excepción.
5. La ventana actualiza la vista o muestra el mensaje correspondiente.

