package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Consola {
    private Consola() {}

    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    static void mostraCabecera(String mensaje) {
        System.out.println(/*ESPACIO*/);
        System.out.println(mensaje);
        System.out.println("-".repeat(mensaje.length()));
    }

    static Evento elegirOpcion() {
        int numeroOpcion;
        while (true) {
            System.out.print("Introduce el número de la opción: ");
            numeroOpcion = Entrada.entero();
            if (Evento.esValido(numeroOpcion)) {
                return Evento.get(leerEntero("Elegir opción."));
            } else {
                System.out.println("Opción no válida. Por favor, vuelve a intentarlo.");
            }
        }
    }

    static void mostrarMenu() {
        mostraCabecera("OPCIONES");
        for (int i = 0; i < Evento.values().length; i++) {
            System.out.println(Evento.values()[i]);
        }
    }

    static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Entrada.entero();
    }

    static float leerReal(String mensaje) {
        System.out.print(mensaje);
        return Entrada.real();
    }

    static String leerCadena(String mensaje) {
        System.out.print(mensaje);
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
            System.out.println("La fecha introducida no es válida, inténtelo de nuevo.");
        }
        return fecha;
    }
}