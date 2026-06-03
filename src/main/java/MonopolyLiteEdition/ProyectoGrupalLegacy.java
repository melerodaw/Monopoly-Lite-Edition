// LEGACY — versión original conservada como referencia. No se usa en producción.
package MonopolyLiteEdition;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ProyectoGrupalLegacy extends Application {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Configurar ventana principal
        stage.setTitle("Monopoly Lite Edition");
        stage.setWidth(800);
        stage.setHeight(600);

        // Crear panel principal
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        // Crear título
        VBox header = new VBox();
        header.setAlignment(Pos.CENTER);
        header.setSpacing(10);

        Label titulo = new Label("MONOPOLY LITE EDITION");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 40));

        Label subtitulo = new Label("Interfaz Gráfica - Prueba");
        subtitulo.setFont(Font.font("Arial", 16));

        header.getChildren().addAll(titulo, subtitulo);

        // Crear área central (aquí irá el juego en el futuro)
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);
        center.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1;");
        center.setPrefHeight(400);

        Label placeholder = new Label("Aquí irá el tablero y la lógica del juego");
        placeholder.setFont(Font.font("Arial", 18));
        center.getChildren().add(placeholder);

        // Establecer layouts
        root.setTop(header);
        root.setCenter(center);

        // Crear escena y mostrar
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*
    // Código original comentado
    private static void mainAnterior() {

        scanner = new Scanner(System.in);

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   BIENVENIDO A MONOPOLY LITE EDITION   ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        // Cargar recursos JSON
        if (!cargarRecursos()) {
            System.out.println("Error al cargar los recursos del juego.");
            return;
        }

        // Crear juego
        juego = new Juego();

        // Agregar jugadores
        agregarJugadores();

        if (juego.getListaJugadores().size() < 2) {
            System.out.println("Se necesitan al menos 2 jugadores para jugar.");
            return;
        }

        // Iniciar partida
        System.out.println("\n¡COMIENZA LA PARTIDA!\n");
        juego.iniciarPartida();

        // Loop principal
        bucleJuego();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       ¡FIN DE LA PARTIDA!              ║");
        System.out.println("╚════════════════════════════════════════╝");
        scanner.close();

    }

    private static boolean cargarRecursos() {
        try {
            String baseDir = "src/main/resources/";
            Gson gson = new Gson();

            // Cargar tablero
            String tableroJson = new String(Files.readAllBytes(Paths.get(baseDir + "tablero.json")));
            JsonObject tableroObj = gson.fromJson(tableroJson, JsonObject.class);
            TableroManager manager = new TableroManager();
            manager.cargarTableroDesdeJson(tableroObj);
            tablero = manager.getTablero();
            System.out.println("✓ Tablero cargado");

            // Cargar cartas de suerte
            String cartasJson = new String(Files.readAllBytes(Paths.get(baseDir + "cartas_suerte.json")));
            JsonObject cartasObj = gson.fromJson(cartasJson, JsonObject.class);
            baraja = new Baraja();
            baraja.cargarDesdeJson(cartasObj);
            System.out.println("✓ Baraja de suerte cargada (" + baraja.getTamaño() + " cartas)");

            // Asignar baraja a casillas de suerte
            for (Casilla casilla : tablero.getCasillas()) {
                if (casilla instanceof Suerte) {
                    ((Suerte) casilla).setBaraja(baraja);
                }
            }

            // Cargar reglas
            String reglasJson = new String(Files.readAllBytes(Paths.get(baseDir + "reglas.json")));
            JsonObject reglasObj = gson.fromJson(reglasJson, JsonObject.class);
            reglas = new ReglasTirada();
            reglas.cargarDesdeJson(reglasObj);
            System.out.println("✓ Reglas cargadas");

            // Inicializar dado
            dado = new Dado();

            return true;
        } catch (IOException e) {
            System.out.println("Error al cargar archivos JSON: " + e.getMessage());
            return false;
        }
    }

    private static void agregarJugadores() {
        System.out.println("¿Cuántos jugadores participarán? (2-4): ");
        int numJugadores = 0;

        try {
            numJugadores = Integer.parseInt(scanner.nextLine());
            if (numJugadores < 2 || numJugadores > 4) {
                numJugadores = 2;
            }
        } catch (NumberFormatException e) {
            numJugadores = 2;
        }

        for (int i = 1; i <= numJugadores; i++) {
            System.out.println("Nombre del jugador " + i + ": ");
            String nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) {
                nombre = "Jugador " + i;
            }

            Jugador jugador = new Jugador(nombre, reglas.getDineroInicial(), 0, false, 0, new ArrayList<>());
            juego.agregarJugador(jugador);
            System.out.println("  ✓ " + nombre + " se unió con " + reglas.getDineroInicial() + " €");
        }
    }

    private static void bucleJuego() {
        int turnoCount = 0;
        int maxTurnos = 1000; // Límite de turnos para evitar loop infinito

        while (turnoCount < maxTurnos) {
            Jugador jugadorActual = juego.getJugadorActual();

            // Verificar si solo queda un jugador
            if (jugadoresActivos() <= 1) {
                Jugador ganador = juego.verificarGanador();
                if (ganador != null) {
                    System.out.println("\n¡" + ganador.getNombre() + " HA GANADO LA PARTIDA!");
                    System.out.println("   Saldo final: " + ganador.getSaldo() + " €");
                }
                return;
            }

            System.out.println("\n┌─ TURNO " + (turnoCount + 1) + " ─────────────────────────┐");
            System.out.println("│ Jugador: " + jugadorActual.getNombre());
            System.out.println("│ Posición: " + jugadorActual.getPosicionActual() + " | Saldo: €" + jugadorActual.getSaldo());
            System.out.println("└────────────────────────────────────┘");

            // Manejo de cárcel
            boolean jugadorEliminado = false;
            if (jugadorActual.getEstaEnCarcel()) {
                manejarCarcel(jugadorActual);
            } else {
                // Tirar dados
                int tirada = dado.generarAleatorio();
                System.out.println( jugadorActual.getNombre() + " tiró un " + tirada);

                // Avanzar posición
                int nuevaPosicion = tablero.calcularNuevaPosicion(jugadorActual.getPosicionActual(), tirada);
                jugadorActual.setPosicionActual(nuevaPosicion);

                // Executar acción de la casilla
                Casilla casilla = tablero.obtenerCasillas(nuevaPosicion);
                if (casilla != null) {
                    System.out.println("Llegó a: " + casilla.getNombre());

                    if (casilla instanceof Propiedad) {
                        jugadorEliminado = resolverPropiedad(jugadorActual, (Propiedad) casilla);
                    } else if (casilla instanceof Transporte) {
                        jugadorEliminado = resolverTransporte(jugadorActual, (Transporte) casilla);
                    } else {
                        casilla.ejecutarAccion(jugadorActual);
                    }
                }

                // Verificar si saldo negativo = eliminado
                if (!jugadorEliminado && jugadorActual.getSaldo() < 0) {
                    System.out.println("  ✗ " + jugadorActual.getNombre() + " quedó con saldo negativo. ¡BANCARROTA! Eliminado del juego.");
                    declararBancarrota(jugadorActual);
                    jugadorEliminado = true;
                }
            }

            if (!jugadorEliminado) {
                ofrecerConstruccion(jugadorActual);
            }

            // Pausa entre turnos
            System.out.println("Presiona ENTER para continuar...");
            scanner.nextLine();

            // Siguiente turno
            if (!jugadorEliminado) {
                juego.siguienteTurno();
            } else if (juego.getTurnoActual() >= juego.getListaJugadores().size()) {
                juego.setTurnoActual(0);
            }
            turnoCount++;
        }

        System.out.println("\nSe alcanzó el límite de turnos.");
    }

    private static void manejarCarcel(Jugador jugador) {
        jugador.setTurnosRestantesCarcel(jugador.getTurnosRestantesCarcel() - 1);
        System.out.println(jugador.getNombre() + " está en cárcel. Turnos restantes: " + Math.max(0, jugador.getTurnosRestantesCarcel()));

        if (jugador.getTurnosRestantesCarcel() <= 0) {
            jugador.setEstaEnCarcel(false);
            System.out.println("✓ ¡" + jugador.getNombre() + " salió de la cárcel!");
        }
    }

    private static int jugadoresActivos() {
        return juego.getListaJugadores().size();
    }

    private static boolean resolverPropiedad(Jugador jugadorActual, Propiedad propiedad) {
        Jugador propietario = propiedad.getPropietario();

        if (propietario == null) {
            System.out.println("  ┌─ PROPIEDAD DISPONIBLE ──────────────┐");
            System.out.println("  │ " + propiedad.getNombre() + " — Precio: €" + propiedad.getPrecioCompra());
            System.out.println("  │ Tu saldo: €" + jugadorActual.getSaldo());
            System.out.println("  └────────────────────────────────────┘");
            System.out.print("  ¿Deseas comprar? (S/N): ");

            if (leerSiNo()) {
                if (jugadorActual.getSaldo() >= propiedad.getPrecioCompra()) {
                    jugadorActual.setSaldo(jugadorActual.getSaldo() - propiedad.getPrecioCompra());
                    propiedad.setPropietario(jugadorActual);
                    jugadorActual.agregarPropiedad(propiedad);
                    System.out.println("  ✓ Compraste " + propiedad.getNombre() + " por €" + propiedad.getPrecioCompra() + ".");
                } else {
                    System.out.println("  ✗ No tienes saldo suficiente para comprar " + propiedad.getNombre() + ".");
                }
            } else {
                System.out.println("  ✗ Pasaste la oportunidad.");
            }
            return false;
        }

        if (propietario == jugadorActual) {
            return false;
        }

        int renta = propiedad.getRentaActual();
        System.out.println("  ➜ " + propiedad.getNombre() + " pertenece al " + propietario.getNombre());
        if (jugadorActual.getSaldo() < renta) {
            System.out.println("    ✗ " + jugadorActual.getNombre() + " no puede pagar. ¡BANCARROTA! Eliminado del juego.");
            declararBancarrota(jugadorActual);
            return true;
        }

        jugadorActual.setSaldo(jugadorActual.getSaldo() - renta);
        propietario.setSaldo(propietario.getSaldo() + renta);
        System.out.println("    ✗ " + jugadorActual.getNombre() + " paga €" + renta + " de alquiler a " + propietario.getNombre());
        return false;
    }

    private static boolean resolverTransporte(Jugador jugadorActual, Transporte transporte) {
        Jugador propietario = transporte.getPropietario();

        if (propietario == null) {
            System.out.println("  ┌─ PROPIEDAD DISPONIBLE ──────────────┐");
            System.out.println("  │ " + transporte.getNombre() + " — Precio: €" + transporte.getPrecioCompra());
            System.out.println("  │ Tu saldo: €" + jugadorActual.getSaldo());
            System.out.println("  └────────────────────────────────────┘");
            System.out.print("  ¿Deseas comprar? (S/N): ");

            if (leerSiNo()) {
                if (jugadorActual.getSaldo() >= transporte.getPrecioCompra()) {
                    jugadorActual.setSaldo(jugadorActual.getSaldo() - transporte.getPrecioCompra());
                    transporte.setPropietario(jugadorActual);
                    jugadorActual.agregarPropiedad(transporte);
                    System.out.println("  ✓ Compraste " + transporte.getNombre() + " por €" + transporte.getPrecioCompra() + ".");
                } else {
                    System.out.println("  ✗ No tienes saldo suficiente para comprar " + transporte.getNombre() + ".");
                }
            } else {
                System.out.println("  ✗ Pasaste la oportunidad.");
            }
            return false;
        }

        if (propietario == jugadorActual) {
            return false;
        }

        int renta = transporte.getAlquilerBase();
        System.out.println("  ➜ " + transporte.getNombre() + " pertenece al " + propietario.getNombre());
        if (jugadorActual.getSaldo() < renta) {
            System.out.println("    ✗ " + jugadorActual.getNombre() + " no puede pagar. ¡BANCARROTA! Eliminado del juego.");
            declararBancarrota(jugadorActual);
            return true;
        }

        jugadorActual.setSaldo(jugadorActual.getSaldo() - renta);
        propietario.setSaldo(propietario.getSaldo() + renta);
        System.out.println("    ✗ " + jugadorActual.getNombre() + " paga €" + renta + " de alquiler a " + propietario.getNombre());
        return false;
    }

    private static boolean leerSiNo() {
        String entrada = scanner.nextLine().trim().toUpperCase();
        return "S".equals(entrada) || "SI".equals(entrada);
    }

    private static void ofrecerConstruccion(Jugador jugadorActual) {
        List<Propiedad> construibles = new ArrayList<>();
        for (Casilla casilla : jugadorActual.getPropiedadesCompradas()) {
            if (casilla instanceof Propiedad) {
                Propiedad p = (Propiedad) casilla;
                if (p.puedeConstruir() && poseeGrupoCompleto(jugadorActual, p.getGrupoColor())) {
                    construibles.add(p);
                }
            }
        }

        if (construibles.isEmpty()) {
            return;
        }

        System.out.print("  ¿Deseas construir casas? (S/N): ");
        if (!leerSiNo()) {
            return;
        }

        System.out.println("  ┌─ TUS PROPIEDADES ───────────────────┐");
        for (int i = 0; i < construibles.size(); i++) {
            Propiedad p = construibles.get(i);
            System.out.println("  │ " + (i + 1) + ") " + p.getNombre() + " | Casas: " + p.getEstadoConstruccion() + " | Coste casa: €" + p.getPrecioCasa());
        }
        System.out.println("  └────────────────────────────────────┘");
        System.out.print("  Elige propiedad (número, 0 para cancelar): ");

        int seleccion;
        try {
            seleccion = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("  ✗ Opción inválida.");
            return;
        }

        if (seleccion == 0) {
            return;
        }
        if (seleccion < 1 || seleccion > construibles.size()) {
            System.out.println("  ✗ Opción inválida.");
            return;
        }

        Propiedad elegida = construibles.get(seleccion - 1);
        int coste = elegida.getPrecioCasa();
        if (jugadorActual.getSaldo() < coste) {
            System.out.println("  ✗ No tienes saldo suficiente para construir.");
            return;
        }

        boolean eraCuartaCasa = !elegida.isHotel() && elegida.getCasas() == 4;
        if (elegida.construirMejora()) {
            jugadorActual.setSaldo(jugadorActual.getSaldo() - coste);
            if (eraCuartaCasa) {
                System.out.println("  ✓ Construiste 1 hotel en " + elegida.getNombre() + ".");
            } else {
                System.out.println("  ✓ Construiste 1 casa en " + elegida.getNombre() + ".");
            }
        }
    }

    private static boolean poseeGrupoCompleto(Jugador jugador, String grupoColor) {
        int totalGrupo = 0;
        int poseidas = 0;

        for (Casilla casilla : tablero.getCasillas()) {
            if (casilla instanceof Propiedad) {
                Propiedad p = (Propiedad) casilla;
                if (p.getGrupoColor().equals(grupoColor)) {
                    totalGrupo++;
                    if (p.getPropietario() == jugador) {
                        poseidas++;
                    }
                }
            }
        }
        return totalGrupo > 0 && totalGrupo == poseidas;
    }

    private static void declararBancarrota(Jugador jugador) {
        List<Casilla> propiedades = new ArrayList<>(jugador.getPropiedadesCompradas());
        for (Casilla casilla : propiedades) {
            if (casilla instanceof Propiedad) {
                Propiedad p = (Propiedad) casilla;
                p.setPropietario(null);
                p.reiniciarMejoras();
            } else if (casilla instanceof Transporte) {
                ((Transporte) casilla).setPropietario(null);
            }
            jugador.quitarPropiedad(casilla);
        }

        int indiceActual = juego.getTurnoActual();
        int indiceEliminado = juego.getListaJugadores().indexOf(jugador);
        juego.getListaJugadores().remove(jugador);
        if (indiceEliminado >= 0 && indiceEliminado < indiceActual) {
            juego.setTurnoActual(Math.max(0, indiceActual - 1));
        }
    }

 */
}
