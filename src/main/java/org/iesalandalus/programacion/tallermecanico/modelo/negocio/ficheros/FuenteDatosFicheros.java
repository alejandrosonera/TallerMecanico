package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public class FuenteDatosFicheros {

    public IClientes crearClientes() {
        return new IClientes() {
            @Override
            public List<Cliente> get() {
                return null;
            }

            @Override
            public void insertar(Cliente cliente) throws OperationNotSupportedException {

            }

            @Override
            public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
                return false;
            }

            @Override
            public Cliente buscar(Cliente cliente) {
                return null;
            }

            @Override
            public void borrar(Cliente cliente) throws OperationNotSupportedException {

            }
        };
    }
}
