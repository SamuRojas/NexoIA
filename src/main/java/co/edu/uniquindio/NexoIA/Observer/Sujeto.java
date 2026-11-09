package co.edu.uniquindio.NexoIA.Observer;

public interface Sujeto {
    void suscribir(Observador observador);

    void desuscribir(Observador observador);

    void notificarObservadores(String mensaje);
}
