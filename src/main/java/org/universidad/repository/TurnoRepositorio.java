package org.universidad.repository;

import org.universidad.model.Turno;

import java.util.List;

public interface TurnoRepositorio {
    Turno agregar(String nombre, String motivo);

    Turno asignarSiguiente(String operario);

    Turno finalizar(String codigo) throws Exception;

    List<Turno> listarActivos();

    List<Turno> listarHistorial();

    int getPendientes();

    int getAtendidos();
}
