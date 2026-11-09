package co.edu.uniquindio.NexoIA.Observer;

import co.edu.uniquindio.NexoIA.Model.Tarea;

public interface Observador {
    void actualizar(Tarea tarea, String mensaje);
}
