package co.edu.uniquindio.NexoIA.Observer;

import co.edu.uniquindio.NexoIA.Model.Tarea;

public class ObservadorFacturacion implements Observador {
    private double totalFacturado = 0;

    @Override
    public void actualizar(Tarea tarea, String mensaje) {
        // Solo se factura una vez, cuando la tarea llega al 100%
        if (tarea.getProgreso() == 100 && !tarea.isFacturada()) {
            tarea.setFacturada(true);
            totalFacturado += tarea.getCostoAcumulado();
            System.out.println("   [FACTURACION] " + tarea.getSolicitud().getUsuario().getNombre()
                    + " paga $" + tarea.getCostoAcumulado() + " (modelo "
                    + tarea.getEstrategia().getNombre() + ")");
        }
    }

    public double getTotalFacturado() {
        return totalFacturado;
    }
}