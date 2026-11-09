package co.edu.uniquindio.NexoIA.Agentes;

import co.edu.uniquindio.NexoIA.Mediator.TipoEventoAgente;
import co.edu.uniquindio.NexoIA.Model.Tarea;


public class AgenteInvestigador extends AgenteIA{
    public AgenteInvestigador() {
        super("Investigador");
    }

    @Override
    protected String ejecutar(Tarea tarea, String contexto) {
        return consultarModelo(tarea, "investigar fuentes sobre: " + contexto);
    }

    @Override
    protected TipoEventoAgente definirEvento(String resultado) {
        return TipoEventoAgente.INVESTIGACION_LISTA;
    }
}
