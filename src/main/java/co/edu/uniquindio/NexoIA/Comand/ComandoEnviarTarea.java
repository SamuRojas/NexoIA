package co.edu.uniquindio.NexoIA.Comand;

import co.edu.uniquindio.NexoIA.Mediator.MediadorOrquestador;
import co.edu.uniquindio.NexoIA.Model.Tarea;

public class ComandoEnviarTarea implements Comando {
    private final Tarea tarea;
    private final MediadorOrquestador orquestador;

    public ComandoEnviarTarea(Tarea tarea, MediadorOrquestador orquestador) {
        this.tarea = tarea;
        this.orquestador = orquestador;
    }

    @Override
    public void ejecutar() {
        tarea.getSolicitud().getUsuario().consumirCuota();
        tarea.notificarObservadores("Tarea enviada (estado: " + tarea.getEstado().getNombre() + ")");
        orquestador.iniciarFlujo(tarea);
    }

    @Override
    public void deshacer() {
        tarea.cancelar();
        tarea.getSolicitud().getUsuario().devolverCuota();
    }

    @Override
    public String obtenerNombre() {
        return "Enviar tarea " + tarea.getId();
    }
}
