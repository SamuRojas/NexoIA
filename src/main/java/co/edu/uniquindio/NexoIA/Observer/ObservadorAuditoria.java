package co.edu.uniquindio.NexoIA.Observer;

import co.edu.uniquindio.NexoIA.Model.Tarea;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ObservadorAuditoria implements Observador {
    private final List<String> registros = new ArrayList<>();

    @Override
    public void actualizar(Tarea tarea, String mensaje) {
        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String registro = hora + " | " + tarea.getId() + " | " + mensaje;
        registros.add(registro);
        System.out.println("   [AUDITORIA] " + registro);
    }

    public List<String> getRegistros() {
        return registros;
    }
}
