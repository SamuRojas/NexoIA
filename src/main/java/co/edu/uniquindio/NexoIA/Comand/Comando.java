package co.edu.uniquindio.NexoIA.Comand;

public interface Comando {
    void ejecutar();

    void deshacer();

    String obtenerNombre();
}
