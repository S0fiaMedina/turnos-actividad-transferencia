package org.universidad.logic;

import org.universidad.model.Turno;
import org.universidad.repository.TurnoRepositorio;

import java.util.List;

public class GestorTurnos {
    private final TurnoRepositorio repositorio;

    public GestorTurnos(TurnoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Turno solicitarTurno(String nombre, String motivo) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del estudiante es obligatorio.");
        }
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("El motivo de la asesoría es obligatorio.");
        }
        return repositorio.agregar(nombre.trim(), motivo.trim());
    }

    public Turno asignarOperario(String operario) {
        if (operario == null || operario.isBlank()) {
            throw new IllegalArgumentException("El nombre del operario es obligatorio.");
        }
        return repositorio.asignarSiguiente(operario.trim());
    }

    public Turno finalizarAtencion(String codigo) throws Exception {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Debe ingresar el código del turno.");
        }
        return repositorio.finalizar(codigo.trim());
    }

    public List<Turno> listarActivos() {
        return repositorio.listarActivos();
    }

    public List<Turno> listarHistorial() {
        return repositorio.listarHistorial();
    }

    public int getPendientes() {
        return repositorio.getPendientes();
    }

    public int getAtendidos() {
        return repositorio.getAtendidos();
    }
}
