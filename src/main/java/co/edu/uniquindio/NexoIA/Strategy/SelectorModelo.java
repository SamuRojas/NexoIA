package co.edu.uniquindio.NexoIA.Strategy;

import co.edu.uniquindio.NexoIA.Model.Prioridad;

public class SelectorModelo {
    public EstrategiaModelo seleccionar(Prioridad prioridad) {
        switch (prioridad) {
            case AHORRO:
                return new ModeloRapido();
            case CALIDAD:
                return new ModeloPreciso();
            default:
                return new ModeloEquilibrado();
        }
    }
}
