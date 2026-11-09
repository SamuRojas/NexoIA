package co.edu.uniquindio.NexoIA.Observer;

import co.edu.uniquindio.NexoIA.Model.Tarea;

// Contrato que cumple cualquier interesado en los cambios de una tarea.
public interface Observador {
    void actualizar(Tarea tarea, String mensaje);
}