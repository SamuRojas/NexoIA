package co.edu.uniquindio.NexoIA.Agentes;

import co.edu.uniquindio.NexoIA.Mediator.TipoEventoAgente;
import co.edu.uniquindio.NexoIA.Model.Tarea;

public class AgenteRevisor extends AgenteIA {
    private int rechazosSimulados;
    private boolean aprobado;

    // rechazosSimulados: cuantas veces rechaza antes de aprobar (para simular la falla)
    public AgenteRevisor(int rechazosSimulados) {
        super("Revisor");
        this.rechazosSimulados = rechazosSimulados;
    }

    @Override
    protected String ejecutar(Tarea tarea, String contexto) {
        consultarModelo(tarea, "revisar: " + contexto);
        if (rechazosSimulados > 0) {
            rechazosSimulados--;
            aprobado = false;
            return "RECHAZADO: faltan fuentes y hay inconsistencias";
        }
        aprobado = true;
        return "APROBADO: el informe cumple los criterios";
    }

    @Override
    protected TipoEventoAgente definirEvento(String resultado) {
        return aprobado ? TipoEventoAgente.REVISION_APROBADA : TipoEventoAgente.REVISION_RECHAZADA;
    }
}
