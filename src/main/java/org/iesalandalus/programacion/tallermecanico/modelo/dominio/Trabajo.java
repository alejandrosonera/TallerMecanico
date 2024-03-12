package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public abstract class Trabajo {
    static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final float FACTOR_DIA = 10;
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    protected int horas;
    protected Cliente cliente;
    protected Vehiculo vehiculo;
    protected Revision revision;
    protected Mecanico mecanico;

    protected Trabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFechaInicio(fechaInicio);
    }
    protected Trabajo(Trabajo trabajo) {
        setCliente(trabajo.cliente);
        setVehiculo(trabajo.vehiculo);
        setFechaInicio(trabajo.fechaInicio);
    }
    public static final Trabajo copiar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo");
        if (trabajo instanceof Mecanico mecanico) {
            mecanico = new Mecanico(mecanico);
        } else if (trabajo instanceof Revision revision) {
            revision = new Revision(revision);
        }
        return trabajo;
    }
    public Trabajo get(Vehiculo vehiculo) {
        return new Revision(new Cliente("Alejandro", "26457854H", "845290112"), vehiculo, LocalDate.now());
    }

    public Cliente getCliente() {
        return cliente;
    }

    protected void setCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    protected void setVehiculo(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    protected void setFechaInicio(LocalDate fechaInicio) {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        } else if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        this.fechaFin = fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public void anadirHoras(int horas) throws OperationNotSupportedException {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        if (estaCerrada()) {
            throw new OperationNotSupportedException("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        this.horas += horas;
    }

    public boolean estaCerrada() {
        return fechaFin != null;
    }

    public void cerrar(LocalDate fechaFin) throws OperationNotSupportedException {
        if (estaCerrada()) {
            throw new OperationNotSupportedException("La revisión ya está cerrada.");
        }
        setFechaFin(fechaFin);
    }

    public float getPrecio() {
        float precioFijo = FACTOR_DIA * getDias() + horas * getHoras();
        float precioEspecificado = Revision.getPrecioMaterial() * mecanico.precioMaterial;
        return precioFijo + precioEspecificado;

    }

    private float getDias() {
        return (estaCerrada()) ? ChronoUnit.DAYS.between(fechaInicio, fechaFin) : 0;
    }

    public abstract float getPrecioEspecifico();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return Objects.equals(fechaInicio, revision.fechaInicio) && Objects.equals(cliente, revision.cliente) && Objects.equals(vehiculo, revision.vehiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaInicio, cliente, vehiculo);
    }
}
