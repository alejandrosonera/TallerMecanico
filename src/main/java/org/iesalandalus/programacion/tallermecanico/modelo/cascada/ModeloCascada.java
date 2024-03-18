package org.iesalandalus.programacion.tallermecanico.modelo.cascada;


import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Trabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.Vehiculos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModeloCascada {
    private IClientes clientes;
    private IVehiculos vehiculos;
    private ITrabajos trabajos;

    public ModeloCascada(FabricaFuenteDatos fabricaFuenteDatos) {
        IFuenteDatos fuenteDatos = fabricaFuenteDatos.crear();
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
        trabajos = fuenteDatos.crearTrabajos();
    }

    public void comenzar() {
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        trabajos = new Trabajos();
    }
    public void terminar() {
        System.out.println("Modelo terminado.......");
    }
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo");
        clientes.insertar(new Cliente(cliente));
    }
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "El vehiculo no puede ser nulo.");
        vehiculos.insertar(vehiculo);
    }
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        if (trabajo instanceof Revision revision) {
            trabajos.insertar(revision);
        } else if (trabajo instanceof Mecanico mecanico) {
            trabajos.insertar(mecanico);
        }
    }
    public Cliente buscar(Cliente cliente) {
        return new Cliente(Objects.requireNonNull(clientes.buscar(cliente), "No existe un cliente igual"));
    }
    public Vehiculo buscar(Vehiculo vehiculo) {
        return Objects.requireNonNull(vehiculos.buscar(vehiculo), "No existe un vehiculo igual");
    }
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        trabajo = trabajos.buscar(trabajo);
        if (trabajo instanceof Mecanico mecanico) {
            trabajo = new Mecanico(mecanico);
        } else if (trabajo instanceof Revision revision) {
            trabajo = new Revision(revision);
        }
        return trabajo;
    }
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente, nombre, telefono);
    }
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        trabajos.anadirHoras(trabajo, horas);
    }
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        trabajos.anadirPrecioMaterial(trabajo, precioMaterial);
    }
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        trabajos.cerrar(trabajo, fechaFin);
    }
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        for (Trabajo trabajo : trabajos.get(cliente)) {
            trabajos.borrar(trabajo);
        }
        clientes.borrar(cliente);
    }
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            trabajos.borrar(trabajo);
        }
        vehiculos.borrar(vehiculo);
    }
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        trabajos.borrar(trabajo);
    }
    public List<Cliente> getClientes() {
        List<Cliente> listaExistente = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            listaExistente.add(new Cliente(cliente));
        }
        return listaExistente;
    }
    public List<Vehiculo> getVehiculos() {
        return vehiculos.get();
    }
    public List<Trabajo> getRevisiones() {
        List<Trabajo> trabajosExistentes = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get()) {
            trabajosExistentes.add(trabajo);
        }
        return trabajosExistentes;
    }
    public List<Trabajo> getTrabajos(Cliente cliente) {
        List<Trabajo> clientesExistentes = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(cliente)) {
            if (trabajo instanceof Mecanico mecanico) {
                clientesExistentes.add(mecanico);
            } else if (trabajo instanceof Revision revision) {
                clientesExistentes.add(revision);
            }
        }
        return clientesExistentes;
    }
    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        List<Trabajo> vehiculosExistentes = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            if (trabajo instanceof Mecanico mecanico) {
                vehiculosExistentes.add(mecanico);
            } else if (trabajo instanceof Revision revision) {
                vehiculosExistentes.add(revision);
            }
        }
        return vehiculosExistentes;
    }
}
