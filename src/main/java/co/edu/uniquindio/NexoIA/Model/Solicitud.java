package co.edu.uniquindio.NexoIA.Model;

public class Solicitud {
    private String texto;
    private Prioridad prioridad;
    private Usuario usuario;

    public Solicitud(String texto, Prioridad prioridad, Usuario usuario) {
        this.texto = texto;
        this.prioridad = prioridad;
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
