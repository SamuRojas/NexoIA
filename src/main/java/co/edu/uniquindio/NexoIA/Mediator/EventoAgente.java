package co.edu.uniquindio.NexoIA.Mediator;

import co.edu.uniquindio.NexoIA.Model.Tarea;

public class EventoAgente {
    private final TipoEventoAgente tipo;
    private final Tarea tarea;
    private final String contenido;

    public EventoAgente(TipoEventoAgente tipo, Tarea tarea, String contenido) {
        this.tipo = tipo;
        this.tarea = tarea;
        this.contenido = contenido;
    }

    public TipoEventoAgente getTipo() {
        return tipo;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public String getContenido() {
        return contenido;
    }
}
