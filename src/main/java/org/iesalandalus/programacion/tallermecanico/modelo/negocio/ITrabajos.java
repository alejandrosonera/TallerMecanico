package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;

public interface ITrabajos {
    List<Trabajo> get();

    List<Trabajo> get(Cliente cliente);

    List<Trabajo> get(Vehiculo vehiculo);

    void insertar(Trabajo trabajo) throws OperationNotSupportedException;

    void anadirHoras(Trabajo trabajo, int horas);

    void anadirPrecioMaterial(float precioMaterial, Trabajo trabajo) throws OperationNotSupportedException;

    void cerrar(LocalDate fechaFin, Trabajo trabajo) throws OperationNotSupportedException;

    Trabajo buscar(Trabajo trabajo);

    void borrar(Trabajo trabajo) throws OperationNotSupportedException;
}
