package MonopolyLiteEdition;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProyectoGrupalView extends Application {
    private final ProyectoGrupalController controller = new ProyectoGrupalController();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Monopoly Lite Edition");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        VBox header = new VBox();
        header.setAlignment(Pos.CENTER);

        Label titulo = new Label("MONOPOLY LITE EDITION");
        titulo.getStyleClass().add("titulo");

        Label subtitulo = new Label("Interfaz Gráfica - Prueba MVC");
        subtitulo.getStyleClass().add("subtitulo");

        header.getChildren().addAll(titulo, subtitulo);

        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);
        center.setSpacing(15);
        center.setPrefHeight(400);
        center.getStyleClass().add("tablero-placeholder");

        Label labelEstado = new Label(controller.obtenerEstado());
        labelEstado.getStyleClass().add("label-estado");

        Button botonDado = new Button("Lanzar dado");
        botonDado.getStyleClass().add("boton-accion");
        botonDado.setOnAction(e -> {
            try {
                int resultado = controller.lanzarDado();
                labelEstado.setText("Dado: " + resultado);
            } catch (Exception ex) {
                labelEstado.setText("Error: " + ex.getMessage());
            }
        });

        center.getChildren().addAll(labelEstado, botonDado);

        root.setTop(header);
        root.setCenter(center);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(
            getClass().getResource("/css/style.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.show();
    }
}


