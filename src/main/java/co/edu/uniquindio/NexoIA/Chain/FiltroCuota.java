package co.edu.uniquindio.NexoIA.Chain;

import co.edu.uniquindio.NexoIA.Model.Solicitud;

public class FiltroCuota extends FiltroSolicitud {
    @Override
    protected void comprobar(Solicitud solicitud) throws SolicitudRechazadaException {
        if (solicitud.getUsuario().getCuotaDisponible() <= 0) {
            throw new SolicitudRechazadaException("El usuario " + solicitud.getUsuario().getNombre()
                    + " no tiene cuota disponible.");
        }
        System.out.println("   [FILTRO] Cuota: OK (" + solicitud.getUsuario().getCuotaDisponible() + " disponibles)");
    }
}
