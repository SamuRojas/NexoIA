package co.edu.uniquindio.NexoIA.State;

import co.edu.uniquindio.NexoIA.Model.Tarea;

public class EstadoPlanificando implements EstadoTarea {

    @Override
    public void avanzar(Tarea tarea) {
        tarea.setEstado(new EstadoEjecutando());
    }

    @Override
    public void fallar(Tarea tarea, String motivo) {
        System.out.println("   [STATE] La tarea fallo al planificar: " + motivo);
        tarea.setEstado(new EstadoFallida());
    }

    @Override
    public void cancelar(Tarea tarea) {
        tarea.setEstado(new EstadoCancelada());
    }

    @Override
    public void reintentar(Tarea tarea) {
        tarea.setEstado(new EstadoEnCola());
    }

    @Override
    public void pausar(Tarea tarea) {
        tarea.setEstado(new EstadoPausada());
    }

    @Override
    public String getNombre() {
        return "Planificando";
    }
}
