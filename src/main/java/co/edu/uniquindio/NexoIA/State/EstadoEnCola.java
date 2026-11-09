package co.edu.uniquindio.NexoIA.State;

import co.edu.uniquindio.NexoIA.Model.Tarea;

public class EstadoEnCola implements EstadoTarea {

    @Override
    public void avanzar(Tarea tarea) {
        tarea.setEstado(new EstadoPlanificando());
    }

    @Override
    public void fallar(Tarea tarea, String motivo) {
        System.out.println("   [STATE] La tarea fallo en cola: " + motivo);
        tarea.setEstado(new EstadoFallida());
    }

    @Override
    public void cancelar(Tarea tarea) {
        tarea.setEstado(new EstadoCancelada());
    }

    @Override
    public void reintentar(Tarea tarea) {
        System.out.println("   [STATE] La tarea ya esta En cola, no hace falta reintentar.");
    }

    @Override
    public void pausar(Tarea tarea) {
        tarea.setEstado(new EstadoPausada());
    }

    @Override
    public String getNombre() {
        return "En cola";
    }
}
