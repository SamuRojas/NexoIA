package co.edu.uniquindio.NexoIA.Chain;

public abstract class FiltroSolicitud {
    // Eslabon base: valida lo suyo y delega en el siguiente de la cadena.
    public abstract class FiltroSolicitud {
        private co.edu.uniquindio.NexoIA.Chain.FiltroSolicitud siguiente;

        public void setSiguiente(co.edu.uniquindio.NexoIA.Chain.FiltroSolicitud siguiente) {
            this.siguiente = siguiente;
        }

        public final void validar(Solicitud solicitud) throws SolicitudRechazadaException {
            comprobar(solicitud);
            if (siguiente != null) {
                siguiente.validar(solicitud);
            }
        }

        protected abstract void comprobar(Solicitud solicitud) throws SolicitudRechazadaException;
}
