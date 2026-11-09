package co.edu.uniquindio.NexoIA.Agentes;

import co.edu.uniquindio.NexoIA.Mediator.EventoAgente;
import co.edu.uniquindio.NexoIA.Mediator.MediadorOrquestador;
import co.edu.uniquindio.NexoIA.Mediator.TipoEventoAgente;
import co.edu.uniquindio.NexoIA.Model.Tarea;

public abstract class AgenteIA {
    private final String nombre;
    private MediadorOrquestador mediador;

    public AgenteIA(String nombre) {
        this.nombre = nombre;
    }

    public final void procesar(Tarea tarea) {
        System.out.println("   >> [" + nombre + "] inicia");
        String contexto = prepararContexto(tarea);
        String resultado = ejecutar(tarea, contexto);
        validar(resultado);
        entregar(tarea, resultado);
    }

    protected String prepararContexto(Tarea tarea) {
        return tarea.getSolicitud().getTexto() + " | previos: " + tarea.getResultadosParciales().size();
    }

    protected abstract String ejecutar(Tarea tarea, String contexto);

    protected abstract TipoEventoAgente definirEvento(String resultado);

    protected void validar(String resultado) {
        if (resultado == null || resultado.trim().isEmpty()) {
            throw new IllegalStateException(nombre + " produjo un resultado vacio.");
        }
    }

    protected void entregar(Tarea tarea, String resultado) {
        tarea.agregarResultado(nombre + ": " + resultado);
        mediador.notificar(new EventoAgente(definirEvento(resultado), tarea, resultado));
    }

    // Usa la Strategy de la tarea y acumula el costo del modelo
    protected String consultarModelo(Tarea tarea, String prompt) {
        tarea.acumularCosto(tarea.getEstrategia().getCostoPorUso());
        return tarea.getEstrategia().ejecutar(prompt);
    }

    public void setMediador(MediadorOrquestador mediador) {
        this.mediador = mediador;
    }

    public String getNombre() {
        return nombre;
    }
}
