package co.edu.uniquindio.NexoIA.State;

import co.edu.uniquindio.NexoIA.Model.Tarea;

public class EstadoCancelada implements EstadoTarea {

    @Override
    public void avanzar(Tarea tarea) {
        System.out.println("   [STATE] Una tarea Cancelada no puede avanzar.");
    }

    @Override
    public void fallar(Tarea tarea, String motivo) {
        System.out.println("   [STATE] Una tarea Cancelada no puede fallar.");
    }

    @Override
    public void cancelar(Tarea tarea) {
        System.out.println("   [STATE] La tarea ya esta Cancelada.");
    }

    @Override
    public void reintentar(Tarea tarea) {
        System.out.println("   [STATE] Una tarea Cancelada no se puede reintentar.");
    }

    @Override
    public void pausar(Tarea tarea) {
        System.out.println("   [STATE] Una tarea Cancelada no se puede pausar.");
    }

    @Override
    public String getNombre() {
        return "Cancelada";
    }
}
