package co.edu.uniquindio.NexoIA.Memento;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class HistorialCheckpoints {
    private final Map<String, Stack<PuntoControl>> puntos = new HashMap<>();

    public void guardar(String tareaId, PuntoControl punto) {
        puntos.computeIfAbsent(tareaId, id -> new Stack<>()).push(punto);
    }

    public PuntoControl ultimo(String tareaId) {
        Stack<PuntoControl> pila = puntos.get(tareaId);
        return pila == null || pila.isEmpty() ? null : pila.peek();
    }

    public int cantidad(String tareaId) {
        Stack<PuntoControl> pila = puntos.get(tareaId);
        return pila == null ? 0 : pila.size();
    }
}
