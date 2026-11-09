package co.edu.uniquindio.NexoIA.Strategy;

public class ModeloPreciso implements EstrategiaModelo {
    private static final double COSTO_POR_USO = 1000;

    @Override
    public String ejecutar(String prompt) {
        return "[modelo preciso] Respuesta detallada y precisa para: " + prompt;
    }

    @Override
    public double getCostoPorUso() {
        return COSTO_POR_USO;
    }

    @Override
    public String getNombre() {
        return "preciso";
    }
}
