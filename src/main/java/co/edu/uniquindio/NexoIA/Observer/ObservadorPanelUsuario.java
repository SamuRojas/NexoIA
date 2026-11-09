package co.edu.uniquindio.NexoIA.Observer;

import co.edu.uniquindio.NexoIA.Model.Tarea;

public class ObservadorPanelUsuario implements Observador {
    @Override
    public void actualizar(Tarea tarea, String mensaje) {
        System.out.println("   [PANEL " + tarea.getSolicitud().getUsuario().getNombre() + "] "
                + tarea.getId() + " (" + tarea.getProgreso() + "%): " + mensaje);
    }
}
