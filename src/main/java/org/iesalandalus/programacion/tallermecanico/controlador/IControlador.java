package org.iesalandalus.programacion.tallermecanico.controlador;

public interface IControlador {
    void comenzar();

    void terminar();

    void actualizar(Evento evento);
}
