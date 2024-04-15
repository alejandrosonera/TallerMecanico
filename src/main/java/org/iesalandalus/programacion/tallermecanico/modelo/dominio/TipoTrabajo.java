package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;

import java.time.LocalDate;
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
        return TipoTrabajo.get(trabajo);

    }
}
