package co.edu.uniquindio.NexoIA.Comand;

import co.edu.uniquindio.NexoIA.Memento.PuntoControl;
import co.edu.uniquindio.NexoIA.Model.Tarea;

public class ComandoPausarTarea implements Comando {
    private final Tarea tarea;
    private PuntoControl estadoAnterior;

    public ComandoPausarTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    @Override
    public void ejecutar() {

        estadoAnterior = tarea.crearPuntoControl("antes de pausar");
        tarea.pausar();
    }

    @Override
    public void deshacer() {
        tarea.restaurar(estadoAnterior);
    }

    @Override
    public String obtenerNombre() {
        return "Pausar tarea " + tarea.getId();
    }
}
