package co.edu.uniquindio.NexoIA.Agentes;

import co.edu.uniquindio.NexoIA.Mediator.TipoEventoAgente;
import co.edu.uniquindio.NexoIA.Model.Tarea;

public class AgenteRevisor extends AgenteIA {
    private final int rechazosAntesDeAprobar;

    public AgenteRevisor(int rechazosAntesDeAprobar) {
        super("Revisor");
        this.rechazosAntesDeAprobar = rechazosAntesDeAprobar;
    }

    @Override
    protected String ejecutar(Tarea tarea, String contexto) {
        consultarModelo(tarea, "revisar: " + contexto);
        if (tarea.getIntentos() < rechazosAntesDeAprobar) {
            return "RECHAZADO: faltan fuentes y hay inconsistencias";
        }
        return "APROBADO: el informe cumple los criterios";
    }

    @Override
    protected TipoEventoAgente definirEvento(String resultado) {
        return resultado.startsWith("APROBADO")
                ? TipoEventoAgente.REVISION_APROBADA
                : TipoEventoAgente.REVISION_RECHAZADA;
    }
}
