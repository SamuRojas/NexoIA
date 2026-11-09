package co.edu.uniquindio.NexoIA.Chain;

import co.edu.uniquindio.NexoIA.Model.Solicitud;

import java.util.Arrays;
import java.util.List;

public class FiltroContenidoProhibido extends FiltroSolicitud {

    private static final List<String> PALABRAS_PROHIBIDAS = Arrays.asList("bomba", "hackear", "malware");

    @Override
    protected void comprobar(Solicitud solicitud) throws SolicitudRechazadaException {
        String texto = solicitud.getTexto().toLowerCase();
        for (String palabra : PALABRAS_PROHIBIDAS) {
            if (texto.contains(palabra)) {
                throw new SolicitudRechazadaException("Contenido prohibido detectado: '" + palabra + "'.");
            }
        }
        System.out.println("   [FILTRO] Contenido prohibido: OK");
    }
}
