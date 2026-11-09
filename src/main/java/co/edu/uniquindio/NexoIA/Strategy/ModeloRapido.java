package co.edu.uniquindio.NexoIA.Strategy;

public class ModeloRapido implements EstrategiaModelo {
    private static final double COSTO_POR_USO = 100;

    @Override
    public String ejecutar(String prompt) {
        return "[modelo rapido] Respuesta veloz y basica para: " + prompt;
    }

    @Override
    public double getCostoPorUso() {
        return COSTO_POR_USO;
    }

    @Override
    public String getNombre() {
        return "rapido";
    }
}
