package co.edu.uniquindio.NexoIA;

import co.edu.uniquindio.NexoIA.Agentes.AgenteInvestigador;
import co.edu.uniquindio.NexoIA.Agentes.AgenteRedactor;
import co.edu.uniquindio.NexoIA.Agentes.AgenteRevisor;
import co.edu.uniquindio.NexoIA.Chain.CadenaFiltros;
import co.edu.uniquindio.NexoIA.Chain.FiltroSolicitud;
import co.edu.uniquindio.NexoIA.Chain.SolicitudRechazadaException;
import co.edu.uniquindio.NexoIA.Comand.ComandoCancelarTarea;
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
import co.edu.uniquindio.NexoIA.Strategy.ModeloRapido;
import co.edu.uniquindio.NexoIA.Strategy.SelectorModelo;

public class Main {

    public static void main(String[] args) {
        FiltroSolicitud cadena = CadenaFiltros.crear();
        HistorialCheckpoints historial = new HistorialCheckpoints();
        PanelControl panel = new PanelControl();

        ObservadorPanelUsuario observadorPanel = new ObservadorPanelUsuario();
        ObservadorAuditoria auditoria = new ObservadorAuditoria();
        ObservadorFacturacion facturacion = new ObservadorFacturacion();

        Usuario usuario = new Usuario("Samuel", 3);

        System.out.println("=== CASO 1: informe sobre energia solar (CALIDAD, el Revisor falla una vez) ===");
        Solicitud solicitud1 = new Solicitud("Genera un informe sobre energia solar", Prioridad.CALIDAD, usuario);
        Tarea tarea1 = crearTarea("T-001", solicitud1, observadorPanel, auditoria, facturacion);
        panel.ejecutarComando(new ComandoEnviarTarea(tarea1, crearOrquestador(1, historial), cadena));

        System.out.println("\n=== CASO 2: solicitud con datos personales ===");
        Solicitud solicitud2 = new Solicitud("Resume el historial de juan@correo.com y su cedula",
                Prioridad.AHORRO, usuario);
        Tarea tarea2 = crearTarea("T-002", solicitud2, observadorPanel, auditoria, facturacion);
        try {
            panel.ejecutarComando(new ComandoEnviarTarea(tarea2, crearOrquestador(0, historial), cadena));
        } catch (SolicitudRechazadaException e) {
            System.out.println("   [RECHAZADA] " + e.getMessage());
        }

        System.out.println("\n=== CASO 3: cambio de modelo, cancelar tarea completada y deshacer ===");
        Solicitud solicitud3 = new Solicitud("Genera un resumen sobre energia eolica",
                Prioridad.EQUILIBRADO, usuario);
        Tarea tarea3 = crearTarea("T-003", solicitud3, observadorPanel, auditoria, facturacion);
        tarea3.setEstrategia(new ModeloRapido());
        panel.ejecutarComando(new ComandoEnviarTarea(tarea3, crearOrquestador(0, historial), cadena));
        panel.ejecutarComando(new ComandoCancelarTarea(tarea3));
        panel.deshacerUltimo();

        System.out.println("\n=== CASO 4: cancelar una tarea En cola y deshacer ===");
        Solicitud solicitud4 = new Solicitud("Genera un informe sobre energia geotermica",
                Prioridad.AHORRO, usuario);
        Tarea tarea4 = crearTarea("T-004", solicitud4, observadorPanel, auditoria, facturacion);
        panel.ejecutarComando(new ComandoCancelarTarea(tarea4));
        panel.deshacerUltimo();

        System.out.println("\n=== CASO 5: pausar una tarea y deshacer ===");
        Solicitud solicitud5 = new Solicitud("Resume articulos de robotica",
                Prioridad.EQUILIBRADO, usuario);
        Tarea tarea5 = crearTarea("T-005", solicitud5, observadorPanel, auditoria, facturacion);
        panel.ejecutarComando(new ComandoPausarTarea(tarea5));
        panel.deshacerUltimo();

        panel.mostrarHistorial();
        System.out.println("\nTotal facturado: $" + facturacion.getTotalFacturado());
        System.out.println("Registros de auditoria: " + auditoria.getRegistros().size());
        System.out.println("Cuota restante de " + usuario.getNombre() + ": " + usuario.getCuotaDisponible());
        System.out.println("T-001: " + tarea1.getEstado().getNombre());
        System.out.println("T-003: " + tarea3.getEstado().getNombre());
        System.out.println("T-004: " + tarea4.getEstado().getNombre());
        System.out.println("T-005: " + tarea5.getEstado().getNombre());
    }

    private static Tarea crearTarea(String id, Solicitud solicitud, ObservadorPanelUsuario observadorPanel,
                                    ObservadorAuditoria auditoria, ObservadorFacturacion facturacion) {
        Tarea tarea = new Tarea(id, solicitud);
        tarea.suscribir(observadorPanel);
        tarea.suscribir(auditoria);
        tarea.suscribir(facturacion);
        return tarea;
    }

    private static OrquestadorCentral crearOrquestador(int rechazos, HistorialCheckpoints historial) {
        return new OrquestadorCentral(
                new AgenteInvestigador(),
                new AgenteRedactor(),
                new AgenteRevisor(rechazos),
                new SelectorModelo(),
                historial);
    }
}
