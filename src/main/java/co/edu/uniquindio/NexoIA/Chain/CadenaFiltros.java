package co.edu.uniquindio.NexoIA.Chain;

public final class CadenaFiltros {

    private CadenaFiltros() {
    }

    public static FiltroSolicitud crear() {
        FiltroSolicitud contenido = new FiltroContenidoProhibido();
        FiltroSolicitud datos = new FiltroDatosPersonales();
        FiltroSolicitud cuota = new FiltroCuota();
        FiltroSolicitud formato = new FiltroFormato();

        contenido.setSiguiente(datos);
        datos.setSiguiente(cuota);
        cuota.setSiguiente(formato);
        return contenido;
    }
}
