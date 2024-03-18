package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;

import java.util.Objects;

public class Controlador implements IControlador {
    private Vista vista;
    private ModeloCascada modelo;

    public Controlador(ModeloCascada modelo, Vista vista) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        Objects.requireNonNull(vista, "La vista no puede ser nula");
        this.vista = vista;
        this.modelo = new ModeloCascada(FabricaFuenteDatos.MEMORIA);
        vista.setControlador(this);
    }
    @Override
    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }
    @Override
    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }
   @Override
    public void actualizar(Evento evento) {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        switch (evento) {
            case INSERTAR_CLIENTE -> modelo.insertar;
        }

    }



}
