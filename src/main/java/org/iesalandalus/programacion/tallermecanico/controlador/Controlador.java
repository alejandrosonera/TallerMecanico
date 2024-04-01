package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.vista.texto.VistaTexto;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Controlador implements IControlador {
    private VistaTexto vista;
    private ModeloCascada modelo;

    public Controlador(ModeloCascada modelo, VistaTexto vista) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        Objects.requireNonNull(vista, "La vista no puede ser nula");
        this.vista = vista;
        this.modelo = modelo;
    }
    @Override
    public void comenzar() throws OperationNotSupportedException {
        modelo.comenzar();
        vista.comenzar();
    }
    @Override
    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }
   @Override
    public void actualizar(Evento evento) throws OperationNotSupportedException {
       Objects.requireNonNull(evento, "El evento no puede ser nulo.");
       String resultado = "";
       boolean exito;
       try {
           switch (evento) {
               case INSERTAR_CLIENTE -> {
                   modelo.insertar(vista.leerCliente());
                   resultado = "Se ha insertado el cliente correctamente.";
               }
               case BUSCAR_CLIENTE -> {
                   vista.mostrarCliente(modelo.buscar(vista.leerCliente()));
                   resultado = "Se ha encontrado el cliente.";
               }
               case BORRAR_CLIENTE -> {
                   modelo.borrar(vista.leerCliente());
                   resultado = "Se ha borrado el cliente.";
               }
               case LISTAR_CLIENTES -> vista.mostrarClientes(modelo.getClientes());

               case MODIFICAR_CLIENTE -> {
                   boolean modificado = modelo.modificar(vista.leerCliente(), vista.leerNuevoNombre(), vista.leerNuevoTelefono());
                   if (modificado) {
                       resultado = "Se ha modificado el cliente.";
                   } else {
                       resultado = "El cliente no se ha modificado.";
                   }
               }
               case INSERTAR_VEHICULO -> {
                   modelo.insertar(vista.leerVehiculo());
                   resultado = "Se ha insertado el vehículo correctamente.";
               }
               case BUSCAR_VEHICULO -> {
                   vista.mostrarVehiculo(modelo.buscar(vista.leerVehiculo()));
                   resultado = "Se ha encontrado el vehículo.";
               }
               case BORRAR_VEHICULO -> {
                   modelo.borrar(vista.leerVehiculo());
                   resultado = "Se ha borrado el vehiculo.";
               }
               case LISTAR_VEHICULOS -> vista.mostrarVehiculos(modelo.getVehiculos());

               case INSERTAR_REVISION -> {
                   modelo.insertar(vista.leerRevision());
                   resultado = "Se ha insertado la revisión correctamente.";
               }
               case INSERTAR_MECANICO -> {
                   modelo.insertar(vista.leerMecanico());
                   resultado = "Se ha insertado el trabajo mecánico correctamente.";
               }
               case BUSCAR_TRABAJO -> {
                   modelo.buscar(vista.leerMecanico());
                   resultado = "Se ha encontrado el trabajo mecanico.";
               }
               case BORRAR_TRABAJO -> {
                   modelo.borrar(modelo.buscar(vista.leerTrabajoVehiculo()));
                   resultado = "Se ha borrado el trabajo.";
               }
               case LISTAR_TRABAJOS -> vista.mostrarTrabajos(modelo.getTrabajos());

               case LISTAR_TRABAJOS_CLIENTE -> vista.mostrarTrabajos(modelo.getTrabajos(vista.leerCliente()));

               case LISTAR_TRABAJOS_VEHICULO -> vista.mostrarTrabajos(modelo.getTrabajos(vista.leerVehiculo()));

               case ANADIR_HORAS_TRABAJO -> {
                   modelo.anadirHoras(modelo.buscar(vista.leerTrabajoVehiculo()), vista.leerHoras());
                   resultado = "Se han añadido las horas al trabajo.";
               }
               case ANADIR_PRECIO_MATERIAL_TRABAJO -> {
                   modelo.anadirPrecioMaterial(modelo.buscar(vista.leerMecanico()), vista.leerPrecioMaterial());
                   resultado = "Se ha añadido el precio material al trabajo.";
               }
               case CERRAR_TRABAJO -> {
                   modelo.cerrar(modelo.buscar(vista.leerTrabajoVehiculo()), vista.leerFechaCierre());
                   resultado = "Se ha cerrado el trabajo correctamente.";
               }
               case SALIR -> terminar();
           }
           exito = true;
       } catch (Exception e) {
           resultado = e.getMessage();
           exito = false;
       }
       vista.notificarResultado(evento,resultado,exito);
   }

}
