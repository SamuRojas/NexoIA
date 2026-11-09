package co.edu.uniquindio.NexoIA.Agentes;

import co.edu.uniquindio.NexoIA.Mediator.TipoEventoAgente;
import co.edu.uniquindio.NexoIA.Model.Tarea;

public class AgenteRedactor extends AgenteIA {
    public AgenteRedactor() {
        super("Redactor");
    }

    @Override
    protected String ejecutar(Tarea tarea, String contexto) {
        return consultarModelo(tarea, "redactar borrador con: " + contexto);
    }

    @Override
    protected TipoEventoAgente definirEvento(String resultado) {
        return TipoEventoAgente.BORRADOR_LISTO;
    }
}
