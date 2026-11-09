package co.edu.uniquindio.NexoIA.State;

import co.edu.uniquindio.NexoIA.Model.Tarea;

public class EstadoPausada implements EstadoTarea {

    @Override
    public void avanzar(Tarea tarea) {
        System.out.println("   [STATE] Reanudando tarea Pausada, vuelve a la cola.");
        tarea.setEstado(new EstadoEnCola());
    }

    @Override
    public void fallar(Tarea tarea, String motivo) {
        System.out.println("   [STATE] La tarea pausada fallo: " + motivo);
        tarea.setEstado(new EstadoFallida());
    }

    @Override
    public void cancelar(Tarea tarea) {
        tarea.setEstado(new EstadoCancelada());
    }

    @Override
    public void reintentar(Tarea tarea) {
        System.out.println("   [STATE] Reanudando tarea Pausada, vuelve a la cola.");
        tarea.setEstado(new EstadoEnCola());
    }

    @Override
    public void pausar(Tarea tarea) {
        System.out.println("   [STATE] La tarea ya esta Pausada.");
    }

    @Override
    public String getNombre() {
        return "Pausada";
    }
}
