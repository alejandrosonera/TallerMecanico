package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.vista.texto.VistaTexto;

public class Main {
    public static void main(String[] args) {
        VistaTexto vista = new VistaTexto();
        ModeloCascada modelo = new ModeloCascada();
        Controlador controlador = new Controlador(modelo, vista);
        controlador.comenzar();
    }
}
