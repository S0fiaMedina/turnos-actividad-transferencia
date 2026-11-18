package org.universidad.repository;

import org.universidad.model.Turno;

import java.util.List;

public interface TurnoRepositorio {

    /**
     * Agrega un nuevo turno a la lista de turnos activos y a la cola de turnos pendientes.
     *
     * @param nombre nombre de la persona que solicita el turno
     * @param motivo motivo de la solicitud del turno
     * @return turno creado y registrado en el sistema
     */
    Turno agregar(String nombre, String motivo);

    /**
     * Asigna el siguiente turno pendiente al operario especificado.
     *
     * @param operario nombre o identificador del operario que atenderá el turno
     * @return turno asignado al operario o null si no hay turnos pendientes
     */
    Turno asignarSiguiente(String operario);


    /**
     * Finaliza la atención del turno identificado por su código.
     *
     * @param codigo código del turno que se desea finalizar
     * @return turno cuya atención ha sido finalizada
     * @throws Exception si ocurre un error durante la finalización del turno
     */
    Turno finalizar(String codigo) throws Exception;

    /**
     * Obtiene la lista de turnos que se encuentran activos.
     *
     * @return lista de turnos activos
     */
    List<Turno> listarActivos();

    /**
     * Obtiene la lista de turnos registrados en el historial de atención.
     *
     * @return lista de turnos del historial
     */
    List<Turno> listarHistorial();

    /**
     * Consulta la cantidad de turnos que se encuentran pendientes de atención.
     *
     * @return número de turnos pendientes
     */
    int getPendientes();

    /**
     * Consulta la cantidad de turnos que han sido atendidos.
     *
     * @return número de turnos atendidos
     */
    int getAtendidos();
}
