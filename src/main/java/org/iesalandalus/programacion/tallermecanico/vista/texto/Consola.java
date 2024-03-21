package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.regex.Pattern;

public class Consola {
    private static final String CADENA_FORMATO_FECHA ="dd/MM/yyyy";

    static void mostrarCabecera(String mensaje) {
        System.out.println(/*ESPACIO*/);
        System.out.println(mensaje);
        System.out.println("_".repeat(mensaje.length()));
    }
    static void mostrarMenu() {
        mostrarCabecera("----OPCIONES----");
        for (int i = 0; i < Evento.values().length; i++) {
            System.out.println(Evento.values()[i]);
        }
    }
    static float leerReal(String mensaje) {
        System.out.println(mensaje);
        return Entrada.real();
    }
    static int leerEntero(String mensaje) {
        System.out.println(mensaje);
        return Entrada.entero();
    }
    static String leerCadena(String mensaje) {
        System.out.println(mensaje);
        return Entrada.cadena();
    }
    static LocalDate leerFecha(String mensaje) {
        Pattern patron = Pattern.compile(CADENA_FORMATO_FECHA);
        DateTimeFormatter comparador = DateTimeFormatter.ofPattern(String.valueOf(patron));
        LocalDate fecha = null;
        try {
            String cadenaFecha = leerCadena(mensaje);
            fecha = LocalDate.parse(cadenaFecha, comparador);
        } catch (DateTimeParseException e) {
            System.out.println("La fecha introducida no es valida.");
        }
        return fecha;
    }
    static Evento elegirOpcion() {
        int numeroOpcion;
        while (true) {
            System.out.print("Introduce el numero de la opción: ");
            numeroOpcion = Entrada.entero();
            if (Evento.esValido(numeroOpcion)) {
                return Evento.get(numeroOpcion);
            } else {
                System.out.println("Opcion no valida. Vuelva a intentarlo");
            }
        }
    }
}
