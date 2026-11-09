package co.edu.uniquindio.NexoIA.Memento;

// Cuidador: guarda los puntos de control sin conocer su contenido.
public class HistorialCheckpoints {
    private final Stack<PuntoControl> puntos = new Stack<>();

    public void guardar(PuntoControl punto) {
        puntos.push(punto);
    }

    public PuntoControl ultimo() {
        return puntos.isEmpty() ? null : puntos.peek();
    }

    public int cantidad() {
        return puntos.size();
    }
}
