package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos implements IVehiculos {

    private static final String FICHERO_VEHICULOS = String.format("%s%s%s", "ficheros.xml", File.separator, "vehiculos.xml");
    private static final String RAIZ = "vehiculos";
    private static final String VEHICULO = "vehiculo";
    private static final String MARCA = "marca";
    private static final String MODELO = "modelo";
    private static final String MATRICULA = "matricula";
    private final List<Vehiculo> coleccionVehiculos;
    private static Vehiculos instancia;

    public Vehiculos() {
        coleccionVehiculos = new ArrayList<>();
    }

    public static Vehiculos getInstancia() {
        if (instancia == null) {
            instancia = new Vehiculos();
        }
        return instancia;
    }

    @Override
    public void comenzar() {
        Document documentoXml = (Document) UtilidadesXml.leerDocumentoXml(FICHERO_VEHICULOS);
        System.out.println("Fichero leido correctamente.");
        procesarDocumentoXml(documentoXml);
    }

    private void procesarDocumentoXml(Document documentoXml) {
        Objects.requireNonNull(documentoXml, "No se puede procesar un documento nulo.");
        NodeList listaVehiculos = documentoXml.getElementsByTagName(VEHICULO);
        for (int i = 0; i < listaVehiculos.getLength(); i++) {
            Element elementoVehiculo = (Element) listaVehiculos.item(i);
            try {
                insertar(getVehiculo(elementoVehiculo));
            } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
                System.out.printf("Error: no se puede procesar el vehiculo numero " + i + " , " + e.getMessage().toLowerCase());
            }
        }
    }

    public Vehiculo getVehiculo(org.w3c.dom.Element elemento) {
        Objects.requireNonNull(elemento, "El elemento no puede ser nulo.");
        String marca = elemento.getAttribute(MARCA);
        String modelo = elemento.getAttribute(MODELO);
        String matricula = elemento.getAttribute(MATRICULA);
        return new Vehiculo(marca, modelo, matricula);
    }

    @Override
    public void terminar() {
        Document documentoXml = crearDocumentoXml();
        if (documentoXml != null) {
            documentoXml.appendChild(documentoXml.createElement(RAIZ));
            for (Vehiculo vehiculo : coleccionVehiculos) {
                Element elementoVehiculo = getElemento(documentoXml, vehiculo);
                documentoXml.getDocumentElement().appendChild(elementoVehiculo);
            }
        }
        UtilidadesXml.escribirDocumentoXml((Document) documentoXml, FICHERO_VEHICULOS);
    }

    private Document crearDocumentoXml() {
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if (constructor != null) {
            documentoXml = constructor.newDocument();
        }
        return documentoXml;
    }

    private Element getElemento(Document documentoXml, Vehiculo vehiculo) {
        Objects.requireNonNull(documentoXml, "No se puede procesar un documento nulo.");
        Objects.requireNonNull(vehiculo, "El vehiculo no puede ser nulo.");
        Element elementoVehiculo = documentoXml.createElement(VEHICULO);
        elementoVehiculo.setAttribute(MARCA, vehiculo.marca());
        elementoVehiculo.setAttribute(MODELO, vehiculo.modelo());
        elementoVehiculo.setAttribute(MATRICULA, vehiculo.matricula());
        return elementoVehiculo;
    }

    @Override
    public List<Vehiculo> get() {
        return new ArrayList<>(coleccionVehiculos);
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        if (coleccionVehiculos.contains(vehiculo)) {
            throw new OperationNotSupportedException("Ya existe un vehículo con esa matrícula.");
        }
        coleccionVehiculos.add(vehiculo);
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");
        int indice = coleccionVehiculos.indexOf(vehiculo);
        return (indice == -1) ? null : coleccionVehiculos.get(indice);
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehículo nulo.");
        if (!coleccionVehiculos.contains(vehiculo)) {
            throw new OperationNotSupportedException("No existe ningún vehículo con esa matrícula.");
        }
        coleccionVehiculos.remove(vehiculo);
    }

    public org.bson.Document getDocumento(Vehiculo vehiculo) {
        return null;
    }
}
