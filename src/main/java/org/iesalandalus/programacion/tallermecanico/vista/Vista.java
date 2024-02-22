package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Objects;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "El controlador no puede ser nulo");
        this.controlador = controlador;
    }
    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
        } while (opcion != Opcion.SALIR);
    }
    public void terminar() {
        System.out.print("Hasta luego!!!");
    }
    private void ejecutar(Opcion opcion) {
        switch (opcion) {
            case SALIR -> salir();
            case INSERTAR_CLIENTE -> insertarCliente();
            case BORRAR_CLIENTE -> borrarCliente();
            case BUSCAR_CLIENTE -> buscarCliente();
            case BORRAR_REVISION -> borrarRevision();
            case BORRAR_VEHICULO -> borrarVehiculo();
            case BUSCAR_REVISION -> buscarRevision();
            case BUSCAR_VEHICULO -> buscarVehiculo();
            case CERRAR_REVISION -> cerrarRevision();
            case LISTAR_VEHICULOS -> listarVehiculos();
            case INSERTAR_REVISION -> insertarRevision();
            case INSERTAR_VEHICULO -> insertarVehiculo();
            case LISTAR_REVISIONES -> listarRevisiones();
            case MODIFICAR_CLIENTE -> modificarCliente();
            case ANADIR_HORAS_REVISION -> anadirHoras();
            case LISTAR_REVISIONES_CLIENTE -> listarRevisionesCliente();
            case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
            case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterialRevision();
            default -> throw new IllegalArgumentException("La opción no es valida");
        }
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
