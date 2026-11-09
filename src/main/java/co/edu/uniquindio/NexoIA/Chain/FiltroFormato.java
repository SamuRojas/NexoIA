package co.edu.uniquindio.NexoIA.Chain;

import co.edu.uniquindio.NexoIA.Model.Solicitud;

public class FiltroFormato extends FiltroSolicitud {
    private static final int MINIMO = 10;
    private static final int MAXIMO = 500;

    @Override
    protected void comprobar(Solicitud solicitud) throws SolicitudRechazadaException {
        String texto = solicitud.getTexto();
        if (texto == null || texto.trim().length() < MINIMO || texto.length() > MAXIMO) {
            throw new SolicitudRechazadaException("El texto debe tener entre " + MINIMO + " y " + MAXIMO + " caracteres.");
        }
        if (solicitud.getPrioridad() == null) {
            throw new SolicitudRechazadaException("Debe indicar una prioridad.");
        }
        System.out.println("   [FILTRO] Formato: OK");
    }
}
