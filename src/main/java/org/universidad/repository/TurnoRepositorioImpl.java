package org.universidad.repository;

import org.universidad.utilities.EstadoTurno;

import org.universidad.model.estructuras.ColaDinamica;
import org.universidad.model.estructuras.ListaEnlazada;
import org.universidad.model.estructuras.PilaDinamica;
import org.universidad.model.Turno;

import java.util.List;

public class TurnoRepositorioImpl implements  TurnoRepositorio{
    private final ListaEnlazada<Turno> activos = new ListaEnlazada<>();
    private final ColaDinamica<Turno> pendientes = new ColaDinamica<>();
    private final PilaDinamica<Turno> atendidos = new PilaDinamica<>();
    private int contador = 0;

    @Override
    public Turno agregar(String nombre, String motivo) {
        contador++;
        String codigo = String.format("T%04d", contador);
        Turno turno = new Turno(codigo, nombre, motivo);
        activos.agregarAlFinal(turno);
        pendientes.encolar(turno);
        return turno;
    }

    @Override
    public Turno asignarSiguiente(String operario) {
        Turno turno = pendientes.desencolar();
        if (turno == null) return null;
        turno.setOperario(operario);
        turno.setEstado(EstadoTurno.ASIGNADO);
        return turno;
    }

    @Override
    public Turno finalizar(String codigo) throws Exception {
        Turno turno = activos.buscar(t -> t.getCodigo().equalsIgnoreCase(codigo));
        if (turno == null) {
            throw new Exception("El turno " + codigo + " no existe.");
        }
        if (turno.getEstado() != EstadoTurno.ASIGNADO) {
            throw new Exception("El turno " + codigo + " no ha sido asignado a un operario.");
        }
        turno.setEstado(EstadoTurno.ATENDIDO);
        activos.eliminar(turno);
        atendidos.apilar(turno);
        return turno;
    }

    @Override
    public List<Turno> listarActivos() {
        return activos.toList();
    }

    @Override
    public List<Turno> listarHistorial() {
        return atendidos.toList();
    }

    @Override
    public int getPendientes() {
        return pendientes.getTamanio();
    }

    @Override
    public int getAtendidos() {
        return atendidos.getTamanio();
    }
}
