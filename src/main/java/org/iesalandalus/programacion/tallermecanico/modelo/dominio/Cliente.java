package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;

public class Cliente {
    private String ER_NOMBRE = "\\b[A-Z][a-z]*\\b";
    private String ER_DNI = "\\d{8}[a-z]+";
    private String ER_TELEFONO = "[67]\\d{8}";
    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String dni, String telefono, String nombre) {
        setDni(dni);
        setTelefono(telefono);
        setNombre(nombre);
    }
    public Cliente(Cliente cliente) {
        dni = cliente.dni;
        nombre = cliente.nombre;
        telefono = cliente.telefono;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        if (!nombre.matches(ER_NOMBRE)) {
            throw new IllegalArgumentException("El nombre no es valido.");
        }
    }
    public String getDni() {
        return dni;
    }
    private void setDni(String dni) {
        Objects.requireNonNull(dni, "El dni no puede ser nulo");
        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("El dni no es valido.");
        }
    }
    private boolean comprobarLetraDni(String dni) {
        char[] letraDni = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        int numeroDni = Integer.parseInt(dni.substring(dni.length() - 1));
        int indiceLetraDni = numeroDni % 23;
        char letra = dni.charAt(dni.length() - 1);
        return (letra == letraDni[indiceLetraDni]) ? true : false;

    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono, "El telefono no puede ser nulo.");
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("El telefono no es valido.");
        }
    }
    public static Cliente get(String dni) {

    }



}
