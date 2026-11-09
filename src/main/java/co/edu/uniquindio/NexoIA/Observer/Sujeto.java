package co.edu.uniquindio.NexoIA.Observer;

// Metodos para gestionar las suscripciones.
public interface Sujeto {
    void suscribir(Observador observador);

    void desuscribir(Observador observador);

    void notificarObservadores(String mensaje);
}