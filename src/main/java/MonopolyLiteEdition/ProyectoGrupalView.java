package MonopolyLiteEdition;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProyectoGrupalView extends Application {
    private final ProyectoGrupalController controller = new ProyectoGrupalController();
    private GridPane tableroGrid;
    private Label labelJugador;
    private Label labelSaldo;
    private Label labelCasilla;
    private Label labelResultadoDado;
    private Label labelAccion;
    private Button botonDado;
    private Button botonComprar;
    private Button botonTerminarTurno;
    private Map<Integer, StackPane> casillaMap = new HashMap<>();
    private List<Jugador> jugadores;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Monopoly Lite Edition");

        // Inicia el juego con 2 jugadores
        jugadores = controller.iniciarJuego(2);

        // Panel principal
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // Título
        Label titulo = new Label("MONOPOLY LITE EDITION");
        titulo.getStyleClass().add("titulo");
        root.getChildren().add(titulo);

        // Tablero visual (GridPane 7x7)
        tableroGrid = crearTablero();
        root.getChildren().add(tableroGrid);

        // Panel de controles y estado
        HBox panelControl = crearPanelControl();
        root.getChildren().add(panelControl);

        Scene scene = new Scene(root, 1000, 900);
        scene.getStylesheets().add(
            getClass().getResource("/css/style.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.show();

        // Actualizar UI inicial
        actualizarUI();
    }

    private GridPane crearTablero() {
        GridPane grid = new GridPane();
        grid.setStyle("-fx-border-color: #000; -fx-border-width: 2;");
        grid.setHgap(0);
        grid.setVgap(0);

        List<Casilla> casillas = controller.getCasillasDelTablero();

        // Distribuir casillas en el perímetro (7x7 grid, bordes solo)
        int indice = 0;

        // Fila 0 (arriba, izq→der): casillas 0-4
        for (int col = 0; col < 5; col++) {
            if (indice < casillas.size()) {
                StackPane celda = crearCeldaTablero(casillas.get(indice), indice);
                grid.add(celda, col, 0);
                casillaMap.put(indice, celda);
                indice++;
            }
        }

        // Columna 6 (derecha, arr→ab): casillas 5-9
        for (int row = 0; row < 5; row++) {
            if (indice < casillas.size()) {
                StackPane celda = crearCeldaTablero(casillas.get(indice), indice);
                grid.add(celda, 6, row);
                casillaMap.put(indice, celda);
                indice++;
            }
        }

        // Fila 6 (abajo, der→izq): casillas 10-14
        for (int col = 6; col >= 2; col--) {
            if (indice < casillas.size()) {
                StackPane celda = crearCeldaTablero(casillas.get(indice), indice);
                grid.add(celda, col, 6);
                casillaMap.put(indice, celda);
                indice++;
            }
        }

        // Columna 0 (izquierda, ab→arr): casillas 15-19
        for (int row = 6; row >= 2; row--) {
            if (indice < casillas.size()) {
                StackPane celda = crearCeldaTablero(casillas.get(indice), indice);
                grid.add(celda, 0, row);
                casillaMap.put(indice, celda);
                indice++;
            }
        }

        // Centro (panel de información del turno)
        VBox panelCentral = new VBox(5);
        panelCentral.getStyleClass().add("panel-central");
        panelCentral.setPadding(new Insets(10));
        panelCentral.setAlignment(Pos.CENTER);
        panelCentral.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #dee2e6; -fx-border-width: 1;");

        labelJugador = new Label("Turno: Rojo");
        labelJugador.getStyleClass().add("label-jugador");
        labelSaldo = new Label("Saldo: $1500");
        labelSaldo.getStyleClass().add("label-saldo");
        labelCasilla = new Label("Casilla: Salida");
        labelAccion = new Label("Presiona 'Lanzar Dado'");
        labelAccion.getStyleClass().add("label-accion");
        labelAccion.setWrapText(true);

        panelCentral.getChildren().addAll(labelJugador, labelSaldo, labelCasilla, labelAccion);

        GridPane.setColumnSpan(panelCentral, 5);
        GridPane.setRowSpan(panelCentral, 5);
        grid.add(panelCentral, 1, 1);

        return grid;
    }

    private StackPane crearCeldaTablero(Casilla casilla, int indice) {
        StackPane celda = new StackPane();
        celda.getStyleClass().add("casilla");
        celda.setPrefSize(70, 70);

        // Determinar color de fondo según tipo de casilla
        String estilo = obtenerEstiloCasilla(casilla);
        celda.getStyleClass().add(estilo);

        // Nombre corto de la casilla
        String nombreCorto = casilla.getNombre();
        if (nombreCorto.length() > 10) {
            nombreCorto = nombreCorto.substring(0, 10) + "...";
        }
        Label labelNombre = new Label(nombreCorto);
        labelNombre.setStyle("-fx-font-size: 10px; -fx-text-alignment: center;");

        // Precio si aplica
        VBox contenido = new VBox(2);
        contenido.setAlignment(Pos.TOP_CENTER);
        contenido.setPadding(new Insets(2));

        Label labelPrecio = new Label("");
        if (casilla instanceof Propiedad) {
            Propiedad prop = (Propiedad) casilla;
            labelPrecio.setText("$" + prop.getPrecioCompra());
            labelPrecio.setStyle("-fx-font-size: 8px;");
        } else if (casilla instanceof Transporte) {
            Transporte transp = (Transporte) casilla;
            labelPrecio.setText("$" + transp.getPrecioCompra());
            labelPrecio.setStyle("-fx-font-size: 8px;");
        }

        contenido.getChildren().addAll(labelNombre, labelPrecio);

        // HBox para los tokens de jugadores (abajo)
        HBox boxTokens = new HBox(2);
        boxTokens.setAlignment(Pos.BOTTOM_CENTER);
        boxTokens.setPadding(new Insets(2, 0, 2, 0));

        StackPane contenedorTokens = new StackPane();
        contenedorTokens.getChildren().addAll(contenido, boxTokens);
        StackPane.setAlignment(boxTokens, Pos.BOTTOM_CENTER);

        celda.getChildren().add(contenedorTokens);
        celda.setUserData(boxTokens); // Guardar referencia al HBox de tokens

        return celda;
    }

    private String obtenerEstiloCasilla(Casilla casilla) {
        if (casilla instanceof Propiedad) return "casilla-propiedad";
        if (casilla instanceof Transporte) return "casilla-transporte";
        if (casilla instanceof Suerte) return "casilla-suerte";
        if (casilla instanceof Carcel) return "casilla-carcel";
        if (casilla instanceof Impuesto) return "casilla-impuesto";
        if (casilla instanceof Salida) return "casilla-salida";
        return "casilla";
    }

    private HBox crearPanelControl() {
        HBox panel = new HBox(10);
        panel.setPadding(new Insets(10));
        panel.setAlignment(Pos.CENTER_LEFT);

        // Botones de acción
        botonDado = new Button("Lanzar dado");
        botonDado.getStyleClass().add("boton-turno");
        botonDado.setOnAction(e -> ejecutarLanzarDado());

        botonComprar = new Button("Comprar");
        botonComprar.getStyleClass().add("boton-comprar");
        botonComprar.setVisible(false);
        botonComprar.setOnAction(e -> ejecutarComprar());

        botonTerminarTurno = new Button("Terminar turno");
        botonTerminarTurno.getStyleClass().add("boton-turno");
        botonTerminarTurno.setDisable(true);
        botonTerminarTurno.setOnAction(e -> ejecutarTerminarTurno());

        labelResultadoDado = new Label("🎲");
        labelResultadoDado.setStyle("-fx-font-size: 24px;");

        panel.getChildren().addAll(botonDado, labelResultadoDado, botonComprar, botonTerminarTurno);
        return panel;
    }

    private void ejecutarLanzarDado() {
        ProyectoGrupalController.TurnoResultado resultado = controller.lanzarDado();

        // Mostrar resultado del dado
        labelResultadoDado.setText("🎲 " + resultado.valorDado);

        // Mover token a la nueva casilla
        Jugador jugadorActual = controller.getJugadorActual();
        if (jugadorActual != null) {
            actualizarTokensEnTablero(jugadorActual);
        }

        // Mostrar descripción de acción
        labelCasilla.setText("Casilla: " + resultado.nombreCasilla);
        labelAccion.setText(resultado.descripcionAccion);

        // Habilitar/deshabilitar botón de compra
        botonComprar.setVisible(resultado.puedoComprar);

        // Deshabilitar botón de dados, habilitar terminar turno
        botonDado.setDisable(true);
        botonTerminarTurno.setDisable(false);

        // Actualizar saldo mostrado
        labelSaldo.setText("Saldo: $" + jugadorActual.getSaldo());
    }

    private void ejecutarComprar() {
        String resultado = controller.comprarPropiedad();
        labelAccion.setText(resultado);
        botonComprar.setVisible(false);

        // Actualizar saldo
        Jugador jugadorActual = controller.getJugadorActual();
        if (jugadorActual != null) {
            labelSaldo.setText("Saldo: $" + jugadorActual.getSaldo());
        }
    }

    private void ejecutarTerminarTurno() {
        Jugador siguiente = controller.terminarTurno();
        actualizarUI();
    }

    private void actualizarUI() {
        Jugador jugador = controller.getJugadorActual();
        if (jugador != null) {
            labelJugador.setText("Turno: " + jugador.getNombre());
            labelSaldo.setText("Saldo: $" + jugador.getSaldo());

            Casilla casilla = controller.getCasillasDelTablero().get(jugador.getPosicionActual());
            if (casilla != null) {
                labelCasilla.setText("Casilla: " + casilla.getNombre());
            }

            labelAccion.setText("Presiona 'Lanzar Dado'");
            labelResultadoDado.setText("🎲");

            // Habilitar lanzar dados, deshabilitar controles posteriores
            botonDado.setDisable(false);
            botonTerminarTurno.setDisable(true);
            botonComprar.setVisible(false);

            // Actualizar tokens en tablero
            actualizarTokensEnTablero(jugador);
        }
    }

    private void actualizarTokensEnTablero(Jugador jugadorEnTurno) {
        // Limpiar todos los tokens
        for (StackPane celda : casillaMap.values()) {
            HBox boxTokens = (HBox) celda.getUserData();
            if (boxTokens != null) {
                boxTokens.getChildren().clear();
            }
        }

        // Redibujar tokens de todos los jugadores
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador j = jugadores.get(i);
            int posicion = j.getPosicionActual();

            if (casillaMap.containsKey(posicion)) {
                StackPane celda = casillaMap.get(posicion);
                HBox boxTokens = (HBox) celda.getUserData();

                if (boxTokens != null) {
                    Circle token = new Circle(7);
                    token.getStyleClass().add("token-j" + (i + 1));
                    boxTokens.getChildren().add(token);
                }
            }
        }
    }
}



