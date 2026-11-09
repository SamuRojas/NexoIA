package co.edu.uniquindio.NexoIA.Comand;

import co.edu.uniquindio.NexoIA.Chain.FiltroSolicitud;
import co.edu.uniquindio.NexoIA.Mediator.MediadorOrquestador;
import co.edu.uniquindio.NexoIA.Model.Tarea;
import co.edu.uniquindio.NexoIA.State.EstadoCancelada;
import co.edu.uniquindio.NexoIA.State.EstadoTarea;

public class ComandoEnviarTarea implements Comando {
    private final Tarea tarea;
    private final MediadorOrquestador orquestador;
    private final FiltroSolicitud cadenaFiltros;

    public ComandoEnviarTarea(Tarea tarea, MediadorOrquestador orquestador, FiltroSolicitud cadenaFiltros) {
        this.tarea = tarea;
        this.orquestador = orquestador;
        this.cadenaFiltros = cadenaFiltros;
    }

    @Override
    public void ejecutar() {
        cadenaFiltros.validar(tarea.getSolicitud());
        tarea.getSolicitud().getUsuario().consumirCuota();
        tarea.notificarObservadores("Tarea enviada (estado: " + tarea.getEstado().getNombre() + ")");
        orquestador.iniciarFlujo(tarea);
    }

    @Override
    public void deshacer() {
        EstadoTarea antes = tarea.getEstado();
        tarea.cancelar();
        if (!(antes instanceof EstadoCancelada) && tarea.getEstado() instanceof EstadoCancelada) {
            tarea.getSolicitud().getUsuario().devolverCuota();
        }
    }

    @Override
    public String obtenerNombre() {
        return "Enviar tarea " + tarea.getId();
    }
}
