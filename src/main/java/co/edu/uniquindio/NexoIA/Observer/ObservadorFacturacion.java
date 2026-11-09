package co.edu.uniquindio.NexoIA.Observer;

import co.edu.uniquindio.NexoIA.Model.Tarea;
import co.edu.uniquindio.NexoIA.State.EstadoCancelada;
import co.edu.uniquindio.NexoIA.State.EstadoCompletada;
import co.edu.uniquindio.NexoIA.State.EstadoFallida;
import co.edu.uniquindio.NexoIA.State.EstadoTarea;

public class ObservadorFacturacion implements Observador {
    private double totalFacturado = 0;

    @Override
    public void actualizar(Tarea tarea, String mensaje) {
        if (tarea.isFacturada()) {
            return;
        }
        EstadoTarea estado = tarea.getEstado();
        boolean finalizada = estado instanceof EstadoCompletada
                || estado instanceof EstadoFallida
                || estado instanceof EstadoCancelada;
        if (!finalizada || tarea.getCostoAcumulado() <= 0) {
            return;
        }
        tarea.setFacturada(true);
        totalFacturado += tarea.getCostoAcumulado();
        String modelo = tarea.getEstrategia() == null ? "sin modelo" : tarea.getEstrategia().getNombre();
        System.out.println("   [FACTURACION] " + tarea.getSolicitud().getUsuario().getNombre()
                + " paga $" + tarea.getCostoAcumulado() + " (modelo " + modelo + ")");
    }

    public double getTotalFacturado() {
        return totalFacturado;
    }
}
