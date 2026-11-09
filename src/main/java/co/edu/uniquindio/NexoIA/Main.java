package co.edu.uniquindio.NexoIA;

import co.edu.uniquindio.NexoIA.Agentes.AgenteInvestigador;
import co.edu.uniquindio.NexoIA.Agentes.AgenteRedactor;
import co.edu.uniquindio.NexoIA.Agentes.AgenteRevisor;
import co.edu.uniquindio.NexoIA.Chain.CadenaFiltros;
import co.edu.uniquindio.NexoIA.Chain.SolicitudRechazadaException;
import co.edu.uniquindio.NexoIA.Comand.ComandoEnviarTarea;
import co.edu.uniquindio.NexoIA.Comand.ComandoPausarTarea;
import co.edu.uniquindio.NexoIA.Comand.PanelControl;
import co.edu.uniquindio.NexoIA.Mediator.OrquestadorCentral;
import co.edu.uniquindio.NexoIA.Memento.HistorialCheckpoints;
import co.edu.uniquindio.NexoIA.Model.Prioridad;
import co.edu.uniquindio.NexoIA.Model.Solicitud;
import co.edu.uniquindio.NexoIA.Model.Tarea;
import co.edu.uniquindio.NexoIA.Model.Usuario;
import co.edu.uniquindio.NexoIA.Observer.ObservadorAuditoria;
import co.edu.uniquindio.NexoIA.Observer.ObservadorFacturacion;
import co.edu.uniquindio.NexoIA.Observer.ObservadorPanelUsuario;
import co.edu.uniquindio.NexoIA.Strategy.SelectorModelo;

public class Main {

    public static void main(String[] args) {
        casoCompletoConReintento();
        casoRechazadoPorFiltros();
        casoPausarYDeshacer();
    }

    private static void casoCompletoConReintento() {
        System.out.println("\n========================================================");
        System.out.println(" 1) CASO COMPLETO: informe sobre energia solar (CALIDAD)");
        System.out.println("========================================================");

        Usuario usuario = new Usuario("Ana Torres", 3);
        Solicitud solicitud = new Solicitud(
                "Genera un informe sobre energia solar", Prioridad.CALIDAD, usuario);

        Tarea tarea = new Tarea("T-001", solicitud);
        ObservadorFacturacion facturacion = new ObservadorFacturacion();
        tarea.suscribir(new ObservadorPanelUsuario());
        tarea.suscribir(new ObservadorAuditoria());
        tarea.suscribir(facturacion);

        AgenteRevisor revisor = new AgenteRevisor(1);
        OrquestadorCentral orquestador = new OrquestadorCentral(
                new AgenteInvestigador(),
                new AgenteRedactor(),
                revisor,
                new SelectorModelo(),
                new HistorialCheckpoints());

        PanelControl panel = new PanelControl();
        System.out.println("\n[1] Validando la solicitud y enviandola...");
        try {
            panel.ejecutarComando(new ComandoEnviarTarea(tarea, orquestador, CadenaFiltros.crear()));
        } catch (SolicitudRechazadaException e) {
            System.out.println("[CHAIN] Solicitud rechazada: " + e.getMessage());
            return;
        }

        panel.mostrarHistorial();
        System.out.println("\n--- RESULTADO DEL CASO 1 ---");
        System.out.println("Estado final : " + tarea.getEstado().getNombre());
        System.out.println("Progreso     : " + tarea.getProgreso() + "%");
        System.out.println("Intentos     : " + tarea.getIntentos());
        System.out.println("Costo tarea  : $" + tarea.getCostoAcumulado());
        System.out.println("Total facturado: $" + facturacion.getTotalFacturado());
        System.out.println("Cuota restante de " + usuario.getNombre() + ": " + usuario.getCuotaDisponible());
        System.out.println("Resultados parciales:");
        for (String resultado : tarea.getResultadosParciales()) {
            System.out.println("   - " + resultado);
        }
    }

    private static void casoRechazadoPorFiltros() {
        System.out.println("\n========================================================");
        System.out.println(" 2) CHAIN OF RESPONSIBILITY: solicitud con contenido prohibido");
        System.out.println("========================================================");

        Solicitud solicitud = new Solicitud(
                "Como hackear un banco", Prioridad.AHORRO, new Usuario("Luis", 1));
        try {
            CadenaFiltros.crear().validar(solicitud);
            System.out.println("[CHAIN] La solicitud paso los filtros.");
        } catch (SolicitudRechazadaException e) {
            System.out.println("[CHAIN] Solicitud rechazada: " + e.getMessage());
        }
    }

    private static void casoPausarYDeshacer() {
        System.out.println("\n========================================================");
        System.out.println(" 3) COMMAND + MEMENTO: pausar una tarea y deshacer");
        System.out.println("========================================================");

        Usuario usuario = new Usuario("Marta Ruiz", 2);
        Tarea tarea = new Tarea("T-002",
                new Solicitud("Resume articulos de robotica", Prioridad.EQUILIBRADO, usuario));
        tarea.suscribir(new ObservadorPanelUsuario());

        PanelControl panel = new PanelControl();
        panel.ejecutarComando(new ComandoPausarTarea(tarea));
        System.out.println("Estado tras pausar: " + tarea.getEstado().getNombre());

        panel.mostrarHistorial();
        panel.deshacerUltimo();
        System.out.println("Estado tras deshacer: " + tarea.getEstado().getNombre());
    }
}
