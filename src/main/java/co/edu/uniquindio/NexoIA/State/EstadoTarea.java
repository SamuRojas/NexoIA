package co.edu.uniquindio.NexoIA.State;

import co.edu.uniquindio.NexoIA.Model.Tarea;

public interface EstadoTarea {
    void avanzar(Tarea tarea);

    void fallar(Tarea tarea, String motivo);

    void cancelar(Tarea tarea);

    void reintentar(Tarea tarea);

    String getNombre();
}
