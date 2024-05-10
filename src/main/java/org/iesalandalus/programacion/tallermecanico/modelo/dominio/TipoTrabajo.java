package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public enum TipoTrabajo {
    MECANICO,
    REVISION;

    private String nombre;

    private void TipoTrabajo(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo.");
        this.nombre = nombre;
    }
    public static TipoTrabajo get(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        if (trabajo instanceof Mecanico) {
            return TipoTrabajo.MECANICO;
        } else {
            return TipoTrabajo.REVISION;
        }
    }
}
