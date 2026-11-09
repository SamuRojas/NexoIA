package co.edu.uniquindio.NexoIA.Comand;

import java.util.Stack;

// Invocador: ejecuta comandos y guarda el historial para deshacer.
public class PanelControl {
    private final Stack<Comando> historial = new Stack<>();

    public void ejecutarComando(Comando comando) {
        System.out.println("\n[PanelControl] Ejecutando: " + comando.obtenerNombre());
        comando.ejecutar();
        historial.push(comando);
    }

    public void deshacerUltimo() {
        if (historial.isEmpty()) {
            System.out.println("\n[PanelControl] No hay acciones para deshacer.");
            return;
        }
        Comando ultimo = historial.pop();
        System.out.println("\n[PanelControl] Deshaciendo: " + ultimo.obtenerNombre());
        ultimo.deshacer();
    }

    public void mostrarHistorial() {
        System.out.println("\n--- HISTORIAL DE COMANDOS ---");
        if (historial.isEmpty()) {
            System.out.println("(vacio)");
        }
        for (int i = 0; i < historial.size(); i++) {
            System.out.println((i + 1) + ". " + historial.get(i).obtenerNombre());
        }
    }
}