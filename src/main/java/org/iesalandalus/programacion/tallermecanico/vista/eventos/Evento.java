package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;

public enum Evento {
    INSERTAR_CLIENTE,
    BUSCAR_CLIENTE,
    BORRAR_CLIENTE,
    LISTAR_CLIENTES,
    MODIFICAR_CLIENTE,
    INSERTAR_VEHICULO,
    BUSCAR_VEHICULO,
    BORRAR_VEHICULO,
    LISTAR_VEHICULOS,
    INSERTAR_REVISION,
    INSERTAR_MECANICO,
    BUSCAR_TRABAJO,
    BORRAR_TRABAJO,
    LISTAR_TRABAJOS,
    LISTAR_TRABAJOS_CLIENTE,
    LISTAR_TRABAJOS_VEHICULO,
    ANADIR_HORAS_TRABAJO,
    ANADIR_PRECIO_MATERIAL_TRABAJO,
    CERRAR_TRABAJO,
    SALIR,

    private int codigo;
    private String texto;
    static final Map<Integer, Evento> eventos;

    static {
        eventos = new HashMap<>();
        for (int i = 0; i < Evento.values().length; i++) {
            eventos.put(i, Evento.values()[i]);
        }
    }
    private Evento(int codigo, String texto) {
        this.texto = texto;
        esValido(codigo);
    }
    public static boolean esValido(int codigo) {
        if (!eventos.containsKey(codigo)) {
            throw new IllegalArgumentException("Código no válido.");
        }
        return true;
    }
    public static Evento get(int codigo) {
        return eventos.get(codigo);
    }
    public String toString() {
        return String.format ("%d-%s", this.codigo, this.texto);
    }
}
