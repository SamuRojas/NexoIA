package co.edu.uniquindio.NexoIA.Model;

public class Usuario {
    private String nombre;
    private int cuotaDisponible;

    public Usuario(String nombre, int cuotaDisponible) {
        this.nombre = nombre;
        this.cuotaDisponible = cuotaDisponible;
    }

    public void consumirCuota() {
        cuotaDisponible--;
    }

    public void devolverCuota() {
        cuotaDisponible++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCuotaDisponible() {
        return cuotaDisponible;
    }

    public void setCuotaDisponible(int cuotaDisponible) {
        this.cuotaDisponible = cuotaDisponible;
    }
}
