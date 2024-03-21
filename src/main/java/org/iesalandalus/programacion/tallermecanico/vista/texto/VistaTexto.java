package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class VistaTexto {

    public GestorEventos getGestorEventos() {
        return getGestorEventos();
    }

    public void comenzar() {
        Evento evento;
        do {
            Consola.mostrarMenu();
            evento = Consola.elegirOpcion();
            Consola.mostrarCabecera();
            ejecutar(evento);
        } while (evento != Evento.SALIR);
        terminar();
    }

    public void terminar() {
        System.out.printf("Hasta luego!!!%n");
    }

    private void ejecutar(Evento opcion) {
        getGestorEventos().notificar(opcion);
    }

    public Cliente leerCliente() {
        Cliente cliente = null;
        boolean clienteCorrecto = false;
        do {
            try {
                cliente = new Cliente(Consola.leerCadena("Dime el nombre del cliente: "), Consola.leerCadena("Dime el dni del cliente: "), Consola.leerCadena("Dime el telefono del cliente: "));
                clienteCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!clienteCorrecto);
        return cliente;
    }

    public Cliente leerClienteDni() {
        Cliente cliente = null;
        boolean clienteCorrecto = false;
        do {
            try {
                cliente = new Cliente(Cliente.get(Consola.leerCadena("Dime el dni del cliente: ")));
                clienteCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!clienteCorrecto);
        return cliente;
    }

    public String leerNuevoNombre() {
        String nombre;
        boolean nombreCorrecto = false;
        do {
            nombre = Consola.leerCadena("Dime el nuevo nombre del cliente: ");
            if (!nombre.isBlank()) {
                try {
                    new Cliente(nombre, VistaTexto.DNI_EJEMPLO, "83829034P");
                    nombreCorrecto = true;
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                nombreCorrecto = true;
            }
        } while (!nombreCorrecto);
        return nombre;
    }

    public String leerNuevoTelefono() {
        String telefono;
        boolean telefonoCorrecto = false;
        do {
            telefono = Consola.leerCadena("Dime el nuevo telefono del cliente: ");
            if (telefono.isBlank()) {
                try {
                    new Cliente("Juan", VistaTexto.DNI_EJEMPLO, telefono);
                    telefonoCorrecto = true;
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                telefonoCorrecto = true;
            }
        } while (!telefonoCorrecto);
        return telefono;
    }

    public Vehiculo leerVehiculo() {
        Vehiculo vehiculo = null;
        boolean vehiculoCorrecto = false;
        do {
            try {
                vehiculo = new Vehiculo(Consola.leerCadena("Dime la marca del vehiculo: "), Consola.leerCadena("Dime el modelo del vehiculo: "), Consola.leerCadena("Dime la matricula del vehiculo: "));
                vehiculoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!vehiculoCorrecto);
        return vehiculo;
    }

    public Vehiculo leerVehiculoMatricula() {
        Vehiculo vehiculo = null;
        boolean vehiculoCorrecto = false;
        do {
            try {
                vehiculo = Vehiculo.get(Consola.leerCadena("Dime la matriculad del vehiculo: "));
                vehiculoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!vehiculoCorrecto);
        return vehiculo;
    }

    public Trabajo leerRevision() {
        Revision revision = null;
        boolean trabajoCorrecto = false;
        do {
            try {
                revision = new Revision(leerClienteDni(), leerVehiculoMatricula(), Consola.leerFecha("Dime la fecha de inicio del trabajo: "));
                trabajoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!trabajoCorrecto);
        return revision;
    }

    public Trabajo leerMecanico() {
        Trabajo mecanico = null;
        boolean trabajoCorrecto = false;
        do {
            try {
                mecanico = new Mecanico(leerClienteDni(), leerVehiculoMatricula(), Consola.leerFecha("Dime la fecha de inicio del trabajo: "));
                trabajoCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!trabajoCorrecto);
        return mecanico;
    }

    public Trabajo leerTrabajoVehiculo() {
        return Trabajo.get(leerVehiculo());
    }

    public int leerHoras() {
        int horas;
        boolean horasCorrectas = false;
        do {
            horas = Consola.leerEntero("Dime las horas que quieres añadir: ");
            try {
                Revision revision = new Revision(Cliente.get(VistaTexto.DNI_EJEMPLO), Vehiculo.get(VistaTexto.MATRICULA_DEFECTO), LocalDate.now());
                revision.anadirHoras(horas);
                horasCorrectas = true;
            } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
                System.out.println(e.getMessage());
            }
        } while (!horasCorrectas);
        return horas;
    }

    public float leerPrecioMaterial() {
        float precioMaterial;
        boolean precioCorrecto = false;
        do {
            precioMaterial = Consola.leerReal("Dime el precio que quieres añadir");
            try {
                Mecanico mecanico = new Mecanico(Cliente.get(VistaTexto.DNI_EJEMPLO), Vehiculo.get(VistaTexto.MATRICULA_DEFECTO), LocalDate.now());
                mecanico.anadirPrecioMaterial(precioMaterial);
                precioCorrecto = true;
            } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
                System.out.println(e.getMessage());
            }
        } while (!precioCorrecto);
        return precioMaterial;
    }

    public LocalDate leerFechaCierre() {
        LocalDate fechaCierre;
        boolean fechaCierreCorrecta = false;
        do {
            fechaCierre = Consola.leerFecha("Dime la fecha de cierre: ");
            try {
                Revision revision = new Revision(Cliente.get(VistaTexto.DNI_EJEMPLO), Vehiculo.get(VistaTexto.MATRICULA_DEFECTO), LocalDate.of(1990, 1, 1));
                revision.cerrar(fechaCierre);
            } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (!fechaCierreCorrecta);
        return fechaCierre;
    }
}
