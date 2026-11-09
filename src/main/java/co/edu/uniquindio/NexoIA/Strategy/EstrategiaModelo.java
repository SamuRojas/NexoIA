package co.edu.uniquindio.NexoIA.Strategy;

public interface EstrategiaModelo {
    String ejecutar(String prompt);

    double getCostoPorUso();

    String getNombre();
}