package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Controlador {
    private Vista vista;
    private ModeloCascada modelo;

    public Controlador(ModeloCascada modelo, Vista vista) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        Objects.requireNonNull(vista, "La vista no puede ser nula");
        this.vista = vista;
        this.modelo = new ModeloCascada();
        vista.setControlador(this);
    }
    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }
    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }
    public void actualizar(Evento evento) {

    }



}
