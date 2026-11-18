package org.universidad.model;

import org.universidad.utilities.EstadoTurno;

public class Turno {

    private final String codigo;
    private final String nombreEstudiante;
    private final String motivo;
    private String operario;
    private EstadoTurno estado;

    public Turno(String codigo, String nombreEstudiante, String motivo) {
        this.codigo = codigo;
        this.nombreEstudiante = nombreEstudiante;
        this.motivo = motivo;
        this.estado = EstadoTurno.PENDIENTE;
        this.operario = "-";
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getOperario() {
        return operario;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setOperario(String operario) {
        this.operario = operario;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Turno)) return false;
        return codigo.equals(((Turno) obj).codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
