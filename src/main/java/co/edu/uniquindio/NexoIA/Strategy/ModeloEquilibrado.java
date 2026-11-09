package co.edu.uniquindio.NexoIA.Strategy;

public class ModeloEquilibrado implements EstrategiaModelo {
    private static final double COSTO_POR_USO = 400;

    @Override
    public String ejecutar(String prompt) {
        return "[modelo equilibrado] Respuesta balanceada para: " + prompt;
    }

    @Override
    public double getCostoPorUso() {
        return COSTO_POR_USO;
    }

    @Override
    public String getNombre() {
        return "equilibrado";
    }
}