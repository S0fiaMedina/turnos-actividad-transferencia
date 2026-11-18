# Sistema de Gestión de Turnos – Universidad Compensar

Aplicación de escritorio en Java con Swing para registrar, asignar y atender turnos de asesoría estudiantil. Los datos se mantienen en memoria mientras la aplicación esté abierta.

## Requisitos

- Java 25 (JDK)

Puedes verificar tu versión con:

```bash
java -version
```

## Estructura del proyecto

```
├───src
│   ├───main
│   │   └───java
│   │       └───org
│   │           └───universidad
│   │               ├───logic
│   │               ├───model
│   │               │   └───estructuras
│   │               ├───repository
│   │               ├───utilities
│   │               └───view
```


## Uso

1. **Solicitar turno:** registra el nombre del estudiante y el motivo. Se genera un código consecutivo (T0001, T0002, …).
2. **Asignar operario:** asigna el primer turno pendiente (FIFO) al operario indicado.
3. **Atender turno:** finaliza un turno asignado ingresando su código. El turno pasa al historial.
4. **Actualizar listado:** refresca la tabla y los indicadores.
5. **Ver historial:** muestra los turnos atendidos, del último al primero (LIFO).
6. **Salir:** cierra la aplicación.

## Estructuras de datos

| Estructura       | Uso                                  |
|------------------|--------------------------------------|
| Lista enlazada   | Turnos actiwinget search Mavenvos                       |
| Cola dinámica    | Turnos pendientes de asignación (FIFO) |
| Pila dinámica    | Historial de turnos atendidos (LIFO) |

Las tres estructuras están implementadas con nodos enlazados propios, sin usar colecciones predefinidas de Java.

## Notas

- Los datos no se guardan en disco; al cerrar la aplicación se pierden.
- Requiere un entorno con interfaz gráfica para ejecutarse.