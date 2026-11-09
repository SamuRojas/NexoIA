package co.edu.uniquindio.NexoIA.State;

import co.edu.uniquindio.NexoIA.Model.Tarea;

public class EstadoFallida implements EstadoTarea {

    @Override
    public void avanzar(Tarea tarea) {
        System.out.println("   [STATE] Una tarea Fallida debe reintentarse antes de avanzar.");
    }

    @Override
    public void fallar(Tarea tarea, String motivo) {
        System.out.println("   [STATE] La tarea ya esta Fallida: " + motivo);
    }

    @Override
    public void cancelar(Tarea tarea) {
        tarea.setEstado(new EstadoCancelada());
    }

    @Override
    public void reintentar(Tarea tarea) {
        System.out.println("   [STATE] Reintentando tarea Fallida, vuelve a la cola.");
        tarea.setEstado(new EstadoEnCola());
    }

    @Override
    public void pausar(Tarea tarea) {
        System.out.println("   [STATE] Una tarea Fallida no se puede pausar.");
    }

    @Override
    public String getNombre() {
        return "Fallida";
    }
}
