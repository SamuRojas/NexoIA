package co.edu.uniquindio.NexoIA.Mediator;

import co.edu.uniquindio.NexoIA.Agentes.AgenteInvestigador;
import co.edu.uniquindio.NexoIA.Agentes.AgenteRedactor;
import co.edu.uniquindio.NexoIA.Agentes.AgenteRevisor;
import co.edu.uniquindio.NexoIA.Memento.HistorialCheckpoints;
import co.edu.uniquindio.NexoIA.Model.Tarea;
import co.edu.uniquindio.NexoIA.Strategy.SelectorModelo;

// Mediador concreto: los agentes no se conocen, solo le avisan a el.
public class OrquestadorCentral implements MediadorOrquestador {
    private static final int MAX_INTENTOS = 2;

    private final AgenteInvestigador investigador;
    private final AgenteRedactor redactor;
    private final AgenteRevisor revisor;
    private final SelectorModelo selector;
    private final HistorialCheckpoints historial;

    public OrquestadorCentral(AgenteInvestigador investigador, AgenteRedactor redactor, AgenteRevisor revisor,
                              SelectorModelo selector, HistorialCheckpoints historial) {
        this.investigador = investigador;
        this.redactor = redactor;
        this.revisor = revisor;
        this.selector = selector;
        this.historial = historial;

        // Inyectamos el mediador en cada agente
        this.investigador.setMediador(this);
        this.redactor.setMediador(this);
        this.revisor.setMediador(this);
    }

    @Override
    public void iniciarFlujo(Tarea tarea) {
        System.out.println("\n[Orquestador] Iniciando flujo de " + tarea.getId());
        // Strategy: se elige el modelo segun la prioridad (si no se fijo uno antes)
        if (tarea.getEstrategia() == null) {
            tarea.setEstrategia(selector.seleccionar(tarea.getSolicitud().getPrioridad()));
        }
        System.out.println("[Orquestador] Modelo elegido: " + tarea.getEstrategia().getNombre());
        guardarCheckpoint(tarea, "antes de planificar");
        tarea.avanzar();
        investigador.procesar(tarea);
    }

    @Override
    public void notificar(EventoAgente evento) {
        Tarea tarea = evento.getTarea();
        System.out.println("[Orquestador] Recibe " + evento.getTipo());
        switch (evento.getTipo()) {
            case INVESTIGACION_LISTA:
                tarea.setProgreso(33);
                guardarCheckpoint(tarea, "antes de redactar");
                tarea.avanzar();
                redactor.procesar(tarea);
                break;
            case BORRADOR_LISTO:
                tarea.setProgreso(66);
                guardarCheckpoint(tarea, "antes de revisar");
                tarea.avanzar();
                revisor.procesar(tarea);
                break;
            case REVISION_APROBADA:
                tarea.setProgreso(100);
                tarea.avanzar();
                break;
            case REVISION_RECHAZADA:
                manejarRechazo(tarea, evento.getContenido());
                break;
            default:
                break;
        }
    }

    private void manejarRechazo(Tarea tarea, String motivo) {
        tarea.fallar(motivo);
        tarea.incrementarIntentos();
        if (tarea.getIntentos() >= MAX_INTENTOS) {
            System.out.println("[Orquestador] Maximo de intentos alcanzado. La tarea queda Fallida.");
            return;
        }
        // Memento: volvemos al ultimo checkpoint en lugar de empezar de cero
        tarea.restaurar(historial.ultimo());
        System.out.println("[Orquestador] Reintentando desde el checkpoint (intento " + (tarea.getIntentos() + 1) + ")");
        redactor.procesar(tarea);
    }

    private void guardarCheckpoint(Tarea tarea, String etapa) {
        historial.guardar(tarea.crearPuntoControl(etapa));
        System.out.println("[Orquestador] Checkpoint guardado: " + etapa);
    }
}
