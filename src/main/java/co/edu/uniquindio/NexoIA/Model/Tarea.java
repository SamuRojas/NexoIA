package co.edu.uniquindio.NexoIA.Model;

import co.edu.uniquindio.NexoIA.Memento.PuntoControl;
import co.edu.uniquindio.NexoIA.Observer.Observador;
import co.edu.uniquindio.NexoIA.Observer.Sujeto;
import co.edu.uniquindio.NexoIA.State.EstadoEnCola;
import co.edu.uniquindio.NexoIA.State.EstadoTarea;
import co.edu.uniquindio.NexoIA.Strategy.EstrategiaModelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad central: es el Contexto de State, el Sujeto de Observer,
 * el Originador de Memento y el Contexto de Strategy.
 */
public class Tarea implements Sujeto {
    private final String id;
    private final Solicitud solicitud;
    private EstadoTarea estado;
    private EstrategiaModelo estrategia;
    private int progreso;
    private int intentos;
    private double costoAcumulado;
    private boolean facturada;
    private List<String> resultadosParciales;
    private final List<Observador> observadores;

    public Tarea(String id, Solicitud solicitud) {
        this.id = id;
        this.solicitud = solicitud;
        this.estado = new EstadoEnCola();
        this.progreso = 0;
        this.intentos = 0;
        this.costoAcumulado = 0;
        this.facturada = false;
        this.resultadosParciales = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }

    // ---------- State: delegacion al estado actual ----------
    public void avanzar() {
        estado.avanzar(this);
    }

    public void fallar(String motivo) {
        estado.fallar(this, motivo);
    }

    public void cancelar() {
        estado.cancelar(this);
    }

    public void reintentar() {
        estado.reintentar(this);
    }

    public void setEstado(EstadoTarea nuevoEstado) {
        this.estado = nuevoEstado;
        notificarObservadores("Estado -> " + nuevoEstado.getNombre());
    }

    // ---------- Memento (Originador) ----------
    public PuntoControl crearPuntoControl(String etapa) {
        return new PuntoControl(etapa, estado, progreso, resultadosParciales);
    }

    public void restaurar(PuntoControl punto) {
        this.estado = punto.getEstado();
        this.progreso = punto.getProgreso();
        this.resultadosParciales = new ArrayList<>(punto.getResultadosParciales());
        // el costo NO se restaura: el modelo ya fue consumido y se cobra
        notificarObservadores("Restaurada al checkpoint '" + punto.getEtapa()
                + "' (estado " + estado.getNombre() + ", progreso " + progreso + "%)");
    }

    // ---------- Observer (Sujeto) ----------
    @Override
    public void suscribir(Observador observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    @Override
    public void desuscribir(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(String mensaje) {
        for (Observador observador : observadores) {
            observador.actualizar(this, mensaje);
        }
    }

    // ---------- Logica propia ----------
    public void agregarResultado(String resultado) {
        resultadosParciales.add(resultado);
    }

    public void acumularCosto(double costo) {
        costoAcumulado += costo;
    }

    public void incrementarIntentos() {
        intentos++;
    }

    @Override
    public String toString() {
        return id + " - " + solicitud.getTexto() + " - " + estado.getNombre();
    }

    public String getId() {
        return id;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public EstrategiaModelo getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(EstrategiaModelo estrategia) {
        this.estrategia = estrategia;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public int getIntentos() {
        return intentos;
    }

    public double getCostoAcumulado() {
        return costoAcumulado;
    }

    public boolean isFacturada() {
        return facturada;
    }

    public void setFacturada(boolean facturada) {
        this.facturada = facturada;
    }

    public List<String> getResultadosParciales() {
        return resultadosParciales;
    }
}