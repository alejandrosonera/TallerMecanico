package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trabajos implements ITrabajos {
    private List<Trabajo> coleccionTrabajos;

    public Trabajos() {
        coleccionTrabajos = new ArrayList<>();
    }
    @Override
    public List<Trabajo> get() {
        return new ArrayList<>(coleccionTrabajos);
    }
    @Override
    public List<Trabajo> get(Cliente cliente) {
        List<Trabajo> revisionesCliente = new ArrayList<>();
        for (Trabajo revision : coleccionTrabajos) {
            if (revision.getCliente().equals(cliente)) {
                revisionesCliente.add(revision);
            }
        }
        return revisionesCliente;
    }
    @Override
    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> revisionesVehiculo = new ArrayList<>();
        for (Trabajo revision : coleccionTrabajos) {
            if (revision.getVehiculo().equals(vehiculo)) {
                coleccionTrabajos.add(revision);
            }
        }
        return revisionesVehiculo;
    }
    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaFin());
        coleccionTrabajos.add(trabajo);
    }
    private void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws OperationNotSupportedException {
        for (Trabajo revision : coleccionTrabajos) {
            if (!revision.estaCerrada()) {
                if (revision.getCliente().equals(cliente)) {
                    throw new OperationNotSupportedException("El cliente tiene otra revision en curso");
                } else if (revision.getVehiculo().equals(vehiculo)) {
                    throw new OperationNotSupportedException("El vehiculo esta actualmente en revision");
                }
            } else {
                if (revision.getCliente().equals(cliente) && !fechaRevision.isAfter(revision.getFechaFin())) {
                    throw new OperationNotSupportedException("El cliente tiene una revision posterior.");
                } else if (revision.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(revision.getFechaFin())) {
                    throw new OperationNotSupportedException("El vehiculo tiene una revision posteior.");
                }
            }
        }
    }
    @Override
    public  void anadirHoras(Trabajo trabajo, int horas) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");

    }
    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) throws OperationNotSupportedException {
        for (Trabajo revision : coleccionTrabajos) {
            if (revision.getVehiculo().equals(vehiculo) && !revision.estaCerrada()) {
                return revision;
            }
        }
        throw new OperationNotSupportedException("No se encontro un trabajo abierto para el vehiculo indicado.");
    }
    @Override
    public void anadirPrecioMaterial(float precioMaterial, Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        Mecanico revisionAbierta = (Mecanico) getTrabajoAbierto(trabajo.getVehiculo());
        revisionAbierta.anadirPrecioMaterial(precioMaterial);
    }
    @Override
    public void cerrar(LocalDate fechaFin, Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        Trabajo revisionAbierta = getTrabajoAbierto(trabajo.getVehiculo());
        revisionAbierta.cerrar(fechaFin);
    }
    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        if (!coleccionTrabajos.contains(trabajo)) {
            trabajo = null;
        }
        return trabajo;
    }
    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        if (buscar(trabajo) == null) {
            throw new OperationNotSupportedException("No existe el trabajo.");
        }
        coleccionTrabajos.remove(trabajo);
    }
}
