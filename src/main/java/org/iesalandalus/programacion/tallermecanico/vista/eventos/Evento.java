package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;

public enum Evento {
    INSERTAR_CLIENTE(1, "Insertar Cliente"),
    BUSCAR_CLIENTE(2, "Buscar Cliente"),
    BORRAR_CLIENTE(3, "Borrar Clientes"),
    LISTAR_CLIENTES(4, "Listar Clientes"),
    MODIFICAR_CLIENTE(5, "Modificar cliente"),
    INSERTAR_VEHICULO(6, "Insertar Vehiculo"),
    BUSCAR_VEHICULO(7, "Buscar vehiculo"),
    BORRAR_VEHICULO(8, "Borrar vehiculo"),
    LISTAR_VEHICULOS(9, "Listar Vehiculos"),
    INSERTAR_REVISION(10, "Insertar Revision"),
    INSERTAR_MECANICO(11, "Insertar Mecanico"),
    BUSCAR_TRABAJO(12, "Buscar trabajo"),
    BORRAR_TRABAJO(13, "Borrar trabajo"),
    LISTAR_TRABAJOS(14, "Listar Trabajos"),
    LISTAR_TRABAJOS_CLIENTE(15, "Listar Trabajos Clientes"),
    LISTAR_TRABAJOS_VEHICULO(16, "Listar Trabajos Vehiculos"),
    ANADIR_HORAS_TRABAJO(17, "Añadir horas al trabajo"),
    ANADIR_PRECIO_MATERIAL_TRABAJO(18, "Añadir precio material al trabajo"),
    CERRAR_TRABAJO(19, "Cerrar Trabajo"),
    SALIR(20, "Salir");

    private int codigo;
    private String texto;
    static final Map<Integer, Evento> eventos;

    static {
        eventos = new HashMap<>();
        for (Evento opcion : values()) {
            eventos.put(opcion.codigo, opcion);
        }
    }
    Evento(int codigo, String texto) {
        this.texto = texto;
        this.codigo = codigo;
    }
    public static boolean esValido(int numeroOpcion) {
        return eventos.containsKey(numeroOpcion);
    }
    public static Evento get(int codigo) {
        if (!esValido(codigo)) {
            throw new IllegalArgumentException("Opcion no valida.");
        }
        return eventos.get(codigo);
    }
    public String toString() {
        return String.format ("%d-%s", this.codigo, this.texto);
    }
}
