package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Revision extends Trabajo {
    private static final float PRECIO_MATERIAL = 1.5f;
    private static final float FACTOR_HORA = 35;
    private static float precioMaterial;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
        fechaFin = null;
        horas = 0;
        precioMaterial = 0;
    }

    public Revision(Revision revision) {
        super(revision);
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        cliente = new Cliente(revision.cliente);
        cliente = new Cliente(revision.cliente.getNombre(), revision.cliente.getDni(), revision.cliente.getTelefono());
        vehiculo = revision.vehiculo;
        horas = revision.horas;
        fechaFin = revision.fechaFin;
        fechaInicio = revision.fechaInicio;
        precioMaterial = precioMaterial;
    }

    public static float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if(precioMaterial <= 0){
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        if (estaCerrado()) {
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        this.precioMaterial += precioMaterial;
    }
    public float getPrecioEspecifico() {
        return horas * 35;
    }

    @Override
    public String toString() {
        String cadenaADevolver;
        if (!estaCerrado()) {
            cadenaADevolver = String.format("%s - %s: (%s - ), %d horas, %.2f € en material", cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), horas, getPrecio());
        } else {
            cadenaADevolver = String.format("%s - %s: (%s - %s), %d horas, %.2f € en material, %.2f € total", cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), fechaFin.format(FORMATO_FECHA), horas, getPrecioMaterial(), getPrecio());
        }
        return cadenaADevolver;
    }
}
