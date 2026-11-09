package co.edu.uniquindio.NexoIA.State;

import co.edu.uniquindio.NexoIA.Model.Tarea;

public class EstadoCompletada implements EstadoTarea {

    @Override
    public void avanzar(Tarea tarea) {
        System.out.println("   [STATE] La tarea ya esta Completada, no puede avanzar.");
    }

    @Override
    public void fallar(Tarea tarea, String motivo) {
        System.out.println("   [STATE] Una tarea Completada no puede fallar.");
    }

    @Override
    public void cancelar(Tarea tarea) {
        System.out.println("   [STATE] No se puede cancelar una tarea Completada.");
    }

    @Override
    public void reintentar(Tarea tarea) {
        System.out.println("   [STATE] Una tarea Completada no se puede reintentar.");
    }

    @Override
    public void pausar(Tarea tarea) {
        System.out.println("   [STATE] No se puede pausar una tarea Completada.");
    }

    @Override
    public String getNombre() {
        return "Completada";
    }
}
