package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.FuenteDatosMemoria;

public enum FabricaFuenteDatos {
    MEMORIA {
        @Override
        public IFuenteDatos crear() {
            return
        }
    };

    public abstract IFuenteDatos crear();
}
