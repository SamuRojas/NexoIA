package co.edu.uniquindio.NexoIA.Chain;

import co.edu.uniquindio.NexoIA.Model.Solicitud;

public class FiltroDatosPersonales extends FiltroSolicitud{
    @Override
    protected void comprobar(Solicitud solicitud) throws SolicitudRechazadaException {
        String texto = solicitud.getTexto().toLowerCase();
        if (texto.contains("@") || texto.contains("cedula") || texto.contains("telefono")) {
            throw new SolicitudRechazadaException("La solicitud contiene datos personales.");
        }
        System.out.println("   [FILTRO] Datos personales: OK");
    }
}
