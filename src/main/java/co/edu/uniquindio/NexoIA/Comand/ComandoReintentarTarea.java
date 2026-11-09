package co.edu.uniquindio.NexoIA.Comand;

import co.edu.uniquindio.NexoIA.Mediator.MediadorOrquestador;
import co.edu.uniquindio.NexoIA.Memento.PuntoControl;
import co.edu.uniquindio.NexoIA.Model.Tarea;

public class ComandoReintentarTarea implements Comando {
    private final Tarea tarea;
    private final MediadorOrquestador orquestador;
    private PuntoControl estadoAnterior;

    public ComandoReintentarTarea(Tarea tarea, MediadorOrquestador orquestador) {
        this.tarea = tarea;
        this.orquestador = orquestador;
    }

    @Override
    public void ejecutar() {
        estadoAnterior = tarea.crearPuntoControl("antes de reintentar");
        tarea.reintentar();
        if (tarea.getEstado().getNombre().equals("En cola")) {
            orquestador.iniciarFlujo(tarea);
        }
    }

    @Override
    public void deshacer() {
        tarea.restaurar(estadoAnterior);
    }

    @Override
    public String obtenerNombre() {
        return "Reintentar tarea " + tarea.getId();
    }
}
