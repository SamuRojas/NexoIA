package co.edu.uniquindio.NexoIA.Chain;

import co.edu.uniquindio.NexoIA.Model.Solicitud;

public abstract class FiltroSolicitud {
    // Eslabon base: valida lo suyo y delega en el siguiente de la cadena.
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
