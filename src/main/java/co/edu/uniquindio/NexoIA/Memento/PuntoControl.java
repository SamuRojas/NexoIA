package co.edu.uniquindio.NexoIA.Memento;

import co.edu.uniquindio.NexoIA.State.EstadoTarea;

import java.util.ArrayList;
import java.util.List;

public class PuntoControl {
    private final String etapa;
    private final EstadoTarea estado;
    private final int progreso;
    private final List<String> resultadosParciales;

    public PuntoControl(String etapa, EstadoTarea estado, int progreso, List<String> resultadosParciales) {
        this.etapa = etapa;
        this.estado = estado;
        this.progreso = progreso;
        this.resultadosParciales = new ArrayList<>(resultadosParciales);
    }

    public String getEtapa() {
        return etapa;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public int getProgreso() {
        return progreso;
    }

    public List<String> getResultadosParciales() {
        return resultadosParciales;
    }
}
