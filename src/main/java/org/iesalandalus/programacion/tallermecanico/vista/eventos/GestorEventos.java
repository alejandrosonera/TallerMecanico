package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import javax.naming.OperationNotSupportedException;
import java.util.*;

public class GestorEventos {
    private Map<Evento, List<ReceptorEventos>> receptores = new EnumMap<>(Evento.class);

    public GestorEventos(Evento... eventos) {
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        for(Evento evento: eventos){
            receptores.put(evento, new ArrayList<>());
        }
    }

    public void suscribir(ReceptorEventos receptor, Evento... eventos){
        List<ReceptorEventos> listaReceptores = new ArrayList<>();
        for(Evento evento : eventos){
            if(!receptores.containsKey(evento)){
                listaReceptores.add(receptor);
                receptores.put(evento, listaReceptores);
            }else{
                listaReceptores = receptores.get(evento);
                listaReceptores.add(receptor);
            }
        }
    }

    public void desuscribir(ReceptorEventos receptor, Evento... eventos){
        List<ReceptorEventos> listaReceptores;
        for(Evento evento : eventos){
            listaReceptores = receptores.get(evento);
            listaReceptores.remove(receptor);
        }
    }

    public void notificar(Evento evento) throws OperationNotSupportedException {
        for(ReceptorEventos receptorEventos : receptores.get(evento)){
            receptorEventos.actualizar(evento);
        }
    }
}
