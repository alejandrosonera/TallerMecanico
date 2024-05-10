package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;

public class LanzadorVentanaPrincipal extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controlador ventanaPrincipal = Controladores.get("/vistas/ventanaPrincipal.fxml", "Taller Mecanico", null);
        ventanaPrincipal.addHojaEstilos("/estilos/estilo.css");
        ventanaPrincipal.getEscenario().setOnCloseRequest(e -> salir(e, ventanaPrincipal.getEscenario()));
        VistaGrafica.getInstancia().setVentanaPrincipal(ventanaPrincipal);
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.LISTAR_VEHICULOS);
        ventanaPrincipal.getEscenario().show();
        centrarVentana(ventanaPrincipal.getEscenario());
    }

    private void centrarVentana(Stage escenario) {

    }

    public static void comenzar() {
        launch(LanzadorVentanaPrincipal.class);
    }

    private void salir(WindowEvent e, Stage escenario) {
    }
}
