package co.edu.uniquindio.NexoIA.Comand;

import co.edu.uniquindio.NexoIA.Memento.PuntoControl;
import co.edu.uniquindio.NexoIA.Model.Tarea;

public class ComandoCancelarTarea implements Comando{
    private final Tarea tarea;
    private PuntoControl estadoAnterior;

    public ComandoCancelarTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    @Override
    public void ejecutar() {
        // Memento: guardamos el estado previo para poder deshacer
        estadoAnterior = tarea.crearPuntoControl("antes de cancelar");
        tarea.cancelar();
    }

    @Override
    public void deshacer() {
        tarea.restaurar(estadoAnterior);
    }

    @Override
    public String obtenerNombre() {
        return "Cancelar tarea " + tarea.getId();
    }
}
