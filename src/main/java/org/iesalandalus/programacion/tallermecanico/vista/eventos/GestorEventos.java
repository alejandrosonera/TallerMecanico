package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GestorEventos {
    static final Map<Evento, List<ReceptorEventos>> receptores;

    public GestorEventos(Evento eventos) {
        Objects.requireNonNull(eventos, "El evento no puede ser nulo.");
    }

    public void suscribir(ReceptorEventos receptor, Evento eventos) {
        Objects.requireNonNull(receptor, "El receptor no puede ser nulo.");
        Objects.requireNonNull(eventos, "El evento no puede ser nulo.");

    }
}
