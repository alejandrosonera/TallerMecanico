package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Objects;

public class Mecanico extends Trabajo {
    private static final float PRECIO_MATERIAL = 1.5f;
    private static final float FACTOR_HORA = 30;
    protected float precioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
        fechaFin = null;
        horas = 0;
        precioMaterial = 0;
    }

    public Mecanico(Mecanico mecanico) {
        super(mecanico);
        Objects.requireNonNull(mecanico, "La revisión no puede ser nula.");
        cliente = new Cliente(mecanico.cliente);
        cliente = new Cliente(mecanico.cliente.getNombre(), mecanico.cliente.getDni(), mecanico.cliente.getTelefono());
        vehiculo = mecanico.vehiculo;
        horas = mecanico.horas;
        fechaFin = mecanico.fechaFin;
        fechaInicio = mecanico.fechaInicio;
        precioMaterial = mecanico.precioMaterial;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial(float precioMaterial) throws OperationNotSupportedException {
        if(precioMaterial <= 0){
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        this.precioMaterial += precioMaterial;
    }
    public float getPrecioEspecifico() {
        return (float) (horas * 30 + precioMaterial * 1.5);
    }

    @Override
    public String toString() {
        String cadenaADevolver;
        if (!estaCerrada()) {
            cadenaADevolver = String.format("%s - %s: (%s - ), %d horas, %.2f € en material", cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), horas, getPrecio());
        } else {
            cadenaADevolver = String.format("%s - %s: (%s - %s), %d horas, %.2f € en material, %.2f € total", cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), fechaFin.format(FORMATO_FECHA), horas, getPrecioMaterial(), getPrecio());
        }
        return cadenaADevolver;
    }
}
