package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import javax.naming.OperationNotSupportedException;
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

    private void anadirHoras() {
        Consola.mostrarCabecera("AÑADIR HORAS");
        try {
            controlador.anadirHoras(Consola.leerRevision(), Consola.leerHoras());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void anadirPrecioMaterialRevision() {
        Consola.mostrarCabecera("AÑADIR PRECIO MATERIAL REVISION");
        try {
            controlador.anadirPrecioMaterial(Consola.leerRevision(), Consola.leerHoras());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarRevisionesVehiculo() {
        Consola.mostrarCabecera("LISTAR REVISIONES DEL VEHICULO");
        List<Revision> revisionesVehiculos = controlador.getRevisiones(Consola.leerVehiculoMatricula());
        if (revisionesVehiculos.isEmpty()) {
            System.out.println("La lista de revisiones esta vacía");
        }
        System.out.println(revisionesVehiculos);
    }

    private void listarRevisionesCliente() {
        Consola.mostrarCabecera("LISTAR REVISIONES DEL CLIENTE");
        List<Revision> revisionesClientes = controlador.getRevisiones(Consola.leerClienteDni());
        if (revisionesClientes.isEmpty()) {
            System.out.println("La lista de revisiones esta vacia");
        }
        System.out.println(revisionesClientes);
    }

    private void modificarCliente() {
        Consola.mostrarCabecera("MODIFICAR CLIENTE");
        boolean modificado = true;
        try {
            modificado = controlador.modificar(Consola.leerClienteDni(), Consola.leerNuevoNombre(), Consola.leerNuevoTelefono());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        if (modificado) {
            System.out.println("El cliente se ha modificado con exito");
        }
    }

    private void listarRevisiones() {
        Consola.mostrarCabecera("LISTAR REVISIONES");
        List<Revision> revisiones = controlador.getRevisiones();
        if (revisiones.isEmpty()) {
            System.out.println("La lista de revisiones esta vacia");
        }
        System.out.println(revisiones);
    }

    private void insertarVehiculo() {
        Consola.mostrarCabecera("INSERTAR VEHICULO");
        try {
            controlador.insertar(Consola.leerVehiculo());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarRevision() {
        Consola.mostrarCabecera("INSERTAR REVISION");
        try {
            controlador.insertar(Consola.leerRevision());
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarVehiculos() {
        Consola.mostrarCabecera("LISTAR VEHICULOS");
        List<Vehiculo> listaVehivulos = controlador.getVehiculos();
        if(listaVehivulos.isEmpty()){
            System.out.println("La lista de vehiculos ");
        } else{
            System.out.println(listaVehivulos);
        }
    }

    private void cerrarRevision() {
        Consola.mostrarCabecera("CERRAR REVISION");
        try {
            controlador.cerrar(Consola.leerRevision(), Consola.leerFechaCierre());
            System.out.println("Revision cerrada correctamente");
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarVehiculo() {
        Consola.mostrarCabecera("BUSCAR VEHICULO");
        try {
            Vehiculo vehiculo = controlador.buscar(Consola.leerVehiculoMatricula());
            if (vehiculo == null) {
                System.out.println("El vehiculo con la matricula introducida no existe");
            } else {
                System.out.println("Se ha encontrado un vehiculo con la matricula: " + vehiculo.matricula());
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarRevision() {
        Consola.mostrarCabecera("BUSCAR REVISION");
        try {
            Revision revision = controlador.buscar(Consola.leerRevision());
            if (revision == null) {
                System.out.println("La revision introducida no existe");
            } else {
                System.out.println("Se ha encontrado una revision: " + revision);
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarVehiculo() {
        Consola.mostrarCabecera("BORRAR VEHICULO");
        try {
            controlador.borrar(Consola.leerVehiculoMatricula());
            System.out.println("Vehiculo borrado correctamente");
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarRevision() {
        Consola.mostrarCabecera("BORRAR REVISION");
        try {
            controlador.borrar(Consola.leerRevision());
            System.out.println("Revision borrada correctamente");
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void buscarCliente() {
        Consola.mostrarCabecera("BUSCAR CLIENTE");
        Cliente cliente = controlador.buscar(Consola.leerClienteDni());
        if (cliente == null) {
            System.out.println("El cliente con el dni introducido no existe");
        } else {
            System.out.println("Se ha encontrado un cliente con el dni: " + cliente.getDni());
        }
    }

    private void borrarCliente() {
        Consola.mostrarCabecera("BORRAR VEHICULO");
        try {
            controlador.borrar(Consola.leerClienteDni());
            System.out.println("Cliente borrado con exito");
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertarCliente() {
        Consola.mostrarCabecera("INSERTAR CLIENTE");
        try {
            controlador.insertar(Consola.leerClienteDni());
            System.out.println("Cliente insertado correctamente");
        } catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void salir() {
        Consola.mostrarCabecera("SALIR");
        controlador.terminar();
    }
}
