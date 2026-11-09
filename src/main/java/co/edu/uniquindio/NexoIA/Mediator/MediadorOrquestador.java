package co.edu.uniquindio.NexoIA.Mediator;

import co.edu.uniquindio.NexoIA.Model.Tarea;

public interface MediadorOrquestador {
    void iniciarFlujo(Tarea tarea);

    void notificar(EventoAgente evento);
}
