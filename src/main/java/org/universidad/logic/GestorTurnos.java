package org.universidad.logic;

import org.universidad.model.Turno;
import org.universidad.repository.TurnoRepositorio;

import java.util.List;


/**
 * Clase que gestiona la lógica de turnos.
 * Mas o menos como un "controlador" que interactúa con la interfaz gráfica y el repositorio de turnos.
 */
public class GestorTurnos {
    private final TurnoRepositorio repositorio;

    public GestorTurnos(TurnoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * Solicita un turno para un estudiante.
     *
     * @param nombre  Nombre del estudiante.
     * @param motivo  Motivo de la solicitud.
     * @return Turno generado.
     */
    public Turno solicitarTurno(String nombre, String motivo) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del estudiante es obligatorio.");
        }
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("El motivo de la asesoría es obligatorio.");
        }
        return repositorio.agregar(nombre.trim(), motivo.trim());
    }

    /**
     * Asigna el siguiente turno pendiente a un operario.
     *
     * @param operario Nombre del operario.
     * @return Turno asignado, o null si no hay turnos pendientes.
     */
    public Turno asignarOperario(String operario) {
        if (operario == null || operario.isBlank()) {
            throw new IllegalArgumentException("El nombre del operario es obligatorio.");
        }
        return repositorio.asignarSiguiente(operario.trim());
    }

    /**
     * Finaliza la atención de un turno.
     *
     * @param codigo Código del turno a finalizar.
     * @return Turno finalizado.
     * @throws Exception Si el turno no existe o no ha sido asignado.
     */
    public Turno finalizarAtencion(String codigo) throws Exception {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Debe ingresar el código del turno.");
        }
        return repositorio.finalizar(codigo.trim());
    }


    /**
     * Lista los turnos activos.
     *
     * @return Lista de turnos activos.
     */
    public List<Turno> listarActivos() {
        return repositorio.listarActivos();
    }


    /**
     * Lista los turnos atendidos (historial).
     *
     * @return Lista de turnos atendidos.
     */
    public List<Turno> listarHistorial() {
        return repositorio.listarHistorial();
    }

    /**
     * Lista los turnos pendientes de asignación.
     * @return Lista de turnos pendientes.
     */
    public int getPendientes() {
        return repositorio.getPendientes();
    }

    /**
     * Lista los turnos atendidos.
     * @return Lista de turnos atendidos.
     */
    public int getAtendidos() {
        return repositorio.getAtendidos();
    }
}
